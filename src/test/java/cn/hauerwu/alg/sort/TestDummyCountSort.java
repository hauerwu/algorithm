package cn.hauerwu.alg.sort;

import cn.hauerwu.alg.comparer.LessThanComparer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDummyCountSort {
    @Test
    public void testSort(){
        Integer[] data = new Integer[]{10,3,5,9,100,2,2,3,4,5,9,100,10};
        DummyCountSort dummyCountSort = new DummyCountSort(new LessThanComparer<Integer>());
        dummyCountSort.sort(data);
    }

    @Test
    public void testNegativeSort(){
        Integer[] data = new Integer[]{-10,3,5,9,-100,2,2,3,-4,5,9,100,10};
        DummyCountSort dummyCountSort = new DummyCountSort(new LessThanComparer<Integer>());
        dummyCountSort.sort(data);
    }
}
