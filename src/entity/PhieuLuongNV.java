package entity;

import java.util.Date;

public class PhieuLuongNV {
	private NhanVien maNV;
	private Date thang;
	private int soNgayCong;
	private float tienLuong;
	public PhieuLuongNV() {
		super();
	}
	public PhieuLuongNV(NhanVien maNV, Date thang, int soNgayCong, float tienLuong) {
		super();
		this.maNV = maNV;
		this.thang = thang;
		this.soNgayCong = soNgayCong;
		this.tienLuong = tienLuong;
	}
	public Date getThang() {
		return thang;
	}
	public void setThang(Date thang) {
		this.thang = thang;
	}
	public int getSoNgayCong() {
		return soNgayCong;
	}
	public void setSoNgayCong(int soNgayCong) {
		this.soNgayCong = soNgayCong;
	}
	public float getTienLuong() {
		return tienLuong;
	}
	public void setTienLuong(float tienLuong) {
		this.tienLuong = tienLuong;
	}
	public NhanVien getMaNV() {
		return maNV;
	}
	public void setMaNV(NhanVien maNV) {
		this.maNV = maNV;
	}
	
	
}
