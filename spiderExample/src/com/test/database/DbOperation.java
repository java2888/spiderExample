package com.test.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbOperation {
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;

	private static void openConnection() {
		try {
			if ((null == connection) || (false == connection.isValid(0))) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// String dataBaseName="ctrip";
				String url = "jdbc:mysql://localhost:3306/ctrip?useUnicode=true&characterEncoding=gbk";
				String user = "root";
				String password = "123456";
				try {
					connection = DriverManager.getConnection(url, user, password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void executeSql(String strSql) {

		try {
			openConnection();//确保数据库连接
			preparedStatement = connection.prepareStatement(strSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void executeDbOperation() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String dataBaseName="ctrip";
		String url = "jdbc:mysql://localhost:3306/ctrip?useUnicode=true&characterEncoding=gbk";
		String user = "root";
		String password = "123456";
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "create table if not exists tbl_city (id integer primary key auto_increment, city_id integer not null, city_name varchar(255) not null, head_pinyin varchar(80) not null, pinyin varchar(255) not null)";
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			boolean flag = preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
