package app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.ImageGraphicAttribute;
import java.io.Console;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf;

import connection.ConnectDB;
import custom.FixButton;
import custom.FixRenderJList;
import custom.FixRenderTree;
import dao.DAOCongNhan;
import dao.DAONhanVien;
import dao.DAOPhieuChamCong;
import dao.DAOToSanXuat;
import entity.ChamCongCN;
import entity.ChamCongNV;
import entity.CongNhan;
import entity.NhanVien;
import entity.ToSanXuat;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.ListSelectionModel;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;

public class FrmChamCongNV extends JFrame implements ActionListener, TreeSelectionListener,MouseListener  {
	
	private Panel pMain;
	private DefaultMutableTreeNode root, nodeTo, nodeCN;
//	DAO
	private DAOToSanXuat daoTSX;
	private DAONhanVien daoNhanVien;
	private DAOPhieuChamCong daoPhieuChamCong;
	

	private JTextField txtTimCN;
	private JPanel pNhapThongTin;
	private JLabel lblNhapThongTin;
	private JLabel lblHoTen;
	private JTextField txtHoTenNV;
	private JLabel lblChucVu;
	private JTextField txtChucVu;
	private JLabel lblNgay;
	private JDateChooser chooserNgay;
	private FixButton btnLuuChamCong, btnTimNV;
	private JScrollPane scrollCNTable;
	private JTable tableNV;
	private DefaultTableModel modelChamCongNV;
	
	
	private SimpleDateFormat dfNgay;

	private Date dNow;
	private LocalDate now;
	private int ngay;
	private int thang;
	private int nam;
	private JButton btnResetList;
	private DefaultTreeModel defaultTreeModel;
	private JScrollPane pListNV;
	private JList JlistNV;
	private JTextField txtMaNV;
	private JTextField txtSDT;
	
	public Panel getFrmChamCong() {
		return pMain;
	}
	
	
	
	public FrmChamCongNV() {
		
		getContentPane().setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1278, 629);
		pMain.setLayout(null);
		getContentPane().add(pMain);
		
		
		/**
		 * Kết nối database
		 */
		try {
			ConnectDB.getinstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
//		Khai bao dao

		daoTSX = new DAOToSanXuat();
		daoNhanVien = new DAONhanVien();
		daoPhieuChamCong = new DAOPhieuChamCong();
		
		/**
		 * Định dạng ngày, lương trong bảng
		 */
		dfNgay=new SimpleDateFormat("dd/MM/yyyy");
		
		
//		ngay hien tai
		now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue();
		nam = now.getYear();
		
		dNow = new Date(nam-1900,thang-1,ngay);
		
		
		scrollCNTable = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollCNTable.setToolTipText("Danh sách thông tin nhân viên");
		scrollCNTable.setBorder(new LineBorder(new Color(164, 44, 167), 2, true));
		scrollCNTable.setBackground(Color.WHITE);
		scrollCNTable.setBounds(368, 156, 823, 428);
		pMain.add(scrollCNTable);
		
		String col[] = {"Mã ","Nhân Viên" , "Chức Vụ", "Số Điện Thoại",  "Ngày Vắng"};
		modelChamCongNV = new DefaultTableModel(col, 0);

		tableNV = new JTable(modelChamCongNV);
		tableNV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableNV.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableNV.setShowHorizontalLines(true); 
		tableNV.setShowGrid(true);
		tableNV.setBackground(Color.WHITE);
		tableNV.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableNV.setSelectionBackground(new Color(164, 44, 167, 30));
		tableNV.setSelectionForeground(new Color(114, 23, 153));
		tableNV.setRowHeight(30);
		tableNV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableNV.setToolTipText("Chọn nhân viên để thực hiện chức năng");
		
		
		JTableHeader tbHeader = tableNV.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		tbHeader.setToolTipText("Danh sách Ngày công nhân viên");

		tableNV.getColumnModel().getColumn(0).setPreferredWidth(120); 
		tableNV.getColumnModel().getColumn(1).setPreferredWidth(220);
		tableNV.getColumnModel().getColumn(2).setPreferredWidth(120); 
		tableNV.getColumnModel().getColumn(3).setPreferredWidth(180); 
		tableNV.getColumnModel().getColumn(4).setPreferredWidth(180); 
		


		DefaultTableCellRenderer rightRenderer=new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

		scrollCNTable.setViewportView(tableNV);
		
		
		pNhapThongTin = new JPanel();
		pNhapThongTin.setLayout(null);
		pNhapThongTin.setToolTipText("Các thông tin nhân viên cần nhập");
		pNhapThongTin.setBorder(new LineBorder(new Color(114, 23, 153), 2));
		pNhapThongTin.setBackground(Color.WHITE);
		pNhapThongTin.setBounds(368, 10, 823, 136);
		pMain.add(pNhapThongTin);
	
		
		lblNhapThongTin = new JLabel("Chấm Công Nhân Viên");
		lblNhapThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhapThongTin.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNhapThongTin.setBounds(10, 4, 239, 29);
		pNhapThongTin.add(lblNhapThongTin);
		
		lblHoTen = new JLabel("Họ và tên:");
		lblHoTen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHoTen.setBounds(10, 45, 90, 29);
		pNhapThongTin.add(lblHoTen);
		
		txtHoTenNV = new JTextField();
		txtHoTenNV.setEditable(false);
		txtHoTenNV.setFont(new Font("Arial", Font.ITALIC, 15));
		txtHoTenNV.setColumns(10);
		txtHoTenNV.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtHoTenNV.setBounds(132, 40, 196, 34);
		pNhapThongTin.add(txtHoTenNV);
		
		lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblChucVu.setBounds(6, 83, 98, 29);
		pNhapThongTin.add(lblChucVu);
		
		txtChucVu = new JTextField();
		txtChucVu.setEditable(false);
		txtChucVu.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtChucVu.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtChucVu.setBounds(132, 81, 196, 32);
		pNhapThongTin.add(txtChucVu);
		
		lblNgay = new JLabel("Ngày:");
		lblNgay.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNgay.setBounds(579, 7, 54, 25);
		pNhapThongTin.add(lblNgay);
		
		
		
		
		chooserNgay = new JDateChooser();
		chooserNgay.getCalendarButton().setToolTipText("Chọn ngày sinh");
		chooserNgay.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgay.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserNgay.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserNgay.setDateFormatString("dd/MM/yyyy");
		chooserNgay.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserNgay.setBounds(633, 10, 181, 29);
		chooserNgay.setDate(dNow);
		chooserNgay.setEnabled(false);
		pNhapThongTin.add(chooserNgay);
		
		btnLuuChamCong = new FixButton("Lưu");
		btnLuuChamCong.setText("Chấm Vắng");
		btnLuuChamCong.setForeground(Color.BLACK);
		btnLuuChamCong.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLuuChamCong.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLuuChamCong.setBackground(new Color(57, 210, 247));
		btnLuuChamCong.setBounds(633, 81, 132, 33);
		pNhapThongTin.add(btnLuuChamCong);
		
		JLabel lblMaNv = new JLabel("Mã NV:");
		lblMaNv.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblMaNv.setBounds(353, 45, 75, 29);
		pNhapThongTin.add(lblMaNv);
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtMaNV.setEditable(false);
		txtMaNV.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtMaNV.setBounds(418, 44, 188, 31);
		pNhapThongTin.add(txtMaNV);
		
		JLabel lblSdt = new JLabel("Số ĐT:");
		lblSdt.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSdt.setBounds(353, 83, 75, 29);
		pNhapThongTin.add(lblSdt);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtSDT.setEditable(false);
		txtSDT.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtSDT.setBounds(418, 82, 188, 31);
		pNhapThongTin.add(txtSDT);
		

		
		pListNV = new JScrollPane();
		pListNV.setBorder(new LineBorder(new Color(114, 23, 153), 2, true));
		pListNV.setBackground(Color.WHITE);
		pListNV.setBounds(17, 10, 339, 568);
		pMain.add(pListNV);
		
	
	
		
		

		
		
		
		
		
		
		


		
		JPanel pControlJList = new JPanel();
		pControlJList.setLayout(null);
		pControlJList.setBackground(Color.WHITE);
		pControlJList.setBounds(17, 578, 339, 45);
		pMain.add(pControlJList);
		
		
		txtTimCN = new JTextField();
		txtTimCN.setLocation(23, 6);
		txtTimCN.setSize(136, 30);
		pControlJList.add(txtTimCN);
		txtTimCN.setColumns(10);
		
		btnTimNV = new FixButton("Tìm");
	
		btnTimNV.setLocation(171, 2);
		btnTimNV.setSize(81, 39);
		pControlJList.add(btnTimNV);
		
		btnResetList = new JButton("");
		btnResetList.setIcon(new ImageIcon("data/icon/refres.png"));
		btnResetList.setBounds(264, 6, 55, 35);
		pControlJList.add(btnResetList);
		tableNV.addMouseListener(this);
		
//		LoadDs Nhân Viên
		  LoadDSNV2JList();


//		btn click
		btnLuuChamCong.addActionListener(this);
		btnResetList.addActionListener(this);

	
	}



	private void LoadDSNV2JList() {
		DefaultListModel<NhanVien> item = new DefaultListModel<NhanVien>();
		 ArrayList<NhanVien> listNV = daoNhanVien.getAllDanhSachNV();
		 for(NhanVien nVien : listNV) {
			 item.addElement(nVien);
			 
		 }
		 
		 JlistNV= new JList<NhanVien>(item);	
		 JlistNV.setCellRenderer(new FixRenderJList());
		 JlistNV.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		 pListNV.setViewportView(JlistNV);
		 
		 
		 JlistNV.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				NhanVien nVien = (NhanVien) JlistNV.getSelectedValue();
					
				txtHoTenNV.setText(nVien.getTenNV());
				txtMaNV.setText(nVien.getMaNV());
				txtSDT.setText(nVien.getSdt());
				txtChucVu.setText(nVien.getChucVu());
				
				if(checkChamCong(nVien.getMaNV())) {
					btnLuuChamCong.setText("Chấm Vắng");
					btnLuuChamCong.setBackground(new Color(57, 210, 247));
					btnLuuChamCong.setEnabled(true);
					btnLuuChamCong.setVisible(true);
				}
				else {
					btnLuuChamCong.setVisible(false);
				}
				
				loadListChamCong(nVien);
				
			}
		});
		 
		
		 
		 
	}
	
	protected boolean checkChamCong(String ma) {
		ArrayList<ChamCongNV> lisctCCNV = daoPhieuChamCong.getDSChamCongNVTungNV(ma);
		java.util.Date date = chooserNgay.getDate();
		
		Date ngayFormat=new Date(date.getYear(), date.getMonth(), date.getDate());

		if(lisctCCNV.size() >=1 ) {
			for (ChamCongNV info : lisctCCNV) {
				if(info.getNgayVang().equals(ngayFormat)) {
					return false;
				}

			}
		}
		return true;
	
		
		
	}

	protected void loadListChamCong(NhanVien nVien) {
		
		removeDSChamCong(modelChamCongNV);
		ArrayList<ChamCongNV> lisctCCNV = daoPhieuChamCong.getDSChamCongNVTungNV(nVien.getMaNV());
		if(lisctCCNV.size() >0 ) {
			for (ChamCongNV info : lisctCCNV) {
				modelChamCongNV.addRow(new Object[] {
						info.getMaNV(), nVien.getTenNV(), nVien.getChucVu(),nVien.getSdt(),dfNgay.format(info.getNgayVang())
					
				
				});
			}
		}
	
	
		
	}
	
	private void removeDSChamCong(DefaultTableModel defaultTableModel) {
		while(tableNV.getRowCount() > 0){
			modelChamCongNV.removeRow(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e. getSource();
		 if(o.equals(btnLuuChamCong)) {
			 luuChamCongNhanVien();
		 }
	}


	private void luuChamCongNhanVien() {

		// TODO Auto-generated method stub
		String ma = txtMaNV.getText();
		String  tenNV = txtHoTenNV.getText();
		String chucVu = txtChucVu.getText();
		String soDT= txtSDT.getText();
		
		
		java.util.Date date = chooserNgay.getDate();
		Date ngayFormat=new Date(date.getYear(), date.getMonth(), date.getDate());
		String ngay =dfNgay.format(ngayFormat);
		
		
		ChamCongNV info = new ChamCongNV();
		
		
		info.setMaNV(ma);
		info.setNgayVang(ngayFormat);
		
	
			if(checkChamCong(ma)) {
				try {
					if(daoPhieuChamCong.themCCNV(info)) {
						JOptionPane.showMessageDialog(this, "Chấm công thành công!", "Thông báo", JOptionPane.ERROR_MESSAGE);
						btnLuuChamCong.setVisible(false);
					}
					else {
						btnLuuChamCong.setVisible(true);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Chấm công thành công!", "Thông báo", JOptionPane.ERROR_MESSAGE);
					btnLuuChamCong.setVisible(true);
				}
				modelChamCongNV.addRow(new Object[] {
						ma, tenNV, chucVu, soDT, ngay
				});
				
			}
			else {
				btnLuuChamCong.setVisible(true);
			}
		
			
		

	}





	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method st	ub
		
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
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}







}