package entity;

public class CTHoaDon {
    private SanPham sanPham;
    private HoaDon hoaDon;
    private int soLuong;
    private double donGia;

    public CTHoaDon(SanPham sanPham, HoaDon hoaDon, int soLuong, double donGia) {
        this.sanPham = sanPham;
        this.hoaDon = hoaDon;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public CTHoaDon() {
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}
