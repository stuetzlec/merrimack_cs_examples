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
import java.io.Console;
import java.util.Scanner;
import java.util.Base64;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PBKDF2Example {
  public static void main(String[] args) throws NoSuchAlgorithmException,
      InvalidKeySpecException
  {
    Scanner in = new Scanner(System.in);
    Console cons = System.console();    // Get a copy of the console.
    String passwd;                  // The password as a string.
    byte[] iv;
    final int NUM_ITERS = 10000;  // The number of interations for the extactor.
    PBEKeySpec pbkdf2Spec;

    // Prompt for the password, when working with the IDE the console will
    // be null so we should fall back to showing the password. When run outside
    // of the IDE the console is non-null and thus the readPassword method
    // will be run.
    if (cons != null)
        passwd = new String(cons.readPassword("Password: "));
    else
    {
        System.out.print("Password: ");
        passwd = in.next();
    }

    // Get a 16-byte IV for an AES key.
    iv = new byte[16];
    SecureRandom rand = new SecureRandom();
    rand.nextBytes(iv);

    // Derive an AES key from the password using the password. The
    // lenght of the output is in bits.
    pbkdf2Spec = new PBEKeySpec(passwd.toCharArray(), iv, NUM_ITERS, 128);

    // Generate the secrete key.
    SecretKey key = SecretKeyFactory.getInstance(
        "PBKDF2WithHmacSHA512").generateSecret(pbkdf2Spec);

    // Display the key to the screen.
    System.out.println("AES Key is: " +
        Base64.getEncoder().encodeToString(key.getEncoded()));

  }
}
