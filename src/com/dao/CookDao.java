package com.dao;

import java.util.List;

import com.pojo.Cook;

public interface CookDao 
{

	public void add(Cook cook);
	public String getPassword(String username);
	
//	public Cook getCook(String username);
//	
//	public void delete(String username);
//	
	public List<Cook> listCook(String service,String location);
	
}
