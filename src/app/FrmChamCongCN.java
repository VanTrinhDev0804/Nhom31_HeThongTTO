package app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.ImageGraphicAttribute;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf;

import connection.ConnectDB;
import custom.FixButton;
import custom.FixRenderTree;
import dao.DAOCongNhan;
import dao.DAOPhieuChamCong;
import dao.DAOToSanXuat;
import entity.ChamCongCN;
import entity.CongNhan;
import entity.ToSanXuat;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;

public class FrmChamCongCN extends JFrame implements ActionListener, TreeSelectionListener,MouseListener  {
	
	private Panel pMain;
	private JTree JTToSX;
	private DefaultMutableTreeNode root, nodeTo, nodeCN;
//	DAO
	private DAOToSanXuat daoTSX;
	private DAOCongNhan daoCongNhan;
	private DAOPhieuChamCong daoPhieuChamCong;
	private JPanel pFormList;
	private JScrollPane scrollList;

	private JTextField txtTimCN;
	private JPanel pNhapThongTin;
	private JLabel lblNhapThongTin;
	private JLabel lblHoTen;
	private JTextField txtHoTenCN;
	private JLabel lblToSX;
	private JTextField txtTenTo;
	private JLabel lblNgay;
	private JDateChooser chooserNgay;
	private JLabel lblChucVu;
	private JComboBox<Object> cboCaLamViec;
	private FixButton btnLuuChamCong,btnCCTo,btnCCMulti;
	private JLabel lblMaCN;
	private JTextField txtMaCN;
	private JLabel lblMato;
	private JTextField txtMaTo;
	private JLabel lblSoLuong;
	private JTextField txtSoluong;
	private JScrollPane scrollCNTable;
	private JTable tableCN;
	private DefaultTableModel modelChamCong;
	
	
	private SimpleDateFormat dfNgay;
	private DecimalFormat dfLuong;
	private Date dNow;
	private LocalDate now;
	private int ngay;
	private int thang;
	private int nam;
	private JButton btnResetList;
	
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
		daoPhieuChamCong = new DAOPhieuChamCong();
		
		/**
		 * Định dạng ngày, lương trong bảng
		 */
		dfNgay=new SimpleDateFormat("dd/MM/yyyy");
		dfLuong=new DecimalFormat("##,###,###");
		
//		ngay hien tai
		now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue();
		nam = now.getYear();
		
		dNow = new Date(nam-1900,thang-1,ngay);
		
		
		scrollCNTable = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollCNTable.setToolTipText("Danh sách thông tin nhân viên");
		scrollCNTable.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollCNTable.setBackground(Color.WHITE);
		scrollCNTable.setBounds(368, 182, 890, 402);
		pMain.add(scrollCNTable);
		
		String col[] = {"Mã ","Công Nhân" , "Tổ",  "Ngày ", "Ca làm", "Số lượng " , "Lương ngày"};
		modelChamCong = new DefaultTableModel(col, 0);

		tableCN = new JTable(modelChamCong);
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
		pNhapThongTin.setBounds(368, 10, 890, 160);
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
		txtHoTenCN.setEditable(false);
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
		txtTenTo.setEditable(false);
		txtTenTo.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTenTo.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtTenTo.setBounds(132, 81, 196, 32);
		pNhapThongTin.add(txtTenTo);
		
		lblNgay = new JLabel("Ngày:");
		lblNgay.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNgay.setBounds(579, 7, 54, 25);
		pNhapThongTin.add(lblNgay);
		
		
		
		
		chooserNgay = new JDateChooser();
		chooserNgay.getCalendarButton().setToolTipText("Chọn ngày sinh");
		chooserNgay.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgay.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserNgay.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserNgay.setDateFormatString("dd/MM/yyyy");
		chooserNgay.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserNgay.setBounds(633, 10, 181, 29);
		chooserNgay.setDate(dNow);
		chooserNgay.setEnabled(false);
		pNhapThongTin.add(chooserNgay);
		
		lblChucVu = new JLabel("Ca làm việc:");
		lblChucVu.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblChucVu.setBounds(10, 124, 98, 29);
		pNhapThongTin.add(lblChucVu);
		
		cboCaLamViec = new JComboBox<Object>(new Object[]{});
		cboCaLamViec.setModel(new DefaultComboBoxModel(new String[] {"Ca 1", "Ca 2", "Ca 3"}));
		cboCaLamViec.setToolTipText("Chọn ca làm việc \r\n");
		cboCaLamViec.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboCaLamViec.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboCaLamViec.setBackground(Color.WHITE);
		cboCaLamViec.setBounds(132, 124, 196, 32);
		pNhapThongTin.add(cboCaLamViec);
		
		btnLuuChamCong = new FixButton("Lưu");
		btnLuuChamCong.setText("Lưu");
		btnLuuChamCong.setForeground(Color.BLACK);
		btnLuuChamCong.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLuuChamCong.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLuuChamCong.setBackground(new Color(57, 210, 247));
		btnLuuChamCong.setBounds(633, 119, 112, 38);
		pNhapThongTin.add(btnLuuChamCong);
		
		
		btnCCTo = new FixButton("Lưu Tổ");
		btnCCTo.setText("Lưu Tổ");
		btnCCTo.setForeground(Color.BLACK);
		btnCCTo.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnCCTo.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnCCTo.setBackground(new Color(57, 210, 247));
		btnCCTo.setBounds(633, 119, 112, 38);
		btnCCTo.setVisible(false);
		pNhapThongTin.add(btnCCTo);
		
		btnCCMulti = new FixButton("Lưu");
		btnCCMulti.setText("Lưu");
		btnCCMulti.setForeground(Color.BLACK);
		btnCCMulti.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnCCMulti.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnCCMulti.setBackground(new Color(57, 210, 247));
		btnCCMulti.setBounds(633, 119, 112, 38);
		btnCCMulti.setVisible(false);
		pNhapThongTin.add(btnCCTo);
		
		
		lblMaCN = new JLabel("Mã CN:");
		lblMaCN.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblMaCN.setBounds(368, 44, 72, 25);
		pNhapThongTin.add(lblMaCN);
		
		txtMaCN = new JTextField();
		txtMaCN.setEditable(false);
		txtMaCN.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtMaCN.setColumns(10);
		txtMaCN.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtMaCN.setBounds(462, 42, 145, 29);
		pNhapThongTin.add(txtMaCN);
		
		lblMato = new JLabel("Mã Tổ Sản Xuất:");
		lblMato.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblMato.setBounds(368, 81, 83, 25);
		pNhapThongTin.add(lblMato);
		
		txtMaTo = new JTextField();
		txtMaTo.setEditable(false);
		txtMaTo.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtMaTo.setColumns(10);
		txtMaTo.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtMaTo.setBounds(462, 83, 145, 29);
		pNhapThongTin.add(txtMaTo);
		
		lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSoLuong.setBounds(368, 126, 83, 25);
		pNhapThongTin.add(lblSoLuong);
		
		txtSoluong = new JTextField();
		txtSoluong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtSoluong.setColumns(10);
		txtSoluong.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtSoluong.setBounds(462, 122, 145, 33);
		pNhapThongTin.add(txtSoluong);
		
		JScrollPane pTreeToSX = new JScrollPane();
		pTreeToSX.setBorder(new LineBorder(new Color(114, 23, 153)));
		pTreeToSX.setBackground(Color.WHITE);
		pTreeToSX.setBounds(17, 10, 339, 568);
		pMain.add(pTreeToSX);
		
		
		loadDSToSX2Jtree();
		
		
		
		
		
		
		
		
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);
		
		JTToSX = new JTree(defaultTreeModel);
		JTToSX.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTToSX.setVisibleRowCount(35);
		JTToSX.setCellRenderer(new FixRenderTree());
		pTreeToSX.setColumnHeaderView(JTToSX);
		

		
		JPanel pControlJList = new JPanel();
		pControlJList.setLayout(null);
		pControlJList.setBackground(Color.WHITE);
		pControlJList.setBounds(17, 578, 339, 45);
		pMain.add(pControlJList);
		
		
		txtTimCN = new JTextField();
		txtTimCN.setLocation(23, 6);
		txtTimCN.setSize(136, 30);
		pControlJList.add(txtTimCN);
		txtTimCN.setColumns(10);
		
		JButton btnTimCN = new JButton("Tìm");
		btnTimCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimCN.setLocation(171, 2);
		btnTimCN.setSize(81, 39);
		pControlJList.add(btnTimCN);
		
		btnResetList = new JButton("\r\n");
		btnResetList.setBounds(264, 6, 55, 35);
		pControlJList.add(btnResetList);

		
		JLabel lblNewLabel = new JLabel("Danh Sách Công Nhân");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(28, 6, 145, 28);
	
		
		JPanel pControl = new JPanel();
		pControl.setBackground(Color.WHITE);
		pControl.setBounds(426, 584, 832, 39);
		pMain.add(pControl);
		pControl.setLayout(null);
		
		JComboBox<Object> cboThang = new JComboBox<Object>(new Object[]{});
		cboThang.setModel(new DefaultComboBoxModel
				(new String[] {"Tháng ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cboThang.setBounds(338, 5, 88, 24);
		cboThang.setToolTipText("Chọn Tháng \r\n");
		cboThang.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboThang.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboThang.setBackground(Color.WHITE);
		pControl.add(cboThang);
		
		JComboBox<Object> cboNam = new JComboBox<Object>(new Object[]{});
		cboNam.setModel(new DefaultComboBoxModel
				(new String[] {"Năm", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		cboNam.setToolTipText("Chọn Năm\r\n");
		cboNam.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboNam.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboNam.setBackground(Color.WHITE);
		cboNam.setBounds(438, 5, 88, 24);
		pControl.add(cboNam);
		
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
		
		
		
		
//  xử lý sự kiện chọn tổ
		JTToSX.addTreeSelectionListener(this);
		tableCN.addMouseListener(this);
		
		
		
//		btn click
		btnLuuChamCong.addActionListener(this);
		btnCCTo.addActionListener(this);
		
		
//		load ds
	
	}



	private void loadDSToSX2Jtree() {
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
		
	}



	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode) JTToSX.getLastSelectedPathComponent();
	    
	    if(node == null)
	    	return;
	    
	    
	    
	    Object nodeObject = node.getUserObject();
	    if(nodeObject instanceof ToSanXuat) {
	    	ToSanXuat toSanXuat = (ToSanXuat) nodeObject;
	    	
	    	ArrayList<CongNhan> listCNTo = new ArrayList<CongNhan>();
	    	

	    	listCNTo = daoCongNhan.getDSCongNhanCungTo(toSanXuat.getMaTo());
//	    	loadListCNCungTo2JTree(listCNTo);
	    	addTo2FormThongin(toSanXuat);
	    	activeCCBasic(false);
	    }
	    if(nodeObject instanceof CongNhan) {
	    	CongNhan cNhan = (CongNhan) nodeObject;
			activeCCBasic(true);
			addThongTinCongNhan2Form(cNhan);
			loadDSChamCongNhan(cNhan);
			resetStatusCC(checkChamCong(cNhan));
	    }
	    
	}



	private void addTo2FormThongin(ToSanXuat toSanXuat) {
		lblNhapThongTin.setText("Chấm Công cho "+ toSanXuat.getTenTo());
		txtMaTo.setText(toSanXuat.getMaTo());
		txtTenTo.setText(toSanXuat.getTenTo());
		
		
		
	}



	protected void resetStatusCC(boolean checkChamCong) {
		if(checkChamCong == false) {
			btnLuuChamCong.setText("Đã Chấm Công!");
			btnLuuChamCong.setBackground(new Color(102, 205, 170));
			btnLuuChamCong.setToolTipText("Đã Chấm Công Cho Ngày" + dNow);
			
		}
		else {
			btnLuuChamCong.setText("Lưu");
			btnLuuChamCong.setBackground(new Color(57, 210, 247));
			
		}
		btnLuuChamCong.setEnabled(checkChamCong);
		
	}



	protected void activeCCBasic(boolean check) {
		lblHoTen.setVisible(check);
		lblMaCN.setVisible(check);
		txtHoTenCN.setVisible(check);
		txtMaCN.setVisible(check);
		btnLuuChamCong.setVisible(check);
		btnLuuChamCong.setVisible(check);
		btnCCTo.setVisible(!check);
	}



	protected boolean checkChamCong(CongNhan cNhan) {
		ArrayList<ChamCongCN> lisctCCCN = daoPhieuChamCong.getChamCongCongNhan(cNhan.getMaCN());
		java.util.Date date = chooserNgay.getDate();
		
		Date ngayFormat=new Date(date.getYear(), date.getMonth(), date.getDate());

		if(lisctCCCN.size() >=1 ) {
			for (ChamCongCN info : lisctCCCN) {
				if(info.getNgayLam().equals(ngayFormat)) {
					return false;
				}

			}
		}
		return true;
	
		
		
	}



	protected void loadDSChamCongNhan(CongNhan cNhan) {
		// TODO Auto-generated method stub
		removeDSChamCong(modelChamCong);
		ArrayList<ChamCongCN> lisctCCCN = daoPhieuChamCong.getChamCongCongNhan(cNhan.getMaCN());

		
		
		for (ChamCongCN info : lisctCCCN) {
			modelChamCong.addRow(new Object[] {
					info.getMaC(), cNhan.getTenCN(), cNhan.getToSanXuat().getTenTo(),
				dfNgay.format(info.getNgayLam()), info.getCaLam(), info.getSoLuong(), info.getLuongNgay()
				
			
			});
		}
	}



	protected void addThongTinCongNhan2Form(CongNhan cn) {
		lblNhapThongTin.setText("Chấm Công Công Nhân");
		txtHoTenCN.setText(cn.getTenCN());
		txtMaCN.setText(cn.getMaCN());
		txtMaTo.setText(cn.getToSanXuat().getMaTo());
		txtTenTo.setText(cn.getToSanXuat().getTenTo());
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e. getSource();
		
		if(o.equals(btnLuuChamCong)) {
			addChamCongNhanVien();
		}
		
		if(o.equals(btnCCTo)) {
			luuChamCongToCN();
		}
		
	}
	
	
	
	private void luuChamCongToCN() {
		String maTo = txtMaTo.getText();
		String caLam = cboCaLamViec.getSelectedItem().toString();
		java.util.Date date = chooserNgay.getDate();
		Date ngayFormat=new Date(date.getYear(), date.getMonth(), date.getDate());
		
		
		String soLuong = txtSoluong.getText();

		ArrayList<CongNhan> lstCNTo = daoCongNhan.getDSCongNhanCungTo(maTo);
		
		if(soLuong.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			txtSoluong.requestFocus();
		}
		else {
				int sl = Integer.parseInt(soLuong) ;
				float giaSX = 1000;
				float luongNgay = tinhLuongNgay(soLuong , giaSX);
				
			for ( CongNhan cn : lstCNTo) {
				
				ChamCongCN input = new ChamCongCN();
				input.setMaC(cn.getMaCN());
				input.setCaLam(caLam);
				input.setNgayLam(ngayFormat);
				input.setSoLuong(sl);
				input.setLuongNgay(luongNgay);
				
				if(checkChamCong(cn)) {
					try {
						
						if(daoPhieuChamCong.themCCNV(input)) {
							JOptionPane.showMessageDialog(this, "Chấm công thành công cho " + txtTenTo.getText(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						}
						
						
						}catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(this, "Chấm công thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
						}
				}
				
				
				
			}
		}
	
		
	}



	private void removeDSChamCong(DefaultTableModel defaultTableModel) {
		while(tableCN.getRowCount() > 0){
			modelChamCong.removeRow(0);
		}
	}


	private void addChamCongNhanVien() {
		String  ma = txtMaCN.getText();
		String tenCN = txtHoTenCN.getText();
		String tenTo = txtTenTo.getText();
		String calam = cboCaLamViec.getSelectedItem().toString();
		String soLuong = txtSoluong.getText();
		java.util.Date date = chooserNgay.getDate();
		Date ngayFormat=new Date(date.getYear(), date.getMonth(), date.getDate());
		String ngay =dfNgay.format(ngayFormat);
		

		

		if(soLuong.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			txtSoluong.requestFocus();
		}
		else {
//			TINH LUONG NGAY
			float giaSX = 1000;
			float luongNgay = tinhLuongNgay(soLuong , giaSX);
			String luong = dfLuong.format(Math.round(luongNgay));
			
			int sl = Integer.parseInt(soLuong) ;
			
			ChamCongCN info = new ChamCongCN();
			info.setMaC(ma);
			info.setCaLam(calam);
			info.setNgayLam(ngayFormat);
			info.setSoLuong(sl);
			info.setLuongNgay(luongNgay);
			
			
			try {
				
					daoPhieuChamCong.themCCNV(info);
			}catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Chấm công thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
			resetAll();
			modelChamCong.addRow(new Object[] {
				ma, tenCN, tenTo, ngay, calam, soLuong,luong 
			});
			
			
			
		}
	
		
		
		
		
		
		
	}






	private void resetAll() {
		txtHoTenCN.setText("");
		txtMaCN.setText("");
		txtMaTo.setText("");
		txtSoluong.setText("");
		txtTenTo.setText("");
		cboCaLamViec.setSelectedItem("Ca 1");
		
		
	}



	private float tinhLuongNgay(String soLuong, float giaSX) {
		float result = 0;
		float sl = Float.parseFloat(soLuong);
		
		result = sl * giaSX;
		
		// TODO Auto-generated method stub
		return result;
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		chooserPhieuChamCong();
		
	}



	private void chooserPhieuChamCong() {
		int selectRow = tableCN.getSelectedRow();
		if(selectRow >= 0) {
			String ma = (String) tableCN.getValueAt(selectRow, 0);
			String tenCN = (String) tableCN.getValueAt(selectRow,1 );
			String to = (String) tableCN.getValueAt(selectRow, 2);
			String ngay = (String) tableCN.getValueAt(selectRow, 3);
			String ca = (String) tableCN.getValueAt(selectRow, 4);
			String soLuong = (String) String.valueOf(tableCN.getValueAt(selectRow, 5));
			String luongNgay = (String) String.valueOf(tableCN.getValueAt(selectRow, 6));
			

			txtMaCN.setText(ma);
			txtHoTenCN.setText(tenCN);
			txtTenTo.setText(to);
			txtSoluong.setText(soLuong);
			cboCaLamViec.setSelectedItem(ca);
			try {
				chooserNgay.setDate(dfNgay.parse(ngay));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}        



	



