package cn.hauerwu.structure;

import cn.hauerwu.alg.comparer.BiggerThanComparer;
import cn.hauerwu.alg.comparer.LessThanComparer;
import cn.hauerwu.structure.Heap;

public class PriorityQueue<T> {
    private Heap<T> maxHeap;

    public PriorityQueue(T[] input){
        this.maxHeap = new Heap<T>(input,new LessThanComparer());
        this.maxHeap.build();
    }

    public void insert(T k){
        if(maxHeap.getSize() == maxHeap.getCapacity()){
            maxHeap.extend(maxHeap.getSize());
        }

        maxHeap.incSize();
        maxHeap.set(maxHeap.getSize(),k);
        increaseKey(maxHeap.getSize(),k);

    }

    public T maximum(){
        return maxHeap.get(1);
    }

    public T extractMax(){
        if(maxHeap.getSize() <1){
            return null;
        }

        T max = maximum();
        maxHeap.set(1,maxHeap.get(maxHeap.getSize()));
        maxHeap.decSize();

        maxHeap.heapify(1);

        return max;

    }

    public void increaseKey(int i,T k){
        T old = maxHeap.get(i);

        if(old == null){
            return;
        }

//        if(maxHeap.getComparer().compare(old,k)>0){
//            return;
//        }

        maxHeap.set(i,k);
        while(i>1 && maxHeap.getComparer().compare(maxHeap.get(maxHeap.parent(i)),maxHeap.get(i))<0){
            maxHeap.swap(maxHeap.parent(i),i);
            i = maxHeap.parent(i);
        }
    }

    public int getSize(){
        return maxHeap.getSize();
    }
}
