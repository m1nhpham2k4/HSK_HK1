package ptbac2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ptbac2 extends JFrame implements ActionListener{

	JPanel pNorth, pSouth, pCenter, pCenter1, pCenter2, pCenter3, pCenter4;
	JLabel la, lb, lc, lkq, ltd;
	JTextField ta, tb,tc, tkq;
	JButton giai, xoa_rong, thoat;
	public ptbac2() {
		setTitle("Phuong Trinh Bac Hai");
		
		pNorth = new JPanel();
		pNorth.setBackground(Color.cyan);
		ltd = new JLabel("GIẢI PHƯƠNG TRÌNH BẬC HAI");
		Font f = new Font(Font.SERIF, Font.BOLD,15);
		ltd.setFont(f);
		pNorth.add(ltd);
		add(pNorth, BorderLayout.NORTH);
		
		pCenter1 = new JPanel();
		pCenter1.setLayout(new BoxLayout(pCenter1, BoxLayout.X_AXIS));
		la = new JLabel("Nhập a");
		la.setFont(f);
		ta = new JTextField(15);
		pCenter1.add(la);
		pCenter1.add(Box.createRigidArea(new Dimension(18,0)));
		pCenter1.add(ta);
		
		pCenter2 = new JPanel();
		pCenter2.setLayout(new BoxLayout(pCenter2, BoxLayout.X_AXIS));
		lb = new JLabel("Nhập b");
		lb.setFont(f);
		tb = new JTextField(15);
		pCenter2.add(lb);
		pCenter2.add(Box.createRigidArea(new Dimension(18,0)));
		pCenter2.add(tb);
		
		pCenter3 = new JPanel();
		pCenter3.setLayout(new BoxLayout(pCenter3, BoxLayout.X_AXIS));
		lc = new JLabel("Nhập c");
		lc.setFont(f);
		tc = new JTextField(15);
		pCenter3.add(lc);
		pCenter3.add(Box.createRigidArea(new Dimension(19,0)));
		pCenter3.add(tc);
		
		pCenter4 = new JPanel();
		pCenter4.setLayout(new BoxLayout(pCenter4, BoxLayout.X_AXIS));
		lkq = new JLabel("Kết quả");
		lkq.setFont(f);
		tkq = new JTextField(15);
		pCenter4.add(lkq);
		pCenter4.add(Box.createRigidArea(new Dimension(14, 0)));
		pCenter4.add(tkq);
		tkq.setEditable(false);
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(pCenter1);
		box.add(pCenter2);
		box.add(pCenter3);
		box.add(pCenter4);
		
		pCenter = new JPanel();
		pCenter.add(box);
//		pCenter.add(pCenter1);
//		pCenter.add(pCenter2);
//		pCenter.add(pCenter3);
//		pCenter.add(pCenter4);
		add(pCenter, BorderLayout.CENTER);
		
		
		pSouth = new JPanel();
		pSouth.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		pSouth.add(giai = new JButton("Giải"));
		pSouth.add(xoa_rong = new JButton("Xóa rỗng"));
		pSouth.add(thoat = new JButton("Thoát"));
		
		add(pSouth, BorderLayout.SOUTH);
		thoat.addActionListener(this);
        giai.addActionListener(this);
        xoa_rong.addActionListener(this);
		
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		new ptbac2();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int a,b,c,delta;
		double kq,x1,x2;
		DecimalFormat fmt = new DecimalFormat("0.#");
		a = Integer.parseInt(ta.getText());
		b = Integer.parseInt(tb.getText());
		c = Integer.parseInt(tc.getText());
		Object src = e.getSource();
		if(src.equals(giai)) {
			if(a == 0) {
				if(b == 0) {
					if(c == 0) {
						tkq.setText("Phương trình vô số nghiệm");
					}
					else
						tkq.setText("Phương trình vô nghiệm");
				}
			}
			else {
				delta = b * b - (4 * a * c);
				if(delta < 0) {
					tkq.setText("Phương trình vô nghiệm");
				}
				else if(delta == 0) {
					x1 = x2 = -1.0 * b / (2 * a);
					tkq.setText(Double.toString(x1));
				}
				else {
					x1 = (-1.0 * b + Math.sqrt(delta)) / (2 * a);
					x2 = (-1.0 * b - Math.sqrt(delta)) / (2 * a);
					tkq.setText("x1 = " + Double.toString(x1) + " x2 = " + Double.toString(x2) );
				}
			}
		}
		else if(src.equals(xoa_rong)) {
			ta.setText("");
			tb.setText("");
			tc.setText("");
		}
		else if(src.equals(thoat)) {
			System.exit(EXIT_ON_CLOSE);
		}
		
	}

}

