package entity;

import java.util.Date;

public class ChamCongNV {
<<<<<<< HEAD
	private String maNV;
	private Date ngayVang;
	
	
	
	public ChamCongNV(String maNV, Date ngayVang) {
=======
	private NhanVien maNV;
	private Date ngayVang;
	
	public ChamCongNV() {
		super();
	}
	
	public ChamCongNV(NhanVien maNV, Date ngayVang) {
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
		super();
		this.maNV = maNV;
		this.ngayVang = ngayVang;
	}
<<<<<<< HEAD
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
=======
	public NhanVien getMaNV() {
		return maNV;
	}
	public void setMaNV(NhanVien maNV) {
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
		this.maNV = maNV;
	}
	public Date getNgayVang() {
		return ngayVang;
	}
	public void setNgayVang(Date ngayVang) {
		this.ngayVang = ngayVang;
	}
	
	
<<<<<<< HEAD

	

	
=======
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
}
