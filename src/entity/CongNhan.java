package entity;

import java.util.Date;
import java.util.Objects;

public class CongNhan {
	private String maCN;
	private String tenCN;
	private ToSanXuat toSanXuat;
	private String gioiTinh;
	private Date ngaySinh;
	private String diaChi;
	private String cccd;
	private String sdt;

	
		
	public CongNhan(String maCN, String tenCN, ToSanXuat toSanXuat, String gioiTinh, Date ngaySinh, String diaChi,
			String cccd, String sdt) {
		super();
		this.maCN = maCN;
		this.tenCN = tenCN;
		this.toSanXuat = toSanXuat;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.cccd = cccd;
		this.sdt = sdt;
	}


	public CongNhan() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public int hashCode() {
		return Objects.hash(cccd, diaChi, gioiTinh, maCN, ngaySinh, sdt, tenCN, toSanXuat);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongNhan other = (CongNhan) obj;
		return Objects.equals(cccd, other.cccd) && Objects.equals(diaChi, other.diaChi)
				&& Objects.equals(gioiTinh, other.gioiTinh) && Objects.equals(maCN, other.maCN)
				&& Objects.equals(ngaySinh, other.ngaySinh) && Objects.equals(sdt, other.sdt)
				&& Objects.equals(tenCN, other.tenCN) && Objects.equals(toSanXuat, other.toSanXuat);
	}


	


	public String getMaCN() {
		return maCN;
	}
	public void setMaCN(String maCN) {
		this.maCN = maCN;
	}
	public String getTenCN() {
		return tenCN;
	}
	public void setTenCN(String tenCN) {
		this.tenCN = tenCN;
	}
	public ToSanXuat getToSanXuat() {
		return toSanXuat;
	}
	public void setToSanXuat(ToSanXuat toSanXuat) {
		this.toSanXuat = toSanXuat;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	

	public Date getNgaySinh() {
		return ngaySinh;
	}


	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
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
		return "CongNhan [maCN=" + maCN + ", tenCN=" + tenCN + ", toSanXuat=" + toSanXuat + ", gioiTinh=" + gioiTinh
				+ ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + ", cccd=" + cccd + ", sdt=" + sdt + "]";
	}
	
	
	
}
