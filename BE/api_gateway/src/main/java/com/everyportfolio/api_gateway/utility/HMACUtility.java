package com.everyportfolio.api_gateway.utility;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class HMACUtility {

    public String generateHMAC(String str) throws Exception{
        MessageDigest sh = MessageDigest.getInstance("SHA-256");

        sh.update(str.getBytes());
        byte[] byteDate = sh.digest();
        StringBuilder resultBuilder = new StringBuilder();

        for(int i = 0; i < byteDate.length; i++) {
            resultBuilder.append(Integer.toString((byteDate[i] & 0xff) + 0x100, 16).substring(1));
        }

        return resultBuilder.toString();
    }
}
