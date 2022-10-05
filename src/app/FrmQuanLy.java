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

	private JPopupMenu popUp;
	private JMenuItem popItem;

	private FrmQLLuongCN FrmQLLuongCN;

	private FrmQLLuongNV FrmQLLuongNV;

	private app.FrmQLLuongCN frmQLLuongCN;

	private app.FrmQLLuongNV frmQLLuongNV;
	
	private String currenMenu = "";
	@SuppressWarnings("deprecation")

	public FrmQuanLy() {
//		this.headerNV = nv;

		IconFontSwing.register(FontAwesome.getIconFont());
		ImageIcon logoApp = (ImageIcon) IconFontSwing.buildIcon(FontAwesome.FOURSQUARE, 50, new Color(164, 44, 167));
		setIconImage(logoApp.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Quản lý nhân viên");
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

		lblHeaderTen = new JLabel();
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
		lblHeaderMaNV.setForeground(Color.WHITE);
		lblHeaderMaNV.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblHeaderMaNV.setBounds(953, 26, 60, 20);
		panel.add(lblHeaderMaNV);

		btnHeaderInfo = new JButton();
		btnHeaderInfo.setText("QL");
//		if(nv.getChucVu().equalsIgnoreCase("Phục vụ"))
//			btnHeaderInfo.setText("PV");
//		else if(nv.getChucVu().equalsIgnoreCase("Quản lý"))
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

		btnItemNhanSu = new JButton("Quản lý Nhân Sự");
		if (btnHeaderInfo.getText().contains("QL")) {

			btnItemNhanSu.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemNhanSu.setBackground(new Color(255, 240, 245));
			btnItemNhanSu.setBounds(x, 0, 132, 31);
			btnItemNhanSu.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemNhanSu);

			x = x + 142; // chuyen vi tri sang mot doan
		}

		btnItemQLSP = new JButton("Quản lý Sản Phẩm");
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

		btnItemQLCC = new JButton("Quản lý Chấm Công");
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
		btnItemQLLuong = new JButton("Quản lý Lương");
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

		btnItemTK = new JButton("Quản lý thống kê");
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
		pSelected.setBounds(0, 715, 236, 25);

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

//		btnSelectNV = new JButton("Nhân Viên");
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
		popItem = new JMenuItem("Trợ giúp");
		popUp.add(popItem);

		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent me) {
				showPopup(me);
			}
		});

//		// Load frm mac dinh 
//		if(btnHeaderInfo.getText().contains("QL")){
//			loadFrmNhanVien();
//		}
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
		popItem.addActionListener(this);

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

	}

	public void loadFrmNhanVien() {
		setTitle("Quản lý nhân viên");
		resetColorMenu();
		pContent.removeAll();
		btnItemNhanSu.setBackground(new Color(192, 255, 255)); // new Color(233,136,236)
		btnItemNhanSu.setBorder(BorderFactory.createLineBorder(Color.white));
//			frmNhanVien = new FrmNhanVien("QL",lblHeaderMaNV.getText(), dNow);
//			pContent.add(frmNhanVien.getPanel());

	}

	public void loadFrmQLCC() {
		setTitle("Quản lý Chấm Công");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLCC.setBackground(new Color(192, 255, 255));
		btnItemQLCC.setBorder(BorderFactory.createLineBorder(Color.white));
		frmChamCongCN = new FrmChamCongCN();
		pContent.add(frmChamCongCN.getFrmChamCong());

	}

//	public void loadFrmQLBH() {
//		setTitle("Quản lý thanh toán");
//		resetColorMenu();
//		pContent.removeAll();
//		btnItemQLCC.setBackground(new Color(192,255,255));
//		btnItemQLCC.setBorder(BorderFactory.createLineBorder(Color.white));
//		frmQLBH = new FrmThanhToan(this,"QL",lblHeaderMaNV.getText(), dNow);
//		pContent.add(frmQLBH.getFrmQLBH());
//	
//	}
//	public void loadFrmDDP() {
//		setTitle("Quản lý đơn đặt phòng");
//		resetColorMenu();
//		pContent.removeAll();
//		btnItemQLSP.setBackground(new Color(192,255,255));
//		btnItemQLSP.setBorder(BorderFactory.createLineBorder(Color.white));
//
//		frmDDP = new FrmDonDatPhong("QL",lblHeaderMaNV.getText(), dNow);
//		pContent.add(frmDDP.getFrmDDP());
//
//		
//
//	
//	}
//	
	public void loadFrmQLLuongCN() {
		setTitle("Quản lý lương CN");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLLuong.setBackground(new Color(192, 255, 255));
		btnItemQLLuong.setBorder(BorderFactory.createLineBorder(Color.white));
//		frmChamCongCN= new FrmChamCongCN();
//		pContent.add(frmChamCongCN.getFrmChamCong());
		frmQLLuongCN = new FrmQLLuongCN();
		pContent.add(frmQLLuongCN.getFrmQLLuongCN());
	}

	public void loadFrmQLLuongNV() {
		setTitle("Quản lý lương NV");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLLuong.setBackground(new Color(192, 255, 255));
		btnItemQLLuong.setBorder(BorderFactory.createLineBorder(Color.white));

		frmQLLuongNV = new FrmQLLuongNV();
		pContent.add(frmQLLuongNV.getFrmQLLuongNV());
	}

//	}
//	
//	public void loadFrmThongKe() {
//		setTitle("Quản lý thống kê");
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
		if(btn.getText().equals(btnSelectCNText)) {
			btnSelectNV.setBackground(new Color(255,255,255));
		}
		else{
			btnSelectCN.setBackground(new Color(255,255,255));
		}
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
//		if(o.equals(btnDangXuat)){
//			dangXuat(); 
//		}
//		
//		if(o.equals(btnItemNhanSu)) {
//			loadFrmNhanVien();
//		}
		if (o.equals(btnItemQLCC)) {
			currenMenu = "MENU_QLCC";
			loadFrmQLCC();
		}
//		if(o.equals(btnItemKH)) {
//			loadFrmKhachHang();
//		}
//		if(o.equals(btnItemQLSP)) {
//			loadFrmDDP();
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
			default:
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
