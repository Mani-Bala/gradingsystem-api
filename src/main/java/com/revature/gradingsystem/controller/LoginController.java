package com.revature.gradingsystem.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.model.UserDetails;
import com.revature.gradingsystem.service.AdminService;
import com.revature.gradingsystem.service.UserService;
import com.revature.gradingsystem.validator.UserValidator;

public class LoginController {

	public String userLogin(String name, String password)
	{
		UserValidator uservalidator1 = new UserValidator();
		UserService userservice = new UserService();

		UserDetails userdetail = null;
		String errMessage = "";
		
		try {
			uservalidator1.Login(name, password);
			userdetail = userservice.userLogin(name, password);
		} catch (ValidatorException e) {
			errMessage = e.getMessage();
		} catch (ServiceException e) {
			errMessage = e.getMessage();
		}
		//prepare JSON obj
		String json = null;
		Gson gson = new Gson();
		if(userdetail != null)
		{
			json = gson.toJson(userdetail);
		} else if(userdetail == null){
			JsonObject jsonObj = new JsonObject();
			jsonObj.addProperty("errorMessage", errMessage);
			json = jsonObj.toString();
		}
		return json;	
	}

	public String adminLogin(String name, String password) {

		UserValidator uservalidator = new UserValidator();
		AdminService adminService = new AdminService();

		UserDetails userdetail = null;
		String errMessage = "";
		
		try {
			uservalidator.Login(name, password);
			userdetail = adminService.adminLogin(name, password);
		} catch (ValidatorException e) {
			errMessage = e.getMessage();
		} catch (ServiceException e) {
			errMessage = e.getMessage();
		}
		
		String json = null;
		Gson gson = new Gson();
		if(userdetail != null)
		{
			json = gson.toJson(userdetail);
		} else if(userdetail == null){
			JsonObject jsonObj = new JsonObject();
			jsonObj.addProperty("errorMessage", errMessage);
			json = jsonObj.toString();
		}
		return json;
	}
}
