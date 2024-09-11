package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.ConnectionDB;
import Entity.GiaoVien;

public class ListGiaoVien {
	public ArrayList<GiaoVien> getDSGiaoVien() throws SQLException {
		ArrayList<GiaoVien> list = new ArrayList<GiaoVien>();
		ConnectionDB con = new ConnectionDB();
		con.connect();
		Statement statement = con.getCon().createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM GiaoVien");
		while(resultSet.next()) {
			list.add(new GiaoVien(resultSet.getString(1), resultSet.getString(2)));
		}
		return list;
		
	}
}
