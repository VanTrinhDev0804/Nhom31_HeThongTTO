package entity;

import java.io.Serializable;
import java.sql.Date;

public class NhanVien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maNV;
	private String tenNV;
	private String chucVu;
	private Date ngaySinh;
	private String gioiTinh;
	private String diaChi;
	private String cccd;
	private String sdt;
	
	
	

	public NhanVien() {
		super();
	}

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public NhanVien(String maNV, String tenNV, String chucVu, Date ngaySinh, String gioiTinh, String diaChi,
			String cccd, String sdt) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.chucVu = chucVu;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.cccd = cccd;
		this.sdt = sdt;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	
	

	

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", chucVu=" + chucVu + ", ngaySinh=" + ngaySinh
				+ ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", cccd=" + cccd + ", sdt=" + sdt + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNV == null) ? 0 : maNV.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		if (maNV == null) {
			if (other.maNV != null)
				return false;
		} else if (!maNV.equals(other.maNV))
			return false;
		return true;
	}
	
	
	
	
	
	
}
