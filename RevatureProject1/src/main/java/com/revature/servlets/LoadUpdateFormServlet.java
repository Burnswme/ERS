package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/loadUpdateForm")
public class LoadUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoadUpdateFormServlet() 
	{
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("features/employeeView/updateEmployeeProfileForm.html").forward(request,response);
	}


}
