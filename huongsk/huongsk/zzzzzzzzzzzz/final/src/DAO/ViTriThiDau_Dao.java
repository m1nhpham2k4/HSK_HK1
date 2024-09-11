package DAO;

import Connect.ConnectDB;
import entity.ViTriThiDau;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ViTriThiDau_Dao {

    public ViTriThiDau_Dao(){

    }


    public ArrayList<ViTriThiDau> getAllViTri() throws SQLException {
        ArrayList<ViTriThiDau> dsVT= new ArrayList<>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql= "select * from ViTriThiDau";

        Statement statement= con.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            String maVT= rs.getString(1);
            String tenVT= rs.getString(2);
            dsVT.add(new ViTriThiDau(maVT,tenVT));
        }
        return dsVT;
    }
}
