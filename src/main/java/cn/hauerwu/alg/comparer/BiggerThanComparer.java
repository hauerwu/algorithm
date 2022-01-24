package cn.hauerwu.alg.comparer;

public class BiggerThanComparer<T> implements Comparer<T>{
    public int compare(T from,T to){
        return ((Comparable)to).compareTo(from);
    }
}
