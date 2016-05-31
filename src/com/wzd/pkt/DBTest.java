package com.wzd.pkt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/reg_schema";
	private static final String DB_USER_NAME = "root";
	private static final String DB__PASSWORD = "123456";

	private static final String SQL_TABLE_IS_EXIST = "SHOW TABLES LIKE 'person';";
	private static final String SQL_TABLE_CREATE = 	  "create table person("
													+ "name varchar(20)," 
													+ "age int(2)," 
													+ "sex varchar(6),"
													+ "job varchar(50)," 
													+ "birthday varchar(20)" 
													+ ");";

	private static String sqlString = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DB_DRIVER);
			System.out.println("driver upload.");

			connection = DriverManager.getConnection(DB_URL, DB_USER_NAME,
					DB__PASSWORD);
			System.out.println("get connection.");

			statement = connection.createStatement();
			System.out.println("create statement.");

			ResultSet resultSet = statement.executeQuery(SQL_TABLE_IS_EXIST);

			if (resultSet.first()) {
				System.out.println("======" + "找到该数据库" + "======");
			} else {
				System.out.println("======" + "没有找到该数据库" + "======");
				statement.executeUpdate(SQL_TABLE_CREATE);
				System.out.println("======" + "创建数据库'person'成功" + "======");

			}

			statement.close();
			System.out.println("close statement.");
			connection.close();
			System.out.println("close connection.");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(!statement.isClosed()){
					statement.close();
				}
				if(!connection.isClosed()){
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
