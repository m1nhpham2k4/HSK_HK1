package DAO;

import Connect.ConnectDB;
import entity.CauThu;
import entity.ViTriThiDau;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class CauThu_Dao {

    public CauThu_Dao(){}

    public ArrayList<CauThu> getAllCauThu() throws SQLException {
        ArrayList<CauThu> dsCT= new ArrayList<>();
        ConnectDB.getInstance();
        Connection con =ConnectDB.getConnection();
        String sql="select * from CauThu";
        Statement statement= con.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            String maCT = rs.getString(1);
            String tenCT= rs.getString(2);
            int tuoi= rs.getInt(3);
            ViTriThiDau VTTD = new ViTriThiDau(rs.getString(4));
            dsCT.add(new CauThu(maCT,tenCT,tuoi,VTTD));
        }
        return dsCT;
    }

    public boolean addCauThu(CauThu ct) throws SQLException {
        ConnectDB.getInstance();
        Connection con =ConnectDB.getConnection();
        String sql="insert into CauThu values(?,?,?,?)";
        PreparedStatement pre= con.prepareStatement(sql);
        int row =0;

        try{
            pre.setString(1,ct.getMaCauThu());
            pre.setString(2,ct.getTenCauThu());
            pre.setInt(3,ct.getTuoi());
            pre.setString(4,ct.getViTri().getMaViTri());
            row= pre.executeUpdate();
        }catch (SQLException e){

        }
        return row>0;

    }

    public boolean xoaCT(String maCT) throws SQLException {
        int row=0;
        ConnectDB.getInstance();
        Connection con =ConnectDB.getConnection();
        String sql="delete from CauThu where maCauThu=?";
        PreparedStatement pre= con.prepareStatement(sql);
        pre.setString(1,maCT);
        row= pre.executeUpdate();
        return row>0;
    }

    public ArrayList<CauThu> getList(String maVT) throws SQLException {
        ArrayList<CauThu> dsCT= new ArrayList<>();
        ConnectDB.getInstance();
        Connection con =ConnectDB.getConnection();
        String sql="select * from CauThu where maViTri=?";
        PreparedStatement pre= con.prepareStatement(sql);
        pre.setString(1,maVT);
        ResultSet rs = pre.executeQuery();

        while (rs.next()){
            String maCT = rs.getString(1);
            String tenCT= rs.getString(2);
            int tuoi= rs.getInt(3);
            ViTriThiDau VTTD = new ViTriThiDau(rs.getString(4));
                dsCT.add(new CauThu(maCT,tenCT,tuoi,VTTD));
        }
        return dsCT;
    }
}
