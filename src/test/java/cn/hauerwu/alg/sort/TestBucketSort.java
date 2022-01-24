package cn.hauerwu.alg.sort;

import cn.hauerwu.alg.comparer.BiggerThanComparer;
import cn.hauerwu.alg.comparer.LessThanComparer;
import cn.hauerwu.utils.AlgUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBucketSort {
    @Test
    public void test(){
        Double input[] = new Double[]{0.1,0.3,0.01,0.9,0.14,0.56,0.82};
        BucketSort bucketSort = new BucketSort(new BiggerThanComparer<>());

        bucketSort.sort(input);
    }
}
