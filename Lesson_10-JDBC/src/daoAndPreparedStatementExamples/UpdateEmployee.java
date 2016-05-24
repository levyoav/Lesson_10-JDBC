package daoAndPreparedStatementExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.Employee.Department;

public class UpdateEmployee {

	public static void main(String[] args) {

		String url = "jdbc:derby://localhost:1527/db1";
		try (Connection con = DriverManager.getConnection(url);) {

			//Composition of an SQL statement for updating the department column 
			//of an employee with a matching id value.
			String sql = "update employees set department = ? where id = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);

			//Replacing the first '?' in the SQL statement with the emum 'HR'. 
			pstmt.setString(1, Department.HR.toString());

			//Replacing the second '?' in the SQL statement with the int 100.
			pstmt.setInt(2, 100);

			pstmt.executeUpdate();

			System.out.println("Employee updated.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
