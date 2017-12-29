package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/loadAllPending")
/*
 * This servlet loads the table for holding all pending requests
 */
public class LoadAllPendingServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public LoadAllPendingServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("features/managerActions/viewAllPendingRequestsTable.html").forward(request, response);
	}


}
