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
 import javax.crypto.Mac;
 import javax.crypto.SecretKey;
 import javax.crypto.KeyGenerator;
 import java.security.NoSuchAlgorithmException;
 import java.security.InvalidKeyException;
 import java.util.Base64;

/**
 * This file implements a simple MAC demo using SHA2
 *
 * @author Zach Kissel
 */
public class MACDemoSHA2 {
   public static void main(String[] args) throws NoSuchAlgorithmException,
       InvalidKeyException
   {
     SecretKey key;
     byte[] tag;

    // Get a new HMAC instance.
     Mac hmac = Mac.getInstance("HmacSHA256");

     // Construct an key for the HMAC.
     KeyGenerator hmacKeyGen = KeyGenerator.getInstance("HmacSHA256");
     key = hmacKeyGen.generateKey();

     // Set the key for the HMAC.
     hmac.init(key);

     // Compute the HMAC of a string.
     tag = hmac.doFinal(
         "An HMAC is an integrity protection mechanism.".getBytes());

    // Display the tag.
    System.out.println("Tag: " + Base64.getEncoder().encodeToString(tag));

   }
 }
