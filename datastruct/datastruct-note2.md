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

判断是否存在 和 前序遍历，以及打印

```java
//查找二分搜索树中是否有某种元素，利用递归查找
    private boolean contain(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contain(node.left, e);
        } else { //e.compareTo(node.e) > 0
            return contain(node.right, e);
        }
    }

    //前序遍历
    public void preOrder() {
        preOrder(root);
    }

    //以node为根节点，递归遍历所有节点
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }
	
    //前序遍历
    public void preOrder() {
        preOrder(root);
    }

    //以node为根节点，递归遍历所有节点
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }


    //中序遍历  中序遍历是有顺序的
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if(node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //后序遍历使用场景 为二分搜索树释放内存
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if(node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generatesBSTString(root, 0, res);
        return res.toString();
    }

    //生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generatesBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generatesBSTString(node.left, depth + 1, res);
        generatesBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
```

测试用例，前序遍历

```java
public class Test {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();

        int[] arr = {5,4,6,7,8,3};


        /////////////////
        //      5      //
        //     / /     //
        //    4   6    //
        //    /   /
        //   3    7  8  //
        //  //     //  //

        for(int a: arr) {
            bst.add(a);
        }

        bst.preOrder();
        System.out.println(bst);
    }
```

