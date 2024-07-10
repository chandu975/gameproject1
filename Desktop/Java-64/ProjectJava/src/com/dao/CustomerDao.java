package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.MyConnection;
import com.model.Customer;

public class CustomerDao {
	public Customer getCustomer(String emailId,String password){
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		con = MyConnection.getConnection();
		try {
			pst = con.prepareStatement("select * from customer where email = ? and password = ?");
			pst.setString(1, emailId);
			pst.setString(2, password);

			rs = pst.executeQuery();

			if(rs.next()){
				//recieve the data from resultset object and store in java object

				Customer Customer =new Customer();
				Customer.setName(rs.getString(1));
				Customer.setEmail(rs.getString(2));
				Customer.setAddress(rs.getString(3));
				Customer.setPincode(rs.getLong(4));
				Customer.setPassword(rs.getString(5));
				return Customer;
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
 
	public int register(Customer customer) {
		Connection con = null;
		PreparedStatement pst = null;
		
		con = MyConnection.getConnection();
		try {
			pst = con.prepareStatement("insert into customer values(?,?,?,?,?,?)");
			pst.setString(1, customer.getName());
			pst.setString(2, customer.getEmail());
			pst.setString(3, customer.getAddress());
			pst.setString(4, customer.getMobile());
			pst.setLong(5, customer.getPincode());
			pst.setString(6, customer.getPassword());
			int x = pst.executeUpdate();
			return x;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Customer getCustomer(String emailId) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		con = MyConnection.getConnection();
		try {
			pst = con.prepareStatement("select * from customer where email = ?");
			pst.setString(1, emailId);
			
			rs = pst.executeQuery();

			if(rs.next()){
				//recieve the data from resultset object and store in java object

				Customer customer =new Customer();
				pst.setString(1, customer.getName());
				pst.setString(2, customer.getEmail());
				pst.setString(3, customer.getAddress());
				pst.setString(4, customer.getMobile());
				pst.setLong(5, customer.getPincode());
				pst.setString(6, customer.getPassword());
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	
}
