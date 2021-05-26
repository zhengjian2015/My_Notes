树结构

使用树结构存储数据，出奇的高效

二分搜索树

平衡二叉树： AVL,红黑树

堆，并查集

线段树，Tire(字典树，前缀树木)



# 二分搜索树

```java
//二分搜索树元素必须是可比较的
public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left,right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;
    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        /*if(root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root,e);
        }*/
        root = add(root,e);
    }

    //向以Node为根的二分搜索树中插入元素E，递归算法
    /*private void add(Node node,E e) {
        if(e.equals(node.e)) {
            return;
        } else if(e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        } else if(e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }

        if(e.compareTo(node.e) < 0) {
            add(node.left,e);
        } else { //e.compareTo(node.e) > 0
            add(node.right,e);
        }
    }*/
	
    //改进后的代码
    private Node add(Node node,E e) {
        if(node == null) {
            size++;
            return new Node(e);
        }

        if(e.compareTo(node.e) < 0) {
            node.left = add(node.left,e);
        } else if(e.compareTo(node.e) > 0) {
            node.right = add(node.right,e);
        }
        return node;
    }
}
```

