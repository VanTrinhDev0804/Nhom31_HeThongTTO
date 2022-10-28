package entity;

import java.util.Date;

public class ChamCongNV {
	private NhanVien maNV;
	private Date ngayVang;
	
	public ChamCongNV() {
		super();
	}
	
	public ChamCongNV(NhanVien maNV, Date ngayVang) {
		super();
		this.maNV = maNV;
		this.ngayVang = ngayVang;
	}
	public NhanVien getMaNV() {
		return maNV;
	}
	public void setMaNV(NhanVien maNV) {
		this.maNV = maNV;
	}
	public Date getNgayVang() {
		return ngayVang;
	}
	public void setNgayVang(Date ngayVang) {
		this.ngayVang = ngayVang;
	}
	
	
}
