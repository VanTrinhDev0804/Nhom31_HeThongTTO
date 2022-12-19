package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connection.ConnectDB;
import dao.DAOCT_CD_SX_SP;
import dao.DAOCongDoan;
import dao.DAOPhatSinhMa;
import dao.DAOSanPham;
import dao.DAOToSanXuat;
import dao.Regex;
import entity.CT_CD_SX_SP;
import entity.CT_CD_SX_SP;
import entity.CongDoan;
import entity.SanPham;
import entity.ToSanXuat;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class FrmQLToSanXuat extends JPanel implements ActionListener, MouseListener,ItemListener,KeyListener {

	private static final long serialVersionUID = 1L;
	private static final Frame FrmQLToSanXuat = null;
	private String sHeaderMaNV;
	private Date dNgayHienTai;
	private JFrame frm;
	private Panel pMain;
	
	private JTextField txtTim;
	private DAOToSanXuat daoToSanXuat;
	private JButton btnThemTSX;
	private JButton btnXoaTSX;
	private JButton btnLamMoiTSX;
	private JButton btnThanhToan;
	private JButton btnLamMoiHD;
	private DAOCT_CD_SX_SP daoCTCD;
	private DAOCongDoan daoCD;
	private DAOSanPham daoSanPham;
	private DAOPhatSinhMa daoMa;
	private Regex regex;
	private JLabel lblThongTinHD;
	private FixButton btnTim;
	private JPanel pSanPham;
	private JLabel lblMaSP;
	private JLabel lblMaCD;
	private JLabel lblTenCD;
	private JTextField txtTenTo;
	private JTextField txtSoLuongCN;
	private DefaultTableModel modelToSanXuat;
	private JTable tbToSanXuat;
	private FixButton btnExcels;
	
	public Panel getFrmQLToSanXuat() {
		return this.pMain;
	}
	
	public FrmQLToSanXuat(JFrame frm,String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai)  {
		
		this.sHeaderMaNV = sHeaderMaNV;
		this.dNgayHienTai = dNgayHienTai;
		this.frm = frm;
		
//connect database
		try {
			ConnectDB.getinstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//khai bao dao
		daoCTCD = new DAOCT_CD_SX_SP();
		daoToSanXuat = new DAOToSanXuat();
		daoCD = new DAOCongDoan();
		daoSanPham = new DAOSanPham();
		daoMa = new DAOPhatSinhMa();
		regex = new Regex();
		
		IconFontSwing.register(FontAwesome.getIconFont());
		
		setLayout(null);
		
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 629);
		add(pMain);
		pMain.setLayout(null);
		
		JLabel lblSubTimKiem = new JLabel("Tìm kiếm:");
		lblSubTimKiem.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblSubTimKiem.setBounds(438, 10, 83, 35);
		pMain.add(lblSubTimKiem);
		
		txtTim = new JTextField();
		txtTim.setToolTipText("Tìm tổ sản xuất theo mã công đoạn.");
		txtTim.setBounds(520, 12, 410, 33);
		txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 14));
		txtTim.setText("Tìm tổ sản xuất theo mã công đoạn.");
		txtTim.setForeground(Color.lightGray);
		pMain.add(txtTim);
		txtTim.setColumns(10);
		
		btnTim = new FixButton("Tìm"); 
		btnTim.setBackground(new Color(114, 23 ,153));
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBounds(940, 12, 150, 33);
		
		Icon iconTim = IconFontSwing.buildIcon(FontAwesome.SEARCH, 20, new Color(255, 255, 255));
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
		
		JLabel lblHeaderSanPham = new JLabel("Sản Phẩm Đang Sản Xuất");
		lblHeaderSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblHeaderSanPham.setFont(new Font("SansSerif", Font.BOLD, 18));
	
		lblHeaderSanPham.setBounds(10, 16, 280, 26);
		pMain.add(lblHeaderSanPham);
		
		pSanPham = new JPanel();
		pSanPham.setToolTipText("Danh Sách Sản Phẩm Đang Sản Xuất");
		
		pSanPham.setBackground(Color.white);	
		pSanPham.setLayout(new GridLayout(0, 1,0,0));

		JScrollPane scrollPane = new JScrollPane(pSanPham, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(pSanPham);
		scrollPane.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		
		
		scrollPane.setBounds(10, 53, 250, 565);
		pMain.add(scrollPane);
		
		JPanel pCongDoan = new JPanel();
		pCongDoan.setToolTipText("Thông Tin Công Đoạn");
		pCongDoan.setBackground(new Color(171, 192, 238));
		pCongDoan.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Thông tin chung", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pCongDoan.setBounds(519, 53, 738, 64);
		pMain.add(pCongDoan);
		pCongDoan.setLayout(null);
		
		JLabel lblSubSP = new JLabel("Sản Phẩm: ");
		lblSubSP.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubSP.setBounds(10, 9, 200, 26);
		pCongDoan.add(lblSubSP);
		
		lblMaSP = new JLabel("");
		lblMaSP.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblMaSP.setBounds(100, 9, 200, 26);
		pCongDoan.add(lblMaSP);
		
		JLabel lblSubTenCD = new JLabel("Công Đoạn: ");
		lblSubTenCD.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubTenCD.setBounds(10, 33, 90, 26);
		pCongDoan.add(lblSubTenCD);
		
		lblMaCD = new JLabel("");
		lblMaCD.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblMaCD.setBounds(100, 33, 150, 26);
		pCongDoan.add(lblMaCD);
		
		lblTenCD = new JLabel("");
		lblTenCD.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblTenCD.setBounds(230, 33, 240, 26);
		pCongDoan.add(lblTenCD);
		
		JPanel pDichVu = new JPanel();
		pDichVu.setToolTipText("Thông tin sản phẩm");
		pDichVu.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Thông Tin ", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		pDichVu.setBackground(Color.WHITE);
		pDichVu.setBounds(263, 53, 253, 568);
		pMain.add(pDichVu);
		pDichVu.setLayout(null);
		
		
		JLabel lblTenTo = new JLabel("Tên Tổ:");
		lblTenTo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblTenTo.setBounds(10, 25, 102, 26);
		pDichVu.add(lblTenTo);
		
		txtTenTo = new JTextField();
		txtTenTo.setBackground(new Color(255, 255, 255));
		txtTenTo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenTo.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		txtTenTo.setBounds(10, 55, 235, 32);
		pDichVu.add(txtTenTo);
		txtTenTo.setColumns(10);
		
		JLabel lblSoLuongCN = new JLabel("Số Lượng Công Nhân:");
		lblSoLuongCN.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSoLuongCN.setBounds(10, 90, 200, 26);
		pDichVu.add(lblSoLuongCN);
		
		txtSoLuongCN = new JTextField();
		txtSoLuongCN.setBackground(new Color(255, 255, 255));
		txtSoLuongCN.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtSoLuongCN.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		txtSoLuongCN.setBounds(10, 120, 235, 32);
		pDichVu.add(txtSoLuongCN);
		txtSoLuongCN.setColumns(10);
		
		btnThemTSX = new FixButton("Thêm ");
		btnThemTSX.setForeground(Color.black);
		btnThemTSX.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		btnThemTSX.setBackground(new Color(57, 210, 247));
		btnThemTSX.setBounds(10, 400, 235, 33);
		
		Icon iconThemMH = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		btnThemTSX.setIcon(iconThemMH);
		
		pDichVu.add(btnThemTSX);
		
		btnXoaTSX = new FixButton("Xóa Tổ");
		btnXoaTSX.setForeground(Color.WHITE);
		btnXoaTSX.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		btnXoaTSX.setBackground(new Color(0xE91940));
		btnXoaTSX.setBounds(10, 450, 235, 33);
		
		Icon iconXoaMH = IconFontSwing.buildIcon(FontAwesome.TIMES_CIRCLE, 20, new Color(255, 255 ,255));
		btnXoaTSX.setIcon(iconXoaMH);
		pDichVu.add(btnXoaTSX);
		
		btnLamMoiTSX = new FixButton("Làm mới");
		btnLamMoiTSX.setForeground(Color.WHITE);
		btnLamMoiTSX.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoiTSX.setBackground(new Color(114, 23, 153));
		btnLamMoiTSX.setBounds(10, 500, 235, 33);
		
		Icon iconLamMoiMH = IconFontSwing.buildIcon(FontAwesome.REFRESH, 20, new Color(255, 255 ,255));
		btnLamMoiTSX.setIcon(iconLamMoiMH);
		
		pDichVu.add(btnLamMoiTSX);
		
		String col [] = {"Mã Tổ Sản Xuất", "Tên Tổ", "Mã Công Đoạn", "Số Lượng CN"};
		modelToSanXuat = new DefaultTableModel(col,0);
		
		tbToSanXuat = new JTable(modelToSanXuat);
		tbToSanXuat.setShowHorizontalLines(true);
		tbToSanXuat.setShowGrid(true);
		tbToSanXuat.setBackground(Color.WHITE);
		tbToSanXuat.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		JTableHeader tbHeader = tbToSanXuat.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		tbToSanXuat.setSelectionBackground(new Color(164, 44, 167,30));
		tbToSanXuat.setSelectionForeground(new Color(114, 23, 153));
		tbToSanXuat.setRowHeight(30);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		
		tbToSanXuat.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		tbToSanXuat.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		tbToSanXuat.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		
		JScrollPane spToSanXuat = new JScrollPane(tbToSanXuat);
		spToSanXuat.setToolTipText("Danh sách các tổ sản xuất trong công đoạn.");
		spToSanXuat.setBounds(520, 120, 736, 500);
		spToSanXuat.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		spToSanXuat.setBackground(new Color(164, 44, 167));
		pMain.add(spToSanXuat);
	
		loadSanPham();
		
		tbToSanXuat.addMouseListener(this);
		txtTim.addMouseListener(this);

		btnThemTSX.addActionListener(this);
		btnLamMoiTSX.addActionListener(this);
		btnXoaTSX.addActionListener(this);
		btnTim.addActionListener(this);
		btnExcels.addActionListener(this);
	
		txtTenTo.addKeyListener(this);
		txtSoLuongCN.addKeyListener(this);
		txtTim.addKeyListener(this);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
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

	public void loadSanPham() {
		ArrayList<CongDoan> lsCD  = daoCD.getDanhSachCDDangSanXuat();
		for(CongDoan cd : lsCD) {	
			JPanel cdn = new JPanel();
		
			JButton btnCD_SP = new JButton(cd.getSanPham().getMaSP());
			cdn.add(btnCD_SP);
			btnCD_SP.setBackground(new Color(57, 210, 247));
			btnCD_SP.setPreferredSize(new Dimension(70,70));
			btnCD_SP.setBorder(new LineBorder(Color.white,10));

			JLabel lblTenCD_SP = new JLabel (cd.getMaCD() + " " + cd.getTenCD());
			lblTenCD_SP.setFont(new Font("SansSerif", Font.BOLD, 15));
			cdn.setBackground(new Color(248, 238, 248));

			btnCD_SP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Object o = e.getSource();
					if(o.equals(btnCD_SP)) {
						resetAll();
						loadInfo(cd);
					}
					
				}
			});
			cdn.add(lblTenCD_SP);
			pSanPham.add(cdn);
		
		}
	}
	
	@SuppressWarnings("deprecation")
	public void loadInfo(CongDoan cd) {
		lblMaSP.setText(cd.getSanPham().getMaSP());
//		CongDoan cd = daoCD.getCDTheoMaSanPham(sp.getMaSP());
		lblMaCD.setText(cd.getTenCD());
		lblTenCD.setText("-       "+cd.getTenThanhPham());
		loadTable(cd);
	}
	
	public void loadTable(CongDoan cd) {
		clearTable();
		ArrayList<ToSanXuat> lstT = daoToSanXuat.getToSanXuatTheoMaCD(cd.getMaCD());
		for(ToSanXuat infoT : lstT) {
			modelToSanXuat.addRow(new Object[] {
					infoT.getMaTo(), infoT.getTenTo(), infoT.getMaCD(), infoT.getSoLuongCN()
			});
		}
		
	}
	public void loadTableT(ToSanXuat t) {
		clearTable();
		ArrayList<ToSanXuat> lstT = daoToSanXuat.getToSanXuatTheoMaCD(t.getMaCD());
		for(ToSanXuat infoT : lstT) {
			modelToSanXuat.addRow(new Object[] {
					infoT.getMaTo(), infoT.getTenTo(), infoT.getMaCD(), infoT.getSoLuongCN()
			});
		}
		
	}
	/**
	 * Xóa toàn bộ các danh sách trong bảng
	 */
	public void clearTable() {
		while (tbToSanXuat.getRowCount() > 0) {
			modelToSanXuat.removeRow(0);
		}
	}
	
	public int timRow() {		
		
		for(int i =0; i< tbToSanXuat.getRowCount(); i++) {
			if(modelToSanXuat.getValueAt(i, 1).toString().equalsIgnoreCase(txtTenTo.toString())&& modelToSanXuat.getValueAt(i,4).toString().equalsIgnoreCase(txtSoLuongCN.toString()))
				return i;
		}
		return -1;
	}

	public void themToVaoCD() {
		if(lblMaSP.getText() != "") {
			if( txtTenTo.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
			}
			else {
				//if (regex.regexTenTo(txtTenTo)) {
					if(regex.regexSoLuong(txtSoLuongCN)) {
						//CongDoan cd = daoCD.getCDTheoMaSanPham(lblMaSP.getText());
						String maT = daoMa.getMaToSX();
						String tenTo = txtTenTo.getText().trim();
						int soLuong = Integer.parseInt(txtSoLuongCN.getText());
						ToSanXuat to = new ToSanXuat(maT, tenTo, daoCTCD.getCT_CD_SX_SPTheoMa(lblMaCD.getText(), lblMaSP.getText()).getCongDoan().getMaCD(), soLuong);
						daoToSanXuat.ThemTo(to);
						modelToSanXuat.addRow(new Object[] {
								maT,tenTo,daoCTCD.getCT_CD_SX_SPTheoMa(lblMaCD.getText(), lblMaSP.getText()).getCongDoan().getMaCD(),soLuong
						});
					}
				//}
			}
			
		}
		else JOptionPane.showMessageDialog(this, "Vui lòng chọn công đoạn sản phẩm sau đó nhập đầy đủ thông tin.");
	}

	public void xoaTo() {
		int row = tbToSanXuat.getSelectedRow();
		if(!lblMaSP.getText().equalsIgnoreCase("")) {
				//if(timRow() != -1) {
			if (row >= 0) {
				CongDoan cd = daoCD.getCDTheoMaSanPham(lblMaSP.getText());
				String maTo = tbToSanXuat.getValueAt(row, 0).toString();
				
				//daoCTDDP.xoaCTDDP(ddp.getMaDDP(),mh.getMaMatHang());
				daoToSanXuat.deleteToTheoMa(maTo);
				
			
				resetTo();
			
				loadTable(cd);
				JOptionPane.showMessageDialog(null, "Đã xóa tổ sản xuất!", "Thông báo", JOptionPane.OK_OPTION);
			}
			else JOptionPane.showMessageDialog(this, "Vui lòng chọn tổ cần xóa!");
		}
		else JOptionPane.showMessageDialog(this, "Vui lòng chọn công đoạn sản phẩm và chọn tổ cần xóa!");
	}	

	private void xuatExcel() throws IOException {
		XuatExcels xuat = new XuatExcels();
		FileDialog fileDialog = new FileDialog(FrmQLToSanXuat, "Xuất Danh Sách Thông Tin Tổ Xuất Công Đoạn", FileDialog.SAVE);
		fileDialog.setFile("Danh Sách Thông Tin Tổ Xuất Công Đoạn");
		fileDialog .setVisible(true);
		String name = fileDialog.getFile();
		String fileName = fileDialog.getDirectory() + name;

		if (name == null) 
			return;
		
		if(!fileName.endsWith(".xlsx")||!fileName.endsWith(".xls")) 
			fileName += ".xlsx";
		
		xuat.xuatTable(tbToSanXuat, "DDanh Sách Thông Tin Tổ Xuất Công Đoạn", fileName);
	}
	public void timKiem() {
		if(regex.regexTimCongDoan(txtTim)) {
			ToSanXuat t1 = daoToSanXuat.getTSXTheoMaCD(txtTim.getText().toString());
			if(t1!=null)
				loadTableT(t1);
			else 
				JOptionPane.showMessageDialog(this, "Không tìm thấy công đoạn đang sản xuất nào như yêu cầu!");
		}
	}
	
	public void resetTo() {
		txtTenTo.setText("");
		txtSoLuongCN.setText("");
	}
	
	public void resetAll() {
		resetTo();
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 14));
		txtTim.setText("Tìm tổ sản xuất theo mã công đoạn.");
		txtTim.setForeground(Color.lightGray);

		pSanPham.removeAll();
		pSanPham.revalidate();
		pSanPham.repaint();
		loadSanPham();;
		lblMaSP.setText("");
		lblMaCD.setText("");
		lblTenCD.setText("");
		clearTable();	
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnLamMoiTSX)) {
			resetTo();	
			resetAll();
		}
		if(o.equals(btnThemTSX)) {
			themToVaoCD();
		}
		if(o.equals(btnXoaTSX)) {
			xoaTo();
		}
		if(o.equals(btnTim)) {
			timKiem();
		
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
	public void mouseClicked(MouseEvent e) {
		
		Object o = e.getSource();
		
		if(o.equals(tbToSanXuat)) {
			
		int row = tbToSanXuat.getSelectedRow();
		txtTenTo.setText(modelToSanXuat.getValueAt(row,1).toString());
		txtSoLuongCN.setText(modelToSanXuat.getValueAt(row,3).toString());
		}
		if(o.equals(txtTim)) {
			
			txtTim.setFont(new Font("SansSerif", Font.PLAIN, 14));
			txtTim.setText("");
			txtTim.setForeground(Color.black);
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object o = e.getSource();
		int key = e.getKeyCode();
		if(o.equals(txtSoLuongCN)&& key == KeyEvent.VK_ENTER ) {
			btnThemTSX.doClick();
		}
		else if(o.equals(txtTim)&& key == KeyEvent.VK_ENTER ) {
			btnTim.doClick();
		}
		
		
	}

}
