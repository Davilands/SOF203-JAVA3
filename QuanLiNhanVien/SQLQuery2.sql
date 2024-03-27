drop database qlnv
create database qlnv
use qlnv

CREATE TABLE Nhanvien(
    msnv NVARCHAR(10)  PRIMARY KEY NOT NULL,
    Hoten NVARCHAR(50),
    quequan NVARCHAR(50) ,
    cccd NVARCHAR(20) ,
    gioitinh bit,
	hinhanh varchar(50),
	sdt varchar(12),
	 luong float
)

--drop table nhanvien
--delete from nhanvien
insert into nhanvien values
('nv019',N'Nguyễn Văn An','Can Tho','09879342023932',1,null,'0392023335',5000000.0),
('nv02',N'Lê Thị Bình','Can Tho','09879342023932',0,null,'0988909890',3000000),
('nv03',N'Trương Quốc Bảo','Can Tho','09879342023932',1,null,'0923432221',7000000),
('nv04',N'Hoàng Thanh Nam','Can Tho','09879342023932',1,null,'0909090008',2000000),
('nv05',N'Lý Quốc Khánh','Can Tho','09879342023932',1,null,'0933453223',9000000)

select * from nhanvien
select * from nhanvien