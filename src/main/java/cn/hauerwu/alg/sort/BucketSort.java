package cn.hauerwu.alg.sort;

import cn.hauerwu.alg.comparer.Comparer;
import cn.hauerwu.alg.comparer.LessThanComparer;
import cn.hauerwu.structure.LinkList;

public class BucketSort{
    private Comparer<Double> comparer;
    public BucketSort(Comparer<Double> comparer){
        this.comparer = comparer;
    }

    public void sort(Double[] input){
        int n = input.length;

        //初始化桶
        LinkList<Double> bucket[] = new LinkList[n];
        for(int i=0;i<n;i++){
            bucket[i] = new LinkList<>();
        }

        //将各个元素通过插入排序，插入到桶中合适的链表里
        for(int i=0;i<n;i++){
            Double value = input[i];
            int idx = (int)Math.floor(n*value);
            bucket[idx].insertSort(value,this.comparer);
        }

        //从桶中一一取出排序
        if(this.comparer instanceof LessThanComparer){
            for(int i=0,j=0;i<n;i++){
                for (Double it:bucket[i]
                ) {
                    input[j]=it;
                    j++;
                }
            }
        }else{
            for(int i=n-1,j=0;i>=0;i--){
                for (Double it:bucket[i]
                ) {
                    input[j]=it;
                    j++;
                }
            }
        }

    }
}
