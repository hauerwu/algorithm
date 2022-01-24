package cn.hauerwu.alg.sort;

import cn.hauerwu.alg.comparer.BiggerThanComparer;
import cn.hauerwu.alg.comparer.LessThanComparer;
import cn.hauerwu.utils.AlgUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCountSort {
    @Test
    public void testSort(){
        Integer[] input = new Integer[]{10,3,5,9,100,2,2,3,4,5,9,100,10};
        Integer[] output = new Integer[input.length];
        CountSort countSort = new CountSort(new LessThanComparer<Integer>());
        countSort.sort(input,output);
    }

    @Test
    public void testNegativeSort(){
        Integer[] input = new Integer[]{-10,3,5,9,-100,2,2,3,-4,5,9,100,10};
        Integer[] output = new Integer[input.length];
        CountSort countSort = new CountSort(new LessThanComparer<>());
        countSort.sort(input,output);

        assertEquals(AlgUtil.isSorted(output,new LessThanComparer<>()),true);
    }

    @Test
    public void testRand(){
        Integer[] input = AlgUtil.randData();
        Integer[] output = new Integer[input.length];

        CountSort countSort = new CountSort(new LessThanComparer<>());
        countSort.sort(input,output);
        assertEquals(AlgUtil.isSorted(output,new LessThanComparer<>()),true);

        countSort = new CountSort(new BiggerThanComparer<>());
        countSort.sort(input,output);
        assertEquals(AlgUtil.isSorted(output,new BiggerThanComparer<>()),true);
    }
}
