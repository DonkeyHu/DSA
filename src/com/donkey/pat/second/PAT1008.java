package com.donkey.pat.second;

import java.util.Arrays;
import java.util.Scanner;

public class PAT1008 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int length = s.nextInt();
        int p = s.nextInt();
        s.nextLine();
        int[] array = new int[length];
        for(int i=0;i<length;i++){
            array[i] = s.nextInt();
        }
        System.out.println("Start:"+Arrays.toString(array));
        reverse(array,0,length-p-1);
        reverse(array,length-p,length-1);
        reverse(array,0,length-1);
        System.out.println("End:"+Arrays.toString(array));
    }

    public static void reverse(int[]a,int start,int end){
        int sum = start + end;
        if((end-start)%2 == 0){
            int mid = (end + start)/2;
            for(int i = start;i < mid ; i++){
                int temp = a[i];
                a[i] = a[sum-i];
                a[sum-i] = temp;
            }
        }else {
            int mid = (end + start)/2;
            for(int i = start;i <= mid ; i++){
                int temp = a[i];
                a[i] = a[sum-i];
                a[sum-i] = temp;
            }
        }
    }
}
