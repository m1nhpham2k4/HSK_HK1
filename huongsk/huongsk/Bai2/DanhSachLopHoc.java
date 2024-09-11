package Bai2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DanhSachLopHoc {
	private ArrayList<LopHoc> dsLop;
	Connection con;

	public DanhSachLopHoc() {
		super();
		dsLop = new ArrayList<LopHoc>();
		// TODO Auto-generated constructor stub
	}

//	Read
	public ArrayList<LopHoc> docTuBang() {
		try {
			con = Database.getInstance().getConnection();
			String sql = "Select * from LopHoc";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {
				String maLop = rs.getString(1);
				String tenLop = rs.getString(2);
				String gvcn = rs.getString(3);

				dsLop.add(new LopHoc(maLop, tenLop, gvcn));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dsLop;
	}

//	Update
	public boolean update(LopHoc l) {
		con = Database.getInstance().getConnection();
		PreparedStatement state = null;
		int n = 0;

		try {
			state = con.prepareStatement("update LopHoc " + "set tenLop = ?," + "giaoVienCN = ?" + "where maLop = ?");
			state.setString(1, l.getTenLop());
			state.setString(2, l.getGiaoVienCN());
			state.setString(3, l.getMaLop());
			n = state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n > 0;
	}
	
//	Delete
	public boolean delete(String maLop)
	{
		int n = 0;
		con = Database.getInstance().getConnection();
		PreparedStatement state = null;
		
		try {
			state = con.prepareStatement("delete from LopHoc where maLop = ?");
			state.setString(1, maLop);
			n = state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return n > 0;
	}

	@Override
	public String toString() {
		String result = "";

		for (LopHoc ele : dsLop) {
			result += ele.toString() + '\n';
		}

		return result;
	}

}
