# c语言简介

解释型和编译型不是绝对的，计算机语言本身并没有解释和编译之分

python也有人写编译器，c也有人做了解释器

只是传同上认为c是编译型语言，python是解释性语言



1989 ANSI 发不了 "ANSI C"，或有时称为"C89"（为了区别C99）。

c标准在95年和99年两次更新 C95和C99

所有当代编译器都支持$\textcolor{Red}{C99} $了



区别小计：

1.ansi c 只能在代码开头定义变量,c99 不限制

2.c99最好定义常量的方式    const int AMOUNT = 100;   

不定义的话，我们称100这种莫名其妙的数字为magic number

 



# 开发工具

dev c++ 5.6.4  可以写c++ (不用建工程)

sublime text 和MinGw一起使用 （Minniset Gnu for windows   gcc）

...

国际惯例：hello world

```c
#include <stdio.h>

int main()
{
	printf("Hello world\n");
    printf("12+23=%d\n",12+23);
	return 0;
}


```

unix中 可以用 gcc hello.c 来编译 如若不指定，则生成a.out 文件

./a.out  则会执行   ./是unix为了安全的手段，保证当前目录和系统重名的文件不会被执行

# 变量

```c
//当变量不初始化，就运算可能得到很奇怪的内容
//因为不打扫就进入，就看上家的人品了
int main()
{	
	int i;
	int j;
	j = i+10;
	printf("%d\n",j);
    //读取
    int price = 0;
    printf("请输入金额(元)");
    scanf("%d",&price);
    printf("您输入了%d元",price);
	return 0;
}

//当输入了英文hello
//输出了0
```

```c
#include <stdio.h>

int main()
{	
	int a;
	int b;
	printf("请输入两个数");
	scanf("%d %d",&a,&b);
	printf("%d+%d=%d",a,b,a+b);
	return 0;
}
```

## 浮点数

c语言中两个整数的运算结果只能是整数

10/3*3 => 是整数 

printf("%f\n",10.0/3); 

当整数和浮点数一起运算时，c语言会把整数变成浮点数再参与运算



#include <stdio.h>

int main(void)
{ub
	char ac[] = {1,2,3,4,5,6,7,};
	char *p = ac;
	printf("p = %p\n",p);
	printf("p+1 = %p\n",p+1);
	int ai[] = {1,2,3,4,5,6,7,};
	int *q = ai;
	int *q1 = &ai[5];
	printf("q = %p\n",q);
	printf("q+1 = %p\n",q+1);
	printf("q1-q = %d\n",q1 - q); //得到的是 a[5]/sizeof(int)
	return 0;
} 

#遍历数组的两种方法

```c
#include <stdio.h>

int main(void)
{
	int ai[] = {1,2,3,4,5,6,7,-1};
	int i;
	for(i=0; i<sizeof(ai)/sizeof(ai[0]); i++) {
		printf("%d\n",ai[i]);
	}
	int *p = ai;
	while(*p!=-1) {
		printf("%d\n",*p++);
	}
	return 0;
	
} 
```

0地址 通常是不能随便碰的地址

NULL是一个预定义的符号，表示0地址







```c
#include <stdio.h>
#include <stdlib.h>

int main(void)
{
	int number;
	int *a;
	int i;
	printf("输入数量");
	scanf("%d",&number);
	//int a[number]
	a = (int*)malloc(number*sizeof(int));
	for(i=0; i<number; i++) {
		scanf("%d",&a[i]);
	} 
	for(i=number-1; i>=0; i--) {
		printf("%d ",a[i]);
	}
	free(a); //释放申请的内存空间 
 	return 0;
} 
```



分配了空间

```c
#include <stdio.h>
#include <stdlib.h>

int main(void)
{
	void *p;
	int cnt = 0;
	while( (p=malloc(100*1024*1024))) {
		cnt++;
	}
	printf("分配了%dM空间\n",cnt);
 	return 0;
} 
```





```java
/**
 * 简易对答程序Server
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TalkSever {
	public static void main(String[] args) {
		try {
			ServerSocket server = null;
			try {
				server = new ServerSocket(4700);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("can not listen:" + e);
			}
			Socket socket = null;

			try {
				socket = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error:" + e);
			}
	
			String line;
			// 缓冲区
			BufferedReader is = new BufferedReader(new 			    InputStreamReader(socket.getInputStream()));
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			// 如果客户端没有输入，这个方法就阻塞了
			System.out.println("client:" + is.readLine());
			line = sin.readLine();
			// 这个循环表明客户端和服务端必须一人说一句
			while (!"bye".equals(line)) {
				// 把服务端键盘的输入写到客户端去
				os.println(line);
				os.flush();
				System.out.println("sever:" + line);
				System.out.println("Client:" + is.readLine());
				line = sin.readLine();
			}
			is.close();
			os.close();
			socket.close();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			System.out.println("Errorex:" + ex);
		}
	}

}


```



```java
/**
 * 简易对答程序Client
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TalkClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 4700);
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String readLine;
			readLine = sin.readLine();
			while (!"bye".equals(readLine)) {
				// 把客户端键盘的输入写到服务端去
				os.println(readLine);
				os.flush();
				System.out.println("Client:" + readLine);
				System.out.println("Server:" + is.readLine());
				readLine = sin.readLine();
			}
			is.close();
			os.close();
			socket.close();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			System.out.println("Errorex:" + ex);
		}
	}
}
```

