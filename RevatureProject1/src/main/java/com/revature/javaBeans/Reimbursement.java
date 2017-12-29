package com.revature.javaBeans;

import java.io.Serializable;

public class Reimbursement implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2660273194547345991L;
	
	private int employeeID;
	private int reimbursementID;
	private int status;
	private double amount;
	private String receipt;
	private int managerID;
	private String timeRequested;
	private String timeResolved;
	private String description;
	private int type;
	
	public Reimbursement(int employeeID, int reimbursementID, int status, double amount, int managerID,
			String timeRequested, String timeResolved, String description, int type)
	{
		super();
		this.employeeID = employeeID;
		this.reimbursementID = reimbursementID;
		this.status = status;
		this.amount = amount;
		this.managerID = managerID;
		this.timeRequested = timeRequested;
		this.timeResolved = timeResolved;
		this.description = description;
		this.type = type;
	}
	
	public Reimbursement(int employeeID, int reimbursementID, int status, double amount, String receipt, int managerID,
			String timeRequested, String timeResolved, String description, int type) 
	{
		super();
		this.employeeID = employeeID;
		this.reimbursementID = reimbursementID;
		this.status = status;
		this.amount = amount;
		this.receipt = receipt;
		this.managerID = managerID;
		this.timeRequested = timeRequested;
		this.timeResolved = timeResolved;
		this.description = description;
		this.type = type;
	}

	public Reimbursement()
	{
		this.employeeID = 1;
		this.reimbursementID = 1;
		this.status = 1;
		this.amount = 0.0;
		this.managerID = 1;
		this.timeRequested = "01-01-1970";
		this.timeResolved = "01-01-1970";
		this.description = "";
		this.type = 1;
		
	}
	public int getEmployeeID() 
	{
		return employeeID;
	}
	public void setEmployeeID(int employeeID)
	{
		this.employeeID = employeeID;
	}
	public int getReimbursementID() 
	{
		return reimbursementID;
	}
	public void setReimbursementID(int reimbursementID) 
	{
		this.reimbursementID = reimbursementID;
	}
	public int getStatus() 
	{
		return status;
	}
	public void setStatus(int status) 
	{
		this.status = status;
	}
	public double getAmount() 
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	public int getManagerID() 
	{
		return managerID;
	}
	public void setManagerID(int managerID) 
	{
		this.managerID = managerID;
	}
	public String getTimeRequested() 
	{
		return timeRequested;
	}
	public void setTimeRequested(String timeRequested)
	{
		this.timeRequested = timeRequested;
	}
	public String getTimeResolved() 
	{
		return timeResolved;
	}
	public void setTimeResolved(String timeResolved) 
	{
		this.timeResolved = timeResolved;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public int getType() 
	{
		return type;
	}
	public void setType(int type) 
	{
		this.type = type;
	}
	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}
	public String getReceipt() 
	{
		return receipt;
	}
	public void setReceipt(String receipt) 
	{
		this.receipt = receipt;
	}
	@Override
	public String toString() 
	{
		return "Reimbursement [employeeID=" + employeeID + ", reimbursementID=" + reimbursementID + ", status=" + status
				+ ", amount=" + amount + ", receipt=" + receipt + ", managerID=" + managerID + ", timeRequested="
				+ timeRequested + ", timeResolved=" + timeResolved + ", description=" + description + ", type=" + type
				+ "]";
	}
	

}
