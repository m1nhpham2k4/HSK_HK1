package tuan1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class ptbac2 extends JFrame implements ActionListener{
    JPanel pNorth, pCenter, pSouth,pCenter1,pCenter2,pCenter4,pCenter3;
    JLabel la, lb, lc, lkq, ltd;
    JTextField ta, tb ,tc, tkq;
    JButton giai, xoa_rong, thoat;

    public ptbac2() {

        setTitle("PT bac 2");
        //north
        pNorth= new JPanel();
        pNorth.setBackground(Color.cyan);
        ltd= new JLabel("GIẢI PHƯƠNG TRÌNH BẬC 2 ");
        Font ft = new Font(Font.SERIF,Font.BOLD,15);
        ltd.setFont(ft);
        pNorth.add(ltd);
        add(pNorth,BorderLayout.NORTH);

        //center
        pCenter= new JPanel();
        Box box = new Box(BoxLayout.Y_AXIS);
        pCenter1= new JPanel();
        pCenter2= new JPanel();
        pCenter3= new JPanel();
        pCenter4= new JPanel();
        pCenter1.add(la = new JLabel("Nhập a:	"));
        pCenter1.add(ta = new JTextField(10));
        pCenter2.add(lb = new JLabel("Nhập b:	"));
        pCenter2.add(tb = new JTextField(10));
        pCenter3.add(lc = new JLabel("Nhập c :	"));
        pCenter3.add(tc = new JTextField(10));
        pCenter4.add(lkq = new JLabel("Kết quả:	"));
        pCenter4.add(tkq = new JTextField(10));
        tkq.setEditable(false);
        box.add(pCenter1);
        box.add(pCenter2);
        box.add(pCenter3);
        box.add(pCenter4);
        pCenter.add(box);
        add(pCenter,BorderLayout.CENTER);

        //south
        pSouth= new JPanel();
        pSouth.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
        pSouth.add(giai= new JButton("Giải"));
        pSouth.add(xoa_rong= new JButton("Xóa Rỗng"));
        pSouth.add(thoat= new JButton("Thoát"));
        add(pSouth,BorderLayout.SOUTH);
        setDefaultCloseOperation(1);
        thoat.addActionListener(this);
        giai.addActionListener(this);
        xoa_rong.addActionListener(this);

        setSize(400, 300);

        setVisible(true);
    }
    public static void main(String[] args) {
        new ptbac2();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();

        float ketqua=0;

        if(object.equals(thoat))
            System.exit(0);
        if(object.equals(xoa_rong))
        {
            ta.setText("");
            tb.setText("");
            tc.setText("");
            tkq.setText("");
        }

        if(object.equals(giai)){
            String a= ta.getText();
            float soa=Float.parseFloat(a);
            String b= tb.getText();
            float sob=Float.parseFloat(b);
            String c= tc.getText();
            float soc=Float.parseFloat(c);
            float delta = sob * sob - 4 * soa * soc;
            Pattern pattern = Pattern.compile(".*\\D.*");
            Matcher matchera = pattern.matcher(a);
            Matcher matcherb = pattern.matcher(b);
            Matcher matcherc = pattern.matcher(c);


            if(b.equals("")|| !matcherb.matches()){
                JOptionPane.showMessageDialog(this,"Vui lòng nhập đúng kiểu dữ liệu");
                tb.requestFocus();
            }
            if(c.equals("")|| !matcherc.matches()){
                JOptionPane.showMessageDialog(this,"Vui lòng nhập đúng kiểu dữ liệu");
                tc.requestFocus();
            }
            if(a.equals("")|| matchera.matches()){
                JOptionPane.showMessageDialog(this,"Vui lòng nhập đúng kiểu dữ liệu");
                ta.requestFocus();
            }

            if(soa==0){
                JOptionPane.showMessageDialog(this,"a phải != 0");
                ta.requestFocus();
            }
            if(delta>0){
                    float kq1= (float) ((-sob + Math.sqrt(delta)) / (2 * soa));
                    float kq2= (float) ((-sob - Math.sqrt(delta)) / (2 * soa));
                    tkq.setText("x1 = "+String.valueOf(kq1) + "; x2 = "+String.valueOf(kq2));
            }
            if(delta==0) {
                    float kq=-sob / (2*soa);
                    tkq.setText("x = "+ String.valueOf(kq));
            }
            if(delta<0){
                JOptionPane.showMessageDialog(this,"Phương trình vô nghiệm");
            }

        }
    }
}
