USE [master]
GO
/****** Object:  Database [QLLuongSP]    Script Date: 9/23/2022 2:38:51 PM ******/
CREATE DATABASE [QLLuongSP]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QLLuongSP', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\QLLuongSP.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QLLuongSP_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\QLLuongSP_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [QLLuongSP] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QLLuongSP].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QLLuongSP] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QLLuongSP] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QLLuongSP] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QLLuongSP] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QLLuongSP] SET ARITHABORT OFF 
GO
ALTER DATABASE [QLLuongSP] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QLLuongSP] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QLLuongSP] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QLLuongSP] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QLLuongSP] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QLLuongSP] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QLLuongSP] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QLLuongSP] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QLLuongSP] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QLLuongSP] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QLLuongSP] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QLLuongSP] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QLLuongSP] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QLLuongSP] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QLLuongSP] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QLLuongSP] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QLLuongSP] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QLLuongSP] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QLLuongSP] SET  MULTI_USER 
GO
ALTER DATABASE [QLLuongSP] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QLLuongSP] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QLLuongSP] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QLLuongSP] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QLLuongSP] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QLLuongSP] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [QLLuongSP] SET QUERY_STORE = OFF
GO
USE [QLLuongSP]
GO
/****** Object:  Table [dbo].[ChamCongCN]    Script Date: 9/23/2022 2:38:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChamCongCN](
	[stt] [int] IDENTITY(1,1) NOT NULL,
	[maCN] [nvarchar](50) NULL,
	[trangThai] [nvarchar](50) NULL,
	[caLamViec] [nvarchar](50) NULL,
	[ngay] [date] NULL,
	[soLuongSX] [int] NULL,
	[luongNgay] [float] NULL,
 CONSTRAINT [PK_ChamCong] PRIMARY KEY CLUSTERED 
(
	[stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChamCongNV]    Script Date: 9/23/2022 2:38:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChamCongNV](
	[stt] [int] IDENTITY(1,1) NOT NULL,
	[maNV] [nvarchar](50) NULL,
	[soNgayCong] [nchar](10) NULL,
	[tangCa] [nchar](10) NULL,
 CONSTRAINT [PK_ChamCongNV] PRIMARY KEY CLUSTERED 
(
	[stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CongDoan]    Script Date: 9/23/2022 2:38:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CongDoan](
	[maCD] [nvarchar](50) NOT NULL,
	[maSP] [nvarchar](50) NOT NULL,
	[tenCD] [nvarchar](255) NULL,
	[tenThanhPham] [nvarchar](255) NULL,
	[giaSX] [float] NULL,
	[trangThaiCD] [nvarchar](255) NULL,
 CONSTRAINT [PK_CongDoan] PRIMARY KEY CLUSTERED 
(
	[maCD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[CongNhan]    Script Date: 9/23/2022 2:38:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CongNhan](
	[maCN] [nvarchar](50) NOT NULL,
	[tenCN] [nvarchar](255) NULL,
	[maTo] [nvarchar](50) NULL,
	[gioiTinh] [nvarchar](50) NULL,
	[ngaySinh] [date] NULL,
	[diaChi] [nvarchar](255) NULL,
	[cccd] [nvarchar](50) NULL,
	[sdt] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_CongNhan] PRIMARY KEY CLUSTERED 
(
	[maCN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CT_CD_SX_SP]    Script Date: 9/23/2022 2:38:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_CD_SX_SP](
	[stt] [int] IDENTITY(1,1) NOT NULL,
	[maCD] [nvarchar](50) NULL,
	[maSP] [nvarchar](50) NULL,
	[soCD] [int] NULL,
 CONSTRAINT [PK_CT_CD_SX_SP] PRIMARY KEY CLUSTERED 
(
	[stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTLuongCB]    Script Date: 9/23/2022 2:38:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTLuongCB](
	[maNV] [nvarchar](50) NOT NULL,
	[luongCB] [float] NULL,
	[phuCap] [float] NULL,
	[heSoLuong] [float] NULL,
 CONSTRAINT [PK_CTLuongCB] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 9/23/2022 2:38:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [nvarchar](50) NOT NULL,
	[tenNV] [nvarchar](255) NULL,
	[chucVu] [nvarchar](50) NULL,
	[ngaySinh] [date] NULL,
	[gioiTinh] [nvarchar](50) NULL,
	[diaChi] [nvarchar](255) NULL,
	[cccd] [nvarchar](50) NULL,
	[sdt] [nvarchar](50) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuLuongCN]    Script Date: 9/23/2022 2:38:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuLuongCN](
	[stt] [int] IDENTITY(1,1) NOT NULL,
	[maCN] [nvarchar](50) NOT NULL,
	[thang] [nvarchar](50) NULL,
	[soSPSX] [int] NULL,
	[tienLuong] [float] NULL,
 CONSTRAINT [PK_Luongw] PRIMARY KEY CLUSTERED 
(
	[maCN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuLuongNV]    Script Date: 9/23/2022 2:38:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuLuongNV](
	[maNV] [nvarchar](50) NOT NULL,
	[thang] [nvarchar](50) NULL,
	[soNgayCong] [int] NULL,
	[tienLuong] [float] NULL,
 CONSTRAINT [PK_PhieuLuongNV] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 9/23/2022 2:38:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[maSP] [nvarchar](50) NOT NULL,
	[tenSP] [nvarchar](255) NULL,
	[giaSX] [float] NULL,
	[soLuong] [int] NULL,
 CONSTRAINT [PK_SanPham] PRIMARY KEY CLUSTERED 
(
	[maSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 9/23/2022 2:38:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[maTK] [nvarchar](50) NOT NULL,
	[matKhau] [nvarchar](255) NULL,
 CONSTRAINT [PK_TaiKhoan_1] PRIMARY KEY CLUSTERED 
(
	[maTK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ToSanXuat]    Script Date: 9/23/2022 2:38:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ToSanXuat](
	[maTo] [nvarchar](50) NOT NULL,
	[tenTo] [nvarchar](255) NULL,
	[maCD] [nvarchar](50) NULL,
	[soLuongCN] [int] NULL,
 CONSTRAINT [PK_ToSanXuat] PRIMARY KEY CLUSTERED 
(
	[maTo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[ChamCongCN] ON 

INSERT [dbo].[ChamCongCN] ([stt], [maCN], [trangThai], [caLamViec], [ngay], [soLuongSX], [luongNgay]) VALUES (1, N'CN001', N'Có mặt', N'ca 1', CAST(N'2022-01-06' AS Date), 200, 340)
INSERT [dbo].[ChamCongCN] ([stt], [maCN], [trangThai], [caLamViec], [ngay], [soLuongSX], [luongNgay]) VALUES (2, N'CN002', N'Vắng', N'ca2', CAST(N'2022-01-06' AS Date), NULL, NULL)
INSERT [dbo].[ChamCongCN] ([stt], [maCN], [trangThai], [caLamViec], [ngay], [soLuongSX], [luongNgay]) VALUES (3, N'CN003', N'Có mặt', N'ca 3', CAST(N'2022-01-06' AS Date), 300, NULL)
SET IDENTITY_INSERT [dbo].[ChamCongCN] OFF
GO
INSERT [dbo].[CongDoan] ([maCD], [maSP], [tenCD], [tenThanhPham], [giaSX], [trangThaiCD]) VALUES (N'CD001',N'SP001', N'Sản Xuất Cổ Áo', N'Cổ Áo', 20000, N'Đang Sản Xuất')
INSERT [dbo].[CongDoan] ([maCD], [maSP], [tenCD], [tenThanhPham], [giaSX], [trangThaiCD]) VALUES (N'CD002',N'SP002', N'Sản Xuất Tay Áo', N'Tay Áo', 50000, N'Ngừng Sản Xuất')
GO
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt]) VALUES (N'CN001', N'Nguyễn Văn Nam ', N'T001', N'nam', CAST(N'1999-12-12' AS Date), N'TPHCM', N'01413214', N'0312565')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt]) VALUES (N'CN002', N'Nguyễn Thị Hà', N'T001', N'nữ', CAST(N'1998-04-07' AS Date), N'TPHCM', N'05123213', N'03155651')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt]) VALUES (N'CN003', N'Hà Văn Bảy', N'T002', N'nam', CAST(N'1997-04-12' AS Date), N'TPHCM', N'0025313215', N'32123163')
GO
SET IDENTITY_INSERT [dbo].[CT_CD_SX_SP] ON 

INSERT [dbo].[CT_CD_SX_SP] ([stt], [maCD], [maSP], [soCD]) VALUES (1, N'CD001', N'SP001', NULL)
INSERT [dbo].[CT_CD_SX_SP] ([stt], [maCD], [maSP], [soCD]) VALUES (2, N'CD002', N'SP001', NULL)
INSERT [dbo].[CT_CD_SX_SP] ([stt], [maCD], [maSP], [soCD]) VALUES (3, N'CD002', N'SP002', NULL)
SET IDENTITY_INSERT [dbo].[CT_CD_SX_SP] OFF
GO
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [chucVu], [ngaySinh], [gioiTinh], [diaChi], [cccd], [sdt]) VALUES (N'', NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [chucVu], [ngaySinh], [gioiTinh], [diaChi], [cccd], [sdt]) VALUES (N'NV001', N'Nguyễn Văn Trinh', N'HCNS', CAST(N'2001-04-28' AS Date), N'Nam', N'TPHCM', N'052210101353', N'0386200961')
GO
SET IDENTITY_INSERT [dbo].[PhieuLuongCN] ON 

INSERT [dbo].[PhieuLuongCN] ([stt], [maCN], [thang], [soSPSX], [tienLuong]) VALUES (1, N'CN001', N'6/2022', NULL, 700000)
SET IDENTITY_INSERT [dbo].[PhieuLuongCN] OFF
GO
INSERT [dbo].[PhieuLuongNV] ([maNV], [thang], [soNgayCong], [tienLuong]) VALUES (N'NV001', N'10/2022', 26, NULL)
GO
INSERT [dbo].[SanPham] ([maSP], [tenSP], [giaSX], [soLuong]) VALUES (N'SP001', N'áo sơ mi', 50, NULL)
INSERT [dbo].[SanPham] ([maSP], [tenSP], [giaSX], [soLuong]) VALUES (N'SP002', N'áo khoác', 60, NULL)
GO
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV001', N'NV001')
GO
INSERT [dbo].[ToSanXuat] ([maTo], [tenTo], [maCD], [soLuongCN]) VALUES (N'T001', N'Tổ 1', N'CD002', NULL)
INSERT [dbo].[ToSanXuat] ([maTo], [tenTo], [maCD], [soLuongCN]) VALUES (N'T002', N'Tổ 2', N'CD002', NULL)
INSERT [dbo].[ToSanXuat] ([maTo], [tenTo], [maCD], [soLuongCN]) VALUES (N'T003', N'Tổ 3', NULL, NULL)
GO
ALTER TABLE [dbo].[ChamCongCN]  WITH CHECK ADD  CONSTRAINT [FK_ChamCong_CongNhan] FOREIGN KEY([maCN])
REFERENCES [dbo].[CongNhan] ([maCN])
GO
ALTER TABLE [dbo].[ChamCongCN] CHECK CONSTRAINT [FK_ChamCong_CongNhan]
GO
ALTER TABLE [dbo].[ChamCongNV]  WITH CHECK ADD  CONSTRAINT [FK_ChamCongNV_NhanVien] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[ChamCongNV] CHECK CONSTRAINT [FK_ChamCongNV_NhanVien]
GO
ALTER TABLE [dbo].[CongNhan]  WITH CHECK ADD  CONSTRAINT [FK_CongNhan_ToSanXuat] FOREIGN KEY([maTo])
REFERENCES [dbo].[ToSanXuat] ([maTo])
GO
ALTER TABLE [dbo].[CongNhan] CHECK CONSTRAINT [FK_CongNhan_ToSanXuat]
GO
ALTER TABLE [dbo].[CT_CD_SX_SP]  WITH CHECK ADD  CONSTRAINT [FK_CT_CD_SX_SP_CongDoan] FOREIGN KEY([maCD])
REFERENCES [dbo].[CongDoan] ([maCD])
GO
ALTER TABLE [dbo].[CT_CD_SX_SP] CHECK CONSTRAINT [FK_CT_CD_SX_SP_CongDoan]
GO
ALTER TABLE [dbo].[CT_CD_SX_SP]  WITH CHECK ADD  CONSTRAINT [FK_CT_CD_SX_SP_SanPham] FOREIGN KEY([maSP])
REFERENCES [dbo].[SanPham] ([maSP])
GO
ALTER TABLE [dbo].[CT_CD_SX_SP] CHECK CONSTRAINT [FK_CT_CD_SX_SP_SanPham]
GO
ALTER TABLE [dbo].[CTLuongCB]  WITH CHECK ADD  CONSTRAINT [FK_CTLuongCB_NhanVien] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[CTLuongCB] CHECK CONSTRAINT [FK_CTLuongCB_NhanVien]
GO
ALTER TABLE [dbo].[PhieuLuongCN]  WITH CHECK ADD  CONSTRAINT [FK_Luong_CongNhan] FOREIGN KEY([maCN])
REFERENCES [dbo].[CongNhan] ([maCN])
GO
ALTER TABLE [dbo].[PhieuLuongCN] CHECK CONSTRAINT [FK_Luong_CongNhan]
GO
ALTER TABLE [dbo].[PhieuLuongNV]  WITH CHECK ADD  CONSTRAINT [FK_PhieuLuongNV_NhanVien] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[PhieuLuongNV] CHECK CONSTRAINT [FK_PhieuLuongNV_NhanVien]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_NhanVien] FOREIGN KEY([maTK])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_NhanVien]
GO
ALTER TABLE [dbo].[ToSanXuat]  WITH CHECK ADD  CONSTRAINT [FK_ToSanXuat_CongDoan] FOREIGN KEY([maCD])
REFERENCES [dbo].[CongDoan] ([maCD])
GO
ALTER TABLE [dbo].[ToSanXuat] CHECK CONSTRAINT [FK_ToSanXuat_CongDoan]
GO
USE [master]
GO
ALTER DATABASE [QLLuongSP] SET  READ_WRITE 
GO
