package com.wjy.simple.trietree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 字典树工具类
 * 
 * @author weijiayu
 * @date 2022/4/18 11:29
 */
public class TrieTreeUtil {

    // 按字典序返回范围[1, n]内所有整数
    public static List<Integer> getTrieOrder(int n) {
        // 构造字典树
        TrieTree<String> trieTree = new TrieTree();
        // 插入
        for (int i = 1; i <= n; i++) {
            trieTree.insert(int2StrList(i));
        }
        // 字典序遍历
        List<String> strList = trieTree.getTrieOrder("", (s1, s2) -> {
            return s1 + s2;
        });
        List<Integer> ls = new ArrayList<>(n);
        for (String val : strList) {
            ls.add(Integer.parseInt(val));
        }
        return ls;
    }

    // 字符串转字符列表："qwe"=>{'q','w','e'}
    public static List<Character> str2CharList(String str) {
        List<Character> charLs = new ArrayList<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            charLs.add(str.charAt(i));
        }
        return charLs;
    }

    // 整数转字符串列表：123=>{"1","2","3"}
    public static List<String> int2StrList(int i) {
        String s = String.valueOf(i);
        List<String> strLs = new LinkedList<>();
        for (int j = 0; j < s.length(); j++) {
            strLs.add(String.valueOf(s.charAt(j)));
        }
        return strLs;
    }

    // 字符串列表转整数：{"1","2","3"}=>123
    public static int strList2Int(List<String> strLs) {
        String sVal = "";
        for (String str : strLs) {
            sVal += str;
        }
        return Integer.valueOf(sVal);
    }
}
