package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.poi.hwpf.model.types.LSTFAbstractType;
import org.apache.poi.ss.formula.functions.T;

import connection.ConnectDB;
import entity.CT_CD_SX_SP;
import entity.CongDoan;
//import entity.MatHang;
import entity.SanPham;
import entity.TaiKhoan;
import entity.ToSanXuat;

public class DAOToSanXuat {

	public boolean ThemTo(ToSanXuat to) {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into ToSanXuat values (?,?,?,?)";
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, to.getMaTo());
			stm.setString(2, to.getTenTo());
			stm.setString(3, to.getMaCD());
			stm.setInt(4, to.getSoLuongCN());
			n= stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stm.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return n>0;
	}
//	get ds tổ sản xuất
	public ArrayList<ToSanXuat> getDSToSanXuat(){
		ArrayList<ToSanXuat> lstToSX = new ArrayList<ToSanXuat>();
		
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from ToSanXuat");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ToSanXuat toSanXuat = new ToSanXuat();
				toSanXuat.setMaTo(rs.getString(1));
				toSanXuat.setTenTo(rs.getString(2));
				toSanXuat.setMaCD(rs.getString(3));
				toSanXuat.setSoLuongCN(rs.getInt(4));
				toSanXuat.setIconToSX("data/icon/iconToSX.png");
				lstToSX.add(toSanXuat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstToSX ;
	}
	public ArrayList<ToSanXuat> getDSToSanXuatTheoMaT(String ma){
		ArrayList<ToSanXuat> lstToSX = new ArrayList<ToSanXuat>();
		
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from ToSanXuat where maTo = '"+ma+"'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ToSanXuat toSanXuat = new ToSanXuat();
				toSanXuat.setMaTo(rs.getString(1));
				toSanXuat.setTenTo(rs.getString(2));
				toSanXuat.setMaCD(rs.getString(3));
				toSanXuat.setSoLuongCN(rs.getInt(4));
				toSanXuat.setIconToSX("data/icon/iconToSX.png");
				lstToSX.add(toSanXuat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstToSX ;
	}
//	Lọc tổ sản xuất theo mã
	public ToSanXuat getToSXfromMaToSX(String maTo) {
		ToSanXuat toSanXuat = new ToSanXuat();
		ArrayList<ToSanXuat> lstTo = getDSToSanXuat();
		for (ToSanXuat to:lstTo) {
			if(maTo.equals(to.getMaTo())) {
				toSanXuat = to;
				
			}	
		}

		return toSanXuat;
	}
	
	public ArrayList<ToSanXuat> getToSanXuatTheoMaCD(String ma) {
		ArrayList<ToSanXuat> lsT = new ArrayList<ToSanXuat>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT ToSanXuat.*\r\n"
				+ "FROM  ToSanXuat where maCD = '"+ma+"'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				ToSanXuat ctT = new ToSanXuat();
				ctT.setMaTo(rs.getNString(1));
				ctT.setTenTo(rs.getNString(2));
				ctT.setMaCD(rs.getString(3));
				ctT.setSoLuongCN(rs.getInt(4));
				
				lsT.add(ctT);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lsT;
		
	}
	public void deleteToTheoMa(String maTo) {
		PreparedStatement preparedStatement = null;
		try {
			ConnectDB.getinstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql ="delete from ToSanXuat where maTo = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maTo);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	/*
	 * @return thông tin phòng theo mã và đang hoạt động, đã được xác nhận đã đặt
	 */
	public ToSanXuat getTSXTheomaCD(String ma) {


		ToSanXuat ctT = null;
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql ="  SELECT * FROM ToSanXuat Where maCD = '"+ma+"'";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				ctT = new ToSanXuat();
				ctT.setMaTo(rs.getNString(1));
				ctT.setTenTo(rs.getNString(2));
				ctT.setMaCD(rs.getString(3));
				ctT.setSoLuongCN(rs.getInt(4));
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ctT;
	}
}
