package cn.hauerwu.alg.sort;

import cn.hauerwu.alg.comparer.Comparer;
import cn.hauerwu.alg.comparer.LessThanComparer;
import cn.hauerwu.alg.converter.Converter;
import cn.hauerwu.alg.converter.CopyConverter;

public class CountSort {
    private Comparer<Integer> comparer;
    private Converter<Integer> converter;

    public CountSort(Comparer<Integer> comparer,Converter<Integer> converter){
        this.comparer = comparer;
        this.converter = converter;
    }

    public CountSort(Comparer<Integer> comparer){
        this.comparer = comparer;
        this.converter = new CopyConverter<Integer>();
    }

    public void sort(Integer[] input,Integer[] output){
        Integer max = null;
        Integer min = null;
        for(int i=0;i<input.length;i++){
            Integer current = converter.convert(input[i]);
            if(max == null || comparer.compare(current,max) >0){
                max = current;
            }

            if(min == null || comparer.compare(current,min) <0){
                min = current;
            }
        }

        Integer delta = 0;
        Integer countLen=0;



        if(min>max){
            Integer temp = min;
            min = max;
            max = temp;
        }

        delta = -min;
        countLen = max - min + 1;

//        if(min >=0 ){
//            countLen = max + 1;
//        }else if(min <0 && max >0){
//            delta = -min;
//            countLen = max - min + 1;
//        }else if(min <0 && max <0){
//            delta = -min;
//            countLen = max - min + 1;
//        }

//        if (min < 0) {
//            delta = -min;
//            countLen = max + delta + 1;
//        } else if (max < 0) {
//            delta = -max;
//            countLen = min + delta + 1;
//        } else {
//            countLen = max + delta + 1;
//        }


        Integer[] countArray = new Integer[countLen];
        for(int i=0;i<countLen;i++){
            countArray[i] = 0;
        }



        for(int i=0;i<input.length;i++){
            Integer current = converter.convert(input[i])+delta;
            try {
                countArray[current]++;
            }catch (ArrayIndexOutOfBoundsException e){
                e.toString();
            }
        }


        if(comparer instanceof LessThanComparer) {
            for(int i=1;i<countLen;i++){
                countArray[i]=countArray[i-1]+countArray[i];
            }
        }else{
            for(int i=countLen-2;i>=0;i--){
                countArray[i]=countArray[i+1]+countArray[i];
            }
        }


        for(int i=input.length-1;i>=0;i--){
            output[countArray[converter.convert(input[i])+delta]-1]=input[i];
            countArray[converter.convert(input[i])+delta]--;
        }
    }
}
