package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.TrainDao;
import com.model.Train;

@SuppressWarnings("serial")
@WebServlet("/adminviewtrainfwd")
public class AdminViewTrain extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		try {
			TrainDao trainDao = new TrainDao();
			List<Train> trains = trainDao.getAllTrains();
			if (trains != null && !trains.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("ViewTrains.html");
				rd.include(request, response);
				pw.println("<div class='main'><p1 class='menu'>Running Trains</p1></div>");
				pw.println("<div class='tab'><table><tr><th>Train Name</th><th>Train Number</th>"
						+ "<th>From Station</th><th>To Station</th><th>Seats Available</th><th>Fare (INR)</th><th>Action</th></tr>");

				for (Train train : trains) {

					pw.println("" + "<tr> " + "" + "<td><a href='viewadmin?trainNo=" + train.getTr_no() + "&fromStn="
							+ train.getFrom_stn() + "&toStn=" + train.getTo_stn() + "'>" + train.getTr_name()
							+ "</a></td>" + "<td>" + train.getTr_no() + "</td>" + "<td>" + train.getFrom_stn() + "</td>"
							+ "<td>" + train.getTo_stn() + "</td>" + "<td>" + train.getSeats() + "</td>" + "<td>"
							+ train.getFare() + " RS</td>" + "<td><a href='adminupdatetrain?trainnumber="
							+ train.getTr_no() + "'>Update</a></td>" + "</tr>");
				}
				pw.println("</table></div>");
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("ViewTrains.html");
				rd.include(request, response);
				pw.println("<div class='main'><p1 class='menu red'> No Running Trains</p1></div>");
			}
		} catch (Exception e) {
			pw.println("<div class='main'><p1 class='menu red'> error....</p1></div>");

		}

	}

}
