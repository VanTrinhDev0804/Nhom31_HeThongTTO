package app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.ProcessHandle.Info;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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
import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAOCT_CD_SX_SP;
import dao.DAOCongDoan;
import dao.DAOCongNhan;
import dao.DAOPhatSinhMa;
import dao.DAOPhieuChamCong;
//import dao.DAOKhachHang;
//import dao.DAOLoaiKH;
//import dao.DAOLoaiPhong;
//import dao.DAONhanVien;
//import dao.DAOPhatSinhMa;
import dao.DAOSanPham;
import dao.DAOTaiKhoan;
import dao.DAOToSanXuat;
import dao.Regex;
import entity.CT_CD_SX_SP;
import entity.ChamCongCN;
import entity.CongDoan;
import entity.CongNhan;
import entity.SanPham;
import entity.ToSanXuat;
//import entity.KhachHang;
//import entity.LoaiKH;
//import entity.LoaiPhong;
//import entity.NhanVien;
//import entity.Phong;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class FrmQLCongDoan extends JPanel implements ActionListener, FocusListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Frame FrmQLCongDoan = null;
	@SuppressWarnings("unused")
	private String sHeaderMaNV, sHeaderTenNV;
	private Panel pMain;
	private Date dNgayHienTai;
	private JLabel lblTim, lblTenKH, lblLoaiKH, lblNgayDen, lblSDT, lblGioDen, lblTinhTrangCongDoan, lblDiaChi, lblChonSanPham, lblBackGround;
	private JButton btnTim, btnThemCD, btnSuaCD, btnLamMoiCD;
	private ButtonGroup bg;
	private java.util.Date timeNow1, timeNow2;
	private long nowHours, nowMinutes;
	@SuppressWarnings("unused")
	private Date dNow;
	private LocalDate now;
	private int ngay, thang, nam;
	private JDateChooser chooserNgayDen;

	private DAOSanPham daoSanPham;
	private DAOCT_CD_SX_SP daoCT;
	private DAOCongDoan daoCongDoan;
	private DAOPhatSinhMa daoPhatSinhMa;
	private Regex regex;

	private CongDoan cd;
	private JPanel pNhapThongTin;
	private JLabel lblNhapThongTin;
	private JTextField txtTenCD;
	private JTextField txtTenThanhPham;
	private JTextField txtGiaSX_CD;
	private JComboBox<Object> cboTrangThaiCongDoan;
	private JTextField txtTim;
	private JTable tblSanPham;
	private DefaultTableModel modelSanPham;
	private JComboBox<Object> cboSapXep;
	private JRadioButton rdoTheoMaSanPham;
	private JRadioButton rdoTheoMaCongDoan;
	private DefaultTableModel modelCongDoan;
	private JTable tblCongDoan;
	private JButton btnExcels;
	private DecimalFormat dfGiaSX;
	private JTextField txtSoLuongTP;
	private DefaultComboBoxModel ModelCbbTo;

	private DAOToSanXuat daoToSanXuat;
	private JComboBox<Object> cboToSX;
	private DAOCongNhan daoCongNhan;
	private DAOPhieuChamCong daoPhieuChamCong;
	
	
	/**
	 * @return pMain
	 */
	public Panel getFrmQLCongDoan() {
		return this.pMain;
	}
	
	/**
	 * Kế thừa tên và mã NV, ngày hiện tại của FrmQuanLy
	 * @param sHeaderTenNV
	 * @param sHeaderMaNV
	 * @param dNgayHienTai
	 */
	@SuppressWarnings("deprecation")
	public FrmQLCongDoan(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {

		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;

		/**
		 * Kết nối database
		 */
		try {
			ConnectDB.getinstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		/**
		 * Khai báo các DAO
		 */
		daoSanPham=new DAOSanPham();
		daoCongDoan=new DAOCongDoan();
		daoCT = new DAOCT_CD_SX_SP();
		daoPhatSinhMa = new DAOPhatSinhMa();
		daoToSanXuat = new DAOToSanXuat();
		daoCongNhan = new DAOCongNhan();
		daoPhieuChamCong = new DAOPhieuChamCong();
		regex = new Regex();

		/**
		 * Khai báo các entity
		 */
		SanPham sp = new SanPham();
		cd = new CongDoan();

		/**
		 * Frame Cong Doan
		 */
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1273, 629);
		add(pMain);
		pMain.setLayout(null);

		dfGiaSX=new DecimalFormat("######");
		/**
		 * Nhập thông tin công đoạn mới
		 * Panel pNhapThongTin
		 */
		pNhapThongTin = new JPanel();
		pNhapThongTin.setToolTipText("Các Thông Tin Công Đoạn Cần Nhập");
		pNhapThongTin.setBorder(new LineBorder(new Color(114, 23, 153)));
		pNhapThongTin.setBackground(Color.WHITE);
		pNhapThongTin.setBounds(265, 50, 277, 567);

		pMain.add(pNhapThongTin);
		pNhapThongTin.setLayout(null);

		lblNhapThongTin = new JLabel("Thông Tin Công Đoạn");
		lblNhapThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhapThongTin.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNhapThongTin.setBounds(21, 10, 200, 29);
		pNhapThongTin.add(lblNhapThongTin);
		
		//Ten cong doan
		JLabel lblTenCD = new JLabel("Tên Công Đoạn:");
		lblTenCD.setBounds(10, 40, 125, 26);
		pNhapThongTin.add(lblTenCD);
		lblTenCD.setFont(new Font("SansSerif", Font.PLAIN, 15));

		txtTenCD = new JTextField();
		txtTenCD.setBounds(10, 65, 250, 35);
		pNhapThongTin.add(txtTenCD);
		txtTenCD.setBackground(new Color(255, 255, 255));
		txtTenCD.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenCD.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtTenCD.setColumns(20);
		
		//ten thanh pham
		JLabel lblTenThanhPham = new JLabel("Tên Thành Phẩm:");
		lblTenThanhPham.setBounds(10, 99, 250, 26);
		pNhapThongTin.add(lblTenThanhPham);
		lblTenThanhPham.setFont(new Font("SansSerif", Font.PLAIN, 15));

		txtTenThanhPham = new JTextField();
		txtTenThanhPham.setBounds(10, 122, 250, 35);
		pNhapThongTin.add(txtTenThanhPham);
		txtTenThanhPham.setBackground(new Color(255, 255, 255));
		txtTenThanhPham.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenThanhPham.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtTenThanhPham.setColumns(20);
		
		//gia san xuat 
		JLabel lblGiaSX_CD = new JLabel("Giá Sản Xuất Công Đoạn:");
		lblGiaSX_CD.setBounds(10, 152, 250, 26);
		pNhapThongTin.add(lblGiaSX_CD);
		lblGiaSX_CD.setFont(new Font("SansSerif", Font.PLAIN, 15));

		txtGiaSX_CD = new JTextField();
		txtGiaSX_CD.setBounds(10, 178, 250, 35);
		pNhapThongTin.add(txtGiaSX_CD);
		txtGiaSX_CD.setBackground(new Color(255, 255, 255));
		txtGiaSX_CD.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtGiaSX_CD.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtGiaSX_CD.setColumns(20);
		
		//tinh trang cong doan
		lblTinhTrangCongDoan = new JLabel("Trạng thái Công Đoạn:");
		lblTinhTrangCongDoan.setBounds(10, 339, 250, 19);
		lblTinhTrangCongDoan.setFont(new Font("SansSerif", Font.PLAIN, 15));
		pNhapThongTin.add(lblTinhTrangCongDoan);

		cboTrangThaiCongDoan = new JComboBox<Object>(new Object[] {"Đang Sản Xuất", "Ngừng Sản Xuất"});
		cboTrangThaiCongDoan.setToolTipText("Chọn trạng thái CĐ");
		cboTrangThaiCongDoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboTrangThaiCongDoan.setBounds(10, 368, 250, 35);
		cboTrangThaiCongDoan.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboTrangThaiCongDoan.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cboTrangThaiCongDoan.setBackground(Color.white);
		pNhapThongTin.add(cboTrangThaiCongDoan);

		lblTim = new JLabel("Tìm kiếm:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(550, 12, 80, 35);
		pMain.add(lblTim);
		//
		txtTim = new JTextField();
		txtTim.setToolTipText("Tìm công đoạn theo mã công đoạn.");
		txtTim.setText("Tìm công đoạn theo mã công đoạn.");
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTim.setForeground(Colors.LightGray);
		txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		txtTim.setBounds(632, 12, 300, 33);
		pMain.add(txtTim);
		//
		btnTim = new FixButton("Tìm");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTim.setBackground(new Color(114, 23, 153));
		btnTim.setBounds(940, 12, 150, 33);
		Icon iconTim = IconFontSwing.buildIcon(FontAwesome.SEARCH, 20, Color.white);
		btnTim.setIcon(iconTim);
		pMain.add(btnTim);
		
		btnExcels = new FixButton("Xuất Excels");
		btnExcels.setForeground(Color.WHITE);
		btnExcels.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnExcels.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnExcels.setBackground(new Color(16, 124, 65));
		btnExcels.setBounds(1101, 12, 159, 33);
		Icon iconExcel = IconFontSwing.buildIcon(FontAwesome.FILE_EXCEL_O, 20, Color.white);
		btnExcels.setIcon(iconExcel);
		pMain.add(btnExcels);

		lblChonSanPham = new JLabel("Chọn sản phẩm");
		lblChonSanPham.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblChonSanPham.setBounds(70, 11, 292, 29);
		pMain.add(lblChonSanPham);

		JScrollPane scrollPaneChonSanPham = new JScrollPane(tblSanPham, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneChonSanPham.setToolTipText("Danh sách thông tin sản phẩm:");
		scrollPaneChonSanPham.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneChonSanPham.setBackground(new Color(164, 44, 167));
		scrollPaneChonSanPham.setBounds(10, 50, 250, 567);
		scrollPaneChonSanPham.getHorizontalScrollBar();
		pMain.add(scrollPaneChonSanPham);

		String colSanPham[] = {"Mã sản phẩm", "Tên sản phẩm", "Giá sản xuất", "Số lượng","Trạng Thái SP"};
		modelSanPham=new DefaultTableModel(colSanPham, 0);

		tblSanPham = new JTable(modelSanPham);
		tblSanPham.setToolTipText("Chọn thông tin sản phẩm muốn sản xuất");
		tblSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblSanPham.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblSanPham.setShowHorizontalLines(true);
		tblSanPham.setShowGrid(true);
		tblSanPham.setBackground(Color.white);
		tblSanPham.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tblSanPham.setSelectionBackground(new Color(164, 44, 167, 30));
		tblSanPham.setSelectionForeground(new Color(114, 23, 153));
		tblSanPham.setRowHeight(30);
		tblSanPham.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeDanhSachCD(modelCongDoan);
				choose1SP();
			}
		});

		JTableHeader tbHeaderSanPham = tblSanPham.getTableHeader();
		tbHeaderSanPham.setToolTipText("Chọn thông tin sản phẩm");
		tbHeaderSanPham.setBackground(new Color(164, 44, 167));
		tbHeaderSanPham.setForeground(Color.white);
		tbHeaderSanPham.setFont(new Font("SansSerif", Font.BOLD, 14));

		tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(100);//masanpham
		tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(120);//tensanpham
		tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(100);//giasanxuat
		tblSanPham.getColumnModel().getColumn(3).setPreferredWidth(150);//soluong
		tblSanPham.getColumnModel().getColumn(4).setPreferredWidth(150);//trangthai
		
		DefaultTableCellRenderer rightRenderer=new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		tblSanPham.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		tblSanPham.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

		scrollPaneChonSanPham.setViewportView(tblSanPham);

		/**
		 * Thêm 1 CĐ vào danh sách bảng CĐ
		 * Nút thêm CĐ
		 * JButton btnThemCD
		 * Icon iconThemCD
		 */
		btnThemCD = new FixButton("Thêm");
		btnThemCD.setForeground(Color.black);
		btnThemCD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThemCD.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThemCD.setBackground(new Color(57, 210, 247));
		btnThemCD.setBounds(10, 420, 263, 35);
		Icon iconThemCD = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, Color.white);
		btnThemCD.setIcon(iconThemCD);
		pNhapThongTin.add(btnThemCD);

		/**
		 * Sửa thông tin CĐ
		 * Nút sửa CĐ
		 * JButton btnSuaCĐ
		 * Icon iconSuaCĐ
		 */
		btnSuaCD = new FixButton("Sửa");
		btnSuaCD.setForeground(Color.black);
		btnSuaCD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSuaCD.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSuaCD.setBackground(new Color(133, 217, 191));
		btnSuaCD.setBounds(10, 470, 263, 35);
		Icon iconSuaCD = IconFontSwing.buildIcon(FontAwesome.WRENCH, 20, Color.white);
		btnSuaCD.setIcon(iconSuaCD);
		pNhapThongTin.add(btnSuaCD);

		btnLamMoiCD = new FixButton("Làm mới");
		btnLamMoiCD.setForeground(Color.white);
		btnLamMoiCD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoiCD.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLamMoiCD.setBackground(new Color(114, 23, 153));
		btnLamMoiCD.setBounds(10, 520, 263, 35);
		Icon iconLamMoiCD = IconFontSwing.buildIcon(FontAwesome.REFRESH, 20, Color.white);
		btnLamMoiCD.setIcon(iconLamMoiCD);
		pNhapThongTin.add(btnLamMoiCD);
		
		JLabel lblChnTSn = new JLabel("Chọn tổ sản xuất");
		lblChnTSn.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblChnTSn.setBounds(10, 274, 250, 19);
		pNhapThongTin.add(lblChnTSn);
		
		
		
		
		
		
		ModelCbbTo = new DefaultComboBoxModel<>();
		ModelCbbTo.setSelectedItem("");
		ArrayList<ToSanXuat> lstTSX = daoToSanXuat.getDSToSanXuat();
		loadItemsComboMaTo(lstTSX);
	

		cboToSX = new JComboBox<Object>(ModelCbbTo);
		cboToSX.setToolTipText("Chọn tổ sản xuất");
		cboToSX.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboToSX.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cboToSX.setBackground(Color.WHITE);
		cboToSX.setBounds(10, 294, 250, 35);
//		cboToSX.setSelectedIndex(0);
		pNhapThongTin.add(cboToSX);
		
		txtSoLuongTP = new JTextField();
		txtSoLuongTP.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtSoLuongTP.setColumns(20);
		txtSoLuongTP.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtSoLuongTP.setBackground(Color.WHITE);
		txtSoLuongTP.setBounds(10, 237, 250, 35);
		pNhapThongTin.add(txtSoLuongTP);
		
		JLabel lblGiaSX_CD_1 = new JLabel("Số lượng thành phẩm: ");
		lblGiaSX_CD_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblGiaSX_CD_1.setBounds(10, 212, 250, 26);
		pNhapThongTin.add(lblGiaSX_CD_1);

		JPanel pSapXep = new JPanel();
		pSapXep.setToolTipText("Sắp Xếp Thông Tin Công Đoạn");
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Sắp Xếp Công Đoạn", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBackground(new Color(178, 192, 237));
		pSapXep.setBounds(552, 50, 705, 50);
		pMain.add(pSapXep);
		pSapXep.setLayout(null);
		cboSapXep = new JComboBox<Object>(new Object[]{"Tăng dần", "Giảm dần"});
		cboSapXep.setToolTipText("Sắp xếp thông tin công đoạn tăng dần hoặc giảm dần theo các tiêu chí");
		cboSapXep.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboSapXep.setBounds(34, 14, 123, 28);
		cboSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboSapXep.setBackground(Color.WHITE);
		pSapXep.add(cboSapXep);

		rdoTheoMaSanPham = new JRadioButton("Theo Mã Sản Phẩm");
		rdoTheoMaSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdoTheoMaSanPham.setBounds(250, 16, 180, 27);
		rdoTheoMaSanPham.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoMaSanPham.setBackground(new Color(178, 192, 237));
		pSapXep.add(rdoTheoMaSanPham);

		rdoTheoMaCongDoan = new JRadioButton("Theo Mã Công Đoạn");
		rdoTheoMaCongDoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdoTheoMaCongDoan.setBounds(450, 16, 180, 27);
		rdoTheoMaCongDoan.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoMaCongDoan.setBackground(new Color(178, 192, 237));
		pSapXep.add(rdoTheoMaCongDoan);

		bg=new ButtonGroup();
		bg.add(rdoTheoMaSanPham); bg.add(rdoTheoMaCongDoan);

		JScrollPane scrollPaneCD = new JScrollPane();
		scrollPaneCD.setToolTipText("Danh sách thông tin CĐ");
		scrollPaneCD.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneCD.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneCD.setBackground(new Color(164, 44, 167));
		scrollPaneCD.setBounds(552, 105, 705, 512);
		scrollPaneCD.getHorizontalScrollBar();
		pMain.add(scrollPaneCD);

		String colCD[] = {"Mã Công Đoạn", "Mã Sản Phẩm","Tổ Sản Xuất", "Tên Công Đoạn", "Tên Thành Phẩm","Số lượng yêu cầu ","Giá Sản Xuất","Đã Sản Xuất", "Trạng Thái CD"};
		modelCongDoan=new DefaultTableModel(colCD, 0);

		tblCongDoan=new JTable(modelCongDoan);
		tblCongDoan.setToolTipText("Chọn thông tin CĐ để thực hiện các chức năng");
		tblCongDoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblCongDoan.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblCongDoan.setShowHorizontalLines(true);
		tblCongDoan.setShowGrid(true);
		tblCongDoan.setBackground(Color.white);
		tblCongDoan.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tblCongDoan.setSelectionBackground(new Color(164, 44, 167, 30));
		tblCongDoan.setSelectionForeground(new Color(114, 23, 153));
		tblCongDoan.setRowHeight(30);
		tblCongDoan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblCongDoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				choose1CD();
			}
		});


		JTableHeader tbHeaderDDP = tblCongDoan.getTableHeader();
		tbHeaderDDP.setToolTipText("Danh sách thông tin CĐ");
		tbHeaderDDP.setBackground(new Color(164, 44, 167));
		tbHeaderDDP.setForeground(Color.white);
		tbHeaderDDP.setFont(new Font("SansSerif", Font.BOLD, 14));

		tblCongDoan.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblCongDoan.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblCongDoan.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblCongDoan.getColumnModel().getColumn(3).setPreferredWidth(148);
		tblCongDoan.getColumnModel().getColumn(4).setPreferredWidth(148);
		tblCongDoan.getColumnModel().getColumn(5).setPreferredWidth(148);
		tblCongDoan.getColumnModel().getColumn(6).setPreferredWidth(148);
		tblCongDoan.getColumnModel().getColumn(7).setPreferredWidth(148);
		tblCongDoan.getColumnModel().getColumn(8).setPreferredWidth(148);
		DefaultTableCellRenderer rightRenderer2=new DefaultTableCellRenderer();
		rightRenderer2.setHorizontalAlignment(JLabel.RIGHT);
		tblCongDoan.getColumnModel().getColumn(0).setCellRenderer(rightRenderer2);
		tblCongDoan.getColumnModel().getColumn(1).setCellRenderer(rightRenderer2);
		tblCongDoan.getColumnModel().getColumn(2).setCellRenderer(rightRenderer2);
		tblCongDoan.getColumnModel().getColumn(3).setCellRenderer(rightRenderer2);
		tblCongDoan.getColumnModel().getColumn(4).setCellRenderer(rightRenderer2);
		tblCongDoan.getColumnModel().getColumn(5).setCellRenderer(rightRenderer2);
		tblCongDoan.getColumnModel().getColumn(6).setCellRenderer(rightRenderer2);
		tblCongDoan.getColumnModel().getColumn(7).setCellRenderer(rightRenderer2);
		tblCongDoan.getColumnModel().getColumn(8).setCellRenderer(rightRenderer2);
		scrollPaneCD.setViewportView(tblCongDoan);

		/**
		 * Hình nền của giao diện
		 */
		lblBackGround=new JLabel("");
		lblBackGround.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackGround.setBounds(0, 0, 1281, 629);
		Image imgBackGround = Toolkit.getDefaultToolkit().getImage("");
		Image resizeBG = imgBackGround.getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), 0);
		lblBackGround.setIcon(new ImageIcon(resizeBG));
		pMain.add(lblBackGround);

		//loadDanhSachCD(cd);

		


		loadDSSanPhamĐangSanXuat(sp);

		btnTim.addActionListener(this);
		btnThemCD.addActionListener(this);
		btnSuaCD.addActionListener(this);
		btnLamMoiCD.addActionListener(this);

		cboSapXep.addActionListener(this);
		rdoTheoMaSanPham.addActionListener(this);
		rdoTheoMaCongDoan.addActionListener(this);
		txtTim.addFocusListener(this);
		txtTim.addActionListener(this);
		btnTim.addKeyListener(this);
		btnExcels.addActionListener(this);
	}
	
	private void loadItemsComboMaTo(ArrayList<ToSanXuat> lstTSX) {
		ModelCbbTo.removeAllElements();
		for(ToSanXuat TSX : lstTSX) {
			if(TSX.getMaCD()== null) {
				ModelCbbTo.addElement(TSX.getMaTo());
			}
			
		}
		
	
		
	}

	private void removeDanhSachSP(DefaultTableModel defaultTableModel) {
		while(tblSanPham.getRowCount() > 0)
			modelSanPham.removeRow(0);
	}

	/**
	 * @param defaultTableModel trả về modelCD
	 */
	private void removeDanhSachCD(DefaultTableModel defaultTableModel) {
		while(tblCongDoan.getRowCount() > 0)
			modelCongDoan.removeRow(0);
	}

	/**
	 * Xóa trắng textfield và textarea, đặt lại mặc định các combobox và các button
	 */
	@SuppressWarnings("deprecation")
	private void resetAll() {
		txtTim.setText("Tìm công đoạn theo mã công đoạn.");
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTim.setForeground(Colors.LightGray);
		
		
		txtTenCD.setText("");
		txtTenThanhPham.setText("");
		txtGiaSX_CD.setText("");
		txtSoLuongTP.setText("");
		cboTrangThaiCongDoan.setSelectedItem("Đang Sản Xuất");


		removeDanhSachSP(modelCongDoan);
		loadDSSanPhamĐangSanXuat(new SanPham());;

		removeDanhSachCD(modelCongDoan);
		//loadDanhSachCD(cd);
		
	}

	private void loadDSSanPhamĐangSanXuat(SanPham sp) {
		removeDanhSachSP(modelSanPham);
		ArrayList<SanPham> lstSP = daoSanPham.getDanhSachCDDangSanXuat();
		for(SanPham infoSP : lstSP) {
			modelSanPham.addRow(new Object[] {
					infoSP.getMaSP(), infoSP.getTenSP(), dfGiaSX.format(infoSP.getGiaSX()), dfGiaSX.format(infoSP.getSoLuong()), infoSP.getTrangThaiSP()
			});
		}
	}

	private void loadDanhSachCD(CongDoan cd) {
		removeDanhSachCD(modelCongDoan);
		ArrayList<CongDoan> lstCD = daoCongDoan.getAllDanhSachCD();
		for(CongDoan infoCD : lstCD) {
			if(infoCD.getTrangThaiCD().equalsIgnoreCase("Ngừng Sản Xuất")) {
				modelCongDoan.addRow(new Object[] {
						infoCD.getMaCD(), infoCD.getSanPham().getMaSP(),"",infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getSoLuongTP() ,dfGiaSX.format(infoCD.getGiaSX()), infoCD.getSoLuongDSX(),infoCD.getTrangThaiCD()});
			}else
				{
				modelCongDoan.addRow(new Object[] {
						infoCD.getMaCD(), infoCD.getSanPham().getMaSP(), infoCD.getToSanXuat().getMaTo(),infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getSoLuongTP() ,dfGiaSX.format(infoCD.getGiaSX()),infoCD.getSoLuongDSX() , infoCD.getTrangThaiCD()});
				}
			}
	}
	
	/**
	 * Hiện danh sách thông tin CĐ theo sđt của KH
	 * @param list
	 */
	private void loadCDTheoMaCD(ArrayList<CongDoan> list) {
		removeDanhSachCD(modelCongDoan);;
		//CongDoan cd = daoCongDoan.getCongDoanTheoMaCD(txtTim.getText().trim());
		ArrayList<CongDoan> lstCD = daoCongDoan.getCongDoanTheoMaCD(txtTim.getText().trim());
		for(CongDoan infoCD : lstCD) {
			if(infoCD.getTrangThaiCD().equalsIgnoreCase("Ngừng Sản Xuất")) {
				modelCongDoan.addRow(new Object[] {
						infoCD.getMaCD(), infoCD.getSanPham().getMaSP(),"",infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getSoLuongTP() ,dfGiaSX.format(infoCD.getGiaSX()), infoCD.getSoLuongDSX(),infoCD.getTrangThaiCD()});
			}else
				{
				modelCongDoan.addRow(new Object[] {
						infoCD.getMaCD(), infoCD.getSanPham().getMaSP(), infoCD.getToSanXuat().getMaTo(),infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getSoLuongTP() ,dfGiaSX.format(infoCD.getGiaSX()),infoCD.getSoLuongDSX() , infoCD.getTrangThaiCD()});
				}
			}
		
	}

	@SuppressWarnings("deprecation")
	private void choose1CD() {
		int selectedRow = tblCongDoan.getSelectedRow();
		if((selectedRow>=0 && tblSanPham.getSelectedRow()==-1) || (selectedRow>=0 && tblSanPham.getSelectedRow()!=-1)) {
			String maCD = tblCongDoan.getValueAt(selectedRow, 0).toString();
		
			ArrayList<CongDoan> lstCD = daoCongDoan.getAllDanhSachCD();
			for(CongDoan cd : lstCD) {
				if(maCD.equals(cd.getMaCD())) {
					
					txtTenCD.setText(cd.getTenCD());
					txtTenThanhPham.setText(cd.getTenThanhPham());
					txtGiaSX_CD.setText(modelCongDoan.getValueAt(tblCongDoan.getSelectedRow(), 6).toString());
					txtSoLuongTP.setText(modelCongDoan.getValueAt(tblCongDoan.getSelectedRow(), 5).toString());
					cboTrangThaiCongDoan.setSelectedItem(cd.getTrangThaiCD()+"");
					if(modelCongDoan.getValueAt(tblCongDoan.getSelectedRow(),2 ) != null) {
						loadComboChoserCD(modelCongDoan.getValueAt(tblCongDoan.getSelectedRow(),2 ).toString());
					}
					
					cboToSX.setSelectedItem(cd.getToSanXuat().getMaTo());
				}
			}
		}
	}
	private void loadComboChoserCD(String string) {
		ModelCbbTo.removeAllElements();
		ModelCbbTo.addElement(string);
		 ArrayList<ToSanXuat> lstTSX = daoToSanXuat.getDSToSanXuat();
		for(ToSanXuat TSX : lstTSX) {
			if(TSX.getMaCD()== null) {
				ModelCbbTo.addElement(TSX.getMaTo());
			}
			
		}
		
		
	}

	private void choose1SP() {
		int selectedRow = tblSanPham.getSelectedRow();
		if(selectedRow >= 0) {
			String maSP = tblSanPham.getValueAt(selectedRow, 0).toString();

			ArrayList<CongDoan> lstCD = daoCongDoan.getAllDanhSachCD();
//			autoUpdateSlDSXCongDoan(lstCD);
			for(CongDoan infoCD : lstCD) {
				if(maSP.equals(infoCD.getSanPham().getMaSP())) {
					
					
					if(infoCD.getTrangThaiCD().equalsIgnoreCase("Ngừng Sản Xuất")) {
						modelCongDoan.addRow(new Object[] {
								infoCD.getMaCD(), infoCD.getSanPham().getMaSP(),"",infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getSoLuongTP() ,dfGiaSX.format(infoCD.getGiaSX()), infoCD.getSoLuongDSX(),infoCD.getTrangThaiCD()});
					}else
						{
						modelCongDoan.addRow(new Object[] {
								infoCD.getMaCD(), infoCD.getSanPham().getMaSP(), infoCD.getToSanXuat().getMaTo(),infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getSoLuongTP() ,dfGiaSX.format(infoCD.getGiaSX()),infoCD.getSoLuongDSX() , infoCD.getTrangThaiCD()});
						}
				}
			}
		}
	}
	private void autoUpdateSlDSXCongDoan(ArrayList<CongDoan> lstCD)  {
		for (CongDoan cDoan : lstCD) {
			ToSanXuat toSanXuat = cDoan.getToSanXuat();
			int soSpTo = 0;
			ArrayList<CongNhan> lstCN =  daoCongNhan.getDSCongNhanCungTo(toSanXuat.getMaTo());	
			for (CongNhan cn : lstCN) {
				ArrayList<ChamCongCN> lisctCCCN = daoPhieuChamCong.getChamCongCongNhan(cn.getMaCN());
				
				for (ChamCongCN info : lisctCCCN) {
						soSpTo += info.getSoLuong();
					
				}
			}
			
		

			try {
				daoCongDoan.capNhatSoLuongDSX(cDoan.getMaCD(), soSpTo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@SuppressWarnings("deprecation")
	private void findCD() {
		String input = txtTim.getText().trim();
		input = input.toUpperCase();
		String regexMaCD = "((CD|cd)[0-9]{3})";
		String regexTenCD= "^[ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$";
		String regexTenThanhPham= "^[ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$";
		
		
		ArrayList<CongDoan> lstCD = null;
		
		if (!txtTim.getText().equals("") && !txtTim.getText().equals("Tìm theo mã sản phẩm")) {
			if(regex.regexTimCongDoan(txtTim)) {
				if (txtTim.getText().trim().matches(regexMaCD)) {
					lstCD = daoCongDoan.getCongDoanTheoMaCD(txtTim.getText().trim());
					loadCDTheoMaCD(lstCD);
				}
				if(lstCD.size() == 0 ) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin tìm kiếm phù hợp!");
					loadCDTheoMaCD(lstCD);;
				}
			}
		} else {
			
			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm!", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			txtTim.requestFocus();
			txtTim.selectAll();
		}
	
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	private void checkInfoCD() {
		if(!txtTenCD.getText().trim().equals("") && !txtTenThanhPham.getText().trim().equals("")  ) {
			String phatSinhMaCD = daoPhatSinhMa.getMaCD();
			String tenCD = txtTenCD.getText();
			String tenThanhPham = txtTenThanhPham.getText();
			float giaSX = Float.parseFloat(txtGiaSX_CD.getText());
			float giaSX1 = (float) Float.parseFloat(txtGiaSX_CD.getText());
			String trangThaiCD = cboTrangThaiCongDoan.getSelectedItem().toString();
			int sl = Integer.parseInt(txtSoLuongTP.getText());
			int chonSP = tblSanPham.getSelectedRow(); 
			String matsx = cboToSX.getSelectedItem().toString();
			if(chonSP>=0) {
							
				String maSanPhamChon = tblSanPham.getValueAt(chonSP, 0).toString();
				String giaSX_Test = tblSanPham.getValueAt(chonSP, 2).toString();
				float giaSX_SP = Float.parseFloat(giaSX_Test);
				
				SanPham sp = daoSanPham.getSPTheoMaSP(maSanPhamChon);
				ToSanXuat toSanXuat = daoToSanXuat.getToSXfromMaToSX(matsx);
			
				if (giaSX_SP > giaSX1) {
				if(regex.regexTenCD(txtTenCD)) {
							CongDoan cd = new CongDoan(phatSinhMaCD, tenCD, tenThanhPham, giaSX, trangThaiCD,sl, toSanXuat, sp);
							try {
								if(trangThaiCD.equals("Đang Sản Xuất")) {
									daoCongDoan.themCD(cd);
									CT_CD_SX_SP ct = new CT_CD_SX_SP(cd, sp, giaSX);
									//resetAll();
									daoCT.themCT_CD_SX_SP(ct);
									daoToSanXuat.capNhatMaCD2ToSX(matsx, phatSinhMaCD);
									JOptionPane.showMessageDialog(this, "Thêm công đoạn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							
								}

								if(trangThaiCD.equals("Ngừng Sản Xuất")) 
									JOptionPane.showMessageDialog(this, "Không được thêm công đoạn có trạng thái ngừng sản xuất!", "Thông báo", JOptionPane.WARNING_MESSAGE);
							}catch (SQLException e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(this, "Thêm công đoạn thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
							}
						//removeDanhSachSP(modelCongDoan);
						//loadDSSanPhamĐangSanXuat(sp);
				}
				}
				else
					JOptionPane.showMessageDialog(null, "Giá sản xuất công đoạn phải nhỏ hơn giá sản xuất sản phẩm!", "Thông báo", JOptionPane.OK_OPTION);

			}
			else
				JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm muốn thêm công đoạn!", "Thông báo", JOptionPane.OK_OPTION);
		}	
		else {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Thêm 1 CD mới vào dữ liệu và hiện lên bảng danh sách
	 */
	@SuppressWarnings("deprecation")
	private void addCD() {
			checkInfoCD();
			removeDanhSachCD(modelCongDoan);
			//loadDanhSachCD(cd);
			choose1SP();
	}

	/**
	 * Sửa, cập nhật thông tin CĐ
	 * @throws SQLException
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	private void updateCD() throws SQLException { //thông tin KH trong ddp ko đc sửa
		int row = tblCongDoan.getSelectedRow();
		if(row>=0) {
			int update = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa thông tin công đoạn này?", "Thông báo", JOptionPane.YES_NO_OPTION);
			if(update == JOptionPane.YES_OPTION) {
				String maCD = tblCongDoan.getValueAt(row, 0).toString();
				String maSP = tblCongDoan.getValueAt(row, 1).toString();
				
				String maToOld = tblCongDoan.getValueAt(row, 2) !=null ? tblCongDoan.getValueAt(row, 2).toString() : "";
				String tenCD = txtTenCD.getText();
				String tenThanhPham = txtTenThanhPham.getText();
				float giaSX = Float.parseFloat(txtGiaSX_CD.getText());
				int sl = Integer.parseInt(txtSoLuongTP.getText());
				String trangThaiCD = cboTrangThaiCongDoan.getSelectedItem().toString();
				
				String maTo = cboToSX.getSelectedItem().toString();
				
				CongDoan cd=new CongDoan();
				cd.setTenCD(tenCD);
				cd.setTenThanhPham(tenThanhPham);
				cd.setGiaSX(giaSX);
				cd.setTrangThaiCD(trangThaiCD);
				cd.setSoLuongTP(sl);
				
				daoCT.suaGiaSX(maCD, maSP, giaSX);
				daoCongDoan.capNhatCD(cd, maCD);
				removeDanhSachCD(modelCongDoan);
				
				
			
				if (trangThaiCD.equalsIgnoreCase("Ngừng Sản Xuất")) {
					daoToSanXuat.capNhatMaCD2ToSX(maToOld, null);
				}else {
					if(cboToSX.getSelectedItem() != null) {
						if(!maToOld.equalsIgnoreCase("")) {
							daoToSanXuat.capNhatMaCD2ToSX(maToOld, null);
						}					
						daoToSanXuat.capNhatMaCD2ToSX(maTo, maCD);
						
					}
				}
				//resetAll();
				choose1SP();
				//removeDanhSachCD(modelCongDoan);
				//modelCongDoan.setRowCount(0);
				//modelCongDoan.addRow(new Object[] {
				//		maCD, maSP, tenCD, tenThanhPham, dfGiaSX.format(giaSX), trangThaiCD
				//});
				
				JOptionPane.showMessageDialog(this, "Sửa thông tin công đoạn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				ArrayList<ToSanXuat> listTo = daoToSanXuat.getDSToSanXuat();
				loadItemsComboMaTo(listTo);
				
			}
		}
		else
			JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin công đoạn cần sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	}
	private void xuatExcel() throws IOException {
		XuatExcels xuat = new XuatExcels();
		FileDialog fileDialog = new FileDialog(FrmQLCongDoan, "Xuất Danh Sách Thông Tin Công Đoạn", FileDialog.SAVE);
		fileDialog.setFile("Danh Sách Thông Tin Công Đoạn");
		fileDialog .setVisible(true);
		String name = fileDialog.getFile();
		String fileName = fileDialog.getDirectory() + name;

		if (name == null) 
			return;
		
		if(!fileName.endsWith(".xlsx")||!fileName.endsWith(".xls")) 
			fileName += ".xlsx";
		
		xuat.xuatTable(tblCongDoan, "Danh Sách Thông Tin Công Đoạn", fileName);
	}
	/**
	 * Sắp xếp theo mã CD tăng dần
	 * @param ddp
	 */
	private void sortMaCDTangDan(CongDoan cd) {
		removeDanhSachCD(modelCongDoan);;
		ArrayList<CongDoan> lstCD = daoCongDoan.sortMaCD("ASC");
		for(CongDoan infoCD : lstCD) {
			modelCongDoan.addRow(new Object[] {
					infoCD.getMaCD(), infoCD.getSanPham().getMaSP(), infoCD.getToSanXuat().getMaTo(),infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getSoLuongTP() ,dfGiaSX.format(infoCD.getGiaSX()), infoCD.getSoLuongDSX(),infoCD.getTrangThaiCD()
			});
		}
	}

	/**
	 * Sắp xếp theo mã CĐ giảm dần
	 * @param ddp
	 */
	private void sortMaCDGiamDan(CongDoan cd) {
		removeDanhSachCD(modelCongDoan);;
		ArrayList<CongDoan> lstCD = daoCongDoan.sortMaCD("DESC");
		for(CongDoan infoCD : lstCD) {
			if(infoCD.getTrangThaiCD().equalsIgnoreCase("Ngừng Sản Xuất")) {
				modelCongDoan.addRow(new Object[] {
						infoCD.getMaCD(), infoCD.getSanPham().getMaSP(),"",infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getSoLuongTP() ,dfGiaSX.format(infoCD.getGiaSX()), infoCD.getSoLuongDSX(),infoCD.getTrangThaiCD()});
			}else
				{
				modelCongDoan.addRow(new Object[] {
						infoCD.getMaCD(), infoCD.getSanPham().getMaSP(), infoCD.getToSanXuat().getMaTo(),infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getSoLuongTP() ,dfGiaSX.format(infoCD.getGiaSX()),infoCD.getSoLuongDSX() , infoCD.getTrangThaiCD()});
				}
		}
	}
	/**
	 * Sắp xếp theo mã SP tăng dần
	 * @param ddp
	 */
	private void sortMaSPTangDan(CongDoan cd) {
		removeDanhSachCD(modelCongDoan);;
		ArrayList<CongDoan> lstCD = daoCongDoan.sortMaSP("ASC");
		for(CongDoan infoCD : lstCD) {
			if(infoCD.getTrangThaiCD().equalsIgnoreCase("Ngừng Sản Xuất")) {
				modelCongDoan.addRow(new Object[] {
						infoCD.getMaCD(), infoCD.getSanPham().getMaSP(),"",infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getSoLuongTP() ,dfGiaSX.format(infoCD.getGiaSX()), infoCD.getSoLuongDSX(),infoCD.getTrangThaiCD()});
			}else
				{
				modelCongDoan.addRow(new Object[] {
						infoCD.getMaCD(), infoCD.getSanPham().getMaSP(), infoCD.getToSanXuat().getMaTo(),infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getSoLuongTP() ,dfGiaSX.format(infoCD.getGiaSX()),infoCD.getSoLuongDSX() , infoCD.getTrangThaiCD()});
				}
		}
	}

	/**
	 * Sắp xếp theo mã SP giảm dần
	 * @param ddp
	 */
	private void sortMaSPGiamDan(CongDoan cd) {
		removeDanhSachCD(modelCongDoan);;
		ArrayList<CongDoan> lstCD = daoCongDoan.sortMaSP("DESC");
		for(CongDoan infoCD : lstCD) {
			if(infoCD.getTrangThaiCD().equalsIgnoreCase("Ngừng Sản Xuất")) {
				modelCongDoan.addRow(new Object[] {
						infoCD.getMaCD(), infoCD.getSanPham().getMaSP(),"",infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getSoLuongTP() ,dfGiaSX.format(infoCD.getGiaSX()), infoCD.getSoLuongDSX(),infoCD.getTrangThaiCD()});
			}else
				{
				modelCongDoan.addRow(new Object[] {
						infoCD.getMaCD(), infoCD.getSanPham().getMaSP(), infoCD.getToSanXuat().getMaTo(),infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getSoLuongTP() ,dfGiaSX.format(infoCD.getGiaSX()),infoCD.getSoLuongDSX() , infoCD.getTrangThaiCD()});
				}
		}
	}

	/**
	 *Code sự kiện
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnTim)) {
			if(txtTim.getText().equals("") || txtTim.getText().equals("Tìm công đoạn theo mã công đoạn.")) {
				removeDanhSachCD(modelCongDoan);
				JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				txtTim.requestFocus();
			}else 
				findCD();
		}

		//thêm CĐ
		if(o.equals(btnThemCD)) {
			addCD();
		}

		//sửa CĐ
		if(o.equals(btnSuaCD)) {
			try {
				updateCD();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		//làm mới
		if(o.equals(btnLamMoiCD)) {
			resetAll();
		}

		//xóa chọn radbutton khi chọn combobox
//		if(o.equals(cboSapXep)) {
//			if(cboSapXep.getSelectedItem()=="Tăng dần")
//				bg.clearSelection();
//				removeDanhSachCD(modelCongDoan);
//			if(cboSapXep.getSelectedItem()=="Giảm dần")
//				bg.clearSelection();
//				removeDanhSachCD(modelCongDoan);
//		}

		//sapxep tăng
		if(cboSapXep.getSelectedItem()=="Tăng dần") {
			if(o.equals(rdoTheoMaCongDoan))
				sortMaCDTangDan(cd);
			if(o.equals(rdoTheoMaSanPham))
				sortMaSPTangDan(cd);
		}

		//sapxep giảm
		if(cboSapXep.getSelectedItem()=="Giảm dần") {
			if(o.equals(rdoTheoMaCongDoan))
				sortMaCDGiamDan(cd);
			if(o.equals(rdoTheoMaSanPham))
				sortMaSPGiamDan(cd);
		}
		if(o.equals(btnExcels)) {
			try {
				xuatExcel();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 *Sự kiện placeholder của txtTim
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if(txtTim.getText().equals("Tìm công đoạn theo mã công đoạn.")) {
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
			txtTim.setText("Tìm công đoạn theo mã công đoạn.");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object o = e.getSource();
		int key = e.getKeyCode();
		
		if(o.equals(txtTim) && key==KeyEvent.VK_ENTER)
			btnTim.doClick();
		
		else if(o.equals(txtTim) && key == KeyEvent.VK_F5)
			btnLamMoiCD.doClick();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
