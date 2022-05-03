package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graphs {


    void doBfs(int index, ArrayList<ArrayList<Integer>> nodes, int[] visited){
        Queue<Integer> q = new LinkedList<>();
        q.add(index);
        visited[index] = 0;
        while(!q.isEmpty()){
            int x = q.poll();
            System.out.println(x);
            ArrayList<Integer> node = nodes.get(x);
            for(int i:node){
                if(visited[i] == -1){
                    visited[i] = 0;
                    q.add(i);
                }
            }
        }
    }
    boolean detectCycleBFS(int index, int parent, ArrayList<ArrayList<Integer>> nodes, int[] visited) {
        Queue<IntPair> q = new LinkedList<>();
        q.add(new IntPair(index,parent));
        visited[index] = 0;
        while(!q.isEmpty()){
            IntPair queueNode = q.poll();
            int x = queueNode.index;
            int y = queueNode.parent;
            ArrayList<Integer> node = nodes.get(x);
            for(int i:node){
                if(visited[i] == -1){
                    visited[i] = 0;
                    q.add(new IntPair(i,x));
                } else if(y != i) return true;
            }
        }
        return false;
    }

    void bfsAndDfs() {
        int n = 9;
        int[] visited = new int[n];
        for(int i=0;i<n;i++) visited[i] = -1;
        ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();
        ArrayList<Integer> x = new ArrayList<>();
        x.add(2);
        nodes.add(new ArrayList<>());
        nodes.add(x);
        ArrayList<Integer> y = new ArrayList<>();
        y.add(1);
        y.add(4);
        y.add(7);
        nodes.add(y);
        ArrayList<Integer> z = new ArrayList<>();
        z.add(5);
        nodes.add(z);
        ArrayList<Integer> a = new ArrayList<>();
        a.add(2);
        a.add(6);
        nodes.add(a);
        ArrayList<Integer> b = new ArrayList<>();
        b.add(3);
        nodes.add(b);
        ArrayList<Integer> c= new ArrayList<>();
        c.add(4);
        c.add(7);
        nodes.add(c);
        ArrayList<Integer> d = new ArrayList<>();
        d.add(8);
        d.add(6);
        nodes.add(d);
        ArrayList<Integer> e = new ArrayList<>();
        e.add(2);
        nodes.add(e);
        System.out.println("====BFS====");
        for(int i=1;i<n;i++){
            if(visited[i] == -1){
                doBfs(i,nodes,visited);
            }
        }
        System.out.println("====DFS====");
        for(int i=0;i<n;i++) visited[i] = -1;
        for(int i=1;i<n;i++){
            if(visited[i] == -1){
                doDfs(i,nodes,visited);
            }
        }

        System.out.println("====BFS cycle====");
        for(int i=0;i<n;i++) visited[i] = -1;
        for(int i=1;i<n;i++){
            if(visited[i] == -1){
                if(detectCycleBFS(i,-1,nodes,visited)) {
                    System.out.println("yes");
                    break;
                }
            }
        }

        System.out.println("====DFS cycle====");
        for(int i=0;i<n;i++) visited[i] = -1;
        for(int i=1;i<n;i++){
            if(visited[i] == -1){
                if(detectCycleDFS(i,-1,nodes,visited)) {
                    System.out.println("yes");
                    break;
                }
            }
        }

        System.out.println("====DFS bipatite====");
        for(int i=0;i<n;i++) visited[i] = -1;
        for(int i=1;i<n;i++){
            if(visited[i] == -1){
                if(biPatite(i,0,nodes,visited)) {
                    System.out.println("yes");
                    break;
                }
            }
        }
    }
    private void doDfs(int index, ArrayList<ArrayList<Integer>> nodes, int[] visited) {
        System.out.println(index);
        visited[index] = 0;
        for(int i:nodes.get(index)) {
            if(visited[i] == -1) {
                doDfs(i,nodes,visited);
            }
        }
    }

    private boolean detectCycleDFS(int index,int parent, ArrayList<ArrayList<Integer>> nodes, int[] visited) {
        visited[index] = 0;
        for(int i:nodes.get(index)) {
            if(visited[i] == -1) {
                if(detectCycleDFS(i,index,nodes,visited))return true;
            } else if(i != parent) return true;
        }
        return false;
    }

    private boolean biPatite(int index,int color, ArrayList<ArrayList<Integer>> nodes, int[] visited) {
        visited[index] = color;
        for(int i:nodes.get(index)) {
            if(visited[i] == -1) {
                if(biPatite(i,1-visited[index],nodes,visited))return true;
            } else if(visited[i] == color) return true;
        }
        return false;
    }


    void doAll() {
        bfsAndDfs();
    }
}

class IntPair {
    int index;
    int parent;

    IntPair(int index,int parent) {
        this.index = index;
        this.parent = parent;
    }
}