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
	private int soLuongTP;
	private int soLuongDSX;
	
	private ToSanXuat toSanXuat;
	private SanPham sanPham;
	public CongDoan(String maCD, String tenCD, String tenThanhPham, float giaSX, String trangThaiCD, int soLuongTP,
			ToSanXuat toSanXuat, SanPham sanPham) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
		this.tenThanhPham = tenThanhPham;
		this.giaSX = giaSX;
		this.trangThaiCD = trangThaiCD;
		this.soLuongTP = soLuongTP;
		this.toSanXuat = toSanXuat;
		this.sanPham = sanPham;
	}
	
	
	public int getSoLuongDSX() {
		return soLuongDSX;
	}


	public void setSoLuongDSX(int soLuongDSX) {
		this.soLuongDSX = soLuongDSX;
	}


	public CongDoan(String ma) {
		// TODO Auto-generated constructor stub
	}
	public CongDoan() {
		// TODO Auto-generated constructor stub
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
	public int getSoLuongTP() {
		return soLuongTP;
	}
	public void setSoLuongTP(int soLuongTP) {
		this.soLuongTP = soLuongTP;
	}
	public ToSanXuat getToSanXuat() {
		return toSanXuat;
	}
	public void setToSanXuat(ToSanXuat toSanXuat) {
		this.toSanXuat = toSanXuat;
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
				+ ", trangThaiCD=" + trangThaiCD + ", soLuongTP=" + soLuongTP + ", soLuongDSX=" + soLuongDSX
				+ ", toSanXuat=" + toSanXuat + ", sanPham=" + sanPham + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(giaSX, maCD, sanPham, soLuongDSX, soLuongTP, tenCD, tenThanhPham, toSanXuat, trangThaiCD);
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
				&& Objects.equals(sanPham, other.sanPham) && soLuongDSX == other.soLuongDSX
				&& soLuongTP == other.soLuongTP && Objects.equals(tenCD, other.tenCD)
				&& Objects.equals(tenThanhPham, other.tenThanhPham) && Objects.equals(toSanXuat, other.toSanXuat)
				&& Objects.equals(trangThaiCD, other.trangThaiCD);
	}


	public CongDoan(String maCD, String tenCD, String tenThanhPham, float giaSX, String trangThaiCD, int soLuongTP,
			int soLuongDSX, ToSanXuat toSanXuat, SanPham sanPham) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
		this.tenThanhPham = tenThanhPham;
		this.giaSX = giaSX;
		this.trangThaiCD = trangThaiCD;
		this.soLuongTP = soLuongTP;
		this.soLuongDSX = soLuongDSX;
		this.toSanXuat = toSanXuat;
		this.sanPham = sanPham;
	}






	
		
}
