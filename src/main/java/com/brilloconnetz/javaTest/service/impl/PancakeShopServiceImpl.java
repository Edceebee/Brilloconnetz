package com.brilloconnetz.javaTest.service.impl;

import com.brilloconnetz.javaTest.pojo.SimulationResult;
import com.brilloconnetz.javaTest.pojo.Users;
import com.brilloconnetz.javaTest.service.PancakeShopService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class PancakeShopServiceImpl implements PancakeShopService {

    private static final int MAX_PANCAKES_PER_USER = 5;
    private static final int MAX_PANCAKES_BY_SHOPKEEPER = 12;
    private static final int NUM_USERS = 3;
    private static final int TIME_SLOT_SECONDS = 30;
    
    @Override
    public SimulationResult simulatePancakeShop() {
        Random random = new Random();

        // Start of the 30-second slot
        LocalDateTime startTime = LocalDateTime.now();

        // Shopkeeper makes pancakes
        int pancakesMadeByShopkeeper = random.nextInt(MAX_PANCAKES_BY_SHOPKEEPER) + 1;

        // Users decide the number of pancakes to eat
        List<Users> users = new ArrayList<>();
        for (int i = 0; i < NUM_USERS; i++) {
            Users user = new Users();
            user.setName("User " + (i + 1));
            user.setPancakesToEat(random.nextInt(MAX_PANCAKES_PER_USER + 1));
            users.add(user);
        }

        // Simulate eating time
        simulateEating();

        // End of the 30-second slot
        LocalDateTime endTime = LocalDateTime.now();

        // Check if the shopkeeper met the needs of the users
        int totalPancakesNeeded = users.stream().mapToInt(Users::getPancakesToEat).sum();

        int pancakesWasted = Math.max(0, pancakesMadeByShopkeeper - totalPancakesNeeded);
        boolean shopkeeperMetNeeds = pancakesMadeByShopkeeper >= totalPancakesNeeded;

        // Prepare and return the result
        return new SimulationResult(startTime, endTime, pancakesMadeByShopkeeper,
                totalPancakesNeeded, pancakesWasted, shopkeeperMetNeeds, users);
    }

    private void simulateEating() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
