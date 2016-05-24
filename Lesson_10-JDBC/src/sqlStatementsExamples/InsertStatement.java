package sqlStatementsExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertStatement {

	public static void main(String[] args) {

		String url = "jdbc:derby://localhost:1527/db1";

		try (Connection con = DriverManager.getConnection(url);) {
			System.out.println("connected");

			//The Statement class is used to to send SQL queries to the DB and
			//retrieve data from it. The createStatement() method returns a
			//Statement object.
			Statement stmt = con.createStatement(); //Throws 'SQLException'.

			//A String of an SQL statement that inserts a new line to the table in the database.
			//Since the first column is a key value, if a row with the same key value will be inserted,
			//it will throw a 'SQLIntegrityConstraintViolationException'.
			String sql = "INSERT INTO employees VALUES(6, 'fff', 'fff', '1988-03-25', 'PR')";
			
			//The 'executeUpdate()' method executes the given SQL statement as the parameter,
			//which may be an INSERT, UPDATE, or DELETE statement or an SQL statement that
			//returns nothing. Returns an int of the number of records that were updated.
			//Cannot be called on a PreparedStatement or CallableStatement.
			//If a table with the same name already exists it will throw 'SQLTransactionRollbackException'.
			int rows = stmt.executeUpdate(sql); //Throws 'SQLException'.
			
			System.out.println(sql);
			
			System.out.println(rows + " rows changed");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("disconnected");
	}

}
