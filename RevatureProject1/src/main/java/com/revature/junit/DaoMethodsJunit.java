package com.revature.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.DTO.Employee;
import com.revature.DTO.Manager;
import com.revature.DTO.ReimbursementRequest;
import com.revature.dao.EncomDaoImplementation;
import com.revature.javaBeans.Reimbursement;

import junit.framework.Assert;

public class DaoMethodsJunit 
{

	@Test
	public void createReimbursementRequestTest() 
	{
		String correct = "1";
		EncomDaoImplementation dao = new EncomDaoImplementation();
		ReimbursementRequest request = new ReimbursementRequest("1","20.00","Purchased billing software","6");
		assertEquals(correct, dao.createReimbursementRequest(request));
	}
	@Test
	public void getUsernameTest()
	{
		EncomDaoImplementation dao = new EncomDaoImplementation();
		String correct = "alan1";
		assertEquals(correct,dao.getUsername(correct));
	}
	@Test
	public void getPasswordTest()
	{
		String correct = "tron";
		EncomDaoImplementation dao = new EncomDaoImplementation();
		assertEquals(correct,dao.getPassword(correct));
	}
	@Test
	public void getPendingReimbursementRequestsByIdTest()
	{
		EncomDaoImplementation dao = new EncomDaoImplementation();
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		int correct = 2;
		assertEquals(correct,dao.getPendingReimbursementRequestsById(1).size());
	}
	@Test
	public void getReimbursementRequestsByEmployeeTest()
	{
		EncomDaoImplementation dao = new EncomDaoImplementation();
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		int correct = 4;
		assertEquals(correct,dao.getReimbursementRequestByEmployee(1).size());
	}
	@Test
	public void getResolvedReimbursementRequestsByEmployeeTest()
	{
		EncomDaoImplementation dao = new EncomDaoImplementation();
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		int correct = 2;
		assertEquals(correct,dao.getResolvedReimbursementRequestsByEmployee(1).size());
	}
	@Test
	public void getAllPendingReimbursementsTest()
	{
		EncomDaoImplementation dao = new EncomDaoImplementation();
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		int correct = 3;
		assertEquals(correct,dao.getAllPendingReimbursements().size());
	}
	@Test
	public void getAllResolvedReimbursementsTest()
	{
		EncomDaoImplementation dao = new EncomDaoImplementation();
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		int correct = 5;
		assertEquals(correct,dao.getAllResolvedReimbursements().size());
	}
	@Test
	public void getAllEmployeesTest()
	{
		List<Employee> employees = new ArrayList<Employee>();
		EncomDaoImplementation dao = new EncomDaoImplementation();
		int correct = 4;
		assertEquals(correct,dao.getAllEmployees().size());
	}
	@Test
	public void getEmployeeById()
	{
		Employee emp = new Employee(1,"Alan","Bradley","alan1","tron","alan1@gmail.com");
		EncomDaoImplementation dao = new EncomDaoImplementation();
		assertEquals(emp.getFirstName(),dao.getEmployeeById(1).getFirstName());
	}
	@Test
	public void getEmployeeByUsernameAndPasswordTest()
	{
		Employee emp = new Employee();
		emp.setFirstName("Alan");
		emp.setLastName("Bradley");
		EncomDaoImplementation dao = new EncomDaoImplementation();
		assertEquals(emp.getFirstName(),dao.getEmployeeByUsernameAndPassword("alan1", "tron").getFirstName());
		assertEquals(emp.getLastName(),dao.getEmployeeByUsernameAndPassword("alan1","tron").getLastName());
	}
	@Test
	public void getEmploymentStatusByUsernameTest()
	{
		assertEquals(1,new EncomDaoImplementation().getEmploymentStatusByUsername("alan1"));
	}
	@Test
	public void getManagerByUsernameAndPasswordTest()
	{
		Manager man = new Manager();
		man.setFirstName("Ed");
		man.setLastName("Dillinger");
		EncomDaoImplementation dao = new EncomDaoImplementation();
		assertEquals(man.getFirstName(),dao.getManagerByUsernameAndPassword("dillinger1", "sark").getFirstName());
		assertEquals(man.getLastName(),dao.getManagerByUsernameAndPassword("dillinger1", "sark").getLastName());
	}
	

}
