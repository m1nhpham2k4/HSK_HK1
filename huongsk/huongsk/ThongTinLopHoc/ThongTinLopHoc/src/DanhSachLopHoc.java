import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.prefs.PreferenceChangeEvent;

public class DanhSachLopHoc {
	private ArrayList<LopHoc> dsLopHoc;
	Connection con;

	public DanhSachLopHoc() {

		dsLopHoc = new ArrayList<>();
	}

	public ArrayList<LopHoc> getDsLopHoc() {
		return dsLopHoc;
	}

	public void setDsLopHoc(ArrayList<LopHoc> dsLopHoc) {
		this.dsLopHoc = dsLopHoc;
	}

	public void themLophoc(LopHoc lop) {
		dsLopHoc.add(lop);
	}

	public ArrayList<LopHoc> docTuBang() {
		try {
			con = Database.getInstance().getConnection();
			String sql = "Select * from Lop";
			Statement statement =  con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String ma = resultSet.getString(1);
				String ten = resultSet.getString(2);
				String gvcn = resultSet.getString(3);
				LopHoc lopHoc = new LopHoc(ma, ten, gvcn);
				dsLopHoc.add(lopHoc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLopHoc;
	}

	public boolean create(LopHoc p) {
		con = Database.getInstance().getConnection();
		PreparedStatement pStatement = null;
		int n = 0;
		try {
			pStatement = con.prepareStatement("insert into Lop values(?, ?, ?)");
			pStatement.setString(1, p.getMaLop());
			pStatement.setString(2, p.getTenLop());
			pStatement.setString(3, p.getGiaoVienCN());
			n = pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean update(LopHoc p) {
		con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update Lop" + "set TenLop = ?, " + "GiaoVienChuNhiem =? " + "where MaLop = ?");
			stmt.setString(1, p.getTenLop());
			stmt.setString(2, p.getGiaoVienCN());
			stmt.setString(3, p.getMaLop());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean delete(String mlop) {
		con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from Lop where Malop = ?");
			stmt.setString(1, mlop);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
