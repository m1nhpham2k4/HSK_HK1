package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ViTriThiDau;

public class ViTriThiDau_DAO {

	public ArrayList<ViTriThiDau> getAllViTri() {
		ArrayList<ViTriThiDau> dsViTri = new ArrayList<ViTriThiDau>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "select * from ViTriThiDau";
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maVitri = rs.getString(1);
				String tenViTri = rs.getString(2);
				ViTriThiDau viTri = new ViTriThiDau(maVitri, tenViTri);
				dsViTri.add(viTri);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsViTri;
	}
			
}
