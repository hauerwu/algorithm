package cn.hauerwu.structure;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TestPriorityQueue {
    @Test
    public void testPQ(){
        Integer[] data= randData();
        PriorityQueue<Integer> pq = new PriorityQueue<>(data);

        Random r = new Random();
        int insertNum = r.nextInt(1000);
        for(int i=0;i<insertNum;i++){
            pq.insert(r.nextInt(100000));
        }

        assertEquals(pq.getSize(),data.length+insertNum);

        Integer max = pq.maximum();
        for(int i=0;i<pq.getSize();i++){
            Integer current = pq.extractMax();
            assertEquals(current<=max,true);
            max = current;
        }

    }

    private Integer[] randData(){
        Random r = new Random();
        int len = r.nextInt(1000);
        Integer []result = new Integer[len];

        for(int i=0;i<len;i++){
            result[i] = r.nextInt(100000);
        }

        return result;
    }
}
