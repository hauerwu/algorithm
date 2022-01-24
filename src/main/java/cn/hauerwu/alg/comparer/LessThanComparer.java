package cn.hauerwu.alg.comparer;

public class LessThanComparer<T> implements Comparer<T>{
    public int compare(T from,T to){
        return ((Comparable)from).compareTo(to);
    }
}
