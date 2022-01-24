package cn.hauerwu.alg.sort;

import cn.hauerwu.alg.comparer.Comparer;

public class QuickSort<T> {
    private Comparer<T> comparer;

    public QuickSort(Comparer<T> comparer){
        this.comparer = comparer;
    }

    public void sort(T[] input){
        if(input.length >0) {
            internalSort(input, 0, input.length - 1);
        }
    }

    private void internalSort(T[]input,int p,int r){
        if(p>=r){
            return;
        }

        int q = partition(input,p,r);
        internalSort(input,p,q-1);
        internalSort(input,q+1,r);
    }

    private int partition(T[] input,int p,int r){
        T x = input[r];
        int i = p-1;
        for(int j = p;j<= r-1;j++){
            if(comparer.compare(input[j],x) <=0){
                i = i +1;
                swap(input,i,j);
            }
        }
        swap(input,i+1,r);
        return i+1;
    }

    private void swap(T[] input,int i,int j){
        T temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
