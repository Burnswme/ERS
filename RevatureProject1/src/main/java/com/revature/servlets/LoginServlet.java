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
import com.revature.DTO.Manager;
import com.revature.dao.EncomDaoImplementation;
import com.revature.services.EncomServices;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() 
    {

    }

	//The login function does not accept GET requests-if one is received, forward back to the login page
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("LoginServlet -GET");
		request.getRequestDispatcher("encomApp.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		System.out.println("LoginServlet -POST");
		//grab parameters
		Map<String,String[]> map = request.getParameterMap();
		
		//get the keyset from the map
		Set<String> set = map.keySet();
		
		//convert JSON object into Java Object
		ObjectMapper mapper = new ObjectMapper();
		
		Object obj = set.toArray()[0];
		Employee emp = mapper.readValue(((String)obj),Employee.class);
		
		int status = 0;

		EncomDaoImplementation dao = new EncomDaoImplementation();

		status = dao.getEmploymentStatusByUsername(emp.getUsername());
		System.out.println(status);
		EncomServices service = new EncomServices();
		
		boolean employeeLoggedIn = service.logIn(emp.getUsername(),emp.getPassword());
		//retrieve the rest of the employee's information
		emp = dao.getEmployeeByUsernameAndPassword(emp.getUsername(),emp.getPassword());
		//store it in the session
		session.setAttribute("username", emp.getUsername());
		session.setAttribute("userId", emp.getEmployeeID());
		session.setAttribute("firstname", emp.getFirstName());
		session.setAttribute("lastname", emp.getLastName());
		
		if(status == 2)
		{
			request.getRequestDispatcher("features/managerView/managerView.html").forward(request,response);
			
		}
		else if(status == 1)
		{
			request.getRequestDispatcher("features/employeeView/employeeView.html").forward(request, response);
		}
		else
		{
			System.out.println("Login failed");
			this.doGet(request,response);
		}

//		System.out.println("LoginServlet -POST");
//		EncomServices serv = new EncomServices();
//		
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		boolean loggedIn = false;
//	    loggedIn = serv.logIn(username, password);
//		
//		if(loggedIn == true)
//		{
//			EncomDaoImplementation dao = new EncomDaoImplementation();
//			int status = dao.getEmploymentStatusByUsername(username);
//			if( status != 0)
//			{
//				if(status == 1)
//				{
//					Employee emp = new Employee();
//					emp = dao.getEmployeeByUsernameAndPassword(username,password);
//					if(emp != null)
//					{	
//						//store the valid user into the session
//						HttpSession session = request.getSession();
//						session.setAttribute("user", emp);
//						
//						//validated user sent to employeeView.html
//						request.getRequestDispatcher("features/employeeView/employeeView.html").forward(request,response);
//					}
//					else
//					{
//						System.out.println("Error in LoginServlet: employee");
//					}
//				
//				}
//				if(status == 2)
//				{
//					Manager man = new Manager();
//					man = dao.getManagerByUsernameAndPassword(username, password);
//					if(man != null)
//					{
//						//store the valid user into the session
//						HttpSession session = request.getSession();
//						session.setAttribute("user", man);
//						
//						//validated user sent to managerView.html
//						request.getRequestDispatcher("features/managerView/managerView.html").forward(request,response);
//					}
//					else
//					{
//						System.out.println("Error in LoginServlet: manager");
//					}
//				}
//			}
//			else
//			{
//				doGet(request,response);
//			}
//		}
//		else
//		{
//			System.out.println("Invalid Username or Password");
//			doGet(request,response);
//		}
//		
		
	}

}
