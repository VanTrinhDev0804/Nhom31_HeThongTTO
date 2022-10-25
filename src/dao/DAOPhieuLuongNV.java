package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.NhanVien;
import entity.PhieuLuongNV;

public class DAOPhieuLuongNV {
	public ArrayList<PhieuLuongNV> getPhieuLuongNV(String maNV){
		ArrayList<PhieuLuongNV> lstPhieuLuongNV = new ArrayList<PhieuLuongNV>();
		
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from PhieuLuongNV where '" + maNV + "'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PhieuLuongNV phieuLuongNV = new PhieuLuongNV();
				phieuLuongNV.setMaNV(new NhanVien(maNV));
				phieuLuongNV.setThang(rs.getDate(3));
				phieuLuongNV.setSoNgayCong(rs.getInt(4));
				phieuLuongNV.setTienLuong(rs.getFloat(5));
				
			
				lstPhieuLuongNV.add(phieuLuongNV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPhieuLuongNV ;
	}
	public ArrayList<PhieuLuongNV> getAllPhieuLuongNV(){
		ArrayList<PhieuLuongNV> lstPhieuLuongNV = new ArrayList<PhieuLuongNV>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
//		select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu, PhieuLuongNV.soNgayCong, PhieuLuongNV.thang, 
//		([heSoLuong]*[luongCB] + [phuCap]) * [soNgayCong] /26 as tienLuong
//		, CTLuongCB.heSoLuong, CTLuongCB.luongCB, CTLuongCB.phuCap
//		from 
//		(PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV) join CTLuongCB on PhieuLuongNV.maNV = CTLuongCB.maNV
//		WHERE Year(PhieuLuongNV.thang) = 2022
//		ORDER BY maNV
//		WHERE MONTH(PhieuLuongNV.thang) = 8
//		for (NhanVien nv : lstNV){
//			for (i = 1 ; i < =12 ; i++){
//				 getChamcong(nv.getMaNV, i , namHientai)
//				tinhLuong();
//				PhieuLuong()	
//		}
		try {
//			PreparedStatement ps = con.prepareStatement("select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu, PhieuLuongNV.soNgayCong, PhieuLuongNV.thang, PhieuLuongNV.tienLuong\r\n"
//					+ "		from PhieuLuongNV\r\n"
//					+ "		Join NhanVien\r\n"
//					+ "		on PhieuLuongNV.maNV = NhanVien.maNV"
//					+ "		ORDER BY maNV");
			PreparedStatement ps = con.prepareStatement("select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu,PhieuLuongNV.soNgayCong,PhieuLuongNV.thang, PhieuLuongNV.tienLuong\r\n"
					+ "		from\n"
					+ "		PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				NhanVien nv = new NhanVien();
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
//	public ArrayList<NhanVien> getAllNVTheoPhieuLuongNV(){
//		ArrayList<NhanVien> list = new ArrayList<NhanVien>();
//		ConnectDB.getinstance();
//		Connection con = ConnectDB.getConnection();
//		try {
//			PreparedStatement ps = con.prepareStatement("select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu, PhieuLuongNV.soNgayCong, PhieuLuongNV.thang, PhieuLuongNV.tienLuong\r\n"
//					+ "		from PhieuLuongNV\r\n"
//					+ "		Join NhanVien\r\n"
//					+ "		on PhieuLuongNV.maNV = NhanVien.maNV");
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				NhanVien nv = new NhanVien();
//				
//				
//				nv.setTenNV(rs.getString(2));
//				nv.setCccd(rs.getString(3));
//				nv.setChucVu(rs.getString(4));
//				
//				list.add(nv);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list ;
//	}
}
