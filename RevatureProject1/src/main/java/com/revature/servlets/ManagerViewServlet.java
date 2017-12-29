package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerViewServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public ManagerViewServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("ManagerViewServlet -GET");
		request.getRequestDispatcher("features/managerView/managerView.html").forward(request, response);
	}

}
