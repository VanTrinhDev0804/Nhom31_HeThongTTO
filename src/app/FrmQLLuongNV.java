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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import dao.DAOCTLuongCB;
import dao.DAONhanVien;
import dao.DAOPhieuChamCong;
import dao.DAOPhieuLuongNV;
import dao.Regex;
import entity.NhanVien;
import entity.PhieuLuongNV;
//import entity.LoaiMatHang;
//import entity.MatHang;
//import dao.Regex;
//import entity.LoaiMatHang;
//import entity.MatHang;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class FrmQLLuongNV extends JFrame implements ActionListener, MouseListener, KeyListener, ItemListener {
	private static final long serialVersionUID = 1L;
	private Panel pMain;
	private JTable tblLuong;
	private DefaultTableModel modelLuong;
	private FixButton btnTim;
	private JTextField txtTim;
	private FixButton btnReset;
	private DAOPhieuLuongNV daoPhieuLuongNV;

	private JComboBox<Object> cboSapXep;
	private JRadioButton rdoTheoLuongCN;
	private JRadioButton rdoTheoTo;
	private JRadioButton rdoTheoTenCN;
	private Regex regex;
	private JPanel pNhapThongTin;
	private JLabel lblNhapThongTin;
	private ButtonGroup bgRdo;
	private FixButton btnExcels;
	private JComboBox<String> cboTenNV;
	private JComboBox<String> cboMaNV;
	private JComboBox<String> cboNam;
	private JTextField txtLuong;
	private FixButton btnTinhLuong;
	private JRadioButton rdoTheoSoNgayCong;
	private DAONhanVien daoNhanVien;
	private NhanVien nv;
	private DAOCTLuongCB daoCTLuongCB;
	private DecimalFormat dfLuong;
	private SimpleDateFormat dfNam;
	private SimpleDateFormat dfThang;
	private JRadioButton rdoTheoTenNV;
	private JRadioButton rdoTheoChucVu;
	private JRadioButton rdoTheoLuongNV;
	private DAOPhieuChamCong daoCCNV;
	private JTextField txtThang;
	private JTextField txtNam;
	private SimpleDateFormat dfDate;
	private SimpleDateFormat dfDate1;
	private SimpleDateFormat dfThang1;

	public Panel getFrmQLLuongNV() {
		return this.pMain;
	}

//	@SuppressWarnings("deprecation")
	public FrmQLLuongNV() {

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
		daoNhanVien = new DAONhanVien();
		daoPhieuLuongNV = new DAOPhieuLuongNV();
		daoCTLuongCB = new DAOCTLuongCB();
		daoCCNV = new DAOPhieuChamCong();
//		daoLoaiKH = new DAOLoaiKH();
//		daoKhachHang = new DAOKhachHang();
//		daoMaKH = new DAOPhatSinhMa();
		regex = new Regex();
		dfLuong = new DecimalFormat("##,###,###");
		dfNam = new SimpleDateFormat("yyyy");
		dfThang = new SimpleDateFormat("MM/yyyy");
		dfThang1 = new SimpleDateFormat("MM");
		dfDate = new SimpleDateFormat("dd/MM/yyyy");
		dfDate1 = new SimpleDateFormat("yyyy-MM-dd");
		// dfNgaySinh
		// dfNgayDangKy = new SimpleDateFormat("dd/MM/yyyy");

		pNhapThongTin = new JPanel();
		pNhapThongTin.setBorder(new LineBorder(new Color(114, 23, 153)));
		pNhapThongTin.setBackground(Color.WHITE);
		pNhapThongTin.setBounds(10, 11, 333, 607);
		pMain.add(pNhapThongTin);
		pNhapThongTin.setLayout(null);
		pNhapThongTin.setToolTipText("L????ng nh??n vi??n");

		JLabel lblMaNV = new JLabel("M?? nh??n vi??n: ");
		lblMaNV.setBounds(10, 83, 102, 36);
		pNhapThongTin.add(lblMaNV);
		lblMaNV.setFont(new Font("SansSerif", Font.PLAIN, 15));

		cboMaNV = new JComboBox<String>();
		cboMaNV.addItem("T???t c???");
		cboMaNV.setBounds(122, 82, 201, 37);
		cboMaNV.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pNhapThongTin.add(cboMaNV);
		cboMaNV.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboMaNV.setBackground(Color.WHITE);

		JLabel lblTenNV = new JLabel("T??n NV: ");
		lblTenNV.setBounds(10, 143, 102, 36);
		pNhapThongTin.add(lblTenNV);
		lblTenNV.setFont(new Font("SansSerif", Font.PLAIN, 15));

		cboTenNV = new JComboBox<String>();
		cboTenNV.setEnabled(false);
		cboTenNV.addItem("T???t c???");
		cboTenNV.setBounds(122, 142, 201, 37);
		cboTenNV.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pNhapThongTin.add(cboTenNV);
		cboTenNV.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboTenNV.setBackground(Color.WHITE);

		JLabel lblThang = new JLabel("Th??ng:");
		lblThang.setBounds(10, 203, 102, 36);
		pNhapThongTin.add(lblThang);
		lblThang.setFont(new Font("SansSerif", Font.PLAIN, 15));

		txtThang = new JTextField(dfThang.format(new Date()));
		txtThang.setBounds(122, 202, 201, 37);
		txtThang.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pNhapThongTin.add(txtThang);
		txtThang.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtThang.setBackground(Color.WHITE);
		txtThang.setEnabled(false);

		lblNhapThongTin = new JLabel("L????ng nh??n vi??n");
		lblNhapThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhapThongTin.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNhapThongTin.setBounds(10, 11, 292, 29);
		pNhapThongTin.add(lblNhapThongTin);

		JLabel lblNam = new JLabel("N??m:");
		lblNam.setBounds(10, 263, 84, 36);
		pNhapThongTin.add(lblNam);
		lblNam.setFont(new Font("SansSerif", Font.PLAIN, 15));

		txtNam = new JTextField(dfNam.format(new Date()));
		txtNam.setBounds(122, 262, 201, 37);
		txtNam.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pNhapThongTin.add(txtNam);
		txtNam.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtNam.setBackground(Color.WHITE);
		txtNam.setEnabled(false);

//		Load cbb thang

		ArrayList<NhanVien> lsNhanVien = daoNhanVien.getAllDanhSachNV();
		for (NhanVien lnv : lsNhanVien) {
			cboMaNV.addItem(lnv.getMaNV());
		}
//		 Load ten nhan vien theo ma :
//		for(NhanVien lnv : lsNhanVien) {
//			cboMaNV.addItem(lnv.getTenNV());
//		}

		JLabel lblSubLMH = new JLabel("L????ng: ");
		lblSubLMH.setBounds(10, 323, 102, 35);
		pNhapThongTin.add(lblSubLMH);
		lblSubLMH.setFont(new Font("SansSerif", Font.PLAIN, 15));

		txtLuong = new JTextField();
		txtLuong.setEnabled(false);
		txtLuong.setBounds(122, 322, 201, 37);
		pNhapThongTin.add(txtLuong);
//		txtLuong.setBackground(new Color(255, 255, 255));
		txtLuong.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtLuong.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));

		txtLuong.setColumns(20);

		JLabel lbltitle = new JLabel("B???NG L????NG NH??N VI??N");
		lbltitle.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lbltitle.setBounds(550, 30, 450, 56);
		lbltitle.setForeground(new Color(164, 44, 167));
		pMain.add(lbltitle);
//		/**
//		 * T??m ki???m
//		 */
//		JLabel lblTim = new JLabel("T??m ki???m:");
//		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
//		lblTim.setBounds(350, 11, 90, 35);
//		pMain.add(lblTim);
//
//		txtTim = new JTextField();
//		txtTim.setToolTipText("Th??ng tin t??m ki???m");
//		txtTim.setText("T??m nh??n vi??n theo m?? nh??n vi??n, t??n nh??n vi??n, ch???c v???, cccd");
//		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
//		txtTim.setForeground(Colors.LightGray);
//		txtTim.setBorder(new LineBorder(new Color(114, 23, 153), 2, true));
//		txtTim.setBounds(425, 11, 670, 33);
//		txtTim.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusGained(FocusEvent e) {
//				if (txtTim.getText().equals("T??m nh??n vi??n theo m?? nh??n vi??n, t??n nh??n vi??n, ch???c v???, cccd")) {
//					txtTim.setFont(new Font("SansSerif", Font.PLAIN, 15));
//					txtTim.setForeground(Color.BLACK);
//					txtTim.setText("");
//				}
//			}
//
//			@Override
//			public void focusLost(FocusEvent e) {
//				if (txtTim.getText().equals("")) {
//					txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
//					txtTim.setForeground(Colors.LightGray);
//					txtTim.setText("T??m nh??n vi??n theo m?? nh??n vi??n, t??n nh??n vi??n, ch???c v???, cccd");
//				}
//			}
//		});
//		pMain.add(txtTim);
//
//		btnTim = new FixButton("T??m");
//		btnTim.setToolTipText("T??m ki???m");
//		btnTim.setForeground(Color.WHITE);
//		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
//		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
//		btnTim.setBackground(new Color(114, 23, 153));
//		btnTim.setBounds(1101, 12, 159, 33);
//		Icon iconTim = IconFontSwing.buildIcon(FontAwesome.SEARCH, 20, Color.white);
//		btnTim.setIcon(iconTim);
//		pMain.add(btnTim);

		/**
		 * N??t xu???t file Excel JButton btnExcel Icon iconExcel
		 */
		btnExcels = new FixButton("Xu???t Excel");
		btnExcels.setForeground(Color.WHITE);
		btnExcels.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnExcels.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnExcels.setBackground(new Color(16, 124, 65));
		btnExcels.setBounds(10, 460, 313, 42);
		Icon iconExcel = IconFontSwing.buildIcon(FontAwesome.FILE_EXCEL_O, 20, Color.white);
		btnExcels.setIcon(iconExcel);
		pNhapThongTin.add(btnExcels);

		/**
		 * C??c buttons
		 */
		btnTinhLuong = new FixButton("T??nh L????ng");
		btnTinhLuong.setForeground(Color.WHITE);
		btnTinhLuong.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTinhLuong.setBackground(new Color(57, 210, 247));
		btnTinhLuong.setBounds(10, 400, 313, 42);
		Icon iconThemMH = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, Color.white);
		btnTinhLuong.setIcon(iconThemMH);
		pNhapThongTin.add(btnTinhLuong);

		btnReset = new FixButton("M???c ?????nh");
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnReset.setBackground(new Color(114, 43, 153));
		btnReset.setBounds(10, 520, 313, 44);
		Icon iconReset = IconFontSwing.buildIcon(FontAwesome.REFRESH, 20, Color.white);
		btnReset.setIcon(iconReset);
		btnReset.setToolTipText("L??m m???i to??n b??? ch????ng tr??nh");
		pNhapThongTin.add(btnReset);

		/**
		 * Panel s???p x???p
		 */
//		JPanel pSapXep = new JPanel();
//		pSapXep.setToolTipText("S???p x???p d??? li???u");
//		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23, 153), 1, true), "S???p x???p",
//				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
//		pSapXep.setBackground(new Color(171, 192, 238));
//		pSapXep.setBounds(350, 49, 909, 47);
//		pMain.add(pSapXep);
//		pSapXep.setLayout(null);

//		cboSapXep = new JComboBox<Object>(new Object[] { "T??ng d???n", "Gi???m d???n" });
//		cboSapXep.setBounds(25, 12, 120, 28);
//		cboSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
//		cboSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
//		cboSapXep.setBackground(Color.WHITE);
//		cboSapXep.setToolTipText("S???p x???p theo ki???u t??ng d???n/ gi???m d???n");
//		pSapXep.add(cboSapXep);
//
//		rdoTheoTenNV = new JRadioButton("Theo t??n NV");
//		rdoTheoTenNV.setBounds(200, 13, 150, 27);
//		rdoTheoTenNV.setSelected(true);
//		rdoTheoTenNV.setFont(new Font("SansSerif", Font.BOLD, 14));
//		rdoTheoTenNV.setBackground(new Color(171, 192, 238));
//		pSapXep.add(rdoTheoTenNV);
//
//		rdoTheoSoNgayCong = new JRadioButton("Theo s??? ng??y c??ng");
//		rdoTheoSoNgayCong.setBounds(350, 13, 175, 27);
//		rdoTheoSoNgayCong.setSelected(true);
//		rdoTheoSoNgayCong.setFont(new Font("SansSerif", Font.BOLD, 14));
//		rdoTheoSoNgayCong.setBackground(new Color(171, 192, 238));
//		pSapXep.add(rdoTheoSoNgayCong);
//
//		rdoTheoChucVu = new JRadioButton("Theo ch???c v???");
//		rdoTheoChucVu.setBounds(560, 13, 150, 27);
//		rdoTheoChucVu.setFont(new Font("SansSerif", Font.BOLD, 14));
//		rdoTheoChucVu.setBackground(new Color(171, 192, 238));
//		pSapXep.add(rdoTheoChucVu);
//
//		rdoTheoLuongNV = new JRadioButton("Theo l????ng ");
//		rdoTheoLuongNV.setBounds(735, 13, 135, 27);
//		rdoTheoLuongNV.setFont(new Font("SansSerif", Font.BOLD, 14));
//		rdoTheoLuongNV.setBackground(new Color(171, 192, 238));
//		pSapXep.add(rdoTheoLuongNV);
//
//		/**
//		 * Buttons Group
//		 */
//		bgRdo = new ButtonGroup();
//		bgRdo.add(rdoTheoTenNV);
//		bgRdo.add(rdoTheoChucVu);
//		bgRdo.add(rdoTheoSoNgayCong);
//		bgRdo.add(rdoTheoLuongNV);
//		bgRdo.clearSelection();

		/**
		 * B???ng ch??nh
		 */
		String cn[] = { "M?? NV", "T??n NV", "CCCD", "Ch???c V???", "SoNgayCong","Th??ng","L????ng" };
		modelLuong = new DefaultTableModel(cn, 0);

		tblLuong = new JTable(modelLuong);
		tblLuong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblLuong.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblLuong.setShowHorizontalLines(true);
		tblLuong.setShowGrid(true);
		tblLuong.setBackground(Color.WHITE);
		tblLuong.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tblLuong.setSelectionBackground(new Color(164, 44, 167, 30));
		tblLuong.setSelectionForeground(new Color(114, 23, 153));
		tblLuong.setRowHeight(30);
		tblLuong.setSelectionBackground(new Color(164, 44, 167, 30));
		tblLuong.setToolTipText("B???ng m???t h??ng");

		JTableHeader tbHeader = tblLuong.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));

		JScrollPane spMatHang = new JScrollPane(tblLuong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spMatHang.setBounds(353, 104, 906, 514);
		spMatHang.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		spMatHang.setBackground(new Color(164, 44, 167));
		spMatHang.getHorizontalScrollBar();
		pMain.add(spMatHang);

		tblLuong.getColumnModel().getColumn(0).setPreferredWidth(80);
		tblLuong.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblLuong.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblLuong.getColumnModel().getColumn(3).setPreferredWidth(80);
		tblLuong.getColumnModel().getColumn(4).setPreferredWidth(80);
		tblLuong.getColumnModel().getColumn(5).setPreferredWidth(80);
		tblLuong.getColumnModel().getColumn(6).setPreferredWidth(200);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		tblLuong.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
		tblLuong.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
		tblLuong.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		tblLuong.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tblLuong.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		tblLuong.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		tblLuong.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		spMatHang.setViewportView(tblLuong);

		/**
		 * Ph??ng n???n - background
		 */
//		JLabel lblBackGround=new JLabel("");
//		lblBackGround.setIcon(new ImageIcon("data\\img\\background.png"));
//		lblBackGround.setBounds(0, 0, 1281, 629);
//		Image imgBackGround = Toolkit.getDefaultToolkit().getImage("data\\img\\background.png");
//		Image resizeBG = imgBackGround.getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), 0);
//		lblBackGround.setIcon(new ImageIcon(resizeBG));
//		pMain.add(lblBackGround);

		/**
		 * Load lo???i m???t h??ng v??o ComboBox lo???i m???t h??ng
		 */
//		phieuLuongNV = daoPhieuLuongNV.getPhieuLuongNV(getName())
//		for(LoaiMatHang lmh : loaiMH) {
//			cboLoaiMH.addItem(lmh.getTenLoaiMatHang());
//		}
		/**
		 * S??? ki???n buttons, chu???t, key
		 */
//		btnTim.addActionListener(this);
		btnReset.addActionListener(this);
		btnExcels.addActionListener(this);
		btnTinhLuong.addActionListener(this);
		tblLuong.addMouseListener(this);

//		rdoTheoLuongNV.addActionListener(this);
//		rdoTheoChucVu.addActionListener(this);
//		rdoTheoTenNV.addActionListener(this);
//		rdoTheoSoNgayCong.addActionListener(this);
//		cboSapXep.addActionListener(this);
		cboMaNV.addItemListener(this);

//		txtTim.addKeyListener(this);
		btnReset.addKeyListener(this);
//		btnTim.addKeyListener(this);
//		txtTim.addKeyListener(this);

		/**
		 * Load d??? li???u l??n b???ng
		 */
		loadTable();
	}

	/**
	 * L???y d??? li???u t??? SQL Server n???p v??o b???ng th??ng qua v??ng l???p for, kh??ng n???p v??o
	 * b???ng v???i lo???i m???t h??ng ng???ng kinh doanh
	 */
	public void loadTableLuong() {
		String maNV = cboMaNV.getSelectedItem().toString();
//		String thang = cboThang.getSelectedItem().toString();
//		String nam = cboNam.getSelectedItem().toString();
		daoPhieuLuongNV.updateLuongNV();
		if (maNV.equals("T???t c???")) {
			loadTable();
			btnTinhLuong.setEnabled(false);
		} else {
			clearTable();
			loadTableTheoMa();}

	}
	public void loadTable() {
//		String thang = cboThang.getSelectedItem().toString();
//		String nam = cboNam.getSelectedItem().toString();
		daoPhieuLuongNV.updateLuongNV();	
			clearTable();
			ArrayList<PhieuLuongNV> lsPLNV = daoPhieuLuongNV.getAllPhieuLuongNV();
			for (PhieuLuongNV phieuLuongNV : lsPLNV) {
				modelLuong.addRow(new Object[] { phieuLuongNV.getMaNV().getMaNV(), phieuLuongNV.getMaNV().getTenNV(),
						phieuLuongNV.getMaNV().getCccd(), phieuLuongNV.getMaNV().getChucVu(),
						phieuLuongNV.getSoNgayCong(), dfThang.format(phieuLuongNV.getThang()) , dfLuong.format(Math.round(phieuLuongNV.getTienLuong())) });
			}
	}
	public void loadTableTheoMa() {
		String maNV = cboMaNV.getSelectedItem().toString();
//		String thang = cboThang.getSelectedItem().toString();
//		String nam = cboNam.getSelectedItem().toString();
		daoPhieuLuongNV.updateLuongNV();	
		ArrayList<PhieuLuongNV> lsPLNV = daoPhieuLuongNV.getAllPhieuLuongNV(maNV);
		for (PhieuLuongNV phieuLuongNV : lsPLNV) {
			modelLuong.addRow(new Object[] { phieuLuongNV.getMaNV().getMaNV(), phieuLuongNV.getMaNV().getTenNV(),
					phieuLuongNV.getMaNV().getCccd(), phieuLuongNV.getMaNV().getChucVu(),
					phieuLuongNV.getSoNgayCong(), dfThang.format(phieuLuongNV.getThang()) ,dfLuong.format(Math.round(phieuLuongNV.getTienLuong())) });
		}
	}
	

	public void detelePhieuChamCong() {
		String maNV = cboMaNV.getSelectedItem().toString();
		if (maNV.equals("T???t c???")) {
			try {
				daoCCNV.xoaPhieuChamCongNV();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			try {
				daoCCNV.xoaPhieuChamCongNV(maNV);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public boolean checkPhieuLuongNV() {
		String maNV = cboMaNV.getSelectedItem().toString();
		if (maNV.equals("T???t c???")) {
			if (daoPhieuLuongNV.getCountLuongNV(dfDate1.format(new Date())) > tblLuong.getRowCount())
				return true;
		} else {
			if (daoPhieuLuongNV.getCountLuongNV(maNV,dfDate1.format(new Date())) > 0)
				return true;
		}
		return false;

	}

	public void loadTable(ArrayList<PhieuLuongNV> lsPLNV) {
		clearTable();
		for (PhieuLuongNV phieuLuongNV : lsPLNV) {
			modelLuong.addRow(new Object[] { phieuLuongNV.getMaNV().getMaNV(), phieuLuongNV.getMaNV().getTenNV(),
					phieuLuongNV.getMaNV().getCccd(), phieuLuongNV.getMaNV().getChucVu(), phieuLuongNV.getSoNgayCong(), dfThang.format(phieuLuongNV.getThang()) ,
					dfLuong.format(Math.round(phieuLuongNV.getTienLuong())) });

		}

	}


	public void tinhLuong() {
		String maNV = cboMaNV.getSelectedItem().toString();
		if (maNV.equals("T???t c???")) {
			ArrayList<NhanVien> lstNV = daoNhanVien.getAllDanhSachNV();
			for (NhanVien nhanVien : lstNV) {
				int x = daoCCNV.getSoNgayVangNhanVien(nhanVien.getMaNV());
				int count = daoPhieuLuongNV.getCountLuongNV(nhanVien.getMaNV(), dfDate1.format(new Date()));
				if(count == 0) {
					try {
						if (x > 0) {
							daoPhieuLuongNV.themPhieuLuongNV(new PhieuLuongNV(nhanVien, new Date(), 26 - x, 0));
						} else
							daoPhieuLuongNV.themPhieuLuongNV(new PhieuLuongNV(nhanVien, new Date(), 26, 0));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} 
				
			}
		} else {
			ArrayList<NhanVien> lstNV = daoNhanVien.getTenNVTheoMa(maNV);
			for (NhanVien nhanVien : lstNV) {
				int x = daoCCNV.getSoNgayVangNhanVien(nhanVien.getMaNV());
				
				try {
					if (x > 0) {
						daoPhieuLuongNV.themPhieuLuongNV(new PhieuLuongNV(nhanVien, new Date(), 26 - x, 0));
					} else
						daoPhieuLuongNV.themPhieuLuongNV(new PhieuLuongNV(nhanVien, new Date(), 26, 0));
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		}
		
	}

	/**
	 * X??a to??n b??? b???ng
	 */
	public void clearTable() {
		while (tblLuong.getRowCount() > 0) {
			modelLuong.removeRow(0);
		}
	}

	/**
	 * L??m m???i to??n b??? ch????ng tr??nh, ?????t t???t c??? v??? gi?? tr??? m???c ?????nh
	 */
	public void LamMoi() {
		txtTim.setText("T??m m???t h??ng theo t??n m???t h??ng, lo???i m???t h??ng");
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTim.setForeground(Colors.LightGray);
		clearTable();
		cboSapXep.setSelectedIndex(0);
		bgRdo.clearSelection();
		loadTable();
	}

	/**
	 * S??? ki???n ch??nh
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	
		Object o = e.getSource();
		if (o.equals(btnReset)) {
			resetAll();
		}
		if (o.equals(btnTim)) {
			tim();
		}
		if (o.equals(btnTinhLuong)) {
			
			if (!checkPhieuLuongNV()) {
				tinhLuong();
				loadTableLuong();
				detelePhieuChamCong();

			} else {
				JOptionPane.showMessageDialog(null, "???? t??nh l????ng");
			}

		}
//		if(rdoTheoTenNV.isSelected()) {
//			SapXepModelTableTheoTenNV();
//		}
//		if (cboSapXep.getSelectedItem() == "T??ng d???n") {
//			if(o.equals(rdoTheoSoNgayCong)) {
//				sortGiaTangDan(nv);
//			else if(o.equals(rdoTheoSoNgayCong)) {
//				sortGiaTangDan(nv);
//			}else if (o.equals(rdoTheoTo)) {
//				sortLMHTangDan(nv);
//			}else if (o.equals(rdoTheoTenCN)) {
//				sortTenMHTangDan(nv); 
//			} 
//		}
//		if (cboSapXep.getSelectedItem() == "Gi???m d???n") {
//			if(o.equals(rdoTheoLuongCN)) {
//				sortGiaGiamDan(mh);
//			}else if (o.equals(rdoTheoTo) ) {
//				sortLMHGiamDan(mh);
//			}else if (o.equals(rdoTheoTenCN)) {
//				sortTenMHGiamDan(mh);
//			} 
//		}
//		if(o.equals(cboSapXep)) {
//			bgRdo.clearSelection();
//		}
		if (o.equals(btnExcels)) {
			try {
				xuatExcel();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * Th??m m???t h??ng v??o table v?? SQL Server
	 */
//	@SuppressWarnings("unlikely-arg-type")
//	public void ThemMH() {
//		String maMH = daoPhatSinhMa.getMaMH();
//		String loaiMH = cboLoaiMH.getSelectedItem().toString();
//		String maLMH = daoLMH.getMaLoaiMHTheoTen(loaiMH);
//		if(tenMH.equals("") || txtSoLuong.equals("") || txtDonGia.equals("") ) {
//			JOptionPane.showMessageDialog(this, "Vui l??ng nh???p th??ng tin ?????y ?????!", "Th??ng b??o", JOptionPane.WARNING_MESSAGE);
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
//						JOptionPane.showMessageDialog(this, "Th??m m???t h??ng th???t b???i!");
//					}
//					clearTable();
//					JOptionPane.showMessageDialog(this, "Th??m m???t h??ng th??nh c??ng!");
//					LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
//					modelLuong.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(), dfVND.format(Math.round(mh.getGiaMatHang())) } );
//					}
//				}
//			} 
//	}
	/**
	 * X??a m???t h??ng kh???i table, update m???t h??ng th??nh ng???ng kinh doanh tr??n SQL
	 * Server
	 */
//	public void updateMHNgungKD() {
//		if (tblLuong.getSelectedRow() == -1) {
//			JOptionPane.showMessageDialog(this, "Vui l??ng ch???n m???t h??ng c???n x??a");
//		}else {
//			int r = tblLuong.getSelectedRow();
//			if(r>0) {
//				int del = JOptionPane.showConfirmDialog(null, "B???n ch???c ch???n mu???n x??a? ", "Th??ng b??o", JOptionPane.YES_NO_OPTION);
//				if(del == JOptionPane.YES_OPTION) {
//					String maMH1 =  tblLuong.getValueAt(r, 0).toString();
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
	 * S???a th??ng tin m???t h??ng
	 */
//	public void SuaMH() {
//		int row = tblLuong.getSelectedRow();
//		if(row >=0) {
//			int update = JOptionPane.showConfirmDialog(this, "B???n mu???n s???a th??ng tin m???t h??ng  n??y kh??ng?", "Th??ng b??o",
//					JOptionPane.YES_NO_OPTION);
//			if(update == JOptionPane.YES_OPTION) {
//				if(regex.regexTenMH(txtTenMH) && regex.regexSoLuong(txtSoLuong) && regex.regexGiaMH(txtDonGia)) {
//					String maMH = (String) tblLuong.getValueAt(row, 0);
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
//						modelLuong.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(), dfVND.format(Math.round(mh.getGiaMatHang())) } );
//						JOptionPane.showMessageDialog(this, "C???p  nh???t m???t h??ng th??nh c??ng! ");
//					} catch (Exception e) {
//						e.printStackTrace();
//						JOptionPane.showMessageDialog(this, "M?? m???t h??ng kh??ng t???n t???i.");
//					}	
//
//				}
//			}
//		}else {
//			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n th??ng tin m???t h??ng c???n s???a!", "Th??ng b??o",
//					JOptionPane.WARNING_MESSAGE);
//		}
//	}
	/**
	 * S???p x???p t??n m???t h??ng t??ng d???n
	 * 
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
//			modelLuong.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
//			}
//		}
//	}
	/**
	 * S???p x???p t??n m???t h??ng gi???m d???n
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
//			modelLuong.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
//			}
//		}
//	}
	/**
	 * S???p x???p lo???i m???t h??ng gi???m d???n
	 */
//	public void sortLMHGiamDan(MatHang mh){
//		clearTable();
//		ArrayList<MatHang> lstMH = daoMH.sortLMH("DESC");
//		for(MatHang infoMH : lstMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
//			String check = lMH.getMaLoaiMatHang();
//			if(!"LMH004".equals(check) ) {
//				modelLuong.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
//			}
//		}
//	}
	/**
	 * S???p x???p lo???i m???t h??ng t??ng d???n
	 */
//	public void sortLMHTangDan(MatHang mh){
//		clearTable();
//		ArrayList<MatHang> lstMH = daoMH.sortLMH("ASC");
//		for(MatHang infoMH : lstMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
//			String check = lMH.getMaLoaiMatHang();
//			if(!"LMH004".equals(check) ) {
//			modelLuong.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
//			}
//		}
//	}
	/**
	 * S???p x???p h??? s??? l????ng nh??n vi??n t??ng d???n
	 */
//	public void sortSoNgayCongTangDan(NhanVien nv){
//		clearTable();
//		ArrayList<NhanVien> lstMH = daoMH.sortGia("ASC");
//		for(MatHang infoMH : lstMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
//			String check = lMH.getMaLoaiMatHang();
//			if(!"LMH004".equals(check) ) {
//			modelLuong.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
//			}
//		}
//	}
	/**
	 * S???p x???p gi?? m???t h??ng gi???m d???n
	 */

//	public void sortGiaGiamDan(MatHang mh){
//		clearTable();
//		ArrayList<MatHang> lstMH = daoMH.sortGia("DESC");
//		for(MatHang infoMH : lstMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
//			String check = lMH.getMaLoaiMatHang();
//			if(!"LMH004".equals(check) ) {
//				modelLuong.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
//			}
//		}
//	}
	/**
	 * T??m m???t h??ng theo t??n m???t h??ng, lo???i m???t h??ng
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void tim() {
		ArrayList<PhieuLuongNV> lstLuongNV = null;
		String input = txtTim.getText().trim();
		String regexMaNV = "^(NV[0-9]{3})$";
		if (!txtTim.equals("")) {
			if (input.matches(regexMaNV)) {
				lstLuongNV = daoPhieuLuongNV.getPhieuLuongNVTheoMa(input);
				loadPhieuLuongTheoMa(lstLuongNV);
			}

			else if (regex.regexTen(txtTim)) {
				lstLuongNV = daoPhieuLuongNV.getPhieuLuongNVTheoTen(input);
				loadPhieuLuongTheoTen(lstLuongNV);
			} else if (regex.regexCCCD(txtTim)) {
				lstLuongNV = daoPhieuLuongNV.getPhieuLuongNVTheoCCCD(input);
				loadPhieuLuongTheoCCCD(lstLuongNV);
			} else if (lstLuongNV.size() == 0)
				JOptionPane.showMessageDialog(null, "Th??ng tin t??m ki???m kh??ng h???p l???!\n", "Th??ng b??o",
						JOptionPane.ERROR_MESSAGE);
		} else {
			clearTable();
			JOptionPane.showMessageDialog(this, "Vui l??ng nh???p th??ng tin t??m ki???m!", "Th??ng b??o",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void loadPhieuLuongTheoTen(ArrayList<PhieuLuongNV> pl1) {

		loadTable(pl1);
	}

	public void loadPhieuLuongTheoMa(ArrayList<PhieuLuongNV> pl1) {
		loadTable(pl1);
	}

	public void loadPhieuLuongTheoCCCD(ArrayList<PhieuLuongNV> pl1) {
		loadTable(pl1);
	}
	/**
	 * N???p d??? li???u t??n m???t h??ng v??o b???ng
	 */
//	public void loadTenMH(ArrayList<MatHang> mh1) {
//		clearTable();
//		ArrayList<MatHang> lsMH = daoMH.getTenMH(txtTim.getText());
//		for(MatHang mh : lsMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
//			modelLuong.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(), dfVND.format(Math.round(mh.getGiaMatHang())) } );
//		}
//	}
	/**
	 * N???p d??? li???u lo???i m???t h??ng v??o b???ng
	 */

//	public void loadLoaiMH(ArrayList<MatHang> mh1) {
//		clearTable();
//		String maLoai = daoLMH.getMaLoaiMHTheoTen(txtTim.getText());
//		ArrayList<MatHang> lsMH = daoMH.getLMH(maLoai);
//		for(MatHang mh : lsMH) {
//			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
//			modelLuong.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(), dfVND.format(Math.round(mh.getGiaMatHang())) } );
//		}
//	}
	/**
	 * Xu???t Excels
	 */
	private void xuatExcel() throws IOException {
		XuatExcels xuat = new XuatExcels();
		FileDialog fileDialog = new FileDialog(this, "Xu???t th??ng tin l????ng nh??n vi??n ra Excels", FileDialog.SAVE);
		fileDialog.setFile("Danh s??ch l????ng nh??n vi??n");
		fileDialog.setVisible(true);
		String name = fileDialog.getFile();
		String fileName = fileDialog.getDirectory() + name;

		if (name == null)
			return;

		if (!fileName.endsWith(".xlsx") || !fileName.endsWith(".xls"))
			fileName += ".xlsx";

		xuat.xuatTable(tblLuong, "DANH S??CH TH??NG L????NG NH??N VI??N", fileName);
	}
	//Lay du lieu tu bang vao arraylist
			private ArrayList<PhieuLuongNV> getDataTuBang(){
				ArrayList<PhieuLuongNV> lstNV = new ArrayList<PhieuLuongNV>();
				int sodong = modelLuong.getRowCount();
				if(sodong == 0)
					return lstNV;
				NhanVien nv;
				String chucvu, manv, hoten, cccd;
				int soNgayCong;
				Date thang;
				float luong;
				for (int i = 0; i < sodong; i++) {
					manv = (String) tblLuong.getValueAt(i, 0);
					hoten = (String) tblLuong.getValueAt(i, 1);
					chucvu = (String) tblLuong.getValueAt(i, 3);
					soNgayCong = (int) tblLuong.getValueAt(i, 4);
					thang =  (Date) tblLuong.getValueAt(i, 5);
					luong = (float) tblLuong.getValueAt(i, 6);
					cccd = (String) tblLuong.getValueAt(i, 2);
					
					nv = new NhanVien(manv, hoten, chucvu, null, null, null, cccd, null);
					PhieuLuongNV plnv = new PhieuLuongNV(nv, thang, soNgayCong, luong);
					lstNV.add(plnv);
				}
				return lstNV;
			}
			//Sap xep theo ten
			@SuppressWarnings("unused")
			private void SapXepModelTableTheoTenNV(){
				ArrayList<PhieuLuongNV> lstNV = getDataTuBang();
				if(cboSapXep.getSelectedItem().toString().equals("T??ng d???n")) {
					Collections.sort(lstNV, new Comparator<PhieuLuongNV>() {
					@Override
						public int compare(PhieuLuongNV o1, PhieuLuongNV o2) {
							// TODO Auto-generated method stub
							return o1.getMaNV().getTenNV().compareTo(o2.getMaNV().getTenNV());
						}
					});
				}
				if(cboSapXep.getSelectedItem().toString().equals("Gi???m d???n")) {
					Collections.sort(lstNV, new Comparator<PhieuLuongNV>() {
						@Override
						public int compare(PhieuLuongNV o1, PhieuLuongNV o2) {
							// TODO Auto-generated method stub
							return o2.getMaNV().getTenNV().compareTo(o1.getMaNV().getTenNV());
						}
					});
				}
//				removeDanhSachLuong(modelLuong);
//				for(PhieuLuongNV plNV : lstNV) {
//					modelLuong.addRow(new Object[] {
//							NV.getMaNV(), NV.getTenNV(), NV.getChucVu(), NV.getNgaySinh(), NV.getGioiTinh(), NV.getDiaChi(), NV.getCccd(), NV.getSdt()
//					});
//				}
				loadTable(lstNV);
			}
			private void removeDanhSachLuong(DefaultTableModel defaultTableModel) {
				while(tblLuong.getRowCount() > 0){
					modelLuong.removeRow(0);
				}
			}

	/**
	 * ResetAll gi??p l??m m???i l???i form l????ng nh??n vi??n
	 */
	public void resetAll() {

		cboMaNV.setSelectedIndex(0);
		cboTenNV.setSelectedIndex(0);

		txtLuong.setText("");

	}

	/**
	 * S??? ki???n click chu???t
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblLuong)) {
			int row = tblLuong.getSelectedRow();
			cboMaNV.setSelectedItem(modelLuong.getValueAt(row, 0).toString());
			cboTenNV.setSelectedItem(modelLuong.getValueAt(row, 1).toString());
			txtThang.setText(modelLuong.getValueAt(row, 5).toString());
//			cboNam.setSelectedItem(modelLuong.getValueAt(row, 6).toString());
			txtLuong.setText(modelLuong.getValueAt(row, 6).toString());
		}
	}

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

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getItem();
		if (o == cboMaNV.getSelectedItem()) {
			/*
			 * N???u cbb nh??n vi??n thay ?????i, th?? cbb t??n nh??n vi??n s??? ???????c hi???n th??? danh s??ch
			 * l??n
			 */
			String maNV = (String) cboMaNV.getSelectedItem();
			ArrayList<NhanVien> lsNV = daoNhanVien.getTenNVTheoMa(maNV);
			cboTenNV.removeAllItems();
			if (cboMaNV.getSelectedIndex() == 0) {
				cboTenNV.addItem("T???t c???");
				loadTable();
			}
			else {
			for (NhanVien nv : lsNV) {
				cboTenNV.addItem(nv.getTenNV());
				clearTable();
				loadTableTheoMa();
		}}
	}
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