package com.brilloconnetz.javaTest.controller;

import com.brilloconnetz.javaTest.pojo.ErrorResponse;
import com.brilloconnetz.javaTest.pojo.JwtGeneration;
import com.brilloconnetz.javaTest.pojo.ValidationError;
import com.brilloconnetz.javaTest.response.ApiResponse;
import com.brilloconnetz.javaTest.response.SuccessfulResponse;
import com.brilloconnetz.javaTest.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class JwtGenerationController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/generate-JWT")
    public ResponseEntity<?> generateJwt(@Valid @RequestBody JwtGeneration requestPayload, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            List<ValidationError> errors = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.add(new ValidationError(error.getField(), error.getDefaultMessage()));
            }
            ErrorResponse response = new ErrorResponse("Validation failed", errors);
            return ResponseEntity.badRequest().body(response);
        }

        SuccessfulResponse generateJwt = jwtService.generateSignedJwt(requestPayload);
        return ResponseEntity.ok(generateJwt);
    }

    @GetMapping("/verify-JWT")
    public ResponseEntity<?> verifyJwt(@RequestParam String jwt) {
        try {
            String generateJwt = jwtService.verifyJwt(jwt);
            return new ResponseEntity<>(generateJwt, HttpStatus.OK);

        } catch (RuntimeException message) {
            return new ResponseEntity<>(new ApiResponse<>(message.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
