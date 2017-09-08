package com.channel;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 信号量使用详解,使用信号量来管理有限的资源 可以限制并发数
 * 创建 :
 * 1). new Semaphore(resourceNumber);
 * 2) new Semaphore(resourceNumber,isFair); 较大概率的按照线程创建的顺序优先获得执行权。
 * 方法：
 * 1.acquire(); 获取资源，线程如果interrupted 则会抛错。
 * 2.release(); 释放资源。
 * 3.acquireUninterruptibly();等待许可的情况下不允许被中断
 * 4.semaphore.availablePermits(); 返回对象当前可用许可数目。
 * 5.semaphore.drainPermits();返回立即可用的许可数并将许可数置为0；
 * 6.semaphore.getQueueLength(); 获取等待获取许可的线程数。
 * 7.semaphore.hasQueuedThreads(); 是否有线程在等待获取许可。
 * 8.tryAcquire(); 尝试获取一个锁，如果获取不到 则立即返回，无阻塞。如果有则获取这个锁。
 * 9.tryAcquire(long timeout, TimeUnit unit); 在给定的时间内获取一个锁，如果获取不到则返回false;
 * 10.tryAcquire(int permits, long timeout, TimeUnit unit); 在给定的时间内获取permits个锁，如果获取不到则返回false;
 */
public class SemaphoreDemo {

    /** 可重入锁,对资源列表进行同步 */
    private final ReentrantLock lock = new ReentrantLock();
    /** 信号量 */
    private final Semaphore semaphore;

    /** 可使用的资源列表 */
    private final LinkedList<Object> resourceList = new LinkedList<Object>();

    public SemaphoreDemo(Collection<Object> resourceList) {
        this.resourceList.addAll(resourceList);
        this.semaphore = new Semaphore(resourceList.size(), true);
    }

    /**
     * 获取资源
     *
     * @return 可用的资源
     * @throws InterruptedException
     */
    public Object acquire() throws InterruptedException {
        semaphore.acquire();

        lock.lock();
        try {
            return resourceList.pollFirst();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 释放或者归还资源
     *
     * @param resource 待释放或归还的资源
     */
    public void release(Object resource) {
        lock.lock();
        try {
            resourceList.addLast(resource);
        } finally {
            lock.unlock();
        }

        semaphore.release();
    }

    public static void main(String[] args) {
        //准备2个可用资源
        List<Object> resourceList = new ArrayList<>();
        resourceList.add("Resource1");
        resourceList.add("Resource2");

        //准备工作任务
        final SemaphoreDemo demo = new SemaphoreDemo(resourceList);
        Runnable worker = new Runnable() {
            @Override
            public void run() {
                Object resource = null;
                try {
                    //获取资源
                    resource = demo.acquire();
                    System.out.println(Thread.currentThread().getName() + "\twork   on\t" + resource);
                    //用resource做工作
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "\tfinish on\t" + resource);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //归还资源
                    if (resource != null) {
                        demo.release(resource);
                    }
                }
            }
        };

        //启动9个任务
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 9; i++) {
            service.submit(worker);
        }
        service.shutdown();
    }
}