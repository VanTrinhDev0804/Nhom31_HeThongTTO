package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.ChamCongCN;
import entity.CongNhan;
import entity.ToSanXuat;

public class DAOPhieuChamCong {

	public ArrayList<ChamCongCN> getChamCongCongNhan(String maCN){
		ArrayList<ChamCongCN> lstCCCN = new ArrayList<ChamCongCN>();
		
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from ChamCongCN where maCN = '"+maCN+"' ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ChamCongCN chamCongCN = new ChamCongCN();
				chamCongCN.setMaC(rs.getString(1));
				chamCongCN.setCaLam(rs.getString(2));
				chamCongCN.setNgayLam(rs.getDate(3));
				chamCongCN.setSoLuong(rs.getInt(4));
				chamCongCN.setLuongNgay(rs.getFloat(5));
				
				lstCCCN.add(chamCongCN);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCCCN ;
	}
	//them NV
	public boolean themCCNV(ChamCongCN info) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into ChamCongCN values (?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, info.getMaC());
			ps.setString(2, info.getCaLam());
			ps.setDate(3, info.getNgayLam());
			ps.setInt(4, info.getSoLuong());
			ps.setFloat(5, info.getLuongNgay());
			

			return ps.executeUpdate() > 0;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
//	public ArrayList<ChamCongNV> getChamCongNhanVien(String maNV, int month, int year){
//		ArrayList<ChamCongNV> lstCCNV = new ArrayList<ChamCongNV>();
//		
//		ConnectDB.getinstance();
//		Connection con = ConnectDB.getConnection();
//		try {
//			PreparedStatement ps = con.prepareStatement("select * from ChamCongNV\r\n"
//					+ "where maNV = '"+ maNV + "' and MONTH(ngayVang) = "+ month + " and YEAR(ngayVang) = " + year );
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				ChamCongNV chamCongNV = new ChamCongNV();
//				chamCongCN.setMaC(rs.getString(1));
//				chamCongCN.setCaLam(rs.getString(2));
//				chamCongCN.setNgayLam(rs.getDate(3));
//				chamCongCN.setSoLuong(rs.getInt(4));
//				chamCongCN.setLuongNgay(rs.getFloat(5));
//				
//				lstCCCN.add(chamCongCN);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return lstCCCN ;
//	}
}
