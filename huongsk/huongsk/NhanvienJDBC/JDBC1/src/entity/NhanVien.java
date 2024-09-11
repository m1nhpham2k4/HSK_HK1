package entity;

public class NhanVien {
    private String hoNV;
    private String tenNV;
    private int maNV,tuoi;
    private boolean phai;
    private  double luong;

    public NhanVien(){}

    public NhanVien(String hoNV,String tenNV, int maNV, int tuoi, boolean phai, double luong) {
        this.hoNV = hoNV;
        this.tenNV = tenNV;
        this.maNV = maNV;
        this.tuoi = tuoi;
        this.phai = phai;
        this.luong = luong;
    }
}
