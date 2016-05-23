package jbt.com.c.usingStatements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo3 {

	public static void main(String[] args) {

		String url = "jdbc:derby://localhost:1527/db1";

		try (Connection con = DriverManager.getConnection(url);) {
			System.out.println("connected");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM employees";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.print(rs.getInt(1) + ", ");
				System.out.print(rs.getString(2) + ", ");
				System.out.print(rs.getString(3) + ", ");
				System.out.print(rs.getObject(4) + ", ");
				System.out.println(rs.getString(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("disconnected");
	}

}
