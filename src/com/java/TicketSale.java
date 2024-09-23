package com.java;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: practice
 * Package: com.java
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/28 9:59
 * @Version 1.0
 */
public class TicketSale implements Runnable {
    private static int tickets = 100; // 共享的票数
//    private final Object lock = new Object(); // 锁对象，用于同步

    private int windowId;
    private static int curWindow = 1;
    private static final ReentrantLock LOCK = new ReentrantLock();

    private static final Condition A = LOCK.newCondition();

    public TicketSale(int windowId) {
        this.windowId = windowId;
    }

    @Override
    public void run() {
        while (true) {
            LOCK.lock();
            try {
                while (curWindow != windowId) {
                    try {
                        A.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (tickets > 0) {
                    try {
                        Thread.sleep(100); // 模拟卖票的时间延迟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 卖出了第 " + tickets + " 张票");
                    tickets--;
                    curWindow = curWindow % 3 + 1;
                    A.signalAll();
                } else {
                    A.signalAll();
                    break; // 如果票卖完了，退出循环
                }
            } finally {

                LOCK.unlock();
            }

        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        // 创建三个线程代表三个售票窗口
        ReentrantLock reentrantLock = new ReentrantLock();
//        Thread window1 = new Thread(new TicketSale(1), "窗口1");
//        Thread window2 = new Thread(new TicketSale(2), "窗口2");
//        Thread window3 = new Thread(new TicketSale(3), "窗口3");
//
//        // 启动三个线程
//        window1.start();
//        window2.start();
//        window3.start();
        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();
        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
        Object object = objectThreadLocal.get();
        CopyOnWriteArrayList<Object> objects = new CopyOnWriteArrayList<>();
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
        List<String> list = Arrays.asList("test1", "test23");
        new Thread(()->{

        });

        ExecutorService service1 = Executors.newFixedThreadPool(3);
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return 0;
            }
        });
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });
        Callable<String> callable = new MyCallable();
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<String> submit = service.submit(callable);

        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread t4 = new Thread(futureTask);
        t4.start();
        System.out.println(futureTask.get());
    }
}

class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("线程执行");
        return "test";
    }
}

