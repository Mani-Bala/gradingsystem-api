package com.revature.gradingsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.gradingsystem.controller.LoginController;

public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		String json = new LoginController().userLogin(name, password);

		// write the json as a response
		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();
	}

}
