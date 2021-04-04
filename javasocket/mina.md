# 1.服务端入门demo

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.zhengj</groupId>
    <artifactId>minaDemo</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>org.apache.mina</groupId>
            <artifactId>mina-core</artifactId>
            <version>2.1.4</version>
        </dependency>
    </dependencies>

</project>
```

```java
package com.mina;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class MinaServer {

    static int PORT = 7080;
    static IoAcceptor accept = null;

    public static void main(String[] args) {
        try {
            accept = new NioSocketAcceptor();
            //设置编码过滤器
            accept.getFilterChain().addLast("codec",new ProtocolCodecFilter(
                    new TextLineCodecFactory(
                            Charset.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(),
                            LineDelimiter.WINDOWS.getValue()
                    )
            ));
            accept.getSessionConfig().setReadBufferSize(1024);
            accept.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);
            accept.setHandler(new Myhandler());
            accept.bind(new InetSocketAddress(PORT));
            System.out.println("Server ->" +PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
```

```java
package com.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

public class Myhandler extends IoHandlerAdapter {

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("sessionCreated");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("sessionOpened");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("sessionClosed");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("sessionIdle");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String msg = (String)message;
        System.out.println("服务端接收到的数据:"+msg);
        Date date = new Date();
        if("exit".equals(msg)) {
            session.closeNow();
        }
        session.write(date);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("messageSent");
    }
}
```

小结： 1.NIOSocket

​			2.设置编码解码过滤器

​			3.设置一些session属性

​			4.绑定一个端口

​			测试时 用 cmd  telnet 127.0.0.1 7080 来测试，直接打字就能看到后台

​	