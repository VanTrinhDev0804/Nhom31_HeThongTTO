package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
				CongNhan.setNgaySinh(rs.getDate(5));
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
	
	//get ds Công nhân theo mã Công Nhân
	public CongNhan getDSCongNhanFromMaCN(String ma){
		
		
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from CongNhan where maCN = '"+ ma+"'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CongNhan congNhan = new CongNhan();
				
				congNhan.setMaCN(rs.getString(1));
				congNhan.setTenCN(rs.getString(2));
				congNhan.setGioiTinh(rs.getString(4));
				congNhan.setNgaySinh(rs.getDate(5));
				congNhan.setDiaChi(rs.getString(6));
				congNhan.setCccd(rs.getString(7));
				congNhan.setSdt(rs.getString(8));
				
				String maTo= rs.getString(3);
				ToSanXuat toSanXuat  = daoToSanXuat.getToSXfromMaToSX(maTo);
				
				congNhan.setToSanXuat(toSanXuat);
				return congNhan;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean themCN(CongNhan cn) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into CongNhan values (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cn.getMaCN());
			ps.setString(2, cn.getTenCN());
			ps.setString(3, cn.getToSanXuat().getMaTo());
			ps.setString(4, cn.getGioiTinh());
			ps.setDate(5, (Date) cn.getNgaySinh());
			ps.setString(6, cn.getDiaChi()); 
			ps.setString(7, cn.getCccd());
			ps.setString(8, cn.getSdt());
			
			return ps.executeUpdate() > 0;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}

	public boolean xoaCN( String maCN) throws SQLException {
		Connection con = ConnectDB.getConnection();
		String sql = "delete from CongNhan where maCN = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maCN);
	
			return ps.executeUpdate() > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		con.close();
		return false;

	}
	public boolean capNhatCN(CongNhan cn, String ma) throws SQLException {
		Connection con = ConnectDB.getConnection();
		String sql = "update CongNhan set tenCN = ?, maTo = ?, gioiTinh = ?, ngaySinh = ?, diaChi = ?, sdt = ?, cccd = ? where maCN = '"+ma+"'";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cn.getTenCN());
			ps.setString(2, cn.getToSanXuat().getMaTo());
			ps.setString(3, cn.getGioiTinh());
			ps.setDate(4, (Date) cn.getNgaySinh());
			ps.setString(5, cn.getDiaChi());
			ps.setString(6, cn.getSdt());
			ps.setString(7, cn.getCccd());
			


			return ps.executeUpdate() > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		con.close();
		return false;

	}

	//Loc Danh Sach

	public ArrayList<CongNhan> TimKiemCongNhan(String ma, String ten, String mato, String ngaysinh, String gioitinh, String diachi, String cccd, String sdt) { 
		ArrayList<CongNhan> lstCN=new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from [dbo].[CongNhan] where maCN like N'%"+ma+"%' and tenCN like N'%"+ten+"%' and maTo like N'%"+mato+"%' and ngaySinh like N'%"+ngaysinh+"%' and gioiTinh like N'%"+gioitinh+"%' and cccd like N'%"+cccd+"%' and sdt like N'%"+sdt+"%' and diaChi like N'%"+diachi+"%'";
		try {
			
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				CongNhan cn=new CongNhan();
				cn.setMaCN(rs.getString(1));
				cn.setTenCN(rs.getString(2));
				cn.setGioiTinh(rs.getString(4));
				cn.setNgaySinh(rs.getDate(5));
				cn.setDiaChi(rs.getString(6));
				cn.setCccd(rs.getString(7));
				cn.setSdt(rs.getString(8));
				String maTo= rs.getString(3);
				ToSanXuat toSanXuat  = daoToSanXuat.getToSXfromMaToSX(maTo);
				cn.setToSanXuat(toSanXuat);
				lstCN.add(cn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCN;
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
	
	
	public boolean checkmaCN(String maCN) { 
		CongNhan cn = new CongNhan();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from [dbo].[CongNhan] where maCN = '"+maCN+"'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				cn.setMaCN(rs.getString(1));
				cn.setTenCN(rs.getString(2));
				cn.setGioiTinh(rs.getString(4));
				cn.setNgaySinh(rs.getDate(5));
				cn.setDiaChi(rs.getString(6));
				cn.setCccd(rs.getString(7));
				cn.setSdt(rs.getString(8));
				String maTo= rs.getString(3);
				ToSanXuat toSanXuat  = daoToSanXuat.getToSXfromMaToSX(maTo);
				cn.setToSanXuat(toSanXuat);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public boolean checkSdtCN(String sdt) { 
		CongNhan cn = new CongNhan();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from [dbo].[CongNhan] where sdt = '"+sdt+"'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				cn.setMaCN(rs.getString(1));
				cn.setTenCN(rs.getString(2));
				cn.setGioiTinh(rs.getString(4));
				cn.setNgaySinh(rs.getDate(5));
				cn.setDiaChi(rs.getString(6));
				cn.setCccd(rs.getString(7));
				cn.setSdt(rs.getString(8));
				String maTo= rs.getString(3);
				ToSanXuat toSanXuat  = daoToSanXuat.getToSXfromMaToSX(maTo);
				cn.setToSanXuat(toSanXuat);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean checkCccdCN(String cccd) { 
		CongNhan cn = new CongNhan();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from [dbo].[CongNhan] where cccd = '"+cccd+"'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				cn.setMaCN(rs.getString(1));
				cn.setTenCN(rs.getString(2));
				cn.setGioiTinh(rs.getString(4));
				cn.setNgaySinh(rs.getDate(5));
				cn.setDiaChi(rs.getString(6));
				cn.setCccd(rs.getString(7));
				cn.setSdt(rs.getString(8));
				String maTo= rs.getString(3);
				ToSanXuat toSanXuat  = daoToSanXuat.getToSXfromMaToSX(maTo);
				cn.setToSanXuat(toSanXuat);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
