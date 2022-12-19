package dao;

import java.sql.Connection;
import java.sql.Date;
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
	public boolean themCCCN(ChamCongCN info) throws SQLException {
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
	public ArrayList<ChamCongNV> getDSChamCongNVTungNV(String maNV){
		ArrayList<ChamCongNV> lstCCNV = new ArrayList<ChamCongNV>();
		
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from ChamCongNV where maNV = '"+maNV+"' ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ChamCongNV chamCongNV = new ChamCongNV();
				chamCongNV.setMaNV(rs.getString(1));
				chamCongNV.setNgayVang(rs.getDate(2));
		
				lstCCNV.add(chamCongNV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCCNV ;
	}

	
	
	public boolean themCCNV(ChamCongNV inf) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into ChamCongNV values (?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, inf.getMaNV());
			ps.setDate(2,(Date) inf.getNgayVang());
		
			return ps.executeUpdate() > 0;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
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
				chamCongNV.setMaNV(rs.getString(1));
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

	public int getSoSPSX(String maCN){
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select sum(soLuongSX) from ChamCongCN "
				+ "where maCN = '"+ maCN + "' ";
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
	public Integer getSoSPSXTheoTo(String maTo){
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select sum(soSPSX)\r\n"
				+ "from PhieuLuongCN   join CongNhan on PhieuLuongCN.maCN = CongNhan.maCN\r\n"
				+ "where maTo ='" + maTo + "'";
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
	
	public Integer getTienLuongCN(String maCN, int thang , int nam){
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = " select sum(luongNgay) , MONTH(ngay), maCN from ChamCongCN  "
				+ "where maCN ='"+maCN+"' and MONTH(ngay) = "+thang+" and year(ngay)="+nam+""
						+ "group by MONTH(ngay), maCN ";
				
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
	
	public void xoaPhieuChamCongCN(String ma)throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("delete  from ChamCongCN"
					+ " where maCN = '" + ma +"'");
			 ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void xoaPhieuChamCongCN()throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("delete  from ChamCongCN");
			 ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
