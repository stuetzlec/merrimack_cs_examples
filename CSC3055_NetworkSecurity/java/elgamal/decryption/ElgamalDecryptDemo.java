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
import java.security.PrivateKey;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import java.security.Security;
import javax.crypto.Cipher;
import java.security.spec.PKCS8EncodedKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.util.Base64;
import java.util.Scanner;

public class ElgamalDecryptDemo {
  public static void main(String[] args) throws NoSuchAlgorithmException,
      InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
      NoSuchPaddingException, InvalidKeySpecException
  {
    String privKey;
    String ciphertext;
    Scanner input = new Scanner(System.in);

    // Add the Bouncy Castle provider from The Legion of the Bouncy Castle.
    Security.addProvider(new BouncyCastleProvider());

    // Construct a new instance of Elgamal with no mode of operation and
    // no padding.
    Cipher cipher = Cipher.getInstance("ElGamal/None/NoPadding");

    // Prompt for ciphertext.
    System.out.print("Enter ciphertext: ");
    ciphertext = input.nextLine();

    // Prompt for the private key.
    System.out.print("Enter private key: ");
    privKey = input.nextLine();

    // Private keys are encoded in PKCS #8 format. Setup the appropriate
    // spec object
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(
        Base64.getDecoder().decode(privKey));
    PrivateKey privateKey = KeyFactory.getInstance(
        "ElGamal").generatePrivate(spec);

    // Do decryption with the private key.
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] plaintext = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
    System.out.println("\nPlaintext: " + new String(plaintext));
  }
}
