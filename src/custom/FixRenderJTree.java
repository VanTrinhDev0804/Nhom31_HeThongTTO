package custom;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import entity.CongNhan;

public class FixRenderJTree extends DefaultListCellRenderer{

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		// TODO Auto-generated method stub
		Component rerender=  super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if(value instanceof CongNhan) {
			CongNhan congNhan = (CongNhan) value;
			((JLabel) rerender).setText(congNhan.getMaCN() + " - "+ congNhan.getTenCN()+ " - "+ congNhan.getSdt() );
		}
		return rerender;
	}
	
}
