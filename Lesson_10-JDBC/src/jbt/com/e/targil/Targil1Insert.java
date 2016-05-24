package jbt.com.e.targil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import classes.Employee;
import classes.Employee.Department;

public class Targil1Insert {

	public static void main(String[] args) {
		
		String url = "jdbc:derby://localhost:1527/db1";
		try(Connection con = DriverManager.getConnection(url);) {
			
			// create a gregorian calendar set to a specific data
			GregorianCalendar cal = new GregorianCalendar(1960, Calendar.MARCH, 22);
			// get the machine time (long) from the calendar
			long ts = cal.getTimeInMillis();
			// create a java.util.Date set to the ts (long)
			java.util.Date birthdate = new java.util.Date(ts);
			
			Employee emp = new Employee(100, "David", "Cohen", birthdate, Department.LEGAL);
			
			String sql = "insert into employees values(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, emp.getId());
			pstmt.setString(2, emp.getFirst());
			pstmt.setString(3, emp.getLast());
			// convert the java.util.Date to java.sql.Date
			pstmt.setDate(4, new java.sql.Date(emp.getBirthDate().getTime()));
			pstmt.setString(5, emp.getDepartment().toString());
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
