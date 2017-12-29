package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.javaBeans.Reimbursement;
import com.revature.services.EncomServices;

@WebServlet("/getEmployeeName")
/*
 * This servlet loads the user acknowledgement
 */
public class GetEmployeeNameServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public GetEmployeeNameServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String name = session.getAttribute("firstname") + " " + session.getAttribute("lastname");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(name);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(json);
	}

}
