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
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
import java.util.Base64;
import java.util.Scanner;

public class RSAVerifyDemo {
  public static void main(String[] args) throws NoSuchAlgorithmException,
      InvalidKeyException, SignatureException, InvalidKeySpecException
  {
    String msg;   // The message to sign.
    String b64Sig;  // The signature encoded as a base-64 string.
    String b64PubKey;   // The public key as a base64 string.
    Scanner in = new Scanner(System.in);

    // Prompt for a message to sign.
    System.out.print("Please enter a message to verify: ");
    msg = in.nextLine();

    // Prompt for the signature.
    System.out.print("Please enter the signature: ");
    b64Sig = in.nextLine();

    // Prompt for the public key.
    System.out.print("Please enter the public key: ");
    b64PubKey = in.nextLine();

    // Construct a new instance of an RSA signature algorithm that uses
    // SHA-2 256 as its cryptographic hash function.
    Signature rsaSign = Signature.getInstance("SHA256withRSA");

    // Load in the public key.
    X509EncodedKeySpec spec = new X509EncodedKeySpec(
        Base64.getDecoder().decode(b64PubKey));
    PublicKey publicKey = KeyFactory.getInstance(
        "RSA").generatePublic(spec);

    // You always verify with the public key. We therefore set up signing
    // as follows:
    rsaSign.initVerify(publicKey);

    // Can't verify in one go, must call update method to load in the message,
    // then call verification with the signature.
    rsaSign.update(msg.getBytes());

    System.out.println("\nThe signature verification is: " +
        rsaSign.verify(Base64.getDecoder().decode(b64Sig)));
  }
}
