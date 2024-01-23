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
import java.net.ServerSocket;
import java.util.Arrays;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
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
public class DiffieHellmanMITM {
  public static int PROXY_PORT = 5100;      // Port for the attacker to listen on.

  /**
   * This method constructs a key with the requester who has been ridrected
   * to our malicious proxy.
   *
   * @param sock the socket on which to recieve the data from the dupped client.
   */
  public static String buildKeyWithRequester(Socket sock) throws
      NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException,
      IOException
  {
    KeyAgreement ecdhKex;     // Key agremeent protocol object.
    String b64PubValue;

    Scanner recv = new Scanner(sock.getInputStream());
    PrintWriter send = new PrintWriter(sock.getOutputStream(), true);

    // Get the public value the other side.
    b64PubValue = recv.nextLine();

    // Load the public value from the other side.
    X509EncodedKeySpec spec = new X509EncodedKeySpec(
      Base64.getDecoder().decode(b64PubValue));
    PublicKey pubKey = KeyFactory.getInstance("EC").generatePublic(spec);

    // Construct a new KEX algoritm.
    // Current best practices use Elliptic Curve Diffie Hellman.
    // Elliptic Curves a group like Z_p^* but, offer better
    // security guarantees.
    ecdhKex = KeyAgreement.getInstance("ECDH");

    // Generate a pair of Elliptic Curve keys.
    KeyPairGenerator generator = KeyPairGenerator.getInstance("EC");
    KeyPair pair = generator.generateKeyPair();

    // Send the public value.
    send.println(Base64.getEncoder().encodeToString(
        pair.getPublic().getEncoded()));

    // Start the exchnage with the "private key". Technically, this is
    // simply the private value.
    ecdhKex.init(pair.getPrivate());

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

    return Base64.getEncoder().encodeToString(secret);
  }

  /**
   * Builds a key with the server that makes the sever think its talking with
   * the client. In actuality it is talking with our proxy.
   *
   * @param sock the socket to communicated with.
   */
  public static String buildKeyWithServer(Socket sock) throws
      NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException,
      IOException
  {
    KeyAgreement ecdhKex;     // Key agremeent protocol object.
    String b64PubValue;       // The value received from server.

    // Make connection on established port and set up the streams.
    Scanner recv = new Scanner(sock.getInputStream());
    PrintWriter send = new PrintWriter(sock.getOutputStream(), true);

    // Construct a new KEX algoritm.
    // Current best practices use Elliptic Curve Diffie Hellman.
    // Elliptic Curves a group like Z_p^* but, offer better
    // security guarantees.
    ecdhKex = KeyAgreement.getInstance("ECDH");

    // Generate a pair of Elliptic Curve keys.
    KeyPairGenerator generator = KeyPairGenerator.getInstance("EC");
    KeyPair pair = generator.generateKeyPair();

    // Send the public value.
    send.println(Base64.getEncoder().encodeToString(
        pair.getPublic().getEncoded()));

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

    return Base64.getEncoder().encodeToString(secret);
  }

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

  /**
   * Marshalls the message from the client and the servers response outputting
   * the data to the screen.
   */
  public static void processMessage(Socket client, Socket server, byte[] rawKeyC,
      byte[] rawKeyS) throws NoSuchAlgorithmException, NoSuchPaddingException,
      InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
      InvalidAlgorithmParameterException, IOException
  {
    // Setup streams for client socket.
    Scanner clientRecv = new Scanner(client.getInputStream());
    PrintWriter clientSend = new PrintWriter(client.getOutputStream(), true);

    // Setup streams for server socket.
    Scanner serverRecv = new Scanner(server.getInputStream());
    PrintWriter serverSend = new PrintWriter(server.getOutputStream(), true);

    // Get message from client side.
    String msgFromClient = clientRecv.nextLine();
    String ptext = doDecryption(rawKeyC, msgFromClient);

    System.out.println("Client Msg: " + ptext);

    // Encrypt and send it to the server side.
    serverSend.println(doEncryption(rawKeyS, ptext));

    // Decrypt the result from the server and send it back to the client.
    String msgFromServer = serverRecv.nextLine();
    String ptext2 = doDecryption(rawKeyS, msgFromServer);

    System.out.println("Server Msg: " + ptext2);

    clientSend.println(doEncryption(rawKeyC, ptext2));
  }

  public static void main(String[] args) throws NoSuchAlgorithmException,
      InvalidKeyException, InvalidKeySpecException, IOException,
      NoSuchPaddingException,
      IllegalBlockSizeException, BadPaddingException,
      InvalidAlgorithmParameterException
  {
    ServerSocket server;
    Socket clientSide;
    Socket serverSide;

    if (args.length != 2)
    {
      System.out.println("Usage: DiffieHellmanMITM <server-IP> <server-port>");
      return;
    }

    // Listen for an incomming connection. This will act
    // like a proxy for the incomming connection.
    System.out.println("Listening on port " + PROXY_PORT + "\n");
    server = new ServerSocket(PROXY_PORT);

    while (true)
    {
      // Accept a new connection and set up the streams.
      clientSide = server.accept();
      System.out.println("Received a connection from " + clientSide.getInetAddress());
      String secretWithAlice = buildKeyWithRequester(clientSide);

      byte[] rawKeyWithClient = Arrays.copyOfRange(
          Base64.getDecoder().decode(secretWithAlice), 0, 16);

      // Create a key with the server pretending to be the
      serverSide = new Socket(args[0], Integer.parseInt(args[1]));
      String secretWithBob = buildKeyWithServer(serverSide);

      byte[] rawKeyWithServer = Arrays.copyOfRange(
          Base64.getDecoder().decode(secretWithBob), 0, 16);

      processMessage(clientSide, serverSide, rawKeyWithClient,
          rawKeyWithServer);

      serverSide.close();
      clientSide.close();

   }
  }
}
