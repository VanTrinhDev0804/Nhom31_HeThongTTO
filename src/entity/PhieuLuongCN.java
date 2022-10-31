package entity;

import java.util.Date;

public class PhieuLuongCN {
	private CongNhan maCN;
	private Date thang;
	private int soSPSX;
	private float tienLuong;
	
	public PhieuLuongCN() {
		super();
	}

	public PhieuLuongCN(CongNhan maCN, Date thang, int soSPSX, float tienLuong) {
		super();
		this.maCN = maCN;
		this.thang = thang;
		this.soSPSX = soSPSX;
		this.tienLuong = tienLuong;
	}

	public CongNhan getMaCN() {
		return maCN;
	}

	public void setMaCN(CongNhan maCN) {
		this.maCN = maCN;
	}

	public Date getThang() {
		return thang;
	}

	public void setThang(Date thang) {
		this.thang = thang;
	}

	public int getSoSPSX() {
		return soSPSX;
	}

	public void setSoSPSX(int soSPSX) {
		this.soSPSX = soSPSX;
	}

	public float getTienLuong() {
		return tienLuong;
	}

	public void setTienLuong(float tienLuong) {
		this.tienLuong = tienLuong;
	}
	
	
	
}
