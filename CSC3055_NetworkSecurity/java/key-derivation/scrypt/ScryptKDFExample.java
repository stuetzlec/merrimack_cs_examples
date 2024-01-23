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
import java.security.Security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jcajce.spec.ScryptKeySpec;

public class ScryptKDFExample {
  public static void main(String[] args) throws NoSuchAlgorithmException,
      InvalidKeySpecException
  {
    Scanner in = new Scanner(System.in);
    Console cons = System.console();    // Get a copy of the console.
    String passwd;                  // The password as a string.
    byte[] iv;
    final int COST = 2048;          // A.K.A Iterations
    final int BLK_SIZE = 8;
    final int PARALLELIZATION = 1;  // Number of parallel threads to use.
    final int KEY_SIZE=128;
    ScryptKeySpec scryptSpec;


    // Register bouncy castle provider.
    Security.addProvider(new BouncyCastleProvider());

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

    SecretKeyFactory factory = SecretKeyFactory.getInstance("SCRYPT");

    // Get a 16-byte IV for an AES key.
    iv = new byte[16];
    SecureRandom rand = new SecureRandom();
    rand.nextBytes(iv);

    // Derive an AES key from the password using the password. The memory
    // required to run the derivation, in bytes, is:
    //    128 * COST * BLK_SIZE * PARALLELIZATION.
    // The password argument expects and array of charaters *not* bytes.
    //
    scryptSpec = new ScryptKeySpec(passwd.toCharArray(), iv, COST, BLK_SIZE,
        PARALLELIZATION, KEY_SIZE);


    // Generate the secrete key.
    SecretKey key = SecretKeyFactory.getInstance("SCRYPT").generateSecret(
        scryptSpec);

    // Display the key to the screen.
    System.out.println("AES Key is: " +
        Base64.getEncoder().encodeToString(key.getEncoded()));

  }
}
