package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/loadAllResolved")
public class LoadAllResolvedServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public LoadAllResolvedServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("features/managerActions/viewAllResolvedRequestsTable.html").forward(request, response);
	}


}
