package daoAndPreparedStatementExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEmployee {

	public static void main(String[] args) {

		String url = "jdbc:derby://localhost:1527/db1";
		
		try (Connection con = DriverManager.getConnection(url);) {

			//Composition of an SQL statement for deleting an employee with a
			//matching id value that will replace '?' in the statement.
			String sql = "delete from employees where id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			//Replacing the '?' in the SQL statement with the int 100.
			pstmt.setInt(1, 100);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
