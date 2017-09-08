package com.channel.AccountDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserAccount2 {
    private final Lock monitor = new ReentrantLock();

    private final Condition low = monitor.newCondition();

    private final Condition high = monitor.newCondition();
    private int balance;
    private long maxMoney = 5000000;
    private long minMOney = 1000000;
    public AtomicInteger code = new AtomicInteger(0);

    public UserAccount2(int balance) {
        this.balance = balance;
    }

    public  void deposit(int amount) {
       monitor.lock();
       try{
            if (balance + amount < maxMoney) {
                balance += amount;
                System.out.println(Thread.currentThread().getName() + ":批次—"+this.increase()+"—;存：" + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + ":批次—"+this.increase()+"—;超过最大限额！");
            }
            high.signal();
        } finally {
            monitor.unlock();
        }

    }

    public  void withdraw(int amount) {
        monitor.lock();
        try{
            if (minMOney <= balance - amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName()+":批次—"+this.increase()+"—;取：" + balance);
            }else{
                System.out.println(Thread.currentThread().getName()+":批次—"+this.increase()+"—;无法取款，最小限额为"+minMOney);
            }
            low.signal();
        } finally {
            monitor.unlock();
        }
    }

    private synchronized  int increase(){
       return code.getAndIncrement();
    }

    public static void main(String[] args) {
        UserAccount2 u = new UserAccount2(1000000);
        ExecutorService executors = Executors.newFixedThreadPool(6);
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                executors.submit(()->{
                    long start = System.currentTimeMillis();
                    u.deposit(1000000);
                    long end = System.currentTimeMillis();
                    System.out.println(Thread.currentThread().getName()+":批次—"+u.code.get()+"—;after 存-----------------------"+u.balance+";耗时："+(end-start));
                });
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 101; i++) {
                    executors.submit(() -> {
                        long start = System.currentTimeMillis();
                        u.withdraw(1000000);
                        long end = System.currentTimeMillis();
                        System.out.println(Thread.currentThread().getName() + ":批次—" + u.code.get() + "—;after 取-----------------------" + u.balance + ";耗时：" + (end - start));
                    });
                }
            }
        };
        new Thread(r1).start();
        new Thread(r2).start();
    }
}