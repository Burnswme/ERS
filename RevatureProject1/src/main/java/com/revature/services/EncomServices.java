package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.DTO.Employee;
import com.revature.DTO.ReimbursementRequest;
import com.revature.dao.EncomDaoImplementation;
import com.revature.javaBeans.Reimbursement;

public class EncomServices 
{
	EncomDaoImplementation dao = new EncomDaoImplementation();
	
	public boolean logIn(String username,String password)
	{
		boolean loggedIn = false;
		String user = dao.getUsername(username);
		String pass = "";
		if(user.equals(username))
		{
			pass = dao.getPassword(password);
			if(pass.equals(password))
				loggedIn = true;
		}
		return loggedIn;
	}
	public boolean createReimbursementRequest(String ers_id,String amount,String description,String reimbursement_code)
	{
		boolean success = false;
		ReimbursementRequest rr = new ReimbursementRequest(ers_id,amount,description,reimbursement_code);
		String report = dao.createReimbursementRequest(rr);

		if(report.equals("1")); //createReimbursementRequest should return a String with a value of '1'
			success = true;

		return success;
	}
	public boolean createReimbursementRequest(String ers_id,String amount,String description,String reimbursement_code,String reimbursement_receipt)
	{
		boolean success = false;
		ReimbursementRequest rr = new ReimbursementRequest(ers_id,amount,description,reimbursement_code,reimbursement_receipt);
		String report = dao.createReimbursementRequest(rr);

		if(report.equals("1")); //createReimbursementRequest should return a String with a value of '1'
			success = true;

		return success;
	}
	public boolean updateProfile(Employee emp)
	{
		boolean success = false;
		int status = 0;
		status = dao.updateProfile(emp);
		
		if(status == 1)
			success = true;
		
		return success;
	}
	public List<Employee> retrieveAllEmployees()
	{
		List<Employee> list = new ArrayList<Employee>();
		list = new EncomDaoImplementation().getAllEmployees();
		
		return list;
	}
	public List<Reimbursement> retrievePendingReimbursements()
	{
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		list = new EncomDaoImplementation().getAllPendingReimbursements();
		
		return list;
	}
	public List<Reimbursement> retrieveResolvedReimbursements()
	{
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		list = new EncomDaoImplementation().getAllResolvedReimbursements();
		return list;
	}
	public Employee getEmployeeProfile(int empId)
	{
		Employee emp = new Employee();
		emp = new EncomDaoImplementation().getEmployeeById(empId);
		
		return emp;
	}
	public List<Reimbursement> retrievePendingReimbursementsByEmployee(int empId)
	{
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		list = new EncomDaoImplementation().getPendingReimbursementRequestsById(empId);
		
		return list;
	}
	public List<Reimbursement> retrieveResolvedReimbursementsByEmployee(int empId)
	{
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		list = new EncomDaoImplementation().getResolvedReimbursementRequestsByEmployee(empId);
		
		return list;
	}
	public int resolveReimbursementRequest(int managerId,int reimbursementId,int answer) 
	{
		int status = 0;
		status = dao.resolveReimbursementRequest(managerId, reimbursementId, answer);
		
		return status;
	}
}
