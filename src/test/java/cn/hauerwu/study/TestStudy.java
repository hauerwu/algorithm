package cn.hauerwu.study;

import com.sun.javafx.util.Logging;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FilterOutputStream;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class TestStudy {
    @Test
    public void testWeekHashMap(){
        Map map;
        map = new WeakHashMap();

        for (int i =0;i<10000000;i++){
            map.put("key"+i,new byte[i]);
        }
    }

    @Test
    public void testReferenceQueue() {
        Object counter = new Object();
        ReferenceQueue refQueue = new ReferenceQueue<>();
        SoftReference p = new SoftReference<>(counter, refQueue);
        counter = null;
        System.gc();
        try {
            // Remove是一个阻塞方法，可以指定timeout，或者选择一直阻塞
            Reference ref = refQueue.remove(1000L);
            if (ref != null) {
                System.out.println("do something");
            }
        } catch (InterruptedException e) { // Handle it}
        }
    }

    @Test
    public void testString(){
        String str1 = "hello";
        str1 = "hello"+"world";

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("hello");
        stringBuffer.append("world");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("hello");
    }

    @Test
    public void testProxy(){
        HelloImpl hello = new HelloImpl();
        MyInvocationHandler handler = new MyInvocationHandler(hello);
        Hello proxy = (Hello)Proxy.newProxyInstance(HelloImpl.class.getClassLoader(),HelloImpl.class.getInterfaces(),handler);
        proxy.sayHello();
    }

    @Test
    public void testInteger(){
        Integer i =1;
        i=i+1;
    }

    @Test
    public void testList(){
        Vector<Integer> v = new Vector<>();
        ArrayList<Integer> al = new ArrayList<>();
    }

    @Test
    public void testMap(){
        Hashtable<Integer,Integer> tab = new Hashtable<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        TreeMap<Integer,Integer> tree = new TreeMap<>();
        LinkedHashMap<Integer,Integer> lhmap = new LinkedHashMap<>();

        lhmap.put(10,10);
        lhmap.put(9,9);
        lhmap.put(3,3);
        lhmap.put(2,2);
        lhmap.put(1,1);
        lhmap.put(12,12);

        Set<Map.Entry<Integer,Integer>> set = lhmap.entrySet();
        for (Map.Entry<Integer,Integer> it:set) {
            System.out.println(it.getKey());
        }

        lhmap.forEach((k,v)->{
            System.out.println(k);
        });
    }

    @Test
    public void testConcurrent(){
        ConcurrentHashMap<Integer,Integer> chash = new ConcurrentHashMap<>();

        chash.put(1,1);
    }

    @Test
    public void testBuffer(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.putChar('c');
        byteBuffer.putChar('a');
        byteBuffer.putInt(1);
        char c = byteBuffer.getChar();
        byteBuffer.flip();
        c = byteBuffer.getChar();
        byteBuffer.compact();
    }

    private class testThread implements Runnable{
        private CyclicBarrier countDownLatch;
        private boolean isConsumer;
        public testThread(CyclicBarrier countDownLatch,boolean isConsumer){
            this.countDownLatch = countDownLatch;
            this.isConsumer = isConsumer;
        }

        public void run(){

            System.out.println("thread run");
            try {
                if(this.isConsumer) {
                    Thread.sleep(3000);
                }
            }catch (InterruptedException e){

            }


                try {
                    this.countDownLatch.await();
                    System.out.println("thread await finish");
                }catch (InterruptedException e){

                }catch (BrokenBarrierException e2){

                }
            }

    }

    @Test
    public void testCountDownLatch(){
        CountDownLatch countDownLatch = new CountDownLatch(1);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        Thread thread = new Thread(new testThread(cyclicBarrier,true));
        thread.start();

        new Thread(new testThread(cyclicBarrier,false)).start();

        try{
            cyclicBarrier.await();
        }catch (InterruptedException e){

        }catch (BrokenBarrierException e2){

        }
        System.out.println("thread finish");
    }

    private class testIntThread implements Runnable{
        ReentrantLock lock;
        public testIntThread(ReentrantLock l){
            this.lock = l;
        }
        public void run(){
            this.lock.lock();
            System.out.println("do");
        }
    }

    @Test
    public void testInt(){
    }

    @Test
    public void testExecutors(){
        ExecutorService executors = Executors.newFixedThreadPool(10);
        executors.submit(new testIntThread(new ReentrantLock()));
    }

    @Test
    public void test() throws InterruptedException{
        ReentrantLock lock = new ReentrantLock();

        lock.tryLock();
        Thread t = new Thread(new testIntThread(lock));
        t.start();

        Thread.sleep(3000);
        lock.unlock();
        t.join();

    }

    @Test
    public void testClassLoader()throws ClassNotFoundException{
        System.out.println("Classloader of Logging:"
                    + Logging.class.getClassLoader());

        System.out.println("Classloader of ArrayList:"
                    + ArrayList.class.getClassLoader());

        Thread t = new Thread();
    }

}
