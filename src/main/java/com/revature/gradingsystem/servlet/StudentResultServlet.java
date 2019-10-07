package com.revature.gradingsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.gradingsystem.dto.StudentGradeDTO;
import com.revature.gradingsystem.model.StudentMark;
import com.revature.gradingsystem.service.UserService;
import com.revature.gradingsystem.validator.StudentValidator;

public class StudentResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get Input
				String regNo = request.getParameter("regno");
				System.out.println("REG-NO :"+regNo);
				
				int regno = Integer.parseInt(regNo);
				
				List<StudentMark> markList = null;
				StudentGradeDTO studentResult = null;
				JsonObject obj = new JsonObject();
				String json ="";
				
				try {
					
					// Reg-No validator
					StudentValidator studentValidate = new StudentValidator();
					studentValidate.isRegnoExistService(regno);
					
					//get the StudentName, Average, Grade
					studentResult = new UserService().getStudentResult(regno);
					
					//get the Marks and Sub-Code
					markList = new UserService().getStudentMarks(regno);
					
					Gson gson = new Gson();
					String json1 = gson.toJson(markList);
					
					String json2 = gson.toJson(studentResult);
					
					json = "{\"marks\"" +":"+ "" + json1+ ", \"SD\""+":" + "" + json2+ "}"; 
					//convert list to json
					
				} catch (Exception e) {
					obj.addProperty("errMsg",e.getMessage());
					json = obj.toString();
				}
				
				System.out.println(json);
				//write the json as a response
				PrintWriter out = response.getWriter();
				out.write(json);
				out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
