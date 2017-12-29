package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoadLoggedInIndicatorServlet")
public class LoadLoggedInIndicatorServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public LoadLoggedInIndicatorServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("features/homepage/loggedInIndicator.html").forward(request, response);
	}

}
