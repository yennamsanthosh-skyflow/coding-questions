package org.example;

class TrieNode {
    TrieNode[] links = new TrieNode[26];
    int countPrefix = 0;
    int countEndsWith = 0;

    void incrementPrefix() {
        countPrefix++;
    }
    void incrementEndsWith() {
        countEndsWith++;
    }

    void decrementPrefix() {
        countPrefix--;
    }
    void decrementEndsWith() {
        countEndsWith--;
    }
    TrieNode containsKey(char ch) {
        return links[ch - 'a'];
    }
    TrieNode setKey(char ch,TrieNode node){
        links[ch-'a'] = node;
        return node;
    }

}

public class TrieDs {

    TrieNode root;
    TrieDs() {
        root = new TrieNode();
    }

    void insertWord(String word) {
        int i = 0;
        TrieNode dummy = root;
        for(i=0;i<word.length();i++) {
            if(dummy.containsKey(word.charAt(i)) == null) {
                dummy = dummy.setKey(word.charAt(i),new TrieNode());
            } else {
                dummy = dummy.containsKey(word.charAt(i));
            }
            dummy.incrementPrefix();
        }
        dummy.incrementEndsWith();
    }

    int countWords(String word) {
        int i = 0;
        TrieNode dummy = root;
        for(i=0;i<word.length();i++) {
            if(dummy.containsKey(word.charAt(i)) == null) {
                return -1;
            } else {
                dummy = dummy.containsKey(word.charAt(i));
            }

        }
        return dummy.countEndsWith;
    }

    int countStartsWith(String word) {
        int i = 0;
        TrieNode dummy = root;
        for(i=0;i<word.length();i++) {
            if(dummy.containsKey(word.charAt(i)) == null) {
                return -1;
            } else {
                dummy = dummy.containsKey(word.charAt(i));
            }

        }
        return dummy.countPrefix;
    }

    void erase(String word) {
        int i = 0;
        TrieNode dummy = root;
        for(i=0;i<word.length();i++) {
            dummy = dummy.containsKey(word.charAt(i));
            dummy.decrementPrefix();
        }
        dummy.decrementEndsWith();
    }
    
    void doALl() {
        insertWord("apple");
        insertWord("apps");
        insertWord("apple");
        System.out.println(countWords("apple"));
        System.out.println(countStartsWith("app"));
        erase("apps");
        System.out.println(countWords("app"));
    }
}
