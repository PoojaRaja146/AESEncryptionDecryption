package decryption;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESDecryption {
	String strToDecrypt;
	String secretkey;
	String salt;

	public AESDecryption(String strToDecrypt, String secretkey, String salt) {
		this.strToDecrypt = strToDecrypt;
		this.secretkey = secretkey;
		this.salt = salt;
	}

	public String decrypt(String strToDecrypt, String secretKey, String salt) throws Exception {
        byte[] ivBytes = new byte[16]; // IV length is 16 bytes to match block size of AES
        IvParameterSpec iv = new IvParameterSpec(ivBytes);//IV is used for having uniqueness in the encryption process
        SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING"); //class providing the functionality of a cryptographic cipher for encryption and decryption.
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv); //initializing Cipher object for Decryption

        byte[] original = cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));

        return new String(original);
    }

}
