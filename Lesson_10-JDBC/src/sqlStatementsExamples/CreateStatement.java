package sqlStatementsExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStatement {

	public static void main(String[] args) {

		String url = "jdbc:derby://localhost:1527/db1";

		try (Connection con = DriverManager.getConnection(url);) {
			System.out.println("connected");

			//The Statement class is used to to send SQL queries to the DB and
			//retrieve data from it. The createStatement() method returns a
			//Statement object.
			Statement stmt = con.createStatement(); //Throws 'SQLException'.

			//A String of an SQL statement that creates a table in the database.
			String sql = "CREATE TABLE employees(" //Create a table named 'employees'.
					+ "id INT PRIMARY KEY, " //id column of int values as the table key values. 
					+ "first_name VARCHAR(15), " //'first_name' column of Strings.
					+ "last_name VARCHAR(15), " //'last_name' column of Strings.
					+ "birthdate DATE, " //'birthdate' column of Date.
					+ "department VARCHAR(15))"; //'department' column of Strings.

			//The 'executeUpdate()' method executes the given SQL statement as the parameter,
			//which may be an INSERT, UPDATE, or DELETE statement or an SQL statement that
			//returns nothing. Returns an int of the number of records that were updated.
			//Cannot be called on a PreparedStatement or CallableStatement.
			//If a table with the same name already exists it will throw 'SQLTransactionRollbackException'.
			int rows = stmt.executeUpdate(sql); //Throws 'SQLException'.

			System.out.println(rows + " changed");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("disconnected");
	}

}
