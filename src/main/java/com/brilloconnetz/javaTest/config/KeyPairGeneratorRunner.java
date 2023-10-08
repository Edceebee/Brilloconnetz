package com.brilloconnetz.javaTest.config;

import com.brilloconnetz.javaTest.pojo.KeyPairHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

@Component
@Slf4j
public class KeyPairGeneratorRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        KeyPair keyPair = generateKeyPair();
        KeyPairHolder.setKeyPair(keyPair);
    }

    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate RSA key pair", e);
        }
    }
}
