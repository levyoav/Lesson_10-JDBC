package daoAndPreparedStatementExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import classes.Employee;
import classes.Employee.Department;

public class InsertEmployee {

	public static void main(String[] args) {

		String url = "jdbc:derby://localhost:1527/db1";
		try(Connection con = DriverManager.getConnection(url);) {

			//Create a Gregorian calendar set to a specific date.
			GregorianCalendar cal = new GregorianCalendar(1960, Calendar.MARCH, 22);

			//Get the machine time (long) in mili seconds from the Gregorian calendar.
			long ts = cal.getTimeInMillis();

			//Create a java.util.Date object corresponding to the number of msec. in 'ts' (long).
			java.util.Date birthdate = new java.util.Date(ts);

			Employee emp = new Employee(100, "David", "Cohen", birthdate, Department.LEGAL);

			String sql = "insert into employees values(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, emp.getId());
			pstmt.setString(2, emp.getFirst());
			pstmt.setString(3, emp.getLast());

			//Convert the java.util.Date to java.sql.Date by getting the time corresponding
			//to the java.util.Date in msec.
			pstmt.setDate(4, new java.sql.Date(emp.getBirthDate().getTime()));
			pstmt.setString(5, emp.getDepartment().toString());

			pstmt.executeUpdate();

			System.out.println("Employee inserted to table.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
