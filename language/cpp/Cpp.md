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
	
	return 0;

} 
```

