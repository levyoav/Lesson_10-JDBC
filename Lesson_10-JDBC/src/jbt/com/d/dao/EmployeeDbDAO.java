package jbt.com.d.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Employee;

//this implementation works with a database
public class EmployeeDbDAO implements EmployeeDAO {

	@Override
	public void create(Object obj, Employee emp) throws DAOException {

		// using statement
		// String sql = "INSERT INTO employees VALUES(" + emp.getId() + ", '"
		// + emp.getFirst() + "', '" + emp.getLast() + "', '"
		// + new Date(emp.getBirthdate().getTime()) + "', '" +
		// emp.getDepartment() + "')";

		// using prepared statement
		Connection con = (Connection) obj;
		String sql = "INSERT INTO employees VALUES(?,?,?,?,?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, emp.getId());
			pstmt.setString(2, emp.getFirst());
			pstmt.setString(3, emp.getLast());
			pstmt.setDate(4, new Date(emp.getBirthdate().getTime()));
			pstmt.setString(5, emp.getDepartment().toString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("create operation failed", e);
		}

	}

	@Override
	public Employee read(Object obj, Employee emp) throws DAOException {
		Connection con = (Connection) obj;
		String sql = "SELECT * FROM employees WHERE id = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, emp.getId());
			ResultSet rs = pstmt.executeQuery();
			// not completed

		} catch (SQLException e) {
			e.printStackTrace();
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
