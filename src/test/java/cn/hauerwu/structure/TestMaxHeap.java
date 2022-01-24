package cn.hauerwu.structure;

import static org.junit.Assert.assertEquals;

import cn.hauerwu.alg.comparer.LessThanComparer;
import org.junit.Test;

public class TestMaxHeap {
    @Test
    public void testCreate(){
        Integer[] input = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        Heap<Integer> h = new Heap<Integer>(input,new LessThanComparer());

        h.build();
        h.heapify(1);

    }
}
