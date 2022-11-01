package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FileDialog;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

import java.awt.BorderLayout;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mindfusion.drawing.Colors;
import connection.ConnectDB;
import dao.DAOCongDoan;
import dao.DAOPhieuChamCong;
import dao.DAOToSanXuat;
import dao.Regex;
import entity.CongDoan;
import entity.ToSanXuat;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

	
public class FrmThongKe extends JFrame implements KeyListener, ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panel pMain;
	private JTable tableTK;
	private DefaultTableModel modelTK;
	private JLabel txt_TongTien;
	private JLabel txt_TongSP;
	private FixButton btnThongKe;
	private FixButton btnXuatFile;
	private FixButton btnTongTien;
	private JTextField txtTK;
	private FixButton btnTim;
	private FixButton btnTongSP;
	private JRadioButton radTheoSLTP;
	private JRadioButton radTheoThanhTien;
	private JComboBox<String> cbbSapXep;

	
	
	
	
	private DAOToSanXuat daoToSanXuat;
	private DAOCongDoan daoCongDoan;
	private DAOPhieuChamCong daoCCCN;
	
	public Panel getFrmThongKe() {
		return this.pMain;
	}
	
	public FrmThongKe() {
		setTitle("Thống kê");
		getContentPane().setLayout(null);
		getContentPane().setSize(1281,629);
		pMain= new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1267, 629);
		pMain.setLayout(null);
		getContentPane().add(pMain);
		
		
		//connectDB
				try {
					ConnectDB.getinstance().connect();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
		//DAO
				daoToSanXuat = new DAOToSanXuat();
				daoCongDoan = new DAOCongDoan();		
				daoCCCN = new DAOPhieuChamCong();
		
		JPanel pTuyChinh = new JPanel();
		pTuyChinh.setBackground(Color.WHITE);
		pTuyChinh.setBounds(2, 0, 1264, 80);
		pTuyChinh.setBorder(new LineBorder(new Color(164, 44, 167), 2, true));
		
		pMain.add(pTuyChinh);
		pTuyChinh.setLayout(null);
		
		btnThongKe = new FixButton("Thống kê");
		btnThongKe.setIcon(new ImageIcon("D:\\PTUDProject\\Nhom31_HeThongTTO\\data\\icon\\iconThongKe.png"));
		btnThongKe.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThongKe.setForeground(new Color(0, 0, 0));
		btnThongKe.setBackground(new Color(255, 255, 0));
		btnThongKe.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThongKe.setBounds(1100, 7, 150, 30);
		pTuyChinh.add(btnThongKe);
		
		btnXuatFile = new FixButton("Xuất file");
		btnXuatFile.setIcon(new ImageIcon("D:\\PTUDProject\\Nhom31_HeThongTTO\\data\\icon\\iconExcel.png"));
		btnXuatFile.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnXuatFile.setForeground(new Color(0, 0, 0));
		btnXuatFile.setBackground(new Color(0, 128, 0));
		btnXuatFile.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnXuatFile.setBounds(1100, 45, 150, 30);
		pTuyChinh.add(btnXuatFile);
		
		JPanel pSapxep = new JPanel();
		pSapxep.setBorder(new LineBorder(new Color(164, 44, 167), 2));
		pSapxep.setBounds(10, 11, 751, 62);
		pSapxep.setBackground(Color.WHITE);
		pTuyChinh.add(pSapxep);
		pSapxep.setLayout(null);
		
		radTheoSLTP = new JRadioButton("Số lượng sản phẩm");
		radTheoSLTP.setBounds(300, 18, 161, 27);
		radTheoSLTP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		radTheoSLTP.setSelected(true);
		radTheoSLTP.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoSLTP.setBackground(Color.WHITE);
		pSapxep.add(radTheoSLTP);
		
		radTheoThanhTien = new JRadioButton("Thành tiền");
		radTheoThanhTien.setBounds(638, 16, 109, 30);
		radTheoThanhTien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		radTheoThanhTien.setSelected(true);
		radTheoThanhTien.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoThanhTien.setBackground(Color.WHITE);
		pSapxep.add(radTheoThanhTien);
		
		cbbSapXep = new JComboBox<String>();
		cbbSapXep.setBounds(10, 17, 101, 28);
		cbbSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cbbSapXep.setBackground(Color.WHITE);
		String cbSort[] = { "Tăng dần", "Giảm dần" };
		for (int i = 0; i < cbSort.length; i++) {
			cbbSapXep.addItem(cbSort[i]);
		}
		pSapxep.add(cbbSapXep);
		
		txtTK = new JTextField();
		txtTK.setText("Tìm theo mã tổ sản xuất");
		txtTK.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTK.setForeground(Colors.LightGray);
		txtTK.setBorder(new LineBorder(new Color(114, 23, 153), 2, true));
		txtTK.setBounds(800, 10, 265, 30);
		txtTK.addFocusListener(new FocusAdapter() { // place holder
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTK.getText().equals("Tìm theo mã sản phẩm")) {
					txtTK.setFont(new Font("SansSerif", Font.PLAIN, 15));
					txtTK.setForeground(Color.BLACK);
					txtTK.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTK.getText().equals("")) {
					txtTK.setFont(new Font("SansSerif", Font.ITALIC, 15));
					txtTK.setForeground(Colors.LightGray);
					txtTK.setText("Tìm theo mã sản phẩm");
		
				}
			}
		});
		pTuyChinh.add(txtTK);
		txtTK.setColumns(10);
		
		btnTim = new FixButton("Tìm");
		btnTim.setIcon(new ImageIcon("D:\\PTUDProject\\Nhom31_HeThongTTO\\data\\icon\\iconTim.png"));
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBounds(800, 45, 265, 30);
		btnTim.setBackground(new Color(114, 23, 153));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		pTuyChinh.add(btnTim);
		
		JPanel pThongKe = new JPanel();
		pThongKe.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		pThongKe.setBackground(Color.WHITE);
		pThongKe.setBounds(2, 91, 1264, 450);
		pMain.add(pThongKe);
		pThongKe.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollTKTable = new JScrollPane();
		scrollTKTable.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		scrollTKTable.setBackground(Color.WHITE);
		pThongKe.add(scrollTKTable, BorderLayout.CENTER);
		
		String col[]= {"Mã tổ", "Tên tổ", "Tên sản phẩm", "Số lượng thành phẩm", "Thành tiền"};
		modelTK = new DefaultTableModel(col,0);
		
		tableTK = new JTable(modelTK);
		tableTK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableTK.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableTK.setShowHorizontalLines(true);
		tableTK.setShowGrid(true);
		tableTK.setBackground(Color.WHITE);
		tableTK.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableTK.setSelectionBackground(new Color(164, 44, 167, 30));
		tableTK.setSelectionForeground(new Color(114, 23, 153));
		tableTK.setRowHeight(30);
		
		JTableHeader tbHeader = tableTK.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		tableTK.getColumnModel().getColumn(0).setPreferredWidth(130);
		tableTK.getColumnModel().getColumn(1).setPreferredWidth(130);
		tableTK.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableTK.getColumnModel().getColumn(3).setPreferredWidth(300);
		tableTK.getColumnModel().getColumn(4).setPreferredWidth(500);
		
		DefaultTableCellRenderer rightRenderer=new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		tableTK.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
		tableTK.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
		tableTK.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		tableTK.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tableTK.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		
		scrollTKTable.setViewportView(tableTK);
		
		JPanel pTong = new JPanel();
		pTong.setBorder(new LineBorder(new Color(164, 44, 167), 2));
		pTong.setBounds(2, 550, 1264, 66);
		pMain.add(pTong);
		pTong.setLayout(null);
		
		btnTongTien = new FixButton("Tổng doanh thu");
		btnTongTien.setForeground(Color.BLACK);
		btnTongTien.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTongTien.setIcon(new ImageIcon("D:\\PTUDProject\\Nhom31_HeThongTTO\\data\\icon\\iconDoanhThu.png"));
		btnTongTien.setBackground(Color.YELLOW);
		btnTongTien.setBounds(10, 25, 163, 27);
		pTong.add(btnTongTien);
		
		txt_TongTien = new JLabel();
		txt_TongTien.setBounds(177, 22, 200, 30);
		txt_TongTien.setFont(new Font("SansSerif", Font.BOLD, 14));
		txt_TongTien.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pTong.add(txt_TongTien);
		
		btnTongSP = new FixButton("Tổng thành phẩm");
		btnTongSP.setForeground(new Color(0, 0, 0));
		btnTongSP.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTongSP.setIcon(new ImageIcon("D:\\PTUDProject\\Nhom31_HeThongTTO\\data\\icon\\iconSanPham.png"));
		btnTongSP.setBackground(new Color(0, 0, 255));
		btnTongSP.setBounds(700, 25, 173, 27);
		pTong.add(btnTongSP);
		
		txt_TongSP = new JLabel();
		txt_TongSP.setBounds(875, 22, 200, 30);
		txt_TongSP.setFont(new Font("SansSerif", Font.BOLD, 14));
		txt_TongSP.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0))); 
		pTong.add(txt_TongSP);
		
		//action
		tableTK.addMouseListener(this);
		cbbSapXep.addActionListener(this);
		radTheoSLTP.addActionListener(this);
		radTheoThanhTien.addActionListener(this);
		txtTK.addKeyListener(this);
		btnTim.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnXuatFile.addActionListener(this);
		btnTongSP.addActionListener(this);
		btnTongTien.addActionListener(this);
		
		//Set enable
		btnTongSP.setEnabled(false);
		btnTongTien.setEnabled(false);
		btnXuatFile.setEnabled(false);
	}
	//Xuất file
	public void xuatExcel() throws IOException {
		XuatExcels xuat = new XuatExcels();
		FileDialog fileDialog  = new FileDialog(this, "Danh sách thống kê", FileDialog.SAVE);
		fileDialog.setFile("Danh sách thống kê");
		fileDialog .setVisible(true);
		String name = fileDialog.getFile();
		String fileName = fileDialog.getDirectory() + name;

		if (name == null) 
			return;
			
		if(!fileName.endsWith(".xlsx")||!fileName.endsWith(".xls")) 
			fileName += ".xlsx";
			
		xuat.xuatTable(tableTK, "DANH SÁCH THỐNG KÊ", fileName);
	}
	
//	clearTable
	public void clearTable(DefaultTableModel defaultTableModel) {
		while (tableTK.getRowCount() > 0) {
			modelTK.removeRow(0);
			
		}
	}
	
	
	//Tìm
//	private void timTheoMaTo() {
//		ArrayList<ToSanXuat> lsTo = null;
//		String input = txtTK.getText().trim();
//		String regexMaTo = "((T|t)[0-9]{3})";
//		if(!txtTK.equals("")) {
//			if(input.matches(regexMaTo)) {
//				
//				lsTo = daoToSanXuat.getDSToSanXuatTheoMaT(input);
//				thongKeLuong();
//				
//				if(lsTo.size() == 0) {
//					JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin tìm kiếm phù hợp!");
//					thongKeLuong();
//				}
//			}
//		}
//	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o.equals(btnXuatFile)) {
			try {
				xuatExcel();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}
		
		if(o.equals(btnTim)) {
//			timTheoMaTo();
		}
		
		if(o.equals(btnThongKe)) {
			thongKeLuong();
			btnTongSP.setEnabled(true);
			btnTongTien.setEnabled(true);
			btnXuatFile.setEnabled(true);
			
		}
		
		if(o.equals(btnTongTien)) {
			tongDoanhThu();
		}
		
		if(o.equals(btnTongSP)) {
			tongSanPham();
		}
		
//		if(cbbSapXep.getSelectedItem()=="Tăng dần") {
//			if(o.equals(radTheoSLTP)) {
//				tangDanSL();
//			}
//			if(o.equals(radTheoThanhTien)) {
//				tangDanGia();
//			}
//		}
//		
//		if(cbbSapXep.getSelectedItem()=="Giảm dần") {
//			if(o.equals(radTheoSLTP)) {
//				giamDanSL();
//			}
//			if(o.equals(radTheoThanhTien)) {
//				giamDanTien();
//			}
//		}
	}
	


	//Giảm dần
//	private void giamDanTien() {
//		
//	}
//
//	private void giamDanSL() {
//		
//	}
//	
//Tăng dần
//	private void tangDanGia(CongDoan cd) {
//		clearTable(modelTK);
//		ArrayList<ToSanXuat> lstTSX = daoToSanXuat.getDSToSanXuat();
//		
//		for(ToSanXuat tsx: lstTSX) {
//			ArrayList<CongDoan> lstCD = daoCongDoan.sortTheoGiaSX("");
//			int soTPTo = daoCCCN.getSoSPSXTheoTo(tsx.getMaTo());
//
//			for(CongDoan CD: lstCD) {
//				float tongTienTo = soTPTo * daoCongDoan.getGiaTP(CD.getMaCD());
//				modelTK.addRow(new Object [] {tsx.getMaTo(), tsx.getTenTo(), 
//						CD.getTenThanhPham(), soTPTo, tongTienTo} );
//				modelTK.getRowCount();				
//			}
//		}
//}
//
//	private void tangDanSL() {
//		
//	}
//	
//	private void tangDanTien() {
//		
//	}

	//Tính tổng sản phẩm
	private void tongSanPham() {
		int tongSP = 0;
		ArrayList<ToSanXuat> lstTSX = daoToSanXuat.getDSToSanXuat();
		
		for(ToSanXuat tsx: lstTSX) {
			int soTPTo = daoCCCN.getSoSPSXTheoTo(tsx.getMaTo());
			tongSP += soTPTo;
		}
		txt_TongSP.setText(String.valueOf(tongSP) + " chiếc");
		
	}

	//Tính tổng doanh thu
	private void tongDoanhThu() {
		float tongDT = 0;
		ArrayList<ToSanXuat> lstTSX = daoToSanXuat.getDSToSanXuat();
	
		for(ToSanXuat tsx: lstTSX) {
			ArrayList<CongDoan> lstCD = daoCongDoan.getCongDoanTheoMaCD(tsx.getMaCD());
			int soTPTo = daoCCCN.getSoSPSXTheoTo(tsx.getMaTo());
			
			for(CongDoan cd: lstCD) {
				float tongTienTo = soTPTo * daoCongDoan.getGiaTP(cd.getMaCD());
				tongDT += tongTienTo;
			}
		}
		txt_TongTien.setText(String.valueOf(tongDT) + " vnđ");
		
	}

	//load bảng thống kê
	private void thongKeLuong() {
		clearTable(modelTK);
		
		ArrayList<ToSanXuat> lstTSX = daoToSanXuat.getDSToSanXuat();
		
		for(ToSanXuat tsx: lstTSX) {
			ArrayList<CongDoan> lstCD = daoCongDoan.getCongDoanTheoMaCD(tsx.getMaCD());
			int soTPTo = daoCCCN.getSoSPSXTheoTo(tsx.getMaTo());

			for(CongDoan cd: lstCD) {
				float tongTienTo = soTPTo * daoCongDoan.getGiaTP(cd.getMaCD());
				modelTK.addRow(new Object [] {tsx.getMaTo(), tsx.getTenTo(), cd.getTenThanhPham(), soTPTo, tongTienTo} );
				modelTK.getRowCount();				
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}

