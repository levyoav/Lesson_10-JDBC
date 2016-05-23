package classes;

import java.util.Date;

public class Employee {

	private int id;
	private String first;
	private String last;
	private Date birthdate;
	private Department department;

	public Employee() {
		super();
	}

	public Employee(int id, String first, String last, Date birthdate,
			Department department) {
		super();
		this.id = id;
		this.first = first;
		this.last = last;
		this.birthdate = birthdate;
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", first=" + first + ", last=" + last
				+ ", birthdate=" + birthdate + ", department=" + department
				+ "]";
	}

	public enum Department {
		SALES, LEGAL, MARKETING, FINANCE, HR, PR;
	}

}
