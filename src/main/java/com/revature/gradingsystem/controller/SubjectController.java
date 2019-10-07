package com.revature.gradingsystem.controller;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.model.StudentMark;
import com.revature.gradingsystem.service.UserFeatureService;

public class SubjectController {

	public String subjectWiseRankHolder(String subCode){
		
		List<StudentMark> list = null;
		String errorMessage = "";
		String status = "";
		try {
			list = new UserFeatureService().findBySubjectCodeService(subCode);
			status = "success";
		} catch (ServiceException e) {
			errorMessage = e.getMessage();
		}

		// convert list to json
		String json = "";
		if (status.equals("success")) {
			// convert list to json
			Gson gson = new Gson();
			json = gson.toJson(list);
			System.out.println(list);
		} else {
			JsonObject obj = new JsonObject();
			obj.addProperty("errMessage", errorMessage);
			json = obj.toString();
		}
	return json;	
	}
}
