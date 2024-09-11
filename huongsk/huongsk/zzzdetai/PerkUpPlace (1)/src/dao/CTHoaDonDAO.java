package dao;

import connectDB.ConnectDB;
import entity.Ban;
import entity.CTHoaDon;
import entity.CTPhieuDat;
import entity.HoaDon;
import entity.PhieuDat;
import entity.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class CTHoaDonDAO {

    public CTHoaDonDAO() {
    }

    public ArrayList<CTHoaDon> getAllTbCTHoaDon(String maHD) {
        ArrayList<CTHoaDon> dsCtHd = new ArrayList<CTHoaDon>();   
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec getCTHD ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, maHD);
			
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
            	HoaDon hd = new HoaDon(maHD);
                SanPham sp = new SanPham();
            	sp.setTenSP(rs.getString(1));
                int soLuong = rs.getInt(2);
                double donGia = rs.getDouble(3);

                CTHoaDon ctHoaDon = new CTHoaDon(sp,hd,soLuong,donGia);
                dsCtHd.add(ctHoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }return dsCtHd;
    }
    
    public ArrayList<CTHoaDon> getCTHDFromHD(String maHD) {
        ArrayList<CTHoaDon> dsCtHd = new ArrayList<CTHoaDon>();   
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec getCTHDFromHoaDon ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, maHD);
			
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
            	HoaDon hd = new HoaDon(maHD);
                SanPham sp = new SanPham();
            	sp.setMaSP(rs.getString(1));
            	sp.setTenSP(rs.getString(2));
                int soLuong = rs.getInt(3);
                double donGia = rs.getDouble(4);

                CTHoaDon ctHoaDon = new CTHoaDon(sp,hd,soLuong,donGia);
                dsCtHd.add(ctHoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }return dsCtHd;
    }
    
    public void addCTHoaDon(CTHoaDon cthd) {
    	ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec addCTHoaDon ?, ?, ?, ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, cthd.getHoaDon().getMaHD());
			statement.setString(2, cthd.getSanPham().getMaSP());
			statement.setDouble(3, cthd.getSoLuong());
			statement.setDouble(4, cthd.getDonGia());
			
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public double tongHoaDon(String maHD) {
    	ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec sumTotal ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, maHD);
			
			ResultSet rs = statement.executeQuery();
		    if (rs.next()) {
		        return rs.getDouble(1);
		    } else {
		        // Xử lý trường hợp không có kết quả trả về
		        return 0.0; // Hoặc giá trị mặc định khác tùy thuộc vào yêu cầu của bạn
		    }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
