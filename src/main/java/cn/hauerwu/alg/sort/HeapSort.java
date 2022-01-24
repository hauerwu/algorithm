package cn.hauerwu.alg.sort;

import cn.hauerwu.alg.comparer.Comparer;
import cn.hauerwu.alg.comparer.LessThanComparer;
import cn.hauerwu.structure.Heap;

public class HeapSort<K> {
    public void sort(K[] input, Comparer comparer){
        Heap<K> heap = new Heap<K>(input,comparer);
        heap.build();

        for(int i=heap.getSize();i>=2;i--){
            heap.swap(1,i);
            heap.decSize();
            heap.heapify(1);
        }
    }
}
