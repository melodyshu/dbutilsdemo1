package com.fx;

import org.apache.commons.dbutils.AsyncQueryRunner;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Hello world!
 *
 */
public class App3
{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "123456";
    public static void main( String[] args ) throws SQLException, ExecutionException, InterruptedException {
        Connection conn=null;
        AsyncQueryRunner asyncQueryRunner=new AsyncQueryRunner(Executors.newCachedThreadPool());
        DbUtils.loadDriver(JDBC_DRIVER);
        conn= DriverManager.getConnection(DB_URL,USER,PASS);
        //查询
        //ResultSetHandler对象将结果集映射到Employee对象。
        ResultSetHandler<Employee> resultSetHandler=new BeanHandler(Employee.class);
        Future<Employee> future=null;
        future= asyncQueryRunner.query(conn,"select * from employee where id=?",resultSetHandler,1);
        System.out.println(future.get());
        DbUtils.close(conn);

    }
}
