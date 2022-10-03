package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.hwpf.model.types.LSTFAbstractType;

import connection.ConnectDB;
import entity.TaiKhoan;
import entity.ToSanXuat;

public class DAOToSanXuat {
	
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
}
