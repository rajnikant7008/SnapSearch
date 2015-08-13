package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import com.pojo.Cook;

public class CookDaoImpl implements CookDao
{
	
	private JdbcTemplate jdbcTemplate;

	public CookDaoImpl(){
		
	}
	public CookDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void add(Cook cook) {
		String sql="insert into sellersignup(userName,password,name,email,phone,service,ex,charge,negotiable,location) values(?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,cook.getUsername(),cook.getPassword(),cook.getName(),cook.getEmail(),cook.getPhone(),cook.getService(),cook.getEx(),cook.getCharge(),cook.getNegotiable(),cook.getLocation());
		String sql1 = "insert into login(userName,password,flag) values(?,?,?)";
		jdbcTemplate.update(sql1,cook.getUsername(),cook.getPassword(),1);
	}
	
	public String getPassword(String username)
	{
		
		String sql = "select password from login where userName=?";
		String result;
		try{
			result = jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
		}
		catch(DataAccessException e)
		{
			result = "null";
		}
		return result;
	}
	
	public List<Cook> listCook(String service,String location)
	{
		
			String sql = "select * from sellersignup where service='"+service+"' AND location='"+location+"'";
			
			List<Map<String, Object>>  mapList= jdbcTemplate.queryForList(sql,new CookMapper());

		      List<Cook> list=new ArrayList<Cook>();
		      for(Map<String,Object> i:mapList){
		          list.add((Cook) i.values());
		      }
			
			return list;
	}
		
		private static  final class CookMapper implements RowMapper<Cook>
		{

			
			public Cook mapRow(ResultSet resultSet,int rowNo) throws SQLException
			{
				Cook cook=new Cook();
				
				cook.setUsername(resultSet.getString(1));
				cook.setName(resultSet.getString(3));
				cook.setEmail(resultSet.getString(4));
				cook.setPhone(resultSet.getInt(5));
				cook.setEx(resultSet.getInt(7));
				cook.setCharge(resultSet.getInt(8));
				cook.setNegotiable(resultSet.getInt(9));
				cook.setPassword(resultSet.getString(2));
				cook.setService(resultSet.getString(6));
				cook.setLocation(resultSet.getString(10));
				
				return cook;
			}
		 }
	}
