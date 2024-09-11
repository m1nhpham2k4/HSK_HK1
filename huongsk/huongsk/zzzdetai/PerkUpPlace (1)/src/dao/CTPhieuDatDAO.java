package dao;

import connectDB.ConnectDB;
import entity.Ban;
import entity.CTPhieuDat;
import entity.PhieuDat;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class CTPhieuDatDAO {

    public CTPhieuDatDAO() {
    }

    public ArrayList<CTPhieuDat> getAllTbCTPhieuDat() {
        ArrayList<CTPhieuDat> dspd = new ArrayList<CTPhieuDat>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();

        String sql = "Select * from NhanVien";
        try {
            Statement statement = con.createStatement();
            // Thực thi câu lệnh sql trả về đối tượng ResultSet.
            ResultSet rs = statement.executeQuery(sql);

            //Duyệt trên kết quả trả về
            while (rs.next()){
                PhieuDat maPhieu = new PhieuDat(rs.getString(1));
                Ban maBan = new Ban(rs.getString(2));
                LocalDate ngayDat = rs.getDate(3).toLocalDate();

                CTPhieuDat ctPhieuDat = new CTPhieuDat(maPhieu,maBan,ngayDat);
                dspd.add(ctPhieuDat);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dspd;
    }
}
