package tuan1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class congtrunhanchia  extends JFrame implements ActionListener {
    JPanel North,Center,ca,cb,ctinh,ckq,West,South;
    JLabel tieude,la,lb,lkq;
    JTextField ta,tb,tkq;
    JRadioButton cong,tru,nhan,chia;
    ButtonGroup gr= new ButtonGroup();
    JButton giai,xoa,thoat;
    public congtrunhanchia(){
        //north
        super("Cộng - Trừ - Nhân - Chia");
        North=new JPanel();
        tieude= new JLabel("Cộng Trừ Nhân Chia");
        Font font= new Font(Font.SERIF,Font.BOLD,30);
        tieude.setForeground(Color.BLUE);
        tieude.setFont(font);
        North.add(tieude);
        add(North,BorderLayout.NORTH);
        //Center
        Center= new JPanel();
        Box box = new Box(BoxLayout.Y_AXIS);
        Center.setBorder(BorderFactory.createTitledBorder("Tính toán"));

        ca= new JPanel();
        ca.add(la=new JLabel("Nhập a: "));
        ca.add(ta=new JTextField(20));

        cb= new JPanel();
        cb.add(lb=new JLabel("Nhập b: "));
        cb.add(tb= new JTextField(20));

        ctinh= new JPanel();
        ctinh.setBorder(BorderFactory.createTitledBorder("Phép toán"));
        gr.add(cong= new JRadioButton("Cộng"));
        gr.add(tru= new JRadioButton("Trừ"));
        gr.add(nhan= new JRadioButton("Nhân"));
        gr.add(chia= new JRadioButton("Chia"));
        ctinh.setLayout(new GridLayout(2,2));
        ctinh.add(cong);
        ctinh.add(tru);
        ctinh.add(nhan);
        ctinh.add(chia);

        ckq= new JPanel();
        ckq.add(lkq= new JLabel("kết quả: "));
        ckq.add(tkq= new JTextField(30));
        tkq.setEditable(false);

        box.add(ca);
        box.add(cb);

        box.add(ctinh);
        box.add(ckq);
        Center.add(box);
        add(Center,BorderLayout.CENTER);


        //west
        West= new JPanel();
        West.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
        Box box1= new Box(BoxLayout.Y_AXIS);
        box1.add(giai= new JButton("Giải"));
        box1.add(xoa= new JButton("Xóa"));
        box1.add(thoat= new JButton("Thoát"));
        West.add(box1);
        add(West,BorderLayout.WEST);


        //south
        South= new JPanel();
        South.setBackground(Color.pink);
        JPanel blue,red,yl;
        blue = new JPanel();
        blue.setLocation(200,150);
        blue.setBackground(Color.BLUE);
        South.add(blue);

        South.add(red= new JPanel());
        red.setBackground(Color.red);
        red.setLocation(200,150);
        add(South,BorderLayout.SOUTH);

        South.add(yl= new JPanel());
        yl.setBackground(Color.yellow);
        yl.setLocation(200,150);
        add(South,BorderLayout.SOUTH);

        setSize(400,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cong.addActionListener(this);
        tru.addActionListener(this);
        nhan.addActionListener(this);
        chia.addActionListener(this);
        giai.addActionListener(this);
        thoat.addActionListener(this);
        xoa.addActionListener(this);
    }


    public static void main(String[] args) {
        new congtrunhanchia();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object= e.getSource();

        Pattern pattern = Pattern.compile(".*\\D.*");

        String a = ta.getText();
        String b= tb.getText();

        Matcher matchera = pattern.matcher(a);
        Matcher matcherb = pattern.matcher(b);
//
        if (object.equals(thoat)){
            System.exit(0);
        }
        if( b.equals("")||matcherb.matches()){
            JOptionPane.showMessageDialog(this,"vui lòng nhập đủ và đúng kí tự số");
            tb.requestFocus();

        }
        if(a.equals("")||  matchera.matches()){
            JOptionPane.showMessageDialog(this,"vui lòng nhập đủ và đúng kí tự số");
            ta.requestFocus();
        }

         if(object.equals(giai)){
            float soA=Integer.parseInt(a);
            float soB=Integer.parseInt(b);
            float ketqua;
            if(cong.isSelected()){
                ketqua=soA+soB;
                tkq.setText(String.valueOf(ketqua));
            }
            if(tru.isSelected()){
                ketqua=soA-soB;
                tkq.setText(String.valueOf(ketqua));
            }
            if(nhan.isSelected()){
                ketqua=soA*soB;
                tkq.setText(String.valueOf(ketqua));
            }
            if(chia.isSelected()&&soB!=0){
                ketqua=soA/soB;
                tkq.setText(String.valueOf(ketqua));
            }
        }

        if(object.equals(xoa)){
            ta.setText("");
            tb.setText("");
            tkq.setText("");
            ta.requestFocus();
        }
    }

}
