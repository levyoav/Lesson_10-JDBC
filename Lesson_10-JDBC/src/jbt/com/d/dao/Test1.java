package jbt.com.d.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.derby.jdbc.ClientDataSource;

import classes.Employee;

public class Test1 {
	public static void main(String[] args) {
		// create an employee
		Employee emp = new Employee();
		emp.setId(5);
		emp.setBirthdate(new GregorianCalendar(1945, Calendar.FEBRUARY, 23)
				.getTime());
		emp.setDepartment(Employee.Department.FINANCE);
		emp.setFirst("John");
		emp.setLast("Bryce");

		String sql = "INSERT INTO employees VALUES(" + emp.getId() + ", '"
				+ emp.getFirst() + "', '" + emp.getLast() + "', '"
				+ new Date(emp.getBirthdate().getTime()) + "', '"
				+ emp.getDepartment() + "')";

		System.out.println(sql);

		// using the create method to add an employee to the database
		ClientDataSource ds = new ClientDataSource();
		ds.setDatabaseName("db1");

		try (Connection con = ds.getConnection();) {
			EmployeeDAO dao = new EmployeeDbDAO();
			dao.create(con, emp);

		} catch (SQLException | DAOException e) {
			e.printStackTrace();
		}
	}

}
