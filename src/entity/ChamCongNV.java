package entity;

import java.util.Date;

public class ChamCongNV {
	private String maNV;
	private Date ngayVang;
	
	
	
	public ChamCongNV(String maNV, Date ngayVang) {
		super();
		this.maNV = maNV;
		this.ngayVang = ngayVang;
	}
	public ChamCongNV() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ChamCongNV [maNV=" + maNV + ", ngayVang=" + ngayVang + "]";
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public Date getNgayVang() {
		return ngayVang;
	}
	public void setNgayVang(Date ngayVang) {
		this.ngayVang = ngayVang;
	}
	
	

	

	
}
