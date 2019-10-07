package com.revature.gradingsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.model.StudentMark;
import com.revature.gradingsystem.model.Subject;
import com.revature.gradingsystem.service.UserService;
import com.revature.gradingsystem.validator.StudentValidator;

public class UpdateMarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String reg_No = request.getParameter("regno");
		String m1 = request.getParameter("mark1");
		String m2 = request.getParameter("mark2");
		String m3 = request.getParameter("mark3");
		String m4 = request.getParameter("mark4");
		String m5 = request.getParameter("mark5");

		int regno = Integer.parseInt(reg_No);
		int mark1 = Integer.parseInt(m1);
		int mark2 = Integer.parseInt(m2);
		int mark3 = Integer.parseInt(m3);
		int mark4 = Integer.parseInt(m4);
		int mark5 = Integer.parseInt(m5);

		String errorMessage = null;
		String status = "";
		try {
			StudentValidator studentValidate = new StudentValidator();
			studentValidate.isRegnoUpdated(regno);

			StudentMark sm1 = new StudentMark();

			sm1.setMark(mark1);
			Subject subject1 = new Subject();
			subject1.setCode("ENG11");
			sm1.setSubject(subject1);

			StudentMark sm2 = new StudentMark();

			sm2.setMark(mark2);
			Subject subject2 = new Subject();
			subject2.setCode("MAT12");
			sm2.setSubject(subject2);

			StudentMark sm3 = new StudentMark();

			sm3.setMark(mark3);
			Subject subject3 = new Subject();
			subject3.setCode("PHY13");
			sm3.setSubject(subject3);

			StudentMark sm4 = new StudentMark();

			sm4.setMark(mark4);
			Subject subject4 = new Subject();
			subject4.setCode("CHE14");
			sm4.setSubject(subject4);

			StudentMark sm5 = new StudentMark();

			sm5.setMark(mark5);
			Subject subject5 = new Subject();
			subject5.setCode("COM15");
			sm5.setSubject(subject5);

			List<StudentMark> list = new ArrayList<StudentMark>();
			list.add(sm1);
			list.add(sm2);
			list.add(sm3);
			list.add(sm4);
			list.add(sm5);

			new UserService().updateMarksAndGradeService(regno, list);

			status = "Success";
		} catch (ValidatorException e) {
			errorMessage = e.getMessage();

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);

	}

}
