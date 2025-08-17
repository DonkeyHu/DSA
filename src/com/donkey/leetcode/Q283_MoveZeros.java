package com.donkey.leetcode;

/**
 * 这题思路是有的，就是右指针遇到!=1的情况，就和前面左指针交换。但我没注意到以下特点：
 * （1）左右指针如果不是指向一块位置，那么必然左指针指向的是0，而右指针跑到前面去了
 * （2）左右指针之间肯定全都是0
 */
public class Q283_MoveZeros {

    /**
     * 这题我一开始只能想到这种方法去解题
     */
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int L = 0;
        int R = 0;
        while(R < len) {
            if(nums[L] == 0 && nums[R] != 0){
                swap(nums, L, R);
                L++;
                R++;
            }else if(nums[L] != 0 && nums[R] != 0){
                L++;
                R++;
            }else if(nums[L] == 0 && nums[R] == 0){
                R++;
            }else{
                L++;
                R++;
            }
        }
    }

    public void moveZeroes2(int[] nums) {
        int len = nums.length;
        int L = 0;
        int R = 0;
        while(R < len) {
            if(nums[R] != 0) {
                swap(nums, L++, R);
            }
            R++;
        }

    }

    public void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
