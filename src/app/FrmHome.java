package app;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAONhanVien;
import dao.DAOPhatSinhMa;
import dao.DAOTaiKhoan;
import dao.Regex;
import entity.CongNhan;
import entity.NhanVien;
import entity.TaiKhoan;
import entity.ToSanXuat;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class FrmHome extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pMain;
	
	
	public JPanel getPanel() {
		return this.pMain;
	}
	
	public FrmHome()  {
		getContentPane().setLayout(null);

		pMain = new JPanel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 629);
		getContentPane().add(pMain);
		pMain.setLayout(null);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 1281, 602);
		pMain.add(lblBackground);
		Image imgBackground = Toolkit.getDefaultToolkit ().getImage ("");
		Image resizeBackground = imgBackground.getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), 0);
		lblBackground.setIcon(new ImageIcon(resizeBackground));
		
		JPanel lblThongTin  = new JPanel();
		lblThongTin.setBounds(0, 600, 1281, 100);
		
		
		
		Button lblDiaChi = new Button("Địa Chỉ");
		Button lblSdt = new Button("Số Điện Thoại");
		Button lblFacebook = new Button("Facebook");
		Button lblWebsite = new Button("Website");
		Button lblEmail = new Button("E-mail");
		
		
		
		lblDiaChi.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSdt.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblFacebook.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblWebsite.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		lblThongTin.add(lblDiaChi);
		lblThongTin.add(lblSdt);
		lblThongTin.add(lblFacebook);
		lblThongTin.add(lblWebsite);
		lblThongTin.add(lblEmail);
		pMain.add(lblThongTin);
		}
	

}