CREATE DATABASE KidszoneSchool
CREATE TABLE Students(
    RegID INT IDENTITY(1,1) PRIMARY KEY NOT NULL ,
    Name NVARCHAR(50) NULL,
    Address NVARCHAR(50) NULL,
    ParentName NVARCHAR(50) NULL,
    Phone NVARCHAR(50) NULL,
    RegDate DATETIME NULL,
standard NVARCHAR(30) NULL
)
CREATE TABLE Standars(
    standard NVARCHAR(30) PRIMARY KEY not NULL,
    fees NVARCHAR(30)  NULL
)
DROP DATABASE KidszoneSchool
drop TABLE Students
drop TABLE Standars
INSERT INTO standars values ('item1','item3')

ALTER TABLE Students ADD FOREIGN KEY(standard) REFERENCES Standars(standard);
ALTER TABLE Standars ADD FOREIGN KEY(standard) REFERENCES Students(standard);
select *from Standars
select *from Students
delete from Standars
delete from Students
use KidszoneSchool