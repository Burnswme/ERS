package com.revature.DTO;

import java.io.Serializable;

public class Manager implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -719621508185353253L;
	
	private int employeeID;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private final int employmentStatus = 2;
	private String email;
	
	public Manager(int employeeID, String firstName, String lastName, String username, String password, String email) 
	{
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public Manager()
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


}
