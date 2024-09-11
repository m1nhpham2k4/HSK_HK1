package entity;

import java.util.Objects;

public class CauThu {
    private String maCauThu,tenCauThu;
    private int tuoi;
    private ViTriThiDau  viTri;

    public CauThu(){

    }

    public CauThu(String maCauThu) {
        this.maCauThu = maCauThu;
    }

    public CauThu(String maCauThu, String tenCauThu, int tuoi, ViTriThiDau viTri) {
        this.maCauThu = maCauThu;
        this.tenCauThu = tenCauThu;
        this.tuoi = tuoi;
        this.viTri = viTri;
    }

    public String getMaCauThu() {
        return maCauThu;
    }

    public void setMaCauThu(String maCauThu) {
        this.maCauThu = maCauThu;
    }

    public String getTenCauThu() {
        return tenCauThu;
    }

    public void setTenCauThu(String tenCauThu) {
        this.tenCauThu = tenCauThu;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public ViTriThiDau getViTri() {
        return viTri;
    }

    public void setViTri(ViTriThiDau viTri) {
        this.viTri = viTri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CauThu cauThu = (CauThu) o;
        return Objects.equals(maCauThu, cauThu.maCauThu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maCauThu);
    }
}
