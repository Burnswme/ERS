package com.revature.javaBeans;

import java.io.Serializable;

public class ReimbursementType implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1049491142614920402L;
	
	private int id;
	private String name;
	
	public ReimbursementType()
	{
		this.id = 1;
		this.name = "food";
	}
	public ReimbursementType(int id, String name) 
	{
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}

	@Override
	public String toString() 
	{
		return "ReimbursementType [id=" + id + ", name=" + name + "]";
	}
	

}
