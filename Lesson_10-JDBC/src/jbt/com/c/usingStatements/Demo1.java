package jbt.com.c.usingStatements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo1 {

	public static void main(String[] args) {

		String url = "jdbc:derby://localhost:1527/db1";

		try (Connection con = DriverManager.getConnection(url);) {
			System.out.println("connected");

			Statement stmt = con.createStatement();
			String sql = "CREATE TABLE employees(id INT PRIMARY KEY, first_name VARCHAR(15), last_name VARCHAR(15), birthdate DATE, department VARCHAR(15))";
			int rows = stmt.executeUpdate(sql);
			System.out.println(rows + " changed");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("disconnected");
	}

}
