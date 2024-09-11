package entity;

public class Ban {
    private String maBan;
    private int soGhe;
    private int trangThai;

    public Ban(String maBan) {
        this.maBan = maBan;
    }

    public Ban() {
    }

    public Ban(String maBan, int soGhe, int trangThai) {
        this.maBan = maBan;
        this.soGhe = soGhe;
        this.trangThai = trangThai;
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
}
