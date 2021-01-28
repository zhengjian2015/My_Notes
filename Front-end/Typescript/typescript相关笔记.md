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

| 类型   | 例子                                                         |      |
| ------ | ------------------------------------------------------------ | ---- |
| number | 1,3                                                          |      |
| string | 'a','b'                                                      |      |
| 字面量 | let  b : 10 = 10;   let b : "male" \|   "false"        联合类型 let c : string \| number |      |
| any    | 任意   不建议用                                              |      |
|        |                                                              |      |
|        |                                                              |      |
|        |                                                              |      |
|        |                                                              |      |
|        |                                                              |      |
|        |                                                              |      |
|        |                                                              |      |

