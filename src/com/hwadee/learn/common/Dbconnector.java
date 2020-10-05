package com.hwadee.learn.common;

import java.sql.*;
import java.util.Properties;

/**
 * 该类用户建立数据库的连接,和操作
 */
public class Dbconnector {
    //指定需要访问的数据库名称
//	private static final String url="jdbc:mysql://localhost:3306/userinfo?useUnicode=true&characterEncoding=gbk";
    private static final String url = "jdbc:mysql://localhost:3306/userinfo";
    //指定需要访问的数据库的驱动程序的名称
    private static final String driver = "com.mysql.jdbc.Driver";
    //数据库连接的用户名和密码
    private static final String user = "dev";
    private static final String passwd = "dev";


    /**
     * 用于连接数据库
     */
    private Connection connection;

    /**
     * 用于执行数据库SQL语句
     */
    private Statement statement;

    /**
     * 用于存放SQL语句的执行结果
     */
    private ResultSet rs;

    /**
     * 创建一个到指定数据库的连接
     */
    public Dbconnector() {
        Properties props = new Properties();
        props.put("user", user);
        props.put("password", passwd);
//        props.put("useUnicode","true");
//        props.put("characterEncoding","utf-8");
//        props.put("encoding","utf-8");

        try {
            //返回该字串对应的类对象
            Class.forName(driver);
//            System.out.println("准备连接数据库");

            //建立数据库的连接
            connection = DriverManager.getConnection(url, props);

            //创建SQL语句的载体
            statement = connection.createStatement();
        } catch (ClassNotFoundException ex) {                                              //获取类对象失败
            ex.printStackTrace();
            throw new RuntimeException("ClassNotFoundException：Cannot find the database driver classes.");
        } catch (SQLException ex) {                                              //SQL语句构造错误
            ex.printStackTrace();
            throw new RuntimeException("SQLException：Cannot connect to database.");
        }
    }

    /**
     * 获取数据库的连接
     *
     * @return 返回对指定数据库的连接
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * 获取数据库的SQL控制权
     *
     * @return 返回对指定数据库的控制权
     */
    public Statement getStatement() {
        return statement;
    }

    /**
     * 用于执行SQL操作
     *
     * @param query 待执行的SQL语句
     */
    public void executeUpdate(String query) {
        if (connection == null || statement == null) {
            throw new RuntimeException("There is no database to execute the query.");
        }
        try {
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("SQLException: Cannot execute SQL");
        }
    }

    /**
     * 执行SQL查询
     *
     * @param query 待执行的SQL语句
     * @return 查询结果
     */
    public ResultSet executeQuery(String query) {
        if (connection == null || statement == null) {
            throw new RuntimeException("There is no database to execute the query.");
        }
        try {
            rs = statement.executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("SQLException: Cannot execute SQL");
        }
        return rs;
    }

    /**
     * 关闭指定的数据库连接
     */
    public void close() {
        try {
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("SQLException:cannot close database !");
        }
    }

    /**
     * 通过销毁对象方式 关闭数据库
     */
    public void finallize() {
        try {
            rs.close();
            statement.close();
            connection.close();
            super.finalize();
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new RuntimeException("Fatal Error!");
        }
    }
}

