package com.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.db.MyConnection;



//Service Implementaion class for booking details of the ticket
//Creates the booking history and save to database
public class BookingDao implements Serializable  {

	public List<HistoryDao> getAllBookingsByCustomerId(String customerEmailId)  {
		List<HistoryDao> transactions = new ArrayList<HistoryDao>();
		String query = "SELECT * FROM HISTORY WHERE MAILID=?";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, customerEmailId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HistoryDao transaction = new HistoryDao();
				transaction.setTransId(rs.getString("transid"));
				transaction.setFrom_stn(rs.getString("from_stn"));
				transaction.setTo_stn(rs.getString("to_stn"));
				transaction.setDate(rs.getString("date"));
				transaction.setMailId(rs.getString("mailid"));
				transaction.setSeats(rs.getInt("seats"));
				transaction.setAmount(rs.getDouble("amount"));
				transaction.setTr_no(rs.getString("tr_no"));
				transactions.add(transaction);
			}

			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		}
		return transactions;
	}

	
	public HistoryDao createHistory(HistoryDao details)  {
		HistoryDao history = null;
		String query = "INSERT INTO HISTORY VALUES(?,?,?,?,?,?,?,?)";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			String transactionId = UUID.randomUUID().toString();
			ps.setString(1, transactionId);
			ps.setString(2, details.getMailId());
			ps.setString(3, details.getTr_no());
			ps.setString(4, details.getDate());
			ps.setString(5, details.getFrom_stn());
			ps.setString(6, details.getTo_stn());
			ps.setLong(7, details.getSeats());
			ps.setDouble(8, details.getAmount());
			int response = ps.executeUpdate();
			if (response > 0) {
				history = (HistoryDao) details;
				history.setTransId(transactionId);
			} 
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return history;
	}

}
