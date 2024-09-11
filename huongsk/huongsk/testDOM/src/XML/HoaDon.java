package XML;

public class HoaDon {
    private String hoaDonID;
    private String nguoiLap;
    private String congTy;
    private String moTa;
    private int soLuong;

    public HoaDon(String hoaDonID, String nguoiLap, String congTy, String moTa, int soLuong) {
        this.hoaDonID = hoaDonID;
        this.nguoiLap = nguoiLap;
        this.congTy = congTy;
        this.moTa = moTa;
        this.soLuong = soLuong;
    }

    public String getHoaDonID() {
        return hoaDonID;
    }

    public void setHoaDonID(String hoaDonID) {
        this.hoaDonID = hoaDonID;
    }

    public String getNguoiLap() {
        return nguoiLap;
    }

    public void setNguoiLap(String nguoiLap) {
        this.nguoiLap = nguoiLap;
    }

    public String getCongTy() {
        return congTy;
    }

    public void setCongTy(String congTy) {
        this.congTy = congTy;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
