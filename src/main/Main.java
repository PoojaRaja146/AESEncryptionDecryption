package main;

import decryption.AESDecryption;
import encryption.AESEncrypt;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Secret message to be encrypted");
        System.out.println(args[0]);
        System.out.println("Secret key of 128 or 256 bits (Input 16 digit for 128 bit key or 32 digit for 256 bit key)");
        System.out.println(args[1]);
        System.out.println("Salt value (Atleast 16 bytes long)");
        System.out.println(args[2]);
        encryptDecrypt(args[0], args[1], args[2]);
    }

    private static void encryptDecrypt(String originalString, String secretKey, String salt) {
        try {

            AESEncrypt aesencrypt = new AESEncrypt(originalString, secretKey, salt);
            String encryptedText = aesencrypt.encrypt(originalString, secretKey, salt); //Encrypted plaintext
            AESDecryption aesDecryption = new AESDecryption(encryptedText, secretKey, salt);
            String decryptedText = aesDecryption.decrypt(encryptedText, secretKey, salt); //Decrypted text or original plain text
            int keyLength = lengthOfKey(secretKey);
            System.out.println("Encrypted text with secretKey of "+ keyLength + "bits " + encryptedText);
            System.out.println("Decrypted text with secretKey of " + keyLength + "bits " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int lengthOfKey(String secretKey) {
        int lengthInCharacters = secretKey.length(); // Get the number of characters in the string
        int bits = lengthInCharacters * 8;
        return bits;
    }


}
