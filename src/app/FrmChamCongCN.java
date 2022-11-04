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
import custom.TblCCModelCtrl;
import dao.DAOCongDoan;
import dao.DAOCongNhan;
import dao.DAOPhieuChamCong;
import dao.DAOToSanXuat;
import entity.ChamCongCN;
import entity.CongDoan;
import entity.CongNhan;
import entity.TemplateCCCN;
import entity.ToSanXuat;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import java.awt.Font;
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
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.ListSelectionModel;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;

public class FrmChamCongCN extends JFrame implements ActionListener, TreeSelectionListener,MouseListener  {
	
	private Panel pMain;
	private JTree JTToSX;
	private DefaultMutableTreeNode root, nodeTo, nodeCN;
//	DAO
	private DAOToSanXuat daoTSX;
	private DAOCongNhan daoCongNhan;
	private DAOPhieuChamCong daoPhieuChamCong;
	private DAOCongDoan daoCongDoan;

	private JTextField txtTimCN;
	private JPanel pNhapThongTin;
	private JLabel lblNhapThongTin;
	private JLabel lblNgay;
	private JDateChooser chooserNgay;
	private FixButton btnLuuChamCong , btnTimCN;
	private JScrollPane scrollCNTable;
	private JTable tableCN;
	private DefaultTableModel modelChamCong;
	
	
	private SimpleDateFormat dfNgay;
	private DecimalFormat dfLuong;
	private Date dNow;
	private LocalDate now;
	private int ngay;
	private int thang;
	private int nam;
	private JButton btnResetList;
	private DefaultTreeModel defaultTreeModel;
	private JScrollPane pTreeToSX;
	private JTable tableCCCN;
	private DefaultTableModel modelActionChamCong;
	private JComboBox<String> comboBox;
	private Component scrollPane;
	private TblCCModelCtrl modelCC;
//	check load cc
	private  ToSanXuat toSXSelected;
	private  CongNhan congNhanSelected;
	private boolean statusTOSXSelected;
	
	public Panel getFrmChamCong() {
		return pMain;
	}
	
	
	
	public FrmChamCongCN() {
		
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
		daoCongNhan = new DAOCongNhan();
		daoPhieuChamCong = new DAOPhieuChamCong();
		daoCongDoan = new DAOCongDoan();
		/**
		 * Định dạng ngày, lương trong bảng
		 */
		dfNgay=new SimpleDateFormat("dd/MM/yyyy");
		dfLuong=new DecimalFormat("##,###,###");
		
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
		scrollCNTable.setBounds(332, 309, 890, 275);
		pMain.add(scrollCNTable);
		
		String col[] = {"Mã ","Công Nhân" , "Tổ",  "Ngày ", "Ca làm", "Số lượng " , "Lương ngày"};
		modelChamCong = new DefaultTableModel(col, 0);

		tableCN = new JTable(modelChamCong);
		tableCN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableCN.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableCN.setShowHorizontalLines(true); 
		tableCN.setShowGrid(true);
		tableCN.setBackground(Color.WHITE);
		tableCN.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableCN.setSelectionBackground(new Color(164, 44, 167, 30));
		tableCN.setSelectionForeground(new Color(114, 23, 153));
		tableCN.setRowHeight(30);
		tableCN.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableCN.setToolTipText("Chọn nhân viên để thực hiện chức năng");
		
		
		JTableHeader tbHeader = tableCN.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		tbHeader.setToolTipText("Danh sách Ngày công nhân viên");

		tableCN.getColumnModel().getColumn(0).setPreferredWidth(90); 
		tableCN.getColumnModel().getColumn(1).setPreferredWidth(170);
		tableCN.getColumnModel().getColumn(2).setPreferredWidth(90); 
		tableCN.getColumnModel().getColumn(3).setPreferredWidth(130); 
		tableCN.getColumnModel().getColumn(4).setPreferredWidth(130); 
		tableCN.getColumnModel().getColumn(5).setPreferredWidth(130); 
		tableCN.getColumnModel().getColumn(6).setPreferredWidth(130);


	

		scrollCNTable.setViewportView(tableCN);
		
		
		pNhapThongTin = new JPanel();
		pNhapThongTin.setLayout(null);
		pNhapThongTin.setToolTipText("Các thông tin nhân viên cần nhập");
		pNhapThongTin.setBorder(new LineBorder(new Color(114, 23, 153), 2));
		pNhapThongTin.setBackground(Color.WHITE);
		pNhapThongTin.setBounds(332, 12, 890, 275);
		pMain.add(pNhapThongTin);
		
		

		
		lblNhapThongTin = new JLabel("Chấm Công Công Nhân");
		lblNhapThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhapThongTin.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNhapThongTin.setBounds(10, 4, 239, 29);
		pNhapThongTin.add(lblNhapThongTin);
		
		lblNgay = new JLabel("Ngày:");
		lblNgay.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNgay.setBounds(637, 7, 54, 25);
		pNhapThongTin.add(lblNgay);
		
		
		
		
		chooserNgay = new JDateChooser();
		chooserNgay.getCalendarButton().setToolTipText("Chọn ngày sinh");
		chooserNgay.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgay.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserNgay.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserNgay.setDateFormatString("dd/MM/yyyy");
		chooserNgay.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserNgay.setBounds(699, 4, 181, 29);
		chooserNgay.setDate(dNow);
		chooserNgay.setEnabled(false);
		pNhapThongTin.add(chooserNgay);
		
		btnLuuChamCong = new FixButton("Lưu");
		btnLuuChamCong.setText("Chấm Công");
		btnLuuChamCong.setForeground(Color.BLACK);
		btnLuuChamCong.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLuuChamCong.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLuuChamCong.setBackground(new Color(57, 210, 247));
		btnLuuChamCong.setBounds(699, 197, 132, 33);
		pNhapThongTin.add(btnLuuChamCong);
		

		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 43, 615, 222);
		pNhapThongTin.add(scrollPane);
		
		
//		String colCC[] = {"Mã ","Công Nhân" , "Tổ", "Ca làm", "Số lượng " };
//		
//		modelActionChamCong = new DefaultTableModel(colCC, 0);
		tableCCCN = new JTable();
	  
	    		  
//		scrollPane.setViewportView(tableCCCN);
		
		pTreeToSX = new JScrollPane();
		pTreeToSX.setBorder(new LineBorder(new Color(114, 23, 153), 2, true));
		pTreeToSX.setBackground(Color.WHITE);
		pTreeToSX.setBounds(17, 10, 308, 568);
		pMain.add(pTreeToSX);
		
		
		loadDSToSX2Jtree();
		
		
		
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
		
		btnTimCN = new FixButton("Tìm");
		btnTimCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimCN.setLocation(171, 2);
		btnTimCN.setSize(81, 39);
		pControlJList.add(btnTimCN);
		
		btnResetList = new JButton("");
		btnResetList.setIcon(new ImageIcon("data/icon/refres.png"));
		btnResetList.setBounds(264, 6, 55, 35);
		pControlJList.add(btnResetList);

	
		
		JPanel pControl = new JPanel();
		pControl.setBackground(Color.WHITE);
		pControl.setBounds(426, 584, 832, 39);
		pMain.add(pControl);
		pControl.setLayout(null);
		
		
		
		
//  xử lý sự kiện chọn tổ
		JTToSX.addTreeSelectionListener(this);
		tableCN.addMouseListener(this);
		


//		btn click
		btnLuuChamCong.addActionListener(this);
		btnTimCN.addActionListener(this);
		btnResetList.addActionListener(this);
//		load ds
	
	}



	private void loadDSToSX2Jtree() {
		
		root = new DefaultMutableTreeNode("Tổ Sản Xuất");
		ArrayList<ToSanXuat> lstToSX = daoTSX.getDSToSanXuat();
		ArrayList<CongNhan> lstCN = daoCongNhan.getDSCongNhan();
		for (ToSanXuat toSanXuat : lstToSX) {
			nodeTo = new DefaultMutableTreeNode(toSanXuat);
			for(CongNhan cn: lstCN) {
				 if(cn.getToSanXuat().equals(toSanXuat)) {
						nodeCN = new DefaultMutableTreeNode(cn);
						nodeTo.add(nodeCN);		
				 }
			}
			
			root.add(nodeTo);
		
		}
		defaultTreeModel = new DefaultTreeModel(root);
		JTToSX = new JTree(defaultTreeModel);
		JTToSX.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTToSX.setVisibleRowCount(35);
		JTToSX.setCellRenderer(new FixRenderTree());
		pTreeToSX.setColumnHeaderView(JTToSX);
	}



	@Override
	public void valueChanged(TreeSelectionEvent e) {

	    DefaultMutableTreeNode node = (DefaultMutableTreeNode) JTToSX.getLastSelectedPathComponent();
	    
	    if(node == null)
	    	return;
	    
	    
	    
	    Object nodeObject = node.getUserObject();
	    if(nodeObject instanceof ToSanXuat) {
	    	ToSanXuat toSanXuat = (ToSanXuat) nodeObject;
	    	
	    	ArrayList<CongNhan> listCNTo = new ArrayList<CongNhan>();
	    	
	    
	    	listCNTo = daoCongNhan.getDSCongNhanCungTo(toSanXuat.getMaTo());
	    	
	    	

	    	toSXSelected = toSanXuat;
	    	statusTOSXSelected = true;
	    	loadDSChamCongTo(toSanXuat);
 	
	    }

	    if(nodeObject instanceof CongNhan) {
	    	CongNhan cNhan = (CongNhan) nodeObject;
	    	statusTOSXSelected = false;
	    	congNhanSelected =cNhan;
			loadDSChamCongNhan(cNhan);

	    }
	    
	}





// Kiem tra

	// kiem tra cham cong ngay hien tai 
		protected boolean checkChamCong(CongNhan cNhan) {
			ArrayList<ChamCongCN> lisctCCCN = daoPhieuChamCong.getChamCongCongNhan(cNhan.getMaCN());
			java.util.Date date = chooserNgay.getDate();
			
			Date ngayFormat=new Date(date.getYear(), date.getMonth(), date.getDate());

			if(lisctCCCN.size() >=1 ) {
				for (ChamCongCN info : lisctCCCN) {
					if(info.getNgayLam().equals(ngayFormat)) {
						return false;
					}

				}
			}
			return true;
		
			
			
		}
		
	protected void loadDSChamCongNhan(CongNhan cNhan) {
		removeDSChamCong(modelChamCong);
		removeDSActionsCC(modelCC);
		ArrayList<TemplateCCCN> templateCCCNs = new ArrayList<>();
		removeDSChamCong(modelChamCong);
		ArrayList<ChamCongCN> lisctCCCN = daoPhieuChamCong.getChamCongCongNhan(cNhan.getMaCN());

		
		
		for (ChamCongCN info : lisctCCCN) {
			modelChamCong.addRow(new Object[] {
					info.getMaC(), cNhan.getTenCN(), cNhan.getToSanXuat().getTenTo(),
				dfNgay.format(info.getNgayLam()), info.getCaLam(), info.getSoLuong(), dfLuong.format(Math.round(info.getLuongNgay()))
				
			
			});
		}
		if(checkChamCong(cNhan)) {
			TemplateCCCN templateCCCN = new TemplateCCCN(cNhan.getMaCN(), cNhan.getTenCN() , cNhan.getToSanXuat().getTenTo() , "Ca 1", 0 );
			templateCCCNs.add(templateCCCN);
		}

		modelCC = new TblCCModelCtrl(templateCCCNs);
		loadTableCC(tableCCCN, modelCC);
		
	}




// set trang thai cham cong cho ngay
	protected void resetStatusCC(boolean checkChamCong) {
		if(checkChamCong == false) {
			btnLuuChamCong.setText("Đã Chấm Công!");
			btnLuuChamCong.setBackground(new Color(102, 205, 170));
			btnLuuChamCong.setToolTipText("Đã Chấm Công Cho Ngày" + dNow);
			
		}
		else {
			btnLuuChamCong.setText("Chấm Công");
			btnLuuChamCong.setBackground(new Color(57, 210, 247));
			
		}
		btnLuuChamCong.setEnabled(checkChamCong);
		
	}




//	 form load 
	private void loadDSChamCongTo(ToSanXuat toSanXuat) {
		removeDSChamCong(modelChamCong);
		removeDSActionsCC(modelCC);
		ArrayList<TemplateCCCN> templateCCCNs = new ArrayList<>();
		ArrayList<CongNhan> lstCN = daoCongNhan.getDSCongNhanCungTo(toSanXuat.getMaTo());	
		for (CongNhan cn : lstCN) {
			ArrayList<ChamCongCN> lisctCCCN = daoPhieuChamCong.getChamCongCongNhan(cn.getMaCN());
			
			for (ChamCongCN info : lisctCCCN) {
				modelChamCong.addRow(new Object[] {
						info.getMaC(), cn.getTenCN(), cn.getToSanXuat().getTenTo(),
					dfNgay.format(info.getNgayLam()), info.getCaLam(), info.getSoLuong(),dfLuong.format(Math.round(info.getLuongNgay())) 
					
				
				});
				
			
				
			}
			if(checkChamCong(cn)) {
				
				TemplateCCCN templateCCCN = new TemplateCCCN(cn.getMaCN(), cn.getTenCN() , cn.getToSanXuat().getTenTo() , "Ca 1", 0 );
				templateCCCNs.add(templateCCCN);
			}
		
			
		}

		modelCC = new TblCCModelCtrl(templateCCCNs);
		loadTableCC(tableCCCN, modelCC);
		
		
	}


	private void loadTableCC(JTable tableCCCN,  TblCCModelCtrl modelCC2) {
	
		tableCCCN = new JTable(modelCC);
		tableCCCN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableCCCN.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableCCCN.setShowHorizontalLines(true); 
		tableCCCN.setShowGrid(true);
		tableCCCN.setBackground(Color.WHITE);
		tableCCCN.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableCCCN.setSelectionBackground(new Color(164, 44, 167, 30));
		tableCCCN.setRowHeight(30);
		tableCCCN.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableCCCN.setToolTipText("Chọn nhân viên để thực hiện chức năng");	
		JTableHeader tbHeaderCC = tableCCCN.getTableHeader();
		tbHeaderCC.setBackground(new Color(164, 44, 167));
		tbHeaderCC.setForeground(Color.white);
		tbHeaderCC.setFont(new Font("SansSerif", Font.BOLD, 14));
		tbHeaderCC.setToolTipText("Danh sách Ngày công nhân viên");

		tableCCCN.getColumnModel().getColumn(0).setPreferredWidth(80); 
		tableCCCN.getColumnModel().getColumn(1).setPreferredWidth(170);
		tableCCCN.getColumnModel().getColumn(2).setPreferredWidth(120); 
		tableCCCN.getColumnModel().getColumn(3).setPreferredWidth(120); 
		tableCCCN.getColumnModel().getColumn(4).setPreferredWidth(120); 
	


		DefaultTableCellRenderer rightRenderer=new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		
//		Ca lam viec in table
		  comboBox = new JComboBox<String>();
	      comboBox.addItem("Ca 1");
	      comboBox.addItem("Ca 2");
	      comboBox.addItem("Ca 3");
	      TableColumn tbColumn = tableCCCN.getColumnModel().getColumn(3);
	      tbColumn.setCellEditor(new DefaultCellEditor(comboBox));
	    		  
	    	
		
		
		
		((JScrollPane) scrollPane).setViewportView(tableCCCN);
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e. getSource();
		
//		if(o.equals(btnLuuChamCong)) {
//			addChamCongCongNhan();
//		}
		
		if(o.equals(btnLuuChamCong)) {
			notifyCCCN(LuuChamCongCn(modelCC.getLstchamCongCN()), toSXSelected , congNhanSelected );	
		}

		if(o.equals(btnTimCN)) {
			findCNJTree();
		}
		if(o.equals(btnResetList)) {
			loadDSToSX2Jtree();
			txtTimCN.setText("");
		}
		
	}
	
	




	private int LuuChamCongCn(ArrayList<TemplateCCCN> lstchamCongCN) {
			int result = 0;
		 for( TemplateCCCN t : lstchamCongCN) {
			 if(t.getSoLuong() > 0 ) {
					float giaSX = getGiaSXCDTo(t.getMa());
					float luongNgay = tinhLuongNgay(t.getSoLuong() , giaSX);
					
					java.util.Date date = chooserNgay.getDate();
					Date ngayFormat=new Date(date.getYear(), date.getMonth(), date.getDate());
					
					ChamCongCN input = new ChamCongCN();
					input.setMaC(t.getMa());
					input.setCaLam(t.getCaLam());
					input.setNgayLam(ngayFormat);
					input.setSoLuong(t.getSoLuong());
					input.setLuongNgay(luongNgay);
					try {
						if(daoPhieuChamCong.themCCCN(input)) {
							result = 1;
						}
								
					}catch (SQLException e) {
						e.printStackTrace();
						result = 0;
					}	
			 }
			
			 
			 
		 }
		 
		 return result;
	}



	private void findCNJTree() {
		
		String ma = txtTimCN.getText();
		
		if(ma.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã công nhân!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
		else {
			
			
			root = new DefaultMutableTreeNode("Công nhân");
			
			ArrayList<CongNhan> lstCN = daoCongNhan.getDSCongNhan();
			
					for (CongNhan cNhan : lstCN ) {
						if(cNhan.getMaCN().equalsIgnoreCase(ma)) {
							nodeCN = new DefaultMutableTreeNode(cNhan);
							root.add(nodeCN);
						}
					}
					
					 defaultTreeModel = new DefaultTreeModel(root);
					 JTToSX = new JTree(defaultTreeModel);
					 JTToSX.setFont(new Font("SansSerif", Font.PLAIN, 15));
					 JTToSX.setVisibleRowCount(35);
					 JTToSX.setCellRenderer(new FixRenderTree());
					 pTreeToSX.setColumnHeaderView(JTToSX);
				
		}


	}






	private void notifyCCCN(int status , ToSanXuat tsx , CongNhan congNhanSelected) {
		System.out.println(status);
		if(status==-1) {
			JOptionPane.showMessageDialog(this, "Vui lòng cập nhật số lượng công nhân sản xuất!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
		else if(status == 0) {
			JOptionPane.showMessageDialog(this, "Chấm công thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		}
		else if(status == 1) {
			JOptionPane.showMessageDialog(this, "Chấm công thành công! ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			if(statusTOSXSelected) {
				 loadDSChamCongTo(tsx);
			}else {
				loadDSChamCongNhan(congNhanSelected);
			}
			
		
		}
	
	}


	private void removeDSChamCong(DefaultTableModel defaultTableModel) {
		while(tableCN.getRowCount() > 0){
			modelChamCong.removeRow(0);
		}
	}
	private void removeDSActionsCC(TblCCModelCtrl modelCC2) {
		while(tableCCCN.getRowCount() > 0){
			modelCC.removeRow(0);
		}
	}







	private float getGiaSXCDTo(String ma) {
		
		CongNhan cn = daoCongNhan.getCongNhanFromMaCN(ma);
		
		CongDoan cd = daoCongDoan.getCDTheoMaCD(cn.getToSanXuat().getMaCD());
		
		float result  = cd.getGiaSX();
		return result;
	}



//	private void resetAll() {
//		txtHoTenCN.setText("");
//		txtMaCN.setText("");
//		txtMaTo.setText("");
//		txtSoluong.setText("");
//		txtTenTo.setText("");
//		cboCaLamViec.setSelectedItem("Ca 1");
//		
//		
//	}



	private float tinhLuongNgay(int soLuong, float giaSX) {
	
		return soLuong * giaSX;
	}



	@Override
	public void mouseClicked(MouseEvent e) {
//		chooserPhieuChamCong();
		
	}



//	private void chooserPhieuChamCong() {
//		int selectRow = tableCN.getSelectedRow();
//		if(selectRow >= 0) {
//			String ma = (String) tableCN.getValueAt(selectRow, 0);
//			String tenCN = (String) tableCN.getValueAt(selectRow,1 );
//			String to = (String) tableCN.getValueAt(selectRow, 2);
//			String ngay = (String) tableCN.getValueAt(selectRow, 3);
//			String ca = (String) tableCN.getValueAt(selectRow, 4);
//			String soLuong = (String) String.valueOf(tableCN.getValueAt(selectRow, 5));
//			String luongNgay = (String) String.valueOf(tableCN.getValueAt(selectRow, 6));
//			
//
//			txtMaCN.setText(ma);
//			txtHoTenCN.setText(tenCN);
//			txtTenTo.setText(to);
//			txtSoluong.setText(soLuong);
//			cboCaLamViec.setSelectedItem(ca);
//			try {
//				chooserNgay.setDate(dfNgay.parse(ngay));
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		
//			
//			
//		}
//		
//	}



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
}