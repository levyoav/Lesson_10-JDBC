package sqlStatementsExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectStatement {

	public static void main(String[] args) {

		String url = "jdbc:derby://localhost:1527/db1";

		try (Connection con = DriverManager.getConnection(url);) {
			System.out.println("connected");

			//The Statement class is used to to send SQL queries to the DB and
			//retrieve data from it. The createStatement() method returns a
			//Statement object.
			Statement stmt = con.createStatement(); //Throws 'SQLException'.
			
			//A String of an SQL statement that selects all the rows from the table
			//in the database.
			String sql = "SELECT * FROM employees";
			
			//The ResultSet holds the result records of the query to enable the program 
			//to read the data and iterate over the records. The 'executeQuery()' method
			//executes the given SQL statement, which is usually a SELECT statement, and
			//returns a ResultSet object (never null). This method cannot be called on a
			//PreparedStatement or CallableStatement. 
			ResultSet rs = stmt.executeQuery(sql); //Throws 'SQLException'.
			
			//The next() method when first called, places the cursor on the first record
			//and allows reading data from it. Every subsequent call of next() advances the
			//cursor to the next record. Returns true if it points to a record, false if it
			//reached the end.
			while(rs.next()) {
				//Getting the values from the currently pointed row according to the column
				//number and the relevant value type in that row. If there will be an attempt
				//to get a value of a type different than the one that is in the specified
				//row, an 'SQLDataException' will be thrown.
				System.out.print(rs.getInt(1) + ", "); //Getting an int from column 1.
				System.out.print(rs.getString(2) + ", "); //Getting an String from column 2.
				System.out.print(rs.getString(3) + ", "); ////Getting an String from column 3.
				System.out.print(rs.getObject(4) + ", "); //Getting an Object (Date) from column 4.
				System.out.println(rs.getString(5)); //Getting an String from column 5.
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("disconnected");
	}

}
