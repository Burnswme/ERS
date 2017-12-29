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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.javaBeans.Reimbursement;
import com.revature.services.EncomServices;
@WebServlet("/resolvedRequests")
public class ViewResolvedRequestsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public ViewResolvedRequestsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("ViewResolvedRequestsServlet -GET");
		
		EncomServices services = new EncomServices();
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		list = services.retrieveResolvedReimbursements();
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(json);
	}


}
