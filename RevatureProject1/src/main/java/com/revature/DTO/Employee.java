package com.revature.DTO;

import java.io.Serializable;

public class Employee implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1732480097476692562L;
	
	private int employeeID;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private final int employmentStatus = 1;
	private String email;
	
	public Employee(int employeeID, String firstName, String lastName, String username, String password, String email) 
	{
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
	public Employee(String firstName, String lastName, String username, String password, String email) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}


	public Employee(int employeeID) 
	{
		super();
		this.employeeID = employeeID;
	}

	public Employee()
	{
		this.employeeID = 1;
		this.firstName = "";
		this.lastName = "";
		this.username = "";
		this.password = "";
		this.email = "";
	}
	
	public int getEmployeeID() 
	{
		return employeeID;
	}
	public void setEmployeeID(int employeeID) 
	{
		this.employeeID = employeeID;
	}
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	public String getLastName() 
	{
		return lastName;
	}
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}
	public int getEmploymentStatus() 
	{
		return employmentStatus;
	}
	@Override
	public String toString() 
	{
		return "Employee [employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", username=" + username + ", password=" + password + ", employmentStatus=" + employmentStatus
				+ ", email=" + email + "]";
	}
	

}
