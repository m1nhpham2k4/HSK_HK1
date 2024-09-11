package dao;

import connectDB.ConnectDB;
import entity.Ban;
import entity.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BanDAO {
    public BanDAO() {
    	
    }

    public ArrayList<Ban> getAllTbBan() {
        ArrayList<Ban> dsb = new ArrayList<Ban>();
        try {
        	ConnectDB.getInstance();
        	Connection con = ConnectDB.getConnection();
        	
	        String sql = "exec getALLBan";
	        Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng RéultSet
			ResultSet rs = statement.executeQuery(sql);

            //Duyệt trên kết quả trả về
            while (rs.next()){
                String maBan = rs.getString(1);
                int soGhe = rs.getInt(2);
                int trangThai = rs.getInt(3);
                Ban b = new Ban(maBan,soGhe,trangThai);
                dsb.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsb;
    }

	public Ban getOneBan(String banID) {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec getOneBan ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, banID);
			
            ResultSet rs = statement.executeQuery();
            
            
            rs.next();
            String maBan = rs.getString(1);
            int soGhe = rs.getInt(2);
            int trangThai = rs.getInt(3);
            return new Ban(maBan,soGhe,trangThai);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
	public String getMaBanLonNhat() {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "exec getTableCount";
        try {
        	Statement statement = con.createStatement();
			
        	ResultSet rs = statement.executeQuery(sql);
        	rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
    
	public void addNewBan(Ban b) {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec addBan ?, ?, ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, b.getMaBan());
			statement.setInt(2, b.getSoGhe());
			statement.setInt(3, b.getTrangThai());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
	public void updateBan(Ban b) {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec updateBan ?, ?, ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, b.getMaBan());
			statement.setInt(2, b.getSoGhe());
			statement.setInt(3, b.getTrangThai());
			
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
	public boolean deleteBan(String maBan) {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "exec deleteBan ?";
        try {
        	statement = con.prepareStatement(sql);
			statement.setString(1, maBan);
			
            int n = statement.executeUpdate();
            if(n>0) {
            	return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
}
