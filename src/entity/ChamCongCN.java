package entity;

import java.sql.Date;
import java.util.Objects;

public class ChamCongCN {
	private String maC;
	private String caLam;
	private Date ngayLam;
	private int soLuong;
	private float luongNgay;
	
	
	
	
	
	public ChamCongCN(String maC, String caLam, Date ngayLam, int soLuong, float luongNgay) {
		super();
		this.maC = maC;
		this.caLam = caLam;
		this.ngayLam = ngayLam;
		this.soLuong = soLuong;
		this.luongNgay = luongNgay;
	}
	public ChamCongCN() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ChamCongCN [maC=" + maC + ", caLam=" + caLam + ", ngayLam=" + ngayLam + ", soLuong=" + soLuong
				+ ", luongNgay=" + luongNgay + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(caLam, luongNgay, maC, ngayLam, soLuong);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChamCongCN other = (ChamCongCN) obj;
		return Objects.equals(caLam, other.caLam)
				&& Float.floatToIntBits(luongNgay) == Float.floatToIntBits(other.luongNgay)
				&& Objects.equals(maC, other.maC) && Objects.equals(ngayLam, other.ngayLam) && soLuong == other.soLuong;
	}
	public String getMaC() {
		return maC;
	}
	public void setMaC(String maC) {
		this.maC = maC;
	}
	public String getCaLam() {
		return caLam;
	}
	public void setCaLam(String caLam) {
		this.caLam = caLam;
	}
	public Date getNgayLam() {
		return ngayLam;
	}
	public void setNgayLam(Date ngayLam) {
		this.ngayLam = ngayLam;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public float getLuongNgay() {
		return luongNgay;
	}
	public void setLuongNgay(float luongNgay) {
		this.luongNgay = luongNgay;
	}
	
	
	
}
