# 最大堆

## 定义

​	完全二叉树，每排节点是满的，且父节点大于等于子节点

![image-20210609025908205](image-20210609025908205.png)



可以用数组来表示 索引 公式如图所示

 完全二叉树可以用数组来表示

import 写好的 Array

```java
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    //返回堆中元素个数
    public int size() {
        return data.getSize();
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    public boolean isEmpty() {
        return data.isEmpty();
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't hava parent.");
        }
        return (index - 1) / 2;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }


}
```

在Array中添加swap方法

```java
    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("index is error");
        }
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
```

## 添加和取出最大元素

```java
    //向堆中添加元素，sift up
    //添加的元素要和父亲节点比较，堆的性质是父亲节点大于等于子节点
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {
        //k所在元素和父亲元素做比较
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    //看堆中的最大元素
    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("heap is empty");
        }
        return data.get(0);
    }

    //取出堆中的最大元素
    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

	private void siftDown(int k){

        while(leftChild(k) < data.getSize()){
            int j = leftChild(k); // 在此轮循环中,data[k]和data[j]交换位置
            if( j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0 )
                j ++;
            // data[j] 是 leftChild 和 rightChild 中的最大值

            if(data.get(k).compareTo(data.get(j)) >= 0 )
                break;

            data.swap(k, j);
            k = j;
        }
    }
```



## 单元测试

```java
public class Main {

    public static void main(String[] args) {

        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++)
            arr[i] = maxHeap.extractMax();

        for(int i = 1 ; i < n ; i ++)
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("Error");

        System.out.println("Test MaxHeap completed.");
    }
}

```

时间复杂度分析， add 和 extractMax 都是 O(logN) 都是树的高度，因为是完全二叉树，所以永远不会退化成链表



## replace和heapify



```java
//取出堆中的最大元素，并且替换成元素e
public E replace(E e) {
    E ret = findMax();
    data.set(0, e);
    siftDown(0);
    return ret;
}
```

heapify：把任意数组转为最大堆

要从非叶子节点开始，一个个siftdown操作

Array中新增构造方法

```java
public Array(E[] arr){
    data = (E[])new Object[arr.length];
    for(int i = 0 ; i < arr.length ; i ++)
        data[i] = arr[i];
    size = arr.length;
}
```

```java
//heapify
public MaxHeap(E[] arr){
    data = new Array<>(arr);
    if(arr.length != 1){
        for(int i = parent(arr.length - 1) ; i >= 0 ; i --)
            siftDown(i);
    }
}
```

![image-20210609221033624](image-20210609221033624.png)

heapify的算法复杂度是O(n)      add则是O(NlogN)



## add和 heapfify的比较

```java
public class Main {

    private static double testHeap(Integer[] testData, boolean isHeapify){

        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if(isHeapify)
            maxHeap = new MaxHeap<>(testData);
        else{
            maxHeap = new MaxHeap<>(testData.length);
            for(int num: testData)
                maxHeap.add(num);
        }

        int[] arr = new int[testData.length];
        for(int i = 0 ; i < testData.length ; i ++)
            arr[i] = maxHeap.extractMax();

        for(int i = 1 ; i < testData.length ; i ++)
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("Error");
        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int n = 1000000;

        Random random = new Random();
        Integer[] testData1 = new Integer[n];
        for(int i = 0 ; i < n ; i ++)
            testData1[i] = random.nextInt(Integer.MAX_VALUE);

        Integer[] testData2 = Arrays.copyOf(testData1, n);

        double time1 = testHeap(testData1, false);
        System.out.println("Without heapify: " + time1 + " s");

        double time2 = testHeap(testData2, true);
        System.out.println("With heapify: " + time2 + " s");
    }
}
```

# 优先队列

定义：不是先进先出，而是最大或最小的先出

基于最大堆的队列

```java
public interface Queue<E>  {
    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
```

```java

public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }
}

```

## 经典问题

求10000000 元素中的前100 位

相当于 N个元素中选出前M个

第一想法是排序，快排能做到NlogN,但是 用优先队列，能做到NlogM

如leetcode 347 

java自带的PriorityQueue 主要实现了最小堆

## 解题

```java
import java.util.*;
import java.util.PriorityQueue;

public class Soulution {


    private static class Freq implements Comparable<Freq> {
        public int key, freq;

        public Freq(int key, int freq) {
            this.key = key;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq)
                return 1;
            else if (this.freq > another.freq)
                return -1;
            else
                return 0;
        }
    }

    public static void main(String[] args) {
        int k = 2;
        int[] nums = {4,1,-1,2,-1,2,3};
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        PriorityQueue<Freq> pq = new PriorityQueue<>();
        for (int key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(new Freq(key, map.get(key)));
            } else if (pq.peek().freq < map.get(key)) {
                pq.remove();
                pq.add(new Freq(key, map.get(key)));
            }
        }

        int m = 0;
        int[] res = new int[k];
        while (!pq.isEmpty()) {
            res[m] = pq.remove().key;
            m++;
        }

    }

}

```



后面主要探讨了比较器的写法

```java
import java.util.*;
import java.util.PriorityQueue;

public class Soulution {


    private static class Freq  {
        public int key, freq;

        public Freq(int key, int freq) {
            this.key = key;
            this.freq = freq;
        }
    }

    private static class FreqComparator implements Comparator<Freq> {

        @Override
        public int compare(Freq o1, Freq o2) {
            return o1.freq - o2.freq;
        }
    }

    public static void main(String[] args) {
        int k = 2;
        int[] nums = {4,1,-1,2,-1,2,3};
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        System.out.println(map);
        PriorityQueue<Freq> pq = new PriorityQueue<>(new FreqComparator());
        for (int key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(new Freq(key, map.get(key)));
            } else if (pq.peek().freq < map.get(key)) {
                pq.remove();
                pq.add(new Freq(key, map.get(key)));
            }
        }

        int m = 0;
        int[] res = new int[k];
        while (!pq.isEmpty()) {
            res[m] = pq.remove().key;
            m++;
        }

        System.out.println("0------------");
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

    }

}
```



```java
import java.util.*;
import java.util.PriorityQueue;

public class Soulution {

    public static void main(String[] args) {
        int k = 2;
        int[] nums = {4, 1, -1, 2, -1, 2, 3};
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        System.out.println(map);
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });
        //PriorityQueue<Integer> pq = new PriorityQueue<>			(Comparator.comparingInt(map::get));
        for (int key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(pq.peek()) < map.get(key)) {
                pq.remove();
                pq.add(key);
            }
        }

        List<Integer> integers = new ArrayList<>();
        while (!pq.isEmpty()) {
            integers.add(pq.remove());
        }
        System.out.println(integers);
    }

}
```



# 线段树

![Q12](Q12.png)

![image-20210613210236921](image-20210613210236921.png)

完全二叉树就是平衡二叉树

最低节点和最深的差值不超过1 

**线段树主要用于区间的查询统计**

## 创建

基础： 

```java
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;

    public SegmentTree(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        //用4倍的空间就够了，不考虑新增是静态的
        tree = (E[]) new Object[4 * arr.length];
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return data[index];
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }
}
```

完整：

```java
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    //组合优于继承，这个融合设计好像是策略模式
    public SegmentTree(E[] arr, Merger<E> merger) {

        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    //在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        //不用(r+l)/2是防止整形溢出
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        //结合业务逻辑，这里是左右还是的和
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return data[index];
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if (i != tree.length - 1)
                res.append(", ");
        }
        res.append("]");
        return res.toString();
    }
}
```

```java
public interface Merger<E> {

    E merge(E a,E b);
}
```

测试：

```java
public class Main {
    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};
//        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
//                new Merger<Integer>() {
//                    @Override
//                    public Integer merge(Integer a, Integer b) {
//                        return a + b;
//                    }
//                });

        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
                (a, b) -> a + b);
        System.out.println(segTree);
    }
}
```

##  查询

```java
//返回区间[queryL,queryR]的值
public E query(int queryL, int queryR) {
    if (queryL < 0 || queryL >= data.length ||
            queryR < 0 || queryR >= data.length || queryL > queryR)
        throw new IllegalArgumentException("Index is illegal");
    return query(0, 0, data.length - 1, queryL, queryR);
}

private E query(int treeIndex, int l, int r, int queryL, int queryR) {
    if (l == queryL && r == queryR) {
        return tree[treeIndex];
    }
    int mid = l + (r - l) / 2;
    int leftTreeIndex = leftChild(treeIndex);
    int rightTreeIndex = rightChild(treeIndex);
    if (queryL >= mid + 1)
        return query(rightTreeIndex, mid + 1, r, queryL, queryR);
    else if (queryR <= mid)
        return query(leftTreeIndex, l, mid, queryL, queryR);
    E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
    E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
    return merger.merge(leftResult,rightResult);
}
```

测试：

```java

public class Test {
    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};
//        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
//                new Merger<Integer>() {
//                    @Override
//                    public Integer merge(Integer a, Integer b) {
//                        return a + b;
//                    }
//                });

        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
                (a, b) -> a + b);
        System.out.println(segTree);

        System.out.println(segTree.query(0,3));
    }
}
```



**LeetCode 303 和 309**