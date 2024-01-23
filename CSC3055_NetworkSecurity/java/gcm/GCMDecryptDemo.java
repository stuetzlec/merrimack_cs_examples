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
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import java.util.Base64;
import java.util.Scanner;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.AEADBadTagException;

public class GCMDecryptDemo {
	public static void main(String[] args) throws
	    NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
      IllegalBlockSizeException, BadPaddingException,
			InvalidAlgorithmParameterException, AEADBadTagException
	{
		String key; // The Base64 encoded key.
		String ciphertext; // The Base64 encoded ciphertext.
		String iv;	// The initialization vector.
		int tagSize = 128;		// 128-bit authentication tag.

		// Set up an AES cipher object.
		Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");

		// Setup the input scanner.
		Scanner input = new Scanner(System.in);

		// Prompt for the ciphertext.
		System.out.print("Please enter ciphertext: ");
		ciphertext = input.nextLine();

		// Prompt for the key.
		System.out.print("Please enter the secret key: ");
		key = input.nextLine();

		// Prompt for the IV.
		System.out.print("Please enter the IV: ");
		iv = input.nextLine();

		// Setup the key.
		SecretKeySpec aesKey = new SecretKeySpec(Base64.getDecoder().decode(key),
		    "AES");

		// Put the cipher in encrypt mode with the specified key.
		aesCipher.init(Cipher.DECRYPT_MODE, aesKey,
		    new GCMParameterSpec(tagSize, Base64.getDecoder().decode(iv)));

		// Finalize the message.
		byte[] plaintext = aesCipher.doFinal(Base64.getDecoder().decode(ciphertext));

    System.out.println("Plaintext: " + new String(plaintext));

	}
}
