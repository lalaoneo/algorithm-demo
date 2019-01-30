package com.lori.algorithmdemo.backpack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackpackAlgorithmTest {

    @Test
    public void testBackpackAlgorithm() {
        List<Integer> locations = buildLocations();

        Collections.sort(locations);

        // 获取背包容量
        int backpackCapacity = BackpackAlgorithm.getBackpackCapacity();

        // 按步长划分的所有背包容量
        List<Integer> allBackpackCapacity = BackpackAlgorithm.getAllBackpackCapacity(backpackCapacity);

        // 背包算法
        int[][] value = BackpackAlgorithm.getMatchLocations(locations,allBackpackCapacity);

        int i = value.length-1;
        int j = value[locations.size()].length-1;
        while (i>0&& j>0){
            if (value[i][j] == 1){
                System.out.println(locations.get(i-1));
                j -= locations.get(i-1);
            }
            i--;
        }
    }

    private List<Integer> buildLocations(){
        List<Integer> locations = new ArrayList<>();
        locations.add(5);
        locations.add(6);
        locations.add(2);
        locations.add(2);
        locations.add(4);
        return locations;
    }
}
