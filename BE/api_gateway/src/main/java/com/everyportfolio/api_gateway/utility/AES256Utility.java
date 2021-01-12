package com.everyportfolio.api_gateway.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;

import org.apache.commons.codec.binary.Base64;

@Component
public class AES256Utility {
    private Logger log = LoggerFactory.getLogger(AES256Utility.class);

    private byte[] iv;
    private Key keySpec;

    public AES256Utility() throws NoSuchAlgorithmException, UnsupportedEncodingException {
       byte[] tmp = new byte[512];

        SecureRandom.getInstance("SHA1PRNG").nextBytes(tmp);
        String key = new String(tmp);

        iv = new byte[16];
        SecureRandom.getInstance("SHA1PRNG").nextBytes(iv);

        byte[] b = key.getBytes("UTF-8");
        byte[] keyBytes = new byte[16];

        int len = b.length;
        if(b.length > keyBytes.length)
            len = keyBytes.length;

        System.arraycopy(b, 0, keyBytes, 0, len);
        this.keySpec = new SecretKeySpec(keyBytes, "AES");
    }

    public String encrypt(String str) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));
        return enStr;
    }

    public String decrypt(String str) throws GeneralSecurityException, UnsupportedEncodingException {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
        byte[] byteStr = Base64.decodeBase64(str.getBytes());
        return new String(c.doFinal(byteStr), "UTF-8");
    }

}
