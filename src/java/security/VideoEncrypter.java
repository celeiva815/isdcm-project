/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Tito
 */
public class VideoEncrypter {
    
    private static VideoEncrypter instance;
    private final static String UTF8 = "UTF-8";    
    private final static String AES = "AES";


    private VideoEncrypter(){}
    
    public static VideoEncrypter getInstance() {
        
        if (instance == null) {
            instance = new VideoEncrypter();
        }
        
        return instance;
    }
    
     public byte[] encryptBytes(byte[] bytes, String password) {

        try {

            SecretKeySpec secretKeySpec = generateKey(password);
            
            Cipher desCipher = Cipher.getInstance(AES);

            desCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] textEncrypted = desCipher.doFinal(bytes);

            //return URLEncoder.encode(new BASE64Encoder().encode(textEncrypted), UTF8);
            //return new BASE64Encoder().encode(textEncrypted);
            return textEncrypted;

        } catch(Exception e) {
            System.out.println("Exception");
        }
        
        return null;
    }
     
     public byte[] desencryptBytes(byte[] encryptedBytes, String password) {

        try {
            
            SecretKeySpec secretKeySpec = generateKey(password);
            
            Cipher desCipher;
            desCipher = Cipher.getInstance(AES);
            
            //String encryptedBase64 = URLDecoder.decode(encrypted, UTF8);
            //byte[] encryptedBytes = new BASE64Decoder().decodeBuffer( encryptedBase64 );

            desCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] textDecrypted = desCipher.doFinal(encryptedBytes);

            return textDecrypted;
            
        }catch(Exception e)
        {
            System.out.println("Exception");
        }
        
        return null;
    }
     
     
    public SecretKeySpec generateKey(String password) throws NoSuchAlgorithmException {
        
         // Get the Key
        byte[] key = password.getBytes();
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // use only first 128 bit

        return new SecretKeySpec(key, AES);
    }  
    
}
