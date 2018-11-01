package com.fx;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App2
{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "123456";
    public static void main( String[] args ) throws SQLException {
        Connection conn=null;
        QueryRunner queryRunner=new QueryRunner();
        DbUtils.loadDriver(JDBC_DRIVER);
        conn= DriverManager.getConnection(DB_URL,USER,PASS);
        //查询
        ResultSetHandler<Employee> resultSetHandler=new BeanHandler(Employee.class);
        Employee emp= queryRunner.query(conn,"select * from employee where id=?",resultSetHandler,1);
        System.out.println(emp);
        DbUtils.close(conn);

    }
}
