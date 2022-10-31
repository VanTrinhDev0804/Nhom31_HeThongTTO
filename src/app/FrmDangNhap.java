package app;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.formdev.flatlaf.FlatLightLaf;

import connection.ConnectDB;
import dao.DAOTaiKhoan;
import entity.TaiKhoan;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FrmDangNhap extends JFrame implements ActionListener,MouseListener, KeyListener {
	
	
	private DAOTaiKhoan daoTK;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhau;
	private JButton btnThoat;
	private JButton btnDangNhap;
	
	
	public static void main(String[] args) {

//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//
//					 for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//					        if ("Nimbus".equals(info.getName())) {
//					            UIManager.setLookAndFeel(info.getClassName());
//					            break;
//					        }
//					    }
//					IconFontSwing.register(FontAwesome.getIconFont());
//						
//					FrmDangNhap frame = new FrmDangNhap();
//					frame.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatLightLaf());
					IconFontSwing.register(FontAwesome.getIconFont());

					FrmDangNhap frame = new FrmDangNhap();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
					
					
	}
	public FrmDangNhap() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Đăng nhập hệ thống");
		setResizable(false);
		setSize(500,500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.GRAY);

		//connect database
		try {
			ConnectDB.getinstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
//		dao
		daoTK = new DAOTaiKhoan();
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
		
		btnDangNhap = new JButton("Đăng Nhập");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDangNhap.setBounds(170, 333, 120, 39);
		getContentPane().add(btnDangNhap);
		
		btnThoat = new JButton("Thoát");
		btnThoat.setBounds(170, 382, 120, 39);
		getContentPane().add(btnThoat);
		
		
		
		
		
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 488, 465);
		getContentPane().add(lblBackground);
		Image imgBackground = Toolkit.getDefaultToolkit ().getImage ("");
		Image resizeBackground = imgBackground.getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), 0);
		lblBackground.setIcon(new ImageIcon(resizeBackground));
		
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
		
		lblQuenMK.addMouseListener(this);
		
		btnDangNhap.addKeyListener(this);
		btnThoat.addKeyListener(this);
		txtMatKhau.addKeyListener(this);
		txtTaiKhoan.addKeyListener(this);
//		popItem.addActionListener(this);
	
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnThoat)) {
			System.exit(0);
		}
		else if(o.equals(btnDangNhap)) {	
			
			dangNhap();
			
		}
		
	
	}
	private void dangNhap() {
		// TODO Auto-generated method stub

//		FrmQuanLy frmQuanLy = new FrmQuanLy();
//		frmQuanLy.setVisible(true);
//		this.setVisible(false);
		
		
		String maTK = txtTaiKhoan.getText().toString().trim();

		String mk = txtMatKhau.getText().toString().trim();
		TaiKhoan tk = daoTK.getTaiKhoanTheoMa(maTK);
	
		
		
		
		if(tk.getMaTK() == null) {
			JOptionPane.showMessageDialog(this, "Tài khoản không đúng!\nVui lòng kiểm tra lại.");
		}
		else if(!tk.getMatKhau().equalsIgnoreCase(mk)){
			JOptionPane.showMessageDialog(this, "Mật khẩu không đúng!\nVui lòng kiểm tra lại.");
		}	
		else {
//			NhanVien nv = daoNhanVien.getNVTheoTK(tk.getMaTK());
				FrmQuanLy frmQL = new FrmQuanLy();
				frmQL.setVisible(true);
				this.setVisible(false);
			}
			
			}
}
