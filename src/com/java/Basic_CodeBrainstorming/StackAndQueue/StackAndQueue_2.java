package com.java.Basic_CodeBrainstorming.StackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * ClassName: StackAndQueue_2
 * Package: com.java.Basic_CodeBrainstorming.StackAndQueue
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/22 11:37
 * @Version 1.0
 */
public class StackAndQueue_2 {

}

//法一：创建和栈中元素一样元素的队列queue1和辅助队列queue2，把que1最后面的元素以外的元素都备份到que2，然后弹出最后面的元素，再把其他元素从que2导回que1。
class MyStack {
    Deque<Integer> queue1;
    Deque<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();//和栈中保持一样元素的队列
        queue2 = new LinkedList<>();//辅助队列
    }

    public void push(int x) {
        queue2.offer(x);//先放在辅助队列中
        while (!queue1.isEmpty())//将queue1中的元素放入到辅助队列后，实现堆栈的后进先出
            queue2.offer(queue1.poll());
        Deque<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;//最后交换queue1和queue2，将所有元素放入到queue1中
    }

    public int pop() {
        return queue1.poll();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}

//法二：法一的优化，不使用辅助队列，每存入一个元素到队列中时，将前面所有的元素出队再入队，实现逆置来达到逆置的效果
class MyStack2 {
    Deque<Integer> queue;

    public MyStack2() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        int n = queue.size();//当前列表中元素的个数，用于记录往后插入的次数
        queue.offer(x);
        while (n-- > 0)//将该插入元素之前的所有元素移至该元素后面
            queue.offer(queue.poll());
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}