package custom;

import java.awt.Component;
import java.awt.font.ImageGraphicAttribute;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import entity.CongNhan;
import entity.ToSanXuat;

public class FixRenderTree extends DefaultTreeCellRenderer {

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object object, boolean sel, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
	
		Component component = super.getTreeCellRendererComponent(tree, object, sel, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) object;
		
		if(node.getUserObject() instanceof ToSanXuat ) {
			ToSanXuat toSanXuat = (ToSanXuat) node.getUserObject();
			setText(toSanXuat.getTenTo());
			ImageIcon imageIcon = new ImageIcon(toSanXuat.getIconToSX());	
//			ImageIcon imageIcon = new ImageIcon(new ImageIcon("src//resources//iconToSX.png").getImage()
//					.getScaledInstance(32, 32, Image.SCALE_DEFAULT));
			setIcon(imageIcon);
		}
		else if(node.getUserObject() instanceof CongNhan){
			CongNhan cNhan = (CongNhan) node.getUserObject();
//			ImageIcon imageIcon = new ImageIcon("data/icon/iconCN.png");	
			setText(cNhan.getTenCN()); 
//			setIcon(imageIcon);
		}
		else {
			setLeafIcon(null);
			setClosedIcon(null);
			setOpenIcon(null);
		}
		
		return component;
	}
	

}


