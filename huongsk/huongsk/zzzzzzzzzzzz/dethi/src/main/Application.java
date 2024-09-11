package main;

import GUI.CauThu_GUI;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        CauThu_GUI frm = new CauThu_GUI();
        frm.setVisible(true);
    }
}