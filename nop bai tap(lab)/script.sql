USE [master]
GO
/****** Object:  Database [qlsinhvien]    Script Date: 8/3/2022 9:23:31 AM ******/
CREATE DATABASE [qlsinhvien]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'qlsinhvien', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\qlsinhvien.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'qlsinhvien_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\qlsinhvien_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [qlsinhvien] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [qlsinhvien].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [qlsinhvien] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [qlsinhvien] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [qlsinhvien] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [qlsinhvien] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [qlsinhvien] SET ARITHABORT OFF 
GO
ALTER DATABASE [qlsinhvien] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [qlsinhvien] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [qlsinhvien] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [qlsinhvien] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [qlsinhvien] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [qlsinhvien] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [qlsinhvien] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [qlsinhvien] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [qlsinhvien] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [qlsinhvien] SET  DISABLE_BROKER 
GO
ALTER DATABASE [qlsinhvien] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [qlsinhvien] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [qlsinhvien] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [qlsinhvien] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [qlsinhvien] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [qlsinhvien] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [qlsinhvien] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [qlsinhvien] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [qlsinhvien] SET  MULTI_USER 
GO
ALTER DATABASE [qlsinhvien] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [qlsinhvien] SET DB_CHAINING OFF 
GO
ALTER DATABASE [qlsinhvien] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [qlsinhvien] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [qlsinhvien] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [qlsinhvien] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [qlsinhvien] SET QUERY_STORE = OFF
GO
USE [qlsinhvien]
GO
/****** Object:  Table [dbo].[sinhvien]    Script Date: 8/3/2022 9:23:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sinhvien](
	[masv] [varchar](12) NOT NULL,
	[ten] [nvarchar](20) NULL,
	[gioitinh] [nvarchar](3) NULL,
	[email] [varchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[masv] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[students]    Script Date: 8/3/2022 9:23:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[students](
	[MASV] [varchar](50) NOT NULL,
	[Hoten] [nvarchar](50) NULL,
	[EMAIL] [varchar](50) NULL,
	[sodt] [varchar](20) NULL,
	[GIOITINH] [bit] NULL,
	[diachi] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[MASV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
USE [master]
GO
ALTER DATABASE [qlsinhvien] SET  READ_WRITE 
GO
