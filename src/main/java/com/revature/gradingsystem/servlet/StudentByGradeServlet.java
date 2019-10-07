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
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.service.UserService;
import com.revature.gradingsystem.validator.GradeValidator;

public class StudentByGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get Input
		String grade = request.getParameter("grade");
		System.out.println("Grade :" + grade);

		// call DAO
		List<StudentGradeDTO> list = null;
		String errorMessage = "";
		String status = "";

		try {
			// grade Validation
			GradeValidator gradeValidator = new GradeValidator();
			gradeValidator.gradeCheck(grade.toUpperCase());

			// call Service class and get the result
			list = new UserService().findByGradeService(grade.toUpperCase());

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

		// write the json as a response
		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  // doGet(request, response);
	}

}
