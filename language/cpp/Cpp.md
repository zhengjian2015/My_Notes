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

\t  水平制表

cout << "aaaa\thelloworld" << endl;
cout << "aa\thelloworld" << endl;
cout << "aaaaaa\thelloworld" << endl;

```

