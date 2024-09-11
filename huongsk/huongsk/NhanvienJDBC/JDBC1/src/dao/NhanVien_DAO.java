package dao;

import UI.ConnectDB;
import entity.NhanVien;
import entity.PhongBan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NhanVien_DAO {
    public List<NhanVien> getalltbNhanVien(){
        List<NhanVien> dsNV = new ArrayList<NhanVien>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql= "select * from NhanVien";
            Statement statement = con.createStatement();
            ResultSet rs= statement.executeQuery(sql);

            while(rs.next()){
                int ma = rs.getInt(1);
                String ho = rs.getString(2);
                String ten= rs.getString(3);
                int tuoi= rs.getInt(4);
                boolean phai= rs.getBoolean(5);
                double luong= rs.getDouble(6);
                PhongBan phongBan = new PhongBan(rs.getString(7));
                NhanVien nv = new NhanVien();
                dsNV.add(nv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsNV;
    }

    public List<NhanVien> getNVtheoMa(int id){
        List<NhanVien> dsNV = new ArrayList<NhanVien>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();

        return dsNV;
    }
}
