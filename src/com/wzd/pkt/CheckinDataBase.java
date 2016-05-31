package com.wzd.pkt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class CheckinDataBase extends HttpServlet {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/reg_schema";
	private static final String DB_USER_NAME = "root";
	private static final String DB__PASSWORD = "123456";

	private static final String SQL_TABLE_IS_EXIST = "SHOW TABLES LIKE 'person';";
	private static final String SQL_TABLE_CREATE = 	  "create table person("
													+ "id int(6)  NOT NULL AUTO_INCREMENT ,"
													+ "name varchar(20)," 
													+ "age int(2)," 
													+ "sex varchar(6),"
													+ "job varchar(50)," 
													+ "birthday varchar(20),"
													+ "primary key(id)"
													+ ")auto_increment=1;";
	
	private static Connection connection = null;
	private static Statement statement = null;
	
	private static String sqlString;
	
	static{

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

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				if(!statement.isClosed()){
					statement.close();
					statement = null;
				}
				if(!connection.isClosed()){
					connection.close();
					connection = null;
				}
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		
		//close statement and connection.
		try {
			if(!statement.isClosed()){
				statement.close();
				statement = null;
			}
			if(!connection.isClosed()){
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 9022816743809669853L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doExecute(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doExecute(request,response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void doExecute(HttpServletRequest request,HttpServletResponse response){
		
		//如果connection为null，直接不处理。
		if (connection == null) {
			return;
		}
		
		log("=================encode(before set):"+request.getCharacterEncoding()+"======================");
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		log("=================encode(after set):"+request.getCharacterEncoding()+"======================");
		
		sqlString = "insert into person (name,age,sex,job,birthday) values(?,?,?,?,?);";
		
		//Get all info to array.
		String[] nameStrings = request.getParameterValues("name");
		String[] ageStrings = request.getParameterValues("age");
		String[] sexStrings = request.getParameterValues("sex"); 
		String[] jobStrings = request.getParameterValues("job");
		String[] birthdayStrings = request.getParameterValues("birthday");
		
		int saveCnt = nameStrings.length;
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			for(int i=0;i<saveCnt;i++){
				preparedStatement.setString(1, nameStrings[i]);
				preparedStatement.setInt(2, Integer.parseInt(ageStrings[i]));
				preparedStatement.setString(3, sexStrings[i]);
				log("===============sex:"+sexStrings[i]+"===============");
				preparedStatement.setString(4, jobStrings[i]);
				preparedStatement.setString(5, birthdayStrings[i]);

				int row = preparedStatement.executeUpdate();
				if(row>0){
					log("========insert sql success:"+row+"==========");
				}else{
					log("========insert sql false==========");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		sqlString = "";
		
	}

}
