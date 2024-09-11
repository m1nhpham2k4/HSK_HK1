package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.CauThu;
import entity.ViTriThiDau;

public class CauThu_DAO {

	public ArrayList<CauThu> getTatCaCauThu() {
		ArrayList<CauThu> dsCauThu = new ArrayList<CauThu>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "select * from CauThu";
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maCauThu = rs.getString(1);
				String tenCauThu = rs.getString(2);
				int tuoi = rs.getInt(3);
				String maViTri = rs.getString(4);
				ViTriThiDau viTri = new ViTriThiDau(maViTri);
				CauThu cauThu = new CauThu(maCauThu, tenCauThu, tuoi, viTri);
				dsCauThu.add(cauThu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsCauThu;
	}
	
	public boolean themCauThu(CauThu cauThu) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into" + " CauThu values(?,?,?,?)");
			stmt.setString(1, cauThu.getMaCauThu());
			stmt.setString(2, cauThu.getTenCauThu());
			stmt.setInt(3, cauThu.getTuoi());
			stmt.setString(4, cauThu.getViTri().getMaViTri());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n>0;
		
	}
	
	public boolean xoaCauThu(String maCauThu) {

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("delete CauThu where maCauThu = ?");
			stmt.setString(1, maCauThu);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n>0;
		
	}
	
	public ArrayList<CauThu> dsCauThuTheoViTri(String maViTri) {
		ArrayList<CauThu> dsCauThu = new ArrayList<CauThu>();
		PreparedStatement stmt = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from CauThu where maViTri = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maViTri);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maCauThu = rs.getString(1);
				String tenCauThu = rs.getString(2);
				int tuoi = rs.getInt(3);
				CauThu cauThu = new CauThu(maCauThu, tenCauThu, tuoi, new ViTriThiDau(maViTri));
				dsCauThu.add(cauThu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dsCauThu;
		
	}
	
	
	public boolean maCauThuExists(String maCauThu) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from CauThu where maCauThu = ?");
            stmt.setString(1, maCauThu);
            rs = stmt.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
	
	
}
