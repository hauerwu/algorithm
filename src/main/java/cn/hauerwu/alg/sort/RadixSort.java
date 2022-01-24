package cn.hauerwu.alg.sort;

import cn.hauerwu.alg.comparer.Comparer;
import cn.hauerwu.alg.comparer.IntegerDigitalComparer;
import cn.hauerwu.alg.comparer.LessThanComparer;
import cn.hauerwu.alg.converter.DigitalConverter;
import cn.hauerwu.utils.AlgUtil;

import java.lang.reflect.Array;

public class RadixSort extends BaseSort<Integer>{

    public RadixSort(Comparer<Integer> comparer){
        super(comparer);
    }

    public void sort(Integer[] input,Integer[] output){
        int maxDigitalLen = 0;
        for(int i=0;i<input.length;i++){
            int currentDigitalLen = AlgUtil.getDigitalLen(input[i]);
            if(currentDigitalLen >maxDigitalLen){
                maxDigitalLen = currentDigitalLen;
            }
        }

        Integer []inter = new Integer[input.length];
        System.arraycopy(input,0,inter,0,input.length);
        for(int i=0;i<maxDigitalLen;i++){
            CountSort countSort = new CountSort(this.comparer,new DigitalConverter(i+1));
            countSort.sort(inter,output);
            System.arraycopy(output,0,inter,0,inter.length);
        }
    }
}
