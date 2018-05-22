/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.sun.org.apache.xml.internal.security.encryption.XMLCipher;
import com.sun.org.apache.xml.internal.security.encryption.XMLEncryptionException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.InitialContext;
import org.apache.xml.security.Init;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Tito
 */
public class XMLEncrypter {
   
    private static XMLEncrypter instance;
    private final static String AES = "AES";

    private XMLEncrypter(){}
    
    public static XMLEncrypter getInstance() {
        
        if (instance == null) {
            instance = new XMLEncrypter();
        }
        
        return instance;
    }


    public Document encrypt(String key, Document node, boolean encryptContentsOnly) throws XMLEncryptionException, Exception {
        
        com.sun.org.apache.xml.internal.security.Init.init();
        XMLCipher keyCipher = XMLCipher.getInstance(XMLCipher.AES_128);
        SecretKey symmetricKey = generateKey(key);
        
        keyCipher.init(XMLCipher.ENCRYPT_MODE, symmetricKey);
        
        Element element = node.getDocumentElement();
        Document encryptedDoc = keyCipher.doFinal(node, element);
        
        return encryptedDoc;
    }
    
    public Document decrypt(String key, Document node) throws XMLEncryptionException, Exception {
        
        XMLCipher cipher = XMLCipher.getInstance(XMLCipher.AES_128);
        SecretKey symmetricKey = generateKey(key);
        
        cipher.init(XMLCipher.DECRYPT_MODE, symmetricKey);
        
        Document decryotedDoc = cipher.doFinal(node, node.getDocumentElement());
        
        return decryotedDoc;
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
