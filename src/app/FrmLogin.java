package app;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmLogin extends JFrame {
	
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhau;
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					UIManager.setLookAndFeel(new FlatLightLaf());
					IconFontSwing.register(FontAwesome.getIconFont());
						
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public FrmLogin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Đăng nhập hệ thống");
		setResizable(false);
		setSize(500,500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.GRAY);
	
		
		getContentPane().setBackground(Color.GRAY);
		getContentPane().setForeground(Color.BLACK);
		
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đăng nhập");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel.setLabelFor(this);
		lblNewLabel.setBounds(170, 116, 156, 39);
		getContentPane().add(lblNewLabel);
		
		JLabel lblTenDN = new JLabel("Tài khoản: ");
		lblTenDN.setForeground(Color.WHITE);
		lblTenDN.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTenDN.setBounds(42, 186, 90, 25);
		getContentPane().add(lblTenDN);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTaiKhoan.setText("NV001");
		txtTaiKhoan.setBounds(143, 182, 216, 33);
		getContentPane().add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setForeground(Color.WHITE);
		lblMatKhau.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblMatKhau.setBounds(42, 251, 91, 19);
		getContentPane().add(lblMatKhau);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMatKhau.setBounds(143, 241, 216, 39);
		txtMatKhau.setText("NV001");
		getContentPane().add(txtMatKhau);
		
		JLabel lblQuenMK = new JLabel("Quên mật khẩu ?");
		lblQuenMK.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblQuenMK.setForeground(Color.WHITE);
		lblQuenMK.setBounds(251, 290, 140, 19);
		getContentPane().add(lblQuenMK);
		
		JButton btnDangNhap = new JButton("Đăng Nhập");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDangNhap.setBounds(170, 333, 120, 39);
		getContentPane().add(btnDangNhap);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.setBounds(170, 382, 120, 39);
		getContentPane().add(btnThoat);
		
		
		
		
		
		
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 488, 465);
		getContentPane().add(lblBackground);
		Image imgBackground = Toolkit.getDefaultToolkit ().getImage ("");
		Image resizeBackground = imgBackground.getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), 0);
		lblBackground.setIcon(new ImageIcon(resizeBackground));
	}
}
