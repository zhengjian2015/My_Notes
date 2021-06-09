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

