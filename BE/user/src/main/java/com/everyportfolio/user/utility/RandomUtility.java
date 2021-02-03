package com.everyportfolio.user.utility;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RandomUtility {
    public String generateRandomString(int length) {
        StringBuilder result = new StringBuilder();
        byte[] tmp = new byte[length / 2];

        try {
            SecureRandom.getInstance("SHA1PRNG").nextBytes(tmp);

            for (int i = 0; i < tmp.length; i++)
                result.append(String.format("%02X", tmp[i]));
        }catch (Exception e) {

        }

        return result.toString();
    }
}
