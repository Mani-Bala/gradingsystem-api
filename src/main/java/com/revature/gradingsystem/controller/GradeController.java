package com.revature.gradingsystem.controller;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.gradingsystem.dto.StudentGradeDTO;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.service.UserFeatureService;
import com.revature.gradingsystem.validator.GradeValidator;

public class GradeController {

	public String gradeWiseList() {
	
		List<StudentGradeDTO> list = null;
		String errorMessage = "";
		String status = "";
	
		try {
			list = new UserFeatureService().listOfStudentService();
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
	
	public String SpecficGradeWiseList(String grade) {
		
		List<StudentGradeDTO> list = null;
		String errorMessage = "";
		String status = "";

		try {
			// grade Validation
			GradeValidator gradeValidator = new GradeValidator();
			gradeValidator.gradeCheck(grade.toUpperCase());

			// call Service class and get the result
			list = new UserFeatureService().findByGradeService(grade.toUpperCase());

			status = "success";

		} catch (ServiceException e) {
			errorMessage = e.getMessage();

		} catch (ValidatorException e) {
			errorMessage = e.getMessage();
		}catch (DBException e) {
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
