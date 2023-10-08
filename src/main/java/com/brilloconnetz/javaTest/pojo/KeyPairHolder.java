package com.brilloconnetz.javaTest.pojo;


import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyPairHolder {
    private static KeyPair keyPair;

    public static void setKeyPair(KeyPair keyPair) {
        KeyPairHolder.keyPair = keyPair;
    }

    public static PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    public static PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }
}
