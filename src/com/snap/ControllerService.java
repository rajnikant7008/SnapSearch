package com.snap;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dao.CookDao;
import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.pojo.Cook;
import com.pojo.User;

@Controller
public class ControllerService
{
	@Autowired
	private UserDaoImpl userDao; 
	
	private CookDao cd;
	//private UserDao ud;
	
	@RequestMapping(value = "addcook.htm", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("cook", new Cook());
		
		return "add";
	}
	
	@RequestMapping(value = "adduser.htm", method = RequestMethod.GET)
	public String addUserController(ModelMap model) {

		model.addAttribute("user", new User());
		
		return "useradd";
	}
	
	@RequestMapping(value = "insertcook.htm", method = RequestMethod.POST)
	public String insertCookData(@ModelAttribute("cook")Cook co,ModelMap model) {
		
		cd.add(co);
		return "add1";
	}
	

//	@RequestMapping(value = "insertuser.htm", method = RequestMethod.POST)
//	public String insertUserData(@ModelAttribute("user")User u,ModelMap model) {
//		
//		ud.add(u);
//		return "add1";
//	}
	
	@RequestMapping(value = "loginpage.htm", method = RequestMethod.GET)
	public String loginController(ModelMap model) {
		
		return "login";
	}
	
	
	@RequestMapping(value = "login.htm", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String pass = cd.getPassword(username);
		ModelAndView mview = new ModelAndView();
		if(pass.equals("null") || !pass.equals(password))
		{
			mview.addObject("MESSAGE", "Invalid Credentials");
			mview.setViewName("error");
		}
		
		if(pass.equals(password))
		{
		
			mview.setViewName("add1");
		}
		
		return mview;
	}
	
	@RequestMapping(value = "search.htm", method = RequestMethod.GET)
	public String searchController(ModelMap model) {
		
		return "search";
	}
	
	
	
	@RequestMapping(value = "searchresult.htm", method = RequestMethod.POST)
	public String searchData(@ModelAttribute("cook")Cook co,ModelMap model) 
	{
		String service = "Cook";
		String location = "Gurgaon";
		List<Cook> list = cd.listCook(service, location);
		
		for(int i=0;i<list.size();i++)
			System.out.println(list.get(i));
		
		
		
		return "add1";
	}

	@Autowired
	public void setCd(CookDao cd) {
		this.cd = cd;
	}
//	@Autowired
//	public void setUd(UserDao ud) {
//		this.ud = ud;
//	}
//	
	
	@RequestMapping(value="insertuser.htm", method = RequestMethod.POST)
	public ModelAndView req(@ModelAttribute User user)
	{
		UserDao userdao = userDao;
		userdao.add(user);
		return new ModelAndView("add1","msg","added");
	}

}
