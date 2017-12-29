package com.revature.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.DTO.Employee;
import com.revature.javaBeans.Reimbursement;
import com.revature.services.EncomServices;
@WebServlet("/resolveRequestsServlet")

public class ResolveRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ResolveRequestsServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("encomApp.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		int manager = (int) session.getAttribute("userId");
		//grab parameters
		Map<String,String[]> map = request.getParameterMap();
		
		//get the keyset from the map
		Set<String> set = map.keySet();
		
		//convert JSON object into Java Object
		ObjectMapper mapper = new ObjectMapper();
		
		Object obj = set.toArray()[0];
		Reimbursement re = mapper.readValue(((String)obj),Reimbursement.class);
		EncomServices services = new EncomServices();

		int status = 0;
		status = services.resolveReimbursementRequest(manager, re.getReimbursementID(), re.getStatus());
		
		if(status == 1)
		{
			request.getRequestDispatcher("features/managerView/requestResolvedPanel.html").forward(request, response);
		}
		else
		{
			doGet(request,response);
		}
	}

}
