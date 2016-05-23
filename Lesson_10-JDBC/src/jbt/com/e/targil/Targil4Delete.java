package jbt.com.e.targil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Targil4Delete {

	public static void main(String[] args) {

		String url = "jdbc:derby://localhost:1527/db1";
		try (Connection con = DriverManager.getConnection(url);) {

			String sql = "delete from employees where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 100);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
