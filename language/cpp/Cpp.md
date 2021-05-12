# Cpp基础

其实 std是全称  std::cout << i << std::endl;

using namespace 可以简便些

```c++
#include<iostream>
using namespace std;

int main()
{
	cout << "Hello World" << endl;
	return 0;

} 
```

## 常量

c++的常量两种方式

宏 和 const

```c++
#include<iostream>
using namespace std;

#define Day 7

int main()
{
	const int hour = 24;
	cout << "a week has = " << Day << " days" <<endl; 
	cout << "a day has = " << hour << " hours" <<endl;
	return 0;

} 
```

sizeof

```c++
#short  -2 15  -2 15  -1
#include<iostream>
using namespace std;

int main()
{
	cout << "short 类型所占空间大小 " << sizeof(short) <<endl; 
	cout << "int   类型所占空间大小 " << sizeof(int) <<endl; 
	cout << "long  类型所占空间大小 " << sizeof(long) <<endl; 
	cout << "long long 类型所占空间大小 " << sizeof(long long) <<endl; 
    
    cout << "float所占大小 " << sizeof(float) << endl;
	cout << "double所占大小 " << sizeof(double) << endl;
	float  f2 = 1.234f;
	//科学计数法 
	float f3 = 3e9;
	cout << "f3 = " << f3 << endl;
	double f4 = 4.1e-8;
	cout << "f4 = " << f4 << endl;
    
    //a 97  A 65
    char ch = 'a'; 
	char ch2 = 'A';
	cout << "a对应的ASCII码：" << (int)ch << endl;
	cout << "A对应的ASCII码：" << (int)ch2 << endl;
    
	return 0;

} 
```



## 转义符

```c++
cout << "hello world" << endl; 
等价于
cout << "hello world\n";

cout << "\\" << endl;

\t  水平制表 能排列整齐
cout << "aaaa\thelloworld" << endl;
cout << "aa\thelloworld" << endl;
cout << "aaaaaa\thelloworld" << endl;


```



## 字符串

```c++
#include<iostream>
using namespace std;

int main()
{	
	//1.c风格字符串 
	//注意事项 char 字符串[] 
	char str1[] = "hello world";
	cout << str1 << endl;
	

	//2.c++风格字符串
    //有些版本要加头文件 #include<string>
	string str2 = "hello world";
	cout << str2 << endl;
	return 0;

}
```



## 布尔类型

除了0 外都是 真

```c++
using namespace std;

int main()
{	
	bool flag = true;
	cout << flag << endl;  //1
	flag = false;
	cout << flag << endl;  //0
	

	cout <<"bool所占空间：" << sizeof(bool) << endl; 
    //输出布尔结果 
    cout << (1 == 1) << endl;
    
    int a = 10;
    cout << !a << endl;
	return 0;

}
```



## 三目运算

```
#include<iostream>
using namespace std;

int main()
{	
	//c++ 中三目运算是变量可以继续赋值
	int a = 10;
	int b = 20;
	a > b ? a : b = 1000;
	cout << "a = " << a << endl;
	cout << "b = " << b << endl;
	return 0;
}
```



## 数组

```c++
#include<iostream>
using namespace std;

int main()
{	
	int arr[] = {1,2,3,44,5,45,99};
	int length = sizeof(arr)/sizeof(arr[0]);
	for (int i=0; i<length; i++) {
		cout << arr[i] << endl;
	}
    
    
    cout << "数组首地址为" << (int)arr << endl;
    cout << "数组首地址为" << arr << endl;
    cout << "数组中第一个元素地址为" << &arr[0] << endl;
    cout << "数组中第二个元素地址为" << &arr[1] << endl;
    
    //arr = 100 错误，数值名是常量，因此不可以赋值
	return 0;

}
```

sizeof(arr)  整个数组在内存中的长度

sizeof(arr[0]) 首地址长度

 (int)arr 报错 可能是因为 溢出， 转为long long 就不报错了



### 数组的置换

```c++
#include<iostream>
using namespace std;

int main()
{	
	int arr[] = {1,2,3,44,5,45,99};
	cout << "数组置换前是:" << endl;
	int length = sizeof(arr)/sizeof(arr[0]); 
	for (int i=0; i<length; i++) {
		cout << arr[i] << ",";
	}
	cout << "" << endl;
	int start = 0;
	int end = sizeof(arr)/sizeof(arr[0]) - 1;
	while (start < end) {
		int temp = arr[start];
		arr[start] = arr[end];
		arr[end] = temp;
		start++;
		end--;
	}
	cout << "数组置换后是:" << endl;
	for (int i=0; i<length; i++) {
		cout << arr[i] << ",";
	}
	return 0; 
}
```



### 冒泡排序

关键点：排序总轮数 = 元素个数 - 1

​			   每轮对比数  = 元素个数 - 排序轮数 - 1

时间复杂度 O (n方)

参考网站：https://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html

```c++
#include<iostream>
using namespace std;

int main()
{	
	int arr[] = {1,2,3,44,5,45,99,4,22,101,8};
	cout << "排序前:" << endl;
	int length = sizeof(arr)/sizeof(arr[0]); 
	for (int i=0; i<length; i++) {
		cout << arr[i] << ",";
	}
	cout << "" << endl;
	//冒泡排序 
	for(int i=0;i<length-1;i++) {
		for(int j=0;j<length-i-1;j++) {
			if(arr[j] < arr[j+1]) {
				int temp = arr[j+1];
				arr[j+1] = arr[j];
				arr[j] = temp;
			}
		}
	} 
	cout << "排序后是:" << endl;
	for (int i=0; i<length; i++) {
		cout << arr[i] << ",";
	}
	return 0; 
}
```

### 二维数组

```c++
//定义二维数组的方式的3种方式
int arr[2][3] = {
		{1,3,4},{0,1,3}
	};


int arr[2][3] = {1,2,3,4,5,6};


int arr[][3] = {
		{1,3,4},{0,1,3}
	};


cout << "二维数组占用内存空间:" << sizeof(arr) << endl;
cout << "二维数组第一行占用内存空间:" << sizeof(arr[0]) << endl;
cout << "二维数组第一个元素占用内存空间:" << sizeof(arr[0][0]) << endl;

cout << "二维数组行数为:" << sizeof(arr)/sizeof(arr[0]) << endl;
cout << "二维数组列数为:" << sizeof(arr[0])/sizeof(arr[0][0]) << endl;

cout << "二维数组首地址:" << arr << endl;
cout << "二维数组第一行首地址:" << arr[0] << endl;
cout << "二维数组第二行首地址:" << arr[1] << endl;
cout << "二维数组第一个元素首地址:" << &arr[0][0] << endl;
return 0; 
```

​	

## 函数

和c语言一样，需要在main方法前定义 ，写后面，需要先进行函数的声明

注意声明可以重复多次，但是定义只能有一次

值传递，swap并不会改变，需要传指针