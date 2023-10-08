package com.brilloconnetz.javaTest;

import com.brilloconnetz.javaTest.pojo.KeyPairHolder;
import com.brilloconnetz.javaTest.response.SuccessfulResponse;
import com.brilloconnetz.javaTest.service.JwtService;
import com.nimbusds.jose.jwk.RSAKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PublicKey;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class JavaTestApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void testInvalidToken() {
		String token = "eyiiyeyw3enqeadaeeq";

		String response = jwtService.verifyJwt(token);
		assertThat(response.equalsIgnoreCase("verification fails"));
	}


	@Test
	void testValidToken() {
		String token = "eyJraWQiOiI4ZTY3Yjk3NC0xNWUxLTQyNjEtOGVjMS1jYTA0YWQ3YzhkNzMiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJOb25pYmVlIiwiZGF0ZU9mQmlydGgiOiIxOTk2LTAxLTIxIiwiZXhwIjoxNjk2Nzg1MjkxLCJlbWFpbCI6Im5vbnNvQGdtYWlsLmNvbSJ9.AtQEAckoNIjFoVPtz0rpuKCv7gulL8pOHIs1C26ed-XeL45Bz6q93406iAzIL3TtU8ZhXUy5btHqrXjDwZW2pE7rp-kJfUk4953KPd0fbQtD95vhaPbZXwFsSC4fxRX-PTY8c758fLqTR7P-k2HX1sIRvfOFctTne4oMFFdTw6jWaWWzdV-T82t0UI-JYACuk4w1MSIVxkrSogLKkoXGHzO5WC4xsryPKQazldPoTb6uc9RdL5s4d7PDA0HwzWwpTluEpppPblITnVaUCosmW1w4J7DN4tuS6zadF3YBj1McuavJjXPBijC544KRfTiU4yeybO6qgqBAdrPhkcMPrw";

		String response = jwtService.verifyJwt(token);
		assertThat(response.equalsIgnoreCase("verification pass"));
	}
}
