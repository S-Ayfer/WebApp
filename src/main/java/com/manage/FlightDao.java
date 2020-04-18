package com.manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightDao {

	public void register(String firstName, String lastName, String dob, String userName, String password)
			throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "sultanYildiz");

		PreparedStatement ps = conn
				.prepareStatement("insert into user(firstname,lastname,dob,username,password) values(?,?,?,?,?) ");
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setString(3, dob);
		ps.setString(4, userName);
		ps.setString(5, password);
		ps.executeUpdate();
	}

	public boolean login(String userName, String password) throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "sultanYildiz");
		PreparedStatement ps = conn.prepareStatement("select * from user where username=? and password=?");

		ps.setString(1, userName);
		ps.setString(2, password);
		ResultSet rSet = ps.executeQuery();

		if (rSet.next()) {
			return true;
		}
		return false;

	}

	public boolean book_ticket(int id) throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "sultanYildiz");

		String sql = "select * from flight where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int currentSeat = rs.getInt("seats");
			int updateSeat = currentSeat - 1;
			String updatesql = "update flight set seats=? where id=" + id;
			PreparedStatement psUpdate = conn.prepareStatement(updatesql);
			psUpdate.setInt(1, updateSeat);
			int i = psUpdate.executeUpdate();
			if (i == 1) {
				return true;
			}
		}
		return false;
	}

	public FlightModel getFlight(String from, String to) throws SQLException {
		
		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "sultanYildiz");


		String sql = "select * from flight where source=? and dest=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, from);
		ps.setString(2, to);
		ResultSet rs = ps.executeQuery();
		FlightModel flight = null;
		if (rs.next()) {
			flight = new FlightModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			return flight;
		}
		return null;
	}

}
