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
import entity.PhieuLuongCN;
import entity.ToSanXuat;

public class DAOPhieuLuongCN {
	
	public boolean themPhieuLuongCN(PhieuLuongCN phieuLuongCN) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into PhieuLuongCN values (?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, phieuLuongCN.getMaCN().getMaCN());
			ps.setDate(2, new Date(phieuLuongCN.getThang().getTime()));
			ps.setInt(3, phieuLuongCN.getSoSPSX());
			ps.setFloat(4, phieuLuongCN.getTienLuong());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	public void updateLuongCN() {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("Update PhieuLuongCN\r\n"
					+ "Set PhieuLuongCN.tienLuong = ([heSoLuong]*[luongCB] + [phuCap]) * [soNgayCong] /26  from \r\n"
					+ "		(PhieuLuongCN Join CongNhan on PhieuLuongCN.maCN = CongNhan.maCN) join CTLuongCB on PhieuLuongCN.maCN = CTLuongCB.maCN ");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<PhieuLuongCN> getAllPhieuLuongCN(){
		ArrayList<PhieuLuongCN> lstPhieuLuongCN = new ArrayList<PhieuLuongCN>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select PhieuLuongCN.maCN, CongNhan.tenCN, CongNhan.cccd, CongNhan.maTo,PhieuLuongCN.soSPSX,PhieuLuongCN.thang, PhieuLuongCN.tienLuong\r\n"
					+ "		from\n"
					+ "		PhieuLuongCN Join CongNhan on PhieuLuongCN.maCN = CongNhan.maCN");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PhieuLuongCN phieuLuongCN = new PhieuLuongCN();
				phieuLuongCN.setMaCN(new CongNhan(rs.getString(1), rs.getString(2),new ToSanXuat(rs.getString(4)) , null, null, null, rs.getString(3), null));
				phieuLuongCN.setSoSPSX(rs.getInt(5));
				phieuLuongCN.setThang(rs.getDate(6));
				phieuLuongCN.setTienLuong(rs.getFloat(7));
				lstPhieuLuongCN.add(phieuLuongCN);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPhieuLuongCN ;
	}
	public PhieuLuongCN getPhieuLuongCN(String ma){
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PhieuLuongCN phieuLuongCN = new PhieuLuongCN();
		try {
			PreparedStatement ps = con.prepareStatement("select PhieuLuongCN.maCN, CongNhan.tenCN, CongNhan.cccd, CongNhan.maTo,PhieuLuongCN.soSPSX,PhieuLuongCN.thang, PhieuLuongCN.tienLuong\r\n"
					+ "		from\n"
					+ "		PhieuLuongCN Join CongNhan on PhieuLuongCN.maCN = CongNhan.maCN");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {		
//				PhieuLuongCN phieuLuongCN = new PhieuLuongCN();
				phieuLuongCN.setMaCN(new CongNhan(rs.getString(1), rs.getString(2),new ToSanXuat(rs.getString(4)) , null, null, null, rs.getString(3), null));
				phieuLuongCN.setSoSPSX(rs.getInt(5));
				phieuLuongCN.setThang(rs.getDate(6));
				phieuLuongCN.setTienLuong(rs.getFloat(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phieuLuongCN ;
	}
	public ArrayList<PhieuLuongCN> getPhieuLuongCNTheoTen(String ten){
		ArrayList<PhieuLuongCN> lstPhieuLuongCN = new ArrayList<PhieuLuongCN>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select PhieuLuongCN.maCN, CongNhan.tenCN, CongNhan.cccd, CongNhan.chucVu,PhieuLuongCN.soNgayCong,PhieuLuongCN.thang, PhieuLuongCN.tienLuong\r\n"
					+ "		from\n"
					+ "		PhieuLuongCN Join CongNhan on PhieuLuongCN.maCN = CongNhan.maCN"
					+ "where CongNhan.tenCN like N'%"+ten+"%'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PhieuLuongCN phieuLuongCN = new PhieuLuongCN();
//				phieuLuongCN.setMaCN(new CongNhan(rs.getString(1), rs.getString(2), rs.getString(4), null, null, null, rs.getString(3), null));
//				phieuLuongCN.setSoNgayCong(rs.getInt(5));
				phieuLuongCN.setThang(rs.getDate(6));
				phieuLuongCN.setTienLuong(rs.getFloat(7));
				lstPhieuLuongCN.add(phieuLuongCN);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPhieuLuongCN ;
	}
	public ArrayList<PhieuLuongCN> getPhieuLuongCNTheoMa(String ma){
		ArrayList<PhieuLuongCN> lstPhieuLuongCN = new ArrayList<PhieuLuongCN>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select PhieuLuongCN.maCN, CongNhan.tenCN, CongNhan.cccd, CongNhan.chucVu,PhieuLuongCN.soNgayCong,PhieuLuongCN.thang, PhieuLuongCN.tienLuong\r\n"
					+ "		from\n"
					+ "		PhieuLuongCN Join CongNhan on PhieuLuongCN.maCN = CongNhan.maCN"
					+ "where PhieuLuongCN.maCN like N'%"+ma+"%'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PhieuLuongCN phieuLuongCN = new PhieuLuongCN();
//				phieuLuongCN.setMaCN(new CongNhan(rs.getString(1), rs.getString(2), rs.getString(4), null, null, null, rs.getString(3), null));
//				phieuLuongCN.setSoNgayCong(rs.getInt(5));
				phieuLuongCN.setThang(rs.getDate(6));
				phieuLuongCN.setTienLuong(rs.getFloat(7));
				lstPhieuLuongCN.add(phieuLuongCN);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPhieuLuongCN ;
	}
	public ArrayList<PhieuLuongCN> getPhieuLuongCNTheoCCCD(String cccd){
		ArrayList<PhieuLuongCN> lstPhieuLuongCN = new ArrayList<PhieuLuongCN>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select PhieuLuongCN.maCN, CongNhan.tenCN, CongNhan.cccd, CongNhan.chucVu,PhieuLuongCN.soNgayCong,PhieuLuongCN.thang, PhieuLuongCN.tienLuong\r\n"
					+ "		from\n"
					+ "		PhieuLuongCN Join CongNhan on PhieuLuongCN.maCN = CongNhan.maCN"
					+ "where CongNhan.cccd like N'%"+cccd+"%'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PhieuLuongCN phieuLuongCN = new PhieuLuongCN();
//				phieuLuongCN.setMaCN(new CongNhan(rs.getString(1), rs.getString(2), rs.getString(4), null, null, null, rs.getString(3), null));
//				phieuLuongCN.setSoNgayCong(rs.getInt(5));
				phieuLuongCN.setThang(rs.getDate(6));
				phieuLuongCN.setTienLuong(rs.getFloat(7));
				lstPhieuLuongCN.add(phieuLuongCN);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPhieuLuongCN ;
	}
	public Integer getCountLuongCN(String thang){
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select count(*) from PhieuLuongCN "
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
public Integer getCountLuongCN(String maCN, String thang){
	ConnectDB.getinstance();
	Connection con = ConnectDB.getConnection();
	
	String sql = "select count(*) from PhieuLuongCN "
			+ "where maCN = '"+ maCN + "' and thang ='" + thang +"'";
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
public Integer getCountLuongCN(int thang){
	ConnectDB.getinstance();
	Connection con = ConnectDB.getConnection();
	String sql = "select count(*) from PhieuLuongCN "
			+ "where thang = " + thang +"";
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
public Integer getCountLuongCN(String maCN, int thang){
ConnectDB.getinstance();
Connection con = ConnectDB.getConnection();

String sql = "select count(*) from PhieuLuongCN "
		+ "where maNV = '"+ maCN + "' and thang =" + thang +"";
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
public boolean deletePhieuLuongCN(String maCN) throws SQLException {
	Connection con = ConnectDB.getConnection();
	String sql = "delete from PhieuLuongCN where maCN = ?";
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
}

