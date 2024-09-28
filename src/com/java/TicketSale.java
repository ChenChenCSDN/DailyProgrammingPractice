package com.java;


import org.junit.Test;

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
        TicketSale ticketSale = new TicketSale(3);
        int[] result = ticketSale.sortedSquares(new int[]{3, 7, 6, 2, 5, 4});
        System.out.println(Arrays.toString(result));
    }

    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        BubbleSort(nums);
        return nums;
    }

    public void quicksort(int[] nums, int low, int high) {
        if (low < high) {
            int pivorition = findPivorition(nums, low, high);
            findPivorition(nums, 0, pivorition - 1);
            findPivorition(nums, pivorition + 1, high);
        }
    }

    public int findPivorition(int[] nums, int low, int high) {
        int pivort = nums[low];
        while (low < high) {
            while (low < high && nums[high] >= pivort) high--;
            nums[low] = nums[high];
            while (low < high && nums[low] <= pivort) low++;
            nums[high] = nums[low];
        }
        nums[low] = pivort;
        return low;
    }

    public void BubbleSort(int[] nums) {
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = true;
                }
            }
            if (flag == false)
                return;
        }
    }
}

class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("线程执行");
        return "test";
    }
}

