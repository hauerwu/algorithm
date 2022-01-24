package cn.hauerwu.utils;

import cn.hauerwu.alg.comparer.Comparer;

import java.util.Random;

public class AlgUtil {
    static public <T> boolean isSorted(T[] input, Comparer<T> comparer){
        T prev = null;
        for(int i=0;i<input.length;i++){
            if(prev != null && comparer.compare(prev,input[i]) >0){
                return false;
            }
            prev = input[i];
        }

        return true;
    }

    static public Integer[] randData(){
        Random r = new Random();
        int len = r.nextInt(1000);
        Integer []result = new Integer[len];

        for(int i=0;i<len;i++){
            result[i] = r.nextInt(100000)- r.nextInt(100000);
        }

        return result;
    }

    static public Integer[] randPositiveData(){
        Random r = new Random();
        int len = r.nextInt(1000);
        Integer []result = new Integer[len];

        for(int i=0;i<len;i++) {
            result[i] = r.nextInt(100000);
        }

        return result;
    }

    static public int getDigitalLen(int i){
        int result =0;
        if(i<0){
            i=0-i;
        }

        while(i>0){
            result++;
            i=i/10;
        }

        return result;
    }

    static public int getDigitalValue(int input,int d){
        for(int i=0;i<d-1;i++){
            input = input/10;
        }

        return input - (input/10)*10;
    }
}
