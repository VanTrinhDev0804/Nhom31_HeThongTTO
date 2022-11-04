package custom;

import java.io.Console;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entity.TemplateCCCN;

public class TblCCModelCtrl extends AbstractTableModel{
	private ArrayList<TemplateCCCN> lstchamCongCN;
	private final Class[] mClasses = {String.class , String.class , String.class , String.class , Integer.class};
	private final String[] columnNames = {"Mã ","Công Nhân" , "Tổ", "Ca làm", "Số lượng " };
		
	
	
	public TblCCModelCtrl(ArrayList<TemplateCCCN> lstchamCongCN) {
		super();
		this.lstchamCongCN = lstchamCongCN;
	}
	
	

	public ArrayList<TemplateCCCN> getLstchamCongCN() {
		return lstchamCongCN;
	}



	public void setLstchamCongCN(ArrayList<TemplateCCCN> lstchamCongCN) {
		this.lstchamCongCN = lstchamCongCN;
	}



	@Override
	public String getColumnName(int column) {
		
		return columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		return mClasses[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex ==0 || columnIndex == 1 || columnIndex == 2) {
			return false;
		}
		return true;
	}



	@Override
	public int getRowCount() {
	
		return lstchamCongCN.size();
	}

	@Override
	public int getColumnCount() {	

		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TemplateCCCN  templateCCCN = lstchamCongCN.get(rowIndex);
		
		switch (columnIndex) {
			case 0: {
				return templateCCCN.getMa();
				
			}
			case 1: {
				return templateCCCN.getTenCN();
			}
			case 2: {
				return templateCCCN.getTenTo();
			}
			case 3: {
				return templateCCCN.getCaLam();
			}
			case 4: {
				return templateCCCN.getSoLuong();
			}
			default:
				return null;
			}
			
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		TemplateCCCN  templateCCCN = lstchamCongCN.get(rowIndex);
		switch (columnIndex) {
		case 0: {
			templateCCCN.setMa((String) aValue);
			break;
		}
		case 1: {
			templateCCCN.setTenCN((String) aValue);
			break;
		}
		case 2: {
			templateCCCN.setTenTo((String) aValue);
			break;
		}
		case 3: {
			templateCCCN.setCaLam((String) aValue);
			break;
		}
		case 4: {
			templateCCCN.setSoLuong((int) aValue);
			break;
		}
		
		}
		
		
	}
	public void removeRow(int row) {
	    // remove a row from your internal data structure
	    fireTableRowsDeleted(row, row);
	}


}
