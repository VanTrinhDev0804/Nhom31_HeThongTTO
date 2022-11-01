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
import dao.DAOCongNhan;
import dao.DAOPhieuChamCong;
import dao.DAOPhieuLuongCN;
import dao.Regex;
import entity.CongNhan;
import entity.PhieuLuongCN;
import entity.PhieuLuongNV;
//import entity.LoaiMatHang;
//import entity.MatHang;
//import dao.Regex;
//import entity.LoaiMatHang;
//import entity.MatHang;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class FrmQLLuongCN extends JFrame implements ActionListener, MouseListener, KeyListener, ItemListener {
	private static final long serialVersionUID = 1L;
	private Panel pMain;
	private JTable tblLuong;
	private DefaultTableModel modelLuong;
	private FixButton btnTim;
	private JTextField txtTim;
	private FixButton btnReset;
	private DAOPhieuLuongCN daoPhieuLuongCN;

	private JComboBox<Object> cboSapXep;
	private JRadioButton rdoTheoLuongCN;
	private JRadioButton rdoTheoTo;
	private JRadioButton rdoTheoTenCN;
	private Regex regex;
	private JPanel pNhapThongTin;
	private JLabel lblNhapThongTin;
	private ButtonGroup bgRdo;
	private FixButton btnExcels;
	private JComboBox<String> cboTenCN;
	private JComboBox<String> cboMaCN;
	private JComboBox<String> cboNam;
	private JTextField txtLuong;
	private FixButton btnTinhLuong;
	private JRadioButton rdoTheoSoNgayCong;
	private DAOCongNhan daoCongNhan;
	private CongNhan cn;
	private DecimalFormat dfLuong;
	private SimpleDateFormat dfNam;
	private SimpleDateFormat dfThang;

	private JRadioButton rdoTheoChucVu;
	private DAOPhieuChamCong daoCCCN;
	private JTextField txtThang;
	private JTextField txtNam;
	private SimpleDateFormat dfDate;
	private SimpleDateFormat dfDate1;
	private JComboBox<String> cboThang;

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
		daoCongNhan = new DAOCongNhan();
		daoPhieuLuongCN = new DAOPhieuLuongCN();
//		daoCTLuongCB = new DAOCTLuongCB();
		daoCCCN = new DAOPhieuChamCong();
//		daoLoaiKH = new DAOLoaiKH();
//		daoKhachHang = new DAOKhachHang();
//		daoMaKH = new DAOPhatSinhMa();
		regex = new Regex();
		dfLuong = new DecimalFormat("##,###,###");
		dfNam = new SimpleDateFormat("yyyy");
<<<<<<< HEAD
		dfThang = new SimpleDateFormat("MM");
=======
		dfThang = new SimpleDateFormat("MM/yyyy");
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
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
		pNhapThongTin.setToolTipText("Lương công nhân");

		JLabel lblMaCN = new JLabel("Mã CN : ");
		lblMaCN.setBounds(10, 83, 102, 36);
		pNhapThongTin.add(lblMaCN);
		lblMaCN.setFont(new Font("SansSerif", Font.PLAIN, 15));

		cboMaCN = new JComboBox<String>();
		cboMaCN.addItem("Tất cả");
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
		cboTenCN.addItem("Tất cả");
		cboTenCN.setBounds(122, 142, 201, 37);
		cboTenCN.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pNhapThongTin.add(cboTenCN);
		cboTenCN.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboTenCN.setBackground(Color.WHITE);

		JLabel lblThang = new JLabel("Tháng:");
		lblThang.setBounds(10, 203, 102, 36);
		pNhapThongTin.add(lblThang);
		lblThang.setFont(new Font("SansSerif", Font.PLAIN, 15));

//		cboThang = new JComboBox<String>();
////		cboThang.setEnabled(false);
//		cboThang.addItem("Tất cả");
//		cboThang.setBounds(122, 202, 201, 37);
//		cboThang.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
//		pNhapThongTin.add(cboThang);
//		cboThang.setFont(new Font("SansSerif", Font.PLAIN, 15));
//		cboThang.setBackground(Color.WHITE);
		
		txtThang = new JTextField(dfThang.format(new Date()));
		txtThang.setBounds(122, 202, 201, 37);
		txtThang.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pNhapThongTin.add(txtThang);
		txtThang.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtThang.setBackground(Color.WHITE);
		txtThang.setEnabled(false);

		lblNhapThongTin = new JLabel("Lương công nhân");
		lblNhapThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhapThongTin.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNhapThongTin.setBounds(10, 11, 292, 29);
		pNhapThongTin.add(lblNhapThongTin);

		JLabel lblNam = new JLabel("Năm:");
		lblNam.setBounds(10, 263, 84, 36);
		pNhapThongTin.add(lblNam);
		lblNam.setFont(new Font("SansSerif", Font.PLAIN, 15));

		
//		cboNam = new JComboBox<String>();
//		cboNam.setEnabled(false);
//		cboNam.addItem("2022");
//		cboNam.setBounds(122, 262, 201, 37);
//		cboNam.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
//		pNhapThongTin.add(cboNam);
//		cboNam.setFont(new Font("SansSerif", Font.PLAIN, 15));
//		cboNam.setBackground(Color.WHITE);
		txtNam = new JTextField(dfNam.format(new Date()));
		txtNam.setBounds(122, 262, 201, 37);
		txtNam.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pNhapThongTin.add(txtNam);
		txtNam.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtNam.setBackground(Color.WHITE);
		txtNam.setEnabled(false);

//		Load cbb thang

		ArrayList<CongNhan> lsCongNhan = daoCongNhan.getDSCongNhan();
		for (CongNhan lcn : lsCongNhan) {
			cboMaCN.addItem(lcn.getMaCN());
		}

//		for (int i = 1; i<= 12; i++) {
//			cboThang.addItem(Integer.toString(i));
//		}
		


		JLabel lblSubLMH = new JLabel("Lương: ");
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

<<<<<<< HEAD
		/**
		 * Tìm kiếm
		 */
		JLabel lblTim = new JLabel("Tìm kiếm:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(350, 11, 90, 35);
		pMain.add(lblTim);

		txtTim = new JTextField();
		txtTim.setToolTipText("Thông tin tìm kiếm");
		txtTim.setText("Tìm công nhân theo mã công nhân, tên công nhân, chức vụ, cccd");
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTim.setForeground(Colors.LightGray);
		txtTim.setBorder(new LineBorder(new Color(114, 23, 153), 2, true));
		txtTim.setBounds(425, 11, 670, 33);
		txtTim.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTim.getText().equals("Tìm công nhân theo mã công nhân, tên công nhân, chức vụ, cccd")) {
					txtTim.setFont(new Font("SansSerif", Font.PLAIN, 15));
					txtTim.setForeground(Color.BLACK);
					txtTim.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTim.getText().equals("")) {
					txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
					txtTim.setForeground(Colors.LightGray);
					txtTim.setText("Tìm công nhân theo mã công nhân, tên công nhân, chức vụ, cccd");
				}
			}
		});
		pMain.add(txtTim);

		btnTim = new FixButton("Tìm");
		btnTim.setToolTipText("Tìm kiếm");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTim.setBackground(new Color(114, 23, 153));
		btnTim.setBounds(1101, 12, 159, 33);
		Icon iconTim = IconFontSwing.buildIcon(FontAwesome.SEARCH, 20, Color.white);
		btnTim.setIcon(iconTim);
		pMain.add(btnTim);
=======
		JLabel lbltitle = new JLabel("BẢNG LƯƠNG CÔNG NHÂN");
		lbltitle.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lbltitle.setBounds(550, 30, 450, 56);
		lbltitle.setForeground(new Color(164, 44, 167));
		pMain.add(lbltitle);
//		/**
//		 * Tìm kiếm
//		 */
//		JLabel lblTim = new JLabel("Tìm kiếm:");
//		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
//		lblTim.setBounds(350, 11, 90, 35);
//		pMain.add(lblTim);
//
//		txtTim = new JTextField();
//		txtTim.setToolTipText("Thông tin tìm kiếm");
//		txtTim.setText("Tìm công nhân theo mã công nhân, tên công nhân, chức vụ, cccd");
//		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
//		txtTim.setForeground(Colors.LightGray);
//		txtTim.setBorder(new LineBorder(new Color(114, 23, 153), 2, true));
//		txtTim.setBounds(425, 11, 670, 33);
//		txtTim.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusGained(FocusEvent e) {
//				if (txtTim.getText().equals("Tìm công nhân theo mã công nhân, tên công nhân, chức vụ, cccd")) {
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
//					txtTim.setText("Tìm công nhân theo mã công nhân, tên công nhân, chức vụ, cccd");
//				}
//			}
//		});
//		pMain.add(txtTim);
//
//		btnTim = new FixButton("Tìm");
//		btnTim.setToolTipText("Tìm kiếm");
//		btnTim.setForeground(Color.WHITE);
//		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
//		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
//		btnTim.setBackground(new Color(114, 23, 153));
//		btnTim.setBounds(1101, 12, 159, 33);
//		Icon iconTim = IconFontSwing.buildIcon(FontAwesome.SEARCH, 20, Color.white);
//		btnTim.setIcon(iconTim);
//		pMain.add(btnTim);
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61

		/**
		 * Nút xuất file Excel JButton btnExcel Icon iconExcel
		 */
		btnExcels = new FixButton("Xuất Excel");
		btnExcels.setForeground(Color.WHITE);
		btnExcels.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnExcels.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnExcels.setBackground(new Color(16, 124, 65));
		btnExcels.setBounds(10, 460, 313, 42);
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
		btnTinhLuong.setBounds(10, 400, 313, 42);
		Icon iconThemMH = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, Color.white);
		btnTinhLuong.setIcon(iconThemMH);
		pNhapThongTin.add(btnTinhLuong);

		btnReset = new FixButton("Mặc định");
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnReset.setBackground(new Color(114, 43, 153));
		btnReset.setBounds(10, 520, 313, 44);
		Icon iconReset = IconFontSwing.buildIcon(FontAwesome.REFRESH, 20, Color.white);
		btnReset.setIcon(iconReset);
		btnReset.setToolTipText("Làm mới toàn bộ chương trình");
		pNhapThongTin.add(btnReset);

<<<<<<< HEAD
		/**
		 * Panel sắp xếp
		 */
		JPanel pSapXep = new JPanel();
		pSapXep.setToolTipText("Sắp xếp dữ liệu");
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23, 153), 1, true), "Sắp xếp",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBackground(new Color(171, 192, 238));
		pSapXep.setBounds(350, 49, 909, 47);
		pMain.add(pSapXep);
		pSapXep.setLayout(null);

		cboSapXep = new JComboBox<Object>(new Object[] { "Tăng dần", "Giảm dần" });
		cboSapXep.setBounds(25, 12, 120, 28);
		cboSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboSapXep.setBackground(Color.WHITE);
		cboSapXep.setToolTipText("Sắp xếp theo kiểu tăng dần/ giảm dần");
		pSapXep.add(cboSapXep);

		rdoTheoTenCN = new JRadioButton("Theo tên CN");
		rdoTheoTenCN.setBounds(200, 13, 150, 27);
		rdoTheoTenCN.setSelected(true);
		rdoTheoTenCN.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoTenCN.setBackground(new Color(171, 192, 238));
		pSapXep.add(rdoTheoTenCN);

		rdoTheoSoNgayCong = new JRadioButton("Theo số ngày công");
		rdoTheoSoNgayCong.setBounds(350, 13, 175, 27);
		rdoTheoSoNgayCong.setSelected(true);
		rdoTheoSoNgayCong.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoSoNgayCong.setBackground(new Color(171, 192, 238));
		pSapXep.add(rdoTheoSoNgayCong);

		rdoTheoChucVu = new JRadioButton("Theo chức vụ");
		rdoTheoChucVu.setBounds(560, 13, 150, 27);
		rdoTheoChucVu.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoChucVu.setBackground(new Color(171, 192, 238));
		pSapXep.add(rdoTheoChucVu);

		rdoTheoLuongCN = new JRadioButton("Theo lương ");
		rdoTheoLuongCN.setBounds(735, 13, 135, 27);
		rdoTheoLuongCN.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoLuongCN.setBackground(new Color(171, 192, 238));
		pSapXep.add(rdoTheoLuongCN);

		/**
		 * Buttons Group
		 */
		bgRdo = new ButtonGroup();
		bgRdo.add(rdoTheoTenCN);
		bgRdo.add(rdoTheoChucVu);
		bgRdo.add(rdoTheoSoNgayCong);
		bgRdo.add(rdoTheoLuongCN);
		bgRdo.clearSelection();
=======
//		/**
//		 * Panel sắp xếp
//		 */
//		JPanel pSapXep = new JPanel();
//		pSapXep.setToolTipText("Sắp xếp dữ liệu");
//		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23, 153), 1, true), "Sắp xếp",
//				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
//		pSapXep.setBackground(new Color(171, 192, 238));
//		pSapXep.setBounds(350, 49, 909, 47);
//		pMain.add(pSapXep);
//		pSapXep.setLayout(null);
//
//		cboSapXep = new JComboBox<Object>(new Object[] { "Tăng dần", "Giảm dần" });
//		cboSapXep.setBounds(25, 12, 120, 28);
//		cboSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
//		cboSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
//		cboSapXep.setBackground(Color.WHITE);
//		cboSapXep.setToolTipText("Sắp xếp theo kiểu tăng dần/ giảm dần");
//		pSapXep.add(cboSapXep);
//
//		rdoTheoTenCN = new JRadioButton("Theo tên CN");
//		rdoTheoTenCN.setBounds(200, 13, 150, 27);
//		rdoTheoTenCN.setSelected(true);
//		rdoTheoTenCN.setFont(new Font("SansSerif", Font.BOLD, 14));
//		rdoTheoTenCN.setBackground(new Color(171, 192, 238));
//		pSapXep.add(rdoTheoTenCN);
//
//		rdoTheoSoNgayCong = new JRadioButton("Theo số ngày công");
//		rdoTheoSoNgayCong.setBounds(350, 13, 175, 27);
//		rdoTheoSoNgayCong.setSelected(true);
//		rdoTheoSoNgayCong.setFont(new Font("SansSerif", Font.BOLD, 14));
//		rdoTheoSoNgayCong.setBackground(new Color(171, 192, 238));
//		pSapXep.add(rdoTheoSoNgayCong);
//
//		rdoTheoChucVu = new JRadioButton("Theo chức vụ");
//		rdoTheoChucVu.setBounds(560, 13, 150, 27);
//		rdoTheoChucVu.setFont(new Font("SansSerif", Font.BOLD, 14));
//		rdoTheoChucVu.setBackground(new Color(171, 192, 238));
//		pSapXep.add(rdoTheoChucVu);
//
//		rdoTheoLuongCN = new JRadioButton("Theo lương ");
//		rdoTheoLuongCN.setBounds(735, 13, 135, 27);
//		rdoTheoLuongCN.setFont(new Font("SansSerif", Font.BOLD, 14));
//		rdoTheoLuongCN.setBackground(new Color(171, 192, 238));
//		pSapXep.add(rdoTheoLuongCN);
//
//		/**
//		 * Buttons Group
//		 */
//		bgRdo = new ButtonGroup();
//		bgRdo.add(rdoTheoTenCN);
//		bgRdo.add(rdoTheoChucVu);
//		bgRdo.add(rdoTheoSoNgayCong);
//		bgRdo.add(rdoTheoLuongCN);
//		bgRdo.clearSelection();
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61

		/**
		 * Bảng chính
		 */
<<<<<<< HEAD
		String cn[] = { "Mã CN", "Tên CN", "CCCD", "Tổ", "SoSPSX", "Lương" };
=======
		String cn[] = { "Mã CN", "Tên CN", "CCCD", "Tổ", "SoSPSX","Tháng", "Lương" };
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
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
		tblLuong.setToolTipText("Bảng mặt hàng");

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
<<<<<<< HEAD
		tblLuong.getColumnModel().getColumn(5).setPreferredWidth(200);
=======
		tblLuong.getColumnModel().getColumn(5).setPreferredWidth(80);
		tblLuong.getColumnModel().getColumn(6).setPreferredWidth(200);
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61

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
<<<<<<< HEAD
=======
		tblLuong.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
		spMatHang.setViewportView(tblLuong);

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
//		phieuLuongNV = daoPhieuLuongNV.getPhieuLuongNV(getName())
//		for(LoaiMatHang lmh : loaiMH) {
//			cboLoaiMH.addItem(lmh.getTenLoaiMatHang());
//		}
		/**
		 * Sự kiện buttons, chuột, key
		 */
<<<<<<< HEAD
		btnTim.addActionListener(this);
=======
//		btnTim.addActionListener(this);
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
		btnReset.addActionListener(this);
		btnExcels.addActionListener(this);
		btnTinhLuong.addActionListener(this);
		tblLuong.addMouseListener(this);

<<<<<<< HEAD
		rdoTheoLuongCN.addActionListener(this);
		rdoTheoChucVu.addActionListener(this);
		rdoTheoTenCN.addActionListener(this);
		rdoTheoSoNgayCong.addActionListener(this);
		cboSapXep.addActionListener(this);
		cboMaCN.addItemListener(this);

		txtTim.addKeyListener(this);
		btnReset.addKeyListener(this);
		btnTim.addKeyListener(this);
		txtTim.addKeyListener(this);
=======
//		rdoTheoLuongCN.addActionListener(this);
//		rdoTheoChucVu.addActionListener(this);
//		rdoTheoTenCN.addActionListener(this);
//		rdoTheoSoNgayCong.addActionListener(this);
//		cboSapXep.addActionListener(this);
		cboMaCN.addItemListener(this);

//		txtTim.addKeyListener(this);
		btnReset.addKeyListener(this);
//		btnTim.addKeyListener(this);
//		txtTim.addKeyListener(this);
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61

		/**
		 * Load dữ liệu lên bảng
		 */
		loadTable();
//		loadTableMH();
	}

	/**
	 * Lấy dữ liệu từ SQL Server nạp vào bảng thông qua vòng lặp for, không nạp vào
	 * bảng với loại mặt hàng ngừng kinh doanh
	 */





	/**
	 * Xóa toàn bộ bảng
	 */
	public void clearTable() {
		while (tblLuong.getRowCount() > 0) {
			modelLuong.removeRow(0);
		}
	}

	public void tinhLuong() {
		String maCN = cboMaCN.getSelectedItem().toString();
<<<<<<< HEAD
		int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
		int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
		if (maCN.equals("Tất cả") && cboThang.getSelectedItem().toString() == "Tất cả") {
			ArrayList<CongNhan> lstCN = daoCongNhan. getDSCongNhan();
			for (CongNhan congNhan : lstCN) {
				int x = daoCCCN.getSoSPSX(congNhan.getMaCN());
				float luong = daoCCCN.getTienLuongCN(congNhan.getMaCN());
				
=======

		if (maCN.equals("Tất cả") ) {
			ArrayList<CongNhan> lstCN = daoCongNhan.getDSCongNhan();
			for (CongNhan congNhan : lstCN) {
				int x = daoCCCN.getSoSPSX(congNhan.getMaCN());
				float luong = daoCCCN.getTienLuongCN(congNhan.getMaCN());
				int count = daoPhieuLuongCN.getCountLuongCN(congNhan.getMaCN(), dfDate1.format(new Date()));
				if(count == 0) {
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
					try {
						
							daoPhieuLuongCN.themPhieuLuongCN(new PhieuLuongCN(congNhan, new Date(), x, luong));
					} catch (SQLException e) {
						e.printStackTrace();
					}
<<<<<<< HEAD
				
				
			}
		} else {
			ArrayList<CongNhan> lstCN = daoCongNhan. getDSCongNhan();
			for (CongNhan congNhan : lstCN) {
				int x = daoCCCN.getSoSPSX(congNhan.getMaCN(),thang, nam);
				float luong = daoCCCN.getTienLuongCN(congNhan.getMaCN(), thang, nam);
=======
				} 
				
			}
		} else {
			ArrayList<CongNhan> lstCN = daoCongNhan.getDSCongNhanFromMa(maCN);
			for (CongNhan congNhan : lstCN) {
				int x = daoCCCN.getSoSPSX(congNhan.getMaCN());
				float luong = daoCCCN.getTienLuongCN(congNhan.getMaCN());
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
				
					try {
						
							daoPhieuLuongCN.themPhieuLuongCN(new PhieuLuongCN(congNhan, new Date(), x, luong));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				
				
			}
		}
	}
	public void loadTableLuong() {
		String maCN = cboMaCN.getSelectedItem().toString();
<<<<<<< HEAD
		int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
//		String thang = cboThang.getSelectedItem().toString();
//		String nam = cboNam.getSelectedItem().toString();;
		if (maCN.equals("Tất cả") && cboThang.getSelectedItem().toString() == "Tất cả") {
			clearTable();
			ArrayList<PhieuLuongCN> lsPLCN = daoPhieuLuongCN.getAllPhieuLuongCN();
			for (PhieuLuongCN phieuLuongCN : lsPLCN) {
				modelLuong.addRow(new Object[] { phieuLuongCN.getMaCN().getMaCN(), phieuLuongCN.getMaCN().getTenCN(),
						phieuLuongCN.getMaCN().getCccd(), phieuLuongCN.getMaCN().getToSanXuat().getTenTo(),
						phieuLuongCN.getSoSPSX(), dfLuong.format(Math.round(phieuLuongCN.getTienLuong())) });
			}
			btnTinhLuong.setEnabled(false);
		} else {
			PhieuLuongCN phieuLuongCN = daoPhieuLuongCN.getPhieuLuongCN(maCN);
			modelLuong.addRow(new Object[] { phieuLuongCN.getMaCN().getMaCN(), phieuLuongCN.getMaCN().getTenCN(),
					phieuLuongCN.getMaCN().getCccd(), phieuLuongCN.getMaCN().getToSanXuat().getTenTo(),
					phieuLuongCN.getSoSPSX(), dfLuong.format(Math.round(phieuLuongCN.getTienLuong())) });
		}
=======
//		int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
//		String thang = cboThang.getSelectedItem().toString();
//		String nam = cboNam.getSelectedItem().toString();;
		if (maCN.equals("Tất cả")) {
			loadTable();
			btnTinhLuong.setEnabled(false);
		} else {
			clearTable();
			loadTableTheoMa();}
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61

	}
	
	public void loadTable() {
//		String thang = cboThang.getSelectedItem().toString();
//		String nam = cboNam.getSelectedItem().toString();
//		daoPhieuLuongNV.updateLuongNV();	
		clearTable();
		ArrayList<PhieuLuongCN> lsPLCN = daoPhieuLuongCN.getAllPhieuLuongCN();
		for (PhieuLuongCN phieuLuongCN : lsPLCN) {
			modelLuong.addRow(new Object[] { phieuLuongCN.getMaCN().getMaCN(), phieuLuongCN.getMaCN().getTenCN(),
<<<<<<< HEAD
					phieuLuongCN.getMaCN().getCccd(), phieuLuongCN.getMaCN().getToSanXuat().getTenTo(),
					phieuLuongCN.getSoSPSX(), dfLuong.format(Math.round(phieuLuongCN.getTienLuong())) });
=======
					phieuLuongCN.getMaCN().getCccd(), phieuLuongCN.getMaCN().getToSanXuat().getMaTo(),
					phieuLuongCN.getSoSPSX(), dfThang.format(phieuLuongCN.getThang()), dfLuong.format(Math.round(phieuLuongCN.getTienLuong())) });
		}
	}
	public void loadTableTheoMa() {
		String maCN = cboMaCN.getSelectedItem().toString();
//		String thang = cboThang.getSelectedItem().toString();
//		String nam = cboNam.getSelectedItem().toString();
//		daoPhieuLuongNV.updateLuongNV();	
		ArrayList<PhieuLuongCN> lsPLCN = daoPhieuLuongCN.getAllPhieuLuongCN(maCN);
		for (PhieuLuongCN phieuLuongCN : lsPLCN) {
			modelLuong.addRow(new Object[] { phieuLuongCN.getMaCN().getMaCN(), phieuLuongCN.getMaCN().getTenCN(),
					phieuLuongCN.getMaCN().getCccd(), phieuLuongCN.getMaCN().getToSanXuat().getMaTo(),
					phieuLuongCN.getSoSPSX(), dfThang.format(phieuLuongCN.getThang()),dfLuong.format(Math.round(phieuLuongCN.getTienLuong())) });
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
		}
	}
	public boolean checkPhieuLuongCN() {
		String maCN = cboMaCN.getSelectedItem().toString();
<<<<<<< HEAD
		int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
		if (maCN.equals("Tất cả") && cboThang.getSelectedItem().toString() == "Tất cả") {
			if (daoPhieuLuongCN.getCountLuongCN(thang) > tblLuong.getRowCount())
				return true;
		} else {
			if (daoPhieuLuongCN.getCountLuongCN(thang) > 0)
=======
		if (maCN.equals("Tất cả") ) {
			if (daoPhieuLuongCN.getCountLuongCN(dfDate1.format(new Date())) > tblLuong.getRowCount())
				return true;
		} else {
			if (daoPhieuLuongCN.getCountLuongCN(maCN,dfDate1.format(new Date())) > 0)
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
				return true;
		}
		return false;

	}
	public void detelePhieuChamCong() {
		String maCN = cboMaCN.getSelectedItem().toString();
		if (maCN.equals("Tất cả")) {
			try {
				daoCCCN.xoaPhieuChamCongNV();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			try {
				daoCCCN.xoaPhieuChamCongCN(maCN);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
<<<<<<< HEAD
=======
		loadTable();
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
//		loadTableMH();
	}

	/**
	 * Sự kiện chính
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	
		Object o = e.getSource();
		if (o.equals(btnReset)) {
			resetAll();
		}
//		if (o.equals(btnTim)) {
//			tim();
//		}
		if (o.equals(btnTinhLuong)) {
			
			if (!checkPhieuLuongCN()) {
				tinhLuong();
<<<<<<< HEAD
				loadTableLuong();}
				detelePhieuChamCong();

//			} else {
				JOptionPane.showMessageDialog(null, "Đã tính lương");
			}}
=======
				loadTableLuong();
				detelePhieuChamCong();

			} else {
				JOptionPane.showMessageDialog(null, "Đã tính lương");
			}
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
//		if (cboSapXep.getSelectedItem() == "Tăng dần") {
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
//		if (cboSapXep.getSelectedItem() == "Giảm dần") {
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
<<<<<<< HEAD
//		if (o.equals(btnExcels)) {
//			try {
//				xuatExcel();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
=======
		if (o.equals(btnExcels)) {
			try {
				xuatExcel();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}}
	}
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
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
//					modelLuong.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(), dfVND.format(Math.round(mh.getGiaMatHang())) } );
//					}
//				}
//			} 
//	}
	/**
	 * Xóa mặt hàng khỏi table, update mặt hàng thành ngừng kinh doanh trên SQL
	 * Server
	 */
//	public void updateMHNgungKD() {
//		if (tblLuong.getSelectedRow() == -1) {
//			JOptionPane.showMessageDialog(this, "Vui lòng chọn mặt hàng cần xóa");
//		}else {
//			int r = tblLuong.getSelectedRow();
//			if(r>0) {
//				int del = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa? ", "Thông báo", JOptionPane.YES_NO_OPTION);
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
	 * Sửa thông tin mặt hàng
	 */
//	public void SuaMH() {
//		int row = tblLuong.getSelectedRow();
//		if(row >=0) {
//			int update = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa thông tin mặt hàng  này không?", "Thông báo",
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
//			modelLuong.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
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
//				modelLuong.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
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
//			modelLuong.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), dfVND.format(Math.round(infoMH.getGiaMatHang())) } );
//			}
//		}
//	}
	/**
	 * Sắp xếp hệ số lương công nhân tăng dần
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
	 * Sắp xếp giá mặt hàng giảm dần
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
	 * Tìm mặt hàng theo tên mặt hàng, loại mặt hàng
	 */
	@SuppressWarnings("unlikely-arg-type")
//	public void tim() {
//		ArrayList<PhieuLuongNV> lstLuongNV = null;
//		String input = txtTim.getText().trim();
//		String regexMaNV = "^(NV[0-9]{3})$";
//		if (!txtTim.equals("")) {
//			if (input.matches(regexMaNV)) {
//				lstLuongNV = daoPhieuLuongNV.getPhieuLuongNVTheoMa(input);
//				loadPhieuLuongTheoMa(lstLuongNV);
//			}
//
//			else if (regex.regexTen(txtTim)) {
//				lstLuongNV = daoPhieuLuongNV.getPhieuLuongNVTheoTen(input);
//				loadPhieuLuongTheoTen(lstLuongNV);
//			} else if (regex.regexCCCD(txtTim)) {
//				lstLuongNV = daoPhieuLuongNV.getPhieuLuongNVTheoCCCD(input);
//				loadPhieuLuongTheoCCCD(lstLuongNV);
//			} else if (lstLuongNV.size() == 0)
//				JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không hợp lệ!\n", "Thông báo",
//						JOptionPane.ERROR_MESSAGE);
//		} else {
//			clearTable();
//			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm!", "Thông báo",
//					JOptionPane.WARNING_MESSAGE);
//		}
//	}

//	public void loadPhieuLuongTheoTen(ArrayList<PhieuLuongNV> pl1) {
//
//		loadTable(pl1);
//	}
//
//	public void loadPhieuLuongTheoMa(ArrayList<PhieuLuongNV> pl1) {
//		loadTable(pl1);
//	}
//
//	public void loadPhieuLuongTheoCCCD(ArrayList<PhieuLuongNV> pl1) {
//		loadTable(pl1);
//	}
	/**
	 * Nạp dữ liệu tên mặt hàng vào bảng
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
	 * Nạp dữ liệu loại mặt hàng vào bảng
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
	 * Xuất Excels
	 */
	private void xuatExcel() throws IOException {
		XuatExcels xuat = new XuatExcels();
		FileDialog fileDialog = new FileDialog(this, "Xuất thông tin lương công nhân ra Excels", FileDialog.SAVE);
		fileDialog.setFile("Danh sách lương công nhân");
		fileDialog.setVisible(true);
		String name = fileDialog.getFile();
		String fileName = fileDialog.getDirectory() + name;

		if (name == null)
			return;

		if (!fileName.endsWith(".xlsx") || !fileName.endsWith(".xls"))
			fileName += ".xlsx";

<<<<<<< HEAD
		xuat.xuatTable(tblLuong, "DANH SÁCH THÔNG LƯƠNG NHÂN VIÊN", fileName);
=======
		xuat.xuatTable(tblLuong, "DANH SÁCH THÔNG LƯƠNG CÔNG NHÂN", fileName);
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
	}

	/**
	 * ResetAll giúp làm mới lại form lương công nhân
	 */
	public void resetAll() {

		cboMaCN.setSelectedIndex(0);
		cboTenCN.setSelectedIndex(0);

		txtLuong.setText("");

	}

	/**
	 * Sự kiện click chuột
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblLuong)) {
			int row = tblLuong.getSelectedRow();
			cboMaCN.setSelectedItem(modelLuong.getValueAt(row, 0).toString());
			cboTenCN.setSelectedItem(modelLuong.getValueAt(row, 1).toString());
<<<<<<< HEAD
//			cboThang.setSelectedItem(modelLuong.getValueAt(row, 5).toString());
//			cboNam.setSelectedItem(modelLuong.getValueAt(row, 6).toString());
			txtLuong.setText(modelLuong.getValueAt(row, 5).toString());
=======
			txtThang.setText(modelLuong.getValueAt(row, 5).toString());
//			cboThang.setSelectedItem(modelLuong.getValueAt(row, 5).toString());
//			cboNam.setSelectedItem(modelLuong.getValueAt(row, 6).toString());
			txtLuong.setText(modelLuong.getValueAt(row, 6).toString());
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61

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
		if (o == cboMaCN.getSelectedItem()) {
			/*
			 * Nếu cbb công nhân thay đổi, thì cbb tên công nhân sẽ được hiển thị danh sách
			 * lên
			 */
			String maCN = (String) cboMaCN.getSelectedItem();
			ArrayList<CongNhan> lsCN = daoCongNhan.getDSCongNhanFromMa(maCN);
			cboTenCN.removeAllItems();
<<<<<<< HEAD
			if (cboMaCN.getSelectedIndex() == 0)
				cboTenCN.addItem("Tất cả");
			for (CongNhan cn : lsCN) {
				cboTenCN.addItem(cn.getTenCN());
=======
			if (cboMaCN.getSelectedIndex() == 0) {
				cboTenCN.addItem("Tất cả");
			loadTable();}
			for (CongNhan cn : lsCN) {
				cboTenCN.addItem(cn.getTenCN());
				clearTable();
				loadTableTheoMa();
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
			}
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
