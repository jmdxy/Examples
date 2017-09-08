package com.channel.AccountDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserAccount {
    private int balance;
    private long maxMoney = 5000000;
    private long minMOney = 1000000;

    public UserAccount(int balance) {
        this.balance = balance;
    }

    public  void deposit(int amount) {
        synchronized (this) {
            if (balance + amount < maxMoney) {
                balance += amount;
                System.out.println(Thread.currentThread().getName() + "存：" + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + "超过最大限额！");
            }
        }

    }

    public  void withdraw(int amount) {
        synchronized (this){
            if (minMOney < balance - amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName()+"取：" + balance);
            }else{
                System.out.println(Thread.currentThread().getName()+"无法取款，最小限额为"+minMOney);
            }
        }
    }

    public static void main(String[] args) {
        UserAccount u = new UserAccount(1000000);
        ExecutorService executors = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 100; i++) {
            executors.submit(()->{
                long start = System.currentTimeMillis();
                u.deposit(1000000);
                long end = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName()+"after 存-----------------------"+u.balance+";耗时："+(end-start));
            });
        }
        for (int i = 0; i < 101; i++) {
            executors.submit(()->{
                long start = System.currentTimeMillis();
                u.withdraw(1000000);
                long end = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName()+"after 取-----------------------"+u.balance+";耗时："+(end-start));
            });
        }
        executors.shutdown();

    }
}