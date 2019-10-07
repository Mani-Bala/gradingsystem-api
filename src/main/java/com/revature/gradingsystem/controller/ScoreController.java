package com.revature.gradingsystem.controller;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.model.ScoreRange;
import com.revature.gradingsystem.service.AdminService;
import com.revature.gradingsystem.validator.GradeValidator;

public class ScoreController {

	private final GradeValidator gradeValidator = new GradeValidator();
	private final AdminService adminservice = new AdminService();
	
	public String defineScore(String grade, int min, int max)
	{
		String errorMessage = null;
		String status = "";
		try {
			gradeValidator.isGradeExist(grade.toUpperCase(), min, max);

			adminservice.updateScoreRangeService(grade.toUpperCase(), min, max);

			status = "Success";
		} catch (ServiceException e) {
			errorMessage = e.getMessage();
		} catch (ValidatorException e) {
			errorMessage = e.getMessage();
		}
		String json = null;
		// Gson gson = new Gson();
		if (status.equals("Success")) {

			JsonObject obj = new JsonObject();
			obj.addProperty("responseMessage", "success");
			json = obj.toString();

		} else {
			JsonObject obj = new JsonObject();
			obj.addProperty("responseMessage", errorMessage);
			json = obj.toString();
		}
		
		return json;
	}
	
	public String deleteScore(){
		AdminService adminservice = new AdminService();
		String status = "";
		try {
			adminservice.deleteScoreRangeService();
			status = "Score Range Deleted..";
		} catch (DBException e) {
			status= e.getMessage();
		}
	
			JsonObject obj = new JsonObject();
			obj.addProperty("message", status);
			String json = obj.toString();
			
		return json;
	}
	
	public String viewScore(){
		AdminService adminservice = new AdminService();
		List<ScoreRange> list = null;
		String errorMessage = null;
		String status = "";
		try {
			list = adminservice.viewScoreRangeService();
			status = "success";
	
		} catch (ServiceException e) {
			errorMessage = e.getMessage();
		}
	
		String json = null;
		if (status.equals("success")) {
			// convert list to json
			Gson gson = new Gson();
			json = gson.toJson(list);
		} else {
			JsonObject obj = new JsonObject();
			obj.addProperty("errMessage", errorMessage);
			json = obj.toString();
		}
	return json;
	}
}
