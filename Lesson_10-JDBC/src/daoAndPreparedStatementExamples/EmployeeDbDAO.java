package daoAndPreparedStatementExamples;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Employee;

//This implementation of EmployeeDAO works with a database.
public class EmployeeDbDAO implements EmployeeDAO {

	@Override
	public void create(Object obj, Employee emp) throws DAOException {
		//In the implementation of the 'create()' method the Object obj is casted
		//to be a Connection object. In other implementation the obj could be casted
		//to other types of classes that handle database access.
		Connection con = (Connection) obj;

		//Using a statement. Every statement sent to the DB is precompiled to
		//a PreparedStatement.
		//		String sql = "INSERT INTO employees VALUES(" 
		//						+ emp.getId() + ", '"
		//						+ emp.getFirst() + "', '"
		//						+ emp.getLast() + "', '"
		//						+ new Date(emp.getBirthdate().getTime()) + "', '"
		//						+ emp.getDepartment() + "')";

		//Using a PREPARED STATEMENT. It is being prepared in the beginning of the
		//program and used later with different parameters. It can improve performance
		//dramatically. The variable parameters are marked in  the SQL statement as '?'.
		//The statement is hard coded so the number of desired parameters we wish to use
		//in the prepared statement must be the same number of the '?'. Each '?' has an
		//ordinal number: the first - 1, second - 2 etc. The numbers are actually the
		//number of the columns in the table. So the assigned parameter must be of a type
		//that is compatible to the column in the table as stated using the ordinal number.
		String sql = "INSERT INTO employees VALUES(?,?,?,?,?)";

		//The prepareStatement() method returns a PreparedStatement object of the given SQL statement
		//from the Connection object. Like in Connection, putting prepared statement creation in the 
		//'try' parenthesis makes sure that the prepared statement will be closed weather the operation
		//succeeds or an exception is thrown (no need to do it in the 'finally' block).
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			//Inserting the values into the prepared statement, not to the table.
			pstmt.setInt(1, emp.getId()); //Setting an int value in column 1. 
			pstmt.setString(2, emp.getFirst()); //Setting an String value in column 2. 
			pstmt.setString(3, emp.getLast()); //Setting an String value in column 3.

			//The Date class here is java.SQL.Date, not java.UTIL.Date (see imports). It corresponds to
			//SQL DATE which means it stores years, months and days and is not tied to time zones.
			//The getBirthDate() method returns a java.util.Date object. Then, the 
			//java.util.Date.getTime() method returns a long value, which is the number of milliseconds
			//since January 1, 1970, 00:00:00 GMT represented by this Date object. Finally, the 
			//java.sql.Date CTOR is invoked with that long value, which creates an object representing
			//same date as represented by the java.util.Date returned from getBirthDate() method.
			pstmt.setDate(4, new Date(emp.getBirthDate().getTime()));

			//The getDepartment() returns an enum Department object. The toString() method returns a String
			//representation of the enum object which is stored as a String in the column.
			pstmt.setString(5, emp.getDepartment().toString());

			//The executeUpdate() method executes the SQL statement in the invoking prepared statement. 
			//The statement has to be either INSERT, UPDATE, DELETE or an SQL statement that returns nothing.
			pstmt.executeUpdate();
		} catch (SQLException e) {
			//If an SQLException is thrown, it is caught and a DAOException is thrown with a proper message.
			throw new DAOException("Create operation failed", e);
		}

	}

	@Override
	public Employee read(Object obj, Employee emp) throws DAOException {
		Connection con = (Connection) obj;

		//A composition of an SQL statement that gets all the rows from the table 'employees' where the value
		//in the id column is equal to the value replaced by the '?' in the prepared statement.
		String sql = "SELECT * FROM employees WHERE id = ?";

		PreparedStatement pstmt = null;
		try {
			//Since the prepared statement is not in the 'try' parenthesis like in the create() method, it will
			//have to be closed in a 'finally' block.
			pstmt = con.prepareStatement(sql);

			//Replacing the '?' in the prepared statement with the id value returned from the getId() method. 
			pstmt.setInt(1, emp.getId());

			//The method executeQuery() executes the SQL statement by the invoking prepared statement and
			//returns a ResultSet object containing the result of the SQL query, NEVER NULL.
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				//Returns the value of the designated column in the current row of this ResultSet
				//object as a String. First column is 1. Prints the first and last name of the employee.
				System.out.println(rs.getString(2) + " " + rs.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		//Need a 'finally' block here for closing the prepared statement. 
		} finally {
			try {
				if(pstmt != null) {
					//Releases this Statement object's database and JDBC resources immediately instead 
					//of waiting for this to happen when it is automatically closed. It is generally good 
					//practice to release resources as soon as you are finished with them to avoid tying
					//up database resources. Calling the method close on a Statement object that is already
					//closed has no effect. 
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public void update(Object obj, Employee emp) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object obj, Employee emp) throws DAOException {
		// TODO Auto-generated method stub

	}

}
