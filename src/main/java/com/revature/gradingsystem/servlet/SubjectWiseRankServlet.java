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
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.model.StudentMark;
import com.revature.gradingsystem.service.UserService;

/**
 * Servlet implementation class SubjectWiseRankServlet
 */
public class SubjectWiseRankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get Input
		String subCode = request.getParameter("subjectCode");
		System.out.println("Subject :" + subCode);

		// call DAO
		List<StudentMark> list = null;
		String errorMessage = "";
		String status = "";
		try {
			list = new UserService().findBySubjectCodeService(subCode);
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

		// write the json as a response
		PrintWriter out = response.getWriter();
		out.write(json);
		System.out.println(out);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//doGet(request, response);
	}

}
