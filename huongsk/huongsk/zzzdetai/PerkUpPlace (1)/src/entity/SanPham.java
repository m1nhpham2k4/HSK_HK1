package entity;

public class SanPham {
    private String maSP;
    private String tenSP;
    private String loaiSP;
    private double donGia;
    private String hinhAnh;
    private boolean trangThai;

    public SanPham() {
    }

    public SanPham(String maSP) {
        this.maSP = maSP;
    }
    
    public SanPham(String maSP,String tenSP) {
    	this.maSP = maSP;
        this.tenSP = tenSP;
    }

    public SanPham(String maSP, String tenSP, String loaiSP, double donGia, String hinhAnh,
			boolean trangThai) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.loaiSP = loaiSP;
		this.donGia = donGia;
		this.hinhAnh = hinhAnh;
		this.trangThai = trangThai;
	}

	public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    
    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
    
    
}
