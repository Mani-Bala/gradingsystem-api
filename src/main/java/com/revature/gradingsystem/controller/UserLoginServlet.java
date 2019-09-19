package com.revature.gradingsystem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.gradingsystem.model.UserDetails;
import com.revature.gradingsystem.service.UserService;
import com.revature.gradingsystem.validator.UserValidator;

public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("username");
		String password = request.getParameter("password");

		UserValidator uservalidator1 = new UserValidator();
		UserService userservice = new UserService();
		UserDetails userdetail = new UserDetails();
		try {
			uservalidator1.Login(name, password);
			userdetail = userservice.userLogin(name, password);

			if (userdetail != null) {
				HttpSession session = request.getSession();
				session.setAttribute("LOGGED_IN_USER", userdetail);

				RequestDispatcher dispatcher = request.getRequestDispatcher("userfeature.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("userlogin.jsp?errMsg=Invalid Login Credentials");
			}

		} catch (Exception e) {
			response.sendRedirect("userlogin.jsp?errMsg=" + e.getMessage());
		}
	}

}
