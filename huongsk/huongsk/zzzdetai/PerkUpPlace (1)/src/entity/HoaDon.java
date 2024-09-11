package entity;

import java.time.LocalDate;

public class HoaDon {
    private String maHD;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private LocalDate ngayTaoHD;
    private boolean trangThai;
    private Ban ban;
    private double tongHD;

    public HoaDon(String maHD) {
        this.maHD = maHD;
    }
    
	public HoaDon(String maHD, NhanVien nhanVien, KhachHang khachHang, LocalDate ngayTaoHD, boolean trangThai, Ban ban,
			double tongHD) {
		super();
		this.maHD = maHD;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayTaoHD = ngayTaoHD;
		this.trangThai = trangThai;
		this.ban = ban;
		this.tongHD = tongHD;
	}
	
	public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public LocalDate getNgayTaoHD() {
        return ngayTaoHD;
    }

    public void setNgayTaoHD(LocalDate ngayTaoHD) {
        this.ngayTaoHD = ngayTaoHD;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }

	public double getTongHD() {
		return tongHD;
	}

	public void setTongHD(double tongHD) {
		this.tongHD = tongHD;
	}
    
    
}
