package dao;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.PhongBan;

import java.sql.*;
import java.util.ArrayList;

public class NhanVien_DAO {

    public NhanVien_DAO() {
    }

    public ArrayList<NhanVien> getAllNV() {
        ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "select * from NhanVien";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString(1);
                String ho = rs.getString(2);
                String ten = rs.getString(3);
                int tuoi = rs.getInt(4);
                boolean phai = rs.getBoolean(5);
                float luong = rs.getFloat(7);
                PhongBan phong = new PhongBan(rs.getString(6));
                NhanVien nv = new NhanVien(maNV,ho,ten,tuoi,phai,phong,luong);
                dsNV.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNV;
    }

    public ArrayList<NhanVien> getNVId(String id) throws SQLException {
        ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "select * from NhanVien where maNV=?";
        PreparedStatement statement =null;
        try {
             statement = con.prepareStatement(sql);
            statement.setString(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString(1);
                String ho = rs.getString(2);
                String ten = rs.getString(3);
                int tuoi = rs.getInt(4);
                boolean phai = rs.getBoolean(5);
                float luong = rs.getFloat(7);
                PhongBan phong = new PhongBan(rs.getString(6));
                NhanVien nv = new NhanVien(maNV,ho,ten,tuoi,phai,phong,luong);
                dsNV.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNV;
    }

    public ArrayList<NhanVien> getNVIdforPhongBan(String idPhongBan) throws SQLException {
        ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "select * from NhanVien where maPhong=  " +idPhongBan+" ";
        Statement statement =null;
        try {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString(1);
                String ho = rs.getString(2);
                String ten = rs.getString(3);
                int tuoi = rs.getInt(4);
                boolean phai = rs.getBoolean(5);
                float luong = rs.getFloat(7);
                PhongBan phong = new PhongBan(rs.getString(6));
                NhanVien nv = new NhanVien(maNV,ho,ten,tuoi,phai,phong,luong);
                dsNV.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            statement.close();
        }
        return dsNV;
    }

    public boolean create(NhanVien nv) throws SQLException {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement pre = null;
        int row = 0;

            pre = con.prepareStatement("insert into" + " NhanVien values(?,?,?,?,?,?,?)");
            pre.setString(1,nv.getMaNV());
            pre.setString(2,nv.getHoNV());
            pre.setString(3,nv.getTenNV());
            pre.setInt(4,nv.getTuoi());
            pre.setBoolean(5,nv.isPhai());
            pre.setFloat(7,nv.getLuong());
            pre.setString(6,nv.getPhong().getMaPhongBan());
            row = pre.executeUpdate();


        return row > 0;
    }

    public boolean update(NhanVien nv) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement pre = null;
        int row = 0;
        try {
            pre = con.prepareStatement("update NhanVien set ho=?,ten=?,tuoi=?,phai=?,maphong=?,tienLuong=?"+"where maNV=?");

            pre.setString(1,nv.getHoNV());
            pre.setString(2,nv.getTenNV());
            pre.setInt(3,nv.getTuoi());
            pre.setBoolean(4,nv.isPhai());
            pre.setFloat(6,nv.getLuong());
            pre.setString(5,nv.getPhong().getMaPhongBan());

            pre.setString(7,nv.getMaNV());
            row = pre.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                pre.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return row > 0;
    }

    public boolean delete(String id) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement pre = null;
        int row = 0;
        try {
            pre = con.prepareStatement("Delete from NhanVien where maNV = ?");
            pre.setString(1,id);
            row = pre.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                pre.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return row > 0;
    }

    public ArrayList<NhanVien> searchMaNV(String id) throws SQLException {
        ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "select * from NhanVien where maNV = ?";
        PreparedStatement statement =null;
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1,id);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString(1);
                String ho = rs.getString(2);
                String ten = rs.getString(3);
                int tuoi = rs.getInt(4);
                boolean phai = rs.getBoolean(5);
                float luong = rs.getFloat(7);
                PhongBan phong = new PhongBan(rs.getString(6));
                NhanVien nv = new NhanVien(maNV,ho,ten,tuoi,phai,phong,luong);
                dsNV.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            statement.close();
        }
        return dsNV;
    }
}
