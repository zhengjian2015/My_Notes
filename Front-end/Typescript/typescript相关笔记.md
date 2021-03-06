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

//null判空
let box1 = document.getElementById("box1");
box1?.addEventListener("click",function(){
    alert(11);
});
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
      ],
	/*
	"compilerOptions"  编译器的选项 默认编译成es3
	*/
	  "compilerOptions": {
          "target":"ES3",
          //模块化规范 可以用小技巧如abc来看可选值，
          "module":"commjs",
          //lib用来指定项目中要使用的库,一般情况不用改，除非要在nodejs中使用
          //"lib":[]
          //outDir 用来指定编译后的目录
          "outDir":"../dist",
          //outFile 所有的全局作用域中的代码合并到一个文件中
          // "outFile":"../dist/app.js",
          //是否对js文件进行编译，默认是false
          "allowJs":false,
          //是否检查js代码是否符合规范,默认是false
          "checkJs":true,
          //是否移除注释
          "removeComments":true,
          //不生成编译后的文件
          //"noEmit":true
          //当有错误时不生成编译后的文件
          "noEmitOnError":true,
          //用来设置编译后的文件是否使用严格模式，默认false
          //ps :有export import 自动进入严格模式
          "alwaysStrict":false，
          //不允许隐式的any
          "noImplicitAny": true,
          //严格检查空值
          "strictNullChecks": false,
          //所有严格检查都打开
          "strcit":true
      }
	}
```

```typescript
//m.ts
export const hi = "你好";

//demo.ts
import {hi} from 'm.js'
console.log(hi);

//tsc来看模块化效果

//严格检查空值
let box1 = document.getElementById("box1");
box1?.addEventListener("click",function(){

});
```

# webpack的使用

npm init -y

//-D 会自动加到config文件上

cnpm i -D webpack webpack-cli typescript ts-loader

如果cnpm 卡主不动 参考这个 https://blog.csdn.net/qq_38409994/article/details/106550877

1.在package.json 的scriptes 加上一句   "build": "webpack"

2.新建webpack.config.js

```javascript
//引入一个包
const path = require("path");

//引入html插件
const htmlWebpackPlugin = require('html-webpack-plugin');

//引入clen插件 
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

//webpack中的所有配置信息 都应该写在 module.exports 文件中
module.exports = {

    //指定入口文件  ./是当前 ../是上一层
    entry: "./src/index.ts",

    //指定打包文件目录
    output: {
        //指定打包的目录
        path: path.resolve(__dirname, 'dist'),
        //打包后文件的名称
        filename: "bundle.js"
    },

    //指定webpack打包文件时要使用模块
    module: {
        //指定要加载的规则
        rules: [
            {
                //test指定的是规则生效的文件
                test: /\.ts$/,
                //要使用的loader
                use: 'ts-loader',
                //要排除的文件
                exclude: /node-modules/

            }
        ]
    },
    
    //配置webpack的插件
    plugins: [
        new CleanWebpackPlugin(),
        new htmlWebpackPlugin({
            //title:"这是自定义的title",
            //设置一个模板
            template:"./src/index.html"
        }),
    ],
    
    //用来设置引用模板 否则 export  import {hi} from "./m1" 这种代码会报错
    resolve: {
        extensions:{".ts",".js"}
    }
};
```

3.新建 tsconfig.json 

```json
{
    "compilerOptions": {
        "target":"ES6",
        "module": "ES6",
        "strict": true
    }
}
```

在src的ts中写些代买

执行命令  npm  run build  来打包

**当想自动生成html文件时**

1.引入插件  cnpm i -D html-webpack-plugin

2.修改webpack.config.js 

3.执行命令 npm run build

**当想有简易服务器时**

1.引入插件 cnpm i -D webpack-dev-server

2.在package.json 的scriptes 加上一句   "start": "webpack serve --open chrome.exe"  (启动服务器并用chrome打开)

3.执行 npm start

**彻底清除dist目录**

1.cnpm i -D clean-webpack-plugin

2.修改webpack.config.js   const 和 plugins 里 new cleanWebpackPlugin(),



**babel的使用**

为了使老游览器也能使用新标准，ts的target 不够，有时也转不了

1.命令      cnpm  i -D  @babel/core  @babel/preset-env   babel-loader core-js

2.修改webpack.config.j module rules 的 use   

```javascript
    //指定webpack打包文件时要使用模块
    module: {
        //指定要加载的规则
        rules: [
            {
                //test指定的是规则生效的文件
                test: /\.ts$/,
                //要使用的loader
                use: [
                    //配置babel
                    {
                        //指定加载器
                        loader: "babel-loader",
                        //设置babel
                        options: {
                            //设置预定义的环境
                            presets: [
                                [
                                    //指定环境的插件
                                    "@babel/preset-env",
                                    //配置信息
                                    {
                                        //要兼容的目标游览器
                                        targets: {
                                            "chrome": "88",
                                            "ie": "11"
                                        },
                                        //指出corejs的版本
                                        "corejs": "3",
                                        //使用core.js的方式 usage 表示按需加载
                                        "useBuiltIns": "usage"
                                    }
                                ]
                            ]
                        }
                    },
                    'ts-loader'
                ],
                //要排除的文件
                exclude: /node-modules/

            }
        ]
    },
```

corejs 可以把没有的转成自己的 比如ie11里的 Promise  corejs就会自己实现

同时如果使用ie webpack 默认会用箭头函数，ie里会报错

要改一下  output里加上 

 environment: {

​      arrowFunction: false

​    }



# 面向对象

```typescript
class Person {
    //只读
    readonly name : string = "zhengjian" ;

    static age : number = 18;

    sayHello () {
        console.log("大家好");
    }

}
const per = new Person();
console.log(per.name);
console.log(Person.age);
console.log(per.sayHello);


class Dog {
    name;
    readonly age;
    //构造函数
    constructor(name:string,age:number) {
        this.name = name;
        this.age = age;
    }

    beak() {
        alert("wang wang");
    }

}

const dog = new Dog("旺财",1);
//dog.age = 2;
console.log(dog);
```

**关于继承和super**

```typescript
(function(){
    //抽象类
    abstract class Animal {
        name: string;
        age: number;

        constructor(name: string) {
            this.name = name;
           
        }
		//抽象方法
        abstract beak(): void;
    }


    class Dog extends Animal{

        //如果子类有构造函数，那子类必须对父类的构造函数调用
        constructor(name: string, age: number) {
            super(name);
            this.age = age;
        }

        run() {
            console.log("dog is run");
        }

        //重写
        beak() {
            console.log("汪汪汪");
        }
    }

    class Cat extends Animal  {

        //如果子类有构造函数，那子类必须对父类的构造函数调用
        constructor(name: string, age: number) {
            super(name);
            this.age = age;
        }
        
         //重写
         beak() {
            //console.log("喵喵喵");
            super.beak();
        }
    }

    const dog1 = new Dog("旺财",1);

    const cat1 = new Cat("小苗",2);

    console.log(dog1.beak());
    console.log(cat1.beak());


}()); 
```

**接口** 

```typescript
  (function(){
   
    //描述一个对象的类型
    type myType = {
        name: string,
        age: number
    }

    //接口用来定义一个类结构
    interface myInteface {
        name: string,
        age: number
    }

    const obj: myInteface = {
        name: "sss",
        age: 23
    }

    /**
     * 接口的所有的属性都不能有实际的值
     * 接口只定义对象的结构，而不是考虑实际值
     *      在接口中所有方法都是抽象方法
     */
    interface myInter {
        name: string;

        sayHello():void;
    }

    /**
     * 定义类 实现接口中的所有成员
     */
    class MyClass implements myInter {
        name: string;

        constructor(name: string) {
            this.name = name;
        }

        sayHello() {
            console.log("hello");
        }
    }

}()); 
```

**私有属性**

```typescript
(function(){
   
   class Person {

        /**
         * public 
         * private
           protected 子类能访问父类的
         */

        private _name: string;

        private _age: number;

        constructor(name: string, age: number) {
            this._name = name;
            this._age = age;
        }

        getName() {
            return this._name;
        }

        setName(name: string) {
            this._name = name;
        }
       //Ts中的get
        get name() {
            return this._name;
        }
   }


   const per = new Person("zhengjian",30);

   console.log(per.getName());


}()); 
```

泛型

```typescript

//在定义函数和类时，如果遇到类型不明确可以使用泛型

function fn<T>(n:T):T {
    return n;
}

function fn2<T,K>(a:T,b:K):T {
    console.log(b);
    return a;
}

fn2<number,string>(123,'hello');

interface Inter {
    length: number;
}

function fn3<T extends Inter>(a:T) : number{
    return a.length;
}

class MyClass<T> {
    name: T;
    constructor(name: T) {
        this.name = name;
    }
}

const mc = new MyClass<string>("myname");
```

