package entity;

import java.util.Objects;

public class LuongTheoTo {
	private String maTo;
	private int thang;
	private int nam;
	private float tongluong;
	private float trungBinh;
	@Override
	public String toString() {
		return "LuongTheoTo [maTo=" + maTo + ", thang=" + thang + ", nam=" + nam + ", tongluong=" + tongluong
				+ ", trungBinh=" + trungBinh + "]";
	}
	public LuongTheoTo(String maTo, int thang, int nam, float tongluong, float trungBinh) {
		super();
		this.maTo = maTo;
		this.thang = thang;
		this.nam = nam;
		this.tongluong = tongluong;
		this.trungBinh = trungBinh;
	}
	public LuongTheoTo() {
		// TODO Auto-generated constructor stub
	}
	public String getMaTo() {
		return maTo;
	}
	public void setMaTo(String maTo) {
		this.maTo = maTo;
	}
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		this.thang = thang;
	}
	public int getNam() {
		return nam;
	}
	public void setNam(int nam) {
		this.nam = nam;
	}
	public float getTongluong() {
		return tongluong;
	}
	public void setTongluong(float tongluong) {
		this.tongluong = tongluong;
	}
	public float getTrungBinh() {
		return trungBinh;
	}
	public void setTrungBinh(float trungBinh) {
		this.trungBinh = trungBinh;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maTo, nam, thang, tongluong, trungBinh);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LuongTheoTo other = (LuongTheoTo) obj;
		return Objects.equals(maTo, other.maTo) && nam == other.nam && thang == other.thang
				&& Float.floatToIntBits(tongluong) == Float.floatToIntBits(other.tongluong)
				&& Float.floatToIntBits(trungBinh) == Float.floatToIntBits(other.trungBinh);
	}
	
	
	
	
}
