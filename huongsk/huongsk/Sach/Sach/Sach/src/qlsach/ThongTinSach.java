package qlsach;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class ThongTinSach extends JFrame implements ActionListener,MouseListener{

    private final JLabel ma;
    private final JPanel row2;
    private JTextField maSach, tuaSach, namXB, soTrang, tacGia, NhaXB, donGia, ISBN;
    private JButton them, xoaRong, xoa, sua,luu;
    private JTable table;
    private DefaultTableModel model;
    private JComboBox tim;
    private DSSach list = new DSSach();
    private final String tenfile = "DuLieu.txt";

    public ThongTinSach() {
        super("Quản lí sách");
        //North
        JPanel vien= new JPanel();
        vien.setBorder(BorderFactory.createTitledBorder("Records Editors"));
        //row 1
        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));


        ma = new JLabel("Mã sách: ");
        row1.add(ma);
        row1.add(Box.createHorizontalStrut(50));

        maSach = new JTextField(10);
        row1.add(maSach);
        row1.add(Box.createHorizontalStrut(450));
        vien.add(row1);
        vien.add(Box.createVerticalStrut(10));

        //row2
        row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2,BoxLayout.X_AXIS));
        JLabel tua= new JLabel("Tựa sách:");
        row2.add(tua);
        row2.add(Box.createHorizontalStrut(45));

        tuaSach= new JTextField(30);
        //tua.setPreferredSize(ma.getPreferredSize());
        row2.add(tuaSach);
        row2.add(Box.createHorizontalStrut(40));
        JLabel tg= new JLabel("Tác giả:");
        row2.add(tg);
        row2.add(Box.createHorizontalStrut(50));

        tacGia= new JTextField(30);
        row2.add(tacGia);
        row2.add(Box.createHorizontalStrut(30));
        //row3
        JPanel row3= new JPanel();
        JLabel Namxb = new JLabel("Năm xuất bản:");
        namXB= new JTextField(10);
        JLabel nhaXB = new JLabel("Nhà xuất bản:");
        NhaXB= new JTextField(10);
        row3.setLayout(new BoxLayout(row3, BoxLayout.X_AXIS));
        row3.add(Namxb);
        row3.add(namXB);
        row3.add(nhaXB);
        row3.add(NhaXB);


        vien.setLayout(new BoxLayout(vien, BoxLayout.Y_AXIS));

        vien.add(row2);
        vien.add(row3);
        add(vien,BorderLayout.NORTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,700);
        setVisible(true);
    }

    public static void main(String[] args) {
         new ThongTinSach();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
