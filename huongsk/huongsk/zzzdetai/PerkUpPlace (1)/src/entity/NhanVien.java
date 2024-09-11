package entity;

import java.time.LocalDate;

public class NhanVien {
    private String maNV;
    private String tenNV;
    private String soDT;
    private LocalDate ngaySinh;
    private String diaChi;
    private String matKhau;
    private String hinhAnh;

    public NhanVien() {
    }

    public NhanVien(String maNV) {
        this.maNV = maNV;
    }

    public NhanVien(String maNV, String tenNV, String soDT, LocalDate ngaySinh, String diaChi, String matKhau, String hinhAnh) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.soDT = soDT;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.matKhau = matKhau;
        this.hinhAnh = hinhAnh;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", soDT=" + soDT + ", ngaySinh=" + ngaySinh + ", diaChi="
				+ diaChi + ", matKhau=" + matKhau + ", hinhAnh=" + hinhAnh + "]";
	}
    
    
}
