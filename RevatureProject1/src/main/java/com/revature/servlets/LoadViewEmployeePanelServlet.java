package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/loadViewEmployeePanel")
public class LoadViewEmployeePanelServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public LoadViewEmployeePanelServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("features/managerActions/managerViewEmployeesTable.html").forward(request,response);
	}


}
