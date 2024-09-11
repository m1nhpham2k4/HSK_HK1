package dao;

import connectDB.ConnectDB;
import entity.NhanVien;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class NhanVienDAO {
    public NhanVienDAO() {
    }
    
    public NhanVien checkAccount(String maNV, String matKhau) {
    	ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec checkAccount ?, ?";
        try {
        	statement = con.prepareStatement(sql);
        	statement.setString(1,maNV);
        	statement.setString(2,matKhau);

	        ResultSet rs = statement.executeQuery();
	        rs.next();
	        String ma = rs.getString(1);
	        String ten = rs.getString(2);
	        String soDT = rs.getString(3);
	        LocalDate ngaySinh = rs.getDate(4).toLocalDate();
	        String diaChi = rs.getString(5);
	        String mk = rs.getString(6);
	        String hinhAnh = rs.getString(7);
	        
	        return new NhanVien(maNV, ten, soDT, ngaySinh, diaChi, matKhau, hinhAnh);
        } catch (SQLException e) {
            return null;
        }
    }

    public void updateNV(NhanVien nv) {
    	ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec updateThongTinNV ?, ?, ?, ?, ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, nv.getMaNV());
			statement.setString(2, nv.getTenNV());
			statement.setString(3, nv.getSoDT());
			statement.setDate(4, Date.valueOf(nv.getNgaySinh()));
			statement.setString(5, nv.getHinhAnh());
			
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
