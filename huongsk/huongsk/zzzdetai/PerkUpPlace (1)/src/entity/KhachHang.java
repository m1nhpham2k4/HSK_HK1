package entity;

public class KhachHang {
    private String maKH;
    private String tenKH;
    private String soDT;
    private double diemTL;

    public KhachHang(String maKH, String tenKH, String soDT, double diemTL) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.soDT = soDT;
        this.diemTL = diemTL;
    }

    public KhachHang() {
    }

    public KhachHang(String maKH) {
        this.maKH = maKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public double getDiemTL() {
        return diemTL;
    }

    public void setDiemTL(double diemTL) {
        this.diemTL = diemTL;
    }
}
