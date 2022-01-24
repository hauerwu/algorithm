package cn.hauerwu.structure;

import java.lang.reflect.Array;

public class BTree<K,V> {
    private class BTreeNode{
        boolean isLeaf;
        int n;
        Object[] keys;
        Object[] childrens;
        V value;

        private BTreeNode(int t){
            isLeaf = false;
            n = 0;
            keys = new Object[2*t-1];
            childrens = new Object[2*t];
        }

        private BTreeNode(V value){
            isLeaf = true;
            value = value;
        }
    }

    private BTreeNode root;

    public BTreeNode search(K key){
        return searchInternal(root,key);
    }

    private BTreeNode searchInternal(BTreeNode root,K key){
        return null;
    }

}
