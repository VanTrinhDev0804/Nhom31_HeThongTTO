package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
	
	private JPopupMenu popUp;
	private JMenuItem popItem;

	private FrmQLLuongCN FrmQLLuongCN;

	private FrmQLLuongNV FrmQLLuongNV;

	private app.FrmQLLuongCN frmQLLuongCN;

	private app.FrmQLLuongNV frmQLLuongNV;
	
	private FrmChamCongNV frmChamCongNV;
	
	private String currenMenu = "";

	private JButton btnSelectSP;

	private JButton btnSelectCD;

	private JButton btnSelectTSX;

	private FrmQLCongDoan frmQLCongDoan;
	private FrmQLToSanXuat frmQLToSanXuat;

	private NhanVien headerNV;
	
	@SuppressWarnings("deprecation")

	public FrmQuanLy() {
		//this.headerNV = nv;

		IconFontSwing.register(FontAwesome.getIconFont());
		ImageIcon logoApp = (ImageIcon) IconFontSwing.buildIcon(FontAwesome.FOURSQUARE, 50, new Color(164, 44, 167));
		setIconImage(logoApp.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Qu???n l?? nh??n vi??n");
		setSize(1299, 778);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1281, 54);
		panel.setBackground(new Color(176, 196, 222));
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(25, 5, 217, 50);
		Image imglogo = Toolkit.getDefaultToolkit().getImage("data\\img\\logo.png");
		Image resizelogo = imglogo.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), 0);
		lblLogo.setIcon(new ImageIcon(resizelogo));
		panel.add(lblLogo);

		now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue();
		nam = now.getYear();

		dNow = new Date(nam - 1900, thang - 1, ngay);

		JLabel lblHeaderDate = new JLabel("Hi???n t???i:");
		lblHeaderDate.setForeground(Color.WHITE);
		lblHeaderDate.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHeaderDate.setBounds(488, 23, 66, 21);
		panel.add(lblHeaderDate);

		JLabel lblNgayHienTai = new JLabel(ngay + " / " + thang + " / " + nam);
		lblNgayHienTai.setForeground(Color.WHITE);
		lblNgayHienTai.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblNgayHienTai.setBounds(564, 20, 151, 21);
		panel.add(lblNgayHienTai);

		lblHeaderTen = new JLabel();
		lblHeaderTen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHeaderTen.setForeground(Color.WHITE);
		lblHeaderTen.setBounds(843, 5, 170, 20);
		panel.add(lblHeaderTen);

		lblSubMa = new JLabel("M?? nh??n vi??n:");
		lblSubMa.setForeground(Color.WHITE);
		lblSubMa.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSubMa.setBounds(843, 26, 110, 20);
		panel.add(lblSubMa);

		btnDangXuat = new FixButton("????ng Xu???t");
		btnDangXuat.setForeground(Color.WHITE);
		btnDangXuat.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnDangXuat.setBounds(1113, 10, 132, 35);
		btnDangXuat.setBackground(new Color(0xE91940));
		Icon iconDangXuat = IconFontSwing.buildIcon(FontAwesome.SIGN_OUT, 25, new Color(255, 255, 255));
		btnDangXuat.setIcon(iconDangXuat);
		panel.add(btnDangXuat);

		lblHeaderMaNV = new JLabel();
		lblHeaderMaNV.setForeground(Color.WHITE);
		lblHeaderMaNV.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblHeaderMaNV.setBounds(953, 26, 60, 20);
		panel.add(lblHeaderMaNV);

		btnHeaderInfo = new JButton();
		btnHeaderInfo.setText("QL");
//		if(nv.getChucVu().equalsIgnoreCase("Ph???c v???"))
//			btnHeaderInfo.setText("PV");
//		else if(nv.getChucVu().equalsIgnoreCase("Qu???n l??"))
//			btnHeaderInfo.setText("QL");
//		else btnHeaderInfo.setText("TN");

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

		int x = 250; // vi tri chieu ngang cua item

		btnItemNhanSu = new JButton("Qu???n l?? Nh??n S???");
		if (btnHeaderInfo.getText().contains("QL")) {

			btnItemNhanSu.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemNhanSu.setBackground(new Color(255, 240, 245));
			btnItemNhanSu.setBounds(x, 0, 132, 31);
			btnItemNhanSu.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemNhanSu);

			x = x + 142; // chuyen vi tri sang mot doan
		}

		btnItemQLSP = new JButton("Qu???n l?? S???n Ph???m");
		if (btnHeaderInfo.getText().contains("QL") || btnHeaderInfo.getText().contains("TN")) {

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

		btnItemQLCC = new JButton("Qu???n l?? Ch???m C??ng");
		if (btnHeaderInfo.getText().contains("QL") || btnHeaderInfo.getText().contains("TN")) {
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
		btnItemQLLuong = new JButton("Qu???n l?? L????ng");
		if (btnHeaderInfo.getText().contains("QL") || btnHeaderInfo.getText().contains("PV")) {

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

		btnItemTK = new JButton("Qu???n l?? th???ng k??");
		if (btnHeaderInfo.getText().contains("QL") || btnHeaderInfo.getText().contains("TN")) {
			x += 15;

			btnItemTK.setLayout(null);
			btnItemTK.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemTK.setBackground(new Color(255, 240, 245));
			btnItemTK.setBounds(x, 0, 132, 31); // 1056
			btnItemTK.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemTK);

		}

		JPanel pSelected = new JPanel();
		pSelected.setBounds(0, 715, 1250, 25);

		getContentPane().add(pSelected);
		pSelected.setLayout(null);

		btnSelectNV = new JButton("Nh??n Vi??n");
		btnSelectNV.setVerticalAlignment(SwingConstants.TOP);

		btnSelectNV.setBounds(0, 0, 111, 31);
		pSelected.add(btnSelectNV);

		btnSelectNV.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		btnSelectCN = new JButton("C??ng Nh??n");
		btnSelectCN.setVerticalAlignment(SwingConstants.TOP);
		btnSelectCN.setBounds(100, 0, 111, 31);

		pSelected.add(btnSelectCN);

		btnSelectCN.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		btnSelectSP = new JButton("S???n Ph???m");
		btnSelectSP.setVerticalAlignment(SwingConstants.TOP);

		btnSelectSP.setBounds(950, 0, 111, 31);
		pSelected.add(btnSelectSP);

		btnSelectSP.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		btnSelectCD = new JButton("C??ng ??o???n");
		btnSelectCD.setVerticalAlignment(SwingConstants.TOP);
		btnSelectCD.setBounds(1050, 0, 111, 31);

		pSelected.add(btnSelectCD);

		btnSelectCD.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		
		btnSelectTSX = new JButton("T??? S???n Xu???t");
		btnSelectTSX.setVerticalAlignment(SwingConstants.TOP);
		btnSelectTSX.setBounds(1150, 0, 111, 31);

		pSelected.add(btnSelectTSX);

		btnSelectTSX.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		

		
		btnSelectCD.setVisible(false);
		btnSelectSP.setVisible(false);
		btnSelectTSX.setVisible(false);
//		btnSelectNV = new JButton("Nh??n Vi??n");
//
//			
//			btnItemTK.setLayout(null);
//			btnItemTK.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
//			btnItemTK.setBackground(new Color(255, 240, 245));
//			btnItemTK.setBounds(0, 0, 132, 31); // 1056
//			btnItemTK.setFont(new Font("SansSerif", Font.BOLD, 13));
//			pSelected.add(btnSelectNV);
//		
//		
//		

		pContent = new JPanel();
		pContent.setBounds(0, 86, 1267, 627);
		getContentPane().add(pContent);
		pContent.setLayout(null);

		popUp = new JPopupMenu();
		popItem = new JMenuItem("Tr??? gi??p");
		popUp.add(popItem);

		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent me) {
				showPopup(me);
			}
		});

		// Load frm mac dinh 
		if(btnHeaderInfo.getText().contains("QL")){
			loadFrmNhanVien();
		}
//		else if(btnHeaderInfo.getText().contains("TN")) {
//			loadFrmDDP();
//		}
//		else if(btnHeaderInfo.getText().contains("PV")) {
//			loadFrmMatHang();
//		}

		btnDangXuat.addActionListener(this);
		btnItemNhanSu.addActionListener(this);
		btnItemQLSP.addActionListener(this);
		btnItemQLCC.addActionListener(this);
		btnItemQLLuong.addActionListener(this);
		btnItemTK.addActionListener(this);
		btnSelectCN.addActionListener(this);
		btnSelectNV.addActionListener(this);
		btnSelectSP.addActionListener(this);
		btnSelectCD.addActionListener(this);
		btnSelectTSX.addActionListener(this);
		popItem.addActionListener(this);

	}

	void showPopup(MouseEvent me) {
		if (me.isPopupTrigger())
			popUp.show(me.getComponent(), me.getX(), me.getY());
	}

	// reset m??u menu
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

	}

	public void loadFrmNhanVien() {
		setTitle("Qu???n l?? nh??n vi??n");
		resetColorMenu();
		pContent.removeAll();
		btnItemNhanSu.setBackground(new Color(192, 255, 255)); // new Color(233,136,236)
		btnItemNhanSu.setBorder(BorderFactory.createLineBorder(Color.white));
			frmNhanVien = new FrmNhanVien();
			pContent.add(frmNhanVien.getPanel());
			togglebtnSelect(true);
	}

	public void loadFrmCongNhan() {
		setTitle("Qu???n l?? c??ng nh??n");
		resetColorMenu();
		pContent.removeAll();
		btnItemNhanSu.setBackground(new Color(192, 255, 255)); // new Color(233,136,236)
		btnItemNhanSu.setBorder(BorderFactory.createLineBorder(Color.white));
			frmCongNhan = new FrmCongNhan();
			pContent.add(frmCongNhan.getPanel());
			togglebtnSelect(true);

	}

	public void loadFrmSanPham() {
		setTitle("Qu???n L?? L????ng");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLSP.setBackground(new Color(192, 255, 255)); // new Color(233,136,236)
		btnItemQLSP.setBorder(BorderFactory.createLineBorder(Color.white));
			frmQLSanPham = new FrmQLSanPham(currenMenu, currenMenu, dNow);
			pContent.add(frmQLSanPham.getFrmQLSanPham());
			togglebtnSelect(false);
		
	}
	public void loadFrmCongDoan	() {
		setTitle("Qu???n L?? L????ng");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLSP.setBackground(new Color(192, 255, 255)); // new Color(233,136,236)
		btnItemQLSP.setBorder(BorderFactory.createLineBorder(Color.white));
			frmQLCongDoan = new FrmQLCongDoan(currenMenu, currenMenu, dNow);
			pContent.add(frmQLCongDoan.getFrmQLCongDoan());
			
			togglebtnSelect(false);
	}
	public void loadFrmToSanXuat() {
		setTitle("Qu???n L?? L????ng");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLSP.setBackground(new Color(192, 255, 255)); // new Color(233,136,236)
		btnItemQLSP.setBorder(BorderFactory.createLineBorder(Color.white));
			frmQLToSanXuat = new FrmQLToSanXuat(null, currenMenu, currenMenu, dNow);
			pContent.add(frmQLToSanXuat.getFrmQLToSanXuat());
		
			togglebtnSelect(false);
		
	}
	
	public void loadFrmQLCCCN() {

		setTitle("Qu???n l?? Ch???m C??ng");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLCC.setBackground(new Color(192, 255, 255));
		btnItemQLCC.setBorder(BorderFactory.createLineBorder(Color.white));
		frmChamCongCN = new FrmChamCongCN();
		pContent.add(frmChamCongCN.getFrmChamCong());
		togglebtnSelect(true);

	}
	public void loadFrmQLCCNV() {
		setTitle("Qu???n l?? Ch???m C??ng");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLCC.setBackground(new Color(192, 255, 255));
		btnItemQLCC.setBorder(BorderFactory.createLineBorder(Color.white));
		frmChamCongNV = new FrmChamCongNV();
		pContent.add(frmChamCongNV.getFrmChamCong());
		togglebtnSelect(true);

	}


	public void loadFrmQLLuongCN() {
		setTitle("Qu???n l?? l????ng CN");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLLuong.setBackground(new Color(192, 255, 255));
		btnItemQLLuong.setBorder(BorderFactory.createLineBorder(Color.white));
		frmQLLuongCN = new FrmQLLuongCN();
		pContent.add(frmQLLuongCN.getFrmQLLuongCN());
		togglebtnSelect(true);
	}

	public void loadFrmQLLuongNV() {
		setTitle("Qu???n l?? l????ng NV");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLLuong.setBackground(new Color(192, 255, 255));
		btnItemQLLuong.setBorder(BorderFactory.createLineBorder(Color.white));

		frmQLLuongNV = new FrmQLLuongNV();
		pContent.add(frmQLLuongNV.getFrmQLLuongNV());
		togglebtnSelect(true);
	}

//	}
//	
//	public void loadFrmThongKe() {
//		setTitle("Qu???n l?? th???ng k??");
//		resetColorMenu();
//		pContent.removeAll();
//		btnItemTK.setBackground(new Color(192,255,255));
//		btnItemTK.setBorder(BorderFactory.createLineBorder(Color.white));
//		frmThongKe = new FrmThongKe("QL",lblHeaderMaNV.getText(),dNow);
//		pContent.add(frmThongKe.getFrmThongKe());
//		
//	
//	}
	public void setColorActive(JButton btn) {
		btn.setBackground(new Color(192, 255, 255));
		String btnSelectCNText = btnSelectCN.getText().toString();
		String btnSelectNVText = btnSelectNV.getText().toString();
		String btnSelectSPText = btnSelectSP.getText().toString();
		String btnSelectCDText = btnSelectCD.getText().toString();
		String btnSelectTSXText = btnSelectTSX.getText().toString();
		
		
	
		
		
		if(btn.getText().equals(btnSelectCNText)) {
			btnSelectNV.setBackground(new Color(255,255,255));
			btnSelectSP.setBackground(new Color(255,255,255));
			btnSelectCD.setBackground(new Color(255,255,255));
			btnSelectTSX.setBackground(new Color(255,255,255));
		} else if(btn.getText().equals(btnSelectNVText)) {
				btnSelectCN.setBackground(new Color(255,255,255));
				btnSelectSP.setBackground(new Color(255,255,255));
				btnSelectCD.setBackground(new Color(255,255,255));
				btnSelectTSX.setBackground(new Color(255,255,255));
			} else if(btn.getText().equals(btnSelectSPText)) {
				btnSelectCN.setBackground(new Color(255,255,255));
				btnSelectNV.setBackground(new Color(255,255,255));
				btnSelectCD.setBackground(new Color(255,255,255));
				btnSelectTSX.setBackground(new Color(255,255,255));
				} else if(btn.getText().equals(btnSelectCDText)) {
					btnSelectCN.setBackground(new Color(255,255,255));
					btnSelectNV.setBackground(new Color(255,255,255));
					btnSelectSP.setBackground(new Color(255,255,255));
					btnSelectTSX.setBackground(new Color(255,255,255));
					} else {
						btnSelectCN.setBackground(new Color(255,255,255));
						btnSelectNV.setBackground(new Color(255,255,255));
						btnSelectCD.setBackground(new Color(255,255,255));
						btnSelectSP.setBackground(new Color(255,255,255));
						}
	}

	private void togglebtnSelect(boolean visible) {
		btnSelectCD.setVisible(!visible);
		btnSelectSP.setVisible(!visible);
		btnSelectTSX.setVisible(!visible);
		btnSelectCN.setVisible(visible);
		btnSelectNV.setVisible(visible);	
	}

	public void dangXuat() {
		int optDangXuat = JOptionPane.showConfirmDialog(this, "B???n c?? ch???n ch???n mu???n ????ng xu???t kh??ng?", "Th??ng b??o",
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
//		x??? l?? khi click MENUs => l??u l???i currenMenu
//		-------------------------------
//		if(o.equals(btnDangXuat)){
//			dangXuat(); 
//		}
//		
		if(o.equals(btnItemNhanSu)) {
			currenMenu = "MENU_QL_NHANSU";
			setColorActive(btnSelectNV);
			loadFrmNhanVien();
			
		}
		if (o.equals(btnItemQLCC)) {
			currenMenu = "MENU_QLCC";
			loadFrmQLCCNV();
		}
		if(o.equals(btnItemQLSP)) {
			currenMenu = "MENU_QL_SP";
			setColorActive(btnSelectSP);
			loadFrmSanPham();
		}
//		if(o.equals(btnItemQLSP)) {
//			loadFrmDDP();
//		}
		if(o.equals(btnItemQLLuong)) {
			currenMenu = "MENU_QL_LUONG";
			setColorActive(btnSelectNV);
			loadFrmQLLuongNV();
		}	
//		-------------------------------
//		x??? l?? khi click nh??n vi??n ho???c c??ng nh??n
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
		if(o.equals(btnSelectSP)) {
			setColorActive(btnSelectSP);
			switch (currenMenu) {
			case "MENU_QL_SP":
				loadFrmSanPham();
				break;
			}
		}
		if(o.equals(btnSelectCD)) {
			setColorActive(btnSelectCD);
			switch (currenMenu) {
			case "MENU_QL_SP":
				loadFrmCongDoan();
				break;
			}
		}
		if(o.equals(btnSelectTSX)) {
			setColorActive(btnSelectTSX);
			switch (currenMenu) {
			case "MENU_QL_SP":
				loadFrmToSanXuat();
				break;
			}
		}

	}
//		
//		if(o.equals(btnItemTK)) {
//			loadFrmThongKe();
//		}

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
