/*
 *   Copyright (C) 2018 -- 2023  Zachary A. Kissel
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import javax.crypto.Cipher;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.security.InvalidKeyException;
import java.util.zip.CRC32;
import java.util.Base64;
import java.util.Arrays;

/**
 * This file demonstrates WEP LLC Encryption
 *
 * @author Zach Kissel
 */
class WEPEncrypt
{
  public static void main(String[] args) throws NoSuchAlgorithmException,
      InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
      NoSuchPaddingException
  {
    SecureRandom rand = new SecureRandom();
    String msg = "Hello World";

    // Generate the 40 bit (5 bytes) key (Normally pre-shared).
    byte[] key = new byte[5];
    rand.nextBytes(key);

    // Generate 24 bit (3 byte) IV
    byte[] iv = new byte[3];
    rand.nextBytes(iv);

    // Encrypt the frame
    byte[] ctext = LLCEncrypt(iv, key, prepareMessage(msg));

    System.out.println("IV: " + Base64.getEncoder().encodeToString(iv));
    System.out.println("Ciphertext: " +
        Base64.getEncoder().encodeToString(ctext));


    // Decrypt and verify.
    byte[] ptext = LLCDecrypt(iv, key, ctext);

    if (ptext != null)
      System.out.println("Decrypted Message: " + new String(ptext));
  }

  /**
   * This method generates the per frame key and uses it to encrypt the
   * frame.
   *
   * @param iv the initialization vector.
   * @param key the master key
   * @param blocks the message blocks.
   * @return the encrypted message (frame) as bytes.
   */
  public static byte[] LLCEncrypt( byte[] iv, byte[] key, byte[] blocks) throws
      NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException,
      BadPaddingException, NoSuchPaddingException
  {

    Cipher rc4;       // The cipher object
    SecretKey pfk;    // The per frame key.

    // ARC4 is the open standard version of RC4.
    rc4 = Cipher.getInstance("ARCFOUR");

    // Build the per frame key.
    pfk = generatePerFrameKey(iv, key);

    // Carry out the encryption.
    rc4.init(Cipher.ENCRYPT_MODE, pfk);

    byte[] ctext = rc4.doFinal(blocks);

    return ctext;
  }

  /**
   * This method generates the per frame key and uses it to decrypt the
   * frame.
   *
   * @param iv the initialization vector.
   * @param key the master key
   * @param blocks the message blocks.
   * @return the encrypted message (frame) as bytes if ICV is valid; otherwise,
   * return null.
   */
  public static byte[] LLCDecrypt( byte[] iv, byte[] key, byte[] blocks) throws
      NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException,
      BadPaddingException, NoSuchPaddingException
  {

    Cipher rc4;       // The cipher object
    SecretKey pfk;    // The per frame key.
    byte[] icv;       // The integrity check value.
    byte[] msg;       // The message bytes.
    CRC32 cksum = new CRC32();

    // ARC4 is the open standard version of RC4.
    rc4 = Cipher.getInstance("ARCFOUR");

    // Build the per frame key.
    pfk = generatePerFrameKey(iv, key);

    // Carry out the encryption.
    rc4.init(Cipher.DECRYPT_MODE, pfk);

    // Decrypt.
    byte[] ptext = rc4.doFinal(blocks);

    // Split IV and message.
    icv = new byte[8];
    msg = new byte[ptext.length - 4];

    System.arraycopy(ptext, ptext.length - 4, icv, 4, 4);
    System.arraycopy(ptext, 0, msg, 0, ptext.length - 4);

    // Verify the ICV and return the plaintext if the ICV
    // verifies; otherwise, return null.
    cksum.update(msg);

    if (Arrays.equals(icv, longToBytes(cksum.getValue())))
      return msg;

    return null;
  }

  /**
   * This method takes a message, appends the CRC32 ICV and retusn the
   * result blocks (bytes) to the caller.
   *
   * @param msg the message to encrypt.
   * @return the message block as bytes.
   */
  public static byte[] prepareMessage(String msg)
  {
    CRC32 cksum = new CRC32();
    byte[] cksumValue;

    // Compute the message's CRC-32 value (the ICV).
    cksum.update(msg.getBytes());
    cksumValue = longToBytes(cksum.getValue());

    // Join the array of bytes.
    byte[] blocks = new byte[msg.getBytes().length + 4];
    System.arraycopy(msg.getBytes(), 0, blocks, 0, msg.getBytes().length);
    System.arraycopy(cksumValue, 4, blocks, msg.getBytes().length, 4);

    return blocks;
  }

  /**
   * This method constructs the perframe key from the IV and master key.
   *
   * @param iv the initialization vector.
   * @param key the master key.
   * @returns an ARC4 secret key which serves as the per frame key.
   */
   private static SecretKey generatePerFrameKey(byte[] iv, byte[] key)
   {
     // construct the per-frame key bytes.
     byte[] pfk = new byte[8];
     System.arraycopy(iv, 0, pfk, 0, iv.length);
     System.arraycopy(key, 0, pfk, iv.length, key.length);

     return new SecretKeySpec(pfk, "ARCFOUR");
   }

  /**
   * This method converts a long value into an 8-byte value.
   *
   * @param num the number to convert to bytes.
   * @return an array of 8 bytes representing the number num.
   */
   private static byte[] longToBytes(long num)
   {
     byte[] res = new byte[8];

     // Decompose the a long type into byte components.
     for (int i = 7; i >= 0; i--)
     {
      res[i] = (byte)(num & 0xFF);
      num >>= 8;
     }

    return res;
  }
}
