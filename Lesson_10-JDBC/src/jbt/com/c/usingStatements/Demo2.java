package jbt.com.c.usingStatements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo2 {

	public static void main(String[] args) {

		String url = "jdbc:derby://localhost:1527/db1";

		try (Connection con = DriverManager.getConnection(url);) {
			System.out.println("connected");

			Statement stmt = con.createStatement();
			String sql = "INSERT INTO employees VALUES(6, 'fff', 'fff', '1988-03-25', 'PR')";
			int rows = stmt.executeUpdate(sql);
			System.out.println(sql);
			System.out.println(rows + " rows changed");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("disconnected");
	}

}
