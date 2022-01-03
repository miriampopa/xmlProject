package com.library_project.utils;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class Common {

    public static int getRandomNumber(){
        SecureRandom secureRandomGenerator = null;
        try {
            secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG", "SUN");

        // Get 128 random bytes
        byte[] randomBytes = new byte[128];
        secureRandomGenerator.nextBytes(randomBytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        //Get random integer
        return secureRandomGenerator.nextInt();
    }
}
