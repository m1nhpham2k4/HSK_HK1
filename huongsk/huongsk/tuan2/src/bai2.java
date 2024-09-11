

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static java.lang.System.exit;

public class bai2 extends JFrame implements ActionListener {
    JButton btn1, btn2, btn3, btn4, btn5, btn6 , btn7, btn8;
    JTextField tf1;
    JCheckBox cb1;
    DefaultListModel listmodelname;
    JList listname;
    public bai2()
    {
        super("Thao tác trên JList");
        //Nor
        JPanel pNor = new JPanel();
        JLabel title = new JLabel("Thao tác trên JList - Checkbox");
        title.setFont(new Font("Time New Roman",Font.BOLD,20));
        title.setForeground(Color.BLUE);
        pNor.add(title);
        add(pNor, BorderLayout.NORTH);
        //Center
        JPanel pCenter = new JPanel();
        pCenter.setBorder(BorderFactory.createTitledBorder("Nhập thông tin:"));
        pCenter.setBorder(BorderFactory.createLineBorder(Color.RED));
        btn8 = new JButton("Nhập");
        pCenter.add(btn8);
        tf1 = new JTextField(10);
        pCenter.add(tf1);
        cb1 = new JCheckBox("Cho nhập số âm");
        pCenter.add(cb1);
        listmodelname = new DefaultListModel<>();
        listname = new JList<>(listmodelname);
        JScrollPane scroll = new JScrollPane(listname);
        scroll.setPreferredSize(new Dimension(300, 200));
        pCenter.add(scroll);
        add(pCenter);
        //Wes
        JPanel pWes = new JPanel();
        pWes.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
        pWes.setBorder(BorderFactory.createLineBorder(Color.RED));
        JPanel pBox = new JPanel();
        pBox.setLayout(new BoxLayout(pBox,BoxLayout.Y_AXIS));

        btn1 = new JButton("Tô đen số chắn");
        pBox.add(btn1);
        pBox.add(Box.createVerticalStrut(10));
        btn2 = new JButton("Tô đen số lẻ");
        pBox.add(btn2);
        pBox.add(Box.createVerticalStrut(10));
        btn3 = new JButton("Tô đen sô nguyên tố");
        pBox.add(btn3);
        pBox.add(Box.createVerticalStrut(10));
        btn4 = new JButton("Bỏ tô đen");
        pBox.add(btn4);
        pBox.add(Box.createVerticalStrut(10));
        btn5 = new JButton("Xóa các giá trị đang tô đen");
        pBox.add(btn5);
        pBox.add(Box.createVerticalStrut(10));
        btn6 = new JButton("Tổng giá trị tring JList");
        pBox.add(btn6);
        pBox.add(Box.createVerticalStrut(10));

        pWes.add(pBox);
        add(pWes,BorderLayout.WEST);

        //south
        JPanel pSouth = new JPanel();
        btn7 = new JButton("Đóng chương trình");
        pSouth.add(btn7);
        pSouth.setBorder(BorderFactory.createLineBorder(Color.red));
        pSouth.setBackground(Color.LIGHT_GRAY);
        add(pSouth,BorderLayout.SOUTH);

        btn8.addActionListener(this::actionPerformed);
        btn1.addActionListener(this::actionPerformed);
        btn2.addActionListener(this::actionPerformed);
        btn3.addActionListener(this::actionPerformed);
        btn4.addActionListener(this::actionPerformed);
        btn5.addActionListener(this::actionPerformed);
        btn6.addActionListener(this::actionPerformed);
        cb1.addActionListener(this::actionPerformed);
        setVisible(true);
        setSize(600,400);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        try {
            if (source.equals(btn8)) {
                String input = tf1.getText().trim();
                if (input.equals("")) {
                    JOptionPane.showMessageDialog(this, "Chưa có dữ liệu");
                } else {
                    if(!cb1.isSelected()){
                        int inputValue = Integer.parseInt(tf1.getText());
                        if(inputValue < 0){
                            JOptionPane.showMessageDialog(this,"Không được nhập số âm");
                            return;
                        }
                    }
                    listmodelname.addElement(input);
                    tf1.setText("");
                }
            } else if (source.equals(btn1)) {
                int[] a = new int[100];
                int j = 0;
                for (int i = 0; i < listmodelname.getSize(); i++) {
                    int n = Integer.parseInt((String) listmodelname.getElementAt(i));
                    if (n % 2 == 0) {
                        a[j++] = i;
                    }
                }
                a = Arrays.copyOf(a, j);
                listname.setSelectedIndices(a);
            } else if(source.equals(btn2)){
                int[] a = new int[100];
                int j = 0;
                for (int i = 0; i < listmodelname.getSize(); i++) {
                    int n = Integer.parseInt((String) listmodelname.getElementAt(i));
                    if (n % 2 == 1) {
                        a[j++] = i;
                    }
                }
                a = Arrays.copyOf(a, j);
                listname.setSelectedIndices(a);
            }else if(source.equals(btn3)){
                int[] a = new int[100];
                int j = 0;
                for (int i = 0; i < listmodelname.getSize(); i++) {
                    int n = Integer.parseInt((String) listmodelname.getElementAt(i));
                    if (isSNT(n)) {
                        a[j++] = i;
                    }
                }
                a = Arrays.copyOf(a, j);
                listname.setSelectedIndices(a);
            } else if (source.equals(btn4)) {
                int []a = new int[0];
                listname.setSelectedIndices(a);
            } else if(source.equals(btn5)){
                int []a = listname.getSelectedIndices();
                for (int i=0; i<a.length; i++) {
                    listmodelname.removeElementAt(a[i]-i);
                }
            }else if(source.equals(btn6)){
                int sum =0;
                for(int i=0; i<listmodelname.size(); i++){
                    sum += Integer.valueOf(listmodelname.getElementAt(i).toString());
                }
                JOptionPane.showMessageDialog(this,"Tổng giá trị của list name là: "+sum);
            }else if(source.equals(btn7)){
                exit(0);
            }
        }catch(NumberFormatException e1){
            JOptionPane.showMessageDialog(this,"Dữ liệu nhâp vào không hợp lệ ");
        }
    }
    public static void main(String[] args) {
        new bai2();
    }
    public boolean isSNT(int a){
        if(a==1) return false;
        if(a<4) return true;
        for (int i=2; i<Math.sqrt(a); i++){
            if(a%i == 0) return false;
        }
        return true;
    }
}

