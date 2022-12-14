package app;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.Window;
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
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import dao.DAOSanPham;
import dao.DAOPhatSinhMa;
import dao.DAOCongDoan;
import dao.DAOToSanXuat;
import dao.DAOCT_CD_SX_SP;
import dao.Regex;
import entity.SanPham;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;


public class FrmQLSanPham extends JFrame implements ActionListener, MouseListener, ItemListener,KeyListener  {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String sHeaderMaNV;
	@SuppressWarnings("unused")
	private String sHeaderTenNV;
	private Panel pMain;
	@SuppressWarnings("unused")
	private Date dNgayHienTai;
	private JTextField txtTK;
	private JButton btnTim;
	private JButton btnThemSP;
	private JButton btnXoaP;
	private JButton btnSuaSP;
	private JButton btnReset;
	private JButton btnExcels;
	private JComboBox<String> cboSapXep;
	private Regex regex;
	private JPanel pNhapThongTin;
	private JLabel lblNhapThongTin;
	private ButtonGroup bgRdo;
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtGiaSX;
	private JTextField txtSoLuong;
	private DefaultTableModel modelSanPham;
	private JTable tblSanPham;
	private DAOPhatSinhMa daoMa;
	private JRadioButton rdoTheoMaSP;
	private JRadioButton rdoTheoGiaSX;
	private JRadioButton rdoTheoSoLuong;
	private DAOSanPham daoSP;
	private DAOCongDoan daoCD;
	private DAOToSanXuat daoTo;
	private SanPham sp;
	private DecimalFormat dfGiaSX;
	private DecimalFormat dftxtGiaSX;
	private DAOCT_CD_SX_SP daoCT;

	public Panel getFrmQLSanPham() {
		return this.pMain;
	}
	public FrmQLSanPham(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {

		//Khai bao dao
		daoSP = new DAOSanPham();
		daoTo = new DAOToSanXuat();
		daoCD = new DAOCongDoan();
		daoCT = new DAOCT_CD_SX_SP();
		daoMa = new DAOPhatSinhMa();
		regex = new Regex();
		//dinh dang
		dfGiaSX=new DecimalFormat("###,###");
//		dftxtGiaSX=new DecimalFormat("######");
		
		try {
			ConnectDB.getinstance().connect();;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//giao dien
		getContentPane().setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 629);
		getContentPane().add(pMain);
		pMain.setLayout(null);

		pNhapThongTin = new JPanel();
		pNhapThongTin.setBorder(new LineBorder(new Color(114, 23, 153)));
		pNhapThongTin.setBackground(Color.WHITE);
		pNhapThongTin.setBounds(10, 11, 333, 607);
		pMain.add(pNhapThongTin);
		pNhapThongTin.setLayout(null);

		lblNhapThongTin = new JLabel("Nh???p th??ng s???n ph???m");
		lblNhapThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhapThongTin.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNhapThongTin.setBounds(10, 11, 313, 29);
		pNhapThongTin.add(lblNhapThongTin);

		//Ten san pham
		JLabel lblTenSP = new JLabel("T??n SP:");
		lblTenSP.setBounds(10, 75, 125, 26);
		pNhapThongTin.add(lblTenSP);
		lblTenSP.setFont(new Font("SansSerif", Font.PLAIN, 15));

		txtTenSP = new JTextField();
		txtTenSP.setBounds(132, 75, 191, 35);
		pNhapThongTin.add(txtTenSP);
		txtTenSP.setBackground(new Color(255, 255, 255));
		txtTenSP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenSP.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtTenSP.setColumns(20);
		
		//Gia san xuat
		JLabel lblGiaSX = new JLabel("Gi?? SX:");
		lblGiaSX.setBounds(10, 125, 111, 26);
		pNhapThongTin.add(lblGiaSX);
		lblGiaSX.setFont(new Font("SansSerif", Font.PLAIN, 15));

		txtGiaSX = new JTextField();
		txtGiaSX.setBounds(132, 125, 191, 35);
		pNhapThongTin.add(txtGiaSX);
		txtGiaSX.setBackground(new Color(255, 255, 255));
		txtGiaSX.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtGiaSX.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtGiaSX.setColumns(20);
		
		//So luong 
		JLabel lblSoLuong = new JLabel("S??? l?????ng SP:");
		lblSoLuong.setBounds(10, 175, 102, 26);
		pNhapThongTin.add(lblSoLuong);
		lblSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 15));

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(132, 175, 191, 35);
		pNhapThongTin.add(txtSoLuong);
		txtSoLuong.setBackground(new Color(255, 255, 255));
		txtSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtSoLuong.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtSoLuong.setColumns(20);
		
		
		// lblTim
		JLabel lblTim = new JLabel("T??m ki???m:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(350, 11, 90, 35);
		pMain.add(lblTim);

		// txtTK
		txtTK = new JTextField();
		txtTK.setText("T??m theo m?? s???n ph???m");
		txtTK.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTK.setForeground(Colors.LightGray);
		txtTK.setBorder(new LineBorder(new Color(114, 23, 153), 2, true));
		txtTK.setBounds(425, 11, 529, 33);
		txtTK.addFocusListener(new FocusAdapter() { // place holder
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTK.getText().equals("T??m theo m?? s???n ph???m")) {
					txtTK.setText("");
					txtTK.setFont(new Font("SansSerif", Font.PLAIN, 15));
					txtTK.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTK.getText().equals("")) {
					txtTK.setFont(new Font("SansSerif", Font.ITALIC, 15));
					txtTK.setText("T??m theo m?? s???n ph???m");
					txtTK.setForeground(Colors.LightGray);
				}
			}
		});
		pMain.add(txtTK);

		// btnTim
		btnTim = new FixButton("T??m");
		btnTim.setForeground(Color.WHITE);

		btnTim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTim.setBackground(new Color(114, 23, 153));
		btnTim.setBounds(964, 12, 127, 33);
		
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
		

		// n??t th??m
		btnThemSP = new FixButton("Th??m");
		btnThemSP.setForeground(Color.WHITE);

		btnThemSP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThemSP.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThemSP.setBackground(new Color(57, 210, 247));
		btnThemSP.setBounds(10, 400, 313, 43);
		Icon iconThemP = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, Color.white);
		btnThemSP.setIcon(iconThemP);
		pNhapThongTin.add(btnThemSP);
		
		//n??t s???a
		btnSuaSP = new FixButton("S???a");
		btnSuaSP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSuaSP.setForeground(Color.WHITE);
		btnSuaSP.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSuaSP.setBackground(new Color(133, 217, 191));
		btnSuaSP.setBounds(10, 450, 313, 43);
		Icon iconSuaP = IconFontSwing.buildIcon(FontAwesome.WRENCH, 20, Color.white);
		btnSuaSP.setIcon(iconSuaP);
		pNhapThongTin.add(btnSuaSP);
		
		//n??t x??a 
		btnXoaP = new FixButton("X??a");
		btnXoaP.setForeground(Color.WHITE);
		btnXoaP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoaP.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnXoaP.setBackground(new Color(0xE91940));
		btnXoaP.setBounds(10, 500, 313, 43);
		Icon iconHuyP = IconFontSwing.buildIcon(FontAwesome.TIMES_CIRCLE, 20, Color.white);
		btnXoaP.setIcon(iconHuyP);
		pNhapThongTin.add(btnXoaP);
		
		//n??t l??m m???i
		btnReset = new FixButton("L??m m???i");
		btnReset.setForeground(Color.WHITE);
		btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnReset.setBackground(new Color(114, 23, 153));
		btnReset.setBounds(10, 550, 313, 43);
		Icon iconReset = IconFontSwing.buildIcon(FontAwesome.REFRESH, 20, Color.white);
		btnReset.setIcon(iconReset);
		pNhapThongTin.add(btnReset);
		//SapXep
		JPanel pSapXep = new JPanel();
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "S???p x???p", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBackground(new Color(171, 192, 238));
		pSapXep.setBounds(353, 51, 904, 47);
		pMain.add(pSapXep);
		pSapXep.setLayout(null);
		
		//S???p x???p t??ng d???n ho???c gi???m d???n
		cboSapXep = new JComboBox<String>();
		cboSapXep.setBounds(51, 12, 115, 28);
		cboSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboSapXep.setBackground(Color.WHITE);
		String cbSort[] = { "T??ng d???n", "Gi???m d???n" };
		for (int i = 0; i < cbSort.length; i++) {
			cboSapXep.addItem(cbSort[i]);
		}
		pSapXep.add(cboSapXep);

		//Group rdo gi??p s???p x???p s???n ph???m
		rdoTheoMaSP = new JRadioButton("Theo M?? S???n Ph???m");
		rdoTheoMaSP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdoTheoMaSP.setBounds(312, 15, 170, 27);
		rdoTheoMaSP.setSelected(true);
		rdoTheoMaSP.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoMaSP.setBackground(new Color(171, 192, 238));
		pSapXep.add(rdoTheoMaSP);

		rdoTheoGiaSX = new JRadioButton("Theo Gi?? S???n Xu???t");
		rdoTheoGiaSX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdoTheoGiaSX.setBounds(518, 15, 170, 27);
		rdoTheoGiaSX.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoGiaSX.setBackground(new Color(171, 192, 238));
		pSapXep.add(rdoTheoGiaSX);

		rdoTheoSoLuong = new JRadioButton("Theo S??? L?????ng");
		rdoTheoSoLuong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdoTheoSoLuong.setBounds(718, 15, 135, 27);
		rdoTheoSoLuong.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoSoLuong.setBackground(new Color(171, 192, 238));
		pSapXep.add(rdoTheoSoLuong);

		bgRdo=new ButtonGroup();
		bgRdo.add(rdoTheoMaSP);
		bgRdo.add(rdoTheoGiaSX);
		bgRdo.add(rdoTheoSoLuong);
		bgRdo.clearSelection();
	
		//t???o b???ng
		//t??n c??c c???t trong b???ng
		String sp [] = {"M?? SP","T??n SP", "Gi?? S???n Xu???t", "S??? L?????ng"};
		modelSanPham = new DefaultTableModel(sp,0);

		tblSanPham = new JTable(modelSanPham);
		tblSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblSanPham.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblSanPham.setShowHorizontalLines(true);
		tblSanPham.setShowGrid(true);
		tblSanPham.setBackground(Color.WHITE);
		tblSanPham.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblSanPham.setSelectionBackground(new Color(164, 44, 167, 30));
		tblSanPham.setSelectionForeground(new Color(114, 23, 153));
		tblSanPham.setRowHeight(30);
		
		JTableHeader tbHeader = tblSanPham.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		//thanh cu???n l??n xu???ng
		JScrollPane spSanPham = new JScrollPane(tblSanPham, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spSanPham.setBounds(353, 104, 904, 514);
		spSanPham.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		spSanPham.setBackground(new Color(164, 44, 167));
		spSanPham.getHorizontalScrollBar();
		pMain.add(spSanPham);
		
		//ch???nh ????? d??i t???ng c???t
		tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(240);
		tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(240);
		tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(240);
		tblSanPham.getColumnModel().getColumn(3).setPreferredWidth(240);
		
		//Ch??? canh l??? tr??i, s??? canh l??? ph???i
		DefaultTableCellRenderer rightRenderer=new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		tblSanPham.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
		tblSanPham.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
		tblSanPham.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		tblSanPham.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		
		///Background
		JLabel lblBackGround=new JLabel("");
		lblBackGround.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackGround.setBounds(0, 0, 1281, 629);
		Image imgBackGround = Toolkit.getDefaultToolkit().getImage("data\\img\\background.png");
		Image resizeBG = imgBackGround.getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), 0);
		lblBackGround.setIcon(new ImageIcon(resizeBG));
		pMain.add(lblBackGround);
		
		
		//add actions
		tblSanPham.addMouseListener(this);
		btnReset.addActionListener(this);
		btnThemSP.addActionListener(this);
		btnSuaSP.addActionListener(this);
		btnXoaP.addActionListener(this);
		btnTim.addActionListener(this);
		btnExcels.addActionListener(this);
		rdoTheoGiaSX.addActionListener(this);
		rdoTheoSoLuong.addActionListener(this);
		rdoTheoMaSP.addActionListener(this);
		cboSapXep.addActionListener(this);
		
		txtSoLuong.addKeyListener(this);
		txtTK.addKeyListener(this);
		
		txtTenSP.setText("??o S?? Mi");
		txtGiaSX.setText("100000");
		txtSoLuong.setText("50");
	
		loadTableSP();

	}
	// end giao dien
	
	public void loadTableSP() {
	
		clearTable(modelSanPham);
		ArrayList<SanPham> lstSP = daoSP.getDSSanPham();
		for (SanPham sP : lstSP) {
			modelSanPham.addRow(new Object[] {
					sP.getMaSP(), sP.getTenSP(), dfGiaSX.format(sP.getGiaSX()), sP.getSoLuong()
			});
		}
	}

	//Lam moi danh sach
	public void clearTable(DefaultTableModel defaultTableModel) {
		while (tblSanPham.getRowCount() > 0) {
			modelSanPham.removeRow(0);
		}
	}
	
	/**
	 * load danh s??ch s???n ph???m
	 * @param lstP: danh s??ch s???n ph???m
	 */
	private void loadDanhSachSanPham(ArrayList<SanPham> lstSP) {
		clearTable(modelSanPham);
		for (SanPham sp : lstSP) {
			modelSanPham.addRow(new Object[] {
					sp.getMaSP(), sp.getTenSP(), dfGiaSX.format(dfGiaSX.format(sp.getGiaSX())), sp.getSoLuong()
			});
		}
	}

	/**
	 * load danh s??ch 1 s???n ph???m
/	 * @param SanPham sp: th??ng tin s???n ph???m ???????c ch???n
	 */
	public void loadSPDuocChon(SanPham sp) {
		modelSanPham.addRow(new Object[] {
				sp.getMaSP(), sp.getTenSP(), dfGiaSX.format(sp.getGiaSX()), sp.getSoLuong()
		});
	}
	//Them san pham
	/**
	 * Th??m s???n ph???m v??o trong danh s??ch
	 * ki???m tra gi?? s???n xu???t ph???i >0
	 * Xu???t ra danh s??ch s???n ph???m
	 */
	public void themSanPham() {
			String maSP = daoMa.getMaSP();
			String tenSP = txtTenSP.getText().trim();
			if(tenSP.equals("") || txtGiaSX.equals("") || txtSoLuong.equals("") ) {
				JOptionPane.showMessageDialog(this, "Vui l??ng nh???p th??ng tin ?????y ?????!", "Th??ng b??o", JOptionPane.WARNING_MESSAGE);
				txtTenSP.requestFocus();
			}	else {
			if(regex.regexTenSP(txtTenSP)) {
				if(regex.regexGiaSX(txtGiaSX)) {
					if (regex.regexSoLuong(txtSoLuong)) {
						float giaSX = Float.parseFloat(txtGiaSX.getText());
						int soLuong = Integer.parseInt(txtSoLuong.getText());
						SanPham sp = new SanPham(maSP, tenSP, giaSX, soLuong);
						try {
							daoSP.ThemSP(sp);
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(this, "Th??m s???n ph???m th???t b???i!");
						}
//						clearTable(modelSanPham);;
						JOptionPane.showMessageDialog(this, "Th??m s???n ph???m th??nh c??ng!");
						modelSanPham.addRow(new Object[] {sp.getMaSP(), sp.getTenSP(), dfGiaSX.format(sp.getGiaSX()), sp.getSoLuong()} );							
					}
				}
			}
		}
	}

	//Sua thong tin san pham
	public void suaThongTin() {
		int row = tblSanPham.getSelectedRow();
		if (row >= 0) {
			int update = JOptionPane.showConfirmDialog(this, "B???n mu???n s???a th??ng tin s???n ph???m n??y kh??ng?", "Th??ng b??o",
					JOptionPane.YES_NO_OPTION);
			if (update == JOptionPane.YES_OPTION) {
				JTextField txtTam = new JTextField();
				JTextField txtTam1 = new JTextField();
				String maSP = modelSanPham.getValueAt(row, 0).toString();
				float gia = Math.round(daoSP.getGiaSPTheoMa(maSP).getGiaSX());
				txtTam.setText(String.valueOf(Math.round(gia)));
				float sl = Math.round(daoSP.getGiaSPTheoMa(maSP).getSoLuong());
				txtTam1.setText(String.valueOf(Math.round(sl)));
				if (regex.regexGiaSX(txtTam) && regex.regexSoLuong(txtTam1) ) {
					try {
						String tenSP = txtTenSP.getText().trim();
						float giaSX = Float.parseFloat(txtGiaSX.getText().toString());
						int soLuong = Integer.parseInt(txtSoLuong.getText().toString());
						SanPham sp = new SanPham(maSP, tenSP, giaSX, soLuong);
						daoSP.updateSP(sp);
						clearTable(modelSanPham);
						loadSPDuocChon(sp);
						JOptionPane.showMessageDialog(this, "Th??ng tin s???n ph???m ???? ???????c s???a!", "Th??ng b??o",
								JOptionPane.OK_OPTION);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Vui l??ng ki???m tra l???i gi?? s???n xu???t ho???c s??? l?????ng s???n ph???m!!", "Th??ng b??o",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n th??ng tin s???n ph???m s???a!", "Th??ng b??o",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	//xoa san pham 
	private boolean xoaSanPham() {
		int row = tblSanPham.getSelectedRow();
		if (row >= 0) {
			int cancel = JOptionPane.showConfirmDialog(null, "B???n mu???n x??a s???n ph???m n??y?", "Th??ng b??o",
					JOptionPane.YES_NO_OPTION);
			if (cancel == JOptionPane.YES_OPTION) {
				String maSP = tblSanPham.getValueAt(row, 0).toString();
				try {
					modelSanPham.removeRow(row);
					//daoCT.deleteCTTheoMa(maSP);
					//daoCD.deleteCDTheoMa(maSP);
					daoSP.deleteSP(maSP);
					JOptionPane.showMessageDialog(null, "???? x??a s???n ph???m!", "Th??ng b??o", JOptionPane.OK_OPTION);
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "x??a s???n ph???m th???t b???i!", "Th??ng b??o",
							JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Vui l??ng ch???n l???i");
			}
		} else {
			JOptionPane.showMessageDialog(null, "B???n ch??a ch???n th??ng tin s???n ph???m c???n h???y!", "Th??ng b??o",
					JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}

//	T??m ki???m S???n Ph???m
	private void findSanPham() {
		ArrayList<SanPham> lstSP = null;
		String regexMaSanPham = "((SP|sp)[0-9]{3})";
		if (!txtTK.getText().equals("") && !txtTK.getText().equals("T??m theo m?? s???n ph???m")) {
			if(regex.regexTimSanPham(txtTK)) {
				if (txtTK.getText().trim().matches(regexMaSanPham)) {
					lstSP = daoSP.getSanPhamTheoMaSP(txtTK.getText().trim());
					loadDanhSachSanPham(lstSP);
				}
				if(lstSP.size() == 0 ) {
					JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y th??ng tin t??m ki???m ph?? h???p!");
					loadDanhSachSanPham(lstSP);;
				}
			}
		} else {
			clearTable(modelSanPham);
			JOptionPane.showMessageDialog(this, "Vui l??ng nh???p th??ng tin t??m ki???m!", "Th??ng b??o",
					JOptionPane.WARNING_MESSAGE);
		}
	}		
	
	//L??m m???i
	public void resetAll() {
		txtTK.setText("T??m theo m?? s???n ph???m");
		txtTK.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTK.setForeground(Colors.LightGray);
		
		txtTenSP.setText("");
		txtGiaSX.setText("");
		txtSoLuong.setText("");
		loadTableSP();
	}
	//sap xep ma san pham giam dan
	private void sortMaSanPhamGiamDan(SanPham sp) {
		clearTable(modelSanPham);
		ArrayList<SanPham> lsSP = daoSP.sortTheoMaSP();
		for (SanPham sanPham : lsSP) {
			modelSanPham.addRow(new Object[] {
					sanPham.getMaSP(), sanPham.getTenSP(), dfGiaSX.format(sanPham.getGiaSX()) , sanPham.getSoLuong()
			});
		}
	}
	//sap xep gia san pham giam dan
	private void sortGiaSPGiamDan(SanPham sp)  {
		clearTable(modelSanPham);
		ArrayList<SanPham> lsSP = daoSP.sortTheoGiaSP("desc");
		for (SanPham lssp: lsSP) {
			modelSanPham.addRow(new Object[] {
					lssp.getMaSP(), lssp.getTenSP(), dfGiaSX.format(lssp.getGiaSX()), lssp.getSoLuong()
			});		
		}
	}
	//sap xep gia san pham tang dan
	private void sortGiaSPTangDan(SanPham sp)  {
		clearTable(modelSanPham);
		ArrayList<SanPham> lsSP = daoSP.sortTheoGiaSP("");
		for (SanPham lssp: lsSP) {
			modelSanPham.addRow(new Object[] {
					lssp.getMaSP(), lssp.getTenSP(), dfGiaSX.format(lssp.getGiaSX()), lssp.getSoLuong()
			});		
		}
	}
	
	//sap xep so luong giam dan
	private void sortSoLuongSPGiamDan(SanPham sp)  {
		clearTable(modelSanPham);
		ArrayList<SanPham> lsSP = daoSP.sortTheoSoLuongSP("desc");
		for (SanPham lssp: lsSP) {
			modelSanPham.addRow(new Object[] {
					lssp.getMaSP(), lssp.getTenSP(), dfGiaSX.format(lssp.getGiaSX()), lssp.getSoLuong()
			});		
		}
	}
	//sap xep so luong tang dan
	private void sortSoLuongSPTangDan(SanPham sp)  {
		clearTable(modelSanPham);
		ArrayList<SanPham> lsSP = daoSP.sortTheoSoLuongSP("");
		for (SanPham lssp: lsSP) {
			modelSanPham.addRow(new Object[] {
					lssp.getMaSP(), lssp.getTenSP(), dfGiaSX.format(lssp.getGiaSX()), lssp.getSoLuong()
			});		
		}
	}
	private void xuatExcel() throws IOException {
		XuatExcels xuat = new XuatExcels();
		FileDialog fileDialog  = new FileDialog(this, "Xu???t th??ng tin s???n ph???m ra Excels", FileDialog.SAVE);
		fileDialog.setFile("Danh s??ch th??ng tin s???n ph???m");
		fileDialog .setVisible(true);
		String name = fileDialog.getFile();
		String fileName = fileDialog.getDirectory() + name;

		if (name == null) 
			return;
		
		if(!fileName.endsWith(".xlsx")||!fileName.endsWith(".xls")) 
			fileName += ".xlsx";
		
		xuat.xuatTable(tblSanPham, "DANH S??CH TH??NG TIN S???N PH???M", fileName);
	}
	
	//Hien thi thong tin san pham khi chon vao bang
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(tblSanPham)) {
			int row = tblSanPham.getSelectedRow();
			String maSP = tblSanPham.getValueAt(row, 0).toString();
			txtTenSP.setText(modelSanPham.getValueAt(row, 1).toString());
			txtGiaSX.setText(modelSanPham.getValueAt(row, 2).toString());
			txtSoLuong.setText(modelSanPham.getValueAt(row, 3).toString());
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnReset)) {
			resetAll();
		}
		if(o.equals(btnThemSP)) {
			themSanPham();
		}
		if(o.equals(btnSuaSP)) {
			suaThongTin();
		}
		if(o.equals(btnXoaP)) {
			xoaSanPham();
		}
		if(cboSapXep.getSelectedItem()=="T??ng d???n") {
			if(o.equals(rdoTheoMaSP)) {
				loadTableSP();
			}
			if(o.equals(rdoTheoGiaSX)) {
				sortGiaSPTangDan(sp);
			}
			if(o.equals(rdoTheoSoLuong)) {
				sortSoLuongSPTangDan(sp);
			}
		}
		if(cboSapXep.getSelectedItem()=="Gi???m d???n") {
			if(o.equals(rdoTheoMaSP)) {
				sortMaSanPhamGiamDan(sp);
			}
			if(o.equals(rdoTheoGiaSX)) {
				sortGiaSPGiamDan(sp);
			}
			if(o.equals(rdoTheoSoLuong)) {
				sortSoLuongSPGiamDan(sp);
			}
		}
		if(o.equals(btnTim)) {
			findSanPham();
		}
		if(o.equals(cboSapXep)) {
			bgRdo.clearSelection();
			clearTable(modelSanPham);;
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
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {

		Object o = e.getSource();
		int key = e.getKeyCode();
		if(o.equals(txtSoLuong)&& key == KeyEvent.VK_ENTER ) {
			btnThemSP.doClick();
		}
		//else
		if(o.equals(txtTK)&& key == KeyEvent.VK_ENTER ) {
			btnTim.doClick();
		}	
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
