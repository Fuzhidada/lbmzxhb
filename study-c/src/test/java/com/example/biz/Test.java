package com.example.biz;

public class Test {

    public int firstMissingPositive(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {
            int a = nums[i];
            int b = nums[i + 1];
            if (a > b) {
                nums[i + 1] = a;
            }
        }


        int a1 = 1;


        return 1;
    }


}
