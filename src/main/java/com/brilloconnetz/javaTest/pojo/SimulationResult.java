package com.brilloconnetz.javaTest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulationResult {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int pancakesMadeByShopkeeper;
    private int totalPancakesNeeded;
    private int pancakesWasted;
    private boolean shopkeeperMetNeeds;
    private List<Users> users;
}
