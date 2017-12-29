package com.revature.dao;

import java.util.List;

import com.revature.DTO.Employee;
import com.revature.DTO.ReimbursementRequest;
import com.revature.javaBeans.Reimbursement;
import com.revature.javaBeans.ReimbursementType;

public interface DAO 
{
	//Create
	public String createReimbursementRequest(ReimbursementRequest request);
	
	//Read
	public String getUsername(String usr);
	public String getPassword(String pwd);
	public List<Reimbursement> getPendingReimbursementRequestsById(int empId);
	public List<Reimbursement> getReimbursementRequestByEmployee(int empId);
	public List<Reimbursement> getResolvedReimbursementRequestsByEmployee(int emdId);
	public List<Reimbursement> getAllPendingReimbursements();
	public List<Reimbursement> getAllResolvedReimbursements();
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int id);
	public Employee getEmployeeByUsernameAndPassword(String username,String password);
	public List<ReimbursementType> getReimbursementTypes();


	//Update
	public int resolveReimbursementRequest(int managerId,int requestID,int answer);
	public int updateProfile(Employee emp);
	public int submitReceiptImage(); //not sure how to implement this yet, will update later
	//Delete
	//Delete is outside the scope of this project


}
