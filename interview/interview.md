# 日记

2021.7.30  

本想下班回去面的，结果只能在B幢13楼的会议室面，还是失败了，虽然早有准备，但是果然人的内心

还是期待奇迹的，百分制我给自己打30分，想要通过需要得到80乃至90分

问到的问题是 springboot， 介绍项目，这次还是说的e融，下次还是把现在的架构讲清楚，毕竟做了快1年了

大厂果然还是看重底层和算法的

springboot的原理，启动原理还是我自己说的，所以这道题还是别抛出去把，或者背背

springboot的starter流程是怎么样的

redis的rp是多少，为什么这么快，没有IO吗

mysql的数据是怎么到应用程序中的，explain 会输出哪些，具体代码什么意思，有哪些索引

mysql的行级锁有用过吗，mybatis的原理，

设计一个秒杀系统， 讲讲快排的思路

看到奥运冠军孙一文的话，想变强只能让自己痛苦点，看来我也没办法，只能继续痛苦下去了



2021.9.4

下午去宾馆开房面试，结果电脑没搞好，错过了笔试题，面试题也没答好

项目经理，单机上无法让人满意的，问了微服务，分布式， 分布式锁有用过吗，用了哪些方法

我答了 用数据库 建了张表 问 这个数据库有并发问题吗， 我答没有 看来是错了

问 哪些技术熟 问了 mysql的 ，mysql的索引用的什么，b+ ，有哪些优点，默认隔离级别上哪个，mysql的事物是怎么实现的

问了 redis  的基本数据类型，redis的string为什么怎么快，

java 的双亲委派机制， java的 类加载器有哪些







# 面经

mysql的行级锁

​	使用行级锁定的主要有InnoDB存储引擎，以及MySQL的分布式存储引擎NDBCluste

​	MYISAM引擎只支持表级锁，而INNODB引擎能够支持行级锁，

mysql的数据是怎么流转的

https://blog.csdn.net/qq_38571987/article/details/118606451?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-0.control&spm=1001.2101.3001.4242



# 代码题

## 题目1

1. 写个代码吧，两个线程交替打印数字，从1到20，线程一打印1,3,5,7... 线程二打印2,4,6,8... 输出结果是1 2 3 4 5 6 ... 20。(当时选择用 ReentrantLock 搭配 Condition 进行实现)

    第一想到的是 连个线程同时打印，用线程名区分1还是2，但是不能保证顺序

    所以还是用两个方法简单

  自己调式出的笨方法一  通过调式得出的结论是，每次noti后，会从wait的方法重新执行



```java
public class Test {

    public static void main(String[] args) {
        final printAB print = new printAB();
        new Thread(new Runnable() {
            public void run() {
                print.pirntA();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                print.pirntB();
            }
        }).start();
    }
}

class printAB {
    private boolean flag = true;
    private int k = 1;

    public synchronized void pirntA() {
        while (k < 20) {
            if (!flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (k % 2 != 0) {
                System.out.println(k);
                k++;
                flag = false;
                this.notify();
            }
        }
    }

    public synchronized void pirntB() {
        while (k < 20) {
            if (flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (k % 2 == 0) {
                System.out.println(k);
                k++;
                flag = true;
                this.notify();
            }
        }

    }

}
```