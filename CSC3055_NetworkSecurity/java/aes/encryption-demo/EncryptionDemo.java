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
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.InvalidKeyException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.security.InvalidAlgorithmParameterException;

/**
 * This file demonstrates symmetric encryption of a message using a AES128
 * in CBC mode with PKCS #5(#7) padding.
 * 
 * @author Zach Kissel
 */
public class EncryptionDemo {
    public static void main(String[] args) throws
        NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, IllegalBlockSizeException,
        BadPaddingException, InvalidAlgorithmParameterException
    {
        String msg;         // The message to encrypt.
        Cipher aesCipher;   // The cipher object
        KeyGenerator aesKeyGen;          // The AES keygenerator.
        SecureRandom rand;               // A secure random number generator.
        byte[] rawIV = new byte[16];     // An AES init. vector.
        IvParameterSpec iv;       // The IV parameter for CBC. Different ciphers
                                  // may have different specifications.
        
        // Make sure we have the correct command line arguments
        if (args.length != 1)
        {
            System.out.println("Usage: EncryptionDemo <msg>");
            System.exit(1);
        }
                                  
        // Set up an AES cipher object.
        aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        System.out.print("Generating key . . . ");
        // Get a key generator object and set the key size to 128 bits.
        aesKeyGen = KeyGenerator.getInstance("AES");
        aesKeyGen.init(128);

        // Generate the key.
        Key aesKey = aesKeyGen.generateKey();
        System.out.println("[ DONE ]");

        // Generate the IV for CBC mode.
        System.out.print("Generating IV  . . . ");
        rand = new SecureRandom();
        rand.nextBytes(rawIV);          // Fill array with random bytes.
        iv = new IvParameterSpec(rawIV);
        System.out.println("[ DONE ]");

        // Put the cipher in encrypt mode with the generated key 
        // and IV. The Cipher object will save message bytes given 
        // to it until doFinal is called, once that happens, the internal 
        // state will be reset.
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, iv);

        // Encrypt the entire message at once. The doFinal method 
        // will ensure padding is applied before encrypting the message.
        // One could use the update method instead but, that method returns 
        // the blocks that were encrypted with each call. The doFinal method 
        // still needs to be called to handle the partial block.
        byte[] ciphertext = aesCipher.doFinal(args[0].getBytes());

        // Display the relevant Base-64 data to the screen.
        System.out.println();
        System.out.println("Ciphertext: " + 
                Base64.getEncoder().encodeToString(ciphertext));
        System.out.println("       Key: " + 
                Base64.getEncoder().encodeToString(aesKey.getEncoded()));
        System.out.println("        IV: " +
                Base64.getEncoder().encodeToString(rawIV));
    }
}
