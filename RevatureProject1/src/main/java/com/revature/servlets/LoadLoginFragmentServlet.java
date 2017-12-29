package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loadLoginFragment")
public class LoadLoginFragmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoadLoginFragmentServlet() 
    {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("LoadLoginFragmentServlet -GET");
		request.getRequestDispatcher("features/login/loginFragment.html").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("LoadLoginFragmentServlet -POST"); 
		doGet(request, response);
	}

}
