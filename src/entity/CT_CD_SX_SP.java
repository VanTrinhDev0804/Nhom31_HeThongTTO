package entity;

import java.util.Objects;

public class CT_CD_SX_SP {
	private static final long serialVersionUID = 1L;
	private int stt;
	private CongDoan congDoan;
	private SanPham sanPham;
	private float giaSX;
	public CT_CD_SX_SP(int stt, CongDoan congDoan, SanPham sanPham, float giaSX) {
		super();
		this.stt = stt;
		this.congDoan = congDoan;
		this.sanPham = sanPham;
		this.giaSX = giaSX;
	}
	public CT_CD_SX_SP(CongDoan congDoan, SanPham sanPham, float giaSX) {
		super();
		this.congDoan = congDoan;
		this.sanPham = sanPham;
		this.giaSX = giaSX;
	}
	public CT_CD_SX_SP(CongDoan congDoan, SanPham sanPham) {
		super();
		this.congDoan = congDoan;
		this.sanPham = sanPham;
	}
	public CT_CD_SX_SP() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public CongDoan getCongDoan() {
		return congDoan;
	}
	public void setCongDoan(CongDoan congDoan) {
		this.congDoan = congDoan;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public float getGiaSX() {
		return giaSX;
	}
	public void setGiaSX(float giaSX) {
		this.giaSX = giaSX;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(congDoan, giaSX, sanPham, stt);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CT_CD_SX_SP other = (CT_CD_SX_SP) obj;
		return Objects.equals(congDoan, other.congDoan)
				&& Float.floatToIntBits(giaSX) == Float.floatToIntBits(other.giaSX)
				&& Objects.equals(sanPham, other.sanPham) && stt == other.stt;
	}
	@Override
	public String toString() {
		return "CT_CD_SX_SP [stt=" + stt + ", congDoan=" + congDoan + ", sanPham=" + sanPham + ", giaSX=" + giaSX + "]";
	}
	
	
	
}
