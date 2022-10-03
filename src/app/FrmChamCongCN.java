package app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Panel;
import java.awt.font.ImageGraphicAttribute;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf;

import connection.ConnectDB;
import dao.DAOCongNhan;
import dao.DAOToSanXuat;
import entity.CongNhan;
import entity.ToSanXuat;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.ListSelectionModel;

public class FrmChamCongCN extends JFrame {
	
	private Panel pMain;
	private JTree JTToSX;
	private DefaultMutableTreeNode root, nodeTo, nodeCN;
//	DAO
	private DAOToSanXuat daoTSX;
	private DAOCongNhan daoCongNhan;
	private JPanel pFormList;
	private JScrollPane scrollList;
	private JList listCN;
	private JTextField txtTimCN;
	private JPanel pNhapThongTin;
	private JLabel lblNhapThongTin;
	private JLabel lblHoTen;
	private JTextField txtHoTenCN;
	private JLabel lblToSX;
	private JTextField txtTenTo;
	private JLabel lblNgay;
	private JDateChooser chooserNgaySinh;
	private JLabel lblChucVu;
	private JComboBox<Object> cboChucVu;
	private FixButton btnLuuChamCong;
	private JLabel lblMaCN;
	private JTextField txtMaCN;
	private JLabel lblMato;
	private JTextField txtMaTo;
	private JLabel lblSoLuong;
	private JTextField txtSoluong;
	private JScrollPane scrollCNTable;
	private JTable tableCN;
	private DefaultTableModel modelNV;
	
	public Panel getFrmChamCong() {
		return pMain;
	}
	
	
	
	public FrmChamCongCN() {
		
		getContentPane().setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1278, 629);
		pMain.setLayout(null);
		getContentPane().add(pMain);
		
		
		/**
		 * Kết nối database
		 */
		try {
			ConnectDB.getinstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
//		Khai bao dao

		daoTSX = new DAOToSanXuat();
		daoCongNhan = new DAOCongNhan();
		
		scrollCNTable = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollCNTable.setToolTipText("Danh sách thông tin nhân viên");
		scrollCNTable.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollCNTable.setBackground(Color.WHITE);
		scrollCNTable.setBounds(426, 182, 832, 402);
		pMain.add(scrollCNTable);
		
		String col[] = {"Mã ","Công Nhân" , "Tổ",  "Ngày ", "Ca làm", "Số lượng " , "Lương ngày"};
		modelNV = new DefaultTableModel(col, 0);

		tableCN = new JTable(modelNV);
		tableCN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableCN.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableCN.setShowHorizontalLines(true); 
		tableCN.setShowGrid(true);
		tableCN.setBackground(Color.WHITE);
		tableCN.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableCN.setSelectionBackground(new Color(164, 44, 167, 30));
		tableCN.setSelectionForeground(new Color(114, 23, 153));
		tableCN.setRowHeight(30);
		tableCN.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableCN.setToolTipText("Chọn nhân viên để thực hiện chức năng");
		
		
		JTableHeader tbHeader = tableCN.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		tbHeader.setToolTipText("Danh sách Ngày công nhân viên");

		tableCN.getColumnModel().getColumn(0).setPreferredWidth(80); 
		tableCN.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableCN.getColumnModel().getColumn(2).setPreferredWidth(80); 
		tableCN.getColumnModel().getColumn(3).setPreferredWidth(130); 
		tableCN.getColumnModel().getColumn(4).setPreferredWidth(130); 
		tableCN.getColumnModel().getColumn(5).setPreferredWidth(130); 
		tableCN.getColumnModel().getColumn(6).setPreferredWidth(130);


		DefaultTableCellRenderer rightRenderer=new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

		scrollCNTable.setViewportView(tableCN);
		
		
		pNhapThongTin = new JPanel();
		pNhapThongTin.setLayout(null);
		pNhapThongTin.setToolTipText("Các thông tin nhân viên cần nhập");
		pNhapThongTin.setBorder(new LineBorder(new Color(114, 23, 153)));
		pNhapThongTin.setBackground(Color.WHITE);
		pNhapThongTin.setBounds(426, 10, 832, 160);
		pMain.add(pNhapThongTin);
		
		lblNhapThongTin = new JLabel("Chấm Công Công Nhân");
		lblNhapThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhapThongTin.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNhapThongTin.setBounds(10, 4, 239, 29);
		pNhapThongTin.add(lblNhapThongTin);
		
		lblHoTen = new JLabel("Họ và tên:");
		lblHoTen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHoTen.setBounds(10, 45, 90, 29);
		pNhapThongTin.add(lblHoTen);
		
		txtHoTenCN = new JTextField();
		txtHoTenCN.setText("Nguyễn Văn Trinh");
		txtHoTenCN.setFont(new Font("Arial", Font.ITALIC, 15));
		txtHoTenCN.setColumns(10);
		txtHoTenCN.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtHoTenCN.setBounds(132, 40, 196, 34);
		pNhapThongTin.add(txtHoTenCN);
		
		lblToSX = new JLabel("Tổ sản xuất:");
		lblToSX.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblToSX.setBounds(6, 83, 98, 29);
		pNhapThongTin.add(lblToSX);
		
		txtTenTo = new JTextField();
		txtTenTo.setText("Tổ 1");
		txtTenTo.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTenTo.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtTenTo.setBounds(132, 81, 196, 32);
		pNhapThongTin.add(txtTenTo);
		
		lblNgay = new JLabel("Ngày:");
		lblNgay.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNgay.setBounds(579, 7, 54, 25);
		pNhapThongTin.add(lblNgay);
		
		
		
		
		chooserNgaySinh = new JDateChooser();
		chooserNgaySinh.getCalendarButton().setToolTipText("Chọn ngày sinh");
		chooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgaySinh.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserNgaySinh.setDateFormatString("dd/MM/yyyy");
		chooserNgaySinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserNgaySinh.setBounds(633, 10, 181, 29);
		pNhapThongTin.add(chooserNgaySinh);
		
		lblChucVu = new JLabel("Ca làm việc:");
		lblChucVu.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblChucVu.setBounds(10, 124, 98, 29);
		pNhapThongTin.add(lblChucVu);
		
		cboChucVu = new JComboBox<Object>(new Object[]{});
		cboChucVu.setToolTipText("Chọn ca làm việc \r\n");
		cboChucVu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboChucVu.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboChucVu.setBackground(Color.WHITE);
		cboChucVu.setBounds(132, 124, 196, 32);
		pNhapThongTin.add(cboChucVu);
		
		btnLuuChamCong = new FixButton("Thêm");
		btnLuuChamCong.setText("Lưu");
		btnLuuChamCong.setForeground(Color.BLACK);
		btnLuuChamCong.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLuuChamCong.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLuuChamCong.setBackground(new Color(57, 210, 247));
		btnLuuChamCong.setBounds(633, 119, 112, 38);
		pNhapThongTin.add(btnLuuChamCong);
		
		lblMaCN = new JLabel("Mã CN:");
		lblMaCN.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblMaCN.setBounds(368, 44, 72, 25);
		pNhapThongTin.add(lblMaCN);
		
		txtMaCN = new JTextField();
		txtMaCN.setText("CN001");
		txtMaCN.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtMaCN.setColumns(10);
		txtMaCN.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtMaCN.setBounds(462, 42, 145, 29);
		pNhapThongTin.add(txtMaCN);
		
		lblMato = new JLabel("Mã Tổ Sản Xuất:");
		lblMato.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblMato.setBounds(368, 81, 83, 25);
		pNhapThongTin.add(lblMato);
		
		txtMaTo = new JTextField();
		txtMaTo.setText("T001");
		txtMaTo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtMaTo.setColumns(10);
		txtMaTo.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtMaTo.setBounds(462, 83, 145, 29);
		pNhapThongTin.add(txtMaTo);
		
		lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSoLuong.setBounds(368, 126, 83, 25);
		pNhapThongTin.add(lblSoLuong);
		
		txtSoluong = new JTextField();
		txtSoluong.setText("100");
		txtSoluong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtSoluong.setColumns(10);
		txtSoluong.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtSoluong.setBounds(462, 122, 145, 33);
		pNhapThongTin.add(txtSoluong);
		
		JScrollPane pTreeToSX = new JScrollPane();
		pTreeToSX.setBorder(new LineBorder(new Color(114, 23, 153)));
		pTreeToSX.setBackground(Color.WHITE);
		pTreeToSX.setBounds(10, 10, 210, 609);
		pMain.add(pTreeToSX);
		
		
		
		root = new DefaultMutableTreeNode("Tổ Sản Xuất");
		ArrayList<ToSanXuat> lstToSX = daoTSX.getDSToSanXuat();
		ArrayList<CongNhan> lstCN = daoCongNhan.getDSCongNhan();
		for (ToSanXuat toSanXuat : lstToSX) {
			nodeTo = new DefaultMutableTreeNode(toSanXuat);
			for(CongNhan cn: lstCN) {
				 if(cn.getToSanXuat().equals(toSanXuat)) {
						nodeCN = new DefaultMutableTreeNode(cn);
						nodeTo.add(nodeCN);		
				 }
			}
			
			root.add(nodeTo);
		
		}
		
		
		
		
		
		
		
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);
		
		JTToSX = new JTree(defaultTreeModel);
		JTToSX.setVisibleRowCount(35);
		JTToSX.setCellRenderer(new FixRenderTree());
		pTreeToSX.setColumnHeaderView(JTToSX);
		
		pFormList = new JPanel();
		pFormList.setBackground(Color.WHITE);
		pFormList.setBorder(new LineBorder(new Color(114, 23, 153)));
		pFormList.setBounds(220, 10, 200, 609);
		pMain.add(pFormList);
		pFormList.setLayout(null);
		
		scrollList = new JScrollPane();
		scrollList.setBounds(0, 30, 200, 550);
		pFormList.add(scrollList);
		
		listCN = new JList();
		scrollList.setViewportView(listCN);
		
		txtTimCN = new JTextField();
		txtTimCN.setBounds(5, 580, 112, 28);
		pFormList.add(txtTimCN);
		txtTimCN.setColumns(10);
		
		JButton btnTimCN = new JButton("Tìm");
		btnTimCN.setBounds(120, 580, 64, 28);
		pFormList.add(btnTimCN);
		
		JLabel lblNewLabel = new JLabel("Danh Sách Công Nhân");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(28, 6, 145, 28);
		pFormList.add(lblNewLabel);
		
		JPanel pControl = new JPanel();
		pControl.setBackground(Color.WHITE);
		pControl.setBounds(426, 584, 832, 39);
		pMain.add(pControl);
		pControl.setLayout(null);
		
		JLabel lblChon = new JLabel("Chọn");
		lblChon.setBounds(290, 7, 48, 20);
		lblChon.setFont(new Font("SansSerif", Font.BOLD, 15));
		pControl.add(lblChon);
		
		JComboBox<Object> cboThang = new JComboBox<Object>(new Object[]{});
		cboThang.setBounds(338, 5, 88, 24);
		cboThang.setToolTipText("Chọn ca làm việc \r\n");
		cboThang.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboThang.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboThang.setBackground(Color.WHITE);
		pControl.add(cboThang);
		
		JComboBox<Object> cboThang_1 = new JComboBox<Object>(new Object[]{});
		cboThang_1.setToolTipText("Chọn ca làm việc \r\n");
		cboThang_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboThang_1.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboThang_1.setBackground(Color.WHITE);
		cboThang_1.setBounds(438, 5, 88, 24);
		pControl.add(cboThang_1);
		
		FixButton btnLoc = new FixButton("Thêm");
		btnLoc.setText("Lọc");
		btnLoc.setForeground(Color.BLACK);
		btnLoc.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLoc.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLoc.setBackground(new Color(204, 204, 204));
		btnLoc.setBounds(541, 4, 107, 35);
		pControl.add(btnLoc);
		
		FixButton btnXuatPhieu = new FixButton("Thêm");
		btnXuatPhieu.setText("In Phiếu Chấm Công");
		btnXuatPhieu.setForeground(Color.BLACK);
		btnXuatPhieu.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnXuatPhieu.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnXuatPhieu.setBackground(new Color(255, 153, 51));
		btnXuatPhieu.setBounds(660, 3, 166, 36);
		pControl.add(btnXuatPhieu);
		
		

		
		
	
		
	}
}        



	



