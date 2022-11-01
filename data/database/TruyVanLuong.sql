use QLLuongSP
select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu, PhieuLuongNV.soNgayCong, PhieuLuongNV.thang, 
		([heSoLuong]*[luongCB] + [phuCap]) * [soNgayCong] /26 as tienLuong
		, CTLuongCB.heSoLuong, CTLuongCB.luongCB, CTLuongCB.phuCap, PhieuLuongNV.tienLuong
		from 
		(PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV) join CTLuongCB on PhieuLuongNV.maNV = CTLuongCB.maNV 
		WHERE Year(PhieuLuongNV.thang) = 2022
		ORDER BY maNV

select count((ngayVang)) from ChamCongNV where maNV = 'NV002' and Mo

select COUNT(ngayVang) from ChamCongNV where MONTH(ngayVang) = 8 and maNV = 'NV001'


Update PhieuLuongNV
Set PhieuLuongNV.tienLuong = ([heSoLuong]*[luongCB] + [phuCap]) * [soNgayCong] /26  from 
		(PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV) join CTLuongCB on PhieuLuongNV.maNV = CTLuongCB.maNV 

select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu,PhieuLuongNV.soNgayCong,PhieuLuongNV.thang, PhieuLuongNV.tienLuong
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

select Month(ngayVang)
from ChamCongNV 
Where maNV ='NV003'
group by MONTH(ngayVang) 


select  MONTH(ngayVang) , Count(*)
from ChamCongNV
Group by MONTH(ngayVang) 

Update PhieuLuongNV
Set Month(PhieuLuongNV.thang) =   from 
		(PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV) join CTLuongCB on PhieuLuongNV.maNV = CTLuongCB.maNV 

select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu,PhieuLuongNV.soNgayCong,PhieuLuongNV.thang, PhieuLuongNV.tienLuong
		from 
		PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV
select ChamCongNV.maNV, NhanVien.tenNV, CTLuongCB.heSoLuong, CTLuongCB.luongCB, CTLuongCB.phuCap,MONTH(ngayVang) as Thang
from (ChamCongNV  join NhanVien on NhanVien.maNV = ChamCongNV.maNV) join CTLuongCB on ChamCongNV.maNV = CTLuongCB.maNV
where ChamCongNV.maNV = 'NV002'
select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu,PhieuLuongNV.soNgayCong,PhieuLuongNV.thang, PhieuLuongNV.tienLuong
					from
					PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV
					where PhieuLuongNV.maNV like N'%NV001%'
delete from PhieuLuongNV
insert into ChamCongNV values 
('NV001', '2022-10-01'),
('NV001', '2022-10-03'),
('NV002', '2022-10-28'),
('NV002', '2022-10-01'),
('NV002', '2022-10-10')

select count(*) from PhieuLuongNV where thang = '2022-10-27' 


