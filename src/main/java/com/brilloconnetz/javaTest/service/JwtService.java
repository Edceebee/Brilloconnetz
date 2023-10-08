package com.brilloconnetz.javaTest.service;

import com.brilloconnetz.javaTest.pojo.JwtGeneration;
import com.brilloconnetz.javaTest.response.SuccessfulResponse;

public interface JwtService {

    SuccessfulResponse generateSignedJwt(JwtGeneration payload) throws Exception;

    String verifyJwt(String jwt);
}
