package com.lori.algorithmdemo.backpack;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BackpackAlgorithm {

    private static final BigDecimal TARGET_WEIGHT =  new BigDecimal(10);

    /**
     * 容量步长可以根据实际业务进行优化
     */
    private static final int BACKPACK_CAPACITY_STEP = 1;

    public static int getBackpackCapacity(){
        int backpackCapacity = TARGET_WEIGHT.intValue();
        if (TARGET_WEIGHT.compareTo(new BigDecimal(backpackCapacity)) > 0){
            backpackCapacity++;
        }
        return backpackCapacity;
    }

    public static List<Integer> getAllBackpackCapacity(int backpackCapacity){
        List<Integer> list = new ArrayList<>();
        if (backpackCapacity % BACKPACK_CAPACITY_STEP == 0){
            int stepSize = backpackCapacity/BACKPACK_CAPACITY_STEP;
            for (int i = 0;i<stepSize;i++){
                list.add(BACKPACK_CAPACITY_STEP*(i+1));
            }
        }else {
            int stepSize = backpackCapacity/BACKPACK_CAPACITY_STEP+1;
            for (int i = 0;i<stepSize;i++){
                if (i == stepSize-1){
                    list.add(backpackCapacity);
                }else {
                    list.add(BACKPACK_CAPACITY_STEP*(i+1));
                }
            }
        }
        return list;
    }

    public static int[][] getMatchLocations(List<Integer> locations,List<Integer> allBackpackCapacity){

        int locationSize = locations.size();
        int allBackpackCapacitySize = allBackpackCapacity.size();

        int value[][] = new int[locationSize+1][allBackpackCapacitySize+1];

        int c[][] = initialize(locationSize,allBackpackCapacitySize);

        for (int i =1;i<locationSize+1;i++){
            for (int j=1;j<allBackpackCapacitySize+1;j++){
                if (locations.get(i-1) <= allBackpackCapacity.get(j-1)){
                    if (c[i-1][j] < c[i-1][allBackpackCapacity.get(j-1)-locations.get(i-1)]+locations.get(i-1)){
                        c[i][j] = c[i-1][allBackpackCapacity.get(j-1)-locations.get(i-1)]+locations.get(i-1);
                        value[i][j] = 1;
                    }else {
                        c[i][j] = c[i-1][j];
                    }
                }else {
                    c[i][j] = c[i-1][j];
                }
            }
        }

        return value;
    }

    private static int[][] initialize(int locationSize,int allBackpackCapacitySize){
        int c[][] = new int[locationSize+1][allBackpackCapacitySize+1];
        for (int i=0;i<locationSize+1;i++){
            c[i][0] = 0;
        }
        for (int i=0;i<allBackpackCapacitySize+1;i++){
            c[0][i] = 0;
        }
        return c;
    }
}
