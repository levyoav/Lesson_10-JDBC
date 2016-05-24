package daoAndPreparedStatementExamples;

import classes.Employee;

//Definition of the DAO (Data Access Object) interface.
public interface EmployeeDAO {

	/**
	 * adds an employee to the data source
	 * 
	 * @param obj
	 *            an object that acts as a connection to the data source
	 * @param emp
	 *            the emploee object to add
	 * 
	 * */
	void create(Object obj, Employee emp) throws DAOException;

	/**
	 * 
	 * @param obj
	 * @param emp
	 * @return
	 * @throws DAOException
	 */
	Employee read(Object obj, Employee emp) throws DAOException;

	void update(Object obj, Employee emp) throws DAOException;

	void delete(Object obj, Employee emp) throws DAOException;

}
