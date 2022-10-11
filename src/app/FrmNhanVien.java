package app;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAONhanVien;
import dao.DAOTaiKhoan;
import dao.Regex;
import entity.NhanVien;
import entity.TaiKhoan;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class FrmNhanVien extends JPanel implements ActionListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV;
	private Date dnow;
	private LocalDate now;
	
	private Panel pMain;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtDiaChi;
	private JTextField txtCCCD;
	private JTextField txtSDT;
	private SimpleDateFormat dfNgaySinh;
	private JButton btnThemNV;
	private JButton btnSuaNV;
	private JButton btnXoaNV;
	private JButton btnLamMoiNV;
	private JButton btnTimKiemNV;
	private JButton btnInDS;
	private DAONhanVien daoNhanVien;
	private DAOTaiKhoan daoTaiKhoan;
	private int ngay, thang, nam;
	private JComboBox<Object> cbbChucVu;
	private JComboBox<Object> cbbGioiTinh;
	private JTable tbNhanVien;
	private DefaultTableModel modelNhanVien;
	private JDateChooser chooserNgaySinh;
	private Regex regex;
	private boolean statusEdit = false;
	
	
	public Panel getPanel() {
		return this.pMain;
	}
	@SuppressWarnings("deprecation")
	public FrmNhanVien()  {
		//connect database
		try {
		    	ConnectDB.getinstance().connect();
		} catch (SQLException e) {
				e.printStackTrace();
		}
	
		//khai bao 
		dfNgaySinh= new SimpleDateFormat("yyyy-MM-dd");
		daoNhanVien = new DAONhanVien();
		daoTaiKhoan = new DAOTaiKhoan();
		regex=new Regex();
		IconFontSwing.register(FontAwesome.getIconFont());
	
		setLayout(null);
	
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 629);
		add(pMain);
		pMain.setLayout(null);
		
		
		JPanel pNhanSu = new JPanel();
		pNhanSu.setToolTipText(" Thông tin nhân viên ");
		pNhanSu.setBorder(new TitledBorder(new LineBorder(new Color(0, 0 ,0), 5, true), " THÔNG TIN NHÂN VIÊN     ", TitledBorder.LEFT, TitledBorder.TOP, null, Color.ORANGE ));
		pNhanSu.setBackground(Color.WHITE);
		pNhanSu.setBounds(10, 10, 350, 600);
		pMain.add(pNhanSu);
		pNhanSu.setLayout(null);
		
		JLabel lblSubMaNV = new JLabel("Mã nhân viên: ");
		lblSubMaNV.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSubMaNV.setBounds(27, 27, 130, 26);
		pNhanSu.add(lblSubMaNV);
		txtMaNV = new JTextField();
		txtMaNV.setBackground(new Color(255, 255, 255));
		txtMaNV.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtMaNV.setBorder(new LineBorder(new Color(0, 0 , 0), 1, true));;
		txtMaNV.setBounds(150, 27, 180, 30);
		pNhanSu.add(txtMaNV);
		
		
		JLabel lblSubTenNV = new JLabel("Tên nhân viên: ");
		lblSubTenNV.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSubTenNV.setBounds(27, 72, 130, 26);
		pNhanSu.add(lblSubTenNV);
		txtTenNV = new JTextField();
		txtTenNV.setBackground(new Color(255, 255, 255));
		txtTenNV.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtTenNV.setBorder(new LineBorder(new Color(0, 0 , 0), 1, true));;
		txtTenNV.setBounds(150, 72, 180, 30);
		pNhanSu.add(txtTenNV);
		txtTenNV.setText("Tran Quoc Viet");
		
		JLabel lblSubChucVu = new JLabel("Chức vụ: ");
		lblSubChucVu.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSubChucVu.setBounds(27, 117, 130, 26);
		pNhanSu.add(lblSubChucVu);
		cbbChucVu = new JComboBox<Object>(new Object[] {"","Quản lí","Kế toán"});
		cbbChucVu.setToolTipText("Chọn chức vụ");
		cbbChucVu.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cbbChucVu.setBackground(Color.WHITE);
		cbbChucVu.setBorder(new LineBorder(new Color(0, 0 ,0), 1, true));
		cbbChucVu.setBounds(150, 117, 180, 30);
		pNhanSu.add(cbbChucVu);
		
		now = LocalDate.now();
		ngay = now.getDayOfMonth(); 
		thang = now.getMonthValue()-1;
		nam = now.getYear();
		dnow = new Date(nam, thang, ngay);
		
		JLabel lblSubNgaySinh = new JLabel("Ngày sinh: ");
		lblSubNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSubNgaySinh.setBounds(27, 162, 130, 26);
		pNhanSu.add(lblSubNgaySinh);
		chooserNgaySinh = new JDateChooser();
		chooserNgaySinh.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserNgaySinh.setBounds(150, 162, 180, 30);
		chooserNgaySinh.setDateFormatString("dd/MM/yyyy");
		//chooserNgaySinh.setDate(dNgayHienTai);
		chooserNgaySinh.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		chooserNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgaySinh.getCalendarButton().setBackground(new Color(0,0, 0));
		chooserNgaySinh.getCalendarButton().setToolTipText("Chọn ngày sinh");
		Icon iconCalendar = IconFontSwing.buildIcon(FontAwesome.CALENDAR, 17, Color.WHITE);
		chooserNgaySinh.setIcon((ImageIcon) iconCalendar);
		pNhanSu.add(chooserNgaySinh);
		
		JLabel lblSubGioiTinh = new JLabel("Giới tính: ");
		lblSubGioiTinh.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSubGioiTinh.setBounds(27, 207, 130, 26);
		pNhanSu.add(lblSubGioiTinh);
		cbbGioiTinh = new JComboBox<Object>(new Object[] {"","Nam","Nữ"} );
		cbbGioiTinh.setToolTipText("Chọn giới tính");
		cbbGioiTinh.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cbbGioiTinh.setBackground(Color.WHITE);
		cbbGioiTinh.setBorder(new LineBorder(new Color(0, 0 ,0), 1, true));
		cbbGioiTinh.setBounds(150, 207, 180, 30);
		pNhanSu.add(cbbGioiTinh);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		lblDiaChi.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblDiaChi.setBounds(27, 252, 130, 26);
		pNhanSu.add(lblDiaChi);
		txtDiaChi = new JTextField();
		txtDiaChi.setBackground(new Color(255, 255, 255));
		txtDiaChi.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtDiaChi.setBorder(new LineBorder(new Color(0, 0 , 0), 1, true));;
		txtDiaChi.setBounds(150, 252, 180, 30);
		pNhanSu.add(txtDiaChi);
		
		JLabel lblCCCD = new JLabel("CCCD/CMND: ");
		lblCCCD.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblCCCD.setBounds(27, 297, 130, 26);
		pNhanSu.add(lblCCCD);
		txtCCCD = new JTextField();
		txtCCCD.setBackground(new Color(255, 255, 255));
		txtCCCD.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtCCCD.setBorder(new LineBorder(new Color(0, 0 , 0), 1, true));;
		txtCCCD.setBounds(150, 297, 180, 30);
		pNhanSu.add(txtCCCD);
		
		JLabel lblSDT = new JLabel("SĐT: ");
		lblSDT.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSDT.setBounds(27, 342, 130, 26);
		pNhanSu.add(lblSDT);
		txtSDT = new JTextField();
		txtSDT.setBackground(new Color(255, 255, 255));
		txtSDT.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtSDT.setBorder(new LineBorder(new Color(0, 0 , 0), 1, true));;
		txtSDT.setBounds(150, 342, 180, 30);
		pNhanSu.add(txtSDT);
		
		String col [] = {"Mã NV", "Họ và Tên", "Chức vụ","Ngày sinh","Giới tính", "Địa chỉ","CCCD","SĐT"};
		modelNhanVien = new DefaultTableModel(col,0);
		
		tbNhanVien = new JTable(modelNhanVien);
		tbNhanVien.setShowHorizontalLines(true);
		tbNhanVien.setShowGrid(true);
		tbNhanVien.setBackground(Color.WHITE);
		tbNhanVien.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		JTableHeader tbHeader = tbNhanVien.getTableHeader();
		tbHeader.setBackground(new Color(255,255,255));
		tbHeader.setForeground(new Color(0,0,0));
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		tbNhanVien.getColumnModel().getColumn(0).setPreferredWidth(70); 
		tbNhanVien.getColumnModel().getColumn(1).setPreferredWidth(160);
		tbNhanVien.getColumnModel().getColumn(2).setPreferredWidth(90); 
		tbNhanVien.getColumnModel().getColumn(3).setPreferredWidth(110); 
		tbNhanVien.getColumnModel().getColumn(4).setPreferredWidth(90); 
		tbNhanVien.getColumnModel().getColumn(5).setPreferredWidth(250); 
		tbNhanVien.getColumnModel().getColumn(6).setPreferredWidth(120); 
		tbNhanVien.getColumnModel().getColumn(7).setPreferredWidth(100); 
		
		tbNhanVien.setSelectionBackground(new Color(255, 255, 255,30));
		tbNhanVien.setSelectionForeground(new Color(114, 23, 153));
		tbNhanVien.setRowHeight(30);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		
		tbNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		tbNhanVien.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tbNhanVien.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		JScrollPane spNhanVien = new JScrollPane(tbNhanVien);
		spNhanVien.setToolTipText("Danh sách nhân viên");
		spNhanVien.setBounds(370,77 , 880, 530);
		spNhanVien.setBorder(new LineBorder(new Color(0, 0 ,0), 5, true));
		spNhanVien.setBackground(new Color(164, 44, 167));
		pMain.add(spNhanVien);
		
		btnThemNV = new FixButton("Thêm");
		btnThemNV.setForeground(Color.black);
		btnThemNV.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnThemNV.setBackground(new Color(57, 210, 247));
		btnThemNV.setBounds(22, 407, 140, 50);
		Icon iconThemNV = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		btnThemNV.setIcon(iconThemNV);
		pNhanSu.add(btnThemNV);
		
		btnXoaNV = new FixButton("Xóa");
		btnXoaNV.setForeground(Color.black);
		btnXoaNV.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnXoaNV.setBackground(new Color(57, 210, 247));
		btnXoaNV.setBounds(187, 407, 140, 50);
		//Icon iconXoaNV = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		//btnXoaNV.setIcon(iconXoaNV);
		pNhanSu.add(btnXoaNV);
		
		btnSuaNV = new FixButton("Sửa");
		btnSuaNV.setForeground(Color.black);
		btnSuaNV.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnSuaNV.setBackground(new Color(57, 210, 247));
		btnSuaNV.setBounds(22, 467, 140, 50);
		//Icon iconXoaNV = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		//btnXoaNV.setIcon(iconXoaNV);
		pNhanSu.add(btnSuaNV);
		
		btnLamMoiNV = new FixButton("Làm mới");
		btnLamMoiNV.setForeground(Color.black);
		btnLamMoiNV.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnLamMoiNV.setBackground(new Color(57, 210, 247));
		btnLamMoiNV.setBounds(187, 467, 140, 50);
		//Icon iconXoaNV = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		//btnXoaNV.setIcon(iconXoaNV);
		pNhanSu.add(btnLamMoiNV);
		
		btnTimKiemNV = new FixButton("Tim");
		btnTimKiemNV.setForeground(Color.black);
		btnTimKiemNV.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTimKiemNV.setBackground(new Color(57, 210, 247));
		btnTimKiemNV.setBounds(22, 527, 140, 50);
		//Icon iconXoaNV = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		//btnXoaNV.setIcon(iconXoaNV);
		pNhanSu.add(btnTimKiemNV);
		
		btnInDS = new FixButton("In");
		btnInDS.setForeground(Color.black);
		btnInDS.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnInDS.setBackground(new Color(57, 210, 247));
		btnInDS.setBounds(187, 527, 140, 50);
		//Icon iconXoaNV = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		//btnXoaNV.setIcon(iconXoaNV);
		loadDanhSachNV();
		pNhanSu.add(btnInDS);
		
		btnThemNV.addActionListener(this);
		btnXoaNV.addActionListener(this);
		btnLamMoiNV.addActionListener(this);
		btnSuaNV.addActionListener(this);
		btnInDS.addActionListener(this);
		btnTimKiemNV.addActionListener(this);
		
		tbNhanVien.addMouseListener(this);
		
	
	}
	private void loadDanhSachNV()  {
		//clearTable();
		removeDanhSachNV(modelNhanVien);
		ArrayList<NhanVien> lstNV = daoNhanVien.getAllDanhSachNV();
		for(NhanVien NV : lstNV) {
			modelNhanVien.addRow(new Object[] {
					NV.getMaNV(), NV.getTenNV(), NV.getChucVu(), NV.getNgaySinh(), NV.getGioiTinh(), NV.getDiaChi(), NV.getCccd(), NV.getSdt()
			});
		}
	}
	private void removeDanhSachNV(DefaultTableModel defaultTableModel) {
		while(tbNhanVien.getRowCount() > 0){
			modelNhanVien.removeRow(0);
		}
	}
	private void checkEdit() {
		 if(!statusEdit) {
			 btnXoaNV.setVisible(true);
			 btnLamMoiNV.setVisible(true);
			 btnThemNV.setEnabled(true);
		 }
		 else {
			 btnXoaNV.setVisible(true);
			 btnLamMoiNV.setVisible(true);
			 btnThemNV.setEnabled(true);
		 }
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int selectedRow = tbNhanVien.getSelectedRow();
		
		if(selectedRow >= 0) {
			txtMaNV.setEditable(true);
			statusEdit = true;
			checkEdit();
			String maNV = (String) tbNhanVien.getValueAt(selectedRow, 0);
			ArrayList<NhanVien> lstNV = daoNhanVien.getAllDanhSachNV();
			for(NhanVien nv : lstNV) {
				if(maNV.equals(nv.getMaNV())) {
					txtMaNV.setText(maNV);
					txtTenNV.setText(nv.getTenNV());
					txtSDT.setText(nv.getSdt());
					txtDiaChi.setText(nv.getDiaChi());
					cbbChucVu.setSelectedItem(nv.getChucVu());
					txtCCCD.setText(nv.getCccd());
					cbbGioiTinh.setSelectedItem(nv.getGioiTinh());
					chooserNgaySinh.setDate(nv.getNgaySinh());
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
		if(o.equals(btnThemNV)) {
			addNV();
		}
		if(o.equals(btnTimKiemNV)) {
			findNV();
		}
		if(o.equals(btnLamMoiNV)) {
			LamMoi();
		}
		if(o.equals(btnXoaNV)) {
			XoaNV();;
		}
		if(o.equals(btnSuaNV)) {
			updateNV();
		}
		
	}
	@SuppressWarnings("deprecation")
	private void addNV() {
		String maNV = txtMaNV.getText().trim();
		String hoTen = txtTenNV.getText().trim();
		String sdt = txtSDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String chucVu = cbbChucVu.getSelectedItem().toString();
		String cccd = txtCCCD.getText().trim();
		String gioiTinh = cbbGioiTinh.getSelectedItem().toString();
		
		

//		kiem tra maNV
		
		String regexMaNV = "^((NV|nv)[0-9]{3})$";
		if(maNV.matches(regexMaNV)) {
			
			if(daoNhanVien.checkmaNV(maNV)) {
				
				String matKhau = maNV;

				if(hoTen.equals("") || sdt.equals("") || diaChi.equals("") || cccd.equals("")) {
					JOptionPane.showMessageDialog(this,  "Vui lòng nhập thông tin đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
					txtTenNV.requestFocus();
				}
				else {
					if(regex.regexTen(txtTenNV) && regex.regexSDT(txtSDT) && regex.regexDiaChi(txtDiaChi) && regex.regexCCCD(txtCCCD)) {
						if(daoNhanVien.checkSdtNV(sdt)) {
							if(daoNhanVien.checkCccdNV(cccd)) {
								java.util.Date date = chooserNgaySinh.getDate();
								Date ngaySinh = new Date(date.getYear(), date.getMonth(), date.getDate());
								int age = nam - date.getYear();
								if(age>=18 && ngaySinh.getDate()>0 && ngaySinh.getDate()<=31 && ngaySinh.getMonth()>0 && ngaySinh.getMonth()<=12 && ngaySinh.getYear()>0 && ngaySinh.getYear()<nam) { 
									TaiKhoan tk1=new TaiKhoan();
									tk1.setMaTK(maNV);
									tk1.setMatKhau(matKhau);
									
									NhanVien nv = new NhanVien();
									nv.setMaNV(maNV);
									nv.setTenNV(hoTen);
									nv.setChucVu(chucVu);
									nv.setGioiTinh(gioiTinh);
									nv.setNgaySinh(ngaySinh);
									nv.setDiaChi(diaChi);
									nv.setSdt(sdt);
									nv.setCccd(cccd);
									try {
										daoNhanVien.themNV(nv);
										
									}catch (SQLException e) {
										e.printStackTrace();
										JOptionPane.showMessageDialog(this,  "Thêm nhân viên thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
									} 
									try {
										daoTaiKhoan.createTK(tk1);
									} catch (SQLException e2) {
										e2.printStackTrace();
										JOptionPane.showMessageDialog(this,  "Thêm tai khoan thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
									}
									LamMoi();
									modelNhanVien.addRow(new Object[] {
											nv.getMaNV(), nv.getTenNV(), nv.getChucVu(), nv.getNgaySinh(), nv.getGioiTinh(), nv.getDiaChi(), nv.getCccd(), nv.getSdt()
									});
									String mkTK = "\nMật khẩu: "+matKhau;
									JOptionPane.showMessageDialog(this, "Thêm thành công!\nMã tài khoản: "+maNV +mkTK, "Thong bao", JOptionPane.INFORMATION_MESSAGE);
								} else
									JOptionPane.showMessageDialog(this, "Nhân viên làm việc phải trên 18 tuổi!", "Thông báo", JOptionPane.WARNING_MESSAGE);
							} else
								JOptionPane.showMessageDialog(this, "Căn cước công dân đã đăng ký", "Thông báo", JOptionPane.ERROR_MESSAGE);
						} else 
							JOptionPane.showMessageDialog(this, "Số điện thoại đã đăng ký", "Thông báo", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(this,  "Mã nhân viên đã tồn tại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else {
			JOptionPane.showMessageDialog(this,  "Mã nhân viên có định dạng NV00x!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	@SuppressWarnings({ "deprecation"})
	private void findNV() {
		String maNV = txtMaNV.getText();
		String hoTen = txtTenNV.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		String chucVu = cbbChucVu.getSelectedItem().toString();
		String cccd = txtCCCD.getText();
		String gioiTinh = cbbGioiTinh.getSelectedItem().toString();
		String ngaySinh;
		


		java.util.Date date = chooserNgaySinh.getDate();
		if(date == null) {
			ngaySinh = new String("");
		}else {
			Date ngaysinh = new Date(date.getYear(), date.getMonth(), date.getDate());
			ngaySinh = dfNgaySinh.format(ngaysinh);
		}
		ArrayList<NhanVien> lstNV = null;
		lstNV = daoNhanVien.TimKiemNhanVien(maNV, hoTen, chucVu, ngaySinh, gioiTinh, diaChi, cccd, sdt);
		
		removeDanhSachNV(modelNhanVien);
		for(NhanVien NV : lstNV) {
			modelNhanVien.addRow(new Object[] {
					NV.getMaNV(), NV.getTenNV(), NV.getChucVu(), NV.getNgaySinh(), NV.getGioiTinh(), NV.getDiaChi(), NV.getCccd(), NV.getSdt()
			});
		}
		
		if(lstNV.size()==0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin nhân viên" , "Thông báo", JOptionPane.ERROR_MESSAGE);
			txtMaNV.requestFocus();
			txtMaNV.selectAll();
		}
		
		
	}
	
	//Lam moi 
	@SuppressWarnings("unused")
	private void LamMoi() {
			txtMaNV.setText("");
			txtTenNV.setText("");
			cbbChucVu.setSelectedItem("");
			cbbGioiTinh.setSelectedItem("");
			txtDiaChi.setText("");
			txtSDT.setText("");
			txtCCCD.setText("");
			chooserNgaySinh.setDate(null);
			txtMaNV.requestFocus();
	}
	
	private void XoaNV() {
		int row = tbNhanVien.getSelectedRow();
		if(row>=0) {
			int delete = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa nhân viên này?", "Thông báo", JOptionPane.YES_NO_OPTION);
			if(delete == JOptionPane.YES_OPTION) {
				String maNV = (String) tbNhanVien.getValueAt(row, 0);
				try {
						daoTaiKhoan.xoaTK(maNV);
						daoNhanVien.xoaNV(maNV);
						removeDanhSachNV(modelNhanVien);
						JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				
				}catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Xóa nhân viên thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin nhân viên cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		

		loadDanhSachNV();
		
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	private void updateNV() {
		int row = tbNhanVien.getSelectedRow();
		if(row>=0) {
			int update = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa thông tin nhân viên này?", "Thông báo", JOptionPane.YES_NO_OPTION);
			if(update == JOptionPane.YES_OPTION) {
				NhanVien nv=new NhanVien();
				String maNV = (String) tbNhanVien.getValueAt(row, 0);
				java.util.Date date = chooserNgaySinh.getDate();
				Date ngaySinh=new Date(date.getYear(), date.getMonth(), date.getDate());
				try {
					if(regex.regexTen(txtTenNV) && regex.regexSDT(txtSDT) && regex.regexDiaChi(txtDiaChi) && regex.regexCCCD(txtCCCD)) {
						nv.setTenNV(txtTenNV.getText());
						nv.setChucVu((String) cbbChucVu.getSelectedItem());
						nv.setGioiTinh((String) cbbGioiTinh.getSelectedItem());
						nv.setNgaySinh(ngaySinh);
						nv.setDiaChi(txtDiaChi.getText());
						nv.setSdt(txtSDT.getText());
						nv.setCccd(txtCCCD.getText());
		

						daoNhanVien.capNhatNV(nv, maNV);
						

						removeDanhSachNV(modelNhanVien);
						ArrayList<NhanVien> lstNV = daoNhanVien.getAllDanhSachNV();
						modelNhanVien.setRowCount(0);
						for(NhanVien NV : lstNV) {
							modelNhanVien.addRow(new Object[] {
									NV.getMaNV(), NV.getTenNV(), NV.getChucVu(), NV.getNgaySinh(), NV.getGioiTinh(), NV.getDiaChi(), NV.getCccd(), NV.getSdt()
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
		
		loadDanhSachNV();
	}
}
