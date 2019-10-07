package com.revature.gradingsystem.controller;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.gradingsystem.dto.StudentGradeDTO;
import com.revature.gradingsystem.model.StudentMark;
import com.revature.gradingsystem.service.UserFeatureService;
import com.revature.gradingsystem.validator.StudentValidator;

public class ResultController {

	public String studentResult(int regno){
		List<StudentMark> markList = null;
		StudentGradeDTO studentResult = null;
		JsonObject obj = new JsonObject();
		String json ="";
		
		try {
			
			// Reg-No validator
			StudentValidator studentValidate = new StudentValidator();
			studentValidate.isRegnoExistService(regno);
			
			//get the StudentName, Average, Grade
			studentResult = new UserFeatureService().getStudentResult(regno);
			
			//get the Marks and Sub-Code
			markList = new UserFeatureService().getStudentMarks(regno);
			
			Gson gson = new Gson();
			String json1 = gson.toJson(markList);
			
			String json2 = gson.toJson(studentResult);
			
			json = "{\"marks\"" +":"+ "" + json1+ ", \"SD\""+":" + "" + json2+ "}"; 
			//convert list to json
			
		} catch (Exception e) {
			obj.addProperty("errMsg",e.getMessage());
			json = obj.toString();
		}
	return json;	
	}
}
