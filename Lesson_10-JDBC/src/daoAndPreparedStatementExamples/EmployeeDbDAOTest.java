package daoAndPreparedStatementExamples;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.derby.jdbc.ClientDataSource;

import classes.Employee;

public class EmployeeDbDAOTest {
	public static void main(String[] args) {
		//Create an Employee object.
		Employee emp = new Employee();
		emp.setId(5);

		//Setting the birth date of emp by using a GregorianCalendar instance and
		//invoking its getTime() method that returns a Date object corresponding
		//to the GregorianCalendar object.
		emp.setBirthDate(new GregorianCalendar(1945, Calendar.FEBRUARY, 23)
		.getTime());

		//Accessing the enum Department via the Employee class.
		emp.setDepartment(Employee.Department.FINANCE);
		emp.setFirst("John");
		emp.setLast("Bryce");

		//Composing an SQL statement for inserting the Employee object emp to
		//the table 'employees'
		String sql = "INSERT INTO employees VALUES(" + emp.getId() + ", '"
				+ emp.getFirst() + "', '" + emp.getLast() + "', '"
				+ new Date(emp.getBirthDate().getTime()) + "', '"
				+ emp.getDepartment() + "')";

		System.out.println(sql); //Printing the SQL statement.

		//Creating a ClientDataSource object for getting a connection to the DB 'db1'. 
		ClientDataSource ds = new ClientDataSource();
		ds.setDatabaseName("db1");

		try (Connection con = ds.getConnection();) {
			//Creating an instance of EmployeeDbDAO that is referenced by an EmployeeDAO
			//interface type variable.
			EmployeeDAO dao = new EmployeeDbDAO();

			//Using the create() method of the EmployeeDbDAO to add an employee to the database.
			dao.create(con, emp);
			
			//Getting the emp object from the DB and printing the full name.
			dao.read(con, emp);

		//Catching either an SQLException or a DAOException.
		} catch (SQLException | DAOException e) {
			e.printStackTrace();
		}
	}

}
