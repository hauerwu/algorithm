package cn.hauerwu.structure;


import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TestRBTree {
//    @Test
//    public void testRBTree(){
//        RBTree rbTree = new RBTree<Integer,Integer>();
//
//        rbTree.put(1,2);
//        assertEquals(rbTree.get(1),2);
//        rbTree.put(1,3);
//        assertEquals(rbTree.get(1),3);
//        rbTree.put(2,5);
//        assertEquals(rbTree.get(1),3);
//        assertEquals(rbTree.get(2),5);
//        rbTree.put(5,5);
//        rbTree.put(-1,15);
//        rbTree.put(0,10);assertEquals(rbTree.get(2),5);
//
//        assertEquals(rbTree.get(0),10);
//    }
//
//    @Test
//    public void testDesInsertFix(){
//        RBTree rbTree = new RBTree<Integer,Integer>();
//
//        for(int i=1000;i>=0;i--){
//            rbTree.put(i,i);
//        }
//
//
//
//        int height = rbTree.getTreeHeight();
//        int maxHeight = new Double(2*(Math.log(rbTree.getSize()+1))/Math.log(2)).intValue();
//
//        System.out.println("height "+height+" maxHeight "+maxHeight);
//
//        assertEquals(height<maxHeight,true);
//    }
//
//    @Test
//    public void testAesInsertFix(){
//        RBTree rbTree = new RBTree<Integer,Integer>();
//
//        for(int i=0;i<=1000;i++){
//            rbTree.put(i,i);
//        }
//
//
//        int height = rbTree.getTreeHeight();
//        int maxHeight = new Double(2*(Math.log(rbTree.getSize()+1))/Math.log(2)).intValue();
//
//        System.out.println("height "+height+" maxHeight "+maxHeight);
//
//        assertEquals(height<maxHeight,true);
//    }
//
//    @Test
//    public void testRandInsertFix(){
//        RBTree rbTree = new RBTree<Integer,Integer>();
//
//        Random r = new Random();
//        for(int i=0;i<=1000;i++){
//            int key = r.nextInt(10000);
//            rbTree.put(key,i);
//            System.out.println(key);
//        }
//
//
//        int height = rbTree.getTreeHeight();
//        int maxHeight = new Double(2*(Math.log(rbTree.getSize()+1))/Math.log(2)).intValue();
//
//        System.out.println("randFix height "+height+" maxHeight "+maxHeight);
//
//        assertEquals(height<maxHeight,true);
//    }

    @Test
    public void testRemove(){
        RBTree rbTree = new RBTree<Integer,Integer>();

        for(int i=1;i<=10;i++){
            rbTree.put(i,i);
        }

        rbTree.remove(3);
        rbTree.remove(5);
        rbTree.remove(1);
        rbTree.remove(7);
        rbTree.remove(8);

        checkHight(rbTree);
    }

    @Test
    public void testRandRemove(){
        RBTree rbTree = new RBTree<Integer,Integer>();
        int []keys = prepareTree(rbTree);

        List list = new ArrayList();
        for(int i = 0;i < keys.length;i++){
            list.add(keys[i]);
        }

        Collections.shuffle(list);

        list = list.subList(0,list.size()/2);

        Iterator ite = list.iterator();
        while(ite.hasNext()) {
            rbTree.remove(ite.next());
        }

        checkHight(rbTree);
    }

    public int[] prepareTree(RBTree rbTree){
        int []keys = new int[1000];
        Random r = new Random();
        for(int i=0;i<1000;i++){
            int key = r.nextInt(10000);
            rbTree.put(key,i);
            keys[i] = key;
        }

        return keys;
    }

    public void checkHight(RBTree rbTree){
        int height = rbTree.getTreeHeight();
        int maxHeight = new Double(2*(Math.log(rbTree.getSize()+1))/Math.log(2)).intValue();

        System.out.println("check height: size "+rbTree.getSize()+"  height "+height+" maxHeight "+maxHeight);

        assertEquals(height<=maxHeight,true);
    }

}
