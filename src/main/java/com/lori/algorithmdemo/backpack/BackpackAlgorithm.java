package com.lori.algorithmdemo.backpack;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BackpackAlgorithm {

    /**
     * 背包容量
     */
    private static final BigDecimal BACKPACK_CAPACITY =  new BigDecimal(10);

    /**
     * 容量步长可以根据实际业务进行优化
     * 比如：仓库匹配货位时,货位重量为25T,可以设置步长为25
     */
    private static final int BACKPACK_CAPACITY_STEP = 1;

    public static int getBackpackCapacity(){
        /**
         * 如果背包容量是小数,需要取最小整数,所以需要++操作
         */
        int backpackCapacity = BACKPACK_CAPACITY.intValue();
        if (BACKPACK_CAPACITY.compareTo(new BigDecimal(backpackCapacity)) > 0){
            backpackCapacity++;
        }
        return backpackCapacity;
    }

    /**
     * 按步长划分容量列表
     * 划分的容量例子为：{1,2,3,4,5,6,7,8,9,10}
     */
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

        // 货位数量
        int locationSize = locations.size();
        // 容量列表
        int allBackpackCapacitySize = allBackpackCapacity.size();
        // 保存放入背包的标志
        int value[][] = new int[locationSize+1][allBackpackCapacitySize+1];
        // 保存背包放入的最大值
        int c[][] = initArray(locationSize,allBackpackCapacitySize);
        /**
         * 外层行,内层列
         * 数组下表从0开始
         */
        for (int i =1;i<locationSize+1;i++){
            for (int j=1;j<allBackpackCapacitySize+1;j++){
                // 当前的货位重量是否可以放入当前的背包容量
                if (locations.get(i-1) <= allBackpackCapacity.get(j-1)){
                    /**
                     * 进来判断说明可以放入背包
                     * allBackpackCapacity.get(j-1)-locations.get(i-1)代表当前容量减掉当前货位重量
                     * c[i-1][allBackpackCapacity.get(j-1)-locations.get(i-1)]减掉当前货位重量的值
                     * 总的意思：不放入当前货位重量的值与(减掉当前货位重量的值+当前货位重量的值)进行比较
                     * 俗话：当前货位不放入背包与放入背包,比较其容纳货位的重量大小
                     */
                    if (c[i-1][j] < c[i-1][allBackpackCapacity.get(j-1)-locations.get(i-1)]+locations.get(i-1)){
                        // 放入背包比不放入背包容纳的重量大,设置当前行列的值
                        c[i][j] = c[i-1][allBackpackCapacity.get(j-1)-locations.get(i-1)]+locations.get(i-1);
                        // 设置value当前行列值为1,标志当前索引放入背包
                        value[i][j] = 1;
                    }else {
                        c[i][j] = c[i-1][j];
                    }
                }else {
                    // 不能放入背包,直接把前一行赋值给当前行
                    c[i][j] = c[i-1][j];
                }
            }
        }

        return value;
    }

    /**
     * 初始化二维数组第一行第一列值,值都为0,设置动态规划的边界
     */
    private static int[][] initArray(int locationSize,int allBackpackCapacitySize){
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
