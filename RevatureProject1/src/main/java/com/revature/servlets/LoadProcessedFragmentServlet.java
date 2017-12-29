package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/loadProcessed")
public class LoadProcessedFragmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoadProcessedFragmentServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("features/employeeActions/reimbursementProcessedFragment.html").forward(request, response);
	}


}
