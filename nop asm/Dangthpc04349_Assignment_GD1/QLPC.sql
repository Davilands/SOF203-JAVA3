use master 
USE QLPC	 select * from hang		update HANG set TENHANG='hggg'  where mahang = 'L'
create database QLPC
DROP TABLE NGUOIDUNG
DROP TABLE TAIKHOAN

CREATE TABLE TAIKHOAN
(
	TENTAIKHOAN NVARCHAR(50) NOT NULL,
	EMAIL VARCHAR(20),
	TENDANGNHAP VARCHAR(12) PRIMARY KEY not null,
	MATKHAU  VARCHAR(8) NOT NULL,
	CHUCVU NVARCHAR(20),
	TRANGTHAI BIT,
	QRCODE VARCHAR(50)
)
DROP TABLE TAIKHOAN

CREATE TABLE SANPHAM
(
	MASANPHAM VARCHAR(9) not null primary key,
	MAHANG VARCHAR(12)  not null,
	TENSANPHAM VARCHAR(9),
	HINHANH VARCHAR(50),
	
	HEDIEUHANH VARCHAR(20),
	CPU NVARCHAR(20),
	CARDDOHOA NVARCHAR(20), 
	PIN VARCHAR(10),
	--TRONGLUONG VARCHAR(10)
	TRONGLUONG INT,
	MANHINH VARCHAR(10),
	RAM VARCHAR(10),
	ODIA NVARCHAR(20),

	SOLUONG INT,
	GIA FLOAT,
	NOISANXUAT NVARCHAR(50),
	MAUSAC NVARCHAR(10),
	--primary key(MASANPHAM) 
)
DROP TABLE SANPHAM

CREATE TABLE HANG
(
	MAHANG VARCHAR(12) not null PRIMARY KEY,
	TENHANG VARCHAR(9),
)
DROP TABLE HANG

ALTER TABLE dbo.SANPHAM ADD PRIMARY KEY (MAHANG)
ALTER TABLE SANPHAM ADD PRIMARY KEY (MASANPHAM)


ALTER TABLE dbo.SANPHAM ADD FOREIGN KEY(MAHANG) REFERENCES HANG(MAHANG)
select * from TaiKhoan

insert into 