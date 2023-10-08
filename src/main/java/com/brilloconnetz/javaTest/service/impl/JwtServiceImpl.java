package com.brilloconnetz.javaTest.service.impl;

import com.brilloconnetz.javaTest.pojo.JwtGeneration;
import com.brilloconnetz.javaTest.pojo.KeyPairHolder;
import com.brilloconnetz.javaTest.response.ApiResponse;
import com.brilloconnetz.javaTest.response.SuccessfulResponse;
import com.brilloconnetz.javaTest.service.JwtService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Service
public class JwtServiceImpl implements JwtService {

    @Override
    public SuccessfulResponse generateSignedJwt(JwtGeneration payload) throws Exception {

        String keyId = UUID.randomUUID().toString();

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(payload.getUsername())
                .claim("email", payload.getEmail())
                .claim("dateOfBirth", payload.getDateOfBirth())
                .expirationTime(new Date(System.currentTimeMillis() + 3600000))  // Expires in 1 hour
                .build();

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .keyID(keyId)
                .build();
        SignedJWT signedJWT = new SignedJWT(header, claimsSet);

        JWSSigner signer = new RSASSASigner(KeyPairHolder.getPrivateKey());
        signedJWT.sign(signer);

        SuccessfulResponse token = SuccessfulResponse.builder().token(signedJWT.serialize()).build();

        return token;
    }

    @Override
    public String verifyJwt(String jwt) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(jwt);

            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) KeyPairHolder.getPublicKey());

            if (signedJWT.verify(verifier)) {
                return "Verification pass";
            } else {
                return "Verification fails";
            }
        } catch (ParseException | JOSEException e) {
            return "Verification fails: " + e.getMessage();
        }
    }
}
