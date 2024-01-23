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
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Base64;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateFactory;

import java.io.FileNotFoundException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.NoSuchProviderException;

public class X509Example {
  public static void main(String[] args) throws FileNotFoundException,
      CertificateException, NoSuchAlgorithmException, InvalidKeyException,
      NoSuchProviderException
  {
    String certFileName;
    X509Certificate cert;
    CertificateFactory certFactory;

    // Setup the scanner object.
    Scanner in = new Scanner(System.in);

    // Prompt for the certificate file.
    System.out.print("Please enter the name of the certificate file: ");
    certFileName = in.nextLine();

    // Setup the X.509 certificate factory.
    certFactory = CertificateFactory.getInstance("X.509");

    // Load the certificate from the named file.
    cert = (X509Certificate) certFactory.generateCertificate(
        new FileInputStream(new File(certFileName)));

    // Print some information about the certificate.
    System.out.println("\nIssuer: " + cert.getIssuerX500Principal().getName());
    System.out.println("\nPrinciple: " +
        cert.getSubjectX500Principal().getName());

    // Verify the certificate usings it's own public key as the certificate
    // self signed. Not self signed certifcates would use a different public
    // key.
    try
    {
      cert.verify(cert.getPublicKey());
      System.out.println("\nValid: Yes");
      System.out.println("Public Key: " +
          Base64.getEncoder().encodeToString(cert.getPublicKey().getEncoded()));
      System.out.println("Algorithm: " + cert.getPublicKey().getAlgorithm());
    }
    catch(SignatureException sigEx)
    {
      System.out.println("\nValid: No");
      System.out.println("Public Key: N/A");
    }
  }
}
