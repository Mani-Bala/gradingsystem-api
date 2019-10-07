package com.revature.gradingsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.model.UserDetails;
import com.revature.gradingsystem.service.UserService;

public class UpdateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobNo = request.getParameter("mobno");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String subject = request.getParameter("subject");

		Long mobno = Long.parseLong(mobNo);

		UserDetails userDetails = new UserDetails();
		userDetails.setName(name);
		userDetails.setEmail(email);
		userDetails.setMobno(mobno);
		userDetails.setPassword(password);
		userDetails.setRole(role);
		userDetails.setSubject(subject);
		
		String errorMessage = null;
		String status = "";
		try {
			new UserService().updateEmployeeService(userDetails);
			status = "Success";
		} catch (ServiceException e) {
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

		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
