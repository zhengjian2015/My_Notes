# 红黑树

## 2-3树

要理解红黑树，先要理解2-3树

![image-20210629214501512](image-20210629214501512.png)

2个元素的节点是 3节点，1个元素的节点是2节点

2-3树是一颗绝对平衡的树

2-3添加的时候，永远不会添加到空节点上去

![image-20210704000230172](image-20210704000230172.png)



## 红黑树和2-3树

![image-20210704002223856](image-20210704002223856.png)



![image-20210704003649849](image-20210704003649849.png)

2-3树里的3节点 转为 红黑节点，向左倾斜的是红节点

红黑树 是保持 黑平衡 的 二叉树

严格意义上，不是平衡二叉树   最大高度：2logn    O(logN)  



视频看到2-3树和红黑树就没再看了，原因，一是坚持不住，而是看了也记不住，三是面试基本不问红黑树的具体实现细节

