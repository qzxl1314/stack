package Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class currentThread {
    private CountDownLatch start=new CountDownLatch(1);//开启阀门
    private CountDownLatch end;//主线程阻塞阀门
    private CountDownLatch push;
    private CountDownLatch length1;
    private CountDownLatch pop;
    private CountDownLatch find;
    private CopyOnWriteArrayList<Double> list=new CopyOnWriteArrayList<>();//初始化一个并发容器写入时会复制一个新的容器写入
    private AtomicInteger a = new AtomicInteger();//原子递增
    private AtomicInteger b = new AtomicInteger();//原子递增
    public currentThread(int task,linkStack s) {
        if (task == 0) {
            System.out.println("task can not 0");
            System.exit(1);//退出程序
        }
        end = new CountDownLatch(task);//将关闭阀门的长度设置为线程数量
        push=new CountDownLatch(1000);
        length1=new CountDownLatch(1);
        pop=new CountDownLatch(499);
        find=new CountDownLatch(500);
        long length = end.getCount();
        for (int i = 0; i < length; i++) {
            if (i < 1000) {
                new Thread(new Runnable() {//同时生成1k个线程
                    @Override
                    public void run() {
                        try {
                            start.await();//若为0提取否则阻塞子线程直到startSignal为0时再运行
                            long start = System.nanoTime();//返回纳米级开始时间
                            linkNode node = new linkNode(0, "name");
                            s.Push(node);//进行操作
                            long end = System.nanoTime();////返回纳米级结束时间
                            double time = (end - start) / 1.0e5;//计算运行时间
                            list.add(time);//加入到列表中
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        end.countDown();//线程完成后结束阀门-1
                        push.countDown();
                    }
                }).start();
            }
            if (i==1000){
                new Thread(new Runnable() {//同时生成1k个线程
                    @Override
                    public void run() {
                        try {
                            start.await();//若为0提取否则阻塞子线程直到startSignal为0时再运行
                            push.await();
                            long start = System.nanoTime();//返回纳米级开始时间
                            System.out.println("目前栈的长度为"+s.Length());
                            long end = System.nanoTime();////返回纳米级结束时间
                            double time = (end - start) / 1.0e5;//计算运行时间
                            list.add(time);//加入到列表中
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        end.countDown();//线程完成后结束阀门-1
                        length1.countDown();
                    }
                }).start();
            }
            if (i>1000&&i<=1499){
                new Thread(new Runnable() {//同时生成1k个线程
                    @Override
                    public void run() {
                        try {
                            start.await();//若为0提取否则阻塞子线程直到startSignal为0时再运行
                            length1.await();
                            push.await();
                            long start = System.nanoTime();//返回纳米级开始时间
                            s.Pop();
                            a.getAndIncrement();
                            long end = System.nanoTime();////返回纳米级结束时间
                            double time = (end - start) / 1.0e5;//计算运行时间
                            list.add(time);//加入到列表中
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                        end.countDown();//线程完成后结束阀门-1
                        pop.countDown();
                    }
                }).start();
            }
            if (i>=1500){
                new Thread(new Runnable() {//同时生成1k个线程
                    @Override
                    public void run() {
                        try {
                            start.await();//若为0提取否则阻塞子线程直到startSignal为0时再运行
                            push.await();
                            length1.await();
                            pop.await();
                            long start = System.nanoTime();//返回纳米级开始时间
                            s.Find("name");
                            b.getAndIncrement();
                            long end = System.nanoTime();////返回纳米级结束时间
                            double time = (end - start) / 1.0e5;//计算运行时间
                            list.add(time);//加入到列表中
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        end.countDown();//线程完成后结束阀门-1
                        find.countDown();
                    }
                }).start();
            }
        }
        start.countDown();//所有线程初始化完成后开启阀门，之后每一个线程运行完毕，释放资源，阀门变为0
        try {
            end.await();//使主线程阻塞直到doneSignal从10000减到0再继续运行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int size=list.size();
        List<Double> list1=new ArrayList<>(size);
        list1.addAll(list);
        Collections.sort(list1);//对list1排序
        double min=list1.get(0);//取最小值
        double max = list1.get(size-1);//取最小值
        double sum = 0;
        for (Double t : list1) {
            sum += t;
        }
        Double avg=sum/size;
        System.out.println("最小时间为: " + min+"纳秒");
        System.out.println("最大时间为: " + max+"纳秒");
        System.out.println("平均时间: " + avg+"纳秒");
        System.out.println("出栈函数运行了: " +a+"次");
        System.out.println("Find函数运行了"+b+"次");
    }
}
