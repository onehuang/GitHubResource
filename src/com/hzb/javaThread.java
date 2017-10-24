package com.hzb;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zibeen on 2017/10/6.
 */
public class javaThread  {
    public  static  void main(String[] args) {
//        ThreadCallable tc = new ThreadCallable();
//        FutureTask ft = new FutureTask(tc);
//        new Thread(ft, "子线程计算：").start();
//        try {
//            while (!ft.isDone()){
//                System.out.println("子线程正在计算，等待...");
//                Thread.sleep(1000);
//            }
//
//            System.out.println("子线程计算完毕，计算结果："+":"+ ft.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

//        ThreadRunable tr = new ThreadRunable();
//        for(int i = 0; i < 10; i ++){
//            new Thread(tr, "子线程:"+i).start();
//        }
//        ThreadCountDownLatch.run();
//        ThreadCyclicBarrier.run();
//        ThreadSemaphore.run();
//        SellTicket.run();
//        ThreadDataExchange.run();
//        ThreadProductAndConsumer.run();
        ThreadVolatile.run();
    }

}

class ThreadCallable implements Callable<Integer>{
    private  Integer it;
    public ThreadCallable(){
       System.out.println("ThreadCallable构造函数");
    }
    @Override
    public Integer call() throws Exception {

        System.out.println(Thread.currentThread().getName() );
        int num = new Random().nextInt(100);
        System.out.println("随机数：" + num);
        for(int i = 0; i < 100; i++){
            if( i == num){
                System.out.println(Thread.currentThread().getName() + ":" + i);
                return  i;
            }else{
                Thread.sleep(100);
            }
        }
        return -1;
    }
}

class ThreadRunable implements Runnable{
    private  int i = 0;
    @Override
    public void run() {
        while (true){
            i++;
            System.out.println(Thread.currentThread().getName()+":"+ i);
            if(i == 10) return;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

/**
 * countdownLatch的使用
 *
 * 线程A 等待其他所有线程结束才继续某项工作
 */
class ThreadCountDownLatch{
    public static void run(){
        final CountDownLatch cd = new CountDownLatch(4);
        Thread.currentThread().setName("线程：A ");
        for(int i=0; i< 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "正在执行任务...");
                        Thread.sleep(1000);
                        cd.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "任务完成！");
                }
            }, "线程:" + i +" ").start();
        }

        System.out.println(Thread.currentThread().getName()+"正在等其他线程...");
        try {
            cd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"完成");
    }
}

/**
 * cyclicBarrier的使用
 * 等待一组进程完成，所有进程去做后面的工作。
 */
class ThreadCyclicBarrier{
    public static final int N = 4;
    public static void run(){
        Thread.currentThread().setName("线程：A ");
        final CyclicBarrier cb = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"所有的线程都完成了，可以继续其他工作了");
            }
        });
        for(int i = 0 ; i< N - 1; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        System.out.println(Thread.currentThread().getName()+"正在做前期工作...");
                        cb.await(200, TimeUnit.MILLISECONDS);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"工作完成, 所有线程都结束");
                }
            }, "线程：" + i +" ").start();
        }
        try {
            Thread.sleep(1000);
            cb.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"工作完成, 所有线程都结束");
    }
}

/**
 * Semaphore的使用
 * 控制同时访问资源的线程数量
 * 例子：
 * 8个工人操作5台机器，一台机器同时只能被一个工人使用。
 */
class ThreadSemaphore{
    public static final int N = 4;  //工人数量；
    public static final int M = 7;  //机器的数量；
    public static void run(){
        final Semaphore ah = new Semaphore(M);

        for(int i = 0; i < N; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ah.acquire(2);
                        System.out.println(Thread.currentThread().getName()+"正在使用机器...");
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+"使用机器完成");
                        ah.release(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            },  i +"号工人").start();
        }
        while (ah.availablePermits() < M){
            System.out.println("当前剩余机器数量: " + ah.availablePermits());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SellTicket {

    static class Ticket implements Runnable{

        private int ticket = 10;
        public void run() {

            synchronized(Ticket.class){
                while(ticket>0){
                    ticket--;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println( Thread.currentThread().getName() +"当前票数为："+ticket);
                }
            }

        }

    }

    public static void run() {
        Ticket t = new Ticket();
        new Thread(t, "线程1: ").start();
        new Thread(t, "线程2: ").start();
    }

}

/**
 * 线程间数据交互
 * 4个线程，2个线程对i加1,2个线程对i减1
 * 不同的操作要在不同线程中进行
 */
class ThreadDataExchange{
    private static final int num = 4;
    private static final Data dt = new Data();

    public static void run(){
        for(int i=0; i < num / 2; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dt.add();
                }
            }, "线程：" + i).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dt.minus();
                }
            }, "线程：" +  ( i +num / 2) ).start();
        }
    }
    static class Data{
        private int i = 0;

        /**
         * 对i增加1
         */
        public synchronized void add(){
            i++;
            System.out.println(Thread.currentThread().getName()+" 对i增加 1： " + i);
        }

        /**
         * 对i减1
         */
        public synchronized void minus(){
            i--;
            System.out.println(Thread.currentThread().getName()+" 对i减少 1: " + i);
        }
    }

}

/**
 * 生产和消费者
 */
class ThreadProductAndConsumer{

    private static final int ProductorNum = 10;
    private final static int ConsumerNum = 10;
    public static void run(){
        Data data = new Data();
        for(int i = 0; i < ProductorNum; i ++){
            new Thread( new producter(data), "生产者 " + i + " 号").start();
        }

        for(int i = 0; i < ConsumerNum; i ++){
            new Thread( new consumer(data), "消费者 " + i + " 号").start();
        }
    }

    static class Data{

        private final int MixNum = 100;
        public int Num = 0;

        Data(){
            System.out.println("初始化了仓库...");
        }

        public synchronized void product(){
            Num ++;
            System.out.println(Thread.currentThread().getName()+" 生产者生产了一个产品, 总共："+ Num);
            notify();
        }

        public synchronized void consume(){
            Num --;
            System.out.println(Thread.currentThread().getName()+" 消费者消费了一个产品, 总共："+ Num);
            notify();
        }

        public synchronized boolean isFull(){
            if (Num >= MixNum){
                try {
                    System.out.println(Thread.currentThread().getName()+" 仓库已满，等待消费者消费 ");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return Num >= MixNum;
        }

        public synchronized boolean isEmpty(){

            if (Num <= 0){
                try {
                    System.out.println(Thread.currentThread().getName()+" 仓库已空，等待生产者生产 ");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return Num <= 0;
        }
    }

    /**
     * 生产者
     */
    static class producter implements Runnable{
        private Data data;
        public producter(Data data){
            this.data = data;
        }
        @Override
        public void run() {
            while (true){
                if( data.isFull() ){
                    continue;
                }
                data.product();

                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 消费者
     */
    static class consumer implements Runnable{
        private Data data;
        public consumer(Data data){
            this.data = data;
        }
        @Override
        public void run() {
            while (true){
                if(data.isEmpty()){
                    continue;
                }
                data.consume();

                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}

class ThreadVolatile{
    public static volatile int n =0;


    public static void run(){
         AtomicInteger ai = new AtomicInteger(0);
        for(int i = 0; i < 100; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    n ++;
                    ai.getAndIncrement();
//                    System.out.println(Thread.currentThread().getName()+" volatile n = " + n);
                    System.out.println(Thread.currentThread().getName()+" ai = " + ai.get());
                }
            }, "线程:" + i).start();
        }



    }

}

