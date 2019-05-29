package Stack;

import java.awt.*;
import java.util.Scanner;

public class testThread {//线程安全的操作

    public static void main(String[] args) {
        linkStack s1 = null;
        label1:
        while (true) {
            System.out.println("------------请选择要进行的堆栈操作------------");
            System.out.println("1.初始化栈");
            System.out.println("2.入栈");
            System.out.println("3.出栈");
            System.out.println("4.打印栈");
            System.out.println("5.获取栈的长度");
            System.out.println("6.清空栈");
            System.out.println("7.查询栈");
            System.out.println("8.安全的多线程操作");
            System.out.println("9.不安全的多线程操作");
            System.out.println("--------------------------------------------");
            System.out.print("你要的操作是：");
            Scanner i = new Scanner(System.in);
            int i1 = i.nextInt();
            switch (i1) {
                case 1: {
                    s1 = new linkStack();
                    if (s1 != null)
                        System.out.println("初始化栈成功！");
                    else
                        System.out.println("初始化栈失败！");
                    System.out.println("任意键返回：");
                    Scanner a = new Scanner(System.in);
                    String a1 = a.next();
                    break;
                }
                case 2: {
                    if (s1 == null) {
                        System.out.println("请先初始化栈！");
                    } else {
                        System.out.println("输入你要加入的内容");
                        Scanner value = new Scanner(System.in);
                        String value1 = value.next();
                        s1.Push(new linkNode(0, value1));
                    }
                    System.out.println("任意键返回：");
                    Scanner a = new Scanner(System.in);
                    String a1 = a.next();
                    break;
                }
                case 3: {
                    if (s1 == null) {
                        System.out.println("请先初始化栈");
                    } else {
                        s1.Pop();
                    }
                    System.out.println("任意键返回：");
                    Scanner a = new Scanner(System.in);
                    String a1 = a.next();
                    break;
                }
                case 4: {
                    if (s1 == null) {
                        System.out.println("请先初始化栈");
                    } else {
                        s1.Print();
                    }
                    System.out.println("任意键返回：");
                    Scanner a = new Scanner(System.in);
                    String a1 = a.next();
                    break;
                }
                case 5: {
                    if (s1 == null) {
                        System.out.println("请先初始化栈");
                    } else {
                        System.out.println("栈的长度为：" + s1.Length());
                    }
                    System.out.println("任意键返回：");
                    Scanner a = new Scanner(System.in);
                    String a1 = a.next();
                    break;
                }
                case 6: {
                    if (s1 == null) {
                        System.out.println("请先初始化栈");
                    } else {
                        s1.Clear();
                    }
                    System.out.println("任意键返回：");
                    Scanner a = new Scanner(System.in);
                    String a1 = a.next();
                    break;
                }
                case 7: {
                    if (s1 == null) {
                        System.out.println("请先初始化栈");
                    } else {
                        System.out.println("输入查找的value：");
                        Scanner a = new Scanner(System.in);
                        String value = a.next();
                        s1.Find(value);
                    }
                    System.out.println("任意键返回：");
                    Scanner a = new Scanner(System.in);
                    String a1 = a.next();
                    break;
                }
                case 8: {
                    //使用自己实现的栈进行初始化
                    final linkStack s2 = new linkStack();//初始化栈栈中数量为1
                    //创建多个线程同时压栈，
                    int threadNum =2000;
                    long start = System.nanoTime();//取当前时间作为开始时间
                    new currentThread(threadNum,s2);
                    long end = System.nanoTime();
                    //打印所有线程的入栈时间
                    System.out.println("总时间为: " + (end - start) / 1.0e9 + "秒");
                    System.out.println("栈中数量为" + s2.Length());
//                    System.out.println("pop ------------------------------------");
//                    for (int j = 0; ; j++) {
//                        linkNode l = s.Pop();
//                        if (l == null)
//                            break;
//                    }
                    System.out.println("任意键返回：");
                    Scanner a = new Scanner(System.in);
                    String a1 = a.next();
                    break;
                }
                case 9: {
                    linkStack s = new linkStack();//初始化栈栈中数量为1
                    //创建多个线程同时压栈，
                    int threadNum = 10000;
                    for (int i2 = 0; i2 < threadNum; i2++) {
                        new Thread(new Runnable() {//同时生成1k个线程
                            @Override
                            public void run() {
                                linkNode node = new linkNode(0, "name");
                                s.Push(node);
                            }
                        }).start();
                    }
                    System.out.println("pop ------------------------------------");
                    System.out.println(s.Length());
                    s.Clear();
                    System.out.println("任意键返回：");
                    Scanner a = new Scanner(System.in);
                    String a1 = a.next();
                    break;
                }
            }
        }
    }
}


