/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Tito
 */
public class VideoEncrypter {
    
    private static VideoEncrypter instance;
    private final static String UTF8 = "UTF8";    
    private final static String DES = "DES";


    private VideoEncrypter(){}
    
    public static VideoEncrypter getInstance() {
        
        if (instance == null) {
            instance = new VideoEncrypter();
        }
        
        return instance;
    }
    
     public EncryptedVideoString encryptString(String text) {

        try {
            
            KeyGenerator keygenerator = KeyGenerator.getInstance(DES);
            SecretKey myDesKey = keygenerator.generateKey();

            Cipher desCipher = Cipher.getInstance(DES);
            byte[] bytes = text.getBytes(UTF8);

            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(bytes);

            String s = new String(textEncrypted);
            
            return new EncryptedVideoString(s, myDesKey);
         
        } catch(Exception e) {
            System.out.println("Exception");
        }
        
        return null;
    }
     
     public String desencryptString(EncryptedVideoString encrypted) {

        try {
            
            Cipher desCipher;
            desCipher = Cipher.getInstance(DES);

            byte[] bytes = encrypted.getEncrypted().getBytes(UTF8);

            desCipher.init(Cipher.DECRYPT_MODE, encrypted.getKey());
            byte[] textDecrypted = desCipher.doFinal(bytes);

            return new String(textDecrypted);
            
        }catch(Exception e)
        {
            System.out.println("Exception");
        }
        
        return null;
    }
     
    
}
