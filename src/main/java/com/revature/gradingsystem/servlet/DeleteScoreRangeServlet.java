package com.revature.gradingsystem.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.service.AdminService;

/**
 * Servlet implementation class DeleteScoreRangeServlet
 */
public class DeleteScoreRangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		AdminService adminservice = new AdminService();
		try {
			adminservice.deleteScoreRangeService();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}

}