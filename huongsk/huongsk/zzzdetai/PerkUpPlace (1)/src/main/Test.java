package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import dao.BanDAO;
import dao.NhanVienDAO;
import entity.Ban;
import entity.NhanVien;

public class Test {

	public static void main(String[] args) {
		// khởi tạo kết nối đến CSDL
				try {
					ConnectDB.getInstance().connect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		BanDAO dao = new BanDAO();
		ArrayList<Ban> dsban = dao.getAllTbBan();
		for(Ban b: dsban) {
			System.out.println(b.toString());
		}
	}

}
