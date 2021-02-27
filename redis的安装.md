# redis的安装

**redis是单线程的**

redis是基于内存的，cpu不是它的性能瓶颈，redis的瓶颈是根据机器的内存和网络带宽，既然可以用单线程就用单线程

6.0 以后貌似已经用多线程了

cd redis-6.0.8

make

cd src

make install

redis的默认安装是 /usr/local/bin

再bin目录下 mkdir kconfig

cp /home/jian/redis-6.0.6/redirs.conf kconfig/

redis-server kconfig/redis.conf

redis-cli -p 6379

```
远程无法访问的问题： [https://blog.csdn.net/weixin_43388789/article/details/94190750?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~first_rank_v2~rank_v25-1-94190750.nonecase&utm_term=centos%E7%AB%AF%E5%8F%A36379%E6%97%A0%E6%B3%95%E8%AE%BF%E9%97%AE](https://blog.csdn.net/weixin_43388789/article/details/94190750?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~first_rank_v2~rank_v25-1-94190750.nonecase&utm_term=centos端口6379无法访问)
```



**常用命令**

```
set age 11  #key为age value为11

keys *

EXIST age 

move age 1  (1表示当前数据库，移除)

EXPIRE age 10  #10s中后过期

ttl age  #查看剩余时间

type age #查看age的类型

select 0 选择数据库0  默认有16个数据库 默认是0
```

# 常用类型

## String

```bash
set key1 v1

get key 1

APPEND key1 "hello"  #追加字段，如果key不存在，就相当于setkey

STRLEN key1  #长度

set views 0 

get views

incr views

incr views #+1

decr views #-1

INCRBY views 10 #加10

decrby views 10 #-10

getrange key1 0 3 #截取字符串[0,3]

setrange key1 1  xx #替换1位置的字符串


###################################################################
#setex(set with expire)  #设置过期时间
#setnx(set if not exist) #不存在在设置 在分布式锁中会常常使用

setex key3 30 "hello" #设置key3 30s

setnx mykey "redis"

setnx mykey "mongdb"

get mykey

mset k1 v1 k2 v2 k3 v3 #批量设置
mget k1 k2 k3	

msetnx k1 v1 k4 v4 #msetnx是个原子性的操作，要么一起成功，要么一起失败
(integer) 0

#对象
set user:1 {name:zhengj,age:28}
#这里的key是一个巧妙的设计：user:{id}:{field} 如此设计在redis里是完全ok的

也可以yongmset
mset user:1:name zhangsan user:1:age 29
mget user:1:name user:1:age


getset #先get后set
getset db redis 如果不存在的值则返回nil
get db
getset db mongdb
get db
```

## list

可以把list玩成 栈 队列 阻塞队列

```
lpush list one  

lpush list two

lpush list three

lrange list 0 -1  #从上往下 0 1 2

rpush list four #往另一边往里塞

lpop  #删除左边最后一个元素

rpop #删除右边最后一个元素

lindex #通过下标获取元素

lindex list 1

llen list #获取长度

lrem list 1 obe  #移除指定个数指定值 可以用来表示取消关注

ltrim list 1 2 #通过下标截取指定长度，这个list已经改变，截断了只剩下截取的元素

rpoplpush 移除列表的最后一个元素并添加到新的数组中

exists list #判断list是否存在

lset list 0 abc  #如果存在就更新

linsert list brfore abc  rfg #在abc之前插入rfg
```

## set

```
字符串加不加引号 或者单引号都没影响
sadd myset "hello"

sadd myset "zhengjian"

smembers myset #查看set里的所有值

sismembers myset hello #查看myset是否有元素 hello

scard myset #查看结果数量

srem myset hello #移除指定元素 hello

sranmembers myset #随机找到一个元素

spop myset #随机删除一个元素

smove myset myset2 "hello" #把指定的一个值移动到另一个set集合中


127.0.0.1:6379> SADD key1 a
(integer) 1
127.0.0.1:6379> SADD key1 b
(integer) 1
127.0.0.1:6379> sadd key1 c
(integer) 1
127.0.0.1:6379> sadd key2 c
(integer) 1
127.0.0.1:6379> sadd key2 d
(integer) 1
127.0.0.1:6379> SADD key2 f
(integer) 1
127.0.0.1:6379> SDIFF key1 key2 #差集
1) "b"
2) "a"
127.0.0.1:6379> SINTER key1 key2 #交集
1) "c"
127.0.0.1:6379> SUNION key1 key2 #并集
1) "c"
2) "a"
3) "d"
4) "b"
5) "f"
127.0.0.1:6379> 
```

## hash

map集合

```
127.0.0.1:6379> hset myhash field1 zhengjian  #设置
(integer) 1
127.0.0.1:6379> hset myhash field2 wln
(integer) 1
127.0.0.1:6379> hget myhash field1 #获取
"zhengjian"
127.0.0.1:6379> hmset myhash field3 ilove filed4 you #多个
OK
127.0.0.1:6379> hmget myhash field1 field3 #获取多个值
1) "zhengjian"
2) "ilove"
127.0.0.1:6379> hgetall myhash
1) "field1"

hlen myhash #判断长度
hkeys myhash #获取key
hvalues myhash #
hincrby myhash field1 #增加
hsetnx myhash fileld1 eee #如果不存在则设置 如果存在则不能设置 可以用来分布式锁 
```

## zset

在set的基础上增加了一个值 set k1 v1 zset k1 score v1

```
zadd myzset 1 one  #添加一个

zadd myzset 2 two 3 three #添加多个

zrange myzset 0 -1  #获取全部

127.0.0.1:6379> ZADD ages 30 wanglinna
(integer) 1
127.0.0.1:6379> zadd ages 28 zhengjian
(integer) 1
127.0.0.1:6379> zadd ages 27 zhangss
(integer) 1

ZRANGEBYSCORE ages -inf +inf #从小大大排列 负无穷到正无穷
```

# 三种特殊的数据类型

## geospatial 地址位置

```
geoadd china:city 121.47 31.23 shanghai

geoadd china:city 120.16 30.24 hangzhou

127.0.0.1:6379> GEODIST china:city hangzhou shanghai
"166761.2770"
127.0.0.1:6379> GEODIST china:city hangzhou shanghai km
"166.7613"

georadius china:city 110 30 500 km

georadius china:city 110 30 500 km withdist #显示到中心位置的距离
```

## Hyperloglog

什么是基数 ？两个数组中不重复的元素

网页的uv(不同的人访问数)

优点占用内存小

## Bitmaps

位存储 只有 0 1

可以用来签到

setbit

# 事务

## 基础操作

Redis事务的本质:一组命令的集合！ 一个事务中的所有命令都会被序列化，在事务执行的过程中，会按照顺序执行

一次性，顺序性，排他性，执行一些列的命令

-----队列 set set set 队列--------

Redis单条命令保存原子性，但是事务不保证原子性

redis的事务

开启事务 （multi)

命令入队

执行事务 (exec)

正常执行事务

```
127.0.0.1:6379> multi
OK
127.0.0.1:6379> set k1 a1
QUEUED
127.0.0.1:6379> set k2 a2
QUEUED
127.0.0.1:6379> get k1
QUEUED
127.0.0.1:6379> set k3 a3
QUEUED
127.0.0.1:6379> exec
1) OK
2) OK
3) "a1"
4) OK
127.0.0.1:6379> 
```

放弃事务 discard

## 两种异常情况：

1.编译型异常 事务中所有命令都不执行

```
127.0.0.1:6379> multi
OK
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> getset k2
(error) ERR wrong number of arguments for 'getset' command
127.0.0.1:6379> get k1
QUEUED
127.0.0.1:6379> exec
(error) EXECABORT Transaction discarded because of previous errors.
127.0.0.1:6379> get k1
(nil)
127.0.0.1:6379> 
```

2.运行时异常，出错的命令不执行，其他照常执行

```
127.0.0.1:6379> multi
OK
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> incr k1
QUEUED
127.0.0.1:6379> get k2
QUEUED
127.0.0.1:6379> exec
1) OK
2) OK
3) (error) ERR value is not an integer or out of range
4) "v2"
```

## 乐观锁

悲观锁：很悲观，认为无论做什么都会出错，每一次都要加锁

乐观锁：很乐观，认为大概率不会出错，cas compare and swap,mysql加个version来比较

redis里的watch就是乐观锁

```
127.0.0.1:6379> watch money
OK
127.0.0.1:6379> set money 100
OK
127.0.0.1:6379> set out 0
OK
127.0.0.1:6379> multi
OK
127.0.0.1:6379> DECRBY money 2
QUEUED
127.0.0.1:6379> INCRBY out 2
QUEUED
127.0.0.1:6379> exec
(nil)
127.0.0.1:6379> 
#此时用另一个线程，再起一个rediscli,改变money的值，就是nil
再操作一遍就成功了， unwatch再执行money操作

127.0.0.1:6379> UNWATCH
OK
127.0.0.1:6379> WATCH money
OK
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379> DECRBY money 5
QUEUED
127.0.0.1:6379> INCRBY out 5
QUEUED
127.0.0.1:6379> exec
1) (integer) 990
2) (integer) 10
127.0.0.1:6379> 
```

# springBoot整合

在springBoot2.x后，原来的jredis被替换为了letture

jedis：采用直连，多个线程操作的话，是不安全的，使用jredis pool连接池 更像BIO

lettuce:采用netty,实例可以再多个线程中进行共享，不存在线程不安全的情况，可以减少线程数据了 更像NIO

整合测试下

1.D:\maven-repository\org\springframework\boot\spring-boot-autoconfigure\2.3.2.RELEASE\spring-boot-autoconfigure-2.3.2.RELEASE.jar!\META-INF\spring.factories

2.找到redis的auto

3.RedisAutoConfiguration

4.再看注解进入RedisProperties.class

源码分析

```java
@Bean
@ConditionalOnMissingBean(name = "redisTemplate") //可以自己设置一个redisTemplate
public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
      throws UnknownHostException {
    //redis的对象都需要序列化 -  c            https://blog.csdn.net/weixin_30298497/article/details/97903700 
    //Object需要强转
   RedisTemplate<Object, Object> template = new RedisTemplate<>();
   template.setConnectionFactory(redisConnectionFactory);
   return template;
}

@Bean
@ConditionalOnMissingBean  //由于string是最常用的，单独拎出来一个
public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory)
      throws UnknownHostException {
   StringRedisTemplate template = new StringRedisTemplate();
   template.setConnectionFactory(redisConnectionFactory);
   return template;
}
```

配置时配jedis已经无效了，没有注入成功

廖雪峰的教程发现是 去掉了redsitemplate,直接自己封装 生菜

https://www.jianshu.com/p/ec42f149e2a5



所有的对象都需要序列化，才能存入redis

因为默认的jdk的序列化是乱码的，所以一般会使用自己配置的

然后封装个客户端来使用



# 配置文件

```bash
# include /path/to/local.conf
# include /path/to/other.conf
类似 import

################################## NETWORK #####################################
bind 0.0.0.0 #虚拟机访问需要修改
protected-mode yes #保护模式
port 6379 #端口设置

################################# GENERAL #####################################
daemonize yes #以守护进程的方式运行，默认是no,我们需要自己开启为yes

# nothing bad happens, the server will start and run normally.
pidfile /var/run/redis_6379.pid #如果以后台的方式运行，我们需要指定一个pid文件

# Specify the server verbosity level.
# This can be one of:
# debug (a lot of information, useful for development/testing)
# verbose (many rarely useful info, but not a mess like the debug level)
# notice (moderately verbose, what you want in production probably)
# warning (only very important / critical messages are logged)
loglevel notice #日志级别


# Specify the log file name. Also the empty string can be used to force
# Redis to log on the standard output. Note that if you use standard
# output for logging but daemonize, logs will be sent to /dev/null
logfile ""   #日志文件名

databases 16 #数据库的数量

always-show-logo yes  #是否总是显示logo

#快照
在规定的时间内，执行了多少次操作，则会持久化到.rdb.aof
redis是内存数据库，如果没有持久化，那么数据断电即失
#如果900秒内，如果至少有1个key进行了修改，我们进行持久化操作
save 900 1
#如果300秒内，如果至少有10个key进行了修改，我们进行持久化操作
save 300 10
#如果60秒内，如果至少有10000个key进行了修改，我们进行持久化操作
save 60 10000

stop-writes-on-bgsave-error yes  #持久化如果出错，是否还要继续工作

rdbcompression yes #是否压缩rdb文件，需要消耗一些cpu资源

rdbchecksum yes #保存rdb文件的时候，进行错误的检查和效验

dir ./

#replication复制，我们后面讲解主从复制，时候再进行讲解


################################### security ####################################
rdbcompression yes
127.0.0.1:6379> ping
PONG
127.0.0.1:6379> config get requirepass #获取redis的密码
1) "requirepass"
2) ""
127.0.0.1:6379> config set requirepass 1245 #设置密码

################################### CLIENTS ####################################
maxclient 1000 #设置能连接上redis的最大客户数
maxmemory <byte> #redis 配置最大的内存容量
maxmemory-policy noeviction #内存到达上限之后的处理策略
#设置方式
config set maxmemory-policy volatile-lru 
maxmemory-policy 六种方式
1、volatile-lru：只对设置了过期时间的key进行LRU（默认值） 
2、allkeys-lru ： 删除lru算法的key   
3、volatile-random：随机删除即将过期key   
4、allkeys-random：随机删除   
5、volatile-ttl ： 删除即将过期的
6、noeviction ： 永不过期，返回错误 

############################## APPEND ONLY MODE ###############################
持久化配置
appendonly no #默认不开启aof,默认rdb，大部分情况下rdb够用
# The name of the append only file (default: "appendonly.aof")
appendfilename "appendonly.aof" 
appendsync everysec #每秒执行一次sync,可能会丢失1s的数据

```

#  Redis持久化

在指定的时间间隔内将数据集体写入快照，也就是行话的快照，它恢复时将文件直接读到内存中

默认就是RDB, 优点是搞笑，缺点是最后一次持久化的数据可能会丢失

## RDB

rdb触发机制

1.save规则满足的情况下，会自动触发rdb

2.执行flushall命令，也会触发rdb规则

3.退出redis,也会产生rdb文件！

备份就自动生成一个dump.rdb文件

如何恢复rdb文件

1.只需要将rdb文件放在我们redis的启动目录就可以，redis的启动的时候会自动检查dump.rdb恢复其中数据

2.查看需要存放的位置

config get dir

#如果在这个目录下存在dump.rdb文件 启动就会自动恢复数据

优点：1.适合大规模的数据恢复  dump.rdb

​			2.对数据完整性要求不高

缺点：1.需要一定的时间间隔，如果redis意外宕机，最后一次修改数据就没了

​			2.fork进程的时候，会占用一定内存



## AOF

append only file

将我们的所有命令都记录下来，history，恢复的时候就是把这个文件的所有命令都再执行一遍

以日志的形式来记录每个操作，将redis执行过的所有命令记录下来，只许追加文件但不可以修改

redis重启的话就根据日志文件的内容将指令从前到后执行一次以完成数据的恢复

默认是不开启的 只要 appendonly yes 就可以了

当  append.aof 被恶意修改后，可以用 redis-check-aof fixed 修复

重启redis 

#appendsync always #每次修改都会 sync,消耗性能

appendsync everysec #每秒执行一次sync,可能会丢失1s的数据

#appendsync no     #不执行sync,这个时候操作系统自己同步数据，速度最快

优点：

1.每一次修改都同步，文件的完整会更加好

2.每秒同步一次，可能会丢失一秒的数据

3.从不同步，效率最高

缺点：

1.相对于数据文件，aof远远大于rdb,速度比rdb慢

2.aof运行效率也要比rdb慢



