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
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.security.InvalidAlgorithmParameterException;
import java.nio.charset.StandardCharsets;


public class GCMEncryptDemo {
	public static void main(String[] args) throws
	    NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException,  InvalidAlgorithmParameterException
	{
		int tagSize = 128;
		// Set up an AES cipher object.
		Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");

		// Get a key generator object.
		KeyGenerator aesKeyGen = KeyGenerator.getInstance("AES");

		// Set the key size to 128 bits.
		aesKeyGen.init(128);

		// Generate the key.
		Key aesKey = aesKeyGen.generateKey();

		// Generate the IV.
		SecureRandom rand = new SecureRandom();
		byte[] rawIv = new byte[16];		// Block size of AES.
		rand.nextBytes(rawIv);					// Fill the array with random bytes.
		GCMParameterSpec gcmParams = new GCMParameterSpec(tagSize, rawIv);

		// Put the cipher in encrypt mode with the specified key.
		aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, gcmParams);

		// Finalize the message. Note: we have specified our type of character
		// encoding for our call to getBytes() this is, on occassion important.
		// Especially if an interface we use mixes UNICODE and ASCII.
		String msg = " Hello World!";
		byte[] ciphertext = aesCipher.doFinal(msg.getBytes(StandardCharsets.US_ASCII));

    System.out.println("Ciphertext: " + Base64.getEncoder().encodeToString(ciphertext));
    System.out.println("Key: " + Base64.getEncoder().encodeToString(aesKey.getEncoded()));
		System.out.println("IV: " + Base64.getEncoder().encodeToString(rawIv));
		System.out.println("Tag Size: " + tagSize + " bits.");
	}
}
