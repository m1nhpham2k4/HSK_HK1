package pheptoan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class pheptoan extends JFrame implements ActionListener{
	JLabel jtd, ja, jb, jkq;
	JPanel pNorth, pCenter, pCenter1, pCenter2, pCenter3, pCenter4, pWest, pSouth;
	JButton giai, xoa, thoat, blue,red,yellow;
	JTextField ta, tb, tkq;
	JRadioButton cong, tru, nhan, chia;
	ButtonGroup gr;
	public pheptoan() {
		
		setTitle("Cộng - Trừ - Nhân - Chia");
		
		pNorth = new JPanel();
		jtd = new JLabel("Cộng Trừ Nhân Chia");
		Font f = new Font(Font.SERIF, Font.BOLD, 30);
		jtd.setFont(f);
		jtd.setForeground(Color.blue);
		pNorth.add(jtd);
		add(pNorth, BorderLayout.NORTH);
		
		pWest = new JPanel();
		pWest.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		Dimension buttonSize = new Dimension(100, 50);

		giai = new JButton("Giải");
		giai.setPreferredSize(buttonSize);

		xoa = new JButton("Xóa");
		xoa.setPreferredSize(buttonSize);

		thoat = new JButton("Thoát");
		thoat.setPreferredSize(buttonSize);

		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(giai);
		box.add(Box.createRigidArea(new Dimension(0, 10)));
		box.add(xoa);
		box.add(Box.createRigidArea(new Dimension(0, 10)));
		box.add(thoat);

		pWest.setBackground(Color.gray);
		pWest.add(box);
		add(pWest, BorderLayout.WEST);
		
		pCenter = new JPanel();
		pCenter1 = new JPanel();
		pCenter2 = new JPanel();
		pCenter3 = new JPanel();
		pCenter4 = new JPanel();
		
		ja = new JLabel("Nhập a");
//		ja.setFont(f);
		ta = new JTextField(15);
		pCenter1.add(ja);
		pCenter1.add(ta);
		
		jb = new JLabel("Nhập b");
//		jb.setFont(f);
		tb = new JTextField(15);
		pCenter2.add(jb);
		pCenter2.add(tb);
		
		pCenter3.setBorder(BorderFactory.createTitledBorder("Phép toán"));
		pCenter3.setLayout(new GridLayout(2,2));
		gr = new ButtonGroup();
		gr.add(cong = new JRadioButton("Cộng"));
		gr.add(tru = new JRadioButton("Trừ"));
		gr.add(nhan = new JRadioButton("Nhân"));
		gr.add(chia = new JRadioButton("Chia"));
		pCenter3.add(cong);
		pCenter3.add(tru);
		pCenter3.add(nhan);
		pCenter3.add(chia);
		
		
		jkq = new JLabel("Kết quả");
//		jkq.setFont(f);
		tkq = new JTextField(15);
		tkq.setEditable(false);
		pCenter4.add(jkq);
		pCenter4.add(tkq);
		
		Box box_center = new Box(BoxLayout.Y_AXIS);
		box_center.add(pCenter1);
		box_center.add(pCenter2);
		box_center.add(pCenter3);
		box_center.add(pCenter4);
		pCenter.setBorder(BorderFactory.createTitledBorder("Tính toán"));
		pCenter.add(box_center);
		add(pCenter, BorderLayout.CENTER);
		
		
		
		pSouth = new JPanel();
		pSouth.setBackground(Color.pink);
		red = new JButton();
		red.setPreferredSize(new Dimension(25,25));
		red.setBackground(Color.red);
		blue = new JButton();
		blue.setPreferredSize(new Dimension(25,25));
		blue.setBackground(Color.blue);
		yellow = new JButton();
		yellow.setPreferredSize(new Dimension(25,25));
		yellow.setBackground(Color.yellow);
		red.addActionListener(this);
		blue.addActionListener(this);
		yellow.addActionListener(this);
		
		
		pSouth.add(red);
		pSouth.add(blue);
		pSouth.add(yellow);
		add(pSouth, BorderLayout.SOUTH);
		
		
		xoa.addActionListener(this);
		giai.addActionListener(this);
		thoat.addActionListener(this);
		
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		new pheptoan();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object src = e.getSource();
		double a,b,kq;
		
		if(src.equals(giai)) {
			a = Double.parseDouble(ta.getText());
			b = Double.parseDouble(tb.getText());
			if(cong.isSelected()) {
				tkq.setText(Double.toString(a + b));
			}
			else if(tru.isSelected()) {
				tkq.setText(Double.toString(a - b));
			}
			else if(nhan.isSelected()) {
				tkq.setText(Double.toString(a * b));
			}
			else if(chia.isSelected()) {
				tkq.setText(Double.toString(a / b));
			}
		}
		else if(src.equals(xoa)) {
	        ta.setText("");
	        tb.setText("");
	        tkq.setText("");
	        gr.clearSelection();
	    } 
		else if (src.equals(thoat)) {
	        System.exit(0);
	    }
		else if(src.equals(red)) {
		    System.out.println("Nút đỏ được nhấn");
		    pSouth.setBackground(Color.red);
		}
		else if(src.equals(blue)) {
		    System.out.println("Nút xanh được nhấn");
		    pSouth.setBackground(Color.blue);
		}
		else if(src.equals(yellow)) {
		    System.out.println("Nút vàng được nhấn");
		    pSouth.setBackground(Color.yellow);
		}	
	}
	
}
