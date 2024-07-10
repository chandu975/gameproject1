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
@WebServlet("/viewuserprofile")
public class ViewUserProfile extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		HttpSession session = req.getSession();

		String emailId = (String) session.getAttribute("email");

		RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
		CustomerDao custDao = new CustomerDao();
		Customer ub = custDao.getCustomer(emailId);
		System.out.println(ub);
		rd.include(req, res);
		pw.println("<div class='tab'>" + "		<p1 class='menu'>" + "	Hello " + 
				" ! Welcome to our new NITRTC Website" + "		</p1>" + "	</div>");
		pw.println("<div class='main'><p1 class='menu'><a href='viewuserprofile'>View Profile</a></p1>&nbsp;"
				+ "<p1 class='menu'><a href='edituserprofile'>Edit Profile</a></p1>&nbsp;"
				+ "<p1 class='menu'><a href='changeuserpassword'>Change Password</a></p1>" + "</div>");
		pw.println("<div class='tab'>Users Profile View</div>");
		pw.println("<div class='tab'>" + "<table>" + "<tr><td>Profile Photo :</td><td>Not Available</td></tr>"
				+ "<tr><td>User Name :</td><td>" + ub.getEmail() + "</td></tr>"
				+ "<tr><td>Password :</td><td><input type='password' disabled value='" + ub.getPassword() + "'/></td></tr>"
				+ "<tr><td>Name :</td><td>" + ub.getName() + "</td></tr>" + "<tr><td>Address :</td><td>" + ub.getAddress() + "</td></tr>"
				+ "<tr><td>Pincode :</td><td>"
				+ ub.getPincode() + "</td></tr>" + "<tr><td>Phone No:</td><td>" + ub.getMobile() + "</td></tr>" + "<tr><td>Mail Id :</td><td>"
				+ ub.getEmail() + "</td></tr>" + "</table>" + "</div>");

	}

}
