package dao;

import connectDB.ConnectDB;
import entity.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class HoaDonDAO {
    public HoaDonDAO() {
    }

    public ArrayList<HoaDon> getAllTbHoaDon() {
        ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();

        String sql = "exec getAllHoaDon";
        try {
            Statement statement = con.createStatement();
            // Thực thi
            ResultSet rs = statement.executeQuery(sql);
            //Duệt từng dòng
            while(rs.next()) {
            	KhachHang kh = new KhachHang();
                NhanVien nv = new NhanVien(); 
                String maHD = rs.getString(1);
                kh.setMaKH(rs.getString(2));
                nv.setMaNV(rs.getString(4));
//                LocalDate ngayTao = rs.getDate(4).toLocalDate();
                LocalDate ngayTao = LocalDate.now();
                double tongTien = rs.getDouble(5);
                Ban maBan = new Ban(rs.getString(6));
                Boolean trangThai = rs.getBoolean(7);

                HoaDon hd = new HoaDon(maHD, nv, kh, ngayTao, trangThai, maBan, tongTien);
                dsHD.add(hd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsHD;
    }
    
    public HoaDon getOneTbHoaDon(String hdID) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec getOneHoaDon ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, hdID);
            // Thực thi
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
			    // Lấy dữ liệu từ ResultSet
			    KhachHang kh = new KhachHang();
			    NhanVien nv = new NhanVien(); 
			    String maHD = rs.getString(1);
			    if(rs.getString(2)!=null) {
			        kh.setMaKH(rs.getString(2));
			        kh.setTenKH(rs.getString(3));
			    } else {
			        kh.setMaKH(""); // Giá trị mặc định khi không có dữ liệu
			        kh.setTenKH(""); // Giá trị mặc định khi không có dữ liệu
			    }
			    if(rs.getString(4)!=null) {
			        nv.setMaNV(rs.getString(4));
			        nv.setTenNV(rs.getString(5));
			    } else {
			        nv.setMaNV(""); // Giá trị mặc định khi không có dữ liệu
			        nv.setTenNV(""); // Giá trị mặc định khi không có dữ liệu
			    }
			    LocalDate ngayTao;
			    if(rs.getDate(6) != null) {
			        ngayTao = rs.getDate(6).toLocalDate();
			    } else {
			        ngayTao = LocalDate.now(); // Giá trị mặc định là ngày hiện tại nếu không có dữ liệu
			    }
			    double tongTien = rs.getDouble(7);
			    Ban ban;
			    if(rs.getString(8) != null) {
			        ban = new Ban(rs.getString(8));
			    } else {
			        ban = new Ban(""); // Giá trị mặc định khi không có dữ liệu
			    }
			    Boolean trangThai = rs.getBoolean(9);

			    return new HoaDon(maHD, nv, kh, ngayTao, trangThai, ban, tongTien);
			} else {
			    // Trả về null hoặc xử lý trường hợp không có dữ liệu
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getSLHoaDon() {
    	ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "exec getSLHoaDon";
        try {
        	Statement statement = con.createStatement();
			
        	ResultSet rs = statement.executeQuery(sql);
        	rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public void createNewHoaDon(HoaDon h) {
    	ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec addHD ?";
        try {
        	statement = con.prepareStatement(sql);
        	statement.setString(1, h.getMaHD());
        	
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public ArrayList<HoaDon> getHoaDonChuaThanhToan() {
    	ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();

        String sql = "exec getAllOrderNonPayment";
        try {
            Statement statement = con.createStatement();
            // Thực thi
            ResultSet rs = statement.executeQuery(sql);
            //Duệt từng dòng
            while(rs.next()) {
                NhanVien nv = new NhanVien(); 
                String maHD = rs.getString(1);
                HoaDon hd = new HoaDon(maHD);
                dsHD.add(hd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsHD;
    }

	public void updateHoaDon(HoaDon h) {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec updateHD ?, ?, ?, ?, ?, ?";
        try {
        	statement = con.prepareStatement(sql);
        	statement.setString(1, h.getMaHD());
        	statement.setString(2, h.getNhanVien().getMaNV());
        	statement.setString(3, h.getKhachHang().getMaKH());
        	statement.setDate(4, Date.valueOf(h.getNgayTaoHD()));
        	statement.setBoolean(5, h.isTrangThai());
        	if(h.getBan().getMaBan().equals("")) {
        		statement.setString(6, null);
        	}else {
        		statement.setString(6, h.getBan().getMaBan());
        	}
			
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
    
}
