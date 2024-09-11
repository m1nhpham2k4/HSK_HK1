create database QLBCAFE
go

USE QLBCAFE
go

create table NhanVien(
	MaNV nvarchar(50) not null primary key,
	TenNV nvarchar(50),
	SoDT nvarchar(15),
	NgaySinh DateTime,
	DiaChi nvarchar(50),
	MatKhau nvarchar(50),
	HinhAnh nvarchar(256),
)
go

create table KhachHang(
	MaKH nvarchar(50) not null primary key,
	TenKH nvarchar(50),
	SoDT nvarchar(15),
	DiemTL float(2)
)
go

create table Ban(
	MaBan nvarchar(5) not null primary key,
	SoGhe int,
	TrangThai int,
)
go

create table HoaDon(
	MaHD nvarchar(50) not null primary key,
	MaNV nvarchar(50),
	MaKH nvarchar(50),
	NgayTaoHD dateTime,
	TrangThai bit,
	MaBan nvarchar(5),
	Constraint fk_MaNV
	Foreign key (MaNV)
	REFERENCES NhanVien(MaNV),
	Constraint fk_MaKH
	Foreign key (MaKH)
	REFERENCES KhachHang(MaKH),
	Constraint fk_MaBan
	Foreign key (MaBan)
	REFERENCES Ban(MaBan)
)
go

create table SanPham (
	MaSP nvarchar(50) not null primary key,
	TenSP nvarchar(50),
	LoaiSP nvarchar(50),
	DonGia money,
	HinhAnh nvarchar(256),
	TrangThai bit
)
go

create table CTHoaDon(
	MaSP nvarchar(50) not null ,
	MaHD nvarchar(50) not null ,
	SoLuong int,
	DonGia money
	Constraint fk_MaSP
	Foreign key (MaSP)
	REFERENCES SanPham(MaSP),
	Constraint fk_MaHD
	Foreign key (MaHD)
	REFERENCES HoaDon(MaHD),
	CONSTRAINT PK_MASP_MAHD PRIMARY KEY (MaSP, MaHD)
)
go

create table PhieuDat(
	MaPhieu nvarchar(50) not null primary key,
	YeuCau nvarchar(50),
	NgayDat DateTime
)
go

create table CTPhieuDat(
	MaPhieu nvarchar(50) not null ,
	MaBan nvarchar(5) not null 
	
	Constraint fk_MaPhieu
	Foreign key (MaPhieu)
	REFERENCES PhieuDat(MaPhieu),
	Constraint fk_MaBanPhieu
	Foreign key (MaBan)
	REFERENCES Ban(MaBan),
	CONSTRAINT PK_MAPHIEU_MABAN PRIMARY KEY (MaPhieu, MaBan)
)
go

insert into NhanVien values
('NV1',N'Giang','003030303','2004-12-01','Long Thanh','@Giang11111','img\avatar.jpg'),
('NV2',N'Son','0222222','2004-9-01','Binh Duong','@Truong22222','img\avatar.jpg'),
('NV3',N'Trường','0111111','2004-8-01','Bien Hoa','@Son33333','img\avatar.jpg')
go

insert into KhachHang values
('KH1',N'Như','062345433',40),
('KH2',N'Linh','062345433',15.5),
('KH3',N'Hậu','062345433',43.6)
go

insert into Ban values
('B1',10,1),
('B2',10,2)
go

insert into HoaDon values
('20240403-00001','NV1','KH1','2024-4-12',1,'B1'),
('20240403-00002','NV2','KH2','2024-4-10',1,'B2')
go

insert into SanPham values
('SP00001',N'Cà Phê',N'Thức Uống',2500,'abc',1),
('SP00002',N'Nước Suối',N'Thức Uống',10000,'abc',0),
('SP00003',N'Bánh kem',N'Bánh',40000,'abc', 1)
go

insert into CTHoaDon values 
('SP00001','20240403-0000',2,50000),
('SP00002','20240403-0000',2,20000)
go