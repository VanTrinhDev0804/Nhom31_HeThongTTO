package entity;

import java.util.Objects;

public class ToSanXuat {
	private String maTo;
	private String tenTo;
	private String maCD;
	private int soLuongCN;
	private String iconToSX;
	
	
	
	
	public ToSanXuat(String maTo) {
		super();
		this.maTo = maTo;
<<<<<<< HEAD
		this.tenTo = tenTo;
=======
>>>>>>> d1e3cbcb816c61050b5a7eb857d90d7351f5db61
	}

	public ToSanXuat(String maTo, String tenTo, String maCD, int soLuongCN,String icon) {
		super();
		this.maTo = maTo;
		this.tenTo = tenTo;
		this.maCD = maCD;
		this.soLuongCN = soLuongCN;
		this.iconToSX = icon;
	}

	public ToSanXuat() {
		// TODO Auto-generated constructor stub
	}

	public ToSanXuat(String maTo, String tenTo, String maCD, int soLuongCN) {
		super();
		this.maTo = maTo;
		this.tenTo = tenTo;
		this.maCD = maCD;
		this.soLuongCN = soLuongCN;
	}

	@Override
	public int hashCode() {
		return Objects.hash(iconToSX, maCD, maTo, soLuongCN, tenTo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToSanXuat other = (ToSanXuat) obj;
		return Objects.equals(iconToSX, other.iconToSX) && Objects.equals(maCD, other.maCD)
				&& Objects.equals(maTo, other.maTo) && soLuongCN == other.soLuongCN
				&& Objects.equals(tenTo, other.tenTo);
	}

	@Override
	public String toString() {
		return "ToSanXuat [maTo=" + maTo + ", tenTo=" + tenTo + ", maCD=" + maCD + ", soLuongCN=" + soLuongCN
				+ ", iconToSX=" + iconToSX + "]";
	}
	
	public String getMaTo() {
		return maTo;
	}

	public void setMaTo(String maTo) {
		this.maTo = maTo;
	}
	public String getTenTo() {
		return tenTo;
	}
	public void setTenTo(String tenTo) {
		this.tenTo = tenTo;
	}
	public String getMaCD() {
		return maCD;
	}
	public void setMaCD(String maCD) {
		this.maCD = maCD;
	}
	public int getSoLuongCN() {
		return soLuongCN;
	}
	public void setSoLuongCN(int soLuongCN) {
		this.soLuongCN = soLuongCN;
	}
	public String getIconToSX() {
		return iconToSX;
	}
	public void setIconToSX(String iconToSX) {
		this.iconToSX = iconToSX;
	}
	
	
}
