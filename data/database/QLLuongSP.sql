USE [master]
GO
/****** Object:  Database [QLLuongSP]    Script Date: 10/31/2022 1:33:59 AM ******/
CREATE DATABASE [QLLuongSP]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QLLuongSP', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QLLuongSP.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QLLuongSP_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QLLuongSP_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
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
/****** Object:  Table [dbo].[ChamCongCN]    Script Date: 10/31/2022 1:33:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChamCongCN](
	[maCN] [nvarchar](50) NOT NULL,
	[caLamViec] [nvarchar](50) NULL,
	[ngay] [date] NULL,
	[soLuongSX] [int] NULL,
	[luongNgay] [float] NULL,
	[stt] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChamCongNV]    Script Date: 10/31/2022 1:33:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChamCongNV](
	[maNV] [nvarchar](50) NULL,
	[ngayVang] [date] NULL,
	[stt] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_ChamCongNV] PRIMARY KEY CLUSTERED 
(
	[stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CongDoan]    Script Date: 10/31/2022 1:33:59 AM ******/
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
/****** Object:  Table [dbo].[CongNhan]    Script Date: 10/31/2022 1:33:59 AM ******/
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
	[trangThai] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_CongNhan] PRIMARY KEY CLUSTERED 
(
	[maCN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CT_CD_SX_SP]    Script Date: 10/31/2022 1:33:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_CD_SX_SP](
	[stt] [int] IDENTITY(1,1) NOT NULL,
	[maCD] [nvarchar](50) NULL,
	[maSP] [nvarchar](50) NULL,
	[giaSX] [float] NULL,
 CONSTRAINT [PK_CT_CD_SX_SP] PRIMARY KEY CLUSTERED 
(
	[stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTLuongCB]    Script Date: 10/31/2022 1:33:59 AM ******/
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
/****** Object:  Table [dbo].[NhanVien]    Script Date: 10/31/2022 1:33:59 AM ******/
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
	[trangThai] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuLuongCN]    Script Date: 10/31/2022 1:33:59 AM ******/
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
/****** Object:  Table [dbo].[PhieuLuongNV]    Script Date: 10/31/2022 1:33:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuLuongNV](
	[stt] [int] IDENTITY(1,1) NOT NULL,
	[maNV] [nvarchar](50) NOT NULL,
	[thang] [date] NULL,
	[soNgayCong] [int] NULL,
	[tienLuong] [float] NULL,
 CONSTRAINT [PK_PhieuLuongNV] PRIMARY KEY CLUSTERED 
(
	[stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 10/31/2022 1:33:59 AM ******/
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
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 10/31/2022 1:33:59 AM ******/
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
/****** Object:  Table [dbo].[ToSanXuat]    Script Date: 10/31/2022 1:33:59 AM ******/
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

INSERT [dbo].[ChamCongCN] ([maCN], [caLamViec], [ngay], [soLuongSX], [luongNgay], [stt]) VALUES (N'CN001', N'ca 1', CAST(N'2022-01-06' AS Date), 200, 340, 1)
INSERT [dbo].[ChamCongCN] ([maCN], [caLamViec], [ngay], [soLuongSX], [luongNgay], [stt]) VALUES (N'CN002', N'ca2', CAST(N'2022-01-06' AS Date), NULL, NULL, 2)
INSERT [dbo].[ChamCongCN] ([maCN], [caLamViec], [ngay], [soLuongSX], [luongNgay], [stt]) VALUES (N'CN003', N'ca 3', CAST(N'2022-01-06' AS Date), 300, NULL, 3)
INSERT [dbo].[ChamCongCN] ([maCN], [caLamViec], [ngay], [soLuongSX], [luongNgay], [stt]) VALUES (N'CN001', N'Ca 1', CAST(N'2022-10-30' AS Date), 100, 100000, 4)
INSERT [dbo].[ChamCongCN] ([maCN], [caLamViec], [ngay], [soLuongSX], [luongNgay], [stt]) VALUES (N'CN002', N'Ca 1', CAST(N'2022-10-30' AS Date), 100, 100000, 5)
INSERT [dbo].[ChamCongCN] ([maCN], [caLamViec], [ngay], [soLuongSX], [luongNgay], [stt]) VALUES (N'CN003', N'Ca 1', CAST(N'2022-10-30' AS Date), 100, 100000, 6)
SET IDENTITY_INSERT [dbo].[ChamCongCN] OFF
GO
SET IDENTITY_INSERT [dbo].[ChamCongNV] ON 

INSERT [dbo].[ChamCongNV] ([maNV], [ngayVang], [stt]) VALUES (N'NV001', CAST(N'2022-10-31' AS Date), 1)
INSERT [dbo].[ChamCongNV] ([maNV], [ngayVang], [stt]) VALUES (N'NV002', CAST(N'2022-10-31' AS Date), 2)
INSERT [dbo].[ChamCongNV] ([maNV], [ngayVang], [stt]) VALUES (N'NV003', CAST(N'2022-10-31' AS Date), 3)

SET IDENTITY_INSERT [dbo].[ChamCongNV] OFF
GO
INSERT [dbo].[CongDoan] ([maCD], [maSP], [tenCD], [tenThanhPham], [giaSX], [trangThaiCD]) VALUES (N'CD001', N'SP001', N'Sản Xuất Cổ Áo', N'Cổ Áo', 20000, N'Đang Sản Xuất')
INSERT [dbo].[CongDoan] ([maCD], [maSP], [tenCD], [tenThanhPham], [giaSX], [trangThaiCD]) VALUES (N'CD002', N'SP002', N'Sản Xuất Tay Áo', N'Tay Áo', 50000, N'Đang Sản Xuất')
INSERT [dbo].[CongDoan] ([maCD], [maSP], [tenCD], [tenThanhPham], [giaSX], [trangThaiCD]) VALUES (N'CD003', N'SP001', N'Sản Xuất Cổ Áo', N'Cổ Áo', 100000, N'Đang Sản Xuất')
GO
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN001', N'Nguyễn Văn Nam ', N'T001', N'Nam', CAST(N'1999-12-12' AS Date), N'15 Nguyễn Kiệm, Q.Gò Vấp', N'014132141475', N'0312565742', N'Đang làm')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN002', N'Nguyễn Thị Hà', N'T001', N'Nữ', CAST(N'1998-04-07' AS Date), N'60 Thống Nhất, p10, Q.Gò Vấp', N'051232137415', N'0315565114', N'Đang làm')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN003', N'Hà Văn Bảy', N'T002', N'Nam', CAST(N'1997-04-12' AS Date), N'34 Nguyễn Văn Linh, Q.7', N'00253132152142', N'3212316354', N'Đang làm')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN004', N'Nguyễn Văn Đông ', N'T001', N'Nam', CAST(N'1994-01-12' AS Date), N'15 Nguyễn Kiệm, Q.Gò Vấp', N'014132147954', N'0312564125', N'Đang làm')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN005', N'Nguyễn Thị Bánh', N'T001', N'Nữ', CAST(N'1992-01-27' AS Date), N'123 Trần Quốc Thảo quận  3,TP.HCM', N'051232133112', N'0315561547', N'Đang làm')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN006', N'Hà Văn Tám', N'T002', N'Nam', CAST(N'1997-04-12' AS Date), N'123 Trần Quốc Thảo quận  3,TP.HCM', N'00253132153475', N'3212316473', N'Đang làm')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN007', N'Nguyễn Văn Kiên ', N'T001', N'Nam', CAST(N'2002-02-11' AS Date), N'15 Nguyễn Kiệm, Q.Gò Vấp', N'014132146451', N'0312523565', N'Đang làm')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN008', N'Nguyễn Thị Thảo', N'T001', N'Nữ', CAST(N'2000-07-07' AS Date), N'60 Thống Nhất, p10, Q.Gò Vấp', N'051232137895', N'0315564851', N'Đang làm')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN009', N'Hà Văn Sáu', N'T002', N'Nam', CAST(N'1997-06-17' AS Date), N'34 Nguyễn Văn Linh, Q.7', N'00253132157412', N'3212316374', N'Đang làm')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN010', N'Nguyễn Văn Ba ', N'T001', N'Nam', CAST(N'1989-02-22' AS Date), N'123 Trần Quốc Thảo quận  3,TP.HCM', N'01413213150', N'0312785565', N'Đang làm')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN011', N'Nguyễn Thị Tứ', N'T001', N'Nữ', CAST(N'1998-04-09' AS Date), N'23 Phú Mỹ Hưng quận 7,TP.HCM', N'051232131248', N'0315514651', N'Đang làm')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN012', N'Hà Văn Anh', N'T002', N'Nam', CAST(N'1993-04-22' AS Date), N'15 Nguyễn Kiệm, Q.Gò Vấp', N'00253132159653', N'3212352163', N'Đang làm')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN013', N'Nguyễn Văn Xu ', N'T001', N'Nam', CAST(N'1991-12-02' AS Date), N'23 Phú Mỹ Hưng quận 7,TP.HCM', N'014132143258', N'0314252565', N'Đang làm')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN014', N'Nguyễn Thị Lan', N'T001', N'Nữ', CAST(N'1998-04-07' AS Date), N'60 Thống Nhất, p10, Q.Gò Vấp', N'051232131427', N'03155645251', N'Đang làm')
INSERT [dbo].[CongNhan] ([maCN], [tenCN], [maTo], [gioiTinh], [ngaySinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'CN015', N'Hà Văn Bảy', N'T002', N'Nam', CAST(N'1997-04-12' AS Date), N'23 Phú Mỹ Hưng quận 7,TP.HCM', N'0025313215', N'3212475163', N'Đang làm')
GO
SET IDENTITY_INSERT [dbo].[CT_CD_SX_SP] ON 

INSERT [dbo].[CT_CD_SX_SP] ([stt], [maCD], [maSP], [giaSX]) VALUES (1, N'CD001', N'SP001', 20000)
INSERT [dbo].[CT_CD_SX_SP] ([stt], [maCD], [maSP], [giaSX]) VALUES (2, N'CD002', N'SP002', 50000)
INSERT [dbo].[CT_CD_SX_SP] ([stt], [maCD], [maSP], [giaSX]) VALUES (3, N'CD003', N'SP001', 100000)
SET IDENTITY_INSERT [dbo].[CT_CD_SX_SP] OFF
GO
INSERT [dbo].[CTLuongCB] ([maNV], [luongCB], [phuCap], [heSoLuong]) VALUES (N'NV001', 2000000, 500000, 3.2)
INSERT [dbo].[CTLuongCB] ([maNV], [luongCB], [phuCap], [heSoLuong]) VALUES (N'NV002', 2000000, 500000, 3.2)
INSERT [dbo].[CTLuongCB] ([maNV], [luongCB], [phuCap], [heSoLuong]) VALUES (N'NV003', 2000000, 500000, 3.2)
GO
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [chucVu], [ngaySinh], [gioiTinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'NV001', N'Nguyễn Văn Trinh', N'HCNS', CAST(N'2001-04-28' AS Date), N'Nam', N'15 Nguyễn Kiệm, Q.Gò Vấp', N'052210101453', N'0386200124', N'Đang làm')
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [chucVu], [ngaySinh], [gioiTinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'NV002', N'Đinh Đức Đạt', N'HCNS', CAST(N'2000-08-11' AS Date), N'Nam', N'34 Nguyễn Văn Linh, Q.7', N'547635625142', N'0332452056', N'Đang làm')
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [chucVu], [ngaySinh], [gioiTinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'NV003', N'Nguyễn Thị Nhi', N'HCNS', CAST(N'2002-08-04' AS Date), N'Nữ', N'60 Thống Nhất, p10, Q.Gò Vấp', N'754214245521', N'0984521475', N'Đang làm')
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [chucVu], [ngaySinh], [gioiTinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'NV004', N'Nguyễn Văn Tuấn', N'HCNS', CAST(N'1998-07-04' AS Date), N'Nam', N'23 Ngô Quyền, Gò Vấp, TPHCM', N'025610101353', N'0386200214', N'Đang làm')
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [chucVu], [ngaySinh], [gioiTinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'NV005', N'Trần Quốc Việt', N'HCNS', CAST(N'2002-08-12' AS Date), N'Nam', N'118 Hoàng Văn Thụ, Q.Phú Nhuận, Tp.HCM', N'547895425142', N'0332314756', N'Đang làm')
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [chucVu], [ngaySinh], [gioiTinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'NV006', N'Nguyễn Thị Mi', N'Quản lí', CAST(N'2002-10-18' AS Date), N'Nữ', N'12 Hai Bà Trưng, Bắc Ninh', N'754214845721', N'0984521245', N'Đang làm')
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [chucVu], [ngaySinh], [gioiTinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'NV007', N'Phạm Hữu Quốc Toàn', N'HCNS', CAST(N'2001-04-02' AS Date), N'Nam', N'784 Hai Bà Trưng, Huế', N'052214781353', N'0312400961', N'Đang làm')
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [chucVu], [ngaySinh], [gioiTinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'NV008', N'Đinh Thị Tiên', N'HCNS', CAST(N'1980-03-05' AS Date), N'Nữ', N'53 thống nhất, P11, gò vấp, TP. HCM', N'547647425142', N'1455324556', N'Đang làm')
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [chucVu], [ngaySinh], [gioiTinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'NV009', N'Nguyễn Thị Lan', N'Quản lí', CAST(N'1998-11-30' AS Date), N'Nữ', N'123 Trần Quốc Thảo quận  3,TP.HCM', N'754215621521', N'0984578556', N'Đang làm')
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [chucVu], [ngaySinh], [gioiTinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'NV010', N'Nguyễn Văn Đức', N'HCNS', CAST(N'1997-02-08' AS Date), N'Nam', N'50 Phan Văn Trị, Gò Vấp, TP.HCM', N'052148101353', N'0383650961', N'Đang làm')
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [chucVu], [ngaySinh], [gioiTinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'NV011', N'Phạm Văn Phúc', N'HCNS', CAST(N'1999-04-28' AS Date), N'Nam', N'68 Tân Kỳ Tân Qúy, TP. HCM', N'547632425142', N'0875318056', N'Đang làm')
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [chucVu], [ngaySinh], [gioiTinh], [diaChi], [cccd], [sdt], [trangThai]) VALUES (N'NV012', N'Trần Thị Anh', N'HCNS', CAST(N'2003-01-08' AS Date), N'Nữ', N'23 Phú Mỹ Hưng quận 7,TP.HCM', N'754214785521', N'0984521245', N'Đang làm')

GO
SET IDENTITY_INSERT [dbo].[PhieuLuongCN] ON 

INSERT [dbo].[PhieuLuongCN] ([stt], [maCN], [thang], [soSPSX], [tienLuong]) VALUES (1, N'CN001', N'6/2022', NULL, 700000)
SET IDENTITY_INSERT [dbo].[PhieuLuongCN] OFF
GO
SET IDENTITY_INSERT [dbo].[PhieuLuongNV] ON 

INSERT [dbo].[PhieuLuongNV] ([stt], [maNV], [thang], [soNgayCong], [tienLuong]) VALUES (1, N'NV002', CAST(N'2022-10-30' AS Date), 23, 6103846.153846154)
INSERT [dbo].[PhieuLuongNV] ([stt], [maNV], [thang], [soNgayCong], [tienLuong]) VALUES (2, N'NV003', CAST(N'2022-10-30' AS Date), 26, 6900000)
INSERT [dbo].[PhieuLuongNV] ([stt], [maNV], [thang], [soNgayCong], [tienLuong]) VALUES (3, N'NV001', CAST(N'2022-10-30' AS Date), 24, 6369230.769230769)
SET IDENTITY_INSERT [dbo].[PhieuLuongNV] OFF
GO
INSERT [dbo].[SanPham] ([maSP], [tenSP], [giaSX], [soLuong]) VALUES (N'SP001', N'áo sơ mi', 50, NULL)
INSERT [dbo].[SanPham] ([maSP], [tenSP], [giaSX], [soLuong]) VALUES (N'SP002', N'áo khoác', 60, NULL)
GO
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV001', N'NV001')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV002', N'NV002')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV003', N'NV003')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV004', N'NV004')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV005', N'NV005')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV006', N'NV006')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV007', N'NV007')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV008', N'NV008')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV009', N'NV009')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV010', N'NV010')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV011', N'NV011')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV012', N'NV012')
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
