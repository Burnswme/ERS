package com.revature.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.DTO.Employee;
import com.revature.DTO.Manager;
import com.revature.DTO.ReimbursementRequest;
import com.revature.javaBeans.Reimbursement;
import com.revature.javaBeans.ReimbursementType;

public class EncomDaoImplementation implements DAO
{
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "Encom";
	private String password = "p4ssw0rd";
	
	//Create
	@Override
	public String createReimbursementRequest(ReimbursementRequest request)
	{
		String returnVal = "0";
		
		//first spot is the ers_id, second spot is the amount(entered by the employee)
		//third spot is the description, and fourth spot is the reimbursement type code
		try (Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "INSERT INTO REIMBURSEMENT(ERS_ID,ST_ID,RB_AMOUNT,RB_RECEIPT,SUBMITTED,RB_DESCRIPTION,RBT_ID)VALUES(?,1,?,?,CURRENT_TIMESTAMP,?,?)";

			CallableStatement cs1 = conn.prepareCall(sql);
			cs1.setString(1, request.getErs_id()); //this is the ers_id of the employee
			cs1.setString(2, request.getAmount()); //this is the amount entered by the employee in the form
			byte[] receiptBytes = null;
			if(request.getReceipt() != null)
			{
				try
				{
					receiptBytes = request.getReceipt().getBytes("UTF-8");
				}
				catch(UnsupportedEncodingException e)
				{
					e.printStackTrace();
				}
			}
			//cs1.setString(3, request.getReceipt()); //this is the (optional) image of the receipt submittd by the employee
			cs1.setBytes(3, receiptBytes);
			cs1.setString(4, request.getDescription());//this is the description of the reimbursement request
			cs1.setString(5, request.getReimbursement_code()); //this is the reimbursement code, chosen by the employee
			
			returnVal = String.valueOf(cs1.executeUpdate());

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return returnVal;
	}

	//Read
	@Override
	public String getUsername(String usr) 
	{
		String userId = "";
		try (Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "SELECT ers_username FROM ERS_USER WHERE ers_username = ?";

			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setString(1,usr); //this is the username of the employee

			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) 
			{
				userId = rs1.getString(1);
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return userId;
	}

	@Override
	public String getPassword(String pwd) 
	{
		String userPassword = "";
		try (Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "SELECT ers_password FROM ERS_USER WHERE ers_password = ?";

			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setString(1,pwd); //this is the password of the employee

			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) 
			{
				userPassword = rs1.getString(1);
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return userPassword;
	}

	@Override
	public List<Reimbursement> getPendingReimbursementRequestsById(int empId) 
	{	
		Blob receipt = null;
		String receiptString = null;
		//this retrieves all of an employee's pending reimbursement requests
		
		List<Reimbursement> pending = new ArrayList<Reimbursement>();

		try(Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "SELECT rb_id,ers_id,st_id,rb_amount,rb_receipt,manager_id,submitted,resolved,rb_description,rbt_id FROM REIMBURSEMENT WHERE ers_id = ? AND st_id = 1";
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setString(1,String.valueOf(empId)); //pass the employee id as a String
			
			//order of inserts: rb_id, ers_id, st_id,rb_amount,manager_id,submitted,resolved,rb_description rbt_id
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next())
				do
				{
					Reimbursement rb = new Reimbursement();
					
					int rb_id = rs1.getInt(1);
					int ers_id = rs1.getInt(2);
					int st_id = rs1.getInt(3);
					double rb_amount = rs1.getDouble(4);
					if(rs1.getBlob(5) != null)
					{
						receipt=rs1.getBlob(5);
						byte [] receiptBytes = receipt.getBytes(1, (int)receipt.length());
						try
						{
							receiptString = new String(receiptBytes,"UTF-8");
						}
						catch(UnsupportedEncodingException e)
						{
							e.printStackTrace();
						}
					}
					int manager_id = rs1.getInt(6);
					String submitted = rs1.getString(7);
					String resolved = rs1.getString(8);
					String rb_description = rs1.getString(9);
					int rbt_id = rs1.getInt(10);
					
					rb.setReimbursementID(rb_id);
					rb.setEmployeeID(ers_id);
					rb.setStatus(st_id);
					rb.setAmount(rb_amount);
					rb.setReceipt(receiptString);
					rb.setManagerID(manager_id);
					rb.setTimeRequested(submitted);
					rb.setTimeResolved(resolved);
					rb.setDescription(rb_description);
					rb.setType(rbt_id);
					
					pending.add(rb);
				}
				while(rs1.next());

		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}

		return pending;
	}

	@Override
	public List<Reimbursement> getReimbursementRequestByEmployee(int empId) 
	{
		Blob receipt = null;
		String receiptString = null;
		//this retrieves all of an employee's pending reimbursement requests
		
		List<Reimbursement> pending = new ArrayList<Reimbursement>();

		try(Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "SELECT rb_id,ers_id,st_id,rb_amount,rb_receipt,manager_id,submitted,resolved,rb_description,rbt_id FROM REIMBURSEMENT WHERE ers_id = ?";
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setString(1,String.valueOf(empId)); //pass the employee id as a String
			
			//order of inserts: rb_id, ers_id, st_id,rb_amount,manager_id,submitted,resolved,rb_description rbt_id
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next())
				do
				{
					Reimbursement rb = new Reimbursement();
					
					int rb_id = rs1.getInt(1);
					int ers_id = rs1.getInt(2);
					int st_id = rs1.getInt(3);
					double rb_amount = rs1.getDouble(4);
					if(rs1.getBlob(5) != null)
					{
						receipt=rs1.getBlob(5);
						byte [] receiptBytes = receipt.getBytes(1, (int)receipt.length());
						try
						{
							receiptString = new String(receiptBytes,"UTF-8");
						}
						catch(UnsupportedEncodingException e)
						{
							e.printStackTrace();
						}
					}
					int manager_id = rs1.getInt(6);
					String submitted = rs1.getString(7);
					String resolved = rs1.getString(8);
					String rb_description = rs1.getString(9);
					int rbt_id = rs1.getInt(10);
					
					rb.setReimbursementID(rb_id);
					rb.setEmployeeID(ers_id);
					rb.setStatus(st_id);
					rb.setAmount(rb_amount);
					rb.setReceipt(receiptString);
					rb.setManagerID(manager_id);
					rb.setTimeRequested(submitted);
					rb.setTimeResolved(resolved);
					rb.setDescription(rb_description);
					rb.setType(rbt_id);
					
					pending.add(rb);
				}
				while(rs1.next());

		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}

		return pending;
	}

	@Override
	public List<Reimbursement> getResolvedReimbursementRequestsByEmployee(int emdId) 
	{
		//This method retrieves all resolved (approved or denied) requests by a given employee
		Blob receipt = null;
		String receiptString = null;
		//this retrieves all of an employee's pending reimbursement requests
		
		List<Reimbursement> pending = new ArrayList<Reimbursement>();

		try(Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "SELECT rb_id,ers_id,st_id,rb_amount,rb_receipt,manager_id,submitted,resolved,rb_description,rbt_id FROM REIMBURSEMENT WHERE ers_id = ? AND st_id != 1";
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setString(1,String.valueOf(emdId)); //pass the employee id as a String
			
			//order of inserts: rb_id, ers_id, st_id,rb_amount,manager_id,submitted,resolved,rb_description rbt_id
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next())
				do
				{
					Reimbursement rb = new Reimbursement();
					
					int rb_id = rs1.getInt(1);
					int ers_id = rs1.getInt(2);
					int st_id = rs1.getInt(3);
					double rb_amount = rs1.getDouble(4);
					if(rs1.getBlob(5) != null)
					{
						receipt=rs1.getBlob(5);
						byte [] receiptBytes = receipt.getBytes(1, (int)receipt.length());
						try
						{
							receiptString = new String(receiptBytes,"UTF-8");
						}
						catch(UnsupportedEncodingException e)
						{
							e.printStackTrace();
						}
					}
					int manager_id = rs1.getInt(6);
					String submitted = rs1.getString(7);
					String resolved = rs1.getString(8);
					String rb_description = rs1.getString(9);
					int rbt_id = rs1.getInt(10);
					
					rb.setReimbursementID(rb_id);
					rb.setEmployeeID(ers_id);
					rb.setStatus(st_id);
					rb.setAmount(rb_amount);
					rb.setReceipt(receiptString);
					rb.setManagerID(manager_id);
					rb.setTimeRequested(submitted);
					rb.setTimeResolved(resolved);
					rb.setDescription(rb_description);
					rb.setType(rbt_id);
					
					pending.add(rb);
				}
				while(rs1.next());

		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}

		return pending;
	}
	@Override
	public List<Reimbursement> getAllPendingReimbursements() 
	{
		//this method should retrieve all pending requests by all employees
		Blob receipt = null;
		String receiptString = null;
		//this retrieves all pending reimbursement requests
		
		List<Reimbursement> pending = new ArrayList<Reimbursement>();

		try(Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "SELECT rb_id,ers_id,st_id,rb_amount,rb_receipt,manager_id,submitted,resolved,rb_description,rbt_id FROM REIMBURSEMENT WHERE st_id = 1";
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
				
			//order of inserts: rb_id, ers_id, st_id,rb_amount,manager_id,submitted,resolved,rb_description rbt_id
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next())
				do
				{
					Reimbursement rb = new Reimbursement();
					
					int rb_id = rs1.getInt(1);
					int ers_id = rs1.getInt(2);
					int st_id = rs1.getInt(3);
					double rb_amount = rs1.getDouble(4);
					if(rs1.getBlob(5) != null)
					{
						receipt=rs1.getBlob(5);
						byte [] receiptBytes = receipt.getBytes(1, (int)receipt.length());
						try
						{
							receiptString = new String(receiptBytes,"UTF-8");
						}
						catch(UnsupportedEncodingException e)
						{
							e.printStackTrace();
						}
					}
					int manager_id = rs1.getInt(6);
					String submitted = rs1.getString(7);
					String resolved = rs1.getString(8);
					String rb_description = rs1.getString(9);
					int rbt_id = rs1.getInt(10);
					
					rb.setReimbursementID(rb_id);
					rb.setEmployeeID(ers_id);
					rb.setStatus(st_id);
					rb.setAmount(rb_amount);
					rb.setReceipt(receiptString);
					rb.setManagerID(manager_id);
					rb.setTimeRequested(submitted);
					rb.setTimeResolved(resolved);
					rb.setDescription(rb_description);
					rb.setType(rbt_id);
					
					pending.add(rb);
				}
				while(rs1.next());

		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}

		return pending;
	}
	@Override
	public List<Reimbursement> getAllResolvedReimbursements() 
	{
		//this method should retrieve all resolved(approved or denied) requests by all employees
		
		Blob receipt = null;
		String receiptString = null;
		//this retrieves all resolved reimbursement requests
		
		List<Reimbursement> pending = new ArrayList<Reimbursement>();

		try(Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "SELECT rb_id,ers_id,st_id,rb_amount,rb_receipt,manager_id,submitted,resolved,rb_description,rbt_id FROM REIMBURSEMENT WHERE st_id != 1";
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
				
			//order of inserts: rb_id, ers_id, st_id,rb_amount,manager_id,submitted,resolved,rb_description rbt_id
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next())
				do
				{
					Reimbursement rb = new Reimbursement();
					
					int rb_id = rs1.getInt(1);
					int ers_id = rs1.getInt(2);
					int st_id = rs1.getInt(3);
					double rb_amount = rs1.getDouble(4);
					if(rs1.getBlob(5) != null)
					{
						receipt=rs1.getBlob(5);
						byte [] receiptBytes = receipt.getBytes(1, (int)receipt.length());
						try
						{
							receiptString = new String(receiptBytes,"UTF-8");
						}
						catch(UnsupportedEncodingException e)
						{
							e.printStackTrace();
						}
					}
					int manager_id = rs1.getInt(6);
					String submitted = rs1.getString(7);
					String resolved = rs1.getString(8);
					String rb_description = rs1.getString(9);
					int rbt_id = rs1.getInt(10);
					
					rb.setReimbursementID(rb_id);
					rb.setEmployeeID(ers_id);
					rb.setStatus(st_id);
					rb.setAmount(rb_amount);
					rb.setReceipt(receiptString);
					rb.setManagerID(manager_id);
					rb.setTimeRequested(submitted);
					rb.setTimeResolved(resolved);
					rb.setDescription(rb_description);
					rb.setType(rbt_id);
					
					pending.add(rb);
				}
				while(rs1.next());

		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}

		return pending;
	}

	@Override
	public List<Employee> getAllEmployees() 
	{
		List<Employee> employees = new ArrayList<Employee>();

		try(Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "SELECT ers_id,ers_fn,ers_ln,ers_username,ers_password,ers_email FROM ERS_USER WHERE ERS_RT_ID = 1";
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
			
			
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next())
				do
				{
					Employee em = new Employee();
					em.setEmployeeID(Integer.parseInt(rs1.getString(1)));
					em.setFirstName(rs1.getString(2));
					em.setLastName(rs1.getString(3));
					em.setUsername(rs1.getString(4));
//					em.setPassword(rs1.getString(5));
					em.setEmail(rs1.getString(6));
					employees.add(em);
				}
				while(rs1.next());
	
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	
		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) 
	{
		Employee em = new Employee();
		
		try(Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "SELECT ers_id,ers_fn,ers_ln,ers_username,ers_password,ers_email FROM ERS_USER WHERE ers_id = ?";
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setString(1, String.valueOf(id));
			
			//order of inserts: rb_id, ers_id, st_id,rb_amount,manager_id,submitted,resolved,rb_description rbt_id
			ResultSet rs1 = ps1.executeQuery();
			
			if(rs1.next())
			{
				em.setEmployeeID(rs1.getInt(1));
				em.setFirstName(rs1.getString(2));
				em.setLastName(rs1.getString(3));
				em.setUsername(rs1.getString(4));
				em.setPassword(rs1.getString(5));
				em.setEmail(rs1.getString(6));
			}
			
	
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	
		return em;
	}

	@Override
	public Employee getEmployeeByUsernameAndPassword(String u_name, String p_word) 
	{
		Employee em = new Employee();
		
		try(Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "SELECT ers_id,ers_fn,ers_ln,ers_username,ers_password,ers_email FROM ERS_USER WHERE ers_username = ? AND ers_password = ? ";
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setString(1, u_name);
			ps1.setString(2, p_word);
			
			//order of inserts: rb_id, ers_id, st_id,rb_amount,manager_id,submitted,resolved,rb_description rbt_id
			ResultSet rs1 = ps1.executeQuery();
			
			if(rs1.next())
			{
				em.setEmployeeID(Integer.parseInt(rs1.getString(1)));
				em.setFirstName(rs1.getString(2));
				em.setLastName(rs1.getString(3));
				em.setUsername(rs1.getString(4));
				em.setPassword(rs1.getString(5));
				em.setEmail(rs1.getString(6));
			}
			
	
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	
		return em;
	}
	
	public int getEmploymentStatusByUsername(String u_name)
	{
		int status = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "SELECT ERS_RT_ID FROM ERS_USER WHERE ERS_USERNAME = ?";
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setString(1, u_name);
			
			ResultSet rs1 = ps1.executeQuery();
			
			if(rs1.next())
			{
				status = rs1.getInt(1);
				System.out.println(status);
			}
	    } 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return status;
	}
	public Manager getManagerByUsernameAndPassword(String u_name, String u_pass)
	{
		Manager man = new Manager();
		
		try(Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "SELECT ers_id,ers_fn,ers_ln,ers_username,ers_password,ers_email FROM ERS_USER WHERE ers_username = ? AND ers_password = ? ";
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setString(1, u_name);
			ps1.setString(2, u_pass);
			
			ResultSet rs1 = ps1.executeQuery();
			
			if(rs1.next())
			{
				man.setEmployeeID(rs1.getInt(1));
				man.setFirstName(rs1.getString(2));
				man.setLastName(rs1.getString(3));
				man.setUsername(rs1.getString(4));
				man.setPassword(null);				//saving the password in the server is bad practice
				man.setEmail(rs1.getString(6));
			}
			
	
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return man;
	}

	//Update
	@Override
	public int resolveReimbursementRequest(int managerId,int requestID,int answer) 
	{
		int retVal = 1;
	
		try(Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "UPDATE REIMBURSEMENT SET MANAGER_ID = ?,ST_ID = ?,RESOLVED = CURRENT_TIMESTAMP WHERE RB_ID = ?";
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setString(1, String.valueOf(managerId));
			ps1.setString(2, String.valueOf(answer));
			ps1.setString(3, String.valueOf(requestID));
			
			String result = String.valueOf(ps1.executeQuery());
			
	    } 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	
		return retVal;
	}

	@Override
	public int updateProfile(Employee emp) 
	{
		String result = "";
		int retVal = 0;
		
		try(Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "UPDATE ERS_USER SET ERS_FN = ?,ERS_LN = ?,ERS_USERNAME = ?,ERS_PASSWORD = ?, ERS_RT_ID = '1',ERS_EMAIL = ? WHERE ERS_ID = ?";
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setString(1, emp.getFirstName());
			ps1.setString(2, emp.getLastName());
			ps1.setString(3, emp.getUsername());
			ps1.setString(4, emp.getPassword());
			ps1.setString(5, emp.getEmail());
			ps1.setString(6, String.valueOf(emp.getEmployeeID()));
			
			result = String.valueOf(ps1.executeQuery());
			

	    } 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	
		return retVal;
		
	}

	@Override
	public int submitReceiptImage() 
	{
		return 0;
	}

	@Override
	public List<ReimbursementType> getReimbursementTypes() 
	{
		ArrayList<ReimbursementType> rbt = new ArrayList<ReimbursementType>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password);) 
		{
			String sql = "SELECT rbt_id,rbt_name FROM REIMBURSEMENT_TYPE";
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ResultSet rs1 = ps1.executeQuery();
			
			if(rs1.next())
				do
				{
					ReimbursementType t = new ReimbursementType();
					t.setId(Integer.parseInt(rs1.getString(1)));
					t.setName(rs1.getString(2));
					rbt.add(t);
					
				}while(rs1.next());
	    } 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return rbt;
	}

	//Delete is outside the scope of this project
}
