package org.example;

public class DpProblems {

    DpProblems() {

    }

    int fibonaciSeries(int n) {
        if(n==0) return 0;
        if(n == 1) return 1;

        return fibonaciSeries(n-1)+ fibonaciSeries(n-2);
    }

    int maxNumberOfPaths(int n) {
        if(n==0) return 1;
        if(n==2) return 2;
        if(n==1) return 1;
        return maxNumberOfPaths(n-1)+maxNumberOfPaths(n-2)+maxNumberOfPaths(n-3);
    }

    int minNumberOfSteps(int n) {
        if(n==0) return 0;
        if(n==2) return 1;
        if(n==1) return 1;
        if(n==3) return 1;
       // System.out.println("index:"+n+": "+Math.min(maxNumberOfPaths(n-1),Math.min(maxNumberOfPaths(n-2),maxNumberOfPaths(n-3))));
        return 1+Math.min(minNumberOfSteps(n-1),Math.min(minNumberOfSteps(n-2),minNumberOfSteps(n-3)));
    }


    int minStepsRequiredForFrogJump(int index,int[] arr) {
        if(index==0) return 0;
        if(index==1) return Math.abs(arr[1]-arr[0]);
        int prev = Math.abs(arr[index]-arr[index-1]) + minStepsRequiredForFrogJump(index-1,arr);
        int prev1 = Math.abs(arr[index]-arr[index-2]) + minStepsRequiredForFrogJump(index-2,arr);
        System.out.println(" index: "+index+" prev: "+prev+" prev1:"+prev1);
        return Math.min(prev,prev1);
    }

    int minStepsRequiredForFrogJumpKsteps(int index,int[] arr,int k) {
        if(index==0) return 0;
        int min = Integer.MAX_VALUE;
        for(int i=index-1;i>=index-k;i--) {
            int x = Integer.MAX_VALUE;
            if(i>=0)
                x = Math.abs(arr[index]-arr[i]) + minStepsRequiredForFrogJumpKsteps(i,arr,k);
            min = Math.min(min,x);
        }
        return min;
    }

    int maxSumOfNonAdjecentElements(int index,int[] arr) {
        if(index == 0) return arr[0];
        if(index == 1) return Math.max(arr[0],arr[1]);
        int x = arr[index] + maxSumOfNonAdjecentElements(index-2,arr);
        int y = maxSumOfNonAdjecentElements(index-1,arr);
        return Math.max(x,y);
    }

    int maxSumOfNonAdjecentElementsInCircle(int index,int[] arr) {

        int[] x = new int[arr.length-1];
        for(int i=1;i<arr.length;i++){
            x[i-1] =  arr[i];
        }
        int[] y = new int[arr.length-1];
        for(int i=0;i<arr.length-1;i++){
            y[i] =  arr[i];
        }
        return Math.max(maxSumOfNonAdjecentElements(arr.length-2,x),maxSumOfNonAdjecentElements(arr.length-2,y));
    }

    boolean sumOfSubSetTarget(int index,int target,int[] arr){
        if(index == 0) return arr[0] == target;
        if(target == 0) return true;
        boolean x = false;
        boolean y = sumOfSubSetTarget(index-1,target,arr);
        if(target>=arr[index])
            x = sumOfSubSetTarget(index-1,target-arr[index],arr);
        return x || y;
    }

    boolean equalSumSubSet(int index,int[] arr){
        int i =0;
        int sum =0;
        for(i=0;i<arr.length;i++){
            sum += arr[i];
        }
        if(sum%2 != 0) return false;
        return sumOfSubSetTarget(arr.length-1,sum/2,arr);
    }

    int noOfWaysForTwoPoints(int m,int n) {
        if(m==0&&n==0) return 1;
        if(m<0 || n<0) return 0;
        return noOfWaysForTwoPoints(m-1,n) + noOfWaysForTwoPoints(m,n-1);
    }

    int noOfWaysForTwoPointsWithAbstacle(int m,int n,int[][] arr) {
        if(m<0 || n<0) return 0;
        if(arr[m][n] == -1) return 0;
        if(m==0&&n==0) return 1;
        return noOfWaysForTwoPointsWithAbstacle(m-1,n,arr) + noOfWaysForTwoPointsWithAbstacle(m,n-1,arr);
    }

    int noOfWaysTriangle(int m,int n,int[][] arr){
        if(n<0 || n>m ) return 250;
        if(m == 0) return arr[m][n];
        System.out.println(m+" "+n);
        int x = arr[m][n] + noOfWaysTriangle(m-1,n-1,arr);
        int y = arr[m][n]+ noOfWaysTriangle(m-1,n,arr);
        return Math.min(x,y);
    }


    int[][] dp = new int[3][3];
    int noOfWaysMultiPoint(int m,int n,int[][] arr){
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++) {
            dp[0][i] = arr[0][i];
        }
        for(int i=1;i<m;i++) {
            for(int j=0;j<n;j++) {
                int x = 2500;
                int z = 2500;
                if(j>0) {
                    System.out.println(i+ "--"+j+ " dp:"+dp[i-1][j-1]);
                    x = arr[i][j] + dp[i-1][j-1];
                }
                int y = arr[i][j] + dp[i-1][j];
                if(j<n-1)
                 z = arr[i][j] + dp[i-1][j+1];
                dp[i][j] = Math.min(x,Math.min(y,z));
                if(i==n-1) {
                    min = Math.min(min,dp[i][j]);
                }
            }
        }
        return min;
    }

    boolean isSubSequenceOfLengthk(int index,int k,int[] arr) {
        if(k == 0) return true;
        if(index == 0){
            return arr[0] == k;
        }

        boolean not = isSubSequenceOfLengthk(index-1,k,arr);
        boolean take = false;
        if(arr[index]<=k) {
            take = isSubSequenceOfLengthk(index-1,k-arr[index],arr);
        }

        return take || not;

    }

    int totalSubsets(int index,int k,int[] arr) {
        if(k == 0) return 1;
        if(index == 0){
            if(arr[0] == k) return 1;
            else return 0;
        }
        int not = totalSubsets(index-1,k,arr);
        int take = 0;
        if(arr[index]<=k) {
            take = totalSubsets(index-1,k-arr[index],arr);
        }

        return take + not;
    }

    boolean isThereTwoEqualSubsets(int index,int[] arr) {
        int sum = 0;
        for(int i=0;i<arr.length;i++) sum+=arr[i];
        if(sum % 2 !=0) return false;
        return isSubSequenceOfLengthk(index,sum/2,arr);
    }

    int partitionWithDiffD(int index,int d,int[] arr){
        int sum = 0;
        for(int i=0;i<arr.length;i++) sum+=arr[i];
        if((sum-d)%2 !=0) return 0;
        return totalSubsets(index,(sum-d)/2,arr);

    }
    int diffOfTwoSubsetsMin(int[] arr){
        int sum = 0;
        for(int i=0;i<arr.length;i++) sum+=arr[i];
        int min = Integer.MAX_VALUE;
        for(int i=0;i<=sum/2;i++){
            if(isSubSequenceOfLengthk(arr.length-1,i,arr)) {
                int x =i;
                int y = sum-i;
                min = Math.min(min,Math.abs(x-y));
            }
        }
        return min;
    }

    int minimumCoins(int index,int[] arr,int k) {
        if(index == 0) {
            if(k%arr[index] == 0){
                return k/arr[index];
            } else {
                return 2500;
            }
        }
        int not = minimumCoins(index-1,arr,k);
        int take = 2500;
        if(arr[index]<= k){
            take = 1 + minimumCoins(index,arr,k-arr[index]);
        }
        System.out.println(index+"  "+Math.min(not,take));
        return Math.min(not,take);
    }

    int noOfWaysForCoins(int index,int[] arr,int k) {
        if(index == 0) {
           if(k%arr[0]==0) return 1;
           else return 0;
        }
        int not = noOfWaysForCoins(index-1,arr,k);
        int take = 0;
        if(arr[index]<= k){
            take = noOfWaysForCoins(index,arr,k-arr[index]);
        }
        return take+not;
    }

    int knapsack01(int index,int w,int[] wt,int[] val){
        if(index==0){
            if(wt[0]<=w) return val[0];
            else return 0;
        }
        int not = knapsack01(index-1,w,wt,val);
        int take = -100;
        if(wt[index]<= w){
            take = val[index] + knapsack01(index-1,w-wt[index],wt,val);
        }
        return Math.max(take,not);
    }

    int unboundknapsack01(int index,int w,int[] wt,int[] val){
        if(index==0){
            if(wt[0]<=w) return val[0];
            else return 0;
        }
        int not = knapsack01(index-1,w,wt,val);
        int take = -100;
        if(wt[index]<= w){
            take = val[index] + knapsack01(index,w-wt[index],wt,val);
        }
        return Math.max(take,not);
    }

    int rodCutting(int index,int[] arr,int n){
        if(index == 0){
//            if(n>0) return n*arr[index];
//            return 0;
            return n*arr[0];
        }
        int not = rodCutting(index-1,arr,n);
        int take = -200;
        if(index+1 <= n){
            take = arr[index] + rodCutting(index,arr,n-(index+1));
        }
        return Math.max(take,not);
    }

    int longestCommonSubsequence(String s1,String s2,int index1,int index2){
        if(index1 < 0 || index2 < 0) {
            return 0;
        }
        if(s1.charAt(index1) == s2.charAt(index2))
            return 1+longestCommonSubsequence(s1,s2,index1-1,index2-1);
        return Math.max(longestCommonSubsequence(s1,s2,index1-1,index2),longestCommonSubsequence(s1,s2,index1,index2-1));
    }

    int minInsertionsToMakePalindrome(String s1){
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        return s1.length() - longestCommonSubsequence(s1,sb.reverse().toString(),s1.length()-1,s1.length()-1);
    }

    int minInsertionsDeletionToMakeS1ToS2(String s1,String s2){
        return s1.length()+s2.length()-2*(longestCommonSubsequence(s1,s2,s1.length()-1,s2.length()-1));
    }

    int minLengthForTwoStringsToBeInSameString(String s1,String s2) {
        return s1.length()+s2.length()-longestCommonSubsequence(s1,s2,s1.length()-1,s2.length()-1);
    }

    int longestCommonSubString(String s1,String s2){
//        int[][] dp = new int[s1.length()+1][s2.length()+1];
//        int max= 0;
//        for(int i=1;i<=s1.length();i++){
//            for(int j=1;j<=s2.length();j++){
//                if(s1.charAt(i-1) == s2.charAt(j-1)){
//                    dp[i][j] = 1+dp[i-1][j-1];
//                    max = Math.max(max,dp[i][j]);
//                }
//            }
//        }
        int[] prev = new int[s2.length()+1];
        int[] curr = new int[s2.length()+1];
        int max= 0;
        for(int i=1;i<=s1.length();i++){
            for(int j=1;j<=s2.length();j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    curr[j] = 1+prev[j-1];
                    max = Math.max(max,curr[j]);
                }
            }
            prev = curr;
        }
        return max;
    }

    String longestCommonSubsequence(String s1,String s2){
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for(int i=1;i<=s1.length();i++){
            for(int j=1;j<=s2.length();j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1+dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        int i= s1.length();
        int j=s2.length();
        StringBuffer sb = new StringBuffer();
        while (i>0 && j>0){
            if(s1.charAt(i-1) == s2.charAt(j-1)){
                sb.append(s1.charAt(i-1));
                i--;j--;
            } else if(dp[i-1][j]>dp[i][j-1]) {
                i--;
            } else {

                j--;
            }
        }
        return sb.reverse().toString();
    }

    int countS2inS1(String s1,String s2,int index1,int index2){
        if(index1 < 0) return 0;
        if(index2<0) return 1;
        if(s1.charAt(index1) == s2.charAt(index2)) {
            return countS2inS1(s1,s2,index1-1,index2)+countS2inS1(s1,s2,index1-1,index2-1);
        }
        return countS2inS1(s1,s2,index1-1,index2);
    }

    int noOfTimesIRR(String s1,String s2,int index1,int index2){
        if(index1 < 0) return index2+1;
        if(index2<0) return index1+1;
        if(s1.charAt(index1) == s2.charAt(index2)) {
            return noOfTimesIRR(s1,s2,index1-1,index2-1);
        }
        return Math.min(1+noOfTimesIRR(s1,s2,index1,index2-1),Math.max(1+noOfTimesIRR(s1,s2,index1-1,index2-1),1+noOfTimesIRR(s1,s2,index1-1,index2)));
    }
    
    void doAll() {
//                System.out.println(fibonaciSeries(6));
//        System.out.println(maxNumberOfPaths(7));
//        System.out.println(minNumberOfSteps(6));
//        System.out.println(minStepsRequiredForFrogJump(5,new int[]{30,10,60,10,60,50}));
//        System.out.println(minStepsRequiredForFrogJumpKsteps(5,new int[]{30,10,60,10,60,50},5));
//        System.out.println(maxSumOfNonAdjecentElements(5,new int[]{5,5,10,100,10,5}));
//        System.out.println(maxSumOfNonAdjecentElements(3,new int[]{3,2,7,10}));
//        System.out.println(maxSumOfNonAdjecentElementsInCircle(2,new int[]{2,3,2}));
//        System.out.println(sumOfSubSetTarget(5,17,new int[]{5,10,15,12,6,40}));
//        System.out.println(equalSumSubSet(5,new int[]{5,10,15,12,5,40}));
//        System.out.println(noOfWaysForTwoPoints(2,2));
//        System.out.println(noOfWaysForTwoPointsWithAbstacle(2,2,new int[][]{{0,0,0},{0,0,0},{0,0,0}}));
//        System.out.println(noOfWaysTriangle(3,0,new int[][]{{8,9,6,10},{3,6,7},{2,3},{1}}));
//        System.out.println(noOfWaysMultiPoint(3,3,new int[][]{{8,9,6,10},{3,6,7,8},{2,3,3,9},{1,2,7,10}}));
//
//         System.out.println(isThereTwoEqualSubsets(5,new int[]{1,8,3,5,8,9}));
//         System.out.println(totalSubsets(5,240,new int[]{1,8,3,5,8,6}));
//        System.out.println(partitionWithDiffD(5,3,new int[]{1,6,3,5,8,6}));
//        System.out.println(diffOfTwoSubsetsMin(new int[]{1,6,3,5,8,6}));
//         System.out.println(minimumCoins(2,new int[]{1,7,9,8},12));
//        System.out.println(noOfWaysForCoins(2,new int[]{1,2,3},4));
//        System.out.println(knapsack01(2,10,new int[]{3,4,5},new int[]{30,50,60}));
//        System.out.println(unboundknapsack01(2,10,new int[]{3,4,5},new int[]{30,50,60}));

//        System.out.println(rodCutting(4,new int[]{2,5,7,8,10},5));
//        System.out.println(rodCutting(7,new int[]{3,5,8,9,10,17,17,20},8));
//        System.out.println(longestCommonSubsequence("abcaaxyz","atcaxz",7,5));
//
//        System.out.println("===longest palindrom subsquence===");
//        System.out.println(longestCommonSubsequence("xabcaaxyz","zyxaacbax",8,8));
//
//        System.out.println("===minimum insertions to make palindrom===");
//        System.out.println(minInsertionsToMakePalindrome("abcaa"));
//
//        System.out.println("===minimum insertions/deletions to make s1 to s2===");
//        System.out.println(minInsertionsDeletionToMakeS1ToS2("santhoshxx","sandeepxx"));
//
//        System.out.println("===minLengthForTwoStringsToBeInSameString===");
//        System.out.println(minLengthForTwoStringsToBeInSameString("santhoshxx","sandeepxx"));
//
//        System.out.println("===longest common substring===");
//        System.out.println(longestCommonSubString("xyzabbc","abbcxy"));

  //      System.out.println(longestCommonSubsequence("abcaa","aca"));
    //    System.out.println(countS2inS1("bbagbagg","bag",7,2));
        System.out.println(noOfWaysForCoins(3,new int[]{1,2,5,10},5));

    }
}
