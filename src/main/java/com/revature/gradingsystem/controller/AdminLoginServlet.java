package com.revature.gradingsystem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.gradingsystem.model.UserDetails;
import com.revature.gradingsystem.service.AdminService;
import com.revature.gradingsystem.validator.UserValidator;

/**
 * Servlet implementation class LoginServlet
 */
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		String name = request.getParameter("adminname");
		String password = request.getParameter("password");

		UserValidator uservalidator = new UserValidator();
		AdminService adminService = new AdminService();
		UserDetails userdetail = new UserDetails();

		try {
			uservalidator.Login(name, password);
			userdetail = adminService.adminLogin(name, password);

			if (userdetail != null) {
				HttpSession session = request.getSession();
				session.setAttribute("LOGGED_IN_USER", userdetail);

				RequestDispatcher dispatcher = request.getRequestDispatcher("adminfeature.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("adminlogin.jsp?message=Invalid Login Credentials");
			}

		} catch (Exception e) {
			response.sendRedirect("adminlogin.jsp?message=" + e.getMessage());
		}
	}

}
