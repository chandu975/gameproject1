package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.MyConnection;
import com.model.Admin;
import com.model.Customer;

public class 	AdminDao {
	public Admin getAdmin(String mailId,String password){
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		con = MyConnection.getConnection();
		try {
			pst = con.prepareStatement("select * from admin where mailId = ? and password = ?");
			pst.setString(1, mailId);
			pst.setString(2, password);

			rs = pst.executeQuery();

			if(rs.next()){
				//recieve the data from resultset object and store in java object

				Admin Admin =new Admin();
				Admin.setMailId(rs.getString(1));
				Admin.setPWord(rs.getString(2));
				Admin.setFName(rs.getString(3));
				Admin.setLName(rs.getString(4));
				Admin.setAddr(rs.getString(5));
				Admin.setPhNo(rs.getLong(6));
				return Admin;
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
