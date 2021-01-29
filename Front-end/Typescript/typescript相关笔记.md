# 安装

1.安装typescript首先安装node.js 参考路径如下：https://www.jianshu.com/p/fbd87ad45631



2.运行 npm(cnpm)  install -g typescript



3.编写ts文件后，可以tsc  xx.ts  编译成js文件



# 环境搭建

vsc  是前端的好工具，按html5 自动加载基本页面

执行 npm init  生成package.json

执行 npm install --save-dev lite-server  安装本地服务器 说明参考：https://blog.csdn.net/u014291497/article/details/70163088

也可以把上述dev改成 start 运行 npm start

同时要注意，html命名为index.html才能打开就是首页



Visual Studio Code可以通过以下快捷键 格式化代码：

- On Windows 　**Shift + Alt + F**
- On Mac 　**Shift + Option + F**
- On Ubuntu　　 **Ctrl + Shift + I**



# 基础语法

typescript可以在编译期帮我们检查出错误

如下代码，tsc 时会报错 需把num1 改成 数字

注意类型的定义都是小写的

```typescript
function add(num1: number, num2: number) {
    return num1 + num2;
}
const num1 = '5';

const num2 = 2.9;

const result = add(num1, num2);

console.log(result);

let result = false;
result = true;
result = 3; //error,如果变量的声明和赋值是同时进行的，TS可以自动对变量进行类型检测
//显然，变量类型在函数中的作用比普通变量更有用

//返回值是number
function add(num1: number, num2: number) : number {
    return num1 + num2;
}

```

ps:  tsc会因为类型问题编译出错，但是依旧会生成对应的js文件

- 如果要在报错的时候终止 js 文件的生成，可以在 tsconfig.json 中配置 noEmitOnError 即可
- `注意tsconfig.json文件只在ts项目中才有，单独创建的ts文件是没有这个配置文件的`

 当设置为 const num:number 时没必要马上赋值，相反的赋值时，可以省略变量类型 如 let number = 5;



关于能指定的类型

| 类型    | 例子                                                         |
| ------- | ------------------------------------------------------------ |
| number  | 1,3                                                          |
| string  | 'a','b'                                                      |
| 字面量  | let  b : 10 = 10;   let b : "male" \|   "false"        联合类型 let c : string \| number |
| any     | 任意   不建议用                                              |
| unkonwn | 类型安全的any  unkonwn不能直接赋值给其他变量 要先判断 typeof  ===  'string' |
|         | 类型断言 告诉解析器变量的实际类型    a = e as string  或者   a = <string> e |
| void    | 用来表示空 以函数为例 没有返回值  function fn() : void { }   |
| never   | 没有值    表示永远不会返回结果 连空都没有        function fn2() : never {} |
| object  | let a : object    一般情况不使用 而是用  let b : {name:string, age ?:number} 加问号表示可选。let c : {name:string ,[proName:string] : any}; 任意类型的属性 |
| array   | [1,2,3]   let  a : string []   或者  let b : Array<string>   |
| tump    | 元组，长度是固定 语法:[类型，类型]      let h : [string,number] |

```typescript
//定义函数
let d : (a:number,b:number) => number
d = function(n1,n2) {
    return n1 + n2;
}

//元组
let h: [string, number];
h = ['a', 12];

//枚举
enum Gender {
    Male,
    Female
}
let Student: { name: string, gender: Gender };
Student = { name: "Mike", gender: Gender.Male };

console.log(Student.gender === Gender.Male);

//类型的别名
//type myType  =  1|2|3|4|5;
let k : 1|2|3|4|5;
let m : myType;
```



# 编译选项

1. tsc   app.ts -w   //实时监控ts,一旦变化编译成js  ，缺点时得一直开着窗口

  

2. tsconfig.json  新建一个json文件    一个tsc 就能编译所有文件 并且-w能监视所有文件

   这个json文件能写注释，很多其他json文件不能写注释

   tsconfig.json是ts编译器的配置文件，ts编译器可以根据它的信息来对代码进行编译

   

```json
   {

	/*
	"include"用来指定哪些ts文件需要被编译
	  **任意目录   
       *任意文件
     “exclude" 不包含  有默认的 node_module,bower_compontents,jspm_package
     extends 继承 其他配置文件
     files  需要编译的文件，数组添加进去
	*/
    “include":[
       	  "./src/**/*"
       ],
	"exclude":[
        "./src/hello/**/*"  
      ]
	}
```

