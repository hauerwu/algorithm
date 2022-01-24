package cn.hauerwu.alg.sort;

import cn.hauerwu.alg.comparer.BiggerThanComparer;
import cn.hauerwu.alg.comparer.LessThanComparer;
import cn.hauerwu.utils.AlgUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRadixSort {
    @Test
    public void testRand(){
        Integer[] input = AlgUtil.randPositiveData();
        Integer[] output = new Integer[input.length];

        RadixSort radixSort = new RadixSort(new LessThanComparer<>());
        radixSort.sort(input,output);
        assertEquals(AlgUtil.isSorted(output,new LessThanComparer<>()),true);
    }
}
