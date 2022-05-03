package org.example;


class Node {
    Node[] links = new Node[26];
    boolean flag = false;

    Node containsKey(char s) {
        return links[s - 'a'];
    }
    Node setKey(char s,Node node){
        links[s-'a'] = node;
        return node;
    }
    void setFlag(boolean flag) {
        this.flag = flag;
    }

    boolean getFlag() {
        return this.flag;
    }
}

public class TrieProblems {

    Node root;
    TrieProblems() {
        root = new Node();
    }
    void insertWord(String word) {
        int i=0;
        Node dummy = root;
        for(i=0;i<word.length();i++) {
            if(dummy.containsKey(word.charAt(i)) == null) {
               dummy = dummy.setKey(word.charAt(i),new Node());
            } else {
                dummy = dummy.containsKey(word.charAt(i));
            }
        }
        dummy.setFlag(true);
    }

    boolean search(String word) {
        int i=0;
        Node dummy = root;
        for(i=0;i<word.length();i++) {
            if(dummy.containsKey(word.charAt(i)) == null) {
                return false;
            } else {
                dummy = dummy.containsKey(word.charAt(i));
            }
        }
        return dummy.getFlag();
    }

    boolean startsWith(String word) {
        int i=0;
        Node dummy = root;
        for(i=0;i<word.length();i++) {
            if(dummy.containsKey(word.charAt(i)) == null) {
                return false;
            } else {
                dummy = dummy.containsKey(word.charAt(i));
            }
        }
        return true;
    }

    int longestString(String[] words){
        int i=0;
        for(i=0;i<words.length;i++){
            insertWord(words[i]);
        }
        int max= Integer.MIN_VALUE;
        for(i=0;i<words.length;i++){
            String word = words[i];
            if(checkPrefix(word) && word.length()>max){
                max = word.length();
            }
        }
        return max;
    }

    boolean checkPrefix(String word) {
        Node dummy =root;
        int i = 0 ;
        for(i=0;i<word.length();i++) {
            if(dummy.containsKey(word.charAt(i)) == null)
                return false;
            dummy = dummy.containsKey(word.charAt(i));
            if(!dummy.getFlag()) return false;
        }
        return true;
    }
    
    void doAll() {
        insertWord("n");
        insertWord("ni");
        insertWord("ninja");
        insertWord("nin");
        insertWord("ningaa");
        insertWord("ninj");
        System.out.println(longestString(new String[]{"n","ni","ning","nin","ninga","ninj"}));
        System.out.println(checkPrefix("ningaa"));
        System.out.println(startsWith("ap"));
        System.out.println(startsWith("apk"));
    }
}
