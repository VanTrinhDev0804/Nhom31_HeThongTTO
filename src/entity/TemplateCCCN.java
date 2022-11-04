package entity;

public class TemplateCCCN {
	private String ma;
	private String tenCN;
	private String tenTo;
	private String caLam;
	private int soLuong;
	
	
	
	
	
	public TemplateCCCN(String ma, String tenCN, String tenTo, String caLam, int soLuong) {
		super();
		this.ma = ma;
		this.tenCN = tenCN;
		this.tenTo = tenTo;
		this.caLam = caLam;
		this.soLuong = soLuong;
	}
	
	
	
	@Override
	public String toString() {
		return "MutilCCCN [ma=" + ma + ", tenCN=" + tenCN + ", tenTo=" + tenTo + ", caLam=" + caLam + ", soLuong="
				+ soLuong + "]";
	}



	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
	}
	public String getTenCN() {
		return tenCN;
	}
	public void setTenCN(String tenCN) {
		this.tenCN = tenCN;
	}
	public String getTenTo() {
		return tenTo;
	}
	public void setTenTo(String tenTo) {
		this.tenTo = tenTo;
	}
	public String getCaLam() {
		return caLam;
	}
	public void setCaLam(String caLam) {
		this.caLam = caLam;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	
	
}
