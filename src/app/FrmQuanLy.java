package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

import org.apache.commons.collections4.map.HashedMap;

import custom.FixButton;
import entity.NhanVien;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class FrmQuanLy extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private JPanel pContent;
	private JButton btnDangXuat;
	private JLabel lblHeaderTen;
	private JLabel lblSubMa;
	
	
	
	private JButton btnItemNhanSu;
	private JButton btnItemQLCC;
	private JButton btnItemQLSP;
	private JButton btnItemQLLuong;
	private JButton btnItemTK;

	private JButton btnSelectNV, btnSelectCN;

	private Date dNow;
	private LocalDate now;
	private int ngay;
	private int thang;
	private int nam;
	private JLabel lblHeaderMaNV;
	private JButton btnHeaderInfo;

	private FrmChamCongCN frmChamCongCN;
	private FrmCongNhan frmCongNhan;
	private FrmNhanVien frmNhanVien;
	private FrmQLSanPham frmQLSanPham;	
	
	

	private FrmQLLuongCN FrmQLLuongCN;

	private FrmQLLuongNV FrmQLLuongNV;

	private app.FrmQLLuongCN frmQLLuongCN;

	private app.FrmQLLuongNV frmQLLuongNV;
	
	private FrmChamCongNV frmChamCongNV;
	
	private String currenMenu = "";
	private JPopupMenu menu;

	private FrmQLCongDoan frmQLCongDoan;
	private FrmQLToSanXuat frmQLToSanXuat;

	private NhanVien headerNV;

	private JButton btnHome;

	private FrmHome frmHome;

	private JPanel pSelected;

	private JPopupMenu popUp;

	private JMenuItem popItem;

	private JMenuItem itemSp;

	private JMenuItem itemCD;

	private JMenuItem itemTSX;

	private FrmThongKe frmThongKe;
	
	@SuppressWarnings("deprecation")

	public FrmQuanLy(NhanVien nv) {
		this.headerNV = nv;

		IconFontSwing.register(FontAwesome.getIconFont());
		ImageIcon logoApp = new ImageIcon("data\\icon\\imgApp.png"); 
		setIconImage(logoApp.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Quản lý nhân viên");
		setSize(1281, 778);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1281, 54);
		panel.setBackground(new Color(176, 196, 222));
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(0, 0, 54, 54);
		Image imglogo = Toolkit.getDefaultToolkit().getImage("data\\img\\logo.png");
		Image resizelogo = imglogo.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), 0);
		lblLogo.setIcon(new ImageIcon(resizelogo));
		panel.add(lblLogo);

		now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue();
		nam = now.getYear();

		dNow = new Date(nam - 1900, thang - 1, ngay);

		JLabel lblHeaderDate = new JLabel("Hiện tại:");
		lblHeaderDate.setForeground(Color.WHITE);
		lblHeaderDate.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHeaderDate.setBounds(488, 23, 66, 21);
		panel.add(lblHeaderDate);

		JLabel lblNgayHienTai = new JLabel(ngay + " / " + thang + " / " + nam);
		lblNgayHienTai.setForeground(Color.WHITE);
		lblNgayHienTai.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblNgayHienTai.setBounds(564, 20, 151, 21);
		panel.add(lblNgayHienTai);

		lblHeaderTen = new JLabel(headerNV.getTenNV());
		lblHeaderTen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHeaderTen.setForeground(Color.WHITE);
		lblHeaderTen.setBounds(843, 5, 170, 20);
		panel.add(lblHeaderTen);
		
		lblSubMa = new JLabel("Mã nhân viên:");
		lblSubMa.setForeground(Color.WHITE);
		lblSubMa.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSubMa.setBounds(843, 26, 110, 20);
		panel.add(lblSubMa);

		btnDangXuat = new FixButton("Đăng Xuất");
		btnDangXuat.setForeground(Color.WHITE);
		btnDangXuat.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnDangXuat.setBounds(1113, 10, 132, 35);
		btnDangXuat.setBackground(new Color(0xE91940));
		Icon iconDangXuat = IconFontSwing.buildIcon(FontAwesome.SIGN_OUT, 25, new Color(255, 255, 255));
		
		btnDangXuat.setIcon(iconDangXuat);
		panel.add(btnDangXuat);

		lblHeaderMaNV = new JLabel();
		lblHeaderMaNV.setText(headerNV.getMaNV());
		lblHeaderMaNV.setForeground(Color.WHITE);
		lblHeaderMaNV.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblHeaderMaNV.setBounds(953, 26, 60, 20);
		panel.add(lblHeaderMaNV);

		btnHeaderInfo = new JButton();
		btnHeaderInfo.setText("NV");


		btnHeaderInfo.setForeground(Color.WHITE);
		btnHeaderInfo.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnHeaderInfo.setBounds(1023, 5, 60, 44);
		btnHeaderInfo.setBackground(new Color(57, 210, 247));

		panel.add(btnHeaderInfo);

		JPanel pMenu = new JPanel();
		pMenu.setBackground(new Color(221, 160, 221));
		pMenu.setBounds(0, 54, 1281, 31);
		getContentPane().add(pMenu);
		pMenu.setLayout(null);

		
		
		
		int x = 220; // vi tri chieu ngang cua item
		btnHome = new JButton("");
		if (btnHeaderInfo.getText().contains("NV")) {
			Icon iconHome = IconFontSwing.buildIcon(FontAwesome.HOME, 25);
			btnHome.setIcon(iconHome);
			btnHome.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnHome.setBackground(new Color(255, 240, 245));
			btnHome.setBounds(x, 0, 80, 31);
			btnHome.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnHome);

			x = x + 90; // chuyen vi tri sang mot doan
		}
		btnItemNhanSu = new JButton("Quản lý Nhân Sự");
		if (btnHeaderInfo.getText().contains("NV")) {

			btnItemNhanSu.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemNhanSu.setBackground(new Color(255, 240, 245));
			btnItemNhanSu.setBounds(x, 0, 132, 31);
			btnItemNhanSu.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemNhanSu);

			x = x + 142; // chuyen vi tri sang mot doan
		}

		btnItemQLSP = new JButton("Quản lý Sản Phẩm");
		menu = new JPopupMenu();
	    itemSp = new JMenuItem("Sản Phẩm ");
	    itemSp.setIcon(new ImageIcon("data/icon/iconSanPham1.png"));
	    itemSp.setBackground(new Color(255, 240, 245));
	    itemSp.setBounds(0, 0, 200, 31); // 261
	    itemSp.setFont(new Font("SansSerif", Font.BOLD, 13));
	    
	    itemCD = new JMenuItem("Phân Chia Công Đoạn");
		itemCD.setIcon(new ImageIcon("data/icon/iconCongDoan.png"));
	    itemCD.setBackground(new Color(255, 240, 245));
	    itemCD.setFont(new Font("SansSerif", Font.BOLD, 13));
	    
	    itemTSX = new JMenuItem("Quản lý Tổ Sản Xuất");
	    
	   
	    itemTSX.setBackground(new Color(255, 240, 245));
	    itemTSX.setFont(new Font("SansSerif", Font.BOLD, 13));
	    
	    menu.add(itemSp);
	    menu.add(itemCD);
//	    menu.add(itemTSX);
	   
		btnItemQLSP.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
//	            if ( e.getButton() == 1 ){ // 1-left, 2-middle, 3-right button
	                menu.show(e.getComponent(),0, e.getY());
//	            }
	        }
			 public void mouseExited(MouseEvent evt) 
	         {
//	           menu.show(false);
	         }
	    });
		
		if (btnHeaderInfo.getText().contains("NV") || btnHeaderInfo.getText().contains("TN")) {

			if (btnHeaderInfo.getText().contains("TN")) {
				x += 225;
			}

			btnItemQLSP.setLayout(null);
			btnItemQLSP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemQLSP.setBackground(new Color(255, 240, 245));
			btnItemQLSP.setBounds(x, 0, 161, 31); // 261
			btnItemQLSP.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemQLSP);

			x = x + 142;
			if (btnHeaderInfo.getText().contains("TN"))
				x -= 255;
		}

		btnItemQLCC = new JButton("Quản lý Chấm Công");
		if (btnHeaderInfo.getText().contains("NV") || btnHeaderInfo.getText().contains("TN")) {
			x += 29;
			if (btnHeaderInfo.getText().contains("TN"))
				x += 255;

			btnItemQLCC.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemQLCC.setBackground(new Color(255, 240, 245));
			btnItemQLCC.setBounds(x, 0, 150, 31); // 432
			btnItemQLCC.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemQLCC);

			x = x + 160;

		}
		btnItemQLLuong = new JButton("Quản lý Lương");
		if (btnHeaderInfo.getText().contains("NV") || btnHeaderInfo.getText().contains("PV")) {

			if (btnHeaderInfo.getText().contains("PV"))
				x += 325;

			btnItemQLLuong.setLayout(null);
			btnItemQLLuong.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemQLLuong.setBackground(new Color(255, 240, 245));
			btnItemQLLuong.setBounds(x, 0, 149, 31); // 574
			btnItemQLLuong.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemQLLuong);

			x = x + 142;
		}

		btnItemTK = new JButton("Thống kê");
		if (btnHeaderInfo.getText().contains("NV") || btnHeaderInfo.getText().contains("TN")) {
			x += 15;

			btnItemTK.setLayout(null);
			btnItemTK.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemTK.setBackground(new Color(255, 240, 245));
			btnItemTK.setBounds(x, 0, 132, 31); // 1056
			btnItemTK.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemTK);

		}

		pSelected = new JPanel();
		pSelected.setBounds(0, 715, 1250, 25);

		getContentPane().add(pSelected);
		pSelected.setLayout(null);

		btnSelectNV = new JButton("Nhân Viên");
		btnSelectNV.setVerticalAlignment(SwingConstants.TOP);

		btnSelectNV.setBounds(0, 0, 111, 31);
		pSelected.add(btnSelectNV);

		btnSelectNV.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		btnSelectCN = new JButton("Công Nhân");
		btnSelectCN.setVerticalAlignment(SwingConstants.TOP);
		btnSelectCN.setBounds(100, 0, 111, 31);

		pSelected.add(btnSelectCN);

		btnSelectCN.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		

		pContent = new JPanel();
		pContent.setBounds(0, 86, 1267, 627);
		getContentPane().add(pContent);
		pContent.setLayout(null);

		popUp = new JPopupMenu();
		popItem = new JMenuItem("Trợ giúp");
		popUp.add(popItem);

		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent me) {
				showPopup(me);
			}
		});
		
		

		// Load frm mac dinh 
		if(btnHeaderInfo.getText().contains("NV")){
			loadFrmHome();
		}


		btnDangXuat.addActionListener(this);
		btnItemNhanSu.addActionListener(this);
		btnItemQLSP.addActionListener(this);
		btnItemQLCC.addActionListener(this);
		btnItemQLLuong.addActionListener(this);
		btnItemTK.addActionListener(this);
		btnSelectCN.addActionListener(this);
		btnSelectNV.addActionListener(this);
		btnHome.addActionListener(this);
		popItem.addActionListener(this);
		
		itemSp.addActionListener(this);
		itemCD.addActionListener(this);
		itemTSX.addActionListener(this);
	}

	void showPopup(MouseEvent me) {
		if (me.isPopupTrigger())
			popUp.show(me.getComponent(), me.getX(), me.getY());
	}

	// reset màu menu
	public void resetColorMenu() {
		btnItemNhanSu.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemNhanSu.setBackground(new Color(255, 240, 245));

		btnItemQLCC.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemQLCC.setBackground(new Color(255, 240, 245));

		btnItemQLSP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemQLSP.setBackground(new Color(255, 240, 245));

		btnItemQLLuong.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemQLLuong.setBackground(new Color(255, 240, 245));

		btnItemTK.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemTK.setBackground(new Color(255, 240, 245));
		
		btnHome.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnHome.setBackground(new Color(255, 240, 245));

	}
	public void loadFrmHome() {
		setTitle("APP CHẤM CÔNG");
		resetColorMenu();
		pContent.removeAll();
		btnHome.setBackground(new Color(192, 255, 255)); // new Color(233,136,236)
		btnHome.setBorder(BorderFactory.createLineBorder(Color.white));
			frmHome = new FrmHome();
			pContent.add(frmHome.getPanel());
		pSelected.setVisible(false);
	}

	public void loadFrmNhanVien() {
		setTitle("Quản lý nhân viên");
		resetColorMenu();
		pContent.removeAll();
		btnItemNhanSu.setBackground(new Color(192, 255, 255)); // new Color(233,136,236)
		btnItemNhanSu.setBorder(BorderFactory.createLineBorder(Color.white));
			frmNhanVien = new FrmNhanVien();
			pContent.add(frmNhanVien.getPanel());
			pSelected.setVisible(true);
	}

	public void loadFrmCongNhan() {
		setTitle("Quản lý công nhân");
		resetColorMenu();
		pContent.removeAll();
		btnItemNhanSu.setBackground(new Color(192, 255, 255)); // new Color(233,136,236)
		btnItemNhanSu.setBorder(BorderFactory.createLineBorder(Color.white));
			frmCongNhan = new FrmCongNhan();
			pContent.add(frmCongNhan.getPanel());

			pSelected.setVisible(true);

	}

	public void loadFrmSanPham() {
		setTitle("Quản Lý Sản Phẩm");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLSP.setBackground(new Color(192, 255, 255)); // new Color(233,136,236)
		btnItemQLSP.setBorder(BorderFactory.createLineBorder(Color.white));
			frmQLSanPham = new FrmQLSanPham(currenMenu, currenMenu, dNow);
			pContent.add(frmQLSanPham.getFrmQLSanPham());
	
			pSelected.setVisible(false);
		
	}
	public void loadFrmCongDoan	() {
		setTitle("Quản Lý Công Đoạn");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLSP.setBackground(new Color(192, 255, 255)); // new Color(233,136,236)
		btnItemQLSP.setBorder(BorderFactory.createLineBorder(Color.white));
			frmQLCongDoan = new FrmQLCongDoan(currenMenu, currenMenu, dNow);
			pContent.add(frmQLCongDoan.getFrmQLCongDoan());
			
			pSelected.setVisible(false);
	}
	public void loadFrmToSanXuat() {
		setTitle("Quản Lý Lương");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLSP.setBackground(new Color(192, 255, 255)); // new Color(233,136,236)
		btnItemQLSP.setBorder(BorderFactory.createLineBorder(Color.white));
			frmQLToSanXuat = new FrmQLToSanXuat(null, currenMenu, currenMenu, dNow);
			pContent.add(frmQLToSanXuat.getFrmQLToSanXuat());
		
			pSelected.setVisible(false);
		
	}
	
	public void loadFrmQLCCCN() {

		setTitle("Quản lý Chấm Công");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLCC.setBackground(new Color(192, 255, 255));
		btnItemQLCC.setBorder(BorderFactory.createLineBorder(Color.white));
		frmChamCongCN = new FrmChamCongCN();
		pContent.add(frmChamCongCN.getFrmChamCong());
		pSelected.setVisible(true);

	}
	public void loadFrmQLCCNV() {
		setTitle("Quản lý Chấm Công");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLCC.setBackground(new Color(192, 255, 255));
		btnItemQLCC.setBorder(BorderFactory.createLineBorder(Color.white));
		frmChamCongNV = new FrmChamCongNV();
		pContent.add(frmChamCongNV.getFrmChamCong());
		pSelected.setVisible(true);

	}


	public void loadFrmQLLuongCN() {
		setTitle("Quản lý lương CN");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLLuong.setBackground(new Color(192, 255, 255));
		btnItemQLLuong.setBorder(BorderFactory.createLineBorder(Color.white));
		frmQLLuongCN = new FrmQLLuongCN();
		pContent.add(frmQLLuongCN.getFrmQLLuongCN());
		pSelected.setVisible(true);

	}

	public void loadFrmQLLuongNV() {
		setTitle("Quản lý lương NV");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLLuong.setBackground(new Color(192, 255, 255));
		btnItemQLLuong.setBorder(BorderFactory.createLineBorder(Color.white));

		frmQLLuongNV = new FrmQLLuongNV();
		pContent.add(frmQLLuongNV.getFrmQLLuongNV());
		togglebtnSelect(true);
		pSelected.setVisible(true);
	}

//	}
//	
	public void loadFrmThongKe() {
		setTitle("Thống kê");
		resetColorMenu();
		pContent.removeAll();
		btnItemTK.setBackground(new Color(192,255,255));
		btnItemTK.setBorder(BorderFactory.createLineBorder(Color.white));
		frmThongKe = new FrmThongKe();
		frmThongKe = new FrmThongKe();
		pContent.add(frmThongKe.getFrmThongKe());
		pSelected.setVisible(false);
		
	
	}
	public void setColorActive(JButton btn) {
		btn.setBackground(new Color(192, 255, 255));
		String btnSelectCNText = btnSelectCN.getText().toString();
		String btnSelectNVText = btnSelectNV.getText().toString();
	
		
		if(btn.getText().equals(btnSelectCNText)) {
			btnSelectNV.setBackground(new Color(255,255,255));
		} else if(btn.getText().equals(btnSelectNVText)) {
				btnSelectCN.setBackground(new Color(255,255,255));
		}
		
	
		
		
	
	}

	private void togglebtnSelect(boolean visible) {
		btnSelectCN.setVisible(visible);
		btnSelectNV.setVisible(visible);	
	}

	public void dangXuat() {
		int optDangXuat = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn đăng xuất không?", "Thông báo",
				JOptionPane.YES_NO_OPTION);
		if (optDangXuat == JOptionPane.YES_OPTION) {
			FrmDangNhap frame = new FrmDangNhap();
			frame.setVisible(true);
			this.setVisible(false);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
//		-------------------------------
//		xử lý khi click MENUs => lưu lại currenMenu
//		-------------------------------
		if(o.equals(btnDangXuat)){
			dangXuat(); 
		}	
		if(o.equals(btnHome)) {
			loadFrmHome();
			
			
		}
		if(o.equals(btnItemNhanSu)) {
			currenMenu = "MENU_QL_NHANSU";
			setColorActive(btnSelectNV);
			loadFrmNhanVien();
			
		}
		if (o.equals(btnItemQLCC)) {
			currenMenu = "MENU_QLCC";
			loadFrmQLCCNV();
		
		}
//		if(o.equals(btnItemQLSP)) {
//			currenMenu = "MENU_QL_SP";
//			setColorActive(btnSelectSP);
//			loadFrmSanPham();
//		}

		if(o.equals(btnItemQLLuong)) {
			currenMenu = "MENU_QL_LUONG";
			setColorActive(btnSelectNV);
			loadFrmQLLuongNV();
		}	
//		-------------------------------
//		xử lý khi click nhân viên hoặc công nhân
//		-------------------------------
		if(o.equals(btnSelectCN)) {
			setColorActive(btnSelectCN);
			switch (currenMenu) {
			case "MENU_QL_LUONG":
				loadFrmQLLuongCN();
				break;
			case "MENU_QL_NHANSU":
				loadFrmCongNhan();
				break;
			case "MENU_QLCC":
				loadFrmQLCCCN();
				break;
			default:
				break;
			}
		}
		if(o.equals(btnSelectNV)) {
			setColorActive(btnSelectNV);
			switch (currenMenu) {
			case "MENU_QL_LUONG":
				loadFrmQLLuongNV();
				break;
			case "MENU_QL_NHANSU":
				loadFrmNhanVien();
				break;
			case "MENU_QLCC":
				loadFrmQLCCNV();
				break;
			default:
				break;
			}	
		}

		if(o.equals(btnItemTK)) {
			loadFrmThongKe();
		}
		if(o.equals(itemSp)) {
			loadFrmSanPham();
			
		}
		if(o.equals(itemCD)) {
			loadFrmCongDoan();
			
		}
		if(o.equals(itemTSX)) {
			loadFrmToSanXuat();
			
		}

	}
		
		

//		if(o.equals(popItem)) {
//			String[] commands = {"cmd", "/c", "data\\help\\HELP.chm"};
//			try {
//				Runtime.getRuntime().exec(commands);
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		}

	

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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
