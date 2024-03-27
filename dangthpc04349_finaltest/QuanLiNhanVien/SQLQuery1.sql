create database qlsv
use qlsv

CREATE TABLE Sinhvien(
    MSSV NVARCHAR(10)  PRIMARY KEY NOT NULL,
    Hoten NVARCHAR(50),
    quequan NVARCHAR(50) ,
    cccd NVARCHAR(20) ,
    gioitinh bit,
    lop nvarchar(20),
	hinhanh varchar(50),
)

--drop table sinhvien
insert into sinhvien values
('Pc00001','Nguyen Van An','Can Tho','09879342023932',1,'IT17305',null)

select * from sinhvien