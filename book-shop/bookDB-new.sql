USE [master]
GO
DROP DATABASE IF EXISTS [bookDB]
GO

CREATE DATABASE [bookDB]
GO

USE [bookDB]
GO

-- Categories Table
CREATE TABLE [dbo].[tblCategories] (
    [CategoryID] INT IDENTITY(1,1) PRIMARY KEY,
    [CategoryName] NVARCHAR(50) NOT NULL
)
GO

-- Suppliers Table
CREATE TABLE [dbo].[tblSuppliers] (
    [SupplierID] INT IDENTITY(1,1) PRIMARY KEY,
    [CompanyName] NVARCHAR(40) NOT NULL,
    [ContactName] NVARCHAR(50) NULL,
    [Country] NVARCHAR(50) NULL,
    [Phone] NVARCHAR(24) NULL,
    [HomePage] NVARCHAR(MAX) NULL
)
GO

-- Products Table
CREATE TABLE [dbo].[tblProducts] (
    [ProductID] INT IDENTITY(1,1) PRIMARY KEY,
    [ProductName] NVARCHAR(100) NOT NULL,
    [Author] NVARCHAR(100) NULL,
    [SupplierID] INT NULL,
    [CategoryID] INT NULL,
    [UnitPrice] MONEY NULL,
    [UnitsInStock] SMALLINT NULL,
    [QuantitySold] INT NULL,
    [Image] NVARCHAR(MAX) NULL,
    [Description] NVARCHAR(MAX) NULL,
    [ReleaseDate] DATE NULL,
    [Discount] FLOAT NULL,
    [Status] BIT NOT NULL,

    CONSTRAINT FK_Products_Supplier FOREIGN KEY ([SupplierID])
        REFERENCES [dbo].[tblSuppliers] ([SupplierID])
        ON DELETE SET NULL ON UPDATE CASCADE,

    CONSTRAINT FK_Products_Category FOREIGN KEY ([CategoryID])
        REFERENCES [dbo].[tblCategories] ([CategoryID])
)
GO


-- Users Table
CREATE TABLE [dbo].[tblUsers] (
    [UserID] INT IDENTITY(1,1),
    [UserName] NVARCHAR(50) NOT NULL PRIMARY KEY,
    [FullName] NVARCHAR(50) NOT NULL,
    [Password] NVARCHAR(50) NOT NULL,
    [RoleID] NVARCHAR(50) NULL,
    [Email] NVARCHAR(50) NULL,
    [BirthDay] DATE NOT NULL,
    [Address] NVARCHAR(200) NULL,
    [Phone] NVARCHAR(11) NOT NULL,
    [Status] BIT NOT NULL
)
GO

CREATE TABLE [dbo].[tblOrders]( 
 	[OrderID] [int] IDENTITY(1,1) NOT NULL, 
 	[Date] [date] NOT NULL, 
 	[UserName] [nvarchar](50) NOT NULL, 
 	[TotalMoney] [money] NULL, 
	[status] bit not null,
 CONSTRAINT [PK_Order] PRIMARY KEY ([OrderID]),
 CONSTRAINT [FK_Order_User] FOREIGN KEY ([UserName])
 REFERENCES [dbo].[tblUsers] ([UserName]) ON DELETE NO ACTION
)

CREATE TABLE [dbo].[tblOrderDetails]( 
 	[OrderID] [int] NOT NULL,  	
	[ProductID] [int] NOT NULL, 
 	[Quantity] [float] NOT NULL, 
 	[UnitPrice] [money] NULL,
	[Discount] [float] NULL,
	CONSTRAINT [PK_OrderDetail] PRIMARY KEY ([OrderID], [ProductID] ),
	CONSTRAINT [FK_ProductDetail] FOREIGN KEY ([ProductID])
	REFERENCES [dbo].[tblProducts]([ProductID]) ON DELETE CASCADE,
	CONSTRAINT [FK_OrderDetail] FOREIGN KEY ([OrderID])
	REFERENCES [dbo].[tblOrders]([OrderID]) ON DELETE CASCADE
)



-- Insert Sample Data

-- Categories
INSERT INTO [dbo].[tblCategories] ([CategoryName]) VALUES 
(N'Fantasy'),
(N'Horror'),
(N'Science Fiction'),
(N'Manga - Comic')
GO

-- Users
INSERT INTO [dbo].[tblUsers] (
    [UserName], [FullName], [Password], [RoleID], [Email], [BirthDay], [Address], [Phone], [Status]
) VALUES
(N'admin', N'Administrator', N'1', N'AD', NULL, '1990-01-01', NULL, '0123456789', 1),
(N'ce175533', N'Mai Thanh Quan', N'ct', N'MB', NULL, '1999-05-15', NULL, '0987654321', 1);
GO


-- Product
INSERT INTO [dbo].[tblProducts] (
    ProductName,
    Author,
    CategoryID,
    UnitPrice,
    UnitsInStock,
    Image,
    Description,
    ReleaseDate,
    Discount,
    Status
)
VALUES 
(N'Fire and Blood', N'George R R Martin', 1, 237000, 10, N'https://i.postimg.cc/wjjyJrfS/F-B.jpg', N'vv...', '2023-09-01', 0, 1),
(N'86 - 1', N' Asato Asato', 3, 145000, 30, N'https://i.postimg.cc/1zY253M7/86vol01.jpg', N'vv...', '2023-01-15', 0, 1),
(N'86 - 2', N' Asato Asato', 3, 145000, 25, N'https://i.postimg.cc/6qLN3N87/86vol02.jpg', N'vv...', '2023-05-10', 0, 1),
(N'86 - 3', N' Asato Asato', 3, 145000, 15, N'https://i.postimg.cc/0jLJ8DJ5/86vol3.jpg', N'vv...', '2023-06-10', 0, 1),
(N'86 - 4', N' Asato Asato', 3, 145000, 10, N'https://i.postimg.cc/JhtVJz7J/86vol04.jpg', N'vv...', '2024-08-10', 0, 1),
(N'86 - 5', N' Asato Asato', 3, 145000, 10, N'https://i.postimg.cc/XYWtJhWF/86vol05.jpg', N'vv...', '2024-08-10', 0, 1);
(N'Another', N' Yukito Ayatsuji', 2, 140000, 16, N'https://i.postimg.cc/3R9j2YLM/another12.jpg', N'vv...', '2015-06-01', 0, 1),
(N'Another S/0', N' Yukito Ayatsuji', 2, 100000, 10, N'https://i.postimg.cc/cLgjFnWx/another.jpg', N'vv...', '2018-09-01', 0, 1),
(N'Another 2001', N' Yukito Ayatsuji', 2, 210000, 18, N'https://i.postimg.cc/3xKL68qq/another2001.jpg', N'vv...', '2023-06-01', 0, 1),
(N'Soul Eater - 1', N' Atsushi Ohkubo', 4, 95000, 12, N'https://i.postimg.cc/65qjNrNN/soul01.jpg', N'vv...', '2024-06-01', 0, 1),
(N'Soul Eater - 2', N' Atsushi Ohkubo', 4, 95000, 18, N'https://i.postimg.cc/mkL97WsF/soul02.jpg', N'vv...', '2024-08-03', 0, 1),
(N'Soul Eater - 15', N' Atsushi Ohkubo', 4, 95000, 16, N'https://i.postimg.cc/brZz7m0B/soul15.jpg', N'vv...', '2025-02-01', 0, 1),
(N'Soul Eater - 17', N' Atsushi Ohkubo', 4, 95000, 10, N'https://i.postimg.cc/C58KZvZf/soul017.jpg', N'vv...', '2025-03-01', 0, 1),
(N'Attack On Titan - 22', N' Hajime Isayama', 4, 48000, 30, N'https://i.postimg.cc/3wy6gzBN/aot22.jpg', N'vv...', '2025-03-07', 0, 1);
GO

