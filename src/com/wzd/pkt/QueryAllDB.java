package com.wzd.pkt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryAllDB extends HttpServlet {
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
				if (!statement.isClosed()) {
					System.out
							.println("=============static finally=============");
					statement.close();
					statement = null;
				}
				if (!connection.isClosed()) {
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
		
		log("=============QueryAllDB destroy=============");
		
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
	private static final long serialVersionUID = -6662612782659062455L;
	
	

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
		execute(request, response);
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
		execute(request, response);
	}
	
	/**
	 * The execute method of the class.<br>
	 * 
	 * This methos is called by doGet and doPost,when receive a request to query all
	 * data in sql database.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sqlString = "select * from person";
		
		try {
			ResultSet resultSet=null;
			if(statement !=null){
				log("============statement is not null==========");
				resultSet = statement.executeQuery(sqlString);
			}else{
				log("============statement is null==========");
			}
			
			
			if(resultSet.first()){
				log("=========sql中有数据========");
				ArrayList<Person> personList = new ArrayList<Person>();
				do{
					Person person = new Person();
					
					person.setId(resultSet.getInt(1));				//id
					person.setName(resultSet.getString(2));			//name
					person.setAge(resultSet.getInt(3));				//age
					person.setSex(resultSet.getString(4));			//sex
					person.setJob(resultSet.getString(5));			//job
					person.setBirthday(resultSet.getString(6));		//birthday
					
					//add element to list.
					personList.add(person);							
				}while(resultSet.next());
				request.setAttribute("ALL_LIST", personList);
				//ListIterator
				
				request.getRequestDispatcher("/ShowAll.jsp").forward(request, response);
			}else{
				log("=========sql中没有数据========");
				request.getRequestDispatcher("/ShowAll.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
