package GUI;

import Connect.ConnectDB;
import DAO.CauThu_Dao;
import DAO.ViTriThiDau_Dao;
import entity.CauThu;
import entity.ViTriThiDau;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static javax.swing.JOptionPane.*;

public class CauThu_GUI extends JFrame  implements ActionListener, MouseListener {

    private static final long serialVersionUID = -1554680235689968471L;
    CauThu_Dao cauThu_dao;
    ViTriThiDau_Dao viTriThiDau_dao;

    private JButton btnThem;
    private JButton btnLuu;
    private JButton btnXoa;
    private JButton btnKetThuc;

    private DefaultTableModel dataModel;
    private JTable table;

    private JScrollPane scroll;

    private JComboBox<String> cboViTriThiDau;
    private JTextField txtMaCauThu;
    private JTextField txtTenCauThu;
    private JTextField txtTuoi;

    private JButton btnLoc;

    public CauThu_GUI() throws SQLException {

        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setTitle("Bài thi cuối kỳ - TL HSK Java - HK2 (2020-2021)");
        setSize(630, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Box b, b1, b2, b3, b4, b5, b6, b7, b8;
        add(b = Box.createVerticalBox());
        b.add(Box.createVerticalStrut(10));
        b.add(b1 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b.add(b2 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b.add(b3 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b.add(b4 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b.add(b5 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b.add(b8 = Box.createHorizontalBox());

        b.add(Box.createVerticalStrut(10));
        b.add(b6 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b.add(b7 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b.add(b8 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        JLabel lblTieuDe, lblMaVDV, lblTenVDV, lblViTri, lblTuoi;
        b1.add(lblTieuDe = new JLabel("-THÔNG TIN CẦU THỦ- ", JLabel.CENTER));
        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblTieuDe.setForeground(Color.BLUE);

        b2.add(lblMaVDV = new JLabel("  Mã số cầu thủ:  ", JLabel.RIGHT));
        b2.add(txtMaCauThu = new JTextField());
        b2.add(Box.createHorizontalStrut(50));
        b3.add(lblTenVDV = new JLabel("Tên cầu thủ:  ", JLabel.RIGHT));
        b3.add(txtTenCauThu = new JTextField());
        b3.add(Box.createHorizontalStrut(50));
        b4.add(lblViTri = new JLabel("Vị trí thi đấu:  ", JLabel.RIGHT));
        b4.add(cboViTriThiDau = new JComboBox<String>());
        b4.add(Box.createHorizontalStrut(300));

        b5.add(lblTuoi = new JLabel("Tuổi:  ", JLabel.RIGHT));
        b5.add(txtTuoi = new JTextField());
        b5.add(Box.createHorizontalStrut(50));

//        DefaultComboBoxModel<String> dataModelLop = new DefaultComboBoxModel<String>();
//
//        cboViTriThiDau.setModel(dataModelLop);

        lblViTri.setPreferredSize(lblMaVDV.getPreferredSize());
        lblTenVDV.setPreferredSize(lblMaVDV.getPreferredSize());
        lblTuoi.setPreferredSize(lblMaVDV.getPreferredSize());

        b6.add(Box.createHorizontalStrut(40));
        b6.add(btnThem = new JButton("Thêm Mới "));
        b6.add(Box.createHorizontalStrut(10));
        b6.add(btnLuu = new JButton("Lưu "));
        b6.add(Box.createHorizontalStrut(10));
        b6.add(btnXoa = new JButton("Xóa"));
        b6.add(Box.createHorizontalStrut(50));
        b6.add(btnKetThuc = new JButton("Kết Thúc"));

        String[] tieuDe = { "Mã Số", "Tên cầu thủ", "Tuổi", "vị trí thi đấu" };
        b7.add(scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0))));
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách cầu thủ:"));

        JLabel lblName;
        b8.add(lblName = new JLabel("Họ tên sv: ................massv:.............."));
        lblName.setFont(new Font("Times", Font.ITALIC, 12));
        lblName.setForeground(Color.RED);
        b8.add(Box.createHorizontalStrut(50));
        b8.add(btnLoc= new JButton("   Lọc danh sách cầu thủ theo vị trí thi đấu "));
        btnLoc.setFont(new Font("Times New Roman", Font.ITALIC,14 ));
        btnLoc.setForeground(Color.BLUE);
        table.addMouseListener(this);
        btnKetThuc.addActionListener(this);
        btnLoc.addActionListener(this);
        btnLuu.addActionListener(this);
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);


        upCombobox();
        updateTable();


        btnKetThuc.addActionListener(this);
        btnThem.addActionListener(this);
        scroll.addMouseListener(this);
        btnLuu.addActionListener(this);
        btnXoa.addActionListener(this);
        btnLoc.addActionListener(this);
    }


    public  void updateTable() throws SQLException {
        cauThu_dao = new CauThu_Dao();
        ArrayList<CauThu> list= cauThu_dao.getAllCauThu();
        for(CauThu ct : list){
            dataModel.addRow(new Object[]{ct.getMaCauThu(),ct.getTenCauThu(),ct.getTuoi(),ct.getViTri().getMaViTri()});
        }
    }

    public void upCombobox() throws SQLException {
        viTriThiDau_dao= new ViTriThiDau_Dao();
        List<ViTriThiDau> list= viTriThiDau_dao.getAllViTri();
        for(ViTriThiDau vt :list){
            cboViTriThiDau.addItem(vt.getMaViTri().toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        Object ob = e.getSource();
        if(ob==btnKetThuc){
           int i= JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát?","Xác nhận thoát",YES_NO_OPTION);

            if (i == YES_OPTION) {
                System.exit(0);
            }
        }
        if(ob==btnThem){
            xoaTrang();
        }

        if(ob==btnLuu) {
                luu();
        }

        if(ob==btnXoa){
            int row= table.getSelectedRow();
            String maCT=table.getValueAt(row,0).toString();
            dataModel.removeRow(row);
            cauThu_dao= new CauThu_Dao();
            try {
               cauThu_dao.xoaCT(maCT);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(ob==btnLoc) {
            int sel = cboViTriThiDau.getSelectedIndex();
            String maVT = cboViTriThiDau.getItemAt(sel).toString();
            int n= dataModel.getRowCount();

            for(int i = n - 1; i >=0; i--)
            {
                dataModel.removeRow(i);
            }
           CauThu_Dao cauThu_dao1= new CauThu_Dao();

            try {
                ArrayList<CauThu> ds=   cauThu_dao1.getList(maVT);
                for(CauThu c: ds){
                    dataModel.addRow(new Object[]{c.getMaCauThu(),c.getTenCauThu(),c.getTuoi(),c.getViTri().getMaViTri()});
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }


    public void xoaTrang(){
        txtMaCauThu.setText("");
        txtTuoi.setText("");
        txtTenCauThu.setText("");
        cboViTriThiDau.setSelectedIndex(0);
        txtMaCauThu.requestFocus();
    }

    public boolean luu() {
        String ma = txtMaCauThu.getText();
        String ten = txtTenCauThu.getText();
        int sel = cboViTriThiDau.getSelectedIndex();

        String vt = cboViTriThiDau.getItemAt(sel).toString();




        try {
            if (ten.trim().equals("") || !ten.matches("^([A-Z][a-z]*\\s)*[A-Z][a-z]*$")) {
                JOptionPane.showMessageDialog(this, "Tên bắt đầu bằng chữ hoa,chỉ chứa kí tự chữ , số số cc ");
                txtTenCauThu.requestFocus();
                return false;
            }

            if (ma.trim().equals("") || !ma.matches("^VDV[\\d]{2}$")) {
                JOptionPane.showMessageDialog(this, "Mã không được rỗng và 3 ký tự đầu là “VDV”, tiếp 2 ký số tùy ý ");
                txtMaCauThu.requestFocus();
                return false;
            }
            int tuoi = Integer.parseInt(txtTuoi.getText());
            if (tuoi < 18 || tuoi > 23) {
                JOptionPane.showMessageDialog(this, "tuổi phải từ 18 đến 23 tuổi");
                txtTuoi.requestFocus();
                return false;
            }
            try{
                if (cauThu_dao.addCauThu(new CauThu(ma, ten, tuoi, new ViTriThiDau(vt)))) {
                    dataModel.addRow(new Object[]{ma, ten, tuoi, new ViTriThiDau(vt).getMaViTri().toString()});
                }
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Trung ma");
            }

        }  catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(this, "Tuoi phai la so");
        }
        return true;
    }

        @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        int row= table.getSelectedRow();
        String ma = table.getValueAt(row,0).toString();
        String ten = table.getValueAt(row,1).toString();

        String tuoi = (table.getValueAt(row,2).toString());
        String vt = table.getValueAt(row,3).toString();

        //show table
        txtMaCauThu.setText(ma);
        txtTenCauThu.setText(ten);
        txtTuoi.setText(tuoi);
        if(vt.equals("CB")){
            cboViTriThiDau.setSelectedIndex(0);
        }
        else if(vt.equals("CF")){
            cboViTriThiDau.setSelectedIndex(1);

        }
        else if(vt.equals("CMF")){
            cboViTriThiDau.setSelectedIndex(2);

        }else if (vt.equals("GK")){
            cboViTriThiDau.setSelectedIndex(3);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub


    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }


}