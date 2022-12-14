package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

//import dao.DAOCTHD;
//import dao.DAOKhachHang;
//import dao.DAOLoaiKH;
//import dao.DAOLoaiMH;
//import dao.DAOMatHang;
//import dao.DAONhanVien;
//import entity.CTHD;
//import entity.HoaDon;
//import entity.KhachHang;
//import entity.LoaiMatHang;
//import entity.MatHang;
//import entity.NhanVien;
import dao.DAOSanPham;
import entity.SanPham;

public class XuatExcels {
	
	

//	private DAOKhachHang daoKhachHang;
//	private DAONhanVien daoNhanVien;
//	private SimpleDateFormat sf;
//	private SimpleDateFormat sdf;
//	private DAOCTHD daoCTHD;
//	private DAOMatHang daoMatHang;
//	private DAOLoaiMH daoLoaiMH;
	private DAOSanPham daoSP;
	
	public XuatExcels() {
//			daoKhachHang =  new DAOKhachHang();
//			daoNhanVien = new DAONhanVien();
//			daoCTHD = new DAOCTHD();
//			daoMatHang = new DAOMatHang();
//			daoLoaiMH = new DAOLoaiMH();
//			sf = new SimpleDateFormat("dd/MM/yyyy");
//			sdf = new SimpleDateFormat("HH:mm");
			daoSP = new DAOSanPham();
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
//        cellTitle.setCellValue("DANH S??CH H??A ????N");
//        rowIndex++;
//        Row headerRow = sheet.createRow(rowIndex);
//        Cell cMaHD = headerRow.createCell(0,CellType.STRING);
//        cMaHD.setCellValue("M?? h??a ????n");
//        
//        Cell cMaKH = headerRow.createCell(1,CellType.STRING);
//        cMaKH.setCellValue("M?? kh??ch h??ng");
//        
//        Cell cTenKH = headerRow.createCell(2,CellType.STRING);
//        cTenKH.setCellValue("T??n kh??ch h??ng");
//        Cell cMaNV = headerRow.createCell(3,CellType.STRING);
//        cMaNV.setCellValue("M?? nh??n vi??n");
//        Cell cTenNV = headerRow.createCell(4,CellType.STRING);
//        cTenNV.setCellValue("T??n nh??n vi??n");
//        Cell cNgayLap = headerRow.createCell(5,CellType.STRING);
//        cNgayLap.setCellValue("Ng??y l???p");
//        Cell cPhong = headerRow.createCell(6,CellType.STRING);
//        cPhong.setCellValue("Ph??ng");
//        Cell cGiovao = headerRow.createCell(7,CellType.STRING);
//        cGiovao.setCellValue("Gi??? v??o");
//        Cell cGiora = headerRow.createCell(8,CellType.STRING);
//        cGiora.setCellValue("Gi??? ra");
//        Cell cPhuThu = headerRow.createCell(9,CellType.STRING);
//        cPhuThu.setCellValue("Ph??? thu");
//        Cell cGiamGia = headerRow.createCell(10,CellType.STRING);
//        cGiamGia.setCellValue("Gi???m gi??");
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
//				hMaMH.setCellValue("M?? M???t h??ng");
//			        
//				Cell hTenMH = headerCTHD.createCell(5,CellType.STRING);
//				hTenMH.setCellValue("T??n m???t h??ng");
//			        
//				Cell hLoaiMH = headerCTHD.createCell(6,CellType.STRING);
//				hLoaiMH.setCellValue("Lo???i m???t h??ng");
//				
//				Cell hSoLuong = headerCTHD.createCell(7,CellType.STRING);
//				hSoLuong.setCellValue("S??? l?????ng");
//				
//				Cell hDonGia = headerCTHD.createCell(8,CellType.STRING);
//				hDonGia.setCellValue("????n gi??");
//			        
//				Cell hTongTien = headerCTHD.createCell(9,CellType.STRING);
//				hTongTien.setCellValue("T???ng ti???n");
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
//			JOptionPane.showMessageDialog(null, "L??u kh??ng th??nh c??ng!\n T??n file ???? t???n t???i");
//		}
//	}
//	
	public void xuatTable(JTable tb,String tieude,String path) throws IOException {
		Workbook workbook = null;
		 
        if (path.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (path.endsWith(".xls")) {
            workbook = new HSSFWorkbook();
        }

        Sheet sheet = workbook.createSheet(); 
 
        int rowIndex = 1;
         
        Row title = sheet.createRow(rowIndex);
        Cell cellTitle = title.createCell(0,CellType.STRING);
        cellTitle.setCellValue(tieude);
        rowIndex++;
        int rowCount = tb.getRowCount();
        int colCount = tb.getColumnCount();
        DefaultTableModel model = (DefaultTableModel) tb.getModel();
        
        // add header to excels
        Row headerRow = sheet.createRow(rowIndex);
        for(int i = 0; i < colCount;i++) {
        	 Cell cH = headerRow.createCell(i,CellType.STRING);
             cH.setCellValue(tb.getColumnName(i));
        }
        //Xuat row
        rowIndex++;
        for(int i = 0; i < rowCount;i++) {
        	Row row = sheet.createRow(rowIndex);
        	for(int j = 0; j < colCount;j++) {
	        	Cell c = row.createCell(j,CellType.STRING);
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
			JOptionPane.showMessageDialog(null, "L??u kh??ng th??nh c??ng!\n T??n file ???? t???n t???i");
		}
        
        
	}
	
	
	
}