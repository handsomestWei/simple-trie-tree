package com.wjy.simple.trietree;

import java.util.LinkedList;
import java.util.List;

/**
 * 字典树节点
 */
public class TrieNode<T> {

    private T t;
    // 访问次数
    private int cnt;
    // TODO 压缩，类似隐式后缀树。添加子节点时实际没有添加，只记录访问次数。当访问到时再真正添加子节点
    private boolean isCompress;
    // TODO 层级。压缩和展开用
    private int level;
    // 子节点
    private List<TrieNode<T>> childTrieNodeList;
    // 是否结尾
    private boolean endFlg;

    public TrieNode(T t) {
        this.t = t;
        this.childTrieNodeList = new LinkedList<>();
    }

    public void addChildNode(TrieNode<T> trieNode) {
        childTrieNodeList.add(trieNode);
    }

    public TrieNode<T> getChildNode(T t) {
        for (TrieNode<T> trieNode : childTrieNodeList) {
            if (t.equals(trieNode.t)) {
                return trieNode;
            }
        }
        return null;
    }

    public T getVal() {
        return this.t;
    }

    public void addTraverseCnt() {
        // 访问次数+1
        this.cnt++;
    }

    public int getTraverseCnt() {
        return this.cnt;
    }

    public List<TrieNode<T>> getChildTrieNodeList() {
        return this.childTrieNodeList;
    }

    public void setEndFlg(boolean flg) {
        endFlg = flg;
    }

    public boolean getEndFlg() {
        return endFlg;
    }
}
