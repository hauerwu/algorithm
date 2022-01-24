package cn.hauerwu.structure;

import cn.hauerwu.alg.comparer.Comparer;

import java.util.Iterator;

public class LinkList<T> implements Iterable<T>{
    private LinkListNode<T> header;
    private LinkListNode<T> tail;
    private int size;

    private class LinkListNode<T>{
        private T data;
        private LinkListNode<T> next;

        private LinkListNode(T data){
            this.data = data;
            this.next = null;
        }
    }

    public class LinkListIterator<T> implements Iterator<T>
    {
        private LinkListNode cursor = header;
        public boolean hasNext(){
            return cursor != null;
        }

        public T next(){
            LinkListNode result = cursor;
            cursor = cursor.next;
            return (T)result.data;
        }
    }

    public Iterator<T> iterator(){
        return new LinkListIterator<>();
    }

    public void add(T data){
        if(tail == null){
            header = tail = new LinkListNode<>(data);
        }else{
            tail.next = new LinkListNode<>(data);
            tail = tail.next;
        }
        size++;
    }

    public void del(T data){
        LinkListNode<T> cursor = header;
        LinkListNode<T> prev = null;
        while(cursor != null){
            if(((Comparable)data).equals(cursor.data)){
                if(prev == null){
                    header = cursor.next;
                }else{
                    prev.next = cursor.next;
                }

                if(cursor == tail){
                    tail = prev;
                }
                size--;
                return;
            }
            prev = cursor;
            cursor = cursor.next;

        }
    }

    public int getSize(){
        return size;
    }

    public void insertSort(T data, Comparer<T> comparer){
        LinkListNode<T> cursor = header;
        LinkListNode<T> prev = null;
        while(cursor != null){
            if(comparer.compare(cursor.data,data)>0){
                break;
            }
            prev = cursor;
            cursor = cursor.next;
        }

        LinkListNode<T> newNode = new LinkListNode<>(data);
        if(prev == null){
            newNode.next = header;
            header = newNode;
        }else{
            newNode.next = prev.next;
            prev.next = newNode;
        }

        if(prev == tail){
            tail = newNode;
        }
        size++;
    }
}
