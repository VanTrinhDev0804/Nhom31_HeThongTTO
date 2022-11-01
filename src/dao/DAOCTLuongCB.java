package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.CTLuongCB;
import entity.NhanVien;

public class DAOCTLuongCB {
	public ArrayList<CTLuongCB> getCTLuongCB(String maNV){
		ArrayList<CTLuongCB> lstCTLuongCB = new ArrayList<CTLuongCB>();
		
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from CTLuongCB where maNV = '"+maNV+"' ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CTLuongCB ctLuongCB = new CTLuongCB();
				ctLuongCB.setMaNV(new NhanVien(maNV));
				ctLuongCB.setLuongCB(rs.getFloat(2));
				ctLuongCB.setPhuCap(rs.getFloat(3));
				ctLuongCB.setHeSoLuong(rs.getFloat(4));
				
				
				lstCTLuongCB.add(ctLuongCB);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCTLuongCB ;
	}
	//them NV
	public boolean themCTLuongCB(CTLuongCB info) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into CTLuongCB values (?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, info.getMaNV().getMaNV());
			ps.setFloat(3, info.getLuongCB());
			ps.setFloat(4, info.getPhuCap());
			ps.setFloat(5, info.getHeSoLuong());
			

			return ps.executeUpdate() > 0;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
<<<<<<< HEAD
=======
	public boolean deleteCTLuongCB(String maNV) throws SQLException {
		Connection con = ConnectDB.getConnection();
		String sql = "delete from CTLuongCB where maNV = ?";
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
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
}
