package com.brilloconnetz.javaTest.controller;

import com.brilloconnetz.javaTest.pojo.SimulationResult;
import com.brilloconnetz.javaTest.response.ApiResponse;
import com.brilloconnetz.javaTest.service.PancakeShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PancakeSimulationController {

    @Autowired
    private PancakeShopService pancakeShopService;

    @PostMapping("/simulate")
    public ResponseEntity<?> simulatePancakeShop() {
        try {
            SimulationResult result = pancakeShopService.simulatePancakeShop();
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (RuntimeException message) {
            return new ResponseEntity<>(new ApiResponse(message.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
