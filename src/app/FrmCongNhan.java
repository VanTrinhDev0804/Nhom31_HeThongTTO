package app;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import dao.DAOCongNhan;
import dao.DAOToSanXuat;
import dao.Regex;
import entity.CongNhan;
import entity.ToSanXuat;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class FrmCongNhan extends JPanel implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV;
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
	private DAOCongNhan daoCongNhan;
	private DAOToSanXuat daoToSX;
	private int ngay, thang, nam;
	private JComboBox<Object> cbbTo;
	private DefaultComboBoxModel<Object> ModelCbbTo;
	private JComboBox<Object> cbbGioiTinh;
	private JTable tbCongNhan;
	private DefaultTableModel modelCongNhan;
	private JDateChooser chooserNgaySinh;
	private Regex regex;
	private boolean statusEdit = false;
	
	
	public Panel getPanel() {
		return this.pMain;
	}
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
	
		setLayout(null);
	
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 629);
		add(pMain);
		pMain.setLayout(null);
		
		
		JPanel pNhanSu = new JPanel();
		pNhanSu.setToolTipText(" Thông tin công nhân ");
		pNhanSu.setBorder(new TitledBorder(new LineBorder(new Color(0, 0 ,0), 5, true), " THÔNG TIN CÔNG NHÂN     ", TitledBorder.LEFT, TitledBorder.TOP, null, Color.ORANGE ));
		pNhanSu.setBackground(Color.WHITE);
		pNhanSu.setBounds(10, 10, 350, 600);
		pMain.add(pNhanSu);
		pNhanSu.setLayout(null);
		
		JLabel lblSubMaCN = new JLabel("Mã Công Nhân: ");
		lblSubMaCN.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSubMaCN.setBounds(27, 27, 130, 26);
		pNhanSu.add(lblSubMaCN);
		txtMaCN = new JTextField();
		txtMaCN.setBackground(new Color(255, 255, 255));
		txtMaCN.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtMaCN.setBorder(new LineBorder(new Color(0, 0 , 0), 1, true));;
		txtMaCN.setBounds(150, 27, 180, 30);
		pNhanSu.add(txtMaCN);
		
		
		JLabel lblSubTenCN = new JLabel("Tên công nhân: ");
		lblSubTenCN.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSubTenCN.setBounds(27, 72, 130, 26);
		pNhanSu.add(lblSubTenCN);
		txtTenCN = new JTextField();
		txtTenCN.setBackground(new Color(255, 255, 255));
		txtTenCN.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtTenCN.setBorder(new LineBorder(new Color(0, 0 , 0), 1, true));;
		txtTenCN.setBounds(150, 72, 180, 30);
		pNhanSu.add(txtTenCN);
		
		
		JLabel lblSubTo = new JLabel("Mã tổ: ");
		lblSubTo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSubTo.setBounds(27, 117, 130, 26);
		pNhanSu.add(lblSubTo);
		
		ModelCbbTo = new DefaultComboBoxModel<>();
		ModelCbbTo.addElement("");
		ArrayList<ToSanXuat> lstTSX = daoToSX.getDSToSanXuat();
		for(ToSanXuat TSX : lstTSX) {
			ModelCbbTo.addElement(TSX.getMaTo());
		}
		cbbTo = new JComboBox<Object>(ModelCbbTo);
		cbbTo.setToolTipText("Chọn chức vụ");
		cbbTo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cbbTo.setBackground(Color.WHITE);
		cbbTo.setBorder(new LineBorder(new Color(0, 0 ,0), 1, true));
		cbbTo.setBounds(150, 117, 180, 30);
		pNhanSu.add(cbbTo);
		
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
		
		String col [] = {"Mã CN", "Họ và Tên", "Mã tổ","Ngày sinh","Giới tính", "Địa chỉ","CCCD","SĐT"};
		modelCongNhan = new DefaultTableModel(col,0);
		
		tbCongNhan = new JTable(modelCongNhan);
		tbCongNhan.setShowHorizontalLines(true);
		tbCongNhan.setShowGrid(true);
		tbCongNhan.setBackground(Color.WHITE);
		tbCongNhan.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		JTableHeader tbHeader = tbCongNhan.getTableHeader();
		tbHeader.setBackground(new Color(255,255,255));
		tbHeader.setForeground(new Color(0,0,0));
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
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		
		tbCongNhan.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		tbCongNhan.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tbCongNhan.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		JScrollPane spCongNhan = new JScrollPane(tbCongNhan);
		spCongNhan.setToolTipText("Danh sách công nhân");
		spCongNhan.setBounds(370,77 , 880, 530);
		spCongNhan.setBorder(new LineBorder(new Color(0, 0 ,0), 5, true));
		spCongNhan.setBackground(new Color(164, 44, 167));
		pMain.add(spCongNhan);
		
		btnThemCN = new FixButton("Thêm");
		btnThemCN.setForeground(Color.black);
		btnThemCN.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnThemCN.setBackground(new Color(57, 210, 247));
		btnThemCN.setBounds(22, 407, 140, 50);
		Icon iconThemCN = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		btnThemCN.setIcon(iconThemCN);
		pNhanSu.add(btnThemCN);
		
		btnXoaCN = new FixButton("Xóa");
		btnXoaCN.setForeground(Color.black);
		btnXoaCN.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnXoaCN.setBackground(new Color(57, 210, 247));
		btnXoaCN.setBounds(187, 407, 140, 50);
		//Icon iconXoaNV = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		//btnXoaNV.setIcon(iconXoaNV);
		pNhanSu.add(btnXoaCN);
		
		btnSuaCN = new FixButton("Sửa");
		btnSuaCN.setForeground(Color.black);
		btnSuaCN.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnSuaCN.setBackground(new Color(57, 210, 247));
		btnSuaCN.setBounds(22, 467, 140, 50);
		//Icon iconXoaNV = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		//btnXoaNV.setIcon(iconXoaNV);
		pNhanSu.add(btnSuaCN);
		
		btnLamMoiCN = new FixButton("Làm mới");
		btnLamMoiCN.setForeground(Color.black);
		btnLamMoiCN.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnLamMoiCN.setBackground(new Color(57, 210, 247));
		btnLamMoiCN.setBounds(187, 467, 140, 50);
		//Icon iconXoaNV = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		//btnXoaNV.setIcon(iconXoaNV);
		pNhanSu.add(btnLamMoiCN);
		
		btnTimKiemCN = new FixButton("Tim");
		btnTimKiemCN.setForeground(Color.black);
		btnTimKiemCN.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTimKiemCN.setBackground(new Color(57, 210, 247));
		btnTimKiemCN.setBounds(22, 527, 140, 50);
		//Icon iconXoaNV = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		//btnXoaNV.setIcon(iconXoaNV);
		pNhanSu.add(btnTimKiemCN);
		
		btnInDS = new FixButton("In");
		btnInDS.setForeground(Color.black);
		btnInDS.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnInDS.setBackground(new Color(57, 210, 247));
		btnInDS.setBounds(187, 527, 140, 50);
		//Icon iconXoaNV = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		//btnXoaNV.setIcon(iconXoaNV);
		loadDanhSachCN();
		pNhanSu.add(btnInDS);
		
		btnThemCN.addActionListener(this);
		btnXoaCN.addActionListener(this);
		btnLamMoiCN.addActionListener(this);
		btnSuaCN.addActionListener(this);
		btnInDS.addActionListener(this);
		btnTimKiemCN.addActionListener(this);
		
		tbCongNhan.addMouseListener(this);
		
	
	}
	private void loadDanhSachCN()  {
		//clearTable();
		removeDanhSachNV(modelCongNhan);
		ArrayList<CongNhan> lstCN = daoCongNhan.getDSCongNhan();
		for(CongNhan CN : lstCN) {
			modelCongNhan.addRow(new Object[] {
					CN.getMaCN(), CN.getTenCN(), CN.getToSanXuat().getMaTo(), CN.getNgaySinh(), CN.getGioiTinh(), CN.getDiaChi(), CN.getCccd(), CN.getSdt()
			});
		}
	}
	private void removeDanhSachNV(DefaultTableModel defaultTableModel) {
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
			JOptionPane.showMessageDialog(this,  "Mã công nhân có định dạng NV00x!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	@SuppressWarnings({ "deprecation"})
	private void findCN() {
		String maCN = txtMaCN.getText();
		String hoTen = txtTenCN.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		String maTo = cbbTo.getSelectedItem().toString();
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
		ArrayList<CongNhan> lstCN = null;
		lstCN = daoCongNhan.TimKiemCongNhan(maCN, hoTen, maTo, ngaySinh, gioiTinh, diaChi, cccd, sdt);
		
		removeDanhSachNV(modelCongNhan);
		for(CongNhan cn : lstCN) {
			modelCongNhan.addRow(new Object[] {
					cn.getMaCN(), cn.getTenCN(), cn.getToSanXuat().getMaTo(), cn.getNgaySinh(), cn.getGioiTinh(), cn.getDiaChi(), cn.getCccd(), cn.getSdt()
			});
		}
		
		if(lstCN.size()==0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin công nhân" , "Thông báo", JOptionPane.ERROR_MESSAGE);
			txtMaCN.requestFocus();
			txtMaCN.selectAll();
		}
		
	}
	
	//Lam moi 
	@SuppressWarnings("unused")
	private void LamMoi() {
			txtMaCN.setText("");
			txtTenCN.setText("");
			cbbTo.setSelectedItem("");
			cbbGioiTinh.setSelectedItem("");
			txtDiaChi.setText("");
			txtSDT.setText("");
			txtCCCD.setText("");
			chooserNgaySinh.setDate(null);
			txtMaCN.requestFocus();
	}
	
	private void XoaNV() {
		int row = tbCongNhan.getSelectedRow();
		if(row>=0) {
			int delete = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa công nhân này?", "Thông báo", JOptionPane.YES_NO_OPTION);
			if(delete == JOptionPane.YES_OPTION) {
				String maCN = (String) tbCongNhan.getValueAt(row, 0);
				try {
						daoCongNhan.xoaCN(maCN);
						removeDanhSachNV(modelCongNhan);
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
					

						removeDanhSachNV(modelCongNhan);
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
	
	private void SapXepModelTable(){
		ArrayList<CongNhan> lstCN = new ArrayList<CongNhan>();
		int sodong = modelCongNhan.getRowCount();
		if(sodong == 0)
			return;
		CongNhan cn = new CongNhan();
		for (int i = 1; i < sodong; i++) {
			String macn = (String) tbCongNhan.getValueAt(i, 0);
			String hoten = (String) tbCongNhan.getValueAt(i, 1);
			String mato = (String) tbCongNhan.getValueAt(i, 2);
			Date ngaysinh =  (Date) tbCongNhan.getValueAt(i, 3);
			String gioitinh = (String) tbCongNhan.getValueAt(i, 4);
			String diachi = (String) tbCongNhan.getValueAt(i, 5);
			String cccd = (String) tbCongNhan.getValueAt(i, 6);
			String sdt = (String) tbCongNhan.getValueAt(i, 7);
			ToSanXuat toSanXuat = daoToSX.getToSXfromMaToSX(mato);
			cn.setMaCN(macn);
			cn.setTenCN(hoten);
			cn.setToSanXuat(toSanXuat);
			cn.setNgaySinh(ngaysinh);
			cn.setGioiTinh(gioitinh);
			cn.setDiaChi(diachi);
			cn.setCccd(cccd);
			cn.setSdt(sdt);
			
			lstCN.add(cn);
		}
		
	}
}
