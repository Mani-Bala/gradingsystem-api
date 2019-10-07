package com.revature.gradingsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.service.AdminService;

/**
 * Servlet implementation class DeleteScoreRangeServlet
 */
public class DeleteScoreRangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		AdminService adminservice = new AdminService();
		String status = "";
		try {
			adminservice.deleteScoreRangeService();
			status = "success";
		} catch (DBException e) {
			status= e.getMessage();
		}
	
			JsonObject obj = new JsonObject();
			obj.addProperty("message", status);
			String json = obj.toString();

		// write the json as a response
		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();
	}

}
