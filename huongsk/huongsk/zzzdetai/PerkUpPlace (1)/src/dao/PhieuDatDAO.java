package dao;

import connectDB.ConnectDB;
import entity.PhieuDat;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class PhieuDatDAO {

    public PhieuDatDAO() {
    }

    public ArrayList<PhieuDat> getAllTbPhieuDat() {
        ArrayList<PhieuDat> dspd = new ArrayList<PhieuDat>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();

        String sql = "Select * from NhanVien";
        try {
            Statement statement = con.createStatement();
            // Thực thi câu lệnh sql trả về đối tượng ResultSet.
            ResultSet rs = statement.executeQuery(sql);

            //Duyệt trên kết quả trả về
            while (rs.next()){
                String ma = rs.getString(1);
                String yeuCau = rs.getString(2);

                PhieuDat pd = new PhieuDat(ma,yeuCau);
                dspd.add(pd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dspd;
    }
}
