package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.SanPham;

public class DAOSanPham {
	/**
	 * Lấy danh sách các mặt hàng từ bảng mặt hàng trong SQL Server
	 * @return ArrayList<MatHang> dsMH 
	 */
	public ArrayList<SanPham> getDSSanPham() {
		ArrayList<SanPham> dsSP = new ArrayList<SanPham>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from SanPham";

		try {
			java.sql.Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setMaSP(rs.getString(1));
				sp.setTenSP(rs.getString(2));
				sp.setGiaSX(rs.getFloat(3));
				sp.setSoLuong(rs.getInt(4));
				dsSP.add(sp);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return dsSP;
	}
	/**
	 * Thêm một mặt hàng vào bảng mặt hàng trong SQL server
	 * @param mh
	 * @return MatHang mh
	 */
	public boolean ThemSP(SanPham sp) {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into SanPham values (?,?,?,?)";
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, sp.getMaSP());
			stm.setString(2, sp.getTenSP());
			stm.setFloat(3, sp.getGiaSX());
			stm.setInt(4, sp.getSoLuong());
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
	public void deleteSP(String maSP) {
		PreparedStatement preparedStatement = null;
		try {
			ConnectDB.getinstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql ="delete from SanPham where maSP = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maSP);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	/**
	 * Cập nhật toàn bộ thông tin mặt hàng trên SQL server với các giá trị theo thứ tự
	 * @param mh
	 * @return n>0
	 */
	public boolean updateSP(SanPham sp) {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "update SanPham set tenSP = ?, giaSX = ?, soLuong = ? where maSP = ?";
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement(sql);
			
			stm.setString(1, sp.getTenSP());
			stm.setFloat(2, sp.getGiaSX());
			stm.setInt(3, sp.getSoLuong());
			stm.setString(4, sp.getMaSP());
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
	/**
	 * Lấy danh sách cách mặt hàng theo mã mặt hàng trong bảng hàng từ SQL Server
	 * @param ma
	 * @return MatHang  mh
	 */
	
	public SanPham getSPTheoMaSP(String ma) {

		SanPham sp = new SanPham();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from SanPham where maSP = '"+ ma +"'";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {

				sp.setMaSP(rs.getString(1));
				sp.setTenSP(rs.getString(2));
				sp.setGiaSX(rs.getFloat(3));
				sp.setSoLuong(rs.getInt(4));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sp;
	}
	/**
	 * Lấy mặt hàng theo tên mặt hàng theo mã được truyền vào từ bảng mặt hàng từ SQL Server
	 * @param ma
	 * @return
	 */
	public SanPham getSPTheoTenSP(String ma) {	
		SanPham sp = new SanPham();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from SanPham where tenSP = '"+ ma +"'";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {

				sp.setMaSP(rs.getString(1));
				sp.setTenSP(rs.getString(2));
				sp.setGiaSX(rs.getFloat(3));
				sp.setSoLuong(rs.getInt(4));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sp;
	}

	/**
	 * Lấy mặt hàng theo tên mặt hàng trong bảng mặt hàng từ SQL Server
	 * @param Ten
	 * @return ArrayList<MatHang> lstMatHang
	 */
	public ArrayList<SanPham> getSanPhamTheoTenSanPham(String Ten) {
		ArrayList<SanPham> lsSanPham = new ArrayList<SanPham>();

		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from SanPham where tenSP like N '"+Ten +"'";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				SanPham sp = new SanPham();

				sp.setMaSP(rs.getString(1));
				sp.setTenSP(rs.getString(2));
				sp.setGiaSX(rs.getFloat(3));
				sp.setSoLuong(rs.getInt(4));

				lsSanPham.add(sp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lsSanPham;

	}
<<<<<<< HEAD
=======
//	/**
//	 * Sắp xếp giá mặt hàng theo kiểu tăng dần/ giảm dần thông qua chuỗi truyền vào trong bảng mặt hàng từ SQL Server
//	 * @param ksx
//	 * @return ArrayList<MatHang> lstMatHang
//	 */
//	public ArrayList<MatHang> sortGia(String ksx) {
//		ArrayList<MatHang> lstMH = new ArrayList<>();
//		ConnectDB.getinstance();
//		Connection con = ConnectDB.getConnection();
//		String sql = "select * from MatHang	order by giaMH "+ksx+"";
//		try {
//			PreparedStatement stm = con.prepareStatement(sql);
//			ResultSet rs = stm.executeQuery();
//			while(rs.next()) {
//				MatHang mh = new MatHang();
//				mh.setMaMatHang(rs.getString(1));
//				mh.setLoaiMatHang(new LoaiMatHang(rs.getString(2)));
//				mh.setTenMatHang(rs.getString(3));
//				mh.setSoLuongMatHang(rs.getInt(4));
//				mh.setGiaMatHang(rs.getDouble(5));
//				lstMH.add(mh);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return lstMH;
//	}
//	/**
//	 * Sắp xếp mã loại mặt hàng theo kiểu sắp xếp truyền vào từ bảng loại mặt hàng trong SQL Server
//	 * @param ksx
//	 * @return ArrayList<MatHang> lstMH
//	 */
//	public ArrayList<MatHang> sortLMH(String ksx) {
//		ArrayList<MatHang> lstMH = new ArrayList<>();
//		ConnectDB.getinstance();
//		Connection con = ConnectDB.getConnection();
//		String sql = "select * from MatHang	order by maLoaiMH "+ksx+"";
//		try {
//			PreparedStatement stm = con.prepareStatement(sql);
//			ResultSet rs = stm.executeQuery();
//			while(rs.next()) {
//				MatHang mh = new MatHang();
//				mh.setMaMatHang(rs.getString(1));
//				mh.setLoaiMatHang(new LoaiMatHang(rs.getString(2)));
//				mh.setTenMatHang(rs.getString(3));
//				mh.setSoLuongMatHang(rs.getInt(4));
//				mh.setGiaMatHang(rs.getDouble(5));
//				lstMH.add(mh);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return lstMH;
//	}
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
	/**
	 * Lấy tên mặt hàng với tên cần tìm được truyền vào trong bảng mặt hàng từ SQL Server
	 * @param tenMH
	 * @return ArrayList<MatHang> lstMH
	 */
	public ArrayList<SanPham> getTenSP(String tenSP) {
		ArrayList<SanPham> lstSP = new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from SanPham	where tenSP like N'%"+tenSP+"%'";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				SanPham sp = new SanPham();
				sp.setMaSP(rs.getString(1));
				sp.setTenSP(rs.getString(2));
				sp.setGiaSX(rs.getFloat(3));
				sp.setSoLuong(rs.getInt(4));
				lstSP.add(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstSP;
	}
<<<<<<< HEAD
	
=======
	/**
	 * Lấy toàn bộ thông tin bảng loại mặt hàng theo mã loại mặt hàng được truyền vào 
	 * @param tenLMH
	 * @return ArrayList<MatHang> lstMH
	 */
//	public ArrayList<MatHang> getLMH(String tenLMH) {
//		ArrayList<MatHang> lstMH = new ArrayList<>();
//		ConnectDB.getinstance();
//		Connection con = ConnectDB.getConnection();
//		String sql = "select * from MatHang	where maLoaiMH like N'"+tenLMH+"'";
//		try {
//			PreparedStatement stm = con.prepareStatement(sql);
//			ResultSet rs = stm.executeQuery();
//			while(rs.next()) {
//				MatHang mh = new MatHang();
//				mh.setMaMatHang(rs.getString(1));
//				mh.setLoaiMatHang(new LoaiMatHang(rs.getString(2)));
//				mh.setTenMatHang(rs.getString(3));
//				mh.setSoLuongMatHang(rs.getInt(4));
//				mh.setGiaMatHang(rs.getDouble(5));
//				lstMH.add(mh);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return lstMH;
//	}
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
	public ArrayList<SanPham> getSanPhamDangSanXuat() {
		ArrayList< SanPham> dsSP = new ArrayList<SanPham>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT SanPham.*\r\n"
				+ "FROM  CongDoan INNER JOIN\r\n"
				+ "         SanPham ON CongDoan.maSP = SanPham.maSP\r\n"
				+ "WHERE maCD = N'Đang sản xuất'";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				SanPham sp = new SanPham();
				sp.setMaSP(rs.getString(1));
				sp.setTenSP(rs.getString(2));
				sp.setGiaSX(rs.getFloat(3));
				sp.setSoLuong(rs.getInt(4));
				dsSP.add(sp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dsSP;
	}
	public ArrayList<SanPham> sortTheoMaSP() {
		ArrayList<SanPham> lsSP=new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from SanPham order by maSP desc");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SanPham sp = new SanPham();

				sp.setMaSP(rs.getNString(1));
				sp.setTenSP(rs.getNString(2));
				sp.setGiaSX(rs.getFloat(3));;
				sp.setSoLuong(rs.getInt(4));
				
				lsSP.add(sp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsSP;
	}
	/**
	 * 
	 * @param mã phòng
	 * @return danh sách phòng theo mã
	 */
	public ArrayList<SanPham> getSanPhamTheoMaSP(String ma) {
		ArrayList<SanPham> lsSP=new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from SanPham where maSP = N'"+ma+"' ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SanPham sp = new SanPham();

				sp.setMaSP(rs.getNString(1));
				sp.setTenSP(rs.getNString(2));
				sp.setGiaSX(rs.getFloat(3));;
				sp.setSoLuong(rs.getInt(4));
				
				lsSP.add(sp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsSP;
	}

	/**
	 * 
	 * @param mã phòng
	 * @return thông tin phòng theo mã
	 */
	public SanPham getGiaSPTheoMa(String ma) {

		SanPham sp = new SanPham();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql ="  select * from SanPham where maSP = '"+ma+"'";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {

				sp.setMaSP(rs.getNString(1));
				sp.setTenSP(rs.getNString(2));
				sp.setGiaSX(rs.getFloat(3));;
				sp.setSoLuong(rs.getInt(4));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sp;
	}
	public ArrayList<SanPham> sortTheoGiaSP(String kieuSort) {
		ArrayList<SanPham> lsSP=new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from SanPham order by giaSX  "+ kieuSort+" ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SanPham sp = new SanPham();

				sp.setMaSP(rs.getNString(1));
				sp.setTenSP(rs.getNString(2));
				sp.setGiaSX(rs.getFloat(3));;
				sp.setSoLuong(rs.getInt(4));

				lsSP.add(sp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsSP;
	}
	public ArrayList<SanPham> sortTheoSoLuongSP(String kieuSort) {
		ArrayList<SanPham> lsSP=new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from SanPham order by soLuong  "+ kieuSort+" ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SanPham sp = new SanPham();

				sp.setMaSP(rs.getNString(1));
				sp.setTenSP(rs.getNString(2));
				sp.setGiaSX(rs.getFloat(3));;
				sp.setSoLuong(rs.getInt(4));

				lsSP.add(sp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsSP;
	}
}
