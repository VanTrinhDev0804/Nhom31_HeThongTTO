package entity;

public class CTLuongCB {
	private NhanVien maNV;
	private float luongCB;
	private float phuCap;
	private float heSoLuong;
	public CTLuongCB() {
		super();
	}
	public CTLuongCB(NhanVien maNV, float luongCB, float phuCap, float heSoLuong) {
		super();
		this.maNV = maNV;
		this.luongCB = luongCB;
		this.phuCap = phuCap;
		this.heSoLuong = heSoLuong;
	}
	public float getLuongCB() {
		return luongCB;
	}
	public void setLuongCB(float luongCB) {
		this.luongCB = luongCB;
	}
	public void setMaNV(NhanVien maNV) {
		this.maNV = maNV;
	}
	public float getPhuCap() {
		return phuCap;
	}
	public void setPhuCap(float phuCap) {
		this.phuCap = phuCap;
	}
	public float getHeSoLuong() {
		return heSoLuong;
	}
	public void setHeSoLuong(float heSoLuong) {
		this.heSoLuong = heSoLuong;
	}
	public NhanVien getMaNV() {
		return maNV;
	}
	
	
}
