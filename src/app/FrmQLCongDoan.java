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
import dao.DAOPhatSinhMa;
//import dao.DAOKhachHang;
//import dao.DAOLoaiKH;
//import dao.DAOLoaiPhong;
//import dao.DAONhanVien;
//import dao.DAOPhatSinhMa;
import dao.DAOSanPham;
import dao.DAOTaiKhoan;
import dao.Regex;
import entity.CT_CD_SX_SP;
import entity.CongDoan;
import entity.SanPham;
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


	/**
	 * @return pMain
	 */
	public Panel getFrmQLCongDoan() {
		return this.pMain;
	}
	
	/**
	 * K??? th???a t??n v?? m?? NV, ng??y hi???n t???i c???a FrmQuanLy
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
		 * K???t n???i database
		 */
		try {
			ConnectDB.getinstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		/**
		 * Khai b??o c??c DAO
		 */
		daoSanPham=new DAOSanPham();
		daoCongDoan=new DAOCongDoan();
		daoCT = new DAOCT_CD_SX_SP();
		daoPhatSinhMa = new DAOPhatSinhMa();
		regex = new Regex();

		/**
		 * Khai b??o c??c entity
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

		dfGiaSX=new DecimalFormat("###,###");
		/**
		 * Nh???p th??ng tin c??ng ??o???n m???i
		 * Panel pNhapThongTin
		 */
		pNhapThongTin = new JPanel();
		pNhapThongTin.setToolTipText("C??c th??ng tin C?? c???n nh???p");
		pNhapThongTin.setBorder(new LineBorder(new Color(114, 23, 153)));
		pNhapThongTin.setBackground(Color.WHITE);
		pNhapThongTin.setBounds(10, 11, 333, 607);
		pMain.add(pNhapThongTin);
		pNhapThongTin.setLayout(null);

		lblNhapThongTin = new JLabel("Nh???p Th??ng Tin C??ng ??o???n");
		lblNhapThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhapThongTin.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNhapThongTin.setBounds(10, 11, 292, 29);
		pNhapThongTin.add(lblNhapThongTin);
		
		//Ten cong doan
		JLabel lblTenCD = new JLabel("T??n C??ng ??o???n:");
		lblTenCD.setBounds(10, 75, 125, 26);
		pNhapThongTin.add(lblTenCD);
		lblTenCD.setFont(new Font("SansSerif", Font.PLAIN, 15));

		txtTenCD = new JTextField();
		txtTenCD.setBounds(132, 75, 191, 35);
		pNhapThongTin.add(txtTenCD);
		txtTenCD.setBackground(new Color(255, 255, 255));
		txtTenCD.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenCD.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtTenCD.setColumns(20);
		
		//ten thanh pham
		JLabel lblTenThanhPham = new JLabel("T??n T.Ph???m:");
		lblTenThanhPham.setBounds(10, 125, 111, 26);
		pNhapThongTin.add(lblTenThanhPham);
		lblTenThanhPham.setFont(new Font("SansSerif", Font.PLAIN, 15));

		txtTenThanhPham = new JTextField();
		txtTenThanhPham.setBounds(132, 125, 191, 35);
		pNhapThongTin.add(txtTenThanhPham);
		txtTenThanhPham.setBackground(new Color(255, 255, 255));
		txtTenThanhPham.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenThanhPham.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtTenThanhPham.setColumns(20);
		
		//gia san xuat 
		JLabel lblGiaSX_CD = new JLabel("Gi?? SX_C??:");
		lblGiaSX_CD.setBounds(10, 175, 102, 26);
		pNhapThongTin.add(lblGiaSX_CD);
		lblGiaSX_CD.setFont(new Font("SansSerif", Font.PLAIN, 15));

		txtGiaSX_CD = new JTextField();
		txtGiaSX_CD.setBounds(132, 175, 191, 35);
		pNhapThongTin.add(txtGiaSX_CD);
		txtGiaSX_CD.setBackground(new Color(255, 255, 255));
		txtGiaSX_CD.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtGiaSX_CD.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtGiaSX_CD.setColumns(20);
		
		//tinh trang cong doan
		lblTinhTrangCongDoan = new JLabel("Tr???ng th??i C??:");
		lblTinhTrangCongDoan.setBounds(10, 235, 133, 19);
		lblTinhTrangCongDoan.setFont(new Font("SansSerif", Font.BOLD, 15));
		pNhapThongTin.add(lblTinhTrangCongDoan);

		cboTrangThaiCongDoan = new JComboBox<Object>(new Object[] {"??ang S???n Xu???t", "Ng???ng S???n Xu???t"});
		cboTrangThaiCongDoan.setToolTipText("Ch???n tr???ng th??i C??");
		cboTrangThaiCongDoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboTrangThaiCongDoan.setBounds(145, 225, 178, 35);
		cboTrangThaiCongDoan.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboTrangThaiCongDoan.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cboTrangThaiCongDoan.setBackground(Color.white);
		pNhapThongTin.add(cboTrangThaiCongDoan);

		lblTim = new JLabel("T??m ki???m:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(550, 12, 80, 35);
		pMain.add(lblTim);
		//
		txtTim = new JTextField();
		txtTim.setToolTipText("T??m c??ng ??o???n theo m?? c??ng ??o???n.");
		txtTim.setText("T??m c??ng ??o???n theo m?? c??ng ??o???n.");
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTim.setForeground(Colors.LightGray);
		txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		txtTim.setBounds(632, 12, 300, 33);
		pMain.add(txtTim);
		//
		btnTim = new FixButton("T??m");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTim.setBackground(new Color(114, 23, 153));
		btnTim.setBounds(940, 12, 150, 33);
		Icon iconTim = IconFontSwing.buildIcon(FontAwesome.SEARCH, 20, Color.white);
		btnTim.setIcon(iconTim);
		pMain.add(btnTim);
		
		btnExcels = new FixButton("Xu???t Excels");
		btnExcels.setForeground(Color.WHITE);
		btnExcels.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnExcels.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnExcels.setBackground(new Color(16, 124, 65));
		btnExcels.setBounds(1101, 12, 159, 33);
		Icon iconExcel = IconFontSwing.buildIcon(FontAwesome.FILE_EXCEL_O, 20, Color.white);
		btnExcels.setIcon(iconExcel);
		pMain.add(btnExcels);

		lblChonSanPham = new JLabel("Ch???n s???n ph???m:");
		lblChonSanPham.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblChonSanPham.setBounds(431, 70, 200, 29);
		pMain.add(lblChonSanPham);

		JScrollPane scrollPaneChonSanPham = new JScrollPane(tblSanPham, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneChonSanPham.setToolTipText("Danh s??ch th??ng tin s???n ph???m:");
		scrollPaneChonSanPham.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneChonSanPham.setBackground(new Color(164, 44, 167));
		scrollPaneChonSanPham.setBounds(353, 106, 273, 512);
		scrollPaneChonSanPham.getHorizontalScrollBar();
		pMain.add(scrollPaneChonSanPham);

		String colSanPham[] = {"M?? s???n ph???m", "T??n s???n ph???m", "Gi?? s???n xu???t", "S??? l?????ng"};
		modelSanPham=new DefaultTableModel(colSanPham, 0);

		tblSanPham = new JTable(modelSanPham);
		tblSanPham.setToolTipText("Ch???n th??ng tin s???n ph???m mu???n s???n xu???t");
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

		JTableHeader tbHeaderSanPham = tblSanPham.getTableHeader();
		tbHeaderSanPham.setToolTipText("Ch???n th??ng tin s???n ph???m");
		tbHeaderSanPham.setBackground(new Color(164, 44, 167));
		tbHeaderSanPham.setForeground(Color.white);
		tbHeaderSanPham.setFont(new Font("SansSerif", Font.BOLD, 14));

		tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(100);//masanpham
		tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(120);//tensanpham
		tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(100);//giasanxuat
		tblSanPham.getColumnModel().getColumn(3).setPreferredWidth(150);//soluong

		DefaultTableCellRenderer rightRenderer=new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		tblSanPham.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		tblSanPham.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

		scrollPaneChonSanPham.setViewportView(tblSanPham);

		/**
		 * Th??m 1 C?? v??o danh s??ch b???ng C??
		 * N??t th??m C??
		 * JButton btnThemCD
		 * Icon iconThemCD
		 */
		btnThemCD = new FixButton("Th??m");
		btnThemCD.setForeground(Color.black);
		btnThemCD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThemCD.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThemCD.setBackground(new Color(57, 210, 247));
		btnThemCD.setBounds(10, 450, 310, 35);
		Icon iconThemCD = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, Color.white);
		btnThemCD.setIcon(iconThemCD);
		pNhapThongTin.add(btnThemCD);

		/**
		 * S???a th??ng tin C??
		 * N??t s???a C??
		 * JButton btnSuaC??
		 * Icon iconSuaC??
		 */
		btnSuaCD = new FixButton("S???a");
		btnSuaCD.setForeground(Color.black);
		btnSuaCD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSuaCD.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSuaCD.setBackground(new Color(133, 217, 191));
		btnSuaCD.setBounds(10, 500, 310, 35);
		Icon iconSuaCD = IconFontSwing.buildIcon(FontAwesome.WRENCH, 20, Color.white);
		btnSuaCD.setIcon(iconSuaCD);
		pNhapThongTin.add(btnSuaCD);

		/**
		 * L??m m???i: x??a tr???ng c??c text, x??a t???t c??? n???i dung trong b???ng DDP, ?????t m???c ?????nh c??c combobox, b??? ch???n checkbox v?? c??c radiobutton
		 * N??t l??m m???i
		 * JButton btnLamMoiCD
		 * Icon iconLamMoiCD
		 */
		btnLamMoiCD = new FixButton("L??m m???i");
		btnLamMoiCD.setForeground(Color.white);
		btnLamMoiCD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoiCD.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLamMoiCD.setBackground(new Color(114, 23, 153));
		btnLamMoiCD.setBounds(10, 550, 310, 35);
		Icon iconLamMoiCD = IconFontSwing.buildIcon(FontAwesome.REFRESH, 20, Color.white);
		btnLamMoiCD.setIcon(iconLamMoiCD);
		pNhapThongTin.add(btnLamMoiCD);

		/**
		 * Khung s???p x???p ch???a c??c m???c s???p x???p theo t??ng d???n, gi???m d???n
		 * JPanel pSapXep
		 */
		JPanel pSapXep = new JPanel();
		pSapXep.setToolTipText("S???p X???p Th??ng Tin C??ng ??o???n");
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "S???p X???p C??ng ??o???n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBackground(new Color(178, 192, 237));
		pSapXep.setBounds(632, 48, 628, 50);
		pMain.add(pSapXep);
		pSapXep.setLayout(null);

		/**
		 * Ch???n ki???u s???p x???p t??ng d???n ho???c gi???m d???n
		 * JComboBox cboSapXep
		 */
		cboSapXep = new JComboBox<Object>(new Object[]{"T??ng d???n", "Gi???m d???n"});
		cboSapXep.setToolTipText("S???p x???p th??ng tin c??ng ??o???n t??ng d???n ho???c gi???m d???n theo c??c ti??u ch??");
		cboSapXep.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboSapXep.setBounds(34, 14, 123, 28);
		cboSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboSapXep.setBackground(Color.WHITE);
		pSapXep.add(cboSapXep);

		rdoTheoMaSanPham = new JRadioButton("Theo M?? S???n Ph???m");
		rdoTheoMaSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdoTheoMaSanPham.setBounds(250, 16, 180, 27);
		rdoTheoMaSanPham.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoMaSanPham.setBackground(new Color(178, 192, 237));
		pSapXep.add(rdoTheoMaSanPham);

		rdoTheoMaCongDoan = new JRadioButton("Theo M?? C??ng ??o???n");
		rdoTheoMaCongDoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdoTheoMaCongDoan.setBounds(450, 16, 180, 27);
		rdoTheoMaCongDoan.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoMaCongDoan.setBackground(new Color(178, 192, 237));
		pSapXep.add(rdoTheoMaCongDoan);

		/**
		 * Nh??m c??c radiobutton
		 * ButtonGroup bg
		 */
		bg=new ButtonGroup();
		bg.add(rdoTheoMaSanPham); bg.add(rdoTheoMaCongDoan);

		JScrollPane scrollPaneCD = new JScrollPane();
		scrollPaneCD.setToolTipText("Danh s??ch th??ng tin C??");
		scrollPaneCD.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneCD.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneCD.setBackground(new Color(164, 44, 167));
		scrollPaneCD.setBounds(632, 106, 628, 512);
		scrollPaneCD.getHorizontalScrollBar();
		pMain.add(scrollPaneCD);

		String colCD[] = {"M?? C??ng ??o???n", "M?? S???n Ph???m", "T??n C??ng ??o???n", "T??n Th??nh Ph???m","Gi?? S???n Xu???t","Tr???ng Th??i CD"};
		modelCongDoan=new DefaultTableModel(colCD, 0);

		tblCongDoan=new JTable(modelCongDoan);
		tblCongDoan.setToolTipText("Ch???n th??ng tin C?? ????? th???c hi???n c??c ch???c n??ng");
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
		tbHeaderDDP.setToolTipText("Danh s??ch th??ng tin C??");
		tbHeaderDDP.setBackground(new Color(164, 44, 167));
		tbHeaderDDP.setForeground(Color.white);
		tbHeaderDDP.setFont(new Font("SansSerif", Font.BOLD, 14));

		tblCongDoan.getColumnModel().getColumn(0).setPreferredWidth(148);
		tblCongDoan.getColumnModel().getColumn(1).setPreferredWidth(148);
		tblCongDoan.getColumnModel().getColumn(2).setPreferredWidth(182);
		tblCongDoan.getColumnModel().getColumn(3).setPreferredWidth(148);
		tblCongDoan.getColumnModel().getColumn(4).setPreferredWidth(148);
		tblCongDoan.getColumnModel().getColumn(5).setPreferredWidth(148);
		
		DefaultTableCellRenderer rightRenderer2=new DefaultTableCellRenderer();
		rightRenderer2.setHorizontalAlignment(JLabel.RIGHT);
		tblCongDoan.getColumnModel().getColumn(0).setCellRenderer(rightRenderer2);
		tblCongDoan.getColumnModel().getColumn(1).setCellRenderer(rightRenderer2);
		tblCongDoan.getColumnModel().getColumn(2).setCellRenderer(rightRenderer2);
		tblCongDoan.getColumnModel().getColumn(3).setCellRenderer(rightRenderer2);
		tblCongDoan.getColumnModel().getColumn(4).setCellRenderer(rightRenderer2);
		tblCongDoan.getColumnModel().getColumn(5).setCellRenderer(rightRenderer2);
		
		scrollPaneCD.setViewportView(tblCongDoan);

		/**
		 * H??nh n???n c???a giao di???n
		 */
		lblBackGround=new JLabel("");
		lblBackGround.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackGround.setBounds(0, 0, 1281, 629);
		Image imgBackGround = Toolkit.getDefaultToolkit().getImage("data\\img\\background.png");
		Image resizeBG = imgBackGround.getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), 0);
		lblBackGround.setIcon(new ImageIcon(resizeBG));
		pMain.add(lblBackGround);

		loadDanhSachCD(cd);

		


		loadDSSanPham??angSanXuat(sp);

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
	
	private void removeDanhSachSP(DefaultTableModel defaultTableModel) {
		while(tblSanPham.getRowCount() > 0)
			modelSanPham.removeRow(0);
	}

	/**
	 * @param defaultTableModel tr??? v??? modelCD
	 */
	private void removeDanhSachCD(DefaultTableModel defaultTableModel) {
		while(tblCongDoan.getRowCount() > 0)
			modelCongDoan.removeRow(0);
	}

	/**
	 * X??a tr???ng textfield v?? textarea, ?????t l???i m???c ?????nh c??c combobox v?? c??c button
	 */
	@SuppressWarnings("deprecation")
	private void resetAll() {
		txtTim.setText("T??m c??ng ??o???n theo m?? c??ng ??o???n.s");
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTim.setForeground(Colors.LightGray);
		
		
		txtTenCD.setText("");
		txtTenThanhPham.setText("");
		txtGiaSX_CD.setText("");
		cboTrangThaiCongDoan.setSelectedItem("??ang S???n Xu???t");


		removeDanhSachSP(modelCongDoan);
		loadDSSanPham??angSanXuat(new SanPham());;

		removeDanhSachCD(modelCongDoan);
		loadDanhSachCD(cd);
		
	}

	private void loadDSSanPham??angSanXuat(SanPham sp) {
		removeDanhSachSP(modelSanPham);
		ArrayList<SanPham> lstSP = daoSanPham.getDSSanPham();
		for(SanPham infoSP : lstSP) {
			modelSanPham.addRow(new Object[] {
					infoSP.getMaSP(), infoSP.getTenSP(), dfGiaSX.format(infoSP.getGiaSX()), infoSP.getSoLuong()
			});
		}
	}

	private void loadDanhSachCD(CongDoan cd) {
		removeDanhSachCD(modelCongDoan);
		ArrayList<CongDoan> lstCD = daoCongDoan.getAllDanhSachCD();
		for(CongDoan infoCD : lstCD) {
			modelCongDoan.addRow(new Object[] {
					infoCD.getMaCD(), infoCD.getSanPham().getMaSP(), infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getGiaSX(), infoCD.getTrangThaiCD()});
		}
	}

	/**
	 * Hi???n danh s??ch th??ng tin C?? theo s??t c???a KH
	 * @param list
	 */
	private void loadCDTheoMaCD(ArrayList<CongDoan> list) {
		removeDanhSachCD(modelCongDoan);;
		//CongDoan cd = daoCongDoan.getCongDoanTheoMaCD(txtTim.getText().trim());
		ArrayList<CongDoan> lstCD = daoCongDoan.getCongDoanTheoMaCD(txtTim.getText().trim());
		for(CongDoan infoCD : lstCD) {
			modelCongDoan.addRow(new Object[] {
					infoCD.getMaCD(), infoCD.getSanPham().getMaSP(), infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getGiaSX(), infoCD.getTrangThaiCD()			});
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
					txtGiaSX_CD.setText(modelCongDoan.getValueAt(tblCongDoan.getSelectedRow(), 4).toString());
					cboTrangThaiCongDoan.setSelectedItem(cd.getTrangThaiCD()+"");
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void findCD() {
		String input = txtTim.getText().trim();
		input = input.toUpperCase();
		String regexMaCD = "((CD|cd)[0-9]{3})";
		String regexTenCD= "^[ A-Za-za-zA-Z??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????]+$";
		String regexTenThanhPham= "^[ A-Za-za-zA-Z??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????]+$";
		
		
		ArrayList<CongDoan> lstCD = null;
		
		if (!txtTim.getText().equals("") && !txtTim.getText().equals("T??m theo m?? s???n ph???m")) {
			if(regex.regexTimCongDoan(txtTim)) {
				if (txtTim.getText().trim().matches(regexMaCD)) {
					lstCD = daoCongDoan.getCongDoanTheoMaCD(txtTim.getText().trim());
					loadCDTheoMaCD(lstCD);
				}
				if(lstCD.size() == 0 ) {
					JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y th??ng tin t??m ki???m ph?? h???p!");
					loadCDTheoMaCD(lstCD);;
				}
			}
		} else {
			
			JOptionPane.showMessageDialog(this, "Vui l??ng nh???p th??ng tin t??m ki???m!", "Th??ng b??o",
					JOptionPane.WARNING_MESSAGE);
			txtTim.requestFocus();
			txtTim.selectAll();
		}
	
	}

	@SuppressWarnings({ "deprecation", "unused" })
	private void checkInfoCD() {
		if(!txtTenCD.getText().trim().equals("") && !txtTenThanhPham.getText().trim().equals("") && regex.regexGiaSX(txtGiaSX_CD) ) {
			String phatSinhMaCD = daoPhatSinhMa.getMaCD();
			String tenCD = txtTenCD.getText();
			String tenThanhPham = txtTenThanhPham.getText();
			float giaSX = Float.parseFloat(txtGiaSX_CD.getText());
			String trangThaiCD = cboTrangThaiCongDoan.getSelectedItem().toString();

			int chonSP = tblSanPham.getSelectedRow(); //ch???n ph??ng l???y info t??? maSanPham
			if(chonSP>=0) {
				String maSanPhamChon = tblSanPham.getValueAt(chonSP, 0).toString();
				SanPham sp = daoSanPham.getSPTheoMaSP(maSanPhamChon);
				if(regex.regexTenCD(txtTenCD)) {
							CongDoan cd = new CongDoan(phatSinhMaCD, tenCD, tenThanhPham, giaSX, trangThaiCD, sp);
							try {
								if(trangThaiCD.equals("??ang S???n Xu???t")) {
									daoCongDoan.themCD(cd);
									CT_CD_SX_SP ct = new CT_CD_SX_SP(cd, sp, giaSX);
									//resetAll();
									daoCT.themCT_CD_SX_SP(ct);
									JOptionPane.showMessageDialog(this, "Th??m c??ng ??o???n th??nh c??ng!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
								}

								if(trangThaiCD.equals("Ng???ng S???n Xu???t")) 
									JOptionPane.showMessageDialog(this, "Kh??ng ???????c th??m c??ng ??o???n c?? tr???ng th??i ng???ng s???n xu???t!", "Th??ng b??o", JOptionPane.WARNING_MESSAGE);
							}catch (SQLException e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(this, "Th??m ????n ?????t ph??ng th???t b???i!", "Th??ng b??o", JOptionPane.ERROR_MESSAGE);
							}
						removeDanhSachSP(modelSanPham);
						loadDSSanPham??angSanXuat(sp);
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Vui l??ng ch???n ph??ng mu???n ?????t!", "Th??ng b??o", JOptionPane.OK_OPTION);
		}	
		else {
			JOptionPane.showMessageDialog(this, "Vui l??ng nh???p th??ng tin ?????y ?????!", "Th??ng b??o", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Th??m 1 CD m???i v??o d??? li???u v?? hi???n l??n b???ng danh s??ch
	 */
	@SuppressWarnings("deprecation")
	private void addCD() {
			checkInfoCD();
			removeDanhSachCD(modelCongDoan);
			loadDanhSachCD(cd);
	}

	/**
	 * S???a, c???p nh???t th??ng tin C??
	 * @throws SQLException
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	private void updateCD() throws SQLException { //th??ng tin KH trong ddp ko ??c s???a
		int row = tblCongDoan.getSelectedRow();
		if(row>=0) {
			int update = JOptionPane.showConfirmDialog(this, "B???n mu???n s???a th??ng tin c??ng ??o???n n??y?", "Th??ng b??o", JOptionPane.YES_NO_OPTION);
			if(update == JOptionPane.YES_OPTION) {
				String maCD = tblCongDoan.getValueAt(row, 0).toString();
				String maSP = tblCongDoan.getValueAt(row, 1).toString();

				String tenCD = txtTenCD.getText();
				String tenThanhPham = txtTenThanhPham.getText();
				float giaSX = Float.parseFloat(txtGiaSX_CD.getText());

				String trangThaiCD = cboTrangThaiCongDoan.getSelectedItem().toString();
				
				CongDoan cd=new CongDoan();
				cd.setTenCD(tenCD);
				cd.setTenThanhPham(tenThanhPham);
				cd.setGiaSX(giaSX);
				cd.setTrangThaiCD(trangThaiCD);
				
				daoCT.suaGiaSX(maCD, maSP, giaSX);
				daoCongDoan.capNhatCD(cd, maCD);
				removeDanhSachCD(modelCongDoan);
				
				resetAll();
				removeDanhSachCD(modelCongDoan);
				modelCongDoan.setRowCount(0);
				modelCongDoan.addRow(new Object[] {
						maCD, maSP, tenCD, tenThanhPham, giaSX, trangThaiCD
				});
				JOptionPane.showMessageDialog(this, "S???a th??ng tin c??ng ??o???n th??nh c??ng!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog(this, "Vui l??ng ch???n th??ng tin c??ng ??o???n c???n s???a!", "Th??ng b??o", JOptionPane.WARNING_MESSAGE);
	}
	private void xuatExcel() throws IOException {
		XuatExcels xuat = new XuatExcels();
		FileDialog fileDialog = new FileDialog(FrmQLCongDoan, "Xu???t Danh S??ch Th??ng Tin C??ng ??o???n", FileDialog.SAVE);
		fileDialog.setFile("Danh S??ch Th??ng Tin C??ng ??o???n");
		fileDialog .setVisible(true);
		String name = fileDialog.getFile();
		String fileName = fileDialog.getDirectory() + name;

		if (name == null) 
			return;
		
		if(!fileName.endsWith(".xlsx")||!fileName.endsWith(".xls")) 
			fileName += ".xlsx";
		
		xuat.xuatTable(tblCongDoan, "Danh S??ch Th??ng Tin C??ng ??o???n", fileName);
	}
	/**
	 * S???p x???p theo m?? CD t??ng d???n
	 * @param ddp
	 */
	private void sortMaCDTangDan(CongDoan cd) {
		removeDanhSachCD(modelCongDoan);;
		ArrayList<CongDoan> lstCD = daoCongDoan.sortMaCD("ASC");
		for(CongDoan infoCD : lstCD) {
			modelCongDoan.addRow(new Object[] {
					infoCD.getMaCD(), infoCD.getSanPham().getMaSP(), infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getGiaSX(), infoCD.getTrangThaiCD()			
			});
		}
	}

	/**
	 * S???p x???p theo m?? C?? gi???m d???n
	 * @param ddp
	 */
	private void sortMaCDGiamDan(CongDoan cd) {
		removeDanhSachCD(modelCongDoan);;
		ArrayList<CongDoan> lstCD = daoCongDoan.sortMaCD("DESC");
		for(CongDoan infoCD : lstCD) {
			modelCongDoan.addRow(new Object[] {
					infoCD.getMaCD(), infoCD.getSanPham().getMaSP(), infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getGiaSX(), infoCD.getTrangThaiCD()			
			});
		}
	}
	/**
	 * S???p x???p theo m?? SP t??ng d???n
	 * @param ddp
	 */
	private void sortMaSPTangDan(CongDoan cd) {
		removeDanhSachCD(modelCongDoan);;
		ArrayList<CongDoan> lstCD = daoCongDoan.sortMaSP("ASC");
		for(CongDoan infoCD : lstCD) {
			modelCongDoan.addRow(new Object[] {
					infoCD.getMaCD(), infoCD.getSanPham().getMaSP(), infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getGiaSX(), infoCD.getTrangThaiCD()			
			});
		}
	}

	/**
	 * S???p x???p theo m?? SP gi???m d???n
	 * @param ddp
	 */
	private void sortMaSPGiamDan(CongDoan cd) {
		removeDanhSachCD(modelCongDoan);;
		ArrayList<CongDoan> lstCD = daoCongDoan.sortMaSP("DESC");
		for(CongDoan infoCD : lstCD) {
			modelCongDoan.addRow(new Object[] {
					infoCD.getMaCD(), infoCD.getSanPham().getMaSP(), infoCD.getTenCD(), infoCD.getTenThanhPham(), infoCD.getGiaSX(), infoCD.getTrangThaiCD()			
			});
		}
	}

	/**
	 *Code s??? ki???n
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnTim)) {
			if(txtTim.getText().equals("") || txtTim.getText().equals("T??m c??ng ??o???n theo m?? c??ng ??o???n.")) {
				removeDanhSachCD(modelCongDoan);
				JOptionPane.showMessageDialog(this, "Vui l??ng nh???p th??ng tin t??m ki???m!", "Th??ng b??o", JOptionPane.WARNING_MESSAGE);
				txtTim.requestFocus();
			}else 
				findCD();
		}

		//th??m C??
		if(o.equals(btnThemCD)) {
			addCD();
		}

		//s???a C??
		if(o.equals(btnSuaCD)) {
			try {
				updateCD();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		//l??m m???i
		if(o.equals(btnLamMoiCD)) {
			resetAll();
		}

		//x??a ch???n radbutton khi ch???n combobox
		if(o.equals(cboSapXep)) {
			if(cboSapXep.getSelectedItem()=="T??ng d???n")
				bg.clearSelection();
				removeDanhSachCD(modelCongDoan);
			if(cboSapXep.getSelectedItem()=="Gi???m d???n")
				bg.clearSelection();
				removeDanhSachCD(modelCongDoan);
		}

		//sapxep t??ng
		if(cboSapXep.getSelectedItem()=="T??ng d???n") {
			if(o.equals(rdoTheoMaCongDoan))
				sortMaCDTangDan(cd);
			if(o.equals(rdoTheoMaSanPham))
				sortMaSPTangDan(cd);
		}

		//sapxep gi???m
		if(cboSapXep.getSelectedItem()=="Gi???m d???n") {
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
	 *S??? ki???n placeholder c???a txtTim
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if(txtTim.getText().equals("T??m c??ng ??o???n theo m?? c??ng ??o???n.")) {
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
			txtTim.setText("T??m c??ng ??o???n theo m?? c??ng ??o???n.");
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
