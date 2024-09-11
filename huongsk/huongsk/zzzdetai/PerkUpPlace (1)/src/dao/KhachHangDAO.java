package dao;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class KhachHangDAO {
    public KhachHangDAO() {
    }

    public ArrayList<KhachHang> getAllTbKhachHang() {
        ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();

        String sql = "Select * from KhachHang";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                String soDT = rs.getString(3);
                Float diemTl = rs.getFloat(4);

                KhachHang kh = new KhachHang(ma,ten,soDT,diemTl);
                dskh.add(kh);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dskh;
    }

	public KhachHang getOneKhachHang(String khID) {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec getOneKhachHang ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, khID);
			
            ResultSet rs = statement.executeQuery();
            
            //Duyệt trên kết quả trả về
            while (rs.next()){
            	String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String soDT = rs.getString(3);
                Double diemTL = rs.getDouble(4);
                
                return new KhachHang(maKH, tenKH, soDT, diemTL);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		return null;
	}
}
