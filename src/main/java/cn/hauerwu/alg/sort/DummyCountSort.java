package cn.hauerwu.alg.sort;

import cn.hauerwu.alg.comparer.Comparer;

public class DummyCountSort {
    private Comparer<Integer> comparer;

    public DummyCountSort(Comparer<Integer> comparer){
        this.comparer = comparer;
    }

    public void sort(Integer[] input){
        Integer max = null;
        Integer min = null;
        for(int i=0;i<input.length;i++){
            Integer current = input[i];
            if(max == null || comparer.compare(current,max) >0){
                max = current;
            }

            if(min == null || comparer.compare(current,min) <0){
                min = current;
            }
        }

        Integer delta = 0;
        if(min < 0){
            delta = -min;
        }

        Integer[] countArray = new Integer[max+delta+1];
        for(int i=0;i<input.length;i++){
            Integer current = input[i]+delta;
            Integer currentCount = countArray[current];
            if(currentCount == null){
                countArray[current] = 1;
            }else {
                countArray[current]++;
            }
        }

        for(int i=0,k=0;i<countArray.length;i++){
            Integer currentCount = countArray[i];
            for(int j=0;currentCount != null && j<currentCount;j++){
                input[k] = i-delta;
                k++;
            }
        }
    }
}
