package dao;

import connectDB.ConnectDB;
import entity.PhongBan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class PhongBan_DAO {

    public PhongBan_DAO() {
    }

    public ArrayList<PhongBan> getAllPhongBan() {
        ArrayList<PhongBan> dsphongban = new ArrayList<PhongBan>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "select * from PhongBan";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);


            while (rs.next()){
                String maPB = rs.getString(1);
                String tenPB = rs.getString(2);
                PhongBan  p = new PhongBan(maPB,tenPB);
                dsphongban.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsphongban;
    }
}
