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
import java.security.PrivateKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Scanner;

public class RSASignDemo {
  public static void main(String[] args) throws NoSuchAlgorithmException,
      InvalidKeyException, SignatureException
  {
    String msg;   // The message to sign.
    Scanner in = new Scanner(System.in);

    // Prompt for a message to sign.
    System.out.print("Please enter a message to sign: ");
    msg = in.nextLine();

    // Construct a new instance of an RSA signature algorithm that uses
    // SHA-2 256 as its cryptographic hash function.
    Signature rsaSign = Signature.getInstance("SHA256withRSA");

    // Generate a pair of keys. Using Elgamal key generation.
    // The size of the key should be 2048-bits. Anything smaller is
    // too small for practical purposes.
    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
    generator.initialize(2048);
    KeyPair pair = generator.generateKeyPair();

    // Get the public and private key pair from the generated pair.
    PublicKey pubKey = pair.getPublic();
    PrivateKey privKey = pair.getPrivate();

    // Display the public and private keys.
    System.out.println("Public Key: " +
        Base64.getEncoder().encodeToString(pubKey.getEncoded()));
    System.out.println("Private Key: " +
        Base64.getEncoder().encodeToString(privKey.getEncoded()) + "\n");

    // You always sign with the private key. We therefore set up signing
    // as follows:
    rsaSign.initSign(privKey);

    // Can't sign in one go, must call update method, then call sign.
    rsaSign.update(msg.getBytes());
    byte[] signature = rsaSign.sign();

    System.out.println("Signature: " +
        Base64.getEncoder().encodeToString(signature) + "\n");
  }
}
