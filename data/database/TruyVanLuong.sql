select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu, PhieuLuongNV.soNgayCong, PhieuLuongNV.thang, 
		([heSoLuong]*[luongCB] + [phuCap]) * [soNgayCong] /26 as tienLuong
		, CTLuongCB.heSoLuong, CTLuongCB.luongCB, CTLuongCB.phuCap, PhieuLuongNV.tienLuong
		from 
		(PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV) join CTLuongCB on PhieuLuongNV.maNV = CTLuongCB.maNV 
		WHERE Year(PhieuLuongNV.thang) = 2022
		ORDER BY maNV

select * from ChamCongNV where maNV = 'NV001'

select COUNT(ngayVang) from ChamCongNV where MONTH(ngayVang) = 8 and maNV = 'NV001'


Update PhieuLuongNV
Set PhieuLuongNV.tienLuong = ([heSoLuong]*[luongCB] + [phuCap]) * [soNgayCong] /26  from 
		(PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV) join CTLuongCB on PhieuLuongNV.maNV = CTLuongCB.maNV 

select PhieuLuongNV.maNV, NhanVien.tenNV, PhieuLuongNV.soNgayCong,FORMAT( PhieuLuongNV.thang, 'MM-yyyy'), PhieuLuongNV.tienLuong
		from 
		PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV
		WHERE Year(PhieuLuongNV.thang) = 2022
		ORDER BY maNV

Select  ngayVang
from  ChamCongNV 
Group by cast(year(ngayVang) as varchar(4)) + '-' + right('00' + CAST(MONTH(ngayVang) AS VARCHAR(2)), 2)

SELECT COUNT(maNV)
FROM PhieuLuongNV;

DROP TABLE ChamCongNV
CREATE TABLE ChamCongNV (
	stt int IDENTITY(1,1) PRIMARY KEY,
	maNV nvarchar(50),
	ngayVang date,
	thang date,
	soNgayVang int
)