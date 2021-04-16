# Docker的概述

## Docker为什么会出现

发布一个项目 jar包（Redis, mysql,jdk es) 

给为    jar  +（环境）

之前在服务器配置一个应用环境 Redis,Mysql, jdk, h5 配置超麻烦，不能跨平台

windos开发，最后发布到Linux

传统 开发 jar  运维来做

现在：开发打包部署上线，一套流程做完





java -apk -发布（应用商店）--张三使用apk-安装即可

java -jar(环境) --打包项目带上环境（镜像）---（Docker仓库：商店）--下载我们发布的镜像---直接运行即可





Docker给以上的问题，提出了解决方案！

看Docker的思想就来自于集装箱

JRE-多个应用（端口冲突）--原来都是交叉的

隔离：Docker的核心思想！ 打包装箱！每个箱子都是互相隔离的

水果：生化武器

Docker通过隔离机制，可以将服务器利用到极致

本质：所有的技术都是因为出现了一些问题，我们需要去解决，才去学习



## Docker的历史

Docker 和 虚拟机都是虚拟化技术

为什么这么火   原因就是 十分轻巧

Docker 官网文档 https://docs.docker.com/  文档详细

Docker 仓库 https://hub.docker.com/_/docker



## Docker能干嘛

虚拟机技术的缺点：

​	1.资源占用十分多

​	2.冗余步骤多

​	3.启动很慢



容器化技术

比较Docker和虚拟机技术的不同

​	传统虚拟机，虚拟出一条硬件，运行一个完整的操作系统，然后在这个系统上运行

​	容器内的应用直接运行在宿主机的内容，容器没有自己的内核，也没有虚拟我们的硬件，所以就轻便了

​	每个容器间互相隔离，每个容器内都有一个属于自己的文件系统，互补影响

​	

DevOps(开发，运维)

**应用更快捷的交付和部署**

传统：一堆帮助文档，安装程序

Docker: 打包镜像发布测试，一键运行

**更便捷的升级和扩缩容**

使用了Docker之后，我们部署应用就和搭积木一样

项目打包为一个镜像，扩展 服务器A 服务器B

**更简单的系统运维**

在容器化之后，我们的开发，测试环境都是高度一致的

**更高效的计算资源利用**

Docker是内核级别的虚拟化，可以再一个物理机上可以运行很多的容器实例！服务器的性能可以被压榨到极致



## Docker的安装



### 基本组成

 ![img](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606480191177&di=efeb784289febed8fa325076e7244eca&imgtype=0&src=http%3A%2F%2Fimage.bubuko.com%2Finfo%2F201709%2F20180111002644528732.png) 

镜像（images) : 

docker镜像好比是一个模板，可以通过这个模板来创建容器服务，tomcat镜像==>run==>tomcat容器（提供服务器），通过这个镜像可以创建多个容器（最终服务运行或者项目运行就在容器中)

容器（container)

docker利用容器技术，独立运行一个或者一个组应用，通过镜像来创建

 启动，停止，删除，基本命令

目前就可以把这个容器理解为就是个简易的Linux系统

仓库（repository)

仓库就是存放镜像的仓库  分为公有和私有的

Docker Hub(默认是国外的)

也有阿里云 

### 安装Docker

查看帮助文档 https://docs.docker.com/engine/install/centos/

```shell
#1.卸载旧版本
 yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
                  
#2.需要的安装
yum install -y yum-utils

#3.设置镜像的仓库
yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo #默认是国外的
    
 yum-config-manager --add-repo \
 http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo #阿里云 推荐
 
#更新 yum软件包索引
yum makecache fast

#4.安装docker相关的  docker-ce社区  ee企业版
yum install docker-ce docker-ce-cli containerd.io

#5.启动docker
systemctl start docker

#查看是否安装成功
docker version

#7.
docker run  hello-world

#查看镜像
docker image

#卸载docker
1.yum remove docker-ce docker-ce-cli containerd.io
2.rm -rf /var/lib/docker
```

### 阿里云镜像加速

```shell
https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors

sudo mkdir -p /etc/docker

sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://ur5s9n4z.mirror.aliyuncs.com"]
}
EOF

sudo systemctl daemon-reload

sudo systemctl restart docker


sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://ur5s9n4z.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```



### Docker原理

https://www.processon.com/diagraming/5fc0fef55653bb027fa32fe9

Docker为什么比VM快

1.Docker有着比虚拟机更少的抽象层

2.Docker利用的是宿主机的内核，vm需要Guest OS

所以说 新建一个容器的内核,docker不需要像虚拟机一样加载一个操作系统的内核，避免引导，虚拟机是加载Guest OS,分钟级别，而docker是利用宿主机的操作系统，省略了复杂的过程，秒级



## Docker常用命令

### 帮助命令

```shell
docker version  #显示docker的版本信息
docker info   #显示docker的系统信息
docker 命令 --help  #万能命令
systemctl restart docker #c重启docker
#开启docker
systemctl start docker
#关闭docker
systemctl stop docker
```

帮助文档的地址：https://docs.docker.com/reference/



### 镜像命令

```shell
docker images #查看本地所有镜像

[root@localhost ~]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
hello-world         latest              bf756fb1ae65        10 months ago       13.3kB

#解释
REPOSITORY  镜像的仓库源
TAG         镜像的标签
IMAGE ID    镜像的ID
CREATED     镜像的创建时间
SIZE        镜像的大小


docker search mysql #和docker hub上搜索是一样的
docker search mysql --filter=stars=3000  搜索出来的镜像大于3000的

docker pull mysql #下载镜像，默认的tag的last 




```



```shell
#镜像下载
[root@localhost ~]# docker pull mysql
Using default tag: latest  #如果不写默认是 last
latest: Pulling from library/mysql
852e50cd189d: Pull complete    #分层下载 docker image的核心 联合文件系统
29969ddb0ffb: Pull complete 
a43f41a44c48: Pull complete 
5cdd802543a3: Pull complete 
b79b040de953: Pull complete 
938c64119969: Pull complete 
7689ec51a0d9: Pull complete 
a880ba7c411f: Pull complete 
984f656ec6ca: Pull complete 
9f497bce458a: Pull complete 
b9940f97694b: Pull complete 
2f069358dc96: Pull complete 
Digest: sha256:4bb2e81a40e9d0d59bd8e3dc2ba5e1f2197696f6de39a91e90798dd27299b093  #签名
Status: Downloaded newer image for mysql:latest
docker.io/library/mysql:latest  #真实地址


[root@localhost ~]# docker pull mysql:5.7
5.7: Pulling from library/mysql
852e50cd189d: Already exists   #分层下载的好处  也是Linux的核心联合文件系统
29969ddb0ffb: Already exists   
a43f41a44c48: Already exists 
5cdd802543a3: Already exists 
b79b040de953: Already exists 
938c64119969: Already exists 
7689ec51a0d9: Already exists 
36bd6224d58f: Pull complete 
cab9d3fa4c8c: Pull complete 
1b741e1c47de: Pull complete 
aac9d11987ac: Pull complete 
Digest: sha256:8e2004f9fe43df06c3030090f593021a5f283d028b5ed5765cc24236c2c4d88e
Status: Downloaded newer image for mysql:5.7
docker.io/library/mysql:5.7


#删除 image
docker rmi -f imageId 或者repository
docker rmi -f 容器id 容器id2 #删除多个容器
docker rmi -f $(docker images -aq) #删除全部的容器

```



### 容器命令

说明：我们有了镜像才可以创建容器，Linux,下载一个centos 学习

docker pull centos

新建容器并启动

```shell
docker run [可选参数] image

#参数说明
--name="Name" 容器名字 tomcat01 tomcat02，用来区分容器
-d            后台方式运行
-it           使用交互方式运行，进入容器查看内容
-p            指定容器的端口 -p 8080:8080
-p  
	-p  ip主机端口：容器端口
	-p  主机端口：容器端口
	-p  容器端口
-p      随机指定端口


docker run -it centos /bin/bash  #启动并进入centos容器
#容器内的和外部的是没有关系，基础版本，命令很多都是不完善的

#从容器退出并退出
exit
#正在运行的容器
docker ps
#曾经和正在运行的容器
docker ps -a
#最近创建的容器
docker ps -a -n=1
#容器id
docker ps -aq
#退出容器但不停止
ctrl +p+q 
#删除容器
docker rm 容器id  #不能删除正在运行的容器
#删除全部的容器
docker rm -f $(docker ps -ap)

启动和停止容器

docker start 容器id   #启动容器
docker restart 容器id  #重启容器
docker stop 容器id    #停止当前正在运行的容器
docker kill 容器id    #强制停止当前容器


```

### 常用的其他命令

```powershell
docker run -d 镜像名 
发现docker ps -a 容器停止了

root@localhost ~]# docker ps
CONTAINER ID        IMAGE        
a0a0acb94059        centos   

#显示日志
docker logs -tf --tail 10 容器id
编写一段脚本
docker run -d centos /bin/bash -c "while true;do echo zhengj;sleep 1;done"

#查看容器中的进程信息
docker top 容器id
#查看镜像的元数据
docker inspect 容器id

```

### 进入当前正在运行的容器

```shell
#命令进入容器
#方式一
docker exec -it 容器id bashShell
#测试
docker exec -it 容器id  /bin/bash

#方式二
docker attach 容器id
正在执行当前的代码

方式一 进入一个新的终端，可以在里面操作
方式二 进入容器正在执行的终端

```

### 从容器内拷贝文件到主机上

```shell
[root@localhost ~]# docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
393a3ed5c2f6        centos              "/bin/bash"         13 minutes ago      Up 13 minutes                           quirky_burnell
[root@localhost ~]# docker exec -it 393a3ed5c2f6 /bin/bash
[root@393a3ed5c2f6 /]# ls 
bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
[root@393a3ed5c2f6 /]# cd home
[root@393a3ed5c2f6 home]# ls
[root@393a3ed5c2f6 home]# touch test1.java
[root@393a3ed5c2f6 home]# exit
exit
[root@localhost ~]# docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
393a3ed5c2f6        centos              "/bin/bash"         14 minutes ago      Up 14 minutes                           quirky_burnell
[root@localhost ~]# docker cp 393a3ed5c2f6:/home/test1.java /home  #拷贝的命令
[root@localhost ~]# ls
anaconda-ks.cfg
[root@localhost ~]# cd /home
[root@localhost home]# ls
#拷贝是一个手动的过程，未来我们使用 -v卷的技术，可以实现
```

## 练习

docker安装nginx

```shell
1.搜索镜像

2.拉取镜像

#-d 后台运行
#--name 给容器命名
#-p 宿主机端口：容器内部端口
docker run -d --name nginx01 -p 3344:80 nginx(也可以换成imageid)
#test
curl localhost:3344

#进入容器
docker exec -it nginx01 /bin/bash
whereis nginx
cd /etc/nginx
ls
思考 ？ 如何不进入容器，来修改配置文件
```

docker安装tomcat

```shell
#官方的使用

docker run -it --rm tomcat:9.0
#我们之前的启动都是后台，停止了容器还是可以找到， docker run -it --rm 一般用来测试，用完就删除

#下载
docker pull tomcat:9.0

#启动并运行
docker run -d -p 3355:8080 --name tomcat01 tomcat

#进入容器
docker exec -it tomcat01 /bin/bash

#发现问题 1.Linux命令少了 2.没有webapps,阿里云镜像的原因，默认是最小的，所有不必要的都剔除了
#保证了最小可运行环境
但是webapps.dist里有，可以把这里的移到 webapps里
cp -r webapps.dist/* webapps

#latest可能不是最新的 而是使用最多的  

#虚拟机开启端口
firewall-cmd --add-port=3355/tcp
firewall-cmd --reload
还不行的话 重启下docker
#当容器名被占用
docker ps -a
docker rm 794b61371631 （容器id）  

#宿主机和容器通信 有一个好处 只有保持端口不变，可以把tomcat换成jetty等其他web服务器
```

思考问题，我们以后要部署，每次都要进入容器是不是十分麻烦，我要是可以在外部提供一个映射，webapps



docker部署es +kibanan

```shell
#es暴露的端口很多
#es 十分的耗内存
#es的数据一般需要放置到安全目录！挂载
#--net somework? 网络配置
来自docker hub
# docker run -d --name elasticsearch --net somenetwork -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:tag

docker run -d --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.9.0

#启动了 linux就卡主了 docker stats 查看cpu的状态
curl localhost:9200 查看是否安装成功 
#-e是配置文件的意思，通过修改配置文件更改es
docker run -d --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e ES_JAVA_OPTS="-Xms64m -Xmx512m"  elasticsearch:7.9.0


```

思考 kibanana 的网络连接通过linux连接到 es

图形化界面

```shell
 docker run -d -p 8088:9000 \
--restart=always \
-v /var/run/docker.sock:/var/run/docker.sock \
--name prtainer-test \
docker.io/portainer/portainer 
```



## Docker镜像原理讲解

镜像是什么

镜像是一种轻量级，可执行的软件包

所有的应用，直接打包docker镜像，就可以直接跑起来

如何得到镜像

​	从远程仓库下载

​	朋友拷贝给你

​	自己制作一个镜像 DockerFile



### Docker镜像加载原理

​	UnionFS(联合文件系统)

​	一层层来添加，比如第一步安装centos,第二步安装docker,第三部安装jdk

   但是三步都会记录下来，下次安装tomcat第一步和第二步就共享了

为什么centos 在docker里这么小呢

因为rootfs是每个发行版不一样的，bootfs则是一致的，docker提供的精简OS,使用了底层的bootfs

思考：如何是windows版的docker是否就会很大呢

### 分层理解

Docker的镜像都是只读的，当容器启动时，一个新的可写层被加载到容器的顶部

这一层就是我们说的容器层，容器之下的都叫镜像层

### commit镜像

```shell
#-a作者  -m 注释
 docker commit -a="zhengjian" -m="add webapps"  74df420ffc58（容器id）  tomcat2:1.0
```



## 容器数据卷

如果数据存储在容器里，删除容器就删除了数据

需要数据持久化

容器间可以数据共享的技术 就是 卷技术

其实就是把 容器里的目录挂载到 linux本地

总结一句话：容器的持久化和同步操作！容器间也可以数据共享的！

### 使用数据卷

```powershell
#直接使用命令来挂载

docker run -it -v 主机目录：容器内目录
#测试
[root@localhost ~]# docker run -it -v /home/ceshi2:/home centos /bin/bash
#测试
docker inspect 7be09a2b56ee


 "Mounts": [
            {
                "Type": "bind",
                "Source": "/home/ceshi2", #主机内地址
                "Destination": "/home",   #容器内地址
                "Mode": "",
                "RW": true,
                "Propagation": "rprivate"
            }
        ],

验证后发现 两边修改一边，另一边就会同步

```

### 实战：安装mysql

```powershell
$ docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw 

#-e环境配置 -d后台运行 -v数据卷
docker run -d -p 3310:3306 -v /home/mysql/conf:/etc/mysql/conf.d  -v \
/home/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=1234 --name mysql01 mysql:5.7

rm -rf mysql01 后本地目录的data还是在的
```

### 具名和匿名挂载

所有docker容器内的卷，没有指定目录的情况下都是在 /var/lib/docker/volume/***/_data

我们通过具名挂载可以方便的找到我们的一个卷，大多数情况要用 具名挂载

docker volume ls

```shell
#如何确定是具名还是匿名还是指定路径

-v 容器内路径 #匿名挂载
-v 卷名:容器内路径 #具名挂载
-v /宿主路径::容器内路径 #指定路径挂载

#拓展
ro  rw  末尾添加表示改变权限，分别是可读和可写

```

### 初识dockerfile

dockerfile就是用来构建镜像的构建文件，命令脚本，先体验一下

通过这个脚本可以生成镜像，镜像是一层一层的，脚本的一个个命令，每个命令就是一层

```shell
#创建 dockerfile1

#里面的内容

FROM centos

VOLUME ["volume01","volume02"]

CMD echo "--------end---------"
CMD /bin/bash

#生成命令
docker build -f dockfile1 -t zhengj/centos:1.0 .

#测试自己的镜像

docker run -it  e7ef78e0c7bf  /bin/bash

然后通过docker inspect 容器id
查看 mouses的 source路径，就可以看到物理映射地址
发现 在容器内的路径新建文件 物理地址也新建了
```



### 数据卷容器

```shell
#--volumes-from
实现容器间的数据同步共享
docker run -d -p 3311:3306 -e MYSQL_ROOT_PASSWORD=1234 --name mysql02 --volumes-from mysql01 mysql:5.7
```

结论：容器之间配置信息的传递，数据卷容器的生命周期一直持续到没有容器使用为止

但是一旦你持久化到了本地，这个时候，本地的数据是不会删除的

--volumes-from 是继承 父容器的



## DockerFile

dockerfile是面向开发的，我们以后要发布项目，做镜像，就要编写dockerfile文件，这个文件十分简单

Docker镜像逐渐成为企业交付的标准，必须要掌握

DockerFile:构建文件，定义了一切的步骤，源代码

Dockerimage:通过dockerFile构建生成的镜像，最终发布和运行的产品

Docker容器：容器就是镜像运行起来提供服务的

基础知识：

​	1.都是大写

​	2.从上到下一步一步执行

​    3.#号是注释

​	4.每一个指令都会创建提交一个新的镜像层，并提交

### DockerFile的指令

```shell
FROM  #这个镜像的妈妈是什么 指定基础镜像

MAINTAINER #告诉别人这个镜像的作者

RUN # 镜像构建的时候需要运行的命令

ADD  #步骤，tomcat镜像 这个tomcat压缩包 添加内容

WORKDIR #设置当前工作目录

VOLUME #挂载目录

EXPOSE #暴露端口配置

CMD  #指定这个容器启动要运行的命令，只有最后一个会生效

ENTRYPOINT #指定这个容器启动要运行的命令，可以追加命令

ONBUILD #当构建一个被继承，Dockerfile 这个时候就会 ONBUILD命令，触发指令

COPY #类似ADD，将我们的文件拷贝到镜像

ENV #构建的时候设置环境变量


```

### 构建自己的centos

```shell
mkdir /home/dockfile
touch mydockerfile
[root@localhost dockerfile]# vi mydockerfile 
[root@localhost dockerfile]# cat mydockerfile 
FROM centos
MAINTAINER zhengj<570470242@qq.com>

#启动镜像直接进入到这个目录
ENV MYPATH /usr/local
WORKDIR $MYPATH

RUN yum -y install vim
RUN yum -y install ll #这个先去掉是自己加的，不是教程里的
RUN yum -y install net-tools #这句卡住了

EXPOSE 80

CMD echo $MYPATH
CMD echo "----end-----"
CMD /bin/bash

#构建文件镜像
docker build -f mydockerfile -t mycentos:0.1 .


#运行结果对比
docker images
docker run -it mycentos:0.1
发现vim 这个命令可以用了
docker history 4cc4743afa35（image id) 可以看都构建过程
#需要注意的是dockerfile文件里的每一步都会生成一个镜像
```



### CMD和ENTRYPOINT的区别

过了，没听



### 实战：构建自己的tomcat

我们以后的开发的步骤：需要掌握Dockerfile的编写，我们之后的一切都是用docker镜像来发布运行

```shell
1.新建了自己的目录, /home/zhengjian/tomcat
2.下载tomcat和jdk的linux版本 放到了tomcat文件夹下,新建readme.txt文件
3.编写Dockerfile,官方名称就是Dockerfile,build会自动寻找这个文件，就不需要-f指定了
新建 Dockerfile
#文件编写
FROM centos
MAINTAINER zhengjian<570470242@qq.com>
COPY readme.txt /usr/local/readme.txt
#会自动解压
ADD jdk-8u181-linux-x64.tar.gz /usr/local
ADD apache-tomcat-9.0.1.tar.gz /usr/local

#RUN yum -y install vim
RUN yum -y install net-tools

ENV MYPATH /usr/local
WORKDIR $MYPATH

ENV JAVA_HOME /usr/local/jdk1.8.0_181
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV CATALINA_HOME /usr/local/apache-tomcat-9.0.1
ENV CATALINA_BASH /usr/local/apache-tomcat-9.0.1
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin

EXPOSE 8080

CMD /usr/local/apache-tomcat-9.0.1/bin/startup.sh && tail -F /usr/local/apache-tomcat-9.0.1/bin/logs/catalina.out

#构建
docker build -t mydirtomcat .

#报错
Error processing tar file(exit status 1): archive/tar: invalid tar header
换了个tomcat包
#卡住
把RUN yum -y install vim 注释掉了

#启动
docker run -d -p 9090:8080 --name zhengjiantomcat -v /home/zhengjian/tomcat/test:/usr/local/apache-tomcat-9.0.1/webapps/test -v /home/zhengjian/tomcat/tomcatlogs:/usr/local/apache-tomcat-9.0.1/logs mydirtomcat  

#测试访问
本来一直不行 yum vim安装后，还有-f 改成-F就醒了，不知道哪个原因
端口9090

#发布项目
在test目录下
mkdir WEB-INF
cd WEB-INF
vim web.xml


<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1">


    <welcome-file-list>
        <welcome-file>index.html</welcome-file>
        <welcome-file>index.htm</welcome-file>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>

</web-app>
  
cd ..
#注意是在 test文件夹下
vim index.jsp

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP在页面显示实时时间</title>
</head>
<body>
	<%
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(d);
	%>
	
	当前时间：<%=now %>
</body>
</html>

#发现是docker里的时间

```

### 发布到DockerHub

dockerhubl.com上注册

docker login 

docker push zhengjian/myditomcat 1.0



### 发布到阿里云

参考官方文档