package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Panel;
import java.awt.Window;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

import java.awt.BorderLayout;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mindfusion.drawing.Colors;
import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAOCT_CD_SX_SP;
import dao.DAOCongDoan;
import dao.DAOCongNhan;
import dao.DAONhanVien;
import dao.DAOPhieuChamCong;
import dao.DAOPhieuLuongCN;
import dao.DAOPhieuLuongNV;
import dao.DAOSanPham;
import dao.DAOToSanXuat;
import dao.Regex;
import entity.CT_CD_SX_SP;
import entity.ChamCongCN;
import entity.CongDoan;
import entity.CongNhan;
import entity.NhanVien;
import entity.PhieuLuongCN;
import entity.PhieuLuongNV;
import entity.SanPham;
import entity.ToSanXuat;

import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.BorderUIResource;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.table.TableModel;

import org.apache.commons.math3.geometry.Space;

	
public class FrmThongKe extends JFrame implements KeyListener, ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panel pMain;
	private DefaultTableModel modelSanPham;
	private DefaultTableModel modelThanhPham;
	private DefaultTableModel modelCongNhan;
	private DefaultTableModel modelNhanVien;
	private JTable tblNhanVien;
	private JPanel pThongKe;
	private JTable tblSanPham;
	private JTable tblThanhPham;
	private JTable tblCongNhan;
	private JLabel lblTongSP;
	private JTextField txtTongSP;
	private JLabel lblTongTP;
	private JTextField txtTongTP;
	private JLabel lblLuongThangCN;
	private JTextField txtTongLuongThangCN;
	private JLabel lblLuongThangNV;
	private JTextField txtTongLuongThangNV;
	private SimpleDateFormat dfThang;
	private DAOSanPham daoSanPham;
	private DAOCongDoan daoCongDoan;
	private DAOToSanXuat daoToSanXuat;
	private DAOPhieuChamCong daoCCCN;
	private DAOPhieuLuongCN daoPLCN;
	private DAOCongNhan daoCN;
	private DAONhanVien daoNV;
	private DAOPhieuLuongNV daoPLNV;
	private DecimalFormat dfLuong;
	private DAOCT_CD_SX_SP daoCT;
	private SimpleDateFormat dfDate1;
	private DAOPhieuChamCong daoPCC;
	
	public Panel getFrmThongKe() {
		return this.pMain;
	}
	
	
	
	
	public FrmThongKe() {
		
		//Khai báo
		dfDate1 = new SimpleDateFormat("yyyy-MM-dd");
		dfThang = new SimpleDateFormat("MM/yyyy");
		dfLuong = new DecimalFormat("##,###,###");
		daoSanPham = new DAOSanPham();
		daoCongDoan = new DAOCongDoan();
		daoToSanXuat = new DAOToSanXuat();
		daoCCCN = new DAOPhieuChamCong();
		daoPLCN = new DAOPhieuLuongCN();
		daoCN = new DAOCongNhan();
		daoNV = new DAONhanVien();
		daoPLNV = new DAOPhieuLuongNV();
		daoCT = new DAOCT_CD_SX_SP();
		daoPCC = new DAOPhieuChamCong();
		
		setTitle("Thống kê");
		getContentPane().setLayout(null);
		getContentPane().setSize(1299,778);
		pMain= new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1267, 629);
		pMain.setLayout(null);
		getContentPane().add(pMain);
		
		pThongKe = new JPanel();
		pThongKe.setBorder(new LineBorder(new Color(164, 44, 167), 2, true));
		pThongKe.setBackground(Color.WHITE);
		pThongKe.setBounds(10, 11, 1252, 609);
		pMain.add(pThongKe);
		pThongKe.setLayout(null);
		
		//Sản phẩm
				JLabel lblSP = new JLabel("Sản phẩm");
				lblSP.setBounds(10, 11, 1232, 20);
				lblSP.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
				lblSP.setFont(new Font("SansSerif", Font.BOLD, 18));
				pThongKe.add(lblSP);
				
				String sp [] = {"Mã sản phẩm", "Tên sản phẩm", "Số Lượng"};
				modelSanPham = new DefaultTableModel(sp,0);
				
				tblSanPham = new JTable(modelSanPham);
				tblSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				tblSanPham.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				tblSanPham.setShowHorizontalLines(true);
				tblSanPham.setShowGrid(true);
				tblSanPham.setBackground(Color.WHITE);
				tblSanPham.setFont(new Font("SansSerif", Font.PLAIN, 14));
				tblSanPham.setSelectionBackground(new Color(164, 44, 167, 30));
				tblSanPham.setSelectionForeground(new Color(114, 23, 153));
				tblSanPham.setRowHeight(30);
				
				JTableHeader tbHeaderSP = tblSanPham.getTableHeader();
				tbHeaderSP.setBackground(new Color(164, 44, 167));
				tbHeaderSP.setForeground(Color.white);
				tbHeaderSP.setFont(new Font("SansSerif", Font.BOLD, 14));
				
				JScrollPane spSanPham = new JScrollPane(tblSanPham, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				spSanPham.setBounds(10, 35, 1232, 100);
				spSanPham.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
				spSanPham.setBackground(new Color(164, 44, 167));
				spSanPham.getHorizontalScrollBar();
				pThongKe.add(spSanPham);
				
				tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(200);
				tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(250);
				tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(450);
				
				//Thành phẩm
				JLabel lblTP = new JLabel("Thành phẩm");
				lblTP.setBounds(10, 146, 1232, 20);
				lblTP.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
				lblTP.setFont(new Font("SansSerif", Font.BOLD, 18));
				pThongKe.add(lblTP);
				
				String tp [] = {"Tên thành phẩm", "Số Lượng"};
				modelThanhPham = new DefaultTableModel(tp,0);
				
				tblThanhPham = new JTable(modelThanhPham);
				tblThanhPham.setShowHorizontalLines(true);
				tblThanhPham.setShowGrid(true);
				tblThanhPham.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				tblThanhPham.setSelectionForeground(new Color(114, 23, 153));
				tblThanhPham.setSelectionBackground(new Color(164, 44, 167, 30));
				tblThanhPham.setRowHeight(30);
				tblThanhPham.setFont(new Font("SansSerif", Font.PLAIN, 14));
				tblThanhPham.setBackground(Color.WHITE);
				
				JTableHeader tbHeaderTP = tblThanhPham.getTableHeader();
				tbHeaderTP.setBackground(new Color(164, 44, 167));
				tbHeaderTP.setForeground(Color.white);
				tbHeaderTP.setFont(new Font("SansSerif", Font.BOLD, 14));
				
				JScrollPane spThanhPham = new JScrollPane(tblThanhPham, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				spThanhPham.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
				spThanhPham.setBounds(10, 171, 1232, 100);
				spThanhPham.setBackground(new Color(164, 44, 167));
				spThanhPham.getHorizontalScrollBar();
				pThongKe.add(spThanhPham);
				
				tblThanhPham.getColumnModel().getColumn(0).setPreferredWidth(450);
				tblThanhPham.getColumnModel().getColumn(1).setPreferredWidth(450);
				
				//Công nhân
				JLabel lblCN = new JLabel("Công nhân");
				lblCN.setBounds(10, 281, 1232, 20);
				lblCN.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
				lblCN.setFont(new Font("SansSerif", Font.BOLD, 18));
				pThongKe.add(lblCN);
				
				String cn [] = {"Mã công nhân", "Tên công nhân", "Tổ", "Số thành phẩm sản xuất", "Tiền lương"};
				modelCongNhan = new DefaultTableModel(cn,0);
				
				tblCongNhan = new JTable(modelCongNhan);
				tblCongNhan.setShowHorizontalLines(true);
				tblCongNhan.setShowGrid(true);
				tblCongNhan.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				tblCongNhan.setSelectionForeground(new Color(114, 23, 153));
				tblCongNhan.setSelectionBackground(new Color(164, 44, 167, 30));
				tblCongNhan.setRowHeight(30);
				tblCongNhan.setFont(new Font("SansSerif", Font.PLAIN, 14));
				tblCongNhan.setBackground(Color.WHITE);
				
				JTableHeader tbHeaderCN = tblCongNhan.getTableHeader();
				tbHeaderCN.setBackground(new Color(164, 44, 167));
				tbHeaderCN.setForeground(Color.white);
				tbHeaderCN.setFont(new Font("SansSerif", Font.BOLD, 14));
				
				JScrollPane spCongNhan = new JScrollPane(tblCongNhan, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				spCongNhan.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
				spCongNhan.setBounds(10, 307, 1232, 100);
				spCongNhan.setBackground(new Color(164, 44, 167));
				spCongNhan.getHorizontalScrollBar();
				pThongKe.add(spCongNhan);
				
				tblCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
				tblCongNhan.getColumnModel().getColumn(1).setPreferredWidth(250);
				tblCongNhan.getColumnModel().getColumn(2).setPreferredWidth(50);
				tblCongNhan.getColumnModel().getColumn(3).setPreferredWidth(250);
				tblCongNhan.getColumnModel().getColumn(4).setPreferredWidth(150);
				
				//Nhân viên
				JLabel lblNV = new JLabel("Nhân viên");
				lblNV.setBounds(10, 416, 1232, 20);
				lblNV.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
				lblNV.setFont(new Font("SansSerif", Font.BOLD, 18));
				pThongKe.add(lblNV);
				
				String nv [] = {"Mã nhân viên", "Tên nhân viên","Tiền lương"};
				modelNhanVien = new DefaultTableModel(nv,0);
				
				tblNhanVien = new JTable(modelNhanVien);
				tblNhanVien.setShowHorizontalLines(true);
				tblNhanVien.setShowGrid(true);
				tblNhanVien.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				tblNhanVien.setSelectionForeground(new Color(114, 23, 153));
				tblNhanVien.setSelectionBackground(new Color(164, 44, 167, 30));
				tblNhanVien.setRowHeight(30);
				tblNhanVien.setFont(new Font("SansSerif", Font.PLAIN, 14));
				tblNhanVien.setBackground(Color.WHITE);
				
				JTableHeader tbHeaderNV = tblNhanVien.getTableHeader();
				tbHeaderNV.setBackground(new Color(164, 44, 167));
				tbHeaderNV.setForeground(Color.white);
				tbHeaderNV.setFont(new Font("SansSerif", Font.BOLD, 14));
				
				JScrollPane spNhanVien = new JScrollPane(tblNhanVien, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				spNhanVien.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
				spNhanVien.setBounds(10, 443, 1232, 100);
				spNhanVien.setBackground(new Color(164, 44, 167));
				spNhanVien.getHorizontalScrollBar();
				pThongKe.add(spNhanVien);
				
				tblNhanVien.getColumnModel().getColumn(0).setPreferredWidth(250);
				tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(250);
				tblNhanVien.getColumnModel().getColumn(2).setPreferredWidth(250);
				
				//Tổng sản phẩm
				lblTongSP = new JLabel("Tổng sản phẩm:");
				lblTongSP.setBounds(10, 551, 175, 22);
				lblTongSP.setFont(new Font("SansSerif", Font.PLAIN, 14));
				pThongKe.add(lblTongSP);
				
				txtTongSP = new JTextField();
				txtTongSP.setBounds(130, 551, 100, 20);
				txtTongSP.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
				txtTongSP.setFont(new Font("SansSerif", Font.PLAIN, 12));
				pThongKe.add(txtTongSP);
				
				//Tổng thành phẩm
				lblTongTP = new JLabel("Tổng thành phẩm:");
				lblTongTP.setBounds(10, 580, 175, 22);
				lblTongTP.setFont(new Font("SansSerif", Font.PLAIN, 14));
				pThongKe.add(lblTongTP);
						
				txtTongTP = new JTextField();
				txtTongTP.setBounds(130, 580, 100, 20);
				txtTongTP.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
				txtTongTP.setFont(new Font("SansSerif", Font.PLAIN, 12));
				pThongKe.add(txtTongTP);
						
				//Tổng lương chi trả công nhân
				lblLuongThangCN = new JLabel("Tổng lương trả công nhân:");
				lblLuongThangCN.setBounds(250, 551, 175, 22);
				lblLuongThangCN.setFont(new Font("SansSerif", Font.PLAIN, 14));
				pThongKe.add(lblLuongThangCN);
						
				txtTongLuongThangCN = new JTextField();
				txtTongLuongThangCN.setBounds(435, 551, 100, 20);
				txtTongLuongThangCN.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
				txtTongLuongThangCN.setFont(new Font("SansSerif", Font.PLAIN, 12));
				pThongKe.add(txtTongLuongThangCN);
				
				//Tổng lương chi trả nhân viên
				lblLuongThangNV = new JLabel("Tổng lương trả nhân viên:");
				lblLuongThangNV.setBounds(250, 580, 175, 22);
				lblLuongThangNV.setFont(new Font("SansSerif", Font.PLAIN, 14));
				pThongKe.add(lblLuongThangNV);
						
				txtTongLuongThangNV = new JTextField();
				txtTongLuongThangNV.setBounds(435, 580, 100, 20);
				txtTongLuongThangNV.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
				txtTongLuongThangNV.setFont(new Font("SansSerif", Font.PLAIN, 12));
				pThongKe.add(txtTongLuongThangNV);
				
				loadThongKe();
				tongSP();
				tongTP();
				tongLuongTraCN();
				tongLuongTraNV();
		
		
		//connectDB
				try {
					ConnectDB.getinstance().connect();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
	}


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
//		if (o.equals(btnThongKe)) {
//			loadThongKe();
//			tongSP();
//			tongTP();
//			tongLuongTraCN();
//			tongLuongTraNV();
//		}
		
	}
	
	
	private void tongLuongTraNV() {
		float tongLuongNV = 0;
		ArrayList<NhanVien> lsNV = daoNV.getAllDanhSachNV();
		for(NhanVien nv : lsNV) {
			ArrayList<PhieuLuongNV> lsPLNV = daoPLNV.getPhieuLuongNVTheoMa(nv.getMaNV());
			System.out.println( "danh sách NV1 :" + lsPLNV);
			for(PhieuLuongNV phieuLuongNV : lsPLNV) {
				System.out.println("danh sách NV2 :" + phieuLuongNV);
				float tongLuong = daoPLNV.getCountLuongNV(nv.getMaNV(), dfDate1.format(new Date()));
				tongLuongNV += tongLuong;
			}
		}
		txtTongLuongThangNV.setText(String.valueOf(dfLuong.format(tongLuongNV) ) + " vnđ");
		
	}




	private void tongLuongTraCN() {
		float tongLuongCN = 0;
		ArrayList<CongNhan> lsCN = daoCN.getDSCongNhan();
		for(CongNhan cn : lsCN) {
			ArrayList<ChamCongCN> lsCCCN = daoCCCN.getChamCongCongNhan(cn.getMaCN());
			for(ChamCongCN chamCongCN : lsCCCN) {
				
				float tongLuong = daoCCCN.getTienLuongCN(chamCongCN.getMaC());
				tongLuongCN += tongLuong;
			}
					
		}
			
			
		
			
		
		txtTongLuongThangCN.setText(String.valueOf(dfLuong.format(tongLuongCN) ) + " vnđ");
		
	}




	private void tongTP() {
		int tongTP = 0;
			ArrayList<CongNhan> lsCN = daoCN.getDSCongNhan();
			for(CongNhan cn : lsCN) {
				ArrayList<ChamCongCN> lsCCCN = daoCCCN.getChamCongCongNhan(cn.getMaCN());
				for(ChamCongCN chamCongCN : lsCCCN) {
					int soTP = daoCCCN.getSoSPSX(chamCongCN.getMaC());
					tongTP += soTP;
				}
			}
		
		txtTongTP.setText(String.valueOf(tongTP) + " chiếc");
		
	}




	private void tongSP() {
		int tongSP = 0;
		ArrayList<CongDoan> lsCD = daoCongDoan.getDanhSachCDDangSanXuat();
		for(CongDoan cd : lsCD) {
			ArrayList<CT_CD_SX_SP> lsChiTiet = daoCT.getCT_CD_SX_SPTheoMaCD(cd.getMaCD());
			for(CT_CD_SX_SP chiTiet : lsChiTiet) {
				ArrayList<SanPham> lsSP = daoSanPham.getSanPhamTheoMaSP(chiTiet.getSanPham().getMaSP());
				for(SanPham sp : lsSP) {
					int soSP = daoSanPham.getSoLuongSP(chiTiet.getSanPham().getMaSP());
					tongSP += soSP;
				}
			}
		}
		txtTongSP.setText(String.valueOf(tongSP) + " chiếc");
		
	}




	private void loadThongKe() {
		
		//Thống kê sản phẩm
		ArrayList<SanPham> lsSP = daoSanPham.getDSSanPham();
		for(SanPham sp : lsSP) {
			modelSanPham.addRow(new Object[] {sp.getMaSP(), sp.getTenSP()
					, sp.getSoLuong()});
		}
		
		//Thống kê thành phẩm
		ArrayList<CongDoan> lsCD = daoCongDoan.getDanhSachCDDangSanXuat();
			for(CongDoan cd : lsCD) {
				ArrayList<CongNhan> lsCN = daoCN.getDSCongNhan();
				for(CongNhan cn : lsCN) {
					ArrayList<ChamCongCN> lsCCCN = daoCCCN.getChamCongCongNhan(cn.getMaCN());
					for(ChamCongCN chamCongCN : lsCCCN) {
						modelThanhPham.addRow(new Object[] {cd.getTenThanhPham(), chamCongCN.getSoLuong()});
						
					}
				}
				
				
				
			}
		//Thống kê công nhân
		ArrayList<ToSanXuat> lsToSanXuat = daoToSanXuat.getDSToSanXuat();
		for(ToSanXuat tsx : lsToSanXuat) {
			ArrayList<CongNhan> lsCN = daoCN.getDSCongNhanCungTo(tsx.getMaTo());
			for(CongNhan cn : lsCN) {
				ArrayList<ChamCongCN> lsCCCN = daoCCCN.getChamCongCongNhan(cn.getMaCN());
				for(ChamCongCN chamCongCN : lsCCCN) {
					modelCongNhan.addRow(new Object[] {cn.getMaCN(), cn.getTenCN()
							, cn.getToSanXuat().getTenTo(), chamCongCN.getSoLuong(), dfLuong.format(chamCongCN.getLuongNgay()) });
				}
			}
		}
		//Thống kê nhân viên
		
		ArrayList<NhanVien> lsNV = daoNV.getAllDanhSachNV();
		for(NhanVien nv : lsNV) {
			ArrayList<PhieuLuongNV> lsPLNV = daoPLNV.getAllPhieuLuongNV();
			
			for(PhieuLuongNV phieuLuongNV : lsPLNV) {
				modelNhanVien.addRow(new Object [] {nv.getMaNV(), nv.getTenNV(), dfLuong.format(phieuLuongNV.getTienLuong())});
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

