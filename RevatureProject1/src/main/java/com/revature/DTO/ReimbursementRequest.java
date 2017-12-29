package com.revature.DTO;

import java.io.Serializable;

public class ReimbursementRequest implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3651421765701404497L;

	private String ers_id;
	private String amount;
	private String description;
	private String reimbursement_code;
	private String receipt;
	
	public ReimbursementRequest()
	{
		this.ers_id = "1";
		this.amount = "0.0";
		this.description = "";
		this.reimbursement_code = "1";
	}

	public ReimbursementRequest(String ers_id, String amount, String description, String reimbursement_code) 
	{
		super();
		this.ers_id = ers_id;
		this.amount = amount;
		this.description = description;
		this.reimbursement_code = reimbursement_code;
	}
	

	public ReimbursementRequest(String ers_id, String amount, String description, String reimbursement_code,
			String receipt) 
	{
		super();
		this.ers_id = ers_id;
		this.amount = amount;
		this.description = description;
		this.reimbursement_code = reimbursement_code;
		this.receipt = receipt;
	}

	public String getErs_id() 
	{
		return ers_id;
	}

	public void setErs_id(String ers_id) 
	{
		this.ers_id = ers_id;
	}

	public String getAmount() 
	{
		return amount;
	}

	public void setAmount(String amount) 
	{
		this.amount = amount;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getReimbursement_code() 
	{
		return reimbursement_code;
	}

	public void setReimbursement_code(String reimbursement_code)
	{
		this.reimbursement_code = reimbursement_code;
	}

	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}
	
	public String getReceipt() 
	{
		return receipt;
	}

	public void setReceipt(String receipt) 
	{
		this.receipt = receipt;
	}

	@Override
	public String toString()
	{
		return "ReimbursementRequest [ers_id=" + ers_id + ", amount=" + amount + ", description=" + description
				+ ", reimbursement_code=" + reimbursement_code + ", receipt=" + receipt + "]";
	}


	
	
}
