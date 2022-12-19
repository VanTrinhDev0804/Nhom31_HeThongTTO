package app;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.formdev.flatlaf.FlatLightLaf;

import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class FrmLoading extends JFrame{
	JProgressBar jb;
    int i = 0, num = 0;
	private JLabel JLabel;
 
    FrmLoading() {
        jb = new JProgressBar(0, 2000);
        jb.setBounds(0, 380, 971, 20);
 
        jb.setValue(0);
        jb.setStringPainted(true);
 
        add(jb);
        setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		setSize(971,400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.GRAY);
		
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 970, 380);
		getContentPane().add(lblBackground);
		Image imgBackground = Toolkit.getDefaultToolkit ().getImage ("");
		Image resizeBackground = imgBackground.getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), 0);
		lblBackground.setIcon(new ImageIcon(resizeBackground));
    }
 
    public void iterate() {
        while (i <= 2000) {
            jb.setValue(i);
            i = i + 20;
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
        if (i >=  2000) {
        	try {
				UIManager.setLookAndFeel(new FlatLightLaf());
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			IconFontSwing.register(FontAwesome.getIconFont());
			
        	FrmDangNhap dn = new FrmDangNhap();
        	dn.setVisible(true);
        	this.setVisible(false);
        }
        
    }
    public static void main(String[] args) {
    	FrmLoading m = new FrmLoading();
    	m.setVisible(true);
    	m.iterate();
    	
		
	}
}
