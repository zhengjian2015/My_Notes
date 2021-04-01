datastruct learning note

# 1.数组

数组最大的优势：快速查询   scores[2]

数组最好应用于 “索引有语意”的情况  

动态数组第一版本

```java
/**
 * 构造动态数组
 * 1.静态数组
 * 2.大小
 * 3.构造器 里面是容量
 */
public class Array {
    private int[] data;
    private int size;

    /**
     * 构造函数 传入数组的容量capacity
     * @param capacity
     */
    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }
    public Array() {
        this(10);
    }

    /**
     * 获取元素个数
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容量
     * @return
     */
    public int getCapacity() {
        return data.length;
    }
    /**
     * 获取数组是否为空
     * @return
     */
    public boolean isEmpty() {
        return 0 == size;
    }
    
     /**
     * 重写是自己写的，总算有进步
     * @return
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0;i<data.length;i++) {
            sb.append(data[i]);
            if(i != data.length-1)
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
```



```java
/**
 * 在数组index位置中插入元素 e
 * @param index
 * @param e
 */

public void add(int index,int e) {
    if(size == data.length) {
        throw new IllegalArgumentException("AddLast failed.Array is full");
    }
    int length = data.length - index;
    int index2 = index;
    int[] copy = new int[length];
    for(int i=0;i<length;i++) {
        copy[i] = data[index2];
        index2++;
    }
    data[index] = e;
    int g =0;
    for(int i=index;i<length;i++) {
        data[i] = copy[g];
        g++;
    }
    size++;
}

//看了视频后自己写的版本 思路是所有index位置的向后加1，但是我想着把数组拷贝一份
//逻辑混乱最后结果还是错的
```



```java
/**
 * 在数组index位置中插入元素 e
 * @param index
 * @param e
 */
public void add(int index,int e) {
    if(size == data.length) {
        throw new IllegalArgumentException("Add failed.Array is full");
    }
    if(index<0 || index > size) {
        throw new IllegalArgumentException("Add failed.Required index >=0");
    }
    for(int i=size-1; i>=index;i--) {
        data[i+1] = data[i];
    }
    data[index] = e;
    size++;
}
//正确的版本  应该从后往前遍历

同时 addLast  就是 add(size,e)  不用再写一遍
```

```java
/**
 * 在数组index位置中删除元素
 * @param index
 * @param e
 */
public void delete(int index) {
    if(size == data.length) {
        throw new IllegalArgumentException("delete failed.Array is full");
    }
    if(index<0 || index > size) {
        throw new IllegalArgumentException("delete failed.Required index >=0");
    }
    for(int i=index+1;i<size;i++) {
        data[i-1] = data[i];
    }
    data[size] = 0;  //如果不加这句data[size] = null  loitering object != memory leak 
    				 //不加这句也没事，就是闲逛对象
    size--;
}
//没看，调了半天，为啥会把i+1 写成i++呢，怪事
```

把数组改造成泛型 要注意

 data = (E[])new Object[capacity];

同时 应该吧 ==  都改成 equals 因为泛型不能是基本数据类型

而是类对象或者包装类，包装类相等要用 equals

```java
/**
 * 修改add方法，当size = 容量时 可以扩容
 */
public void add(int index,E e) {
        if(index<0 || index > size) {
            throw new IllegalArgumentException("Add failed.Required index >=0");
        }
        if(size == data.length) {
            resize(2*data.length);
        }
        for(int i=size-1; i>=index;i--) {
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

/**
 * 扩容
 * @param newCapacity
 */
private void resize(int newCapacity) {
    E[] newData = (E[]) new Object[newCapacity];
    for(int i =0;i<data.length;i++) {
        newData[i] = data[i];
    }
    data = newData;
}
```



时间复杂度分析： 忽略常熟，忽略低阶，考虑最坏情况， 大概就是这样，严谨的需要数学归纳

addLast的 均摊复杂度是 O(1) , 因为最坏情况不是每次都发生



# 2.栈

编辑器的 undo操作 就是 栈的应用  撤销 先进后出

程序调用的系统栈   ，先执行A函数，A函数里执行B函数   

或者递归  都是栈的应用

leetcode 21题目

```java
/**
 * 这题没做出来，原因是思维上以为要一次性把char都压进栈里
 * 而是把左半边压进去，右半边出栈
 * 最后看清题目，把几种情况都看清楚
 *
 */
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if (a == '(' || a == '{' || a == '[') {
                stack.push(a);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char p = stack.pop();
                if (a == '}' && p != '{') {
                    return false;
                }
                if (a == ')' && p != '(') {
                    return false;
                }
                if (a == ']' && p != '[') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
```



# 3.队列

循环队列

front 和 tail

front == tail 队列为空

(tail+1)%c == front    队列满





# 4.链表

动态数组，栈，队列，都是线性结构，而且底层都是静态数组，靠扩容实现的

而链表是真正的动态数据结构，也是最简单的动态数据结构

学习链表的好处

更好的理解引用（指针）

更好的理解递归

辅助其他数据结构

class Node {

​	E e;

​	Node next;

}

真正的动态，不需要处理固定容量问题

缺点：丧失了随机访问的能力



```java
public class LinkedList<E> {
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
}
```

链表 在头部添加节点

先new一个把头部的next指向 head

head 再指向头部 

在index位置添加元素

关键是弄一个pre节点，pre遍历到要插入的前一个，new指向pre的next,pre的next再指向new

把bobo老师的动画刻进脑海里





```java
private Node head;
private int size;

//这点有点迷惑，当没有虚拟头节点时，head是空
public LinkedList() {
    this.head = null;
    this.size = 0;
}

public int getSize() {
    return size;
}

public boolean isEmpty() {
    return size == 0;
}

//在头节点添加
public void addFirst(E e) {
    Node node = new Node(e);
    node.next = head;
    head = node;
    //上面上句等价 head = new Node(e,head);
    size++;
}

//在index添加节点
//链表不常用
public void add(int index, E e) {
    if (index < 0 || index > size) {
        throw new IllegalArgumentException("添加错误");
    }
    if (index == 0) {
        addFirst(e);
    } else {
        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;
        size++;
    }
}

//一个也没插入时，直接输出会报错，插入后，输出链表就不会报错了
@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    Node prev = head;
    while (prev.next != null) {
        sb.append(prev.e + "->");
        prev = prev.next;
    }
    sb.append("NULL");
    return sb.toString();
}
```





//下面这个是虚拟头节点的版本

//记住循环一遍只是为了找到要操作节点的位置，或者说前一位置

```java
private Node dummyHead;
private int size;

public LinkedList() {
    this.dummyHead = new Node(null, null);
    this.size = 0;
}

public int getSize() {
    return size;
}

public boolean isEmpty() {
    return size == 0;
}

//在头节点添加
public void addFirst(E e) {
   add(0,e);
}

//在index添加节点
//链表不常用
public void add(int index, E e) {
    if (index < 0 || index > size) {
        throw new IllegalArgumentException("添加错误");
    }

    Node prev = dummyHead;
    for (int i = 0; i < index ; i++) {
        prev = prev.next;
    }
    Node node = new Node(e);
    node.next = prev.next;
    prev.next = node;
    size++;

}

//获得当前节点的值
//在链表中不是一个常用的操作
public E get(int index) {
    if (index < 0 || index > size) {
        throw new IllegalArgumentException("get错误");
    }
    //获取时要从实际有的值出发寻找
    Node cur = dummyHead.next;
    for(int i=0;i<index;i++) {
        cur = cur.next;
    }
    return cur.e;
}

@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    Node cur = dummyHead.next;
    for(int i=0;i<size;i++) {
        sb.append(cur.e + "->");
        cur = cur.next;
    }
    /*while (cur != null) {
        sb.append(cur.e + "->");
        cur = cur.next;
    }*/
    sb.append("NULL");
    return sb.toString();
}
```



```java
//删除节点
 /**
     * 删除节点常见的错误 cur = cur.next;
     * 主要原因还是对引用概念糊涂，除了基本数据类型外，其他类型的=都是指向
     * @param index
     */
public E delete(int index) {
    if (index < 0 || index > size) {
        throw new IllegalArgumentException("删除错误");
    }
    Node prev = dummyHead;
    for(int i=0;i<index;i++) {
        prev = prev.next;
    }
    Node delete = prev.next;
    prev.next = delete.next;
    delete.next = null;
    size--;
    return delete.e;
}

 public E deleteFirst() {
        return delete(0);
    }
```

增，删，查，当头节点时 时间复杂度是o(1) ，想到了栈

所以用链表实现栈

再实现链表的队列



刷题leetcode 203



```java
public ListNode removeElements(ListNode head, int val) {
    while(head != null && head.val == val) {
        ListNode delNode = head;
        head = head.next;
        delNode.next = null;
    }
    
    if(head == null) {
        return head;
    }

    ListNode prev = head;
    while (prev.next != null ) {
        if(prev.next.val == val) {
            ListNode delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
        } else
            prev = prev.next;
    }
    return head;
}

//第2种方式是虚拟头节点
```

StringBuilder 

String 底层+ 是new StringBuilder,但是+断开，就是用分号后

会再new 一个，所以大量的时候用StringBuilder效率高





```java
	/*
     * Leetcode的测试用例，可以测试链表
     */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("can not be empty");
        //难理解，为啥cur = this
        this.val = arr[0];
        ListNode cur = this;
        //从1开始输入
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
```