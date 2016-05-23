package jbt.com.b.createDbAndConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateNewDB {

	public static void main(String[] args) {
		//Vendor supplied driver name.
		String driverName = "org.apache.derby.jdbc.ClientDriver";

		//Loading the driver.
		try {
			Class.forName(driverName);
			System.out.println("driver loaded");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//The URL of the database. 'db1' is the name of the database.
		//'create=true' means if the database does not exist, create it.
		String url = "jdbc:derby://localhost:1527/db1;create=true";

		//Creating a Connection instance.
		Connection con = null;

		try {
			//Returns a connection to the given URL using the DriverManger class.
			//Throws 'SQLException'.
			con = DriverManager.getConnection(url);
			System.out.println("connected to " + url);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//Finally block being done for closing the connection weather an 
			//exception was thrown or not.
			try {
				if (con != null) {
					//Closing the connection. Throws 'SQLException'.
					con.close();
					System.out.println("disconnected");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
