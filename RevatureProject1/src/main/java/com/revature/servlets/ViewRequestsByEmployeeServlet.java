package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.DTO.Employee;
import com.revature.javaBeans.Reimbursement;
import com.revature.services.EncomServices;

@WebServlet("/viewRequestsByEmployee")
public class ViewRequestsByEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewRequestsByEmployeeServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("encomApp.html").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
					
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		List<Reimbursement> list2 = new ArrayList<Reimbursement>();
		
		EncomServices services = new EncomServices();
		
		list = services.retrievePendingReimbursements();
		list2 = services.retrieveResolvedReimbursements();
		
		list.addAll(list2);
		
		if(list != null)
		{
			ObjectMapper mapper2 = new ObjectMapper();
			String json = mapper2.writeValueAsString(list);		
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(json);
		}
		else
		{
			System.out.println("Error in ViewRequestsByEmployeeServlet");
		}
	}

}
