package dao;

import UI.ConnectDB;
import entity.PhongBan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhongBan_DAO {
    public List<PhongBan> getalltbPhongBan(){
        List<PhongBan> dsPhongBan = new ArrayList<PhongBan>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql= "select * from PhongBan";
            Statement statement = con.createStatement();
            ResultSet rs= statement.executeQuery(sql);

            while(rs.next()){
                String maPB= rs.getString(1);
                String tenPB= rs.getString(2);
                PhongBan p = new PhongBan(maPB,tenPB);
                dsPhongBan.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsPhongBan;
    }
}
