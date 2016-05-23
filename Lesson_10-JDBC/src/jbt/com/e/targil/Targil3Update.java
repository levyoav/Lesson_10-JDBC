package jbt.com.e.targil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.Employee.Department;

public class Targil3Update {

	public static void main(String[] args) {

		String url = "jdbc:derby://localhost:1527/db1";
		try (Connection con = DriverManager.getConnection(url);) {

			String sql = "update employees set department = ? where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Department.HR.toString());
			pstmt.setInt(2, 100);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
