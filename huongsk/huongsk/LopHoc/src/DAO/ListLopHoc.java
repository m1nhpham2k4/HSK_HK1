package DAO;

import java.util.ArrayList;

import Entity.LopHoc;

public class ListLopHoc {
	private ArrayList<LopHoc> list = new ArrayList<LopHoc>();

	public ArrayList<LopHoc> getList() {
		return list;
	}

	public void setList(ArrayList<LopHoc> list) {
		this.list = list;
	}

	public ListLopHoc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListLopHoc(ArrayList<LopHoc> list) {
		super();
		this.list = list;
	}
	
}
