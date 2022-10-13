package app;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mindfusion.drawing.Colors;

import connection.ConnectDB;
//import dao.DAOLoaiMH;
//import dao.DAOMatHang;
import dao.DAOPhatSinhMa;
//import entity.LoaiMatHang;
//import entity.MatHang;
//import dao.Regex;
//import entity.LoaiMatHang;
//import entity.MatHang;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
//import app.XuatExcels;

public class FrmQLLuongCN extends JFrame implements ActionListener, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private Panel pMain;
	private JTable tblMH;
	private DefaultTableModel modelMatHang;
	private FixButton btnTim;
	private JTextField txtTim;
	private FixButton btnReset;
//	private DAOMatHang daoMH;
//	private DAOLoaiMH daoLMH;
	private JComboBox<String> cboLoaiMH;
	private DAOPhatSinhMa daoPhatSinhMa;
	private JComboBox<Object> cboSapXep;
//	private ArrayList<LoaiMatHang> loaiMH;
	private JRadioButton rdoTheoLuongCN;
	private JRadioButton rdoTheoTo;
	private JRadioButton rdoTheoTenCN;
	private DecimalFormat dfVND;
//	private MatHang mh;
//	private Regex regex;
	private JPanel pNhapThongTin;
	private JLabel lblNhapThongTin;
	private ButtonGroup bgRdo;
	private FixButton btnExcels;
	private JComboBox<String> cboTenCN;
	private JComboBox<String> cboMaCN;
	private JComboBox<String> cboThang;
	private JComboBox<String> cboNam;
	private JTextField txtLuong;
	private FixButton btnTinhLuong;
	private FixButton btnXoaLuong;
	private JRadioButton rdoTheoSoSP;

	public Panel getFrmQLLuongCN() {
		return this.pMain;
	}

//	@SuppressWarnings("deprecation")
	public FrmQLLuongCN() {

//		this.sHeaderMaNV = sHeaderMaNV;
//		this.sHeaderTenNV = sHeaderTenNV;
//		this.dNgayHienTai = dNgayHienTai;

		getContentPane().setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1278, 629);
		pMain.setLayout(null);
		getContentPane().add(pMain);
		// connect database
		try {
			ConnectDB.getinstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// khai bao dao
//		daoLoaiKH = new DAOLoaiKH();
//		daoKhachHang = new DAOKhachHang();
//		daoMaKH = new DAOPhatSinhMa();
//		regex = new Regex();
		
		//dfNgaySinh  
		//dfNgayDangKy  = new SimpleDateFormat("dd/MM/yyyy");

		
		

		pNhapThongTin = new JPanel();
		pNhapThongTin.setBorder(new LineBorder(new Color(114, 23, 153)));
		pNhapThongTin.setBackground(Color.WHITE);
		pNhapThongTin.setBounds(10, 11, 333, 607);
		pMain.add(pNhapThongTin);
		pNhapThongTin.setLayout(null);
		pNhapThongTin.setToolTipText("Lương công nhân");
		
		JLabel lblMaCN = new JLabel("Mã công nhân: ");
		lblMaCN.setBounds(10, 83, 102, 36);
		pNhapThongTin.add(lblMaCN);
		lblMaCN.setFont(new Font("SansSerif", Font.PLAIN, 15));

		cboMaCN = new JComboBox<String>();
		cboMaCN.setEnabled(false);
		cboMaCN.setBounds(122, 82, 201, 37);
		cboMaCN.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pNhapThongTin.add(cboMaCN);
		cboMaCN.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboMaCN.setBackground(Color.WHITE);
		
		JLabel lblTenCN = new JLabel("Tên CN: ");
		lblTenCN.setBounds(10, 143, 102, 36);
		pNhapThongTin.add(lblTenCN);
		lblTenCN.setFont(new Font("SansSerif", Font.PLAIN, 15));

		cboTenCN = new JComboBox<String>();
		cboTenCN.setEnabled(false);
		cboTenCN.setBounds(122, 142, 201, 37);
		cboTenCN.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pNhapThongTin.add(cboTenCN);
		cboTenCN.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboTenCN.setBackground(Color.WHITE);

		JLabel lblThang = new JLabel("Tháng:");
		lblThang.setBounds(10, 203, 102, 36);
		pNhapThongTin.add(lblThang);
		lblThang.setFont(new Font("SansSerif", Font.PLAIN, 15));

		cboThang = new JComboBox<String>();
		cboThang.setBounds(122, 202, 201, 37);
		cboThang.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pNhapThongTin.add(cboThang);
		cboThang.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboThang.setBackground(Color.WHITE);

		lblNhapThongTin = new JLabel("Lương công nhân");
		lblNhapThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhapThongTin.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNhapThongTin.setBounds(10, 11, 292, 29);
		pNhapThongTin.add(lblNhapThongTin);

		JLabel lblSoluongMH = new JLabel("Năm:");
		lblSoluongMH.setBounds(10, 263, 84, 36);
		pNhapThongTin.add(lblSoluongMH);
		lblSoluongMH.setFont(new Font("SansSerif", Font.PLAIN, 15));

		cboNam = new JComboBox<String>();
		cboNam.setBounds(122, 262, 201, 37);
		cboNam.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pNhapThongTin.add(cboNam);
		cboNam.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboNam.setBackground(Color.WHITE);

		JLabel lblSubLMH = new JLabel("Lương: ");
		lblSubLMH.setBounds(10, 323, 102, 35);
		pNhapThongTin.add(lblSubLMH);
		lblSubLMH.setFont(new Font("SansSerif", Font.PLAIN, 15));

		txtLuong = new JTextField();
		txtLuong.setEnabled(false);
		txtLuong.setBounds(122, 322, 201, 37);
		pNhapThongTin.add(txtLuong);
		txtLuong.setBackground(new Color(255, 255, 255));
		txtLuong.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtLuong.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		
		txtLuong.setColumns(20);
		
		
		/**
		 * Tìm kiếm
		 */
		JLabel lblTim = new JLabel("Tìm kiếm:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(350, 11, 90, 35);
		pMain.add(lblTim);
		
		txtTim = new JTextField();
		txtTim.setToolTipText("Thông tin tìm kiếm");
		txtTim.setText("Tìm công nhân theo mã công nhân, tên công nhân, tổ, cccd");
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTim.setForeground(Colors.LightGray);
		txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		txtTim.setBounds(425, 11, 670, 33);
		txtTim.addFocusListener(new FocusAdapter() {	
			@Override
			public void focusGained(FocusEvent e) {
				if(txtTim.getText().equals("Tìm công nhân theo mã công nhân, tên công nhân, tổ, cccd")) {
					txtTim.setFont(new Font("SansSerif", Font.PLAIN, 15));
					txtTim.setForeground(Color.BLACK);
					txtTim.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtTim.getText().equals("")) {
					txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
					txtTim.setForeground(Colors.LightGray);
					txtTim.setText("Tìm công nhân theo mã công nhân, tên công nhân, tổ, cccd");
				}
			}
		});
		pMain.add(txtTim);

		btnTim = new FixButton("Tìm");
		btnTim.setToolTipText("Tìm kiếm mặt hàng");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTim.setBackground(new Color(114, 23, 153));
		btnTim.setBounds(1101, 12, 159, 33);
		Icon iconTim = IconFontSwing.buildIcon(FontAwesome.SEARCH, 20, Color.white);
		btnTim.setIcon(iconTim);
		pMain.add(btnTim);
		
		/**
		 * Nút xuất file Excel
		 * JButton btnExcel
		 * Icon iconExcel
		 */
		btnExcels = new FixButton("Xuất Excel");
		btnExcels.setForeground(Color.WHITE);
		btnExcels.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnExcels.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnExcels.setBackground(new Color(16, 124, 65));
		btnExcels.setBounds(10, 445, 313, 42);
		Icon iconExcel = IconFontSwing.buildIcon(FontAwesome.FILE_EXCEL_O, 20, Color.white);
		btnExcels.setIcon(iconExcel);
		pNhapThongTin.add(btnExcels);

		/**
		 * Các buttons
		 */
		btnTinhLuong = new FixButton("Tính Lương");
		btnTinhLuong.setForeground(Color.WHITE);
		btnTinhLuong.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTinhLuong.setBackground(new Color(57, 210, 247));
		btnTinhLuong.setBounds(10, 385, 313, 42);
		Icon iconThemMH = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, Color.white);
		btnTinhLuong.setIcon(iconThemMH);
		pNhapThongTin.add(btnTinhLuong);

		btnXoaLuong = new FixButton("Xóa");
		btnXoaLuong.setForeground(Color.WHITE);
		btnXoaLuong.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnXoaLuong.setBackground(new Color(0xE91940));
		btnXoaLuong.setBounds(10, 499, 313, 42);
		Icon iconHuyMH = IconFontSwing.buildIcon(FontAwesome.TIMES_CIRCLE, 20, Color.white);
		btnXoaLuong.setIcon(iconHuyMH);
		pNhapThongTin.add(btnXoaLuong);

		btnReset = new FixButton("Làm mới");
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnReset.setBackground(new Color(114, 43, 153));
		btnReset.setBounds(10, 552, 313, 44);
		Icon iconReset = IconFontSwing.buildIcon(FontAwesome.REFRESH, 20, Color.white);
		btnReset.setIcon(iconReset);
		btnReset.setToolTipText("Làm mới toàn bộ chương trình");
		pNhapThongTin.add(btnReset);
		
		/**
		 * Panel sắp xếp
		 */
		JPanel pSapXep = new JPanel();
		pSapXep.setToolTipText("Sắp xếp dữ liệu");
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Sắp xếp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBackground(new Color(171, 192, 238));
		pSapXep.setBounds(350, 49, 909, 47);
		pMain.add(pSapXep);
		pSapXep.setLayout(null);

		cboSapXep = new JComboBox<Object>(new Object[]{"Tăng dần", "Giảm dần"});
		cboSapXep.setBounds(76, 12, 135, 28);
		cboSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboSapXep.setBackground(Color.WHITE);
		cboSapXep.setToolTipText("Sắp xếp theo kiểu tăng dần/ giảm dần");
		pSapXep.add(cboSapXep);

		rdoTheoTenCN = new JRadioButton("Theo tên công nhân");
		rdoTheoTenCN.setBounds(248, 13, 170, 27);
		rdoTheoTenCN.setSelected(true);
		rdoTheoTenCN.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoTenCN.setBackground(new Color(171, 192, 238));
		pSapXep.add(rdoTheoTenCN);
		
		rdoTheoSoSP = new JRadioButton("Theo số sản phẩm");
		rdoTheoSoSP.setBounds(450, 13, 170, 27);
		rdoTheoSoSP.setSelected(true);
		rdoTheoSoSP.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoSoSP.setBackground(new Color(171, 192, 238));
		pSapXep.add(rdoTheoSoSP);

		rdoTheoTo = new JRadioButton("Theo tổ");
		rdoTheoTo.setBounds(630, 13, 100, 27);
		rdoTheoTo.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoTo.setBackground(new Color(171, 192, 238));
		pSapXep.add(rdoTheoTo);

		rdoTheoLuongCN = new JRadioButton("Theo lương ");
		rdoTheoLuongCN.setBounds(735, 13, 135, 27);
		rdoTheoLuongCN.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoLuongCN.setBackground(new Color(171, 192, 238));
		pSapXep.add(rdoTheoLuongCN);

		
		/**
		 * Buttons Group
		 */
		bgRdo=new ButtonGroup();
		bgRdo.add(rdoTheoTenCN);
		bgRdo.add(rdoTheoTo);
		bgRdo.add(rdoTheoLuongCN);
		bgRdo.clearSelection();
		
		/**
		 * Bảng chính
		 */
		String cn [] = {"Mã Lương","Mã CN","Tên Công Nhân", "CCCD", "Tổ", "Số SPSX", "Tháng", "Năm", "Lương"};
		modelMatHang = new DefaultTableModel(cn,0);

		tblMH = new JTable(modelMatHang);
		tblMH.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblMH.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblMH.setShowHorizontalLines(true);
		tblMH.setShowGrid(true);
		tblMH.setBackground(Color.WHITE);
		tblMH.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tblMH.setSelectionBackground(new Color(164, 44, 167, 30));
		tblMH.setSelectionForeground(new Color(114, 23, 153));
		tblMH.setRowHeight(30);
		tblMH.setSelectionBackground(new Color(164, 44, 167,30));
		tblMH.setToolTipText("Bảng mặt hàng");

		JTableHeader tbHeader = tblMH.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));

		JScrollPane spMatHang = new JScrollPane(tblMH, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spMatHang.setBounds(353, 104, 906, 514);
		spMatHang.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		spMatHang.setBackground(new Color(164, 44, 167));
		spMatHang.getHorizontalScrollBar();
		pMain.add(spMatHang);

		tblMH.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblMH.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblMH.getColumnModel().getColumn(2).setPreferredWidth(200);
		tblMH.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblMH.getColumnModel().getColumn(4).setPreferredWidth(80);
		tblMH.getColumnModel().getColumn(5).setPreferredWidth(80);
		tblMH.getColumnModel().getColumn(6).setPreferredWidth(80);
		tblMH.getColumnModel().getColumn(7).setPreferredWidth(80);
		tblMH.getColumnModel().getColumn(8).setPreferredWidth(200);

		DefaultTableCellRenderer rightRenderer=new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		tblMH.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
		tblMH.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
		tblMH.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
		tblMH.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tblMH.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		tblMH.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		tblMH.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		tblMH.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		tblMH.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		spMatHang.setViewportView(tblMH);
		
		/**
		 * Phông nền - background
		 */
//		JLabel lblBackGround=new JLabel("");
//		lblBackGround.setIcon(new ImageIcon("data\\img\\background.png"));
//		lblBackGround.setBounds(0, 0, 1281, 629);
//		Image imgBackGround = Toolkit.getDefaultToolkit().getImage("data\\img\\background.png");
//		Image resizeBG = imgBackGround.getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), 0);
//		lblBackGround.setIcon(new ImageIcon(resizeBG));
//		pMain.add(lblBackGround);

		/**
		 * Load loại mặt hàng vào ComboBox loại mặt hàng
		 */
//		loaiMH = daoLMH.getAllLoaiMatHang();
//		for(LoaiMatHang lmh : loaiMH) {
//			cboLoaiMH.addItem(lmh.getTenLoaiMatHang());
//		}
		/**
		 * Sự kiện buttons, chuột, key
		 */
		btnXoaLuong.addActionListener(this);
		btnTim.addActionListener(this);
		btnReset.addActionListener(this);
		btnExcels.addActionListener(this);
		
		tblMH.addMouseListener(this);

		rdoTheoLuongCN.addActionListener(this);
		rdoTheoTo.addActionListener(this);
		rdoTheoTenCN.addActionListener(this);
		
		rdoTheoLuongCN.addActionListener(this);
		rdoTheoTo.addActionListener(this);
		rdoTheoTenCN.addActionListener(this);
		cboSapXep.addActionListener(this);
		
		txtTim.addKeyListener(this);
		btnXoaLuong.addKeyListener(this);
		btnReset.addKeyListener(this);
		btnTim.addKeyListener(this);
		txtTim.addKeyListener(this);
		/**
		 * Định dạng giá trị
		 */
		dfVND = new DecimalFormat("###,### VND");
		/**
		 * Dữ liệu mẫu
		 */
//		cboLoaiMH.setSelectedIndex(1);
		
		/**
		 * Load dữ liệu lên bảng 
		 */
//		loadTableMH();
	}
	/**
	 * Lấy dữ liệu từ SQL Server nạp vào bảng thông qua vòng lặp for, không nạp vào bảng với loại mặt hàng ngừng kinh doanh
	 */
//	public void loadTableMH() {
//		ArrayList<MatHang> lsMH = daoMH.getDSMatHang();
//		for(MatHang mh : lsMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
//			String check = lMH.getMaLoaiMatHang();
//			if(!"LMH004".equals(check) ) {
//				modelMatHang.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(), dfVND.format(Math.round(mh.getGiaMatHang())) } );
//			}
//		}
//	}
	/**
	 * Xóa toàn bộ bảng
	 */
	public void clearTable() {
		while(tblMH.getRowCount() > 0) {
			modelMatHang.removeRow(0);
		}
	}
	/**
	 * Làm mới toàn bộ chương trình, đặt tất cả về giá trị mặc định 
	 */
	public void LamMoi() {
		txtTim.setText("Tìm mặt hàng theo tên mặt hàng, loại mặt hàng");
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTim.setForeground(Colors.LightGray);
		clearTable();
		cboSapXep.setSelectedIndex(0);
		bgRdo.clearSelection();
//		loadTableMH();
	}
	
	/**
	 * Sự kiện chính
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnReset)) {
			LamMoi();
		}
		if(o.equals(btnXoaLuong)) {
//			updateMHNgungKD();
		}
		if(o.equals(btnTim)) {
//			timMH();
		}
//		if (cboSapXep.getSelectedItem() == "Tăng dần") {
//			if(o.equals(rdoTheoLuongCN)) {
//				sortGiaTangDan(mh);
//			}else if (o.equals(rdoTheoTo)) {
//				sortLMHTangDan(mh);
//			}else if (o.equals(rdoTheoTenCN)) {
//				sortTenMHTangDan(mh); 
//			} 
//		}
//		if (cboSapXep.getSelectedItem() == "Giảm dần") {
//			if(o.equals(rdoTheoLuongCN)) {
//				sortGiaGiamDan(mh);
//			}else if (o.equals(rdoTheoTo) ) {
//				sortLMHGiamDan(mh);
//			}else if (o.equals(rdoTheoTenCN)) {
//				sortTenMHGiamDan(mh);
//			} 
//		}
		if(o.equals(cboSapXep)) {
			bgRdo.clearSelection();
		}
		if(o.equals(btnExcels)) {
			try {
				xuatExcel();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * Thêm mặt hàng vào table và SQL Server
	 */
//	@SuppressWarnings("unlikely-arg-type")
//	public void ThemMH() {
//		String maMH = daoPhatSinhMa.getMaMH();
//		String loaiMH = cboLoaiMH.getSelectedItem().toString();
//		String maLMH = daoLMH.getMaLoaiMHTheoTen(loaiMH);
//		if(tenMH.equals("") || txtSoLuong.equals("") || txtDonGia.equals("") ) {
//			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
//		}else
//			if(regex.regexSoLuong(txtSoLuong)) {
//				if (regex.regexGiaMH(txtDonGia)) {
//					int soluong = Integer.parseInt(txtSoLuong.getText());
//					double dongia = Double.parseDouble(txtDonGia.getText());
//					MatHang mh = new MatHang(maMH, tenMH, soluong, dongia, new LoaiMatHang(maLMH));
//					try {
//						daoMH.ThemMH(mh);
//					} catch (Exception e) {
//						e.printStackTrace();
//						JOptionPane.showMessageDialog(this, "Thêm mặt hàng thất bại!");
//					}
//					clearTable();
//					JOptionPane.showMessageDialog(this, "Thêm mặt hàng thành công!");
//					LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
//					modelMatHang.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(), dfVND.format(Math.round(mh.getGiaMatHang())) } );
//					}
//				}
//			} 
//	}
	/**
	 * Xóa mặt hàng khỏi table, update mặt hàng thành ngừng kinh doanh trên SQL Server
	 */
//	public void updateMHNgungKD() {
//		if (tblMH.getSelectedRow() == -1) {
//			JOptionPane.showMessageDialog(this, "Vui lòng chọn mặt hàng cần xóa");
//		}else {
//			int r = tblMH.getSelectedRow();
//			if(r>0) {
//				int del = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa? ", "Thông báo", JOptionPane.YES_NO_OPTION);
//				if(del == JOptionPane.YES_OPTION) {
//					String maMH1 =  tblMH.getValueAt(r, 0).toString();
//					String maLMH = "LMH004";
//					int soluong = Integer.parseInt(txtSoLuong.getText());
//					double dongia = Double.parseDouble(txtDonGia.getText());
//					MatHang mh = new MatHang(maMH1, tenMH, soluong, dongia, new LoaiMatHang(maLMH));  
//					daoMH.updateMH(mh);
//					clearTable();
//					loadTableMH();
//				}
//			}
//		}
//	}
	/**
	 * Sửa thông tin mặt hàng
	 */
//	public void SuaMH() {
//		int row = tblMH.getSelectedRow();
//		if(row >=0) {
//			int update = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa thông tin mặt hàng  này không?", "Thông báo",
//					JOptionPane.YES_NO_OPTION);
//			if(update == JOptionPane.YES_OPTION) {
//				if(regex.regexTenMH(txtTenMH) && regex.regexSoLuong(txtSoLuong) && regex.regexGiaMH(txtDonGia)) {
//					String maMH = (String) tblMH.getValueAt(row, 0);
//					String tenMH = txtTenMH.getText();
//					String loaiMH = cboLoaiMH.getSelectedItem().toString();
//					String maLMH = daoLMH.getMaLoaiMHTheoTen(loaiMH);
//					int soluong = Integer.parseInt(txtSoLuong.getText());
//					double dongia = Double.parseDouble(txtDonGia.getText());
//					
//					try {
//						
//						MatHang mh = new MatHang(maMH, tenMH, soluong, dongia, new LoaiMatHang(maLMH));  
//						daoMH.updateMH(mh);
//						clearTable();
//						LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
//						modelMatHang.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(), dfVND.format(Math.round(mh.getGiaMatHang())) } );
//						JOptionPane.showMessageDialog(this, "Cập  nhật mặt hàng thành công! ");
//					} catch (Exception e) {
//						e.printStackTrace();
//						JOptionPane.showMessageDialog(this, "Mã mặt hàng không tồn tại.");
//					}	
//
//				}
//			}
//		}else {
//			JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin mặt hàng cần sửa!", "Thông báo",
//					JOptionPane.WARNING_MESSAGE);
//		}
//	}
	/**
	 * Sắp xếp tên mặt hàng tăng dần
	 * @param mh
	 */
//	public void sortTenMHTangDan(MatHang mh) {
//		clearTable();
//		ArrayList<MatHang> lstMH = daoMH.getDSMatHang();
//		Collections.sort(lstMH, new Comparator<MatHang>() {
//
//			@Override
//			public int compare(MatHang o1, MatHang o2) {
//				return o1.getTenMatHang().compareTo(o2.getTenMatHang());
//			}
//		});
//		for (MatHang infoMH : lstMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
//			String check = lMH.getMaLoaiMatHang();
//			if(!"LMH004".equals(check) ) {
//			modelMatHang.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
//			}
//		}
//	}
	/**
	 * Sắp xếp tên mặt hàng giảm dần
	 */
//	public void sortTenMHGiamDan(MatHang mh) {
//		clearTable();
//		ArrayList<MatHang> lstMH = daoMH.getDSMatHang();
//		Collections.sort(lstMH, new Comparator<MatHang>() {
//
//			@Override
//			public int compare(MatHang o1, MatHang o2) {
//				return o2.getTenMatHang().compareTo(o1.getTenMatHang());
//			}
//		});
//		for (MatHang infoMH : lstMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
//			String check = lMH.getMaLoaiMatHang();
//			if(!"LMH004".equals(check) ) {
//			modelMatHang.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
//			}
//		}
//	}
	/**
	 * Sắp xếp loại mặt hàng giảm dần
	 */
//	public void sortLMHGiamDan(MatHang mh){
//		clearTable();
//		ArrayList<MatHang> lstMH = daoMH.sortLMH("DESC");
//		for(MatHang infoMH : lstMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
//			String check = lMH.getMaLoaiMatHang();
//			if(!"LMH004".equals(check) ) {
//				modelMatHang.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
//			}
//		}
//	}
	/**
	 * Sắp xếp loại mặt hàng tăng dần
	 */
//	public void sortLMHTangDan(MatHang mh){
//		clearTable();
//		ArrayList<MatHang> lstMH = daoMH.sortLMH("ASC");
//		for(MatHang infoMH : lstMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
//			String check = lMH.getMaLoaiMatHang();
//			if(!"LMH004".equals(check) ) {
//			modelMatHang.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
//			}
//		}
//	}
	/**
	 *	Sắp xếp giá mặt hàng tăng dần
	 */
//	public void sortGiaTangDan(MatHang mh){
//		clearTable();
//		ArrayList<MatHang> lstMH = daoMH.sortGia("ASC");
//		for(MatHang infoMH : lstMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
//			String check = lMH.getMaLoaiMatHang();
//			if(!"LMH004".equals(check) ) {
//			modelMatHang.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
//			}
//		}
//	}
	/**
	 * Sắp xếp giá mặt hàng giảm dần
	 */
//	public void sortGiaGiamDan(MatHang mh){
//		clearTable();
//		ArrayList<MatHang> lstMH = daoMH.sortGia("DESC");
//		for(MatHang infoMH : lstMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
//			String check = lMH.getMaLoaiMatHang();
//			if(!"LMH004".equals(check) ) {
//				modelMatHang.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
//			}
//		}
//	}
	/**
	 * Tìm mặt hàng theo tên mặt hàng, loại mặt hàng
	 */
	@SuppressWarnings("unlikely-arg-type")
//	public void timMH() {
//		ArrayList<MatHang> lstMH = null;
//		String input = txtTim.getText().trim();
//		String regexTenMH= "^[ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$";
//		if(!txtTim.equals("") && !txtTim.getText().equals("Tìm mặt hàng theo tên mặt hàng, loại mặt hàng")) {
//			if(input.matches(regexTenMH)) {
//				lstMH = daoMH.getTenMH(input);
//				loadTenMH(lstMH);
//			}
//			if(regex.regexTimKiemLoaiMatHang(txtTim)) {
//				lstMH = daoMH.getLMH(input);
//				loadLoaiMH(lstMH);	
//			}else
//			if(lstMH.size() == 0)
//				JOptionPane.showMessageDialog(null,
//						"Thông tin tìm kiếm không hợp lệ!\nThông tin có thể tìm kiếm:\n - Tên mặt hàng. Ví dụ: Bia Heiniken,..."
//								+ "\n - Loại mặt hàng. Ví dụ: Đồ uống, đồ ăn.",
//						"Thông báo", JOptionPane.ERROR_MESSAGE);
//		}
//		else {
//			clearTable();
//			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
//		}
//	}
	/**
	 * Nạp dữ liệu tên mặt hàng vào bảng
	 */
//	public void loadTenMH(ArrayList<MatHang> mh1) {
//		clearTable();
//		ArrayList<MatHang> lsMH = daoMH.getTenMH(txtTim.getText());
//		for(MatHang mh : lsMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
//			modelMatHang.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(), dfVND.format(Math.round(mh.getGiaMatHang())) } );
//		}
//	}
	/**
	 * Nạp dữ liệu loại mặt hàng vào bảng
	 */
//	public void loadLoaiMH(ArrayList<MatHang> mh1) {
//		clearTable();
//		String maLoai = daoLMH.getMaLoaiMHTheoTen(txtTim.getText());
//		ArrayList<MatHang> lsMH = daoMH.getLMH(maLoai);
//		for(MatHang mh : lsMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
//			modelMatHang.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(), dfVND.format(Math.round(mh.getGiaMatHang())) } );
//		}
//	}
	/**
	 * Xuất Excels
	 */
	private void xuatExcel() throws IOException {
//		XuatExcels xuat = new XuatExcels();
		FileDialog fileDialog  = new FileDialog(this ,"Xuất thông tin nhân viên ra Excels", FileDialog.SAVE);
		fileDialog.setFile("Danh sách thông tin mặt hàng");
		fileDialog .setVisible(true);
		String name = fileDialog.getFile();
		String fileName = fileDialog.getDirectory() + name;

		if (name == null) 
			return;
		
		if(!fileName.endsWith(".xlsx")||!fileName.endsWith(".xls")) 
			fileName += ".xlsx";
		
//		xuat.xuatTable(tblMH, "DANH SÁCH THÔNG TIN MẶT HÀNG", fileName);
	}
	/**
	 * Sự kiện click chuột
	 */
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		Object o = e.getSource();
//		if(o.equals(tblMH)) {
//			int row = tblMH.getSelectedRow();	
//			txtSoLuong.setText(modelMatHang.getValueAt(row, 3).toString());
//			try {
//				txtDonGia.setText(dfVND.parse(modelMatHang.getValueAt(row, 4).toString())+"");
//			} catch (ParseException e1) {
//				e1.printStackTrace();
//			}
//			cboLoaiMH.setSelectedItem(modelMatHang.getValueAt(row, 2).toString());

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

//		}	
//	}
//	@Override
//	public void mousePressed(MouseEvent e) {
//
//	}
//	@Override
//	public void mouseReleased(MouseEvent e) {
//
//	}
//	@Override
//	public void mouseEntered(MouseEvent e) {
//
//	}
//	@Override
//	public void mouseExited(MouseEvent e) {
//
//	}
//	@Override
//	public void keyTyped(KeyEvent e) {
//		
//	}
//	@Override
//	public void keyPressed(KeyEvent e) {
//		Object o = e.getSource();
//		int key = e.getKeyCode();
//		if(o.equals(txtTim) && key == KeyEvent.VK_ENTER) {
//			btnTim.doClick();
//		}
//		else if (o.equals(txtSoLuong) && key == KeyEvent.VK_TAB) {
//			cboLoaiMH.requestFocus();
//		}
//		else if (o.equals(cboLoaiMH) && key == KeyEvent.VK_TAB)  {
//			btnThemMH.requestFocus();
//		}
//	}
//	@Override
//	public void keyReleased(KeyEvent e) {
//				
//	}
}
