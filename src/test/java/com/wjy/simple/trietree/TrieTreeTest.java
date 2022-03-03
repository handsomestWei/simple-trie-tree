package com.wjy.simple.trietree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrieTreeTest {

    public static void main(String[] args) {

        String[] words = {"qwe", "asd", "asxc"};
        String pref = "as";
        // insert and countPrefix
        TrieTree<Character> trieTree1 = new TrieTree<>();
        for (String word : words) {
            trieTree1.insert(str2CharList(word));
        }
        System.out.println(trieTree1.countPrefix(str2CharList(pref)));

        // delete and countPrefix
        String delWord = "a";
        trieTree1.delete(str2CharList(delWord));
        System.out.println(trieTree1.countPrefix(str2CharList(pref)));

        int n = 13;
        int k = 7;
        // insert and findIndex
        TrieTree<String> trieTree2 = new TrieTree<>();
        for (int i = 1; i <= n; i++) {
            // insert by trie sort
            trieTree2.insert(int2StrList(i));
        }
        List<String> rs = trieTree2.findIndexByDFS(k);
        System.out.println(strList2Int(rs));
        k = 2;
        rs = trieTree2.findIndexByDFS(k);
        System.out.println(strList2Int(rs));

    }

    private static List<Character> str2CharList(String str) {
        List<Character> charLs = new ArrayList<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            charLs.add(str.charAt(i));
        }
        return charLs;
    }

    private static List<String> int2StrList(int i) {
        String s = String.valueOf(i);
        List<String> strLs = new LinkedList<>();
        for (int j = 0; j < s.length(); j++) {
            strLs.add(String.valueOf(s.charAt(j)));
        }
        return strLs;
    }

    private static int strList2Int(List<String> strLs) {
        String sVal = "";
        for (String str : strLs) {
            sVal += str;
        }
        return Integer.valueOf(sVal);
    }

}
