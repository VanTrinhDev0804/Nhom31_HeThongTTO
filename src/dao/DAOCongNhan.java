package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.CongNhan;
import entity.ToSanXuat;


public class DAOCongNhan {
	
	private DAOToSanXuat daoToSanXuat= new DAOToSanXuat();
	
	//get ds Công nhân
	public ArrayList<CongNhan> getDSCongNhan(){
		ArrayList<CongNhan> lstCN = new ArrayList<CongNhan>();
		
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from CongNhan");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CongNhan CongNhan = new CongNhan();
				
				CongNhan.setMaCN(rs.getString(1));
				CongNhan.setTenCN(rs.getString(2));
				CongNhan.setGioiTinh(rs.getString(4));
				CongNhan.setNgaySinh(rs.getString(5));
				CongNhan.setDiaChi(rs.getString(6));
				CongNhan.setCccd(rs.getString(7));
				CongNhan.setSdt(rs.getString(8));
				
				String maTo= rs.getString(3);
				ToSanXuat toSanXuat  = daoToSanXuat.getToSXfromMaToSX(maTo);
				
				CongNhan.setToSanXuat(toSanXuat);
				
				lstCN.add(CongNhan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCN ;
	}
	
	//get ds Công nhân Cùng tổ
	public ArrayList<CongNhan> getDSCongNhanCungTo(String maToSX){
	ArrayList<CongNhan> lstCN = new ArrayList<CongNhan>();
	ArrayList<CongNhan> lstCNCungTo = new ArrayList<CongNhan>();
		lstCN = getDSCongNhan();
		
		for (CongNhan cn: lstCN) {
			if(cn.getToSanXuat().getMaTo().equalsIgnoreCase(maToSX)) {
				lstCNCungTo.add(cn);
			}
		}
		
		return lstCNCungTo;
	}
	
}
