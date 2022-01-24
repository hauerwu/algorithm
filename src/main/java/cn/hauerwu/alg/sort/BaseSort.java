package cn.hauerwu.alg.sort;

import cn.hauerwu.alg.comparer.Comparer;
import cn.hauerwu.alg.comparer.LessThanComparer;

public class BaseSort<T> {
    protected Comparer<T> comparer;

    public BaseSort(){
        this.comparer = new LessThanComparer<>();
    }

    public BaseSort(Comparer<T> comparer)
    {
        this.comparer = comparer;
    }
}
