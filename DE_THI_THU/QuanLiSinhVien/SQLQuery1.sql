create database qlnv
use qlsv

CREATE TABLE Nhanvien(
    msnv NVARCHAR(10)  PRIMARY KEY NOT NULL,
    Hoten NVARCHAR(50),
    quequan NVARCHAR(50) ,
    cccd NVARCHAR(20) ,
    gioitinh bit,
	hinhanh varchar(50),
	sdt varchar(12),
	float luong
)

--drop table nhanvien
insert into nhanvien values
('nv01',N'Nguyễn Văn An','Can Tho','09879342023932',1,null,'0392023335'),
('nv02',N'Lê Thị Bình','Can Tho','09879342023932',1,null,'0988909890'),
('nv03',N'Trương Quốc Bảo','Can Tho','09879342023932',1,null,'0923432221'),
('nv04',N'Hoàng Thanh Nam','Can Tho','09879342023932',1,null,'0909090008'),
('nv05',N'Lý Quốc Khánh','Can Tho','09879342023932',1,null,'0933453223'),

select * from sinhvien