package com.revature.servlets;

import java.io.BufferedReader;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.DTO.Employee;
import com.revature.DTO.ReimbursementRequest;
import com.revature.dao.EncomDaoImplementation;
import com.revature.javaBeans.Reimbursement;
import com.revature.services.EncomServices;
/*
 * This servlet saves the user's request to the database
 */

@WebServlet("/createReimbursement")
public class CreateReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateReimbursementServlet() 
    {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("encomApp.html").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("CreateReimbursementRequest -POST");
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		
		try 
		{
			String line;
			while((line = reader.readLine()) != null)
			{
				sb.append(line);
			}
		}
		finally
		{
			reader.close();
		}
		
		String jsonString = sb.toString();
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = null;
		try
		{
			jsonObj = (JSONObject)parser.parse(jsonString);
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		
		String amount = String.valueOf(jsonObj.get("amount"));
		String reimbursement_code = (String) jsonObj.get("reimbursement_code");
		String description = (String)jsonObj.get("description");
		String receipt = (String)jsonObj.get("receipt");
		
		String rbType = (reimbursement_code).toString();
		String ers_id = String.valueOf(session.getAttribute("userId"));
		ReimbursementRequest rr = new ReimbursementRequest(ers_id,amount,description,rbType,receipt);
		
		boolean status = new EncomServices().createReimbursementRequest(ers_id, amount, description, rbType, receipt);
		if(status == true)
		{
			request.getRequestDispatcher("features/employeeActions/reimbursementProcessedFragment.html").forward(request, response);
		}
		else
		{
			doGet(request,response);
		}

	}

}
