package com.fx;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
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
        int insertRecords= queryRunner.update(conn,"INSERT INTO employee(id,name,gender,email,deptid)  VALUES (?,?,?,?,?)",100,"bom",1,"bom@qq.com",103);
        System.out.println("插入记录数:"+insertRecords);
        DbUtils.close(conn);

    }
}
