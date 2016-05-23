package jbt.com.b.createDbAndConnect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectJava7 {

	public static void main(String[] args) {
		//Vendor supplied driver name. The database must exist since there is no
		//'create=true' assertion.
		String url = "jdbc:derby://localhost:1527/cmsdb";

		//Getting a connection within parenthesis of the 'try' block automatically
		//closes the connection weather an exception was thrown or not. Don't need
		//to write a 'finally' block for that.
		try(Connection con = DriverManager.getConnection(url);) {
			System.out.println("connected");

			//The 'DatabaseMetaData' interface is implemented by the driver vendors
			//to let users know the capabilities of a Database Management System (DBMS)
			//in combination with the driver based on JDBC technology ("JDBC driver")
			//that is used with it. The getMetaData() method retrieves a DatabaseMetaData 
			//object that contains metadata about the database to which this Connection 
			//object represents a connection. The metadata includes information about 
			//the database's tables, its supported SQL grammar, its stored procedures, 
			//the capabilities of this connection, and so on.
			DatabaseMetaData dbMeta =  con.getMetaData();

			//Retrieves a description of the tables available in the given catalog. 
			//Only table descriptions matching the catalog, schema, table name and type
			//criteria are returned. They are ordered by TABLE_TYPE, TABLE_CAT, TABLE_SCHEM 
			//and TABLE_NAME.
			ResultSet rs =  dbMeta.getTables("APP", "APP", null, null);

			//The ResultSet cursor initially points to the row before the first row. The first call
			//for rs.next() positions the cursor to the first row. Returns true if it points
			//to a row, false if points to the end of the set.
			while(rs.next()) {
				//Returns the value of the designated column in the current row of this ResultSet
				//object as a String. First column is 1.
				System.out.println(rs.getString(3));
			}

			//Returns the name of this database product.
			System.out.println("product name: " + dbMeta.getDatabaseProductName());

			//Returns the URL for this DBMS.
			System.out.println("db url: " + dbMeta.getURL());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("disconnected");
	}

}
