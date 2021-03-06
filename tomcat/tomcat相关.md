https://www.processon.com/diagraming/601d79dc6376893ae22292c9

大致架构，草图



# 环境搭建

tomcat的源码下载

http://archive.apache.org/dist/tomcat/tomcat-8/v8.5.9/src/

1.下载后解压

2.在解压的同级目录新建 catalina-home 

3.在 catalina-home里新建config，webapps文件夹，里面内容来自soucre的同名文件夹  ，新建lib ，logs, temp ,	为空 work里建work\Catalina\localhost   webapps里的再复制过去

4.在解压的同级目录创建 pom.xml

内容如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.tomcat</groupId>
    <artifactId>apache-tomcat-8</artifactId>
    <version>1.0</version>
    <name>apache-tomcat-8-source</name>
    <packaging>pom</packaging>

    <modules>
        <module>apache-tomcat-8.5.9-src</module>
    </modules>

</project>
```

5.tomcat source目录下新建pom.xml  

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>Tomcat8.5.9</artifactId>
    <version>8.5</version>
    <name>Tomcat8.5.9</name>

    <build>
        <finalName>Tomcat8.0</finalName>
        <sourceDirectory>java</sourceDirectory>
        <resources>
            <resource>
                <directory>java</directory>
            </resource>
        </resources>
        <testResources>
            <testResource>
                <directory>test</directory>
            </testResource>
        </testResources>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.0.2</version>

                <configuration>
                    <encoding>UTF-8</encoding>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <dependency>
            <groupId>org.easymock</groupId>
            <artifactId>easymock</artifactId>
            <version>3.5</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>ant</groupId>
            <artifactId>ant</artifactId>
            <version>1.7.0</version>
        </dependency>
        <dependency>
            <groupId>wsdl4j</groupId>
            <artifactId>wsdl4j</artifactId>
            <version>1.6.2</version>
        </dependency>
        <dependency>
            <groupId>javax.xml</groupId>
            <artifactId>jaxrpc</artifactId>
            <version>1.1</version>
        </dependency>
        <dependency>
            <groupId>org.eclipse.jdt.core.compiler</groupId>
            <artifactId>ecj</artifactId>
            <version>4.6.1</version>
        </dependency>
    </dependencies>
</project>
```

6.采坑的地方  ， 1.删除 source里的 test目录。否则在test目录中有错误类会报错

​		2.配置启动参数

-Dcatalina.home=catalina-home -Dcatalina.base=catalina-home
-Djava.endorsed.dirs=catalina-home/lib  -Djava.io.tmpdir=catalina-home/temp
-Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager
-Djava.util.logging.config.file=catalina-home/conf/logging.properties

配置的地方，idea run configurations  新建Application 命名tomcat8

​		Main class选择  org.apache.catalina.startup.Bootstrap

​		vm options选择上面的

​		use classpath of module选择tomcat8.5.9

3. ContextConfig.java 文件的 webConfig();加入以下代码

   context.addServletContainerInitializer(new JasperInitializer(), null);



点run后 启动成功 能访问 http://localhost:8080/

整合后的代码放置在qq邮箱中转站



# 代码分析

激励的话：

​		听源码肯定枯燥和难懂，但是你还得硬着头皮去听。等你听的多，翻源码翻的多了。	

​        等你思考的多了，等你能有自己的架构设计思想，这个时候想去哪就不是问题了。



Tomcat**初始化**源码分析

tomcat的入口类

org.apache.catalina.startup.Bootstrap.java

静态代码块 ： 

1.设置CatalinaHome (Tomcat安装目录)

2.设置CatalinaBase  (Tomcat工作目录)

main函数   4点，序列号的标记大小很重要

​	1.实例化 Bootstrap

​	2.初始化 Bootstarp    init()

​			(1)初始化初始 CommonLoader,catalinaLoader,sharedLoader 三个类加载器

​            (2)反射实例化 Catalina 对象  

​				//反射方法实例化 Catalina

Class<?> startupClass =    catalinaLoader.loadClass    ("org.apache.catalina.startup.Catalina");                                            Object 	startupInstance = startupClass.newInstance();

​			（3）引用Catalina实例

​					catalinaDaemon = startupInstance;

 		

​    3.**daemon.load()**  最主要

​		反射调度Catalina的load

```java
//Bootstrap的main方法
else if (command.equals("start")) {   
    //执行catalinout的load的方法
	daemon.setAwait(true);   
    daemon.load(args);  //代码流转到Catalina.java的load()
    daemon.start();
    }
```

​			Catalina.java  load() 方法

​              (1)定义解析server.xml配置的Digester

​			  	  //定义解析server.xml的配置，告诉Digester哪个xml标签应该解析成什么样

​					Digester digester = createStartDigester();

​			(2）加载conf/server.xml

​						a.先尝试解析conf/server.xml

​						b.没有server.xml 继续解析embed.xml

​						c.都没有，直接return

​			 (3) 为server设置了catalina的信息

​					 catalina的home  catalina的base

​			  

```java
  		//为server设置catalina的信息，指定catalina的实例，设置catalina的home,base路径
		getServer().setCatalina(this);
        getServer().setCatalinaHome(Bootstrap.getCatalinaHomeFile());
        getServer().setCatalinaBase(Bootstrap.getCatalinaBaseFile());

        // Stream redirection
        initStreams();

        // Start the new server
        try {
            //调度starndserver的init方法，完成各个组件的初始化
            getServer().init();
        } catch (LifecycleException e) {
            if (Boolean.getBoolean("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE")) {
                throw new java.lang.Error(e);
            } else {
                log.error("Catalina.start", e);
            }
        }
```

​		Load方法中很重要的一件事情，就是调度了Server,然后代码流转到StandardServer的init()	

​        (4)**StandardServer的init的方法**

​				StandSever 没有init方法     源码分析调的是爷爷的 init方法

​				我们通过观察发现LifeCycleBase的initInternal方法是抽象方法，所以交给子类去重写

​	所以代码就通过多态机制，调度的是子类的initInternal方法，我们再次将执行流程回到StandSever           	initInternal 方法

​				a.往jmx中注册全局的Stringcache

​				b.注册MBeanFactory,用来管理Service

​				c.往jmx中注册全局的NamingResources

​				d.初始化内部的Service

​		在StandardSever 的initInternal方法中，做的最主要的事情是初始化services集合

​		所以代码流转到StandardService的initInternal方法

​    4.daemon.start



