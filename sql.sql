USE [master]
GO
/****** Object:  Database [SharingResource]    Script Date: 5/26/2021 2:15:01 PM ******/
CREATE DATABASE [SharingResource]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SharingResource', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.SQLEXPRESS\MSSQL\DATA\SharingResource.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SharingResource_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.SQLEXPRESS\MSSQL\DATA\SharingResource_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [SharingResource] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SharingResource].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SharingResource] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SharingResource] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SharingResource] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SharingResource] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SharingResource] SET ARITHABORT OFF 
GO
ALTER DATABASE [SharingResource] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SharingResource] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SharingResource] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SharingResource] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SharingResource] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SharingResource] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SharingResource] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SharingResource] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SharingResource] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SharingResource] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SharingResource] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SharingResource] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SharingResource] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SharingResource] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SharingResource] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SharingResource] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SharingResource] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SharingResource] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SharingResource] SET  MULTI_USER 
GO
ALTER DATABASE [SharingResource] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SharingResource] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SharingResource] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SharingResource] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SharingResource] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SharingResource] SET QUERY_STORE = OFF
GO
USE [SharingResource]
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
USE [SharingResource]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 5/26/2021 2:15:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[categoryID] [varchar](50) NOT NULL,
	[categoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Request]    Script Date: 5/26/2021 2:15:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Request](
	[RequestID] [nvarchar](100) NOT NULL,
	[userID] [nvarchar](50) NULL,
	[createDate] [datetime] NULL,
	[status] [varchar](8) NULL,
	[resourceID] [nvarchar](50) NULL,
 CONSTRAINT [PK_Oder] PRIMARY KEY CLUSTERED 
(
	[RequestID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RequestDetail]    Script Date: 5/26/2021 2:15:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RequestDetail](
	[orderDetailID] [nvarchar](100) NOT NULL,
	[orderID] [nvarchar](100) NULL,
	[resourceID] [nvarchar](50) NULL,
	[quantity] [int] NULL,
	[checkin] [date] NULL,
	[chechout] [date] NULL,
 CONSTRAINT [PK_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[orderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Resources]    Script Date: 5/26/2021 2:15:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Resources](
	[resourceID] [nvarchar](50) NOT NULL,
	[resourceName] [nvarchar](50) NULL,
	[color] [nvarchar](50) NULL,
	[usingDate] [date] NULL,
	[image] [nvarchar](200) NULL,
	[quantity] [int] NULL,
	[categoryID] [varchar](50) NULL,
	[status] [varchar](50) NULL,
 CONSTRAINT [PK_Resources] PRIMARY KEY CLUSTERED 
(
	[resourceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 5/26/2021 2:15:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[roleID] [nvarchar](50) NOT NULL,
	[roleName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 5/26/2021 2:15:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[userID] [nvarchar](50) NOT NULL,
	[fullName] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[roleID] [nvarchar](50) NULL,
	[status] [varchar](10) NULL,
	[createDate] [date] NULL,
	[address] [nvarchar](50) NULL,
	[phone] [varchar](15) NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'H1', N'Cơ sở vật chất')
GO
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'H2', N'Công cụ')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'001', N'Laptop Dell G3', N'Black', CAST(N'2021-05-24' AS Date), N'Dell.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'002', N'Máy in', N'White', CAST(N'2021-04-19' AS Date), N'001.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'003', N'Phòng họp 001', NULL, CAST(N'2021-05-24' AS Date), N'ph.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'004', N'MSI ', N'Red', CAST(N'2021-05-19' AS Date), N'msi.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'005', N'Bàn phím ', N'Black', CAST(N'2021-04-14' AS Date), N'bp.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'006', N'Phòng họp 002', N'White', CAST(N'2021-10-01' AS Date), N'ph2.jpg', 1, N'H1', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'007', N'Con dấu ', N'Red', CAST(N'2021-04-01' AS Date), N'condau.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'009', N'Bàn họp', N'Blue', CAST(N'2021-06-15' AS Date), N'002.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'010', N'Ghế', N'Black', CAST(N'2021-05-01' AS Date), N'003.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'011', N'Màn hình chiếu', N'White', CAST(N'2021-10-05' AS Date), N'005.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'012', N'Máy chiếu', N'Red', CAST(N'2021-04-25' AS Date), N'006.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'013', N'Bảng trắng', N'Red', CAST(N'2021-04-01' AS Date), N'007.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'014', N'Đèn cây', N'Black', CAST(N'2021-12-01' AS Date), N'008.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'015', N'Bàn tròn ', N'White', CAST(N'2020-12-01' AS Date), N'009.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'016', N'Ghế xoay', N'Red', CAST(N'2021-03-01' AS Date), N'010.jpg', 1, N'H1', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'017', N'Đèn bàn tròn ', N'Blue', CAST(N'2021-04-01' AS Date), N'011.jpg', 1, N'H1', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'018', N'Sạc dự phòng', N'Black', CAST(N'2021-06-01' AS Date), N'012.jpg', 1, N'H1', N'Access right')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'019', N'Ipad', N'White', CAST(N'2020-04-01' AS Date), N'013.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'020', N'Đèn bàn dài', N'Black', CAST(N'2021-05-01' AS Date), N'014.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Resources] ([resourceID], [resourceName], [color], [usingDate], [image], [quantity], [categoryID], [status]) VALUES (N'021', N'Sạc không dây', N'Black', CAST(N'2021-04-12' AS Date), N'015.jpg', 1, N'H2', N'Availble')
GO
INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (N'001', N'Admin')
GO
INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (N'002', N'User')
GO
INSERT [dbo].[Users] ([userID], [fullName], [password], [roleID], [status], [createDate], [address], [phone]) VALUES (N'1212@gmail.com', N'hoang', N'123456', N'002', N'New', CAST(N'2021-05-22' AS Date), N'Chung cu SKy9', N'0356449764')
GO
INSERT [dbo].[Users] ([userID], [fullName], [password], [roleID], [status], [createDate], [address], [phone]) VALUES (N'123@gmail.com', N'Minh hoang111', N'123456', N'002', N'Active', CAST(N'2021-05-20' AS Date), N'Chung cu SKy9', N'0356449764')
GO
INSERT [dbo].[Users] ([userID], [fullName], [password], [roleID], [status], [createDate], [address], [phone]) VALUES (N'minhhoang@gmail.com', N'Minh Hoàng', N'123456', N'001', N'Active', CAST(N'2021-05-12' AS Date), N'Chung cư SKY9', N'356449764')
GO
INSERT [dbo].[Users] ([userID], [fullName], [password], [roleID], [status], [createDate], [address], [phone]) VALUES (N'uio@gmail.com', N'Minh Hoang', N'123456', N'002', N'Active', CAST(N'2021-05-19' AS Date), N'Chung cu SKy9', N'0956449764')
GO
ALTER TABLE [dbo].[Request]  WITH CHECK ADD  CONSTRAINT [FK_Request_Resources] FOREIGN KEY([resourceID])
REFERENCES [dbo].[Resources] ([resourceID])
GO
ALTER TABLE [dbo].[Request] CHECK CONSTRAINT [FK_Request_Resources]
GO
ALTER TABLE [dbo].[Request]  WITH CHECK ADD  CONSTRAINT [FK_Request_Users] FOREIGN KEY([userID])
REFERENCES [dbo].[Users] ([userID])
GO
ALTER TABLE [dbo].[Request] CHECK CONSTRAINT [FK_Request_Users]
GO
ALTER TABLE [dbo].[Resources]  WITH CHECK ADD  CONSTRAINT [FK_Resources_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([categoryID])
GO
ALTER TABLE [dbo].[Resources] CHECK CONSTRAINT [FK_Resources_Category]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Role] FOREIGN KEY([roleID])
REFERENCES [dbo].[Role] ([roleID])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Role]
GO
USE [master]
GO
ALTER DATABASE [SharingResource] SET  READ_WRITE 
GO
