package custom;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import entity.CongNhan;
import entity.NhanVien;

public class FixRenderJList extends DefaultListCellRenderer{
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		// TODO Auto-generated method stub
		Component rerender=  super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if(value instanceof CongNhan) {
			CongNhan congNhan = (CongNhan) value;
			((JLabel) rerender).setText(congNhan.getMaCN() + " - "+ congNhan.getTenCN()+ " - "+ congNhan.getSdt() );
		}
		if(value instanceof NhanVien) {
			NhanVien nhanVien = (NhanVien) value;
			((JLabel) rerender).setText(nhanVien.getMaNV()+ " - "+ nhanVien.getTenNV());
		}
		return rerender;
	}
}
