/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.io.UnsupportedEncodingException;
import javax.crypto.SecretKey;

/**
 *
 * @author Tito
 */
public class EncryptedVideoString  {
    
    private String encrypted;
    private SecretKey key;

    public EncryptedVideoString(String encrypted, SecretKey key) {
        this.encrypted = encrypted;
        this.key = key;
    }
    
    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public SecretKey getKey() {
        return key;
    }

    public void setKey(SecretKey key) {
        this.key = key;
    }

    
}
