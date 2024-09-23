package com.java.Basic_CodeBrainstorming.StackAndQueue;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: StackAndQueue_1
 * Package: com.java.Basic_CodeBrainstorming.StackAndQueue
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/22 11:13
 * @Version 1.0
 */
public class StackAndQueue_1 {
    @Test
    public void test1() {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        System.out.println(myQueue);
        myQueue.push(2);
        System.out.println(myQueue);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue);
        System.out.println(myQueue.empty());
        System.out.println(myQueue.pop());
    }
}

class MyQueue {
    //Deque是一个双端队列接口，继承自Queue接口，Deque的实现类是LinkedList、ArrayDeque、LinkedBlockingDeque，其中LinkedList是最常用的。
    //Deque即可当作双端队列，也可当作常规队列和堆栈
    Deque<Integer> stackIn;
    Deque<Integer> stackOut;

    //实现思路：创建两个堆栈stackIn和stackOut，stackIn用来存储插入的元素，stackOut存储要输出的元素
    // 要入队时直接入stackIn，要出队时判断stackOut是否为空，若为空则将stackIn中的元素全部放入stackOut中，然后输出即可；若不为空则直接输出stackOut中的元素
    public MyQueue() {
        stackIn = new ArrayDeque<>();
        stackOut = new ArrayDeque<>();
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "stackIn=" + stackIn +
                ", stackOut=" + stackOut +
                '}';
    }

    public void push(int x) {//元素插入，直接入stackIn
        stackIn.push(x);
    }

    public int pop() {//元素出队，判断stackOut是否为空，为空则将stackIn放入stackOut后再输出；不为空则直接输出
        dumpStack();
        return stackOut.pop();
    }

    public int peek() {
        dumpStack();
        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    public void dumpStack() {//判断stackOut是否为空，为空则将stackIn放入stackOut后再输出；不为空则直接输出
        if (!stackOut.isEmpty()) return;
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
    }
}
