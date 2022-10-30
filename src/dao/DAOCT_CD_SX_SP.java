package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.CT_CD_SX_SP;
import entity.CongDoan;
import entity.SanPham;

public class DAOCT_CD_SX_SP {
	
	public boolean themCT_CD_SX_SP(CT_CD_SX_SP ctcd) {
		
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into CT_CD_SX_SP values (?,?,?)");
			stmt.setString(1, ctcd.getCongDoan().getMaCD());
			stmt.setString(2, ctcd.getSanPham().getMaSP());
			stmt.setFloat(3, ctcd.getGiaSX());
			n = stmt.executeUpdate();
			} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}
		return n>0;
	}
	
	public boolean xoaCT_CD_SX_SP(String maCD,String maSP) {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("delete CTCD where maCD =? and maSP = ?");
			stmt.setString(1, maCD);
			stmt.setString(2, maSP);
			n = stmt.executeUpdate();
			} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n>0;
	}
	
	public boolean suaGiaSX(String maCD,String maSP,float giaSX) {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("update CT_CD_SX_SP set giaSX = "+giaSX+" where maCD =? and maSP = ?");
			stmt.setString(1, maCD);
			stmt.setString(2, maSP);
			n = stmt.executeUpdate();
			} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n>0;
	}
	
	public	CT_CD_SX_SP getCT_CD_SX_SPTheoMa(String maCD,String maSP) {
		CT_CD_SX_SP ctcd = new CT_CD_SX_SP();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM  CT_CD_SX_SP where maCD = '"+maCD+"' and maSP = '"+maSP+"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				
				ctcd.setStt(rs.getInt(1));
				ctcd.setCongDoan(new CongDoan(maCD));;
				ctcd.setSanPham(new SanPham(maSP));
				ctcd.setGiaSX(rs.getFloat(4));		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ctcd;
		
	}
	
	public ArrayList<CT_CD_SX_SP> getCT_CD_SX_SPTheoMaCD(String ma) {
		ArrayList< CT_CD_SX_SP> lsCTCD = new ArrayList<CT_CD_SX_SP>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT CT_CD_SX_SP.*\r\n"
				+ "FROM  CT_CD_SX_SP where maCD = '"+ma+"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				CT_CD_SX_SP ctcd = new CT_CD_SX_SP();
				ctcd.setStt(rs.getInt(1));
				ctcd.setCongDoan(new CongDoan(ma));;
				ctcd.setSanPham(new SanPham(rs.getNString(3)));
				ctcd.setGiaSX(rs.getFloat(4));
				
				lsCTCD.add(ctcd);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lsCTCD;
		
	}
	public void deleteCTTheoMa(String maSP) {
		PreparedStatement preparedStatement = null;
		try {
			ConnectDB.getinstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql ="delete from CT_CD_SX_SP where maSP = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maSP);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
}
