
CREATE TABLE sinhvien
(
	masv VARCHAR(12) PRIMARY KEY,
	ten  NVARCHAR(20),
	gioitinh NVARCHAR(3),
	email varchar(30)
)
DROP TABLE sinhvien
INSERT INTO sinhvien
VALUES
('pc001', 'Nguyen Van An', 'Nam', 'annvpc001@fpt.edu.vn'),
('pc002', 'Tran Van Binh', 'Nam', 'binhtvpc002@fpt.edu.vn'),
('pc003', 'Nguyen Thi Minh', N'Nữ', 'minhntpc003@fpt.edu.vn')

select * from sinhvien