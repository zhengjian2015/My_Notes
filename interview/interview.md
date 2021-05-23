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