import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.LookupOp;

public class NhanVien_App extends JFrame implements ActionListener, MouseListener {
    private final JComboBox<String> comboBox;
    FileDocGhi fi;
    private static final String tenfile= "D:\\bt\\huongsk\\dataNV.txt";
    private final JTable tb;
    private final DefaultTableModel model;
    QuanLi ql = new QuanLi();
    JPanel pNorth,pSouth,pCenter;
    JLabel lHead,lMa,lHo,lTuoi,lTen,lPhai,lTien,lNhap;
    JRadioButton rbtnNam,rbtnNu;
    JButton tim,them,xoa,xoaTrang,luu;
    private JTextField tfMa,tfHo,tfTuoi,tfTen,tfTien,tfNhap;


    NhanVien_App() {
        super("Quản lý nhân viên");
        // North
        pNorth = new JPanel();
        lHead = new JLabel("THÔNG TIN NHÂN VIÊN");
        lHead.setFont(new Font("Time New Roman",Font.BOLD,20));
        lHead.setForeground(Color.blue);
        pNorth.add(lHead);
        add(pNorth,BorderLayout.NORTH);

        // Center
        pCenter = new JPanel();
        // row 1
        JPanel jp1 = new JPanel();
        lMa =  new JLabel("Mã Nhân Viên:");
        tfMa =  new JTextField();
        tfMa.setPreferredSize(new Dimension(600,20));
        jp1.add(lMa);
        jp1.add(tfMa);
        jp1.setLayout(new BoxLayout(jp1, BoxLayout.X_AXIS));
        pCenter.add(jp1);


        //row 2
        JPanel jp2 = new JPanel();
        lHo= new JLabel("Họ:");
        lTen= new JLabel("Tên nhân viên:");

        tfHo= new JTextField();
        tfTen= new JTextField();

        lHo.setPreferredSize(lMa.getPreferredSize());
        tfHo.setPreferredSize(new Dimension(254,20));
        tfTen.setPreferredSize(tfHo.getPreferredSize());

        jp2.add(lHo);
        jp2.add(tfHo);
        jp2.add(lTen);
        jp2.add(tfTen);
        jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));

        pCenter.add(jp2);

        //jp3
        JPanel jp3= new JPanel();
        lTuoi= new JLabel("Tuổi:");
        lTuoi.setPreferredSize(lHo.getPreferredSize());
        tfTuoi= new JTextField();
        tfTuoi.setPreferredSize(new Dimension(480,20));


        jp3.add(lTuoi);
        jp3.add(tfTuoi);


        lPhai= new JLabel("Phái:");
        rbtnNu= new JRadioButton("Nữ");
        rbtnNam= new JRadioButton("Nam");
        ButtonGroup gr = new ButtonGroup();
        rbtnNam.setSelected(true);
        gr.add(rbtnNu);
        gr.add(rbtnNam);
        jp3.add(lPhai);
        jp3.add(rbtnNu);
        jp3.add(rbtnNam);

        jp3.setLayout(new BoxLayout(jp3, BoxLayout.X_AXIS));
        pCenter.add(jp3);


        //row 4
        JPanel jp4 = new JPanel();
        lTien = new JLabel("Tiền lương:");
        lTien.setPreferredSize(lMa.getPreferredSize());
        tfTien= new JTextField();
        tfTien.setPreferredSize(tfMa.getPreferredSize());
        jp4.add(lTien);
        jp4.add(tfTien);
        jp4.setLayout(new BoxLayout(jp4,BoxLayout.X_AXIS));
        pCenter.add(jp4);

        //table


        String[] tieude = {"Mã NV", "Họ", "Tên", "Phái", "Tuổi", "Tiền lương"};
        model = new DefaultTableModel(tieude,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column !=0;
            }
        };


        tb = new JTable(model);

        JScrollPane sc = new JScrollPane(tb);
        sc.setPreferredSize(new Dimension(680,168));

        TableColumn gender= new TableColumn();
        gender=tb.getColumnModel().getColumn(3);
        comboBox = new JComboBox<String>();
        comboBox.addItem("Nam");
        comboBox.addItem("Nữ");
        gender.setCellEditor(new DefaultCellEditor(comboBox));

        pCenter.add(sc);

        add(pCenter, BorderLayout.CENTER);

        //south
        pSouth= new JPanel();
        
        lNhap= new JLabel("Nhập mã số cần tìm:");
        tfNhap= new JTextField();
        tfNhap.setPreferredSize(new Dimension(70,20));
        them= new JButton("Thêm");
        xoaTrang= new JButton("Xóa trắng");
        tim= new JButton("Tìm");
        xoa= new JButton("Xóa");
        luu= new JButton("Lưu");
        pSouth.add(lNhap);
        pSouth.add(tfNhap);
        pSouth.add(tim);
        pSouth.add(them);
        pSouth.add(xoaTrang);
        pSouth.add(xoa);
        pSouth.add(luu);
        
        pSouth.setLayout(new BoxLayout(pSouth,BoxLayout.X_AXIS));
        add(pSouth,BorderLayout.SOUTH);
        
        
        
        // main


        fi= new FileDocGhi();
        try{
            ql=(QuanLi)fi.readFromFile(tenfile);
        }catch (Exception e){
            System.out.println("Không tìm thấy file");
        }
        them.addActionListener(this);
        xoaTrang.addActionListener(this);
        xoa.addActionListener(this);
        tim.addActionListener(this);
        tb.addMouseListener(this);
        luu.addActionListener(this);
        setSize(700,450);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();
        if(ob==them){
            NhanVien newNV= new NhanVien();
            newNV.setGioiTinh(rbtnNam.isSelected()  ?  "Nam":"Nữ");
            newNV.setHo(tfHo.getText());
            newNV.setLuong(tfTien.getText());
            newNV.setTuoi(tfTuoi.getText());
            newNV.setTenNV(tfTen.getText());
            newNV.setMaNV(tfMa.getText());
            if(newNV.getMaNV().equals("")||newNV.getTenNV().equals("")||newNV.getHo().equals("")||newNV.getLuong().equals("")||newNV.getTuoi().equals(""))
            {
                JOptionPane.showMessageDialog(tb, "Thiếu dữ liệu");
            }
            else {
                if(ql.add(newNV)){
                    model.addRow(new Object[]{newNV.getMaNV(),newNV.getHo(),newNV.getTenNV(),newNV.getGioiTinh(),newNV.getTuoi(),newNV.getLuong()});
                }
                else JOptionPane.showMessageDialog(tb,"Đã tồn tại mã nv");
            }
        }
        if(ob==xoaTrang){
            tfMa.setText("");
            tfTen.setText("");
            tfTien.setText("");
            tfHo.setText("");
            tfTuoi.setText("");
            tfNhap.setText("");
        }
        if(ob==xoa){
            if(tb.getSelectedRow()==-1)
                JOptionPane.showMessageDialog(this,"Chọn dòng cần xóa");
            int[] selRow=tb.getSelectedRows();
            for(int i =0;i<selRow.length;i++){
                if(JOptionPane.showConfirmDialog(tb,"bạn có muốn xóa ?","Cảnh báo",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION)
                    break;
                ql.remove((String) model.getValueAt(selRow[i],0));
                model.removeRow(i);
            }
        }
        if(ob==tim){
            tb.clearSelection();
            String maTim=tfNhap.getText();
            if(maTim.trim().equals(""))
                tb.clearSelection();
            boolean timThay=false;
            for(int i = 0; i<tb.getRowCount();i++){
                if(tb.getValueAt(i,0).toString().trim().equals(maTim))
                {
                    tb.setRowSelectionInterval(i,i);
                    timThay=true;
                }
            }
            if(!timThay)
                 JOptionPane.showMessageDialog(tb,"Không tìm thấy!");
        }
        if(ob==luu){
            fi= new FileDocGhi();
            try {
                fi.writeToFile(ql,tenfile);
                fi.readFromFile(tenfile);
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
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
