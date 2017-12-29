package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * This servlet loads the employee homepage
 */
public class EmployeeViewServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public EmployeeViewServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("features/employeeView/employeeView.html").forward(request, response);
	}


}
