package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectDB;

public class DAOPhatSinhMa {
	//ma Công nhân
	public String getMaCN() {
		String maCN="";
		try {
			ConnectDB.getinstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select CONCAT('CN', RIGHT(CONCAT('000',ISNULL(right(max(maCN),3),0) + 1),3)) from [dbo].[CongNhan] where maCN like 'CN%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next())
			{
				maCN = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO: handle 
			e.printStackTrace();
		}
		return maCN;
	}

	//ma Tổ sản xuất
	public String getMaToSX() {
		String maTo="";
		try {
			ConnectDB.getinstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select CONCAT('T', RIGHT(CONCAT('000',ISNULL(right(max(maTo),3),0) + 1),3)) from [dbo].[ToSanXuat] where maTo like 'T%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next())
			{
				maTo = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO: handle 
			e.printStackTrace();
		}
		return maTo;
	}

	//maNV
	public String getMaNV() {
		String maNV="";
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('NV', RIGHT(CONCAT('000',ISNULL(right(max(maNV),3),0) + 1),3)) from [dbo].[NhanVien] where maNV like 'NV%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				maNV = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maNV;
	}

	//ma Công Đoạn
	public String getMaCD() {
		String maCD="";
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('CD', RIGHT(CONCAT('000',ISNULL(right(max(maCD),3),0) + 1),3)) from [dbo].[CongDoan] where maCD like  'CD%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				maCD = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maCD;
	}

	//ma San Pham
	public String getMaSP() {
		String maSP="";
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('SP', RIGHT(CONCAT('000',ISNULL(right(max(maSP),3),0) + 1),3)) from SanPham where maSP like  'SP%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				maSP = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maSP;
	}
	



}

