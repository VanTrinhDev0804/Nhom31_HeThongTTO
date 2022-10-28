package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.ChamCongCN;
import entity.ChamCongNV;
import entity.NhanVien;

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
	public void xoaPhieuChamCongNV(String ma)throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("delete  from ChamCongNV"
					+ " where maNV = '" + ma +"'");
			 ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void xoaPhieuChamCongNV()throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("delete  from ChamCongNV");
			 ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<ChamCongNV> getChamCongNhanVien(NhanVien nv, int month){
		ArrayList<ChamCongNV> lstCCNV = new ArrayList<ChamCongNV>();
		
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from ChamCongNV\r\n"
					+ "where maNV = '"+ nv.getMaNV() + "' and MONTH(ngayVang) = "+ month + " and YEAR(ngayVang) = 2022"  );
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ChamCongNV chamCongNV = new ChamCongNV();
				chamCongNV.setMaNV(new NhanVien(rs.getString(1)));
				chamCongNV.setNgayVang(rs.getDate(2));
				
//				chamCongCN.setMaC(rs.getString(1));
//				chamCongCN.setCaLam(rs.getString(2));
//				chamCongCN.setNgayLam(rs.getDate(3));
//				chamCongCN.setSoLuong(rs.getInt(4));
//				chamCongCN.setLuongNgay(rs.getFloat(5));
				
				lstCCNV.add(chamCongNV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCCNV ;
	}
	public Integer getSoNgayVangNhanVien(String maNV){
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select count(*) from ChamCongNV "
				+ "where maNV = '"+ maNV + "'";
		int dem =0;
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			rs.next();
			dem = rs.getInt(1);
			rs.close();
			return dem;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}			 
	}

}
