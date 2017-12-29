package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.DTO.Employee;
import com.revature.javaBeans.Reimbursement;
import com.revature.services.EncomServices;
@WebServlet("/viewProfile")
public class ViewEmployeeProfileServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public ViewEmployeeProfileServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		int empId = (int) session.getAttribute("userId");
		
		System.out.println("ViewEmployeeProfileServlet -GET");
		
		EncomServices services = new EncomServices();
		Employee emp = new Employee();
		emp = services.getEmployeeProfile(empId);
		if(emp != null)
		{
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(emp);
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(json);
		}
		else
		{
			System.out.println("Error in ViewEmployeesServlet");
		}
	}


}
