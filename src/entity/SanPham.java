package entity;

import java.io.Serializable;
import java.util.Objects;

public class SanPham  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String maSP;
	private String tenSP;
	private float giaSX;
	private int soLuong;
	
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPham(String maSP, String tenSP, float giaSX, int soLuong) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.giaSX = giaSX;
		this.soLuong = soLuong;
	}

	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}

	@Override
	public int hashCode() {
		return Objects.hash(giaSX, maSP, soLuong, tenSP);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Float.floatToIntBits(giaSX) == Float.floatToIntBits(other.giaSX) && Objects.equals(maSP, other.maSP)
				&& soLuong == other.soLuong && Objects.equals(tenSP, other.tenSP);
	}
	
	

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public float getGiaSX() {
		return giaSX;
	}

	public void setGiaSX(float giaSX) {
		this.giaSX = giaSX;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", giaSX=" + giaSX + ", soLuong=" + soLuong + "]";
	}
	
}
