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
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.util.Base64;

public class ElgamalKeyGen {
  public static void main(String[] args) throws
	    NoSuchAlgorithmException
  {
    // Add the Bouncy Castle provider from The Legion of
		// the Bouncy Castle.
    Security.addProvider(new BouncyCastleProvider());

    // Generate a pair of keys. Using Elgamal key generation.
    // The size of the key should be 512-bits. Anything smaller is
    // too small for practical purposes.
    KeyPairGenerator elgamalKeyGen = KeyPairGenerator.getInstance(
		    "ElGamal");
    elgamalKeyGen.initialize(512);
    KeyPair elgamalPair = elgamalKeyGen.generateKeyPair();

    // Get the public and private key pair from the generated
		// pair.
    PublicKey pubKey = elgamalPair.getPublic();
    PrivateKey privKey = elgamalPair.getPrivate();

    // Display the public and private keys.
    System.out.println("Public Key: " +
        Base64.getEncoder().encodeToString(pubKey.getEncoded()) +
				"\n");
    System.out.println("Private Key: " +
        Base64.getEncoder().encodeToString(privKey.getEncoded()) +
				"\n");
 }
}
