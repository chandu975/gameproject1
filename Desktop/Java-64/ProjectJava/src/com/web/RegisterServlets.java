package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CustomerDao;
import com.model.Customer;



@SuppressWarnings("serial")
@WebServlet("/RegisterServlets")
public class RegisterServlets extends HttpServlet {


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
			Customer user = new Customer();
			user.setEmail(req.getParameter("email"));
			user.setPassword(req.getParameter("password"));
			user.setName(req.getParameter("name"));
			user.setAddress(req.getParameter("address"));
			user.setPincode(Long.parseLong(req.getParameter("pincode")));
			user.setMobile((req.getParameter("mobile")));
			CustomerDao customerDao = new CustomerDao(); 
			int message = customerDao.register(user);
			if (message>0) {
				RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>User Registered Successfully !</p1></div>");

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("UserRegister.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>" + message + "</p1></div>");

			}

		} catch (Exception e) {
			pw.println("<div class='tab'><p1 class='menu'> failed....</p1></div>");
		}
	}

}
