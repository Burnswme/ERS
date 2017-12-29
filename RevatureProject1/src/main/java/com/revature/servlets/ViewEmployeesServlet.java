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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.DTO.Employee;
import com.revature.services.EncomServices;

@WebServlet("/viewEmployeesServlet")
public class ViewEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewEmployeesServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("ViewEmployeesServlet -GET");
		
		EncomServices services = new EncomServices();
		List<Employee> list = new ArrayList<Employee>();
		list = services.retrieveAllEmployees();
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(json);

		
	}


}
