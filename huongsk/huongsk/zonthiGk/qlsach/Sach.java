package qlsach;

import java.io.Serializable;
import java.util.Objects;

public class Sach implements Serializable{
	private String ma;
	private String tua;
	private String tacGia;
	private int namXB;
	private String nhaXB;
	private int soTrang;
	private double donGia;
	private String ISBN;
	
	

	

	public Sach(String ma, String tua, String tacGia, int namXB, String nhaXB, int soTrang, double donGia,
			String iSBN) {
		super();
		this.ma = ma;
		this.tua = tua;
		this.tacGia = tacGia;
		this.namXB = namXB;
		this.nhaXB = nhaXB;
		this.soTrang = soTrang;
		this.donGia = donGia;
		this.ISBN = iSBN;
	}

	public Sach(){}




	public String getMa() {
		return ma;
	}





	public void setMa(String ma) {
		this.ma = ma;
	}





	public String getTua() {
		return tua;
	}





	public void setTua(String tua) {
		this.tua = tua;
	}





	public String getTacGia() {
		return tacGia;
	}





	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}





	public int getNamXB() {
		return namXB;
	}





	public void setNamXB(int namXB) {
		this.namXB = namXB;
	}





	public String getNhaXB() {
		return nhaXB;
	}





	public void setNhaXB(String nhaXB) {
		this.nhaXB = nhaXB;
	}





	public int getSoTrang() {
		return soTrang;
	}





	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}





	public double getDonGia() {
		return donGia;
	}





	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}





	public String getISBN() {
		return ISBN;
	}





	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}





	@Override
	public int hashCode() {
		return Objects.hash(ISBN, donGia, ma, namXB, nhaXB, soTrang, tacGia, tua);
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sach other = (Sach) obj;
		return Objects.equals(ma, other.ma);
	}

	
}
