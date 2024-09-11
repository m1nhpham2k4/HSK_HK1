use QLBCAFE
go

-- Lấy ra thông tin hóa đơn
create proc getAllHoaDon
as
begin
	SELECT
		HD.MaHD,
		HD.MaKH,
		HD.MaNV,
		HD.NgayTaoHD,
		SUM(SP.DonGia * CTHD.SoLuong) AS TongTien,
		HD.MaBan,
		HD.TrangThai
	FROM
		HoaDon HD
	JOIN
		CTHoaDon CTHD ON HD.MaHD = CTHD.MaHD
	JOIN
		SanPham SP ON CTHD.MaSP = SP.MaSP
	GROUP BY
		HD.MaHD,
		HD.MaKH,
		HD.MaNV,
		HD.NgayTaoHD,
		HD.MaBan,
		HD.TrangThai;
end
go

-- Lấy ra thông tin của một hóa đơn theo mã
create proc getOneHoaDon @maHD nvarchar(50)
as
begin
	SELECT
		HD.MaHD,
		HD.MaKH,
		KH.TenKH,
		HD.MaNV,
		NV.TenNV,
		HD.NgayTaoHD,
		SUM(SP.DonGia * CTHD.SoLuong) AS TongTien,
		HD.MaBan,
		HD.TrangThai
	FROM
		HoaDon HD
	full JOIN
		CTHoaDon CTHD ON HD.MaHD = CTHD.MaHD
	full JOIN
		SanPham SP ON CTHD.MaSP = SP.MaSP
	full JOIN
		KhachHang KH ON HD.MaKH=KH.MaKH
	full JOIN
		NhanVien NV ON HD.MaNV=NV.MaNV
	WHERE
		HD.MaHD = @maHD
	GROUP BY
		HD.MaHD,
		HD.MaKH,
		KH.TenKH,
		HD.MaNV,
		NV.TenNV,
		HD.NgayTaoHD,
		HD.MaBan,
		HD.TrangThai;
end
go

-- Lấy ra thông tin chi tiết hóa đơn
CREATE PROC getCTHD @maHD nvarchar(50)
AS
BEGIN
	SELECT TenSP, SoLuong, ct.DonGia 
	FROM CTHoaDon ct JOIN
		SanPham s ON ct.MaSP = s.MaSP
	WHERE MaHD = @maHD
END
GO

-- Lấy tất cả sản phẩm
create proc getAllSanPham
as
begin
	SELECT
		SP.MaSP,
		SP.TenSP,
		SP.LoaiSP,
		SP.DonGia,
		SP.HinhAnh,
		SP.TrangThai
	FROM
		SanPham SP
end
go

-- Lấy sản phẩm theo mã
create proc getOneSanPham @maSP nvarchar(50)
as
begin
	SELECT
		SP.MaSP,
		SP.TenSP,
		SP.LoaiSP,
		SP.DonGia,
		SP.HinhAnh,
		SP.TrangThai
	FROM
		SanPham SP
	WHERE
		MaSP = @maSP
end
go

-- Lấy tất cả khách hàng
create proc getAllKhachHang
as
begin
	SELECT
		KH.MaKH,
		KH.TenKH,
		KH.SoDT,
		KH.DiemTL
	FROM
		KhachHang KH
end
go

-- Lấy khách hàng theo mã
create proc getOneKhachHang @maKH nvarchar(50)
as
begin
	SELECT
		KH.MaKH,
		KH.TenKH,
		KH.SoDT,
		KH.DiemTL
	FROM
		KhachHang KH
	WHERE
		MaKH = @maKH
end
go

-- Lấy ra số Sản Phẩm
create proc getProductCount
as
begin
	SELECT TOP 1
		MaSP
	FROM
		SanPham
	ORDER BY
		MaSP DESC
end
go

-- Thêm sản phẩm
create proc addSP
@maSP nvarchar(50),
@tenSP nvarchar(50),
@loaiSP nvarchar(50),
@donGia money,
@hinhAnh nvarchar(256),
@trangThai bit
as
begin
	insert into SanPham(MaSP,TenSP,LoaiSP,DonGia,HinhAnh,TrangThai)
	values(@maSP,@tenSP,@loaiSP,@donGia,@hinhAnh,@trangThai)
end
go

-- Xóa sản phẩm
create proc deleteSP @maSP nvarchar(50)
as
begin
	declare @maHD nvarchar(50)
	select top 1 @maHD = MaHD
	from CTHoaDon
	where MaSP = @maSP
	if @maHD is null
		Delete from SanPham where MaSP = @maSP
end
go

-- Cập nhật 1 sản phẩm
create proc updateSP
@maSP nvarchar(50),
@tenSP nvarchar(50),
@loaiSP nvarchar(50),
@donGia money,
@hinhAnh nvarchar(256),
@trangThai bit
as
begin
	update SanPham
	SET TenSP =@tenSP,
		LoaiSP = @loaiSP,
		DonGia = @donGia,
		HinhAnh = @hinhAnh,
		TrangThai = @trangThai
	where MaSP = @maSP
end
go

-- Lấy thông tin của bàn
create proc getALLBan
as
begin
	select
		MaBan,
		SoGhe,
		TrangThai
	from
		Ban
end
go

--Lấy thông tin của bàn theo mã
create proc getOneBan @maBan nvarchar(5)
as
begin
	select
		MaBan,
		SoGhe,
		TrangThai
	from 
		Ban
	where
		MaBan = @maBan
end
go

-- Thêm 1 bàn
create proc addBan
@maBan nvarchar(5),
@soGhe int,
@trangThai int
as
begin
	insert into Ban(MaBan,SoGhe,TrangThai)
	VALUES (@maBan,@soGhe,@trangThai)
end
go

-- Lấy ra số Bàn
create proc getTableCount
as
begin
	SELECT TOP 1
		MaBan
	FROM
		Ban
	ORDER BY
		MaBan DESC
end
go

-- Sửa trạng thái của 1 bàn
create proc updateBan
@maBan nvarchar(5),
@soGhe int,
@trangThai int
as
begin 
	update Ban
	set TrangThai = @trangThai,
		SoGhe = @soGhe
	where MaBan = @maBan
end
go

--Xóa 1 bàn bằng mã bàn
create proc deleteBan @maBan nvarchar(5)
as
begin
	DECLARE @maHD nvarchar(50)
	select top 1 @maHD =MaHD
	from HoaDon 
	where MaBan =@maBan
	if @maHD is null
	delete from Ban where MaBan =  @maBan
end
go

-- Lấy ra số lượng hóa đơn
create proc getSLHoaDon
as
begin
	select count(*)
	from HoaDon
end
go

-- Thêm hóa đơn mới
create proc addHD
@maHD nvarchar(50)
as 
begin
	insert into HoaDon(MaHD, TrangThai)
	values(@maHD, 0)
end
go

-- Kiểm tra tài khoản
create proc checkAccount
@maNV nvarchar(50),
@matKhau nvarchar(50)
as
begin
	select
		MaNV,
		TenNV,
		SoDT,
		NgaySinh,
		DiaChi,
		MatKhau,
		HinhAnh
	from
		NhanVien
	where MaNV=@maNV and MatKhau=@matKhau
end
go

-- Lấy ra các hóa đơn có trạng thái thanh toán là 0
create proc getAllOrderNonPayment
as
begin
	select
		MaHD
	from
		HoaDon
	where
		TrangThai = 0
end
go

-- Lấy ra các chi tiết hóa đơn của một hóa đơn
create proc getCTHDFromHoaDon @maHD nvarchar(50)
as
begin
	select
		ct.MaSP,
		TenSP,
		SoLuong,
		ct.DonGia
	from CTHoaDon ct join
	SanPham s on ct.MaSP=s.MaSP
	where MaHD=@maHD
end
go

-- Thêm chi tiết hóa đơn
create proc addCTHoaDon
@maHD nvarchar(50),
@maSP nvarchar(50),
@soLuong int,
@donGia money
as
begin
	insert into CTHoaDon (MaHD,MaSP,SoLuong,DonGia) values
	(@maHD,@maSP,@soLuong,@donGia)
end
go

-- Tổng hóa đơn
create proc sumTotal @maHD nvarchar(50)
as
begin
	select sum(SoLuong*DonGia)
	from
	CTHoaDon
	where MaHD=@maHD
end
go

-- Cập nhật hóa đơn
create proc updateHD
@maHD nvarchar(50),
@maNV nvarchar(50),
@maKH nvarchar(50),
@ngayTaoHHD dateTime,
@trangThai bit,
@maBan nvarchar(50)
as
begin
	update HoaDon
	SET
		MaNV = @maNV,
		MaKH = @maKH,
		NgayTaoHD = @ngayTaoHHD,
		TrangThai = @trangThai,
		MaBan = @maBan
		
	where MaHD = @maHD
end
go


-- Tìm sản phẩm theo tên và loại
CREATE PROCEDURE findSP
    @tenSP NVARCHAR(50),
    @loaiSP NVARCHAR(50)
AS
BEGIN
    SELECT 
        SP.MaSP,
        SP.TenSP,
        SP.LoaiSP,
        SP.DonGia,
        SP.HinhAnh,
        SP.TrangThai
    FROM SanPham SP
    WHERE SP.TenSP LIKE '%' + @tenSP + '%' COLLATE Vietnamese_CI_AI -- So sánh không phân biệt hoa thường cho TenSP
          AND SP.LoaiSP = @loaiSP COLLATE Vietnamese_CI_AI; -- So sánh không phân biệt hoa thường cho LoaiSP
END;

-- Tìm sản phẩm theo tên
CREATE PROCEDURE findSPByName @tenSP NVARCHAR(50)
AS
BEGIN
    SELECT 
        SP.MaSP,
        SP.TenSP,
        SP.LoaiSP,
        SP.DonGia,
        SP.HinhAnh,
        SP.TrangThai
    FROM SanPham SP
    WHERE SP.TenSP LIKE '%' + @tenSP + '%' COLLATE Vietnamese_CI_AI 
END;

-- Tìm sản phẩm theo loại
CREATE PROCEDURE findSPByType
 @loaiSP NVARCHAR(50)
AS
BEGIN
    SELECT 
        SP.MaSP,
        SP.TenSP,
        SP.LoaiSP,
        SP.DonGia,
        SP.HinhAnh,
        SP.TrangThai
    FROM SanPham SP
    WHERE SP.LoaiSP = @loaiSP COLLATE Vietnamese_CI_AI; -- So sánh không phân biệt hoa thường cho LoaiSP
END;

-- cập nhật tài khoản
create proc updateThongTinNV
@maNV nvarchar(50),
@tenNV nvarchar(50),
@soDT nvarchar(15),
@ngaySinh datetime,
@hinhAnh nvarchar(256)
as
begin
	update NhanVien
	set TenNV=@tenNV,
		SoDT=@soDT,
		NgaySinh=@ngaySinh,
		HinhAnh=@hinhAnh
	where MaNV=@maNV
end
go