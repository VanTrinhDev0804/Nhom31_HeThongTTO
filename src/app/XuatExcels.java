package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XuatExcels {

//	private DAOKhachHang daoKhachHang;
//	private DAONhanVien daoNhanVien;
//	private SimpleDateFormat sf;
//	private SimpleDateFormat sdf;
//	private DAOCTHD daoCTHD;
//	private DAOMatHang daoMatHang;
//	private DAOLoaiMH daoLoaiMH;

	public XuatExcels() {
//			daoKhachHang =  new DAOKhachHang();
//			daoNhanVien = new DAONhanVien();
//			daoCTHD = new DAOCTHD();
//			daoMatHang = new DAOMatHang();
//			daoLoaiMH = new DAOLoaiMH();
//			sf = new SimpleDateFormat("dd/MM/yyyy");
//			sdf = new SimpleDateFormat("HH:mm");
	}

//	public void xuatHoaDon(ArrayList<HoaDon> lsHD,String path) throws IOException {
//		Workbook workbook = null;
//		 
//        if (path.endsWith(".xlsx")) {
//            workbook = new XSSFWorkbook();
//        } else if (path.endsWith(".xls")) {
//            workbook = new HSSFWorkbook();
//        }
//
//        Sheet sheet = workbook.createSheet("DSHD"); 
// 
//        int rowIndex = 1;
//         
//        Row title = sheet.createRow(rowIndex);
//        
//        Cell cellTitle = title.createCell(0,CellType.STRING);
//        cellTitle.setCellValue("DANH SÃ�CH HÃ“A Ä�Æ N");
//        rowIndex++;
//        Row headerRow = sheet.createRow(rowIndex);
//        Cell cMaHD = headerRow.createCell(0,CellType.STRING);
//        cMaHD.setCellValue("MÃ£ hÃ³a Ä‘Æ¡n");
//        
//        Cell cMaKH = headerRow.createCell(1,CellType.STRING);
//        cMaKH.setCellValue("MÃ£ khÃ¡ch hÃ ng");
//        
//        Cell cTenKH = headerRow.createCell(2,CellType.STRING);
//        cTenKH.setCellValue("TÃªn khÃ¡ch hÃ ng");
//        Cell cMaNV = headerRow.createCell(3,CellType.STRING);
//        cMaNV.setCellValue("MÃ£ nhÃ¢n viÃªn");
//        Cell cTenNV = headerRow.createCell(4,CellType.STRING);
//        cTenNV.setCellValue("TÃªn nhÃ¢n viÃªn");
//        Cell cNgayLap = headerRow.createCell(5,CellType.STRING);
//        cNgayLap.setCellValue("NgÃ y láº­p");
//        Cell cPhong = headerRow.createCell(6,CellType.STRING);
//        cPhong.setCellValue("PhÃ²ng");
//        Cell cGiovao = headerRow.createCell(7,CellType.STRING);
//        cGiovao.setCellValue("Giá»� vÃ o");
//        Cell cGiora = headerRow.createCell(8,CellType.STRING);
//        cGiora.setCellValue("Giá»� ra");
//        Cell cPhuThu = headerRow.createCell(9,CellType.STRING);
//        cPhuThu.setCellValue("Phá»¥ thu");
//        Cell cGiamGia = headerRow.createCell(10,CellType.STRING);
//        cGiamGia.setCellValue("Giáº£m giÃ¡");
//        
//        rowIndex++; 
//        for(HoaDon hd : lsHD) {
//        	KhachHang kh = daoKhachHang.getKHTheoMa(hd.getKhachHang().getMaKhangHang());
//			NhanVien nv = daoNhanVien.getNVTheoMa(hd.getNhanVien().getMaNhanVien());
//			Row row = sheet.createRow(rowIndex);
//			
//			Cell cRowMaHD = row.createCell(0,CellType.STRING);
//	        cRowMaHD.setCellValue(hd.getMaHoaDon());
//	        
//	        Cell cRowMaKH = row.createCell(1,CellType.STRING);
//	        cRowMaKH.setCellValue(kh.getMaKhangHang());
//	        
//	        Cell cRowTenKH = row.createCell(2,CellType.STRING);
//	        cRowTenKH.setCellValue(kh.getTenKH());
//	        
//	        Cell cRowMaNV = row.createCell(3,CellType.STRING);
//	        cRowMaNV.setCellValue(nv.getMaNhanVien());
//	        
//	        Cell cRowTenNV = row.createCell(4,CellType.STRING);
//	        cRowTenNV.setCellValue(nv.getTenNhanVien());
//	        
//	        Cell cRowNgayLap = row.createCell(5,CellType.STRING);
//	        cRowNgayLap.setCellValue(sf.format(hd.getNgayLap()));
//	        
//	        Cell cRowPhong = row.createCell(6,CellType.STRING);
//	        cRowPhong.setCellValue(hd.getPhong().getMaPhong());
//	        
//	        Cell cRowGiovao = row.createCell(7,CellType.STRING);
//	        cRowGiovao.setCellValue(sdf.format(hd.getGioVao()));
//	        
//	        Cell cRowGiora = row.createCell(8,CellType.STRING);
//	        cRowGiora.setCellValue(sdf.format(hd.getGioRa()));
//	        
//	        Cell cRowPhuThu = row.createCell(9,CellType.STRING);
//	        cRowPhuThu.setCellValue(hd.getPhuThu());
//	        
//	        Cell cRowGiamGia = row.createCell(10,CellType.NUMERIC);
//	        cRowGiamGia.setCellValue(hd.getGiamGia());
//			
//	        rowIndex++;
//			
//	        ArrayList<CTHD> lsCTHD = daoCTHD.getCTHDTheoMaHD(hd.getMaHoaDon());
//	        
//	       
//	        Row headerCTHD = sheet.createRow(rowIndex);
//	        for(int i = 0; i < 5;i++) {
//	        	Cell hMaMH = headerCTHD.createCell(4,CellType.STRING);
//				hMaMH.setCellValue("MÃ£ Máº·t hÃ ng");
//			        
//				Cell hTenMH = headerCTHD.createCell(5,CellType.STRING);
//				hTenMH.setCellValue("TÃªn máº·t hÃ ng");
//			        
//				Cell hLoaiMH = headerCTHD.createCell(6,CellType.STRING);
//				hLoaiMH.setCellValue("Loáº¡i máº·t hÃ ng");
//				
//				Cell hSoLuong = headerCTHD.createCell(7,CellType.STRING);
//				hSoLuong.setCellValue("Sá»‘ lÆ°á»£ng");
//				
//				Cell hDonGia = headerCTHD.createCell(8,CellType.STRING);
//				hDonGia.setCellValue("Ä�Æ¡n giÃ¡");
//			        
//				Cell hTongTien = headerCTHD.createCell(9,CellType.STRING);
//				hTongTien.setCellValue("Tá»•ng tiá»�n");
//	        }
//	        //Xuat row
//	        rowIndex++;
//	        
//			for(CTHD ct : lsCTHD) {
//				if(ct!= null) {
//					MatHang mh = daoMatHang.getMHTheoMaMH(ct.getMatHang().getMaMatHang());
//					LoaiMatHang loaiMH =daoLoaiMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
//					double tongTien = mh.getGiaMatHang()*ct.getSoLuong();
//					Row rCTHD = sheet.createRow(rowIndex);
//					Cell cMaMH = rCTHD.createCell(4,CellType.STRING);
//					cMaMH.setCellValue(mh.getMaMatHang());
//				        
//					Cell cTenMH = rCTHD.createCell(5,CellType.STRING);
//					cTenMH.setCellValue(mh.getTenMatHang());
//				        
//					Cell cLoaiMH = rCTHD.createCell(6,CellType.STRING);
//					cLoaiMH.setCellValue(loaiMH.getTenLoaiMatHang());
//					
//					Cell cSoLuong = rCTHD.createCell(7,CellType.NUMERIC);
//					cSoLuong.setCellValue(ct.getSoLuong());
//					
//					Cell cDonGia = rCTHD.createCell(8,CellType.NUMERIC);
//					cDonGia.setCellValue(mh.getGiaMatHang());
//				        
//					Cell cTongTien = rCTHD.createCell(9,CellType.NUMERIC);
//					cTongTien.setCellValue(tongTien);
//					
//					
//					rowIndex++;
//				}
//			}
//			rowIndex += 2;
//	        
//        }
//        File f = new File(path);
//         try {
//        	
//        		 FileOutputStream out = new FileOutputStream(f);
//        		 workbook.write(out);
//				
//        		 out.close();
//		} catch (FileNotFoundException e) {
//			JOptionPane.showMessageDialog(null, "Lưu không thành công!\\n Tên file đã tồn tại");
//		}
//	}

	public void xuatTable(JTable tb, String tieude, String path) throws IOException {
		Workbook workbook = null;

		if (path.endsWith(".xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (path.endsWith(".xls")) {
			workbook = new HSSFWorkbook();
		}

		Sheet sheet = workbook.createSheet();

		int rowIndex = 1;

		Row title = sheet.createRow(rowIndex);
		Cell cellTitle = title.createCell(0, CellType.STRING);
		cellTitle.setCellValue(tieude);
		rowIndex++;
		int rowCount = tb.getRowCount();
		int colCount = tb.getColumnCount();
		DefaultTableModel model = (DefaultTableModel) tb.getModel();

		// add header to excels
		Row headerRow = sheet.createRow(rowIndex);
		for (int i = 0; i < colCount; i++) {
			Cell cH = headerRow.createCell(i, CellType.STRING);
			cH.setCellValue(tb.getColumnName(i));
		}
		// Xuat row
		rowIndex++;
		for (int i = 0; i < rowCount; i++) {
			Row row = sheet.createRow(rowIndex);
			for (int j = 0; j < colCount; j++) {
				Cell c = row.createCell(j, CellType.STRING);
				c.setCellValue(model.getValueAt(i, j).toString());
			}
			rowIndex++;
		}

		File f = new File(path);
		try {

			FileOutputStream out = new FileOutputStream(f);
			workbook.write(out);

			out.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Lưu không thành công!\\n Tên file đã tồn tại");
		}

	}

}