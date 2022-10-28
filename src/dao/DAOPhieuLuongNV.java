package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.NhanVien;
import entity.PhieuLuongNV;

public class DAOPhieuLuongNV {
	
	public boolean themPhieuLuongNV(PhieuLuongNV phieuLuongNV) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into PhieuLuongNV values (?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, phieuLuongNV.getMaNV().getMaNV());
			ps.setDate(2, new Date(phieuLuongNV.getThang().getTime()));
			ps.setInt(3, phieuLuongNV.getSoNgayCong());
			ps.setFloat(4, phieuLuongNV.getTienLuong());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	public void updateLuongNV() {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("Update PhieuLuongNV\r\n"
					+ "Set PhieuLuongNV.tienLuong = ([heSoLuong]*[luongCB] + [phuCap]) * [soNgayCong] /26  from \r\n"
					+ "		(PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV) join CTLuongCB on PhieuLuongNV.maNV = CTLuongCB.maNV ");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<PhieuLuongNV> getAllPhieuLuongNV(){
		ArrayList<PhieuLuongNV> lstPhieuLuongNV = new ArrayList<PhieuLuongNV>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu,PhieuLuongNV.soNgayCong,PhieuLuongNV.thang, PhieuLuongNV.tienLuong\r\n"
					+ "		from\n"
					+ "		PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PhieuLuongNV phieuLuongNV = new PhieuLuongNV();
				phieuLuongNV.setMaNV(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(4), null, null, null, rs.getString(3), null));
				phieuLuongNV.setSoNgayCong(rs.getInt(5));
				phieuLuongNV.setThang(rs.getDate(6));
				phieuLuongNV.setTienLuong(rs.getFloat(7));
				lstPhieuLuongNV.add(phieuLuongNV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPhieuLuongNV ;
	}
	public PhieuLuongNV getPhieuLuongNV(String ma){
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PhieuLuongNV phieuLuongNV = new PhieuLuongNV();
		try {
			PreparedStatement ps = con.prepareStatement("select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu,PhieuLuongNV.soNgayCong,PhieuLuongNV.thang, PhieuLuongNV.tienLuong\r\n"
					+ "		from\n"
					+ "		PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV"
					+ " where PhieuLuongNV.maNV = '" + ma +"'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {		
				phieuLuongNV.setMaNV(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(4), null, null, null, rs.getString(3), null));
				phieuLuongNV.setSoNgayCong(rs.getInt(5));
				phieuLuongNV.setThang(rs.getDate(6));
				phieuLuongNV.setTienLuong(rs.getFloat(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phieuLuongNV ;
	}
	public ArrayList<PhieuLuongNV> getPhieuLuongNVTheoTen(String ten){
		ArrayList<PhieuLuongNV> lstPhieuLuongNV = new ArrayList<PhieuLuongNV>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu,PhieuLuongNV.soNgayCong,PhieuLuongNV.thang, PhieuLuongNV.tienLuong\r\n"
					+ "		from\n"
					+ "		PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV"
					+ "where NhanVien.tenNV like N'%"+ten+"%'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PhieuLuongNV phieuLuongNV = new PhieuLuongNV();
				phieuLuongNV.setMaNV(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(4), null, null, null, rs.getString(3), null));
				phieuLuongNV.setSoNgayCong(rs.getInt(5));
				phieuLuongNV.setThang(rs.getDate(6));
				phieuLuongNV.setTienLuong(rs.getFloat(7));
				lstPhieuLuongNV.add(phieuLuongNV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPhieuLuongNV ;
	}
	public ArrayList<PhieuLuongNV> getPhieuLuongNVTheoMa(String ma){
		ArrayList<PhieuLuongNV> lstPhieuLuongNV = new ArrayList<PhieuLuongNV>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu,PhieuLuongNV.soNgayCong,PhieuLuongNV.thang, PhieuLuongNV.tienLuong\r\n"
					+ "		from\n"
					+ "		PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV"
					+ "where PhieuLuongNV.maNV like N'%"+ma+"%'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PhieuLuongNV phieuLuongNV = new PhieuLuongNV();
				phieuLuongNV.setMaNV(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(4), null, null, null, rs.getString(3), null));
				phieuLuongNV.setSoNgayCong(rs.getInt(5));
				phieuLuongNV.setThang(rs.getDate(6));
				phieuLuongNV.setTienLuong(rs.getFloat(7));
				lstPhieuLuongNV.add(phieuLuongNV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPhieuLuongNV ;
	}
	public ArrayList<PhieuLuongNV> getPhieuLuongNVTheoCCCD(String cccd){
		ArrayList<PhieuLuongNV> lstPhieuLuongNV = new ArrayList<PhieuLuongNV>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu,PhieuLuongNV.soNgayCong,PhieuLuongNV.thang, PhieuLuongNV.tienLuong\r\n"
					+ "		from\n"
					+ "		PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV"
					+ "where NhanVien.cccd like N'%"+cccd+"%'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PhieuLuongNV phieuLuongNV = new PhieuLuongNV();
				phieuLuongNV.setMaNV(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(4), null, null, null, rs.getString(3), null));
				phieuLuongNV.setSoNgayCong(rs.getInt(5));
				phieuLuongNV.setThang(rs.getDate(6));
				phieuLuongNV.setTienLuong(rs.getFloat(7));
				lstPhieuLuongNV.add(phieuLuongNV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPhieuLuongNV ;
	}
	public Integer getCountLuongNV(String thang){
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select count(*) from PhieuLuongNV "
				+ "where thang ='" + thang +"'";
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
public Integer getCountLuongNV(String maNV, String thang){
	ConnectDB.getinstance();
	Connection con = ConnectDB.getConnection();
	
	String sql = "select count(*) from PhieuLuongNV "
			+ "where maNV = '"+ maNV + "' and thang ='" + thang +"'";
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
