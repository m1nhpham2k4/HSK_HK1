package bai1;

import java.util.Objects;

public class NhanVien {
    private String maNV;
    private String ho;
    private String ten;
    private String phai;
    private String tuoi;
    private String tienLuong;



    public NhanVien() {
        super();
        // TODO Auto-generated constructor stub
    }
    public NhanVien(String maNV, String ho, String ten, String phai, String tuoi, String tienLuong) {
        super();
        setMaNV(maNV);
        setHo(ho);
        setTen(ten);
        setPhai(phai);
        setTuoi(tuoi);
        setTienLuong(tienLuong);
    }

    public String getMaNV() {
        return maNV;
    }
    public String getHo() {
        return ho;
    }
    public String getTen() {
        return ten;
    }
    public String getPhai() {
        return phai;
    }
    public String getTuoi() {
        return tuoi;
    }
    public String getTienLuong() {
        return tienLuong;
    }



    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    public void setHo(String ho) {
        this.ho = ho;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public void setPhai(String phai) {
        this.phai = phai;
    }
    public void setTuoi(String tuoi) {
        this.tuoi = tuoi;
    }
    public void setTienLuong(String tienLuong) {
        this.tienLuong = tienLuong;
    }
    public Object[] data()
    {
        return new Object[] {maNV, ho, ten, phai, tuoi, tienLuong};
    }
    @Override
    public String toString() {
        return "NhanVien [maNV=" + maNV + ", ho=" + ho + ", ten=" + ten + ", phai=" + phai + ", tuoi=" + tuoi
                + ", tienLuong=" + tienLuong + "]";
    }
    @Override
    public int hashCode() {
        return maNV.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NhanVien other = (NhanVien) obj;
        return Objects.equals(maNV, other.maNV);
    }


}
