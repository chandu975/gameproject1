package com.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CustomerDao;
import com.model.Customer;


@SuppressWarnings("serial")
@WebServlet("/userprofile")
public class UserProfile extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();


		HttpSession session = req.getSession();

		String emailId = (String) session.getAttribute("email");

		RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
		CustomerDao custDao = new CustomerDao();
		Customer ub = custDao.getCustomer(emailId);
		rd.include(req, res);
		pw.println("<div class='tab'>" + "		<p1 class='menu'>" + "	Hello " +emailId +" ! Welcome to our new NITRTC Website" + "		</p1>" + "	</div>");
		pw.println("<div class='main'><p1 class='menu'><a href='viewuserprofile'>View Profile</a></p1>&nbsp;"
				+ "<p1 class='menu'><a href='edituserprofile'>Edit Profile</a></p1>&nbsp;"
				+ "<p1 class='menu'><a href='changeuserpassword'>Change Password</a></p1>" + "</div>");
		pw.println("<div class='tab yellow'>Hey ! " + ub.getName() + ",Welcome to NITRTC<br/><br/>Here You can Edit,View Your Profile and change your PassWord.<br/>"
				+ "<br/>Thanks For Being Connected With Us!" + "</div>");

	}

}
