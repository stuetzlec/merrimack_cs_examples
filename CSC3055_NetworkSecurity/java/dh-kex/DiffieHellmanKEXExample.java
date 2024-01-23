
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

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

public class DiffieHellmanKEXExample {
  public static void main(String[] args) throws NoSuchAlgorithmException,
      InvalidKeyException, InvalidKeySpecException
  {
    KeyAgreement ecdhKex;
    String b64PubValue;       // The value "recieved"
    Scanner in = new Scanner(System.in);

    // Prompt for the value sent from the other side (a public key).
    System.out.print("Please enter the value received: ");
    b64PubValue = in.nextLine();

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
    generator.initialize(256);
    KeyPair pair = generator.generateKeyPair();

    // Output what is to be sent.
    System.out.println("Send: " + Base64.getEncoder().encodeToString(
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
    System.out.println("The DH-KEX shared secret matrial is: " +
        Base64.getEncoder().encodeToString(secret));

  }
}
