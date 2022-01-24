package cn.hauerwu.alg.sort;

import cn.hauerwu.alg.comparer.LessThanComparer;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TestQuickSort {
    @Test
    public void testSort(){
         Integer []data= new Integer[]{5,4,6,7,1};
         QuickSort<Integer> quickSort = new QuickSort(new LessThanComparer());
         quickSort.sort(data);
    }
}
