package bai1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.print.attribute.standard.Fidelity;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Gui implements ActionListener , Serializable {

    private String[] colNames = {"Mã NV","Họ","Tên","Phái","Tuổi","Tiền Lương"};
    private NhanVien[] cells = {new NhanVien("1111", "Nguyễn" ,"Hoàng", "Nam" ,"26"  ,"4,500.0 $"),
            new NhanVien("2222", "Lê" ,"Thu", "Nữ" ,"28"  ,"5,000.0 $"),
            new NhanVien("3333", "Hoàng" ,"Lê", "Nam" ,"30"  ,"5,000.0 $"),
            new NhanVien("4444", "Trần" ,"Lan", "Nữ" ,"27" ,"3,500.0 $")
    };

    private ManageNhanVien mNV;
    private JFrame frame;
    private DefaultTableModel model;
    private JPanel pNorth;
    private JLabel labelHeader;
    private JPanel pCenter;
    private JPanel pID;
    private JLabel labelID;
    private JTextField textfID;
    private JPanel pName;
    private JLabel labelSurname;
    private JLabel labelName;
    private JTextField textfName;
    private JTextField textfSurname;
    private JPanel pAgeGender;
    private JLabel labelAge;
    private JLabel labelGender;
    private JTextField textfAge;
    private JRadioButton rbNam;
    private JRadioButton rbNu;
    private ButtonGroup groupGender;
    private JPanel pSalary;
    private JLabel labelSalary;
    private JTextField textfSalary;
    private JPanel pContainer;
    private JPanel pSouth;
    private JPanel pLeftSouth;
    private JLabel labelIDFind;
    private JTextField textfIDFind;
    private JButton btnIDFind;
    private JPanel pRightSouth;
    private JButton btnAdd;
    private JButton btnClear;
    private JButton btnDelete;
    private JButton btnSave;
    private JTable table;
    private JScrollPane scPaneTable;
    private TableColumn columnGender;
    private JComboBox<String> comboBox;

    public Gui()
    {
        frame = new JFrame();
        frame.setTitle("^-^");
        frame.setSize(700, 450);
        frame.setLocationRelativeTo(null);
//		mNV
        mNV = new ManageNhanVien();

//		NORTH
        pNorth = new JPanel();

        labelHeader = new JLabel("THÔNG TIN NHÂN VIÊN", JLabel.CENTER);
        labelHeader.setFont(new Font(null, Font.BOLD, 20));
        labelHeader.setForeground(Color.blue);

        pNorth.add(labelHeader);

//		CENTER
        pCenter = new JPanel();

//		id
        pID = new JPanel();
        labelID = new JLabel("Mã nhân viên:");
        textfID = new JTextField();
        textfID.setPreferredSize(new Dimension(600, 20));

        pID.add(labelID);
        pID.add(textfID);

//		name
        pName = new JPanel();
        labelSurname = new JLabel("Họ: ");
        labelName = new JLabel("Tên nhân viên: ");

        textfSurname = new JTextField();
        textfName = new JTextField();

        labelSurname.setPreferredSize(labelID.getPreferredSize());
        textfSurname.setPreferredSize(new Dimension(253, 20));
        textfName.setPreferredSize(textfSurname.getPreferredSize());

        pName.add(labelSurname);
        pName.add(textfSurname);
        pName.add(labelName);
        pName.add(textfName);


//		age and gender
        pAgeGender = new JPanel();
        labelAge = new JLabel("Tuổi: ");
        labelGender = new JLabel("Phái: ");
        textfAge = new JTextField();
        rbNam = new JRadioButton("Nam");
        rbNu = new JRadioButton("Nữ");
        groupGender = new ButtonGroup();
        groupGender.add(rbNam);
        groupGender.add(rbNu);
        rbNam.setSelected(true);

        labelAge.setPreferredSize(labelID.getPreferredSize());
        textfAge.setPreferredSize(new Dimension(462, 20));

        pAgeGender.add(labelAge);
        pAgeGender.add(textfAge);
        pAgeGender.add(labelGender);
        pAgeGender.add(rbNam);
        pAgeGender.add(rbNu);


//		salary

        pSalary = new JPanel();
        labelSalary = new JLabel("Tiền lương:");
        textfSalary = new JTextField();
        pSalary.add(labelSalary);
        pSalary.add(textfSalary);

        labelSalary.setPreferredSize(labelID.getPreferredSize());
        textfSalary.setPreferredSize(new Dimension(600, 20));

        pCenter.add(pID);
        pCenter.add(pName);
        pCenter.add(pAgeGender);
        pCenter.add(pSalary);

//		table
        model = new DefaultTableModel(colNames, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                // TODO Auto-generated method stub
                return column != 0;
            }
        };
//		model.addRow(cells);
        for (NhanVien ele : cells) {
            model.addRow(ele.data());
            mNV.add(ele);
        }
        table = new JTable(model);
        scPaneTable = new JScrollPane(table);
        scPaneTable.setPreferredSize(new Dimension(680,168));
        scPaneTable.setBorder(BorderFactory.createLineBorder(null));

        columnGender = table.getColumnModel().getColumn(3);
        comboBox = new JComboBox<String>();
        comboBox.addItem("Nam");
        comboBox.addItem("Nữ");
        columnGender.setCellEditor(new DefaultCellEditor(comboBox));

        pCenter.add(scPaneTable);

//		South

        pSouth = new JPanel();
        pLeftSouth = new JPanel();
        labelIDFind = new JLabel("Nhập mã số cần tìm:");
        textfIDFind = new JTextField();
        btnIDFind = new JButton("Tìm");

        textfIDFind.setPreferredSize(new Dimension(185,20));

        pLeftSouth.add(labelIDFind);
        pLeftSouth.add(textfIDFind);
        pLeftSouth.add(btnIDFind);

        pRightSouth = new JPanel();
        btnAdd = new JButton("Thêm");
        btnClear = new JButton("Xóa trắng");
        btnDelete = new JButton("Xóa");
        btnSave = new JButton("Lưu");

        pRightSouth.add(btnAdd);
        pRightSouth.add(btnClear);
        pRightSouth.add(btnDelete);
        pRightSouth.add(btnSave);

//		pLeftSouth.setBorder(BorderFactory.createLineBorder(null));
//		pRightSouth.setBorder(BorderFactory.createLineBorder(null));
//
        pSouth.add(pLeftSouth);
        pSouth.add(pRightSouth);
        pSouth.setBorder(BorderFactory.createLineBorder(null));

//		Container

        pContainer = new JPanel(new BorderLayout());
        pContainer.add(pNorth, BorderLayout.NORTH);
        pContainer.add(pCenter, BorderLayout.CENTER);
        pContainer.add(pSouth, BorderLayout.SOUTH);

//		listener

        btnIDFind.addActionListener(this);
        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);
        btnDelete.addActionListener(this);
        btnSave.addActionListener(this);

//		Frame



        frame.add(pContainer);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub


//		find
        if (e.getSource() == btnIDFind)
        {
            table.clearSelection();
            String idSearch = textfIDFind.getText();
            if (idSearch.trim().length() == 0)
            {
                table.clearSelection();
            }
            else
            {
                for (int i = 0; i < table.getRowCount(); ++i)
                {
                    if (table.getValueAt(i, 0).toString().equals(idSearch))
                    {
                        table.setRowSelectionInterval(i, i);
                    }
                }
                if (table.getSelectedRowCount() == 0)
                {
                    JOptionPane.showMessageDialog(table, "Không tìm thấy");
                }
            }
        }

//		add
        if (e.getSource() == btnAdd)
        {
            NhanVien newNV = new NhanVien();
            newNV.setHo(textfSurname.getText());
            newNV.setMaNV(textfID.getText());
            newNV.setTen(textfName.getText());
            newNV.setTuoi(textfAge.getText());
            newNV.setPhai(rbNam.isSelected()? "Nam" : "Nữ");
            newNV.setTienLuong(textfSalary.getText());
            if(newNV.getMaNV().equals("")||newNV.getHo().equals("")||newNV.getTen().equals("")||newNV.getTuoi().equals("")||newNV.getPhai().equals("")||newNV.getTienLuong().equals(""))
            {
                    JOptionPane.showMessageDialog(table, "Thiếu dữ liệu");
            }
            else
            {
                if(mNV.add(newNV))
                {
                    model.addRow(newNV.data());
                    System.out.println(mNV.toString());
                }
                else
                {
                    JOptionPane.showMessageDialog(table, "Nhân viên có mã NV đã tồn tại");
                }
            }

        }

//		clear
        if (e.getSource() == btnClear)
        {
            textfAge.setText(null);
            textfID.setText(null);
            textfName.setText(null);
            textfIDFind.setText(null);
            textfSurname.setText(null);
            textfSalary.setText(null);
        }

//		delete
        if (e.getSource() == btnDelete)
        {
            if (table.getSelectedRow() == -1)
            {
                JOptionPane.showMessageDialog(table, "Chưa chọn dòng cần xóa");
            }
            else
            {
                if(JOptionPane.showConfirmDialog(table,"Bạn có muốn xóa các dòng này không?","Canh bao",JOptionPane.YES_NO_CANCEL_OPTION)== JOptionPane.YES_OPTION) {
                    int[] selRows = table.getSelectedRows();
                    for (int i = selRows.length - 1; i >= 0; --i)
                    {
                        mNV.remove(model.getValueAt(selRows[i], 0).toString());
                        model.removeRow(selRows[i]);
                    }
                    System.out.println(mNV.toString());
                }
            }
        }

        if (e.getSource() == btnSave)
        {
            if (table.getSelectedRow() == -1)
            {
                JOptionPane.showMessageDialog(table, "Chưa chọn dòng cần lưu");
            }
            else
            {
                if(JOptionPane.showConfirmDialog(table,"Bạn có muốn lưu các dòng này không?","Canh bao",JOptionPane.YES_NO_CANCEL_OPTION)== JOptionPane.YES_OPTION) {
                    int[] selRows = table.getSelectedRows();
                    for (int i = selRows.length - 1; i >= 0; --i)
                    {
                        NhanVien newNV = new NhanVien();
                        newNV.setMaNV(model.getValueAt(selRows[i], 0).toString());
                        newNV.setHo(model.getValueAt(selRows[i], 1).toString());
                        newNV.setTen(model.getValueAt(selRows[i], 2).toString());
                        newNV.setTuoi(model.getValueAt(selRows[i], 3).toString());
                        newNV.setPhai(model.getValueAt(selRows[i], 4).toString());
                        newNV.setTienLuong(model.getValueAt(selRows[i], 5).toString());
                        System.out.println(newNV.toString());
                        if(mNV.update(newNV.getMaNV(), newNV.getHo(), newNV.getTen(), newNV.getPhai(), newNV.getTuoi(), newNV.getTienLuong()))
                        {
                            JOptionPane.showMessageDialog(table, "lưu thành công");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(table, "lưu thất bại");
                        }
                    }
                    System.out.println(mNV.toString());
                }
            }
        }
    }
}
