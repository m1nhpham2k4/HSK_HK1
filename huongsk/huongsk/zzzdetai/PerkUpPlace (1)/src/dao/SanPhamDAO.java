package dao;

import connectDB.ConnectDB;

import entity.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class SanPhamDAO {

    public SanPhamDAO() {
    }

    public ArrayList<SanPham> getAllTbSanPham() {
        ArrayList<SanPham> dssp = new ArrayList<SanPham>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();

        String sql = "exec getAllSanPham";
        try {
            Statement statement = con.createStatement();
            // Thực thi câu lệnh sql trả về đối tượng ResultSet.
            ResultSet rs = statement.executeQuery(sql);

            //Duyệt trên kết quả trả về
            while (rs.next()){
                String maSP = rs.getString(1);
                String ten = rs.getString(2);
                String loaiSP = rs.getString(3);
                Double donGia = rs.getDouble(4);
                String hinhAnh = rs.getString(5);
                boolean trangThai = rs.getBoolean(6);
                SanPham sp = new SanPham(maSP, ten, loaiSP, donGia, hinhAnh, trangThai);
                dssp.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dssp;
    }

	public SanPham getOneSanPham(String spID) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec getOneSanPham ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, spID);
			
            ResultSet rs = statement.executeQuery();
            
            //Duyệt trên kết quả trả về
            while (rs.next()){
            	String maSP = rs.getString(1);
                String ten = rs.getString(2);
                String loaiSP = rs.getString(3);
                Double donGia = rs.getDouble(4);
                String hinhAnh = rs.getString(5);
                boolean trangThai = rs.getBoolean(6);
                
                return new SanPham(maSP, ten, loaiSP, donGia, hinhAnh, trangThai);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		return null;
	}

	public String getMaSPLonNhat() {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "exec getProductCount";
        try {
        	Statement statement = con.createStatement();
			
        	ResultSet rs = statement.executeQuery(sql);
        	rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	public void addNewSP(SanPham sp) {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec addSP ?, ?, ?, ?, ?, ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, sp.getMaSP());
			statement.setString(2, sp.getTenSP());
			statement.setString(3, sp.getLoaiSP());
			statement.setDouble(4, sp.getDonGia());
			statement.setString(5, sp.getHinhAnh());
			statement.setBoolean(6, sp.isTrangThai());
			
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
	public boolean deleteSP(String maSP) {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec deleteSP ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, maSP);
			
            int n = statement.executeUpdate();
            if(n>0) {
            	return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	public void updateSP(SanPham sp) {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec updateSP ?, ?, ?, ?, ?, ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, sp.getMaSP());
			statement.setString(2, sp.getTenSP());
			statement.setString(3, sp.getLoaiSP());
			statement.setDouble(4, sp.getDonGia());
			statement.setString(5, sp.getHinhAnh());
			statement.setBoolean(6, sp.isTrangThai());
			
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
	public ArrayList<SanPham> findSP(String tenSP, String loai){
	    ArrayList<SanPham> dssp = new ArrayList<SanPham>();
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement statement = null;
	    try {
	        // Tên của thủ tục lưu trữ là "findSP", không cần dấu "?" ở đây
	        String sql = "exec findSP ?, ?";
	        statement = con.prepareStatement(sql);
	        // Đặt giá trị cho tham số
	        statement.setString(1, tenSP);
	        statement.setString(2, loai);
	        
	        // Thực thi câu lệnh SQL
	        ResultSet rs = statement.executeQuery();

	        // Duyệt trên kết quả trả về
	        while (rs.next()){
	            String maSP = rs.getString(1);
	            String ten = rs.getString(2);
	            String loaiSP = rs.getString(3);
	            Double donGia = rs.getDouble(4);
	            String hinhAnh = rs.getString(5);
	            boolean trangThai = rs.getBoolean(6);
	            SanPham sp = new SanPham(maSP, ten, loaiSP, donGia, hinhAnh, trangThai);
	            dssp.add(sp);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            // Đóng các tài nguyên
	            if (statement != null) {
	                statement.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return dssp;
	}
	
	public ArrayList<SanPham> findSPByName(String tenSP){
	    ArrayList<SanPham> dssp = new ArrayList<SanPham>();
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement statement = null;
	    try {
	        // Tên của thủ tục lưu trữ là "findSP", không cần dấu "?" ở đây
	        String sql = "exec findSPByName ?";
	        statement = con.prepareStatement(sql);
	        // Đặt giá trị cho tham số
	        statement.setString(1, tenSP);
	        
	        // Thực thi câu lệnh SQL
	        ResultSet rs = statement.executeQuery();

	        // Duyệt trên kết quả trả về
	        while (rs.next()){
	            String maSP = rs.getString(1);
	            String ten = rs.getString(2);
	            String loaiSP = rs.getString(3);
	            Double donGia = rs.getDouble(4);
	            String hinhAnh = rs.getString(5);
	            boolean trangThai = rs.getBoolean(6);
	            SanPham sp = new SanPham(maSP, ten, loaiSP, donGia, hinhAnh, trangThai);
	            dssp.add(sp);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            // Đóng các tài nguyên
	            if (statement != null) {
	                statement.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return dssp;
	}
	
	public ArrayList<SanPham> findSPByType(String loai) {
	    ArrayList<SanPham> dssp = new ArrayList<SanPham>();
	    Connection con = null;
	    PreparedStatement statement = null;
	    try {
	        con = ConnectDB.getConnection();
	        String sql = "exec findSPByType ?";
	        statement = con.prepareStatement(sql);
	        statement.setString(1, loai);
	        ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            String maSP = rs.getString(1);
	            String ten = rs.getString(2);
	            String loaiSP = rs.getString(3);
	            Double donGia = rs.getDouble(4);
	            String hinhAnh = rs.getString(5);
	            boolean trangThai = rs.getBoolean(6);
	            SanPham sp = new SanPham(maSP, ten, loaiSP, donGia, hinhAnh, trangThai);
	            dssp.add(sp);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return dssp;
	}
}
