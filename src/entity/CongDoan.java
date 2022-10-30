package entity;

import java.io.Serializable;
import java.util.Objects;

public class CongDoan  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String maCD;
	private String tenCD;
	private String tenThanhPham;
	private float giaSX;
	private String trangThaiCD;
	
	private SanPham sanPham;

	@Override
	public int hashCode() {
		return Objects.hash(giaSX, maCD, sanPham, tenCD, tenThanhPham, trangThaiCD);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongDoan other = (CongDoan) obj;
		return Float.floatToIntBits(giaSX) == Float.floatToIntBits(other.giaSX) && Objects.equals(maCD, other.maCD)
				&& Objects.equals(sanPham, other.sanPham) && Objects.equals(tenCD, other.tenCD)
				&& Objects.equals(tenThanhPham, other.tenThanhPham) && Objects.equals(trangThaiCD, other.trangThaiCD);
	}

	public CongDoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CongDoan(String maCD) {
		super();
		this.maCD = maCD;
	}

	public CongDoan(String maCD, String tenCD, String tenThanhPham, float giaSX, String trangThaiCD, SanPham sanPham) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
		this.tenThanhPham = tenThanhPham;
		this.giaSX = giaSX;
		this.trangThaiCD = trangThaiCD;
		this.sanPham = sanPham;
	}

	public String getMaCD() {
		return maCD;
	}

	public void setMaCD(String maCD) {
		this.maCD = maCD;
	}

	public String getTenCD() {
		return tenCD;
	}

	public void setTenCD(String tenCD) {
		this.tenCD = tenCD;
	}

	public String getTenThanhPham() {
		return tenThanhPham;
	}

	public void setTenThanhPham(String tenThanhPham) {
		this.tenThanhPham = tenThanhPham;
	}

	public float getGiaSX() {
		return giaSX;
	}

	public void setGiaSX(float giaSX) {
		this.giaSX = giaSX;
	}

	public String getTrangThaiCD() {
		return trangThaiCD;
	}

	public void setTrangThaiCD(String trangThaiCD) {
		this.trangThaiCD = trangThaiCD;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CongDoan [maCD=" + maCD + ", tenCD=" + tenCD + ", tenThanhPham=" + tenThanhPham + ", giaSX=" + giaSX
				+ ", trangThaiCD=" + trangThaiCD + ", sanPham=" + sanPham + "]";
	}
	
		
}
