package app;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAOCongNhan;
import dao.DAOToSanXuat;
import dao.Regex;
import entity.CongNhan;
import entity.ToSanXuat;

import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;


public class FrmCongNhan extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dnow;
	private LocalDate now;
	
	private Panel pMain;
	private JTextField txtMaCN;
	private JTextField txtTenCN;
	private JTextField txtDiaChi;
	private JTextField txtCCCD;
	private JTextField txtSDT;
	private SimpleDateFormat dfNgaySinh;
	private JButton btnThemCN;
	private JButton btnSuaCN;
	private JButton btnXoaCN;
	private JButton btnLamMoiCN;
	private JButton btnTimKiemCN;
	private JButton btnInDS;
	private JLabel lblSoNV;
	private JRadioButton radSXtheoMaCN;
	private JRadioButton radSXtheoTen;
	private JRadioButton radSXtheoMaTo;
	private JRadioButton radSXtheoNgaySinh;
	private DAOCongNhan daoCongNhan;
	private DAOToSanXuat daoToSX;
	private int ngay, thang, nam;
	private JComboBox<Object> cbbTo;
	private JComboBox<Object> cbbSapXep;
	private JComboBox<Object> cbbGioiTinh;
	private DefaultComboBoxModel<Object> ModelCbbTo;
	private DefaultComboBoxModel<Object> modelCbbGioiTinh;
	
	private JTable tbCongNhan;
	private DefaultTableModel modelCongNhan;
	private JDateChooser chooserNgaySinh;
	private Regex regex;
	private boolean statusEdit = false;
	private int soNV;
	
	public Panel getPanel() {
		return this.pMain;
	}
	
//	@SuppressWarnings("deprecation")
	@SuppressWarnings("deprecation")
	public FrmCongNhan()  {
		//connect database
		try {
		    	ConnectDB.getinstance().connect();
		} catch (SQLException e) {
				e.printStackTrace();
		}
	
		//khai bao 
		dfNgaySinh= new SimpleDateFormat("yyyy-MM-dd");
		daoCongNhan = new DAOCongNhan();
		daoToSX = new DAOToSanXuat();
		regex=new Regex();
		IconFontSwing.register(FontAwesome.getIconFont());
	
		//setLayout(null);
		getContentPane().setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 629);
		getContentPane().add(pMain);
		pMain.setLayout(null);
		
		JLabel lbltitle = new JLabel("BẢNG CÔNG NHÂN");
		lbltitle.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lbltitle.setBounds(675, 10, 350, 56);
		lbltitle.setForeground(new Color(164, 44, 167));
		pMain.add(lbltitle);
		
		JPanel pNhanSu = new JPanel();
		pNhanSu.setToolTipText(" Thông tin công nhân ");
		pNhanSu.setBorder(new TitledBorder(new LineBorder(new Color(114, 23, 153), 2, true), "                               THÔNG TIN CÔNG NHÂN                                     ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(114, 23, 153) ));
		pNhanSu.setBackground(Color.white);
		pNhanSu.setBounds(5, 10, 355, 605);
		pMain.add(pNhanSu);
		pNhanSu.setLayout(null);
		
		JLabel lblSubMaCN = new JLabel("Mã Công Nhân: ");
		lblSubMaCN.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSubMaCN.setBounds(17, 37, 140, 26);
		pNhanSu.add(lblSubMaCN);
		txtMaCN = new JTextField();
		txtMaCN.setBackground(new Color(255, 255, 255));
		txtMaCN.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtMaCN.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));;
		txtMaCN.setBounds(150, 37, 185, 30);
		pNhanSu.add(txtMaCN);
		
		
		JLabel lblSubTenCN = new JLabel("Tên công nhân: ");
		lblSubTenCN.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSubTenCN.setBounds(17, 82, 130, 26);
		pNhanSu.add(lblSubTenCN);
		txtTenCN = new JTextField();
		txtTenCN.setBackground(new Color(255, 255, 255));
		txtTenCN.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtTenCN.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));;
		txtTenCN.setBounds(150, 82, 185, 30);
		pNhanSu.add(txtTenCN);
		
		
		JLabel lblSubTo = new JLabel("Mã tổ: ");
		lblSubTo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSubTo.setBounds(17, 127, 130, 26);
		pNhanSu.add(lblSubTo);
		
		ModelCbbTo = new DefaultComboBoxModel<>();
		ModelCbbTo.setSelectedItem("");
		ArrayList<ToSanXuat> lstTSX = daoToSX.getDSToSanXuat();
		for(ToSanXuat TSX : lstTSX) {
			ModelCbbTo.addElement(TSX.getMaTo());
		}
		cbbTo = new JComboBox<Object>(ModelCbbTo);
		cbbTo.setToolTipText("Chọn chức vụ");
		cbbTo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cbbTo.setBackground(Color.WHITE);
		cbbTo.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		cbbTo.setBounds(150, 127, 185, 30);
		pNhanSu.add(cbbTo);
		
		now = LocalDate.now();
		ngay = now.getDayOfMonth(); 
		thang = now.getMonthValue()-1;
		nam = now.getYear();
		dnow = new Date(nam, thang, ngay);
		
		JLabel lblSubNgaySinh = new JLabel("Ngày sinh: ");
		lblSubNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSubNgaySinh.setBounds(17, 172, 130, 26);
		pNhanSu.add(lblSubNgaySinh);
		chooserNgaySinh = new JDateChooser();
		chooserNgaySinh.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserNgaySinh.setBounds(150, 172, 185, 30);
		chooserNgaySinh.setDateFormatString("dd/MM/yyyy");
		//chooserNgaySinh.setDate(dNgayHienTai);
		chooserNgaySinh.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		chooserNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgaySinh.getCalendarButton().setBackground(new Color(114, 23, 153));
		chooserNgaySinh.getCalendarButton().setToolTipText("Chọn ngày sinh");
		Icon iconCalendar = IconFontSwing.buildIcon(FontAwesome.CALENDAR, 17, Color.WHITE);
		chooserNgaySinh.setIcon((ImageIcon) iconCalendar);
		pNhanSu.add(chooserNgaySinh);
		
		JLabel lblSubGioiTinh = new JLabel("Giới tính: ");
		lblSubGioiTinh.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSubGioiTinh.setBounds(17, 217, 130, 26);
		pNhanSu.add(lblSubGioiTinh);
		
		modelCbbGioiTinh = new DefaultComboBoxModel<>();
		modelCbbGioiTinh.setSelectedItem("");
		modelCbbGioiTinh.addElement("Nam");
		modelCbbGioiTinh.addElement("Nữ");
		cbbGioiTinh = new JComboBox<Object>(modelCbbGioiTinh);
		cbbGioiTinh.setToolTipText("Chọn giới tính");
		cbbGioiTinh.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cbbGioiTinh.setBackground(Color.WHITE);
		cbbGioiTinh.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		cbbGioiTinh.setBounds(150, 217, 185, 30);
		pNhanSu.add(cbbGioiTinh);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		lblDiaChi.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblDiaChi.setBounds(17, 262, 130, 26);
		pNhanSu.add(lblDiaChi);
		txtDiaChi = new JTextField();
		txtDiaChi.setBackground(new Color(255, 255, 255));
		txtDiaChi.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtDiaChi.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));;
		txtDiaChi.setBounds(150, 262, 185, 30);
		pNhanSu.add(txtDiaChi);
		
		JLabel lblCCCD = new JLabel("CCCD/CMND: ");
		lblCCCD.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblCCCD.setBounds(17, 307, 130, 26);
		pNhanSu.add(lblCCCD);
		txtCCCD = new JTextField();
		txtCCCD.setBackground(new Color(255, 255, 255));
		txtCCCD.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtCCCD.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));;
		txtCCCD.setBounds(150, 307, 185, 30);
		pNhanSu.add(txtCCCD);
		
		JLabel lblSDT = new JLabel("SĐT: ");
		lblSDT.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSDT.setBounds(17, 352, 130, 26);
		pNhanSu.add(lblSDT);
		txtSDT = new JTextField();
		txtSDT.setBackground(new Color(255, 255, 255));
		txtSDT.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtSDT.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));;
		txtSDT.setBounds(150, 352, 185, 30);
		pNhanSu.add(txtSDT);
		
		JPanel pSoNhanVien = new JPanel();
		pSoNhanVien.setToolTipText("Số công nhân");
		pSoNhanVien.setBackground(new Color(178, 192, 237));
		pSoNhanVien.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "❀", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSoNhanVien.setBounds(370, 65, 191, 50);
		pMain.add(pSoNhanVien);
		pSoNhanVien.setLayout(null);
		
		JPanel pSapXep = new JPanel();
		pSapXep.setToolTipText("Sắp xếp");
		pSapXep.setBackground(new Color(178, 192, 237));
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "❀", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBounds(560, 65, 695, 50);
		pMain.add(pSapXep);
		pSapXep.setLayout(null);
		
		
		lblSoNV = new JLabel("Số công nhân: "+soNV );
		lblSoNV.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSoNV.setBounds(20, 14, 170, 30);
		pSoNhanVien.add(lblSoNV);
		
		JLabel lblSapxep = new JLabel("Sắp xếp: ");
		lblSapxep.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSapxep.setBounds(15, 14, 80, 30);
		pSapXep.add(lblSapxep);
		
		cbbSapXep = new JComboBox<>(new Object[] {"Tăng dần","Giảm dần"});
		cbbSapXep.setFont(new Font("SansSerif", Font.PLAIN, 18));
		cbbSapXep.setBounds(100, 14, 120, 30);
		cbbSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pSapXep.add(cbbSapXep);
		
		radSXtheoMaCN = new JRadioButton("Mã CN");
		radSXtheoMaCN.setFont(new Font("SansSerif", Font.PLAIN, 18));
		radSXtheoMaCN.setBounds(235, 14, 100, 30);
		radSXtheoMaCN.setSelected(true);
		pSapXep.add(radSXtheoMaCN);
		
		radSXtheoTen = new JRadioButton("Tên CN");
		radSXtheoTen.setFont(new Font("SansSerif", Font.PLAIN, 18));
		radSXtheoTen.setBounds(345, 14, 100, 30);
		pSapXep.add(radSXtheoTen);
		
		radSXtheoMaTo = new JRadioButton("Tổ SX");
		radSXtheoMaTo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		radSXtheoMaTo.setBounds(460, 14, 105, 30);
		pSapXep.add(radSXtheoMaTo);
		
		radSXtheoNgaySinh = new JRadioButton("Ngày sinh");
		radSXtheoNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 18));
		radSXtheoNgaySinh.setBounds(560, 14, 150, 30);
		pSapXep.add(radSXtheoNgaySinh);
		
		ButtonGroup bg = new ButtonGroup();
        bg.add(radSXtheoMaCN);
        bg.add(radSXtheoTen);
        bg.add(radSXtheoMaTo);
        bg.add(radSXtheoNgaySinh);
        
        
        //Table Công Nhân
		
		String col [] = {"Mã CN", "Họ và Tên", "Mã tổ","Ngày sinh","Giới tính", "Địa chỉ","CCCD","SĐT"};
		modelCongNhan = new DefaultTableModel(col,0);
		tbCongNhan = new JTable(modelCongNhan);
		tbCongNhan.setShowHorizontalLines(true);
		tbCongNhan.setShowGrid(true);
		tbCongNhan.setBackground(Color.WHITE);
		tbCongNhan.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		JTableHeader tbHeader = tbCongNhan.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(new Color(255, 255, 255));
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		tbCongNhan.getColumnModel().getColumn(0).setPreferredWidth(70); 
		tbCongNhan.getColumnModel().getColumn(1).setPreferredWidth(160);
		tbCongNhan.getColumnModel().getColumn(2).setPreferredWidth(90); 
		tbCongNhan.getColumnModel().getColumn(3).setPreferredWidth(110); 
		tbCongNhan.getColumnModel().getColumn(4).setPreferredWidth(90); 
		tbCongNhan.getColumnModel().getColumn(5).setPreferredWidth(250); 
		tbCongNhan.getColumnModel().getColumn(6).setPreferredWidth(120); 
		tbCongNhan.getColumnModel().getColumn(7).setPreferredWidth(100); 
		
		tbCongNhan.setSelectionBackground(new Color(255, 255, 255,30));
		tbCongNhan.setSelectionForeground(new Color(114, 23, 153));
		tbCongNhan.setRowHeight(30);
		
//		TableColumn GioiTinh = tbCongNhan.getColumnModel().getColumn(4);
//		GioiTinh.setCellEditor(new DefaultCellEditor(cbbGioiTinh));
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		
		tbCongNhan.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		tbCongNhan.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tbCongNhan.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		JScrollPane spCongNhan = new JScrollPane(tbCongNhan);
		spCongNhan.setToolTipText("Danh sách công nhân");
		spCongNhan.setBounds(370,120 , 885, 493);
		spCongNhan.setBorder(new LineBorder(new Color(164, 44, 167), 1, false));
		spCongNhan.setBackground(new Color(164, 44, 167));
		pMain.add(spCongNhan);
		
		btnThemCN = new FixButton("➕ Thêm");
		btnThemCN.setForeground(Color.white);
		btnThemCN.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnThemCN.setBackground(new Color(57, 210, 247));
		btnThemCN.setBounds(22, 407, 140, 50);
		pNhanSu.add(btnThemCN);
		
		btnXoaCN = new FixButton("❌ Xóa");
		btnXoaCN.setForeground(Color.white);
		btnXoaCN.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnXoaCN.setBackground(new Color(0xE91940));
		btnXoaCN.setBounds(22, 527, 140, 50);
//		Icon iconXoaCN = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
//		btnXoaCN.setIcon(iconXoaCN);
		pNhanSu.add(btnXoaCN);
		
		btnSuaCN = new FixButton("🔧 Sửa");
		btnSuaCN.setForeground(Color.white);
		btnSuaCN.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnSuaCN.setBackground(new Color(133, 217, 191));
		btnSuaCN.setBounds(22, 467, 140, 50);
//		Icon iconXoaCN = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
//		btnXoaCN.setIcon(iconXoaCN);
		pNhanSu.add(btnSuaCN);
		
		btnLamMoiCN = new FixButton("🔄 Làm mới");
		btnLamMoiCN.setForeground(Color.WHITE);
		btnLamMoiCN.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnLamMoiCN.setBackground(new Color(114, 23, 153));
		btnLamMoiCN.setBounds(187, 467, 140, 50);
//		Icon iconXoaNV = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
//		btnXoaNV.setIcon(iconXoaNV);
		pNhanSu.add(btnLamMoiCN);
		
		btnTimKiemCN = new FixButton("🔍 Tra cứu");
		btnTimKiemCN.setForeground(Color.white);
		btnTimKiemCN.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTimKiemCN.setBackground(Color.black);
		btnTimKiemCN.setBounds(187, 407, 140, 50);
		//Icon iconXoaNV = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		//btnXoaNV.setIcon(iconXoaNV);
		pNhanSu.add(btnTimKiemCN);
		
		btnInDS = new FixButton("📃 Xuất Excel");
		btnInDS.setForeground(Color.white);
		btnInDS.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnInDS.setBackground(new Color(0, 128, 0));
		btnInDS.setBounds(187, 527, 140, 50);
		//Icon iconXoaNV = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		//btnXoaNV.setIcon(iconXoaNV);
		
		pNhanSu.add(btnInDS);
		
		loadDanhSachCN();
		
		btnThemCN.addActionListener(this);
		btnXoaCN.addActionListener(this);
		btnLamMoiCN.addActionListener(this);
		btnSuaCN.addActionListener(this);
		btnInDS.addActionListener(this);
		btnTimKiemCN.addActionListener(this);
		radSXtheoMaCN.addActionListener(this);
		radSXtheoTen.addActionListener(this);
		radSXtheoMaTo.addActionListener(this);
		radSXtheoNgaySinh.addActionListener(this);
		cbbSapXep.addActionListener(this);
		tbCongNhan.addMouseListener(this);
		
	
	}
	private void loadDanhSachCN()  {
		//clearTable();
		removeDanhSachCN(modelCongNhan);
		ArrayList<CongNhan> lstCN = daoCongNhan.getDSCongNhan();
		for(CongNhan CN : lstCN) {
			modelCongNhan.addRow(new Object[] {
					CN.getMaCN(), CN.getTenCN(), CN.getToSanXuat().getMaTo(), CN.getNgaySinh(), CN.getGioiTinh(), CN.getDiaChi(), CN.getCccd(), CN.getSdt()
			});
		}
		soNV = modelCongNhan.getRowCount();
		lblSoNV.setText("Số công nhân: "+soNV );
	}
	private void removeDanhSachCN(DefaultTableModel defaultTableModel) {
		while(tbCongNhan.getRowCount() > 0){
			modelCongNhan.removeRow(0);
		}
	}
	private void checkEdit() {
		 if(!statusEdit) {
			 btnXoaCN.setVisible(true);
			 btnLamMoiCN.setVisible(true);
			 btnThemCN.setEnabled(true);
		 }
		 else {
			 btnXoaCN.setVisible(true);
			 btnLamMoiCN.setVisible(true);
			 btnThemCN.setEnabled(true);
		 }
		
	}
	
	//Xuat excels
	public void xuatExcel() throws IOException {
		XuatExcels xuat = new XuatExcels();
		FileDialog fileDialog  = new FileDialog(this, "Danh sách thông tin công nhân", FileDialog.SAVE);
		fileDialog.setFile("Danh sách thông tin công nhân");
		fileDialog .setVisible(true);
		String name = fileDialog.getFile();
		String fileName = fileDialog.getDirectory() + name;

		if (name == null) 
			return;
			
		if(!fileName.endsWith(".xlsx")||!fileName.endsWith(".xls")) 
			fileName += ".xlsx";
			
		xuat.xuatTable(tbCongNhan, "DANH SÁCH THÔNG TIN CÔNG NHÂN", fileName);
	}
	
	@SuppressWarnings("deprecation")
	private void addNV() {
		String maCN = txtMaCN.getText().trim();
		String hoTen = txtTenCN.getText().trim();
		String sdt = txtSDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String mato = cbbTo.getSelectedItem().toString();
		String cccd = txtCCCD.getText().trim();
		String gioiTinh = cbbGioiTinh.getSelectedItem().toString();
		ToSanXuat tosanxuat = daoToSX.getToSXfromMaToSX(mato);
		

//		kiem tra maNV
		
		String regexMaCN = "^((CN|cn)[0-9]{3})$";
		if(maCN.matches(regexMaCN)) {
			
			if(daoCongNhan.checkmaCN(maCN)) {
				

				if(hoTen.equals("") || sdt.equals("") || diaChi.equals("") || cccd.equals("")) {
					JOptionPane.showMessageDialog(this,  "Vui lòng nhập thông tin đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
					txtTenCN.requestFocus();
				}
				else {
					if(regex.regexTen(txtTenCN) && regex.regexSDT(txtSDT) && regex.regexDiaChi(txtDiaChi) && regex.regexCCCD(txtCCCD)) {
						if(daoCongNhan.checkSdtCN(sdt)) {
							if(daoCongNhan.checkCccdCN(cccd)) {
								java.util.Date date = chooserNgaySinh.getDate();
								Date ngaySinh = new Date(date.getYear(), date.getMonth(), date.getDate());
								int age = nam - date.getYear();
								if(age>=18 && ngaySinh.getDate()>0 && ngaySinh.getDate()<=31 && ngaySinh.getMonth()>0 && ngaySinh.getMonth()<=12 && ngaySinh.getYear()>0 && ngaySinh.getYear()<nam) { 
				
									
									CongNhan cn = new CongNhan();
									cn.setMaCN(maCN);
									cn.setTenCN(hoTen);
									cn.setToSanXuat(tosanxuat);
									cn.setGioiTinh(gioiTinh);
									cn.setNgaySinh(ngaySinh);
									cn.setDiaChi(diaChi);
									cn.setSdt(sdt);
									cn.setCccd(cccd);
									try {
										daoCongNhan.themCN(cn);
										
									}catch (SQLException e) {
										e.printStackTrace();
										JOptionPane.showMessageDialog(this,  "Thêm nhân viên thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
									} 
									LamMoi();
									modelCongNhan.addRow(new Object[] {
											cn.getMaCN(), cn.getTenCN(), cn.getToSanXuat().getMaTo(), cn.getNgaySinh(), cn.getGioiTinh(), cn.getDiaChi(), cn.getCccd(), cn.getSdt()
									});
							
									JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thong bao", JOptionPane.INFORMATION_MESSAGE);
								} else
									JOptionPane.showMessageDialog(this, "Công nhân làm việc phải trên 18 tuổi!", "Thông báo", JOptionPane.WARNING_MESSAGE);
							} else
								JOptionPane.showMessageDialog(this, "Căn cước công dân đã đăng ký", "Thông báo", JOptionPane.ERROR_MESSAGE);
						} else 
							JOptionPane.showMessageDialog(this, "Số điện thoại đã đăng ký", "Thông báo", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(this,  "Mã công nhân đã tồn tại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else {
			JOptionPane.showMessageDialog(this,  "Mã công nhân có định dạng CN00x!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	@SuppressWarnings({ "deprecation"})
	private void findCN() {
		String maCN = txtMaCN.getText();
		String hoTen = txtTenCN.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		
		
		
		
		String cccd = txtCCCD.getText();
		String gioiTinh = cbbGioiTinh.getSelectedItem().toString();
		String ngaySinh;
		String regexTo = "^(T|t){1}[0-9]+";
		String maTo = cbbTo.getSelectedItem().toString();
		if(maTo.matches(regexTo)) {
			
		}else {
			maTo = "";
		}
		
		
		


		java.util.Date date = chooserNgaySinh.getDate();
		if(date == null) {
			ngaySinh = new String("");
		}else {
			Date ngaysinh = new Date(date.getYear(), date.getMonth(), date.getDate());
			ngaySinh = dfNgaySinh.format(ngaysinh);
		}
		ArrayList<CongNhan> lstCN = null;
		lstCN = daoCongNhan.TimKiemCongNhan(maCN, hoTen, maTo, ngaySinh, gioiTinh, diaChi, cccd, sdt);
		
		removeDanhSachCN(modelCongNhan);
		for(CongNhan cn : lstCN) {
			modelCongNhan.addRow(new Object[] {
					cn.getMaCN(), cn.getTenCN(), cn.getToSanXuat().getMaTo(), cn.getNgaySinh(), cn.getGioiTinh(), cn.getDiaChi(), cn.getCccd(), cn.getSdt()
			});
		}
		soNV = modelCongNhan.getRowCount();
		lblSoNV.setText("Số công nhân: "+soNV );
		if(lstCN.size()==0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin công nhân" , "Thông báo", JOptionPane.ERROR_MESSAGE);
			txtMaCN.requestFocus();
			txtMaCN.selectAll();
		}
		
	}
	
	//Lam moi 
	@SuppressWarnings("unused")
	private void LamMoi() {
			txtMaCN.setEnabled(true);
			txtMaCN.setText("");
			txtTenCN.setText("");
			ModelCbbTo.setSelectedItem("");
			modelCbbGioiTinh.setSelectedItem("");
			txtDiaChi.setText("");
			txtSDT.setText("");
			txtCCCD.setText("");
			chooserNgaySinh.setDate(null);
			txtMaCN.requestFocus();
			removeDanhSachCN(modelCongNhan);
			loadDanhSachCN();
	}
	
	private void XoaNV() {
		int row = tbCongNhan.getSelectedRow();
		if(row>=0) {
			int delete = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa công nhân này?", "Thông báo", JOptionPane.YES_NO_OPTION);
			if(delete == JOptionPane.YES_OPTION) {
				String maCN = (String) tbCongNhan.getValueAt(row, 0);
				try {
						daoCongNhan.xoaCN(maCN);
						removeDanhSachCN(modelCongNhan);
						JOptionPane.showMessageDialog(this, "Xóa công nhân thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				
				}catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Xóa công nhân thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin nhân viên cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		

		loadDanhSachCN();
		soNV = modelCongNhan.getRowCount();
		lblSoNV.setText("Số Nhân Viên: "+soNV );
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	private void updateNV() {
		int row = tbCongNhan.getSelectedRow();
		if(row>=0) {
			int update = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa thông tin công nhân này?", "Thông báo", JOptionPane.YES_NO_OPTION);
			if(update == JOptionPane.YES_OPTION) {
				CongNhan cn=new CongNhan();
				String maCN = (String) tbCongNhan.getValueAt(row, 0);
				java.util.Date date = chooserNgaySinh.getDate();
				Date ngaySinh=new Date(date.getYear(), date.getMonth(), date.getDate());
				try {
					if(regex.regexTen(txtTenCN) && regex.regexSDT(txtSDT) && regex.regexDiaChi(txtDiaChi) && regex.regexCCCD(txtCCCD)) {
						cn.setTenCN(txtTenCN.getText());
						String mato =  (String) cbbTo.getSelectedItem();
						ToSanXuat tosanxuat = daoToSX.getToSXfromMaToSX(mato);
						cn.setToSanXuat(tosanxuat);
						cn.setGioiTinh((String) cbbGioiTinh.getSelectedItem());
						cn.setNgaySinh(ngaySinh);
						cn.setDiaChi(txtDiaChi.getText());
						cn.setSdt(txtSDT.getText());
						cn.setCccd(txtCCCD.getText());
		

						daoCongNhan.capNhatCN(cn, maCN);
					

						removeDanhSachCN(modelCongNhan);
						ArrayList<CongNhan> lstCN = daoCongNhan.getDSCongNhan();
						modelCongNhan.setRowCount(0);
						for(CongNhan CN : lstCN) {
							modelCongNhan.addRow(new Object[] {
									CN.getMaCN(), CN.getTenCN(), CN.getToSanXuat().getMaTo(), CN.getNgaySinh(), CN.getGioiTinh(), CN.getDiaChi(), CN.getCccd(), CN.getSdt()
							});
						}
						JOptionPane.showMessageDialog(this, "Thông tin nhân viên đã được sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}
				}catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Chỉnh sửa thông tin thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin nhân viên cần sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		
		loadDanhSachCN();
	}
	//Lay du lieu tu bang vao arraylist
	private ArrayList<CongNhan> getDataTuBang(){
		ArrayList<CongNhan> lstCN = new ArrayList<CongNhan>();
		int sodong = modelCongNhan.getRowCount();
		if(sodong == 0)
			return lstCN;
		CongNhan cn;
		String mato, macn, hoten, gioitinh, diachi, cccd, sdt;
		ToSanXuat toSanXuat = new ToSanXuat();
		Date ngaysinh;
		for (int i = 0; i < sodong; i++) {
			macn = (String) tbCongNhan.getValueAt(i, 0);
			hoten = (String) tbCongNhan.getValueAt(i, 1);
			mato = (String) tbCongNhan.getValueAt(i, 2);
			ngaysinh =  (Date) tbCongNhan.getValueAt(i, 3);
			gioitinh = (String) tbCongNhan.getValueAt(i, 4);
			diachi = (String) tbCongNhan.getValueAt(i, 5);
			cccd = (String) tbCongNhan.getValueAt(i, 6);
			sdt = (String) tbCongNhan.getValueAt(i, 7);
			toSanXuat = daoToSX.getToSXfromMaToSX(mato);
			cn = new CongNhan(macn, hoten, toSanXuat, gioitinh, ngaysinh, diachi, cccd, sdt);
			lstCN.add(cn);
		}
		return lstCN;
	}
	//Sap xep theo MaCN
	private void SapXepModelTableTheoMaCN(){
		ArrayList<CongNhan> lstCN = getDataTuBang();
		if(cbbSapXep.getSelectedItem().toString().equals("Tăng dần")) {
			Collections.sort(lstCN, new Comparator<CongNhan>() {

				@Override
				public int compare(CongNhan o1, CongNhan o2) {
					// TODO Auto-generated method stub
					return o1.getMaCN().compareTo(o2.getMaCN());
				}
			});
		}
		if(cbbSapXep.getSelectedItem().toString().equals("Giảm dần")) {
			Collections.sort(lstCN, new Comparator<CongNhan>() {

				@Override
				public int compare(CongNhan o1, CongNhan o2) {
					// TODO Auto-generated method stub
					return o2.getMaCN().compareTo(o1.getMaCN());
				}
			});
		}
		removeDanhSachCN(modelCongNhan);
		for(CongNhan CN : lstCN) {
			modelCongNhan.addRow(new Object[] {
					CN.getMaCN(), CN.getTenCN(), CN.getToSanXuat().getMaTo(), CN.getNgaySinh(), CN.getGioiTinh(), CN.getDiaChi(), CN.getCccd(), CN.getSdt()
			});
		}
	}
	//Sap xep theo ten
	private void SapXepModelTableTheoTenCN(){
		ArrayList<CongNhan> lstCN = getDataTuBang();
		if(cbbSapXep.getSelectedItem().toString().equals("Tăng dần")) {
			Collections.sort(lstCN, new Comparator<CongNhan>() {

				@Override
				public int compare(CongNhan o1, CongNhan o2) {
					// TODO Auto-generated method stub
					return o1.getTenCN().compareTo(o2.getTenCN());
				}
			});
		}
		if(cbbSapXep.getSelectedItem().toString().equals("Giảm dần")) {
			Collections.sort(lstCN, new Comparator<CongNhan>() {

				@Override
				public int compare(CongNhan o1, CongNhan o2) {
					// TODO Auto-generated method stub
					return o2.getTenCN().compareTo(o1.getTenCN());
				}
			});
		}
		removeDanhSachCN(modelCongNhan);
		for(CongNhan CN : lstCN) {
			modelCongNhan.addRow(new Object[] {
					CN.getMaCN(), CN.getTenCN(), CN.getToSanXuat().getMaTo(), CN.getNgaySinh(), CN.getGioiTinh(), CN.getDiaChi(), CN.getCccd(), CN.getSdt()
			});
		}
	}
	//Sap xep theo ma to
	private void SapXepModelTableTheoMaTo(){
		ArrayList<CongNhan> lstCN = getDataTuBang();
		if(cbbSapXep.getSelectedItem().toString().equals("Tăng dần")) {
			Collections.sort(lstCN, new Comparator<CongNhan>() {

				@Override
				public int compare(CongNhan o1, CongNhan o2) {
					// TODO Auto-generated method stub
					return o1.getToSanXuat().getMaTo().compareTo(o2.getToSanXuat().getMaTo());
				}
			});
		}
		if(cbbSapXep.getSelectedItem().toString().equals("Giảm dần")) {
			Collections.sort(lstCN, new Comparator<CongNhan>() {

				@Override
				public int compare(CongNhan o1, CongNhan o2) {
					// TODO Auto-generated method stub
					return o2.getToSanXuat().getMaTo().compareTo(o1.getToSanXuat().getMaTo());
				}
			});
		}
		removeDanhSachCN(modelCongNhan);
		for(CongNhan CN : lstCN) {
			modelCongNhan.addRow(new Object[] {
					CN.getMaCN(), CN.getTenCN(), CN.getToSanXuat().getMaTo(), CN.getNgaySinh(), CN.getGioiTinh(), CN.getDiaChi(), CN.getCccd(), CN.getSdt()
			});
		}
	}
	//Sap xep theo ngay sinh
	private void SapXepModelTableTheoNgaySinh(){
		ArrayList<CongNhan> lstCN = getDataTuBang();
		if(cbbSapXep.getSelectedItem().toString().equals("Tăng dần")) {
			Collections.sort(lstCN, new Comparator<CongNhan>() {

				@SuppressWarnings("deprecation")
				@Override
				public int compare(CongNhan o1, CongNhan o2) {
					// TODO Auto-generated method stub
					return o1.getNgaySinh().getYear() - o2.getNgaySinh().getYear();
				}
			});
		}
		if(cbbSapXep.getSelectedItem().toString().equals("Giảm dần")) {
			Collections.sort(lstCN, new Comparator<CongNhan>() {

				@SuppressWarnings("deprecation")
				@Override
				public int compare(CongNhan o1, CongNhan o2) {
					// TODO Auto-generated method stub
					return o2.getNgaySinh().getYear() - o1.getNgaySinh().getYear();
				}
			});
		}
		removeDanhSachCN(modelCongNhan);
		for(CongNhan CN : lstCN) {
			modelCongNhan.addRow(new Object[] {
					CN.getMaCN(), CN.getTenCN(), CN.getToSanXuat().getMaTo(), CN.getNgaySinh(), CN.getGioiTinh(), CN.getDiaChi(), CN.getCccd(), CN.getSdt()
			});
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int selectedRow = tbCongNhan.getSelectedRow();
		
		if(selectedRow >= 0) {
			txtMaCN.setEditable(true);
			statusEdit = true;
			checkEdit();
			String maCN = (String) tbCongNhan.getValueAt(selectedRow, 0);
			ArrayList<CongNhan> lstCN = daoCongNhan.getDSCongNhan();
			for(CongNhan cn : lstCN) {
				if(maCN.equals(cn.getMaCN())) {
					txtMaCN.setEnabled(false);
					txtMaCN.setText(maCN);
					txtTenCN.setText(cn.getTenCN());
					txtSDT.setText(cn.getSdt());
					txtDiaChi.setText(cn.getDiaChi());
					cbbTo.setSelectedItem(cn.getToSanXuat().getMaTo());
					txtCCCD.setText(cn.getCccd());
					cbbGioiTinh.setSelectedItem(cn.getGioiTinh());
					chooserNgaySinh.setDate(cn.getNgaySinh());
					break;
				}
				
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnThemCN)) {
			addNV();
		}
		if(o.equals(btnTimKiemCN)) {
			findCN();
		}
		if(o.equals(btnLamMoiCN)) {
			LamMoi();
		}
		if(o.equals(btnXoaCN)) {
			XoaNV();;
		}
		if(o.equals(btnSuaCN)) {
			updateNV();
		}
		
		if(o.equals(btnInDS)) {
			try {
				xuatExcel();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(radSXtheoMaCN.isSelected()) {
				SapXepModelTableTheoMaCN();
		}
		
		if(radSXtheoTen.isSelected()) {
			SapXepModelTableTheoTenCN();
		}
		
		if(radSXtheoMaTo.isSelected()) {
			SapXepModelTableTheoMaTo();
		}
		
		if(radSXtheoNgaySinh.isSelected()) {
			SapXepModelTableTheoNgaySinh();
		}
		
		if(cbbSapXep.getSelectedItem().toString().equals("")) {
			if(radSXtheoMaCN.isSelected()) {
				SapXepModelTableTheoMaCN();
			}
			if(radSXtheoTen.isSelected()) {
				SapXepModelTableTheoTenCN();
			}
			if(radSXtheoMaTo.isSelected()) {
				SapXepModelTableTheoMaTo();
			}
			if(radSXtheoNgaySinh.isSelected()) {
				SapXepModelTableTheoNgaySinh();
			}
		}	
			
	}
}
