package tk.shuai93.snippet;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author 杨帅
 * @Date 2021/11/2 下午9:22
 * @Version 1.0
 */
public class ThreadDemo {
    public static void main(String[] args) {

        // 多线程的案例
        Thread t1 = new ExtendThread();
        t1.start();
        Thread t2 = new Thread(new ImpRunnable());
        t2.start();

    }
}




class ExtendThread extends Thread {
    @Override
    public void run(){
        System.out.println("Extends Thread success");
    }
}

class ImpRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Implements Runnable success");
    }
}

class ImpCallable implements Callable<String> {

    private static final int CORE_POOL_SIZE=2;
    private static final int MAX_POOL_SIZE=4;
    private static final int KEEP_ALIVE_TIME=1;
    private static final int QUEUE_CAPACITY=5;
    private static final TimeUnit UNIT = TimeUnit.SECONDS;
    private static final String THREAD_NAME = "my-self-thread-pool-%d";

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                UNIT,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new BasicThreadFactory.Builder().namingPattern(THREAD_NAME).build(),
                new ThreadPoolExecutor.CallerRunsPolicy());


        ImpCallable task = new ImpCallable();
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // 提交任务到线程池
            Future<String> future = executor.submit(task);
            // 任务结果 future 加入结果队列
            futureList.add(future);
        }

        for (Future<String> fut : futureList) {
            try {
                // 取出结果
                System.out.println(new Date() + "--" + fut.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }


        executor.shutdown();

        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All threads Finished");
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(2000L);
        return Thread.currentThread().getName();
    }
}

class PrintNumber {

    //输出的最大数字
    private static final int MAX_NUM = 100;
    //自增变量
    private static volatile int i = 1;
    //临界资源
    private static final Object object = new Object();

    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    while (i <= MAX_NUM) {
                        System.out.println(Thread.currentThread().getName() + ":" + i++);
                        try {
                            object.notify();
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //防止有子线程被阻塞未被唤醒，导致主线程不退出
                    object.notify();
                }
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }
}