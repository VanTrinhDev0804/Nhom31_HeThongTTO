select PhieuLuongNV.maNV, NhanVien.tenNV, NhanVien.cccd, NhanVien.chucVu, PhieuLuongNV.soNgayCong, PhieuLuongNV.thang, 
		([heSoLuong]*[luongCB] + [phuCap]) * [soNgayCong] /26 as tienLuong
		, CTLuongCB.heSoLuong, CTLuongCB.luongCB, CTLuongCB.phuCap
		from 
		(PhieuLuongNV Join NhanVien on PhieuLuongNV.maNV = NhanVien.maNV) join CTLuongCB on PhieuLuongNV.maNV = CTLuongCB.maNV
		WHERE Year(PhieuLuongNV.thang) = 2022
		ORDER BY maNV
