package entity;

public class PhieuDat {
    private String maPhieu;
    private String yeuCau;

    public PhieuDat(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public PhieuDat(String maPhieu, String yeuCau) {
        this.maPhieu = maPhieu;
        this.yeuCau = yeuCau;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getYeuCau() {
        return yeuCau;
    }

    public void setYeuCau(String yeuCau) {
        this.yeuCau = yeuCau;
    }
}
