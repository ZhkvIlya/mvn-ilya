package com.kzn.itis;

import com.kzn.itis.db.model.User;
import com.kzn.itis.db.repositories.UserRepository;
import com.kzn.itis.db.repositories.impl.UserRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 */
public class ExampleMain {

	private static Properties pros;
	
    private static final Logger logger = LoggerFactory.getLogger(ExampleMain.class);


    public static void main(String... args) throws SQLException {
    	try {
    	pros = new Properties();
    	InputStream is = ExampleMain.class.getResourceAsStream("/derby.properties");
			pros.load(is);
		} catch (IOException e1) {
			System.out.println("Load failure");
		}
    	
    	//FileInputStream iStream = new FileInputStream(
    	String url = pros.getProperty("connectionUrl");
		// Для вечной БД вместо ":memory..." пишем адрес директории
		// оканчивающийся папкой с именем БД
    	System.out.println("URL "+ url);
		Statement stmnt = null;
		Connection con;
		try {
			con = DriverManager.getConnection(url);
			stmnt = con.createStatement();
			System.out.println("Statement : "+stmnt);
			System.out.println("Connection : "+con);
		} catch (SQLException e) {
			System.out.println("Failure of geting connection and creating statement");
			e.printStackTrace();
		}
		
		String sql = "DROP TABLE Students";
		try {
			stmnt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Drop didn't succed");
		}/**/
		
		sql = "CREATE TABLE Students(" + "id INTEGER not NULL,"
				+ "name VARCHAR(256)," + "middlename VARCHAR(256)," +"lastname VARCHAR(256)," + "age INTEGER," + "PRIMARY KEY(id))";
		try {
			stmnt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Warning:the database wasn't created");
		}
		
		
		
		/*String[] lines = new String[] { "'Airat','A','V',23",
				"'Greg','B','N',24", "'Bheg','J','B',23",
				"'Ferth','F','K',21", "'Trecf','D','O',30",
				"'Daswer','P','A',20", "'Gfdart','K','A',22",
				"'Sawde','A','L',21", "'Kerubs','R','S',22",
				"'Derfji','K','S',23" };*/
		String[] lines = new String[] { "'Airat','A','V',23",
				"'Fufert','B','N',24", "'BAqwe','J','B',23",
				"'Ferth','F','K',21", "'Fugji','K','S',23",
				"'Lukswer','P','A',20", "'Jartart','K','A',22",
				"'Trecf','D','O',30"};
		///*
		for (int i = 0; i < lines.length; i++) {
			sql = "INSERT INTO Students VALUES (" + i+','+ lines[i]+")";
			try {
				int res = stmnt.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println("Insert failure");
			}
		}
		//*/
		sql = "SELECT * FROM Students";
		ResultSet res = null;
		try {
			res = stmnt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			int i = 0;
			while (res.next()) {
				System.out.println(i + " row : " + res.getInt("id") + " "
						+ res.getString("name") + " " + res.getInt("age"));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }
}
