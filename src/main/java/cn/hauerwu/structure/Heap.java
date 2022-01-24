package cn.hauerwu.structure;

import cn.hauerwu.alg.comparer.Comparer;
import org.omg.CORBA.Object;

import java.lang.reflect.Array;

public class Heap<K> {
    private int size; //大小
    private K[] data; //存储数据的数组

    private Comparer comparer;

    public Comparer getComparer() {
        return comparer;
    }

    public void setComparer(Comparer comparer) {
        this.comparer = comparer;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void decSize(){
        this.size -= 1;
    }

    public void incSize(){
        this.size += 1;
    }

    public int getCapacity(){
        return this.data.length;
    }

    public Heap(K[] input,Comparer comparer){
        this.data = input;
        this.size = input.length;
        this.comparer = comparer;
    }

    public int parent(int i){
        return i>>1;
    }

    public int leftChild(int i){
        return i<<1;
    }

    public int rightChild(int i){
        return (i<<1)+1;
    }

    public K get(int i){
        if(i <= 0 || i > getSize()){
            return null;
        }

        return data[i-1];
    }

    public void set(int i,K value){
        if(i <= 0 || i > getSize()){
            return;
        }

        this.data[i-1] = value;
    }

    public void build(){
        for(int i=getSize()/2;i>=1;i--){
            heapify(i);
        }
    }

    public void heapify(int i){
        if(i > getSize() || i <= 0){
            return;
        }

        int l = leftChild(i);
        int r = rightChild(i);
        int max = i;

        if(l <= getSize() && comparer.compare(get(l),get(i)) > 0){
            max = l;
        }

        if(r <= getSize() && comparer.compare(get(r),get(max)) > 0){
            max = r;
        }

        if(max != i){
            swap(i,max);

            heapify(max);
        }
    }

    public void swap(int i,int j){
        if(i != j && i <= getSize() && j <= getSize() ){
            K temp = get(i);
            set(i,get(j));
            set(j,temp);
        }
    }

    public void extend(int len){
        K[] n = (K[])Array.newInstance(data[0].getClass(),this.getSize()+len);

        for(int i=0;i<getSize();i++){
            n[i] = data[i];
        }
        data = n;
    }

}
