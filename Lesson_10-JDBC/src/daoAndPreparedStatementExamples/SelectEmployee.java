package daoAndPreparedStatementExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Employee;
import classes.Employee.Department;

public class SelectEmployee {

	public static void main(String[] args) {

		String url = "jdbc:derby://localhost:1527/db1";
		try (Connection con = DriverManager.getConnection(url);) {

			//Composition of an SQL statement for selecting an employee with a
			//matching id value that will replace '?' in the statement.
			String sql = "select * from employees where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 100); //Replacing the '?' in the prepared statement with 100.

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt(1));
				emp.setFirst(rs.getString(2));
				emp.setLast(rs.getString(3));

				java.sql.Date date = rs.getDate(4);
				emp.setBirthDate(date); //java.sql.Date is converted to java.util.Date.

				//Convert the department string to a Department enum.
				Department department = Department.valueOf(rs.getString(5));
				emp.setDepartment(department);

				System.out.println(emp);
			}else{
				System.out.println("employee not found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
