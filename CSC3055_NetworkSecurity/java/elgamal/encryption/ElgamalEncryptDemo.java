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
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import java.security.Security;
import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.util.Base64;

public class ElgamalEncryptDemo {
  public static void main(String[] args) throws NoSuchAlgorithmException,
      InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
      NoSuchPaddingException
  {
    // Add the Bouncy Castle provider from The Legion of the Bouncy Castle.
    Security.addProvider(new BouncyCastleProvider());

    // Construct a new instance of Elgamal with no mode of operation and
    // no padding.
    Cipher cipher = Cipher.getInstance("ElGamal/None/NoPadding");

    // Generate a pair of keys. Using Elgamal key generation.
    // The size of the key should be 512-bits. Anything smaller is
    // too small for practical purposes.
    KeyPairGenerator generator = KeyPairGenerator.getInstance("ElGamal");
    generator.initialize(512);
    KeyPair pair = generator.generateKeyPair();

    // Get the public and private key pair from the generated pair.
    PublicKey pubKey = pair.getPublic();
    PrivateKey privKey = pair.getPrivate();

    // Display the public and private keys.
    System.out.println("Public Key: " +
        Base64.getEncoder().encodeToString(pubKey.getEncoded()));
    System.out.println("Private Key: " +
        Base64.getEncoder().encodeToString(privKey.getEncoded()) + "\n");

    // Do encryption with the public key.
    cipher.init(Cipher.ENCRYPT_MODE, pubKey);
    byte[] cipherText = cipher.doFinal("Hello World".getBytes());
    System.out.println("Ciphertext: " +
        Base64.getEncoder().encodeToString(cipherText) + "\n");
  }
}
