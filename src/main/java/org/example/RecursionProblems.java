package org.example;

import java.util.ArrayList;

public class RecursionProblems {

    int[] arr = {3,2,1,4};
    void printAllSubsets(int index, ArrayList<Integer> ds, int n){
        if(index == n) {
            System.out.println(ds.toString());
            return;
        }
        ds.add(arr[index]);
        //System.out.println(ds.toString());
        printAllSubsets(index+1,ds,n);
        ds.remove(ds.size()-1);
        printAllSubsets(index+1,ds,n);
    }

    int sum = 0;
    void printAllSubsetsWithSum(int index, ArrayList<Integer> ds, int n,int s){
        if(index == n) {
            if(s==sum)
            System.out.println(ds.toString());
            return;
        }
        ds.add(arr[index]);
        sum = sum + arr[index];
        printAllSubsetsWithSum(index+1,ds,n,s);
        ds.remove(ds.size()-1);
        sum = sum - arr[index];
        printAllSubsetsWithSum(index+1,ds,n,s);
    }

    void isSumAvaliable(int index, int n,int s){
        if(index == n) {
            if(s==sum)
                System.out.println("yes");
            return;
        }
        sum = sum + arr[index];
        isSumAvaliable(index+1,n,s);
        sum = sum - arr[index];
        isSumAvaliable(index+1,n,s);
    }

    int repeatSum = 0;
    void repeatAndGetSum(int index, ArrayList<Integer> ds, int n,int s) {
        if(index == n) {
            if(s==repeatSum)
                System.out.println(ds.toString());
            return;
        }
        if(repeatSum < s) {
            ds.add(arr[index]);
            repeatSum = repeatSum + arr[index];
            repeatAndGetSum(index,ds,n,s);
            ds.remove(ds.size()-1);
            repeatSum = repeatSum - arr[index];
        }
        repeatAndGetSum(index+1,ds,n,s);
    }

    boolean[] flag = {false,false,false,false};
    void getPermutations(ArrayList<Integer> ds,int n) {
        if(n == ds.size()) {
            System.out.println(ds.toString());
            return;
        }

        for(int i=0;i<n;i++){
            if(!flag[i]) {
                ds.add(arr[i]);
                flag[i] = true;
                getPermutations(ds,n);
                ds.remove(ds.size()-1);
                flag[i]= false;
            }
        }
        //getPermutations(index+1,ds,n);
    }
    
    void doAll() {
        printAllSubsets(0,new ArrayList<>(),3);
        System.out.println("===sum====");
        printAllSubsetsWithSum(0,new ArrayList<>(),3,3);
        sum = 0;
        isSumAvaliable(0,3,7);

        System.out.println("===repeat sum====");
        repeatAndGetSum(0,new ArrayList<>(),3,7);

        System.out.println("===permutations====");
        getPermutations(new ArrayList<>(),4);
    }
}
