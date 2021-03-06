package createDbAndConnectExamples;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.derby.jdbc.ClientDataSource;

public class ConnectUsingDataSource {

	public static void main(String[] args) {

		ClientDataSource ds = new ClientDataSource();
		ds.setDatabaseName("db1");

		try(Connection con = ds.getConnection();) {
			System.out.println("connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("disconnected");
	}

}
