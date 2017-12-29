package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.DTO.Employee;
import com.revature.services.EncomServices;
@WebServlet("/updateEmployeeInformation")
public class UpdateEmployeeInformationServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
  
    public UpdateEmployeeInformationServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("encomApp.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		//grab parameters
		Map<String,String[]> map = request.getParameterMap();
		
		//get the keyset from the map
		Set<String> set = map.keySet();
		
		//convert JSON object into Java Object
		ObjectMapper mapper = new ObjectMapper();
		
		Object obj = set.toArray()[0];
		Employee emp = mapper.readValue(((String)obj),Employee.class);
		EncomServices services = new EncomServices();

		boolean success = false;
		success = services.updateProfile(emp);
		if(success == true)
		{
			request.getRequestDispatcher("features/employeeView/updateSuccessfulFragment.html").forward(request, response);
		}
		else
		{
			doGet(request,response);
		}
	}

}
