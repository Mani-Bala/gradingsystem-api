package com.revature.gradingsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.service.AdminService;
import com.revature.gradingsystem.validator.GradeValidator;

public class DefineScoreRangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String grade = request.getParameter("grade");
		String minimum = request.getParameter("min");
		String maximum = request.getParameter("max");

		int max = Integer.parseInt(maximum);
		int min = Integer.parseInt(minimum);

		GradeValidator gradeValidator = new GradeValidator();
		AdminService adminservice = new AdminService();
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
			obj.addProperty("responseMessage", "score range updated");
			json = obj.toString();

		} else {
			JsonObject obj = new JsonObject();
			obj.addProperty("responseMessage", errorMessage);
			json = obj.toString();
		}

		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
	}

}
