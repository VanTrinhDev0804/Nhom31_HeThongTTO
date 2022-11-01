package dao;

import java.sql.Connection;
<<<<<<< HEAD
import java.sql.Date;
=======
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
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
<<<<<<< HEAD
	public boolean themCCCN(ChamCongCN info) throws SQLException {
=======
	public boolean themCCNV(ChamCongCN info) throws SQLException {
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
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
<<<<<<< HEAD

=======
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
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
<<<<<<< HEAD

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
	
	
	
	
	

=======
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
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
<<<<<<< HEAD
				chamCongNV.setMaNV(rs.getString(1));
=======
				chamCongNV.setMaNV(new NhanVien(rs.getString(1)));
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
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
<<<<<<< HEAD
	
	
=======
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
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
<<<<<<< HEAD
	public Integer getSoSPSX(String maCN, int thang, int nam){
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select sum(soLuongSX) from ChamCongCN "
				+ "where maNV = '"+ maCN + "' and  MONTH(ngay) = " + thang + " and YEAR(ngay) = " + nam;
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
	public Integer getSoSPSX(String maCN){
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select sum(soLuongSX) from ChamCongCN "
				+ "where maNV = '"+ maCN + "' ";
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
	public Integer getTienLuongCN(String maCN, int thang, int nam){
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select sum(luongngay) from ChamCongCN "
				+ "where maCN = '"+ maCN + "' and  MONTH(ngay) = " + thang + " and YEAR(ngay) = " + nam;
=======
//	public int getSoSPSX(String maCN, int thang, int nam){
//		ConnectDB.getinstance();
//		Connection con = ConnectDB.getConnection();
//		String sql = "select sum(soLuongSX) from ChamCongCN "
//				+ "where maNV = '"+ maCN + "' and  MONTH(ngay) = " + thang + " and YEAR(ngay) = " + nam;
//		int dem =0;
//		try {
//			Statement stm = con.createStatement();
//			ResultSet rs = stm.executeQuery(sql);
//			rs.next();
//			dem = rs.getInt(1);
//			rs.close();
//			return dem;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return -1;
//		}			 
//	}
	public int getSoSPSX(String maCN){
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select sum(soLuongSX) from ChamCongCN "
				+ "where maCN = '"+ maCN + "' ";
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
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
<<<<<<< HEAD
=======
//	public Integer getTienLuongCN(String maCN, int thang, int nam){
//		ConnectDB.getinstance();
//		Connection con = ConnectDB.getConnection();
//		String sql = "select sum(luongngay) from ChamCongCN "
//				+ "where maCN = '"+ maCN + "' and  MONTH(ngay) = " + thang + " and YEAR(ngay) = " + nam;
//		int dem =0;
//		try {
//			Statement stm = con.createStatement();
//			ResultSet rs = stm.executeQuery(sql);
//			rs.next();
//			dem = rs.getInt(1);
//			rs.close();
//			return dem;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return -1;
//		}			 
//	}
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
	public Integer getTienLuongCN(String maCN){
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select sum(luongngay) from ChamCongCN "
				+ "where maCN = '" + maCN + "'";
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
<<<<<<< HEAD

=======
	public boolean deleteChamCongNV(String maNV) throws SQLException {
		Connection con = ConnectDB.getConnection();
		String sql = "delete from ChamCongNV where maNV = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maNV);

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.close();
		return false;

	}
	public boolean deleteChamCongCN(String maCN) throws SQLException {
		Connection con = ConnectDB.getConnection();
		String sql = "delete from ChamCongCN where maCN = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maCN);

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.close();
		return false;

	}
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
}
