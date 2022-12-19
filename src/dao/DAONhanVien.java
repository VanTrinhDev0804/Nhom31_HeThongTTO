package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.NhanVien;

public class DAONhanVien implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean themNV(NhanVien nv) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into NhanVien values (?,?,?,?,?,?,?,?,N'Đang làm')";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nv.getMaNV());
			ps.setString(2, nv.getTenNV());
			ps.setString(3, nv.getChucVu());
			ps.setDate(4, nv.getNgaySinh());
			ps.setString(5, nv.getGioiTinh());
			ps.setString(6, nv.getDiaChi());
			ps.setString(7, nv.getCccd());
			ps.setString(8, nv.getSdt());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}

	public boolean xoaNV(String maNV) throws SQLException {
		Connection con = ConnectDB.getConnection();
//		String sql = "delete from NhanVien where maNV = ?";
		String sql = "update NhanVien set trangThai = N'Nghỉ việc' where maNV = ?";
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

	public boolean capNhatNV(NhanVien nv, String ma) throws SQLException {
		Connection con = ConnectDB.getConnection();
		String sql = "update NhanVien set tenNV = ?, chucVu = ?, gioiTinh = ?, ngaySinh = ?, diaChi = ?, sdt = ?, cccd = ? where maNV = '"
				+ ma + "'";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nv.getTenNV());
			ps.setString(2, nv.getChucVu());
			ps.setString(3, nv.getGioiTinh());
			ps.setDate(4, nv.getNgaySinh());
			ps.setString(5, nv.getDiaChi());
			ps.setString(6, nv.getSdt());
			ps.setString(7, nv.getCccd());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.close();
		return false;

	}

	// Load tat ca ds NV
	public ArrayList<NhanVien> getAllDanhSachNV() {
		ArrayList<NhanVien> lstNV = new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from [dbo].[NhanVien] where trangThai = N'Đang làm'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNV(rs.getString(1));
				nv.setTenNV(rs.getString(2));
				nv.setChucVu(rs.getString(3));
				nv.setNgaySinh(rs.getDate(4));
				nv.setGioiTinh(rs.getString(5));

				nv.setDiaChi(rs.getString(6));
				nv.setCccd(rs.getString(7));
				nv.setSdt(rs.getString(8));

				lstNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstNV;
	}

//	load nhan vien theo ma
	public ArrayList<NhanVien> getTenNVTheoMa(String maNV) {
		ArrayList<NhanVien> lsNV = new ArrayList<NhanVien>();

		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from NhanVien where maNV = '" + maNV + "'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNV(rs.getString(1));
				nv.setTenNV(rs.getString(2));
				nv.setChucVu(rs.getString(3));
				nv.setNgaySinh(rs.getDate(4));
				nv.setGioiTinh(rs.getString(5));

				nv.setDiaChi(rs.getString(6));
				nv.setCccd(rs.getString(7));
				nv.setSdt(rs.getString(8));

				lsNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsNV;

	}

	// Loc Danh Sach

	public ArrayList<NhanVien> TimKiemNhanVien(String ma, String ten, String chucvu, String ngaysinh, String gioitinh,
			String diachi, String cccd, String sdt) {
		ArrayList<NhanVien> lstNV = new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from [dbo].[NhanVien] where maNV like N'%" + ma + "%' and tenNV like N'%" + ten
				+ "%' and chucVu like N'%" + chucvu + "%' and ngaySinh like N'%" + ngaysinh + "%' and gioiTinh like N'%"
				+ gioitinh + "%' and cccd like N'%" + cccd + "%' and sdt like N'%" + sdt + "%' and diaChi like N'%"
				+ diachi + "%' and trangThai = N'Đang làm'";
		try {

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNV(rs.getString(1));
				nv.setTenNV(rs.getString(2));
				nv.setChucVu(rs.getString(3));
				nv.setNgaySinh(rs.getDate(4));
				nv.setGioiTinh(rs.getString(5));

				nv.setDiaChi(rs.getString(6));
				nv.setCccd(rs.getString(7));
				nv.setSdt(rs.getString(8));

				lstNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstNV;
	}

	public boolean checkmaNV(String maNV) {
		NhanVien nv = new NhanVien();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from [dbo].[NhanVien] where maNV = '" + maNV + "' and trangThai = N'Đang làm'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				nv.setMaNV(rs.getString(1));

				nv.setTenNV(rs.getString(2));
				nv.setChucVu(rs.getString(3));
				nv.setNgaySinh(rs.getDate(4));
				nv.setGioiTinh(rs.getString(5));

				nv.setDiaChi(rs.getString(6));
				nv.setCccd(rs.getString(7));
				nv.setSdt(rs.getString(8));

				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean checkSdtNV(String sdt) {
		NhanVien nv = new NhanVien();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from [dbo].[NhanVien] where sdt = '" + sdt + "' and trangThai = N'Đang làm'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				nv.setMaNV(rs.getString(1));

				nv.setTenNV(rs.getString(2));
				nv.setChucVu(rs.getString(3));
				nv.setNgaySinh(rs.getDate(4));
				nv.setGioiTinh(rs.getString(5));

				nv.setDiaChi(rs.getString(6));
				nv.setCccd(rs.getString(7));
				nv.setSdt(rs.getString(8));

				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean checkCccdNV(String cccd) {
		NhanVien nv = new NhanVien();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from [dbo].[NhanVien] where cccd = '" + cccd + "' and trangThai = N'Đang làm'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				nv.setMaNV(rs.getString(1));

				nv.setTenNV(rs.getString(2));
				nv.setChucVu(rs.getString(3));
				nv.setNgaySinh(rs.getDate(4));
				nv.setGioiTinh(rs.getString(5));

				nv.setDiaChi(rs.getString(6));
				nv.setCccd(rs.getString(7));
				nv.setSdt(rs.getString(8));

				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static NhanVien getNVTheoTK(String maTK) { 
		NhanVien nv = new NhanVien();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from NhanVien where maNV = '"+maTK+"' and trangThai = N'Đang làm'";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				nv.setMaNV(rs.getString(1));

				nv.setTenNV(rs.getString(2));
				nv.setChucVu(rs.getString(3));
				nv.setNgaySinh(rs.getDate(4));
				nv.setGioiTinh(rs.getString(5));

				nv.setDiaChi(rs.getString(6));
				nv.setCccd(rs.getString(7));
				nv.setSdt(rs.getString(8));
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
	public boolean ThemCTLuongCB(NhanVien nv) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		int luongCB = 2000000,phuCap = 500000;
		double heSoLuong = 3.2;
		if(nv.getChucVu().equals("HCNS")) {
			luongCB = 2000000;
			phuCap = 500000;
			heSoLuong = 3.2;
		}
		if(nv.getChucVu().equals("Quản lí")) {
			luongCB = 2000000;
			phuCap = 600000;
			heSoLuong = 4.2;
		}
		if(nv.getChucVu().equals("Chấm công")) {
			luongCB = 2000000;
			phuCap = 400000;
			heSoLuong = 3.2;
		}
		if(nv.getChucVu().equals("Kế toán")) {
			luongCB = 2000000;
			phuCap = 500000;
			heSoLuong = 3.5;
		}
		String sql = "INSERT [dbo].[CTLuongCB] ([maNV], [luongCB], [phuCap], [heSoLuong]) VALUES (N'"+nv.getMaNV()+"', "+luongCB+", "+phuCap+", "+heSoLuong+")";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	public boolean SuaCTLuongCB(NhanVien nv, String manv) throws SQLException{
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		int luongCB = 2000000,phuCap = 500000;
		double heSoLuong = 3.2;
		if(nv.getChucVu().equals("HCNS")) {
			luongCB = 2000000;
			phuCap = 500000;
			heSoLuong = 3.2;
		}
		if(nv.getChucVu().equals("Quản lí")) {
			luongCB = 2000000;
			phuCap = 600000;
			heSoLuong = 4.2;
		}
		if(nv.getChucVu().equals("Chấm công")) {
			luongCB = 2000000;
			phuCap = 400000;
			heSoLuong = 3.2;
		}
		if(nv.getChucVu().equals("Kế toán")) {
			luongCB = 2000000;
			phuCap = 500000;
			heSoLuong = 3.5;
		}
		String sql = "Update CTLuongCB set luongCB=?, phuCap=?, heSoLuong=? where maNV = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, luongCB);
			ps.setInt(2, phuCap);
			ps.setDouble(3, heSoLuong);
			ps.setString(4, manv);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
}
