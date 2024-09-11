package main;

import java.sql.SQLException;

import gui.CauThu_GUI;

public class Application {
	public static void main(String[] args) throws SQLException {		
		CauThu_GUI frm = new CauThu_GUI();
		frm.setVisible(true);
	}
}
