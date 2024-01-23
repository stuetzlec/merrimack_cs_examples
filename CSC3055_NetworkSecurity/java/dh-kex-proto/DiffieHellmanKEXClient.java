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

import java.security.PublicKey;
import java.security.KeyPair;
import javax.crypto.KeyAgreement;
import java.security.KeyPairGenerator;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Scanner;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.net.UnknownHostException;
import java.io.IOException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import java.security.InvalidAlgorithmParameterException;

/**
 * A simple demo of the DH KEX for the initiator.
 *
 * @author Zach Kissel
 */
public class DiffieHellmanKEXClient {
  public static int PORT_NUM = 5000;      // Default port.

  public static String doEncryption(byte[] rawKey, String msg) throws
      NoSuchAlgorithmException, NoSuchPaddingException,
      InvalidKeyException, IllegalBlockSizeException,
      BadPaddingException, InvalidAlgorithmParameterException
  {
    byte[] rawIV = new byte[16];

    // Set up an AES cipher object.
    Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");


    // Generate the IV for CBC mode.
    SecureRandom rand = new SecureRandom();
    rand.nextBytes(rawIV);          // Fill array with random bytes.
    IvParameterSpec iv = new IvParameterSpec(rawIV);

    // Put the cipher in encrypt mode with the generated key
    // and IV. The Cipher object will save message bytes given
    // to it until doFinal is called, once that happens, the internal
    // state will be reset.
    aesCipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(rawKey, "AES"), iv);

    // Encrypt the entire message at once. The doFinal method
    // will ensure padding is applied before encrypting the message.
    // One could use the update method instead but, that method returns
    // the blocks that were encrypted with each call. The doFinal method
    // still needs to be called to handle the partial block.
    byte[] ciphertext = aesCipher.doFinal(msg.getBytes());

    return Base64.getEncoder().encodeToString(rawIV) + ":" +
       Base64.getEncoder().encodeToString(ciphertext);
  }

  public static String doDecryption(byte[] rawKey, String msg) throws
      NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
      IllegalBlockSizeException, BadPaddingException,
      InvalidAlgorithmParameterException
  {
    String[] parts = msg.split(":");
    // Set up an AES cipher object.
    Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");


    // Put the cipher in encrypt mode with the generated key
    // and IV. The Cipher object will save message bytes given
    // to it until doFinal is called, once that happens, the internal
    // state will be reset.
    aesCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(rawKey, "AES"),
        new IvParameterSpec(Base64.getDecoder().decode(parts[0])));

    // Encrypt the entire message at once. The doFinal method
    // will ensure padding is applied before encrypting the message.
    // One could use the update method instead but, that method returns
    // the blocks that were encrypted with each call. The doFinal method
    // still needs to be called to handle the partial block.
    byte[] plaintext = aesCipher.doFinal(Base64.getDecoder().decode(parts[1]));

    return new String(plaintext);
  }

  public static void main(String[] args) throws NoSuchAlgorithmException,
      InvalidKeyException, InvalidKeySpecException, UnknownHostException,
      IOException, NoSuchPaddingException,
      IllegalBlockSizeException, BadPaddingException,
      InvalidAlgorithmParameterException
  {
    KeyAgreement ecdhKex;     // Key agremeent protocol object.
    String ipAddr;            // The IP address
    String b64PubValue;       // The value received from server.

    Socket sock;
    PrintWriter send;
    Scanner recv;

    Scanner in = new Scanner(System.in);

    // Prompt for the value sent from the other side (a public key).
    System.out.print("Please enter the server's IP: ");
    ipAddr = in.nextLine();

    // Make connection on established port and set up the streams.
    sock = new Socket(ipAddr, PORT_NUM);
    recv = new Scanner(sock.getInputStream());
    send = new PrintWriter(sock.getOutputStream(), true);

    // Construct a new KEX algoritm.
    // Current best practices use Elliptic Curve Diffie Hellman.
    // Elliptic Curves a group like Z_p^* but, offer better
    // security guarantees.
    ecdhKex = KeyAgreement.getInstance("ECDH");

    // Generate a pair of Elliptic Curve keys.
    KeyPairGenerator generator = KeyPairGenerator.getInstance("EC");
//    generator.initialize(128);
    KeyPair pair = generator.generateKeyPair();

    // Send the public value.
    send.println(Base64.getEncoder().encodeToString(
        pair.getPublic().getEncoded()));

    // Output my public data.
    System.out.println("Sending Public Value: " +
        Base64.getEncoder().encodeToString(pair.getPublic().getEncoded()));

    // Start the exchnage with the "private key". Technically, this is
    // simply the private value.
    ecdhKex.init(pair.getPrivate());

    // Wait for the response from the other side.
    b64PubValue = recv.nextLine();

    // Load the public value from the other side.
    X509EncodedKeySpec spec = new X509EncodedKeySpec(
      Base64.getDecoder().decode(b64PubValue));
    PublicKey pubKey = KeyFactory.getInstance("EC").generatePublic(spec);

    // DH-KEX can be achieved with 1 round-trip message. We provide the
    // doPhase method is invoked only once. Since, this is the last message
    // the second argument to doPhase must be true.
    ecdhKex.doPhase(pubKey, true);

    // The DH-KEX algorithm is complete, inform the key generator we wish
    // to generate an AES-128 key.
    byte[] secret = ecdhKex.generateSecret();

    // Display the shared secret.
    System.out.println("Sharing key material " +
        Base64.getEncoder().encodeToString(secret) + " with " +
        sock.getInetAddress());

    // The first 16-bytes of the secret can be used as the AES key, lets
    // do that.
    byte[] rawKey = Arrays.copyOfRange(secret, 0, 16);

    System.out.print("Enter message to send: ");
    String msg = in.nextLine();

    send.println(doEncryption(rawKey, msg));

    System.out.println("Response: " + doDecryption(rawKey, recv.nextLine()));




  }
}
