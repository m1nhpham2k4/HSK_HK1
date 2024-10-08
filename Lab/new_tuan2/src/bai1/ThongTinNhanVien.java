package bai1;

public class ThongTinNhanVien {
	protected String mnv,ho,tennv, phai;
	protected int tuoi;
	protected double tienluong;
	
	public String getPhai() {
		return phai;
	}
	public void setPhai(String phai) {
		this.phai = phai;
	}

	public String getMnv() {
		return mnv;
	}
	public void setMnv(String mnv) throws Exception{
		if(mnv.equals("") || mnv == null)
			throw new Exception("Mã nhân viên không được để trống");
		else
			this.mnv = mnv;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) throws Exception{
		if(ho.equals("") || ho == null)
			throw new Exception("Họ không được để trống");
		else
			this.ho = ho;
	}
	public String getTennv() {
		return tennv;
	}
	public void setTennv(String tennv) throws Exception {
		if(tennv.equals("") || tennv == null)
			throw new Exception("Họ tên không được để trống");
		else
			this.tennv = tennv;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) throws Exception{
		if(tuoi <= 18)
			throw new Exception("Tuổi phải lớn hơn 18");
		else 
			this.tuoi = tuoi;
	}
	public double getTienluong() {
		return tienluong;
	}
	public void setTienluong(double tienluong) {
		this.tienluong = tienluong;
	}
	
	
	
	public ThongTinNhanVien(String mnv, String ho, String tennv, String phai, int tuoi, double tienluong) {
		super();
		this.mnv = mnv;
		this.ho = ho;
		this.tennv = tennv;
		this.tuoi = tuoi;
		this.phai = phai;
		this.tienluong = tienluong;
	}
	@Override
    public boolean equals(Object obj) {
        if (obj instanceof ThongTinNhanVien) {
            ThongTinNhanVien other = (ThongTinNhanVien) obj;
            return mnv.equals(other.mnv);
        }
        return false;
    }
}
