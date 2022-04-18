package com.wjy.simple.trietree;

import java.util.List;

public class TrieTreeTest {

    public static void main(String[] args) {

        String[] words = {"qwe", "asd", "asxc"};
        String pref = "as";
        // insert and countPrefix
        TrieTree<Character> trieTree1 = new TrieTree<>();
        for (String word : words) {
            trieTree1.insert(TrieTreeUtil.str2CharList(word));
        }
        System.out.println(trieTree1.countPrefix(TrieTreeUtil.str2CharList(pref)));

        // delete and countPrefix
        String delWord = "a";
        trieTree1.delete(TrieTreeUtil.str2CharList(delWord));
        System.out.println(trieTree1.countPrefix(TrieTreeUtil.str2CharList(pref)));

        int n = 13;
        int k = 7;
        // insert and findIndex
        TrieTree<String> trieTree2 = new TrieTree<>();
        for (int i = 1; i <= n; i++) {
            // insert by trie sort
            trieTree2.insert(TrieTreeUtil.int2StrList(i));
        }
        List<String> rs = trieTree2.findIndexByDFS(k);
        System.out.println(TrieTreeUtil.strList2Int(rs));
        k = 2;
        rs = trieTree2.findIndexByDFS(k);
        System.out.println(TrieTreeUtil.strList2Int(rs));

        System.out.println(TrieTreeUtil.getTrieOrder(13));

    }

}
