package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ArrayProblems {

    void reverseArray() {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        int low=0,high=9;
        while(low<high) {
            swap(arr,low,high);
            low++;
            high--;
        }
        for(int i:arr) {
            System.out.print(i+"\t");
        }
     }
     void swap(int[] arr,int low,int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
     }

     void sort012(int[] arr,int n) {
        int low = 0,mid = 0,high = n-1;
        while(mid<=high) {
            if(arr[mid] == 0){
                swap(arr,low,mid);
                low++;
                mid++;
            } else if(arr[mid]==2) {
                swap(arr,mid,high);
                high--;
            } else {
                mid++;
            }
        }
         for(int i:arr) {
             System.out.print(i+"\t");
         }
     }

     boolean isPowerOf2(int n){
        return (n&(n-1))==0;
     }

     void primeNumbers() {
        int[] arr = new int[100];
         for (int i = 0; i < arr.length; i++) {
             arr[i] = 0;
         }
         for(int i=2;i<=Math.sqrt(arr.length);i++) {
             for(int j=i*i;j<100;j=j+i){
                 arr[j] = 1;
             }
         }

         for(int i=0;i<100;i++){
             if(arr[i] == 0){
                 System.out.print(i+"\t");
             }
         }
     }

     void primefactorization(int n) {
         int[] arr = new int[100];
         for (int i = 0; i < arr.length; i++) {
             arr[i] = i;
         }
         for(int i=2;i<=Math.sqrt(arr.length);i++) {
            if(arr[i] == i) {
                for(int j=i*i;j<100;j=j+i){
                        arr[j] = i;
                }
            }
         }
         while (n !=1) {
             System.out.print(arr[n]+"\t");
             n = n/arr[n];
         }
     }

     boolean isContinuous(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0;i<arr.length;i++){
            if(arr[i]<min) min = arr[i];
            else if(arr[i]>max) max = arr[i];
        }
        if(max-min+1 != arr.length) return false;
         boolean[] visited = new boolean[arr.length];
         for (int i = 0; i < visited.length; i++) {
             visited[i] = false;
         }
         for(int i=0;i<arr.length;i++) {

             if(!visited[arr[i]-min]) visited[arr[i]-min] = true;
             else return false;
         }
         return true;
     }

     boolean isPythogorus(int[] arr) {
        Arrays.sort(arr);
        int lastIndex = arr.length-1;
        for(int i=lastIndex;i>=0;i--) {
            int low = 0;
            int high = i-1;
            while (low<high) {
               int x = arr[low]*arr[low];
               int y= arr[high]*arr[high];
               int z = arr[i]*arr[i];
               if(x+y > z) {
                   high--;
               } else if(x+y <z) {
                   low++;
               } else if(x+y == z){
                   System.out.println(x+"-"+y+"-"+z);
                   return true;
               }
            }
        }
        return false;
     }

     int setBit(int n,int i) {
            int x = 1;
            x = x<<i;
            return n|x;
     }

    int getBit(int n,int i) {
        int x = 1;
        x = x<<i;
        return n&x;
    }
    int clearBit(int n,int i) {
        int x = 1;
        x = (x<<i);
        return n&(~x);
    }

    void getTwoUnknownNumbers(int[] arr) {
        int xor = 0;
        for(int i =0;i< arr.length;i++){
            xor = xor ^ arr[i];
        }
        int pos = 0;
        int index = 0;
        System.out.println("xor"+xor);
        while(pos==0) {
            int x =1<<index;
            pos = xor & x;
            index++;
        }
        System.out.println("index"+index);
        ArrayList<Integer> y = new ArrayList<>();
        for(int i = 0 ;i<arr.length;i++) {
            if(getBit(arr[i],index-1) != 0) y.add(arr[i]);
        }
        int newXor = 0;
        for(int i:y){
            System.out.println("---"+i);
            newXor = newXor ^ i;
        }
        System.out.println("first number:"+newXor+" and second number:"+(newXor^xor));
    }

    //kadan's algorithm
    int getMaxSumSubArray(int[] arr) {
        int sum = 0;
        int curSum = 0;
        for(int i=0;i<arr.length;i++){
            curSum = curSum+arr[i];
            if(curSum<0){
                curSum = 0;
            }
            sum = Math.max(curSum,sum);
        }
        return sum;
    }

    int getMaxContinuousSubArray(int[] arr){
        Arrays.sort(arr);
        int max = 0;
        int count =1;
        for(int i=1;i<arr.length;i++) {
            if(arr[i]-arr[i-1] == 1) count++;
            else count =0;
            System.out.println("count:"+count);
            max = Math.max(max,count);
        } // or use hashset and insert all elements , iterate again and check hashset if there is no lesser(less than 1)value for array elements
        return max;
    }

    int getMaxSubArrayWithSumZero(int[] arr) {

        int max =0;
        int sum = 0;
        HashMap<Integer,Integer> map = new HashMap();
        for(int i=0;i<arr.length;i++){
            sum = sum + arr[i];
            if(sum==0){
                max = i+1;
            }
            if(map.get(sum) == null) {
                map.put(sum,i);
            } else {
               int val= map.get(sum);
               max = Math.max(max,i-val);
            }

        }
        return max;
    }
    
    void doAll() {
        System.out.println(getMaxSumSubArray(new int[]{1,2,3,-1,10,20,-2,6,8,12,-10,2}));
        System.out.println(getMaxContinuousSubArray(new int[]{1,101,2,3,102,5,104,105,103,100}));
        System.out.println(getMaxSubArrayWithSumZero(new int[]{1,-1,3,2,-2,-8,1,7,10,2,3}));
        reverseArray();
        System.out.println();
        int[] arr = {0,1,2,2,0,0,1,0,2};
        sort012(arr,9);
        System.out.println();
        System.out.println(isPowerOf2(16));
        System.out.println();
        primeNumbers();
        System.out.println();
        primefactorization(63);
        System.out.println();
        int[] x = {45,46,43,44,47,49,48,50};
        System.out.println(isContinuous(x));
        System.out.println();
        int[] y = {20,30,4,40,3,80,45,5};
        System.out.println(isPythogorus(y));
        System.out.println();
        System.out.println(setBit(16,2));
        System.out.println();
        System.out.println(getBit(16,4));
        System.out.println();
        System.out.println(clearBit(17,4));
        System.out.println();
        int[] z = {1,2,3,2,1,10,7,3};
        getTwoUnknownNumbers(z);
    }


}
