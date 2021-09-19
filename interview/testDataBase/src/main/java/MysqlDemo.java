import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class MysqlDemo {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            go();
            String url = "jdbc:mariadb://127.0.0.1:3306/mytest";
            String user = "root";
            String pass = "1234";
            connection = DriverManager.getConnection(url,user,pass);
            System.out.println("远程Mysql连接测试： " + connection);
            System.out.println(connection == null);
            connection.setAutoCommit(false);	//1,开启事务		/true是自动提交&#xff0c;一个语句一个语句执行&#xff0c;设置为false不自动提交。
            statement = connection.createStatement();		//2&#xff0c;打开窗口
            statement.addBatch("update test_bankacc2 set money = money-1 where id = 1");		//3&#xff0c;创建语句&#xff0c;将语句存到对象中&#xff0c;便于批量执行
            //int a = 1/0;
            statement.addBatch("update test_bankacc2 set money = money+'A' where id = 2");

            statement.executeBatch();

            connection.commit();
        } catch (Exception e) {
             try {
                 System.out.println("cccc");
                connection.rollback();	//try中代码如果出现异常&#xff0c;上边提交不成功&#xff0c;一定执行回滚语句。不出现异常会提交成功。
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {

            try {
                if(statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void go() {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession("root", "10.211.55.3", 22);
            session.setPassword("Asd112233");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL("127.0.0.1",3306, "10.211.55.3", 3306);//端口映射 转发

            System.out.println("localhost:" + assinged_port);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //https://blog.csdn.net/earbao/article/details/50216999
}