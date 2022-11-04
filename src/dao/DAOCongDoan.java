package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.CongDoan;
import entity.NhanVien;
import entity.SanPham;
public class DAOCongDoan {

	public boolean themCD(CongDoan cd) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into CongDoan values (?,?,?,?,?,?)");
			ps.setString(1, cd.getMaCD());
			ps.setString(2, cd.getSanPham().getMaSP());
			ps.setString(3, cd.getTenCD());
			ps.setString(4, cd.getTenThanhPham());
			ps.setFloat(5, cd.getGiaSX());
			ps.setString(6, cd.getTrangThaiCD());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	
	public boolean capNhatCD(CongDoan cd, String ma) throws SQLException {
		Connection con = ConnectDB.getConnection();
		String sql = "update CongDoan set tenCD = ?, tenThanhPham = ?, giaSX = ?, trangThaiCD = ? where maCD like '"+ma+"'";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cd.getTenCD());
			ps.setString(2, cd.getTenThanhPham());
			ps.setFloat(3, cd.getGiaSX());
			ps.setString(4, cd.getTrangThaiCD());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	public boolean capNhatTrangThaiCD(String ma) throws SQLException {
		Connection con = ConnectDB.getConnection();
		String sql = "update CongDoan set TrangThaiCD = N'Đang Sản Xuất' where maCD = '"+ma+"'";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}

	public ArrayList<CongDoan> getAllDanhSachCD() {
		ArrayList<CongDoan> lsCD = new ArrayList<CongDoan>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from CongDoan");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CongDoan cd = new CongDoan();
				cd.setMaCD(rs.getString(1));
				cd.setSanPham(new SanPham(rs.getString(2)));
				cd.setTenCD(rs.getString(3));
				cd.setTenThanhPham(rs.getString(4));
				cd.setGiaSX(rs.getFloat(5));
				cd.setTrangThaiCD(rs.getString(6));
				lsCD.add(cd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsCD;
	}
	public ArrayList<CongDoan> getDanhSachCDDangSanXuat() {
		ArrayList<CongDoan> lsCD = new ArrayList<CongDoan>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from CongDoan where TrangThaiCD != N'Ngừng Sản Xuất'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				CongDoan cd = new CongDoan();
				cd.setMaCD(rs.getString(1));
				cd.setSanPham(new SanPham(rs.getString(2)));
				cd.setTenCD(rs.getString(3));
				cd.setTenThanhPham(rs.getString(4));
				cd.setGiaSX(rs.getFloat(5));
				cd.setTrangThaiCD(rs.getString(6));
				lsCD.add(cd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsCD;
	}

	public CongDoan getCDTheoMaSanPham(String ma){
		CongDoan cd = new CongDoan();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT CongDoan.*\r\n"
				+ "FROM  CongDoan\r\n"
				+ "where maSP = N'"+ma+"' and trangThaiCD = N'Đang Sản Xuất'"
				+ "";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				cd.setMaCD(rs.getNString(1));
				cd.setSanPham(new SanPham(rs.getNString(2)));;
				cd.setTenCD(rs.getNString(3));
				cd.setTenThanhPham(rs.getNString(4));
				cd.setGiaSX(rs.getInt(5));
				cd.setTrangThaiCD(rs.getNString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cd;
	}
	public CongDoan getCDTheoMaCD(String ma){
		CongDoan cd = new CongDoan();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT CongDoan.*\r\n"
				+ "FROM  CongDoan\r\n"
				+ "where maCD = N'"+ma+"' and trangThaiCD = N'Đang Sản Xuất'"
				+ "";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {

				cd.setMaCD(rs.getNString(1));
				cd.setSanPham(new SanPham(rs.getNString(2)));;
				cd.setTenCD(rs.getNString(3));
				cd.setTenThanhPham(rs.getNString(4));
				cd.setGiaSX(rs.getInt(5));
				cd.setTrangThaiCD(rs.getNString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cd;
	}
	
	
	
	public ArrayList<CongDoan> getCongDoanTheoMaCD(String ma) {
		ArrayList<CongDoan> lsCD=new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from CongDoan where maCD = N'"+ma+"' ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CongDoan cd = new CongDoan();

				cd.setMaCD(rs.getNString(1));
				cd.setSanPham(new SanPham(rs.getNString(2)));;
				cd.setTenCD(rs.getNString(3));
				cd.setTenThanhPham(rs.getNString(4));
				cd.setGiaSX(rs.getInt(5));
				cd.setTrangThaiCD(rs.getNString(6));
				
				lsCD.add(cd);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsCD;
	}

	public void deleteCDTheoMa(String maSP) {
		PreparedStatement preparedStatement = null;
		try {
			ConnectDB.getinstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql ="delete from CongDoan where maSP = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maSP);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public ArrayList<CongDoan> sortMaCD(String kieuSX) {
		ArrayList<CongDoan> lstCD = new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from CongDoan order by maCD "+kieuSX+"");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CongDoan cd = new CongDoan();

				cd.setMaCD(rs.getNString(1));
				cd.setSanPham(new SanPham(rs.getNString(2)));;
				cd.setTenCD(rs.getNString(3));
				cd.setTenThanhPham(rs.getNString(4));
				cd.setGiaSX(rs.getInt(5));
				cd.setTrangThaiCD(rs.getNString(6));
				
				lstCD.add(cd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCD;
	}
	public ArrayList<CongDoan> sortMaSP(String kieuSX) {
		ArrayList<CongDoan> lstCD = new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from CongDoan order by maSP "+kieuSX+"");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CongDoan cd = new CongDoan();
	
				cd.setMaCD(rs.getNString(1));
				cd.setSanPham(new SanPham(rs.getNString(2)));;
				cd.setTenCD(rs.getNString(3));
				cd.setTenThanhPham(rs.getNString(4));
				cd.setGiaSX(rs.getInt(5));
				cd.setTrangThaiCD(rs.getNString(6));
				
				lstCD.add(cd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCD;
	}
	
	public Integer getGiaTP(String maCD) {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select giaSX from CongDoan\r\n"
				+ "where maCD ='" + maCD +"'";
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
