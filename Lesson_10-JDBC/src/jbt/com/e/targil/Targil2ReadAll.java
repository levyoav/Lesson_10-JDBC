package jbt.com.e.targil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Employee;
import classes.Employee.Department;

public class Targil2ReadAll {

	public static void main(String[] args) {

		String url = "jdbc:derby://localhost:1527/db1";
		try (Connection con = DriverManager.getConnection(url);) {

			String sql = "select * from employees";
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt(1));
				emp.setFirst(rs.getString(2));
				emp.setLast(rs.getString(3));

				java.sql.Date date = rs.getDate(4);
				emp.setBirthDate(date);

				// convert the department string to an enum
				Department department = Department.valueOf(rs.getString(5));
				emp.setDepartment(department);

				System.out.println(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
