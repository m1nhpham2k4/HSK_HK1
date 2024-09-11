package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DataSummaryGUI extends JPanel{
	

	private GradientBackground pnlOrdersToday;
	private JLabel lblQuantityOT;
	private JLabel lblTitleOT;
	private GradientBackground pnlOrdersWeek;
	private JLabel lblQuantityOW;
	private JLabel lblTitleOW;
	private GradientBackground pnlSalesToday;
	private JLabel lblQuantityST;
	private JLabel lblTitleST;
	private GradientBackground pnlSalesMonth;
	private JLabel lblQuantitySM;
	private JLabel lblTitleSM;
	private JPanel pnlContainTableRecentOrder;
	private JTable tableRecentOrder;
	private JScrollPane scollRecentOrder;
	public JButton btnSales;

	public DataSummaryGUI() {
		this.init();
	}

	private void init() {
		this.setLayout(null);
		this.setBackground(new Color(255, 255, 255));
		
		// Order Today
		pnlOrdersToday = new GradientBackground(new Color(79, 195, 247), new Color(33, 150, 243));
		pnlOrdersToday.setLayout(null);
		pnlOrdersToday.setBounds(26, 3, 448, 210);
		this.add(pnlOrdersToday);
		
		lblQuantityOT = new JLabel("24");
		lblQuantityOT.setFont(new Font("Arial", Font.BOLD, 40));
		lblQuantityOT.setForeground(new Color(255, 255, 255));
		lblQuantityOT.setBounds(38, 48, 372, 47);
		pnlOrdersToday.add(lblQuantityOT);
		
		lblTitleOT = new JLabel("Hóa đơn hôm nay");
		lblTitleOT.setFont(new Font("Arial", 0, 36));
		lblTitleOT.setForeground(new Color(255, 255, 255));
		lblTitleOT.setBounds(38, 120, 372, 90);
		pnlOrdersToday.add(lblTitleOT);
		
		// Order Week
		pnlOrdersWeek = new GradientBackground(new Color(255, 213, 79), new Color(255, 152, 0));
		pnlOrdersWeek.setLayout(null);
		pnlOrdersWeek.setBounds(499, 3, 448, 210);
		this.add(pnlOrdersWeek);
		
		lblQuantityOW = new JLabel("150");
		lblQuantityOW.setFont(new Font("Arial", Font.BOLD, 40));
		lblQuantityOW.setForeground(new Color(255, 255, 255));
		lblQuantityOW.setBounds(38, 48, 372, 47);
		pnlOrdersWeek.add(lblQuantityOW);
		
		lblTitleOW = new JLabel("Hóa đơn trong tuần");
		lblTitleOW.setFont(new Font("Arial", 0, 36));
		lblTitleOW.setForeground(new Color(255, 255, 255));
		lblTitleOW.setBounds(38, 120, 372, 90);
		pnlOrdersWeek.add(lblTitleOW);
		
		// Sales Today
		pnlSalesToday = new GradientBackground(new Color(255, 128, 171), new Color(255, 82, 82));
		pnlSalesToday.setLayout(null);
		pnlSalesToday.setBounds(972, 3, 448, 210);
		this.add(pnlSalesToday);
		
		lblQuantityST = new JLabel("1,000,000 đ");
		lblQuantityST.setFont(new Font("Arial", Font.BOLD, 40));
		lblQuantityST.setForeground(new Color(255, 255, 255));
		lblQuantityST.setBounds(38, 48, 372, 47);
		pnlSalesToday.add(lblQuantityST);
		
		lblTitleST = new JLabel("Doanh thu hôm nay");
		lblTitleST.setFont(new Font("Arial", 0, 36));
		lblTitleST.setForeground(new Color(255, 255, 255));
		lblTitleST.setBounds(38, 120, 372, 90);
		pnlSalesToday.add(lblTitleST);
		
		// Sales Month
		pnlSalesMonth = new GradientBackground(new Color(186, 104, 200), new Color(103, 58, 183));
		pnlSalesMonth.setLayout(null);
		pnlSalesMonth.setBounds(1445, 3, 448, 210);
		this.add(pnlSalesMonth);
		
		lblQuantitySM = new JLabel("12,000,000 đ");
		lblQuantitySM.setFont(new Font("Arial", Font.BOLD, 40));
		lblQuantitySM.setForeground(new Color(255, 255, 255));
		lblQuantitySM.setBounds(38, 48, 372, 47);
		pnlSalesMonth.add(lblQuantitySM);
		
		lblTitleSM = new JLabel("Doanh thu trong tháng");
		lblTitleSM.setFont(new Font("Arial", 0, 36));
		lblTitleSM.setForeground(new Color(255, 255, 255));
		lblTitleSM.setBounds(38, 120, 372, 90);
		pnlSalesMonth.add(lblTitleSM);
		
		pnlContainTableRecentOrder = new JPanel();
		pnlContainTableRecentOrder.setLayout(new BorderLayout());
		pnlContainTableRecentOrder.setBounds(26, 282, 1394, 815);
		this.add(pnlContainTableRecentOrder);
		
		tableRecentOrder = new JTable();
		tableRecentOrder.setModel(new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"mã hóa đơn","mã nhân viên","tên khách hàng","tổng tiền"
				}
		));
		
		scollRecentOrder = new JScrollPane(tableRecentOrder);
		pnlContainTableRecentOrder.add(scollRecentOrder);
		
		btnSales = new JButton("Bán Hàng");
		btnSales.setBackground(new Color(79, 195, 247));
		btnSales.setForeground(new Color(255, 255, 255));
		btnSales.setBorder(null);
		btnSales.setFont(new Font("Arial", Font.BOLD, 40));
		btnSales.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSales.setBounds(1445, 282, 448, 84);
		this.add(btnSales);		
	}
	
	
}
