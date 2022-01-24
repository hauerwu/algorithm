package cn.hauerwu.alg.sort;

import cn.hauerwu.alg.comparer.LessThanComparer;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TestHeapSort {
    @Test
    public void testSort(){
        Random r = new Random();
        int size = r.nextInt(10000);
        Integer[] input = new Integer[size];

        for(int i=0;i<size;i++){
            input[i] = r.nextInt(10000);

        }

        HeapSort<Integer> hs = new HeapSort<Integer>();
        hs.sort(input,new LessThanComparer());

        boolean not_sorted = false;
        if(size > 1) {
            int prev = input[0];
            for (int i = 1; i < size; i++) {
                if(prev > input[i]){
                    not_sorted = true;
                    break;
                }
                prev = input[i];
            }
        }

        assertEquals(not_sorted,false);
    }
}
