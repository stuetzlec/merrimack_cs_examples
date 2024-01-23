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

import java.security.Security;
import java.util.Set;
import java.security.Provider;

/**
 * List all supported mechanisms.
 *
 * @author Zach Kissel
 */
public class CryptoAlgorithms {
 public static void main(String[] args)
 {
	Set<String> algos;		// The supported mechanisms.
	Provider[] providers;		// The providers.

	// Setup the mechs.
	String[] mechs = new String[15];
	mechs[0] = "Signature";
	mechs[1] = "MessageDigest";
	mechs[2] = "Cipher";
	mechs[3] = "Mac";
	mechs[4] = "KeyStore";
	mechs[5] = "MessageDigest";
	mechs[6] = "SecretKeyFactory";
	mechs[7] = "SecureRandom";
	mechs[8] = "KeyFactory";
	mechs[9] = "AlgorithmParameters";
	mechs[10] = "AlgorithmParameterGenerator";
	mechs[11] = "CertificateFactory";
	mechs[12] = "CertPathBuilder";
	mechs[13] = "CertPathValidator";
	mechs[14] = "CertStore";

	System.out.println("Installed Providers");
	providers = Security.getProviders();
	for (Provider prov : providers)
		System.out.println("\t" + prov);
	System.out.println();

	for (String mech : mechs)
	{
	    System.out.println(mech);
	    algos = Security.getAlgorithms(mech);
	    Object[] algorithms = algos.toArray();
	    for (Object alg : algorithms)
		    System.out.println("\t" + alg);
	}
 }
}
