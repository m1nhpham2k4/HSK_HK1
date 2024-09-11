import java.io.Serializable;
import java.util.Objects;

public class NhanVien implements Serializable {
    private String Ho,tenNV,maNV;
    private String tuoi;
    private String luong;
    private String gioiTinh;

    public NhanVien(){}

    public NhanVien(String ho, String tenNV, String maNV, String tuoi, String luong, String gioiTinh) {
        Ho = ho;
        this.tenNV = tenNV;
        this.maNV = maNV;
        this.tuoi = tuoi;
        this.luong = luong;
        this.gioiTinh = gioiTinh;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String ho) {
        Ho = ho;
    }

    public String getTenNV() {
        return tenNV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTuoi() {
        return tuoi;
    }

    public void setTuoi(String tuoi) {
        this.tuoi = tuoi;
    }

    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }

    public String isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }


    @Override
    public String toString() {
        return "NhanVien{" +
                "Ho='" + Ho + '\'' +
                ", tenNV='" + tenNV + '\'' +
                ", maNV='" + maNV + '\'' +
                ", tuoi=" + tuoi +
                ", luong=" + luong +
                ", gioiTinh=" + gioiTinh +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NhanVien nhanVien = (NhanVien) o;
        return Objects.equals(maNV, nhanVien.maNV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maNV);
    }

}
