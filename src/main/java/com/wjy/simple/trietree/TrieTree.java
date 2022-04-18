package com.wjy.simple.trietree;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * 字典树：利用字符串的公共前缀可以减少查询字符串的时间，能够最大限度的减少无谓的字符串比较，同时在查询的过程中不需要预知待查询字符串的长度，沿着字典树的边进行匹配，查询效率比较高。
 */
public class TrieTree<T> {

    private TrieNode<T> root;

    public TrieTree() {
        this.root = new TrieNode<T>(null);
    }

    // 在字典树中插入单词
    public TrieNode<T> insert(List<T> ls) {
        TrieNode temp = root;
        for (int i = 0; i < ls.size(); i++) {
            T t = ls.get(i);
            TrieNode childNode = temp.getChildNode(t);
            if (childNode == null) {
                childNode = new TrieNode<>(t);
                temp.addChildNode(childNode);
            }
            // 访问次数+1
            childNode.addTraverseCnt();
            temp = childNode;
            if (i == ls.size() - 1) {
                // 最后一位，设置结束标记
                childNode.setEndFlg(true);
            }
        }
        return root;
    }

    // 在字典树中删除单词
    public void delete(List<T> ls) {
        TrieNode temp = root;
        TrieNode preNode = root;
        for (T t : ls) {
            TrieNode childNode = temp.getChildNode(t);
            if (childNode == null) {
                return;
            }
            preNode = temp;
            temp = childNode;
        }
        // 只需删除路径上的最后一个节点
        preNode.getChildTrieNodeList().remove(temp);
        // TODO 删除后更新路线上的结束节点标记
    }

    // 检查字典树中是否完全包含单词
    public boolean search(List<T> ls) {
        TrieNode temp = root;
        for (T t : ls) {
            TrieNode childNode = temp.getChildNode(t);
            if (childNode == null) {
                return false;
            }
            temp = childNode;
        }
        return temp.getEndFlg();
    }

    // 检查字典树中是否包含前缀
    public boolean startWith(List<T> ls) {
        TrieNode temp = root;
        for (T t : ls) {
            TrieNode childNode = temp.getChildNode(t);
            if (childNode == null) {
                return false;
            }
            temp = childNode;
        }
        return true;
    }

    // 统计在字典树中有多少个单词是以xxx为前缀的
    public int countPrefix(List<T> ls) {
        TrieNode temp = root;
        for (T t : ls) {
            TrieNode childNode = temp.getChildNode(t);
            if (childNode == null) {
                return 0;
            }
            temp = childNode;
        }
        return temp.getTraverseCnt();
    }

    // 深度优先查询第k位单词
    public List<T> findIndexByDFS(int index) {
        TrieNode temp = root;
        // 找到该节点，并拼接该条边上所有元素
        List<T> ls = new LinkedList<>();
        findIndexByDFS(temp, ls, index);
        return ls;
    }

    // 遍历字典树，返回字典序列表
    public List<T> getTrieOrder(T defaultVal, BiFunction<T, T, T> combineFunc) {
        TrieNode temp = root;
        List<T> ls = new LinkedList<>();
        travelTreeByBFS(temp, ls, defaultVal, combineFunc);
        return ls;
    }

    // 深度优先，递归查询第k位单词
    private int findIndexByDFS(TrieNode<T> trieNode, List<T> ls, int index) {
        if (index == 0) {
            return index;
        }
        List<TrieNode<T>> tList = trieNode.getChildTrieNodeList();
        if (trieNode.getVal() != null) {
            // 跳过根节点
            ls.add(trieNode.getVal());
            index--;
        }
        for (TrieNode<T> tempNode : tList) {
            if (index > tempNode.getTraverseCnt()) {
                // 遍历优化：不用遍历所有子树。根据访问次数，跳过子树遍历，直接找到目标区间
                index -= tempNode.getTraverseCnt();
            } else {
                index = findIndexByDFS(tempNode, ls, index);
                if (index == 0) {
                    return 0;
                }
                // 回溯
                ls.remove(ls.size() - 1);
            }
        }
        return index;
    }

    // 广度优先，递归遍历字典树
    private void travelTreeByBFS(TrieNode<T> trieNode, List<T> ls, T data, BiFunction<T, T, T> combineFunc) {
        if (trieNode == null) {
            return;
        }
        List<TrieNode<T>> childTrieNodeList = trieNode.getChildTrieNodeList();
        // 跳过根节点
        if (trieNode.getVal() != null) {
            // 合并
            data = combineFunc.apply(data, trieNode.getVal());
            ls.add(data);
        }
        for (TrieNode<T> tempNode : childTrieNodeList) {
            travelTreeByBFS(tempNode, ls, data, combineFunc);
        }
    }

}
