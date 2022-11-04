package dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Regex {
	public boolean regexDiaChi(JTextField txtDiaChi) {
		String input = txtDiaChi.getText().trim();
		String regex = "^([ A-Za-z0-9,\\/.a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Địa chỉ không hợp lệ!\nMẫu địa chỉ:56a Cầu Xéo, Tân quí, Tân Phú",
					"Thông báo", JOptionPane.ERROR_MESSAGE);
			txtDiaChi.requestFocus();
			txtDiaChi.selectAll();
			return false;
		} else
			return true;
	}

	public boolean regexTen(JTextField txtTen2) {
		String input = txtTen2.getText().trim();
		String regex = "^([ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Họ tên không hợp lệ!\nMẫu họ tên: Nguyễn Văn A", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
			txtTen2.requestFocus();
			txtTen2.selectAll();
			return false;
		} else
			return true;
	}

	public boolean regexSDT(JTextField txtSDT) {
		String input = txtSDT.getText().trim();
		String regex = "^(0[0-9]{9}$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "SĐT không hợp lệ!\nSĐT gồm 10 chữ số và bắt đầu bằng số 0",
					"Thông báo", JOptionPane.ERROR_MESSAGE);
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		} else
			return true;
	}

	public boolean regexCCCD(JTextField txtCCCD) {
		String input = txtCCCD.getText().trim();
		String regex = "^([0-9]{12}$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "CCCD không hợp lệ!\nCCCD gồm 12 chữ số", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
			txtCCCD.requestFocus();
			txtCCCD.selectAll();
			return false;
		} else
			return true;
	}
	
	public boolean regexTimKiemMaSP(JTextField txtTim) {
		String input = txtTim.getText();
		String regex = "^((SP|p)[0-9]{3})$";
		if (!input.matches(regex)) {
			
			  JOptionPane.showMessageDialog(null,"Thông tin tìm kiếm không hợp lệ\nThông tin có thể tìm kiếm:\n - Mã Sản Phẩm. Ví dụ: SP001\n", "Thông báo", JOptionPane.ERROR_MESSAGE);
			 
			txtTim.requestFocus();
			txtTim.selectAll();
			return false;
		}
		return true;
	}
	
	public boolean regexTimSanPham(JTextField txtTK) {
		String input = txtTK.getText().trim();
		input = input.toUpperCase();
		String regexMaSP = "((SP|sp)[0-9]{3})|";
		//String regexTen = "([ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+)|";
		String regexSanPham = "^(" + regexMaSP +")$";
		if (!input.matches(regexSanPham)) {
			JOptionPane.showMessageDialog(null,
					"Thông tin tìm kiếm không hợp lệ!\nThông tin có thể tìm kiếm:\nMã Sản Phẩm. Ví dụ: SP001 hoặc sp001",
					"Thông báo", JOptionPane.ERROR_MESSAGE);
			txtTK.requestFocus();
			txtTK.selectAll();
			return false;
		}
		return true;
	}

	public boolean regexGiaSX(JTextField txtGiaSX) {
		String input = txtGiaSX.getText();
		String regex = "^[1-9]+[0-9]*$";
		if (!input.matches(regex)) {
			JOptionPane.showMessageDialog(null,
					"Giá sản xuất không được để trống, không được nhập chữ và phải lớn hơn 0\nVí dụ: 1000000", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
			txtGiaSX.requestFocus();
			txtGiaSX.selectAll();
			return false;
		}
		return true;
	}
	public boolean regexSoLuong(JTextField txtSoLuong) {
		String input = txtSoLuong.getText();
		String regex = "^[0-9]+[0-9]*$";
		if (!input.matches(regex)) {
			JOptionPane.showMessageDialog(null,
					"Số lượng không được để trống, không được nhập chữ và phải lớn hơn 0\nVí dụ: 1000000", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
			txtSoLuong.requestFocus();
			txtSoLuong.selectAll();
			return false;
		}
		return true;
	}
	public boolean regexTenSP(JTextField txtTenSP) {
		String input = txtTenSP.getText().trim();
		String regex = "^([ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Tên không hợp lệ!\nMẫu tên: Áo Sơ Mi, Áo Thun", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
			txtTenSP.requestFocus();
			txtTenSP.selectAll();
			return false;
		} else
			return true;
		}
	
	public boolean regexTimCongDoan(JTextField txtTK) {
		String input = txtTK.getText().trim();
		input = input.toUpperCase();
		String regexMaCD = "((CD|cd)[0-9]{3})|";
		//String regexTen = "([ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+)|";
		String regexCongDoan = "^(" + regexMaCD +")$";
		if (!input.matches(regexCongDoan)) {
			JOptionPane.showMessageDialog(null,
					"Thông tin tìm kiếm không hợp lệ!\nThông tin có thể tìm kiếm:\nMã Công Đoạn. Ví dụ: CD001 hoặc cd001",
					"Thông báo", JOptionPane.ERROR_MESSAGE);
			txtTK.requestFocus();
			txtTK.selectAll();
			return false;
		}
		return true;
	}
	public boolean regexTenCD(JTextField txtTenCD) {
		String input = txtTenCD.getText().trim();
		String regex = "^([ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Tên không hợp lệ!\nMẫu tên: Áo Sơ Mi, Áo Thun", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
			txtTenCD.requestFocus();
			txtTenCD.selectAll();
			return false;
		} else
			return true;
	}
	public boolean regexTenThanhPham(JTextField txtTenThanhPham) {
		String input = txtTenThanhPham.getText().trim();
		String regex = "^([ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Tên không hợp lệ!\nMẫu tên: Áo Sơ Mi, Áo Thun", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
			txtTenThanhPham.requestFocus();
			txtTenThanhPham.selectAll();
			return false;
		} else
			return true;
	}
	public boolean regexTenTo(JTextField txtTenTo) {
		String input = txtTenTo.getText().trim();
		String regex = "^([ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Tên không hợp lệ!\nMẫu tên: Tổ 1, Tổ 2", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
			txtTenTo.requestFocus();
			txtTenTo.selectAll();
			return false;
		} else
			return true;
	}
	public boolean regexTimKiemMaTo(JTextField txtTim) {
		String input = txtTim.getText();
		String regex = "^((T|t)[0-9]{3})$";
		if (!input.matches(regex)) {
			
			  JOptionPane.showMessageDialog(null,"Thông tin tìm kiếm không hợp lệ\nThông tin có thể tìm kiếm:\n - Mã Tổ. Ví dụ: T003\n", "Thông báo", JOptionPane.ERROR_MESSAGE);
			 
			txtTim.requestFocus();
			txtTim.selectAll();
			return false;
		}
		return true;
	}
}
