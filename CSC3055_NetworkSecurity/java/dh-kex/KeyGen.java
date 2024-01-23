/**
 * This file implements a file encryption service that encrypts a file
 * with a symmetric key and then encrypts the key with a public key cipher.
 * This is similar to how PGP works.
 *
 * @author Zach Kissel
 */
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.Cipher;
import java.io.FileNotFoundException;
import java.security.InvalidAlgorithmParameterException;

import java.util.Base64;

public class KeyGen {

  public static void saveKey(String fname, String b64data) throws
      FileNotFoundException
  {
    File file = new File(fname);
    PrintWriter out = new PrintWriter(file);
    out.println(b64data);
    out.close();
  }

  public static void main(String[] args) throws NoSuchAlgorithmException,
      InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
      NoSuchPaddingException, FileNotFoundException,
      InvalidAlgorithmParameterException, FileNotFoundException
  {


    // Generate a pair of keys. Using Elliptic Curve key generation.
    KeyPairGenerator generator = KeyPairGenerator.getInstance("EC");
    generator.initialize(256);
    KeyPair pair = generator.generateKeyPair();

    // Get the public and private key pair from the generated pair.
    PublicKey pubKey = pair.getPublic();
    PrivateKey privKey = pair.getPrivate();

    saveKey("pubkey.txt",
        Base64.getEncoder().encodeToString(pubKey.getEncoded()));
    saveKey("privkey.txt",
        Base64.getEncoder().encodeToString(privKey.getEncoded()));
  }
}
