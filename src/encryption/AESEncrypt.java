package encryption;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncrypt {
	String originalString; 
	String secretKey;
	String salt;

	public AESEncrypt(String originalString, String secretKey, String salt) {
		this.originalString = originalString;
		this.secretKey = secretKey;
		this.salt = salt;		
	}

	public String encrypt(String originalString, String secretKey, String salt) throws Exception {
		byte[] ivBytes = new byte[16]; // IV length is 16 bytes to match the block size of AES
        IvParameterSpec iv = new IvParameterSpec(ivBytes); //IV used in encryption mode like CBC for adding randomness in the encryption process
        SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING"); //class for encryption and decryption process, AES-encryption algorithm, CBS-mode of operation, PKCS5PADDING - padding to fit the input to algorithm's block size.
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv); //initializing Cipher object for Encryption

        byte[] encrypted = cipher.doFinal(originalString.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

}
