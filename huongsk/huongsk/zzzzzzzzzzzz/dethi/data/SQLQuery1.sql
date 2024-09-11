﻿
go
create database QLCauThu
go
use QLCauThu
go
 CREATE TABLE ViTriThiDau(
   maViTri  NVARCHAR (30) primary key,
   tenViTri NVARCHAR (50) NOT NULL,     
);
CREATE TABLE CauThu(
   maCauThu  NVARCHAR (30) primary key,
   tenCauThu NVARCHAR (50)  NULL,
   Tuoi int, 
   maViTri  NVARCHAR (30) ,  
   Constraint F_VT_HN Foreign key(maViTri) references ViTriThiDau(maViTri),
);
INSERT ViTriThiDau([maViTri], [tenViTri]) VALUES ('CF', N'TIỀN ĐẠO')
INSERT ViTriThiDau([maViTri], [tenViTri]) VALUES ('CMF', N'TIỀN VỆ')
INSERT ViTriThiDau([maViTri], [tenViTri]) VALUES ('CB', N'HẬU VỆ')
INSERT ViTriThiDau([maViTri], [tenViTri]) VALUES ('GK', N'THỦ MÔN')


INSERT CauThu([maCauThu], [tenCauThu],[Tuoi],[maViTri]) VALUES ('VDV10', N'Nguyễn Công Phượng',23,N'CF')

INSERT CauThu([maCauThu], [tenCauThu],[Tuoi],[maViTri]) VALUES ('VDV19', N'Nguyễn Quang Hải',21,N'CMF')

INSERT CauThu([maCauThu], [tenCauThu],[Tuoi],[maViTri]) VALUES ('VDV05', N'Đoàn Văn Hậu',19,N'CB')

INSERT CauThu([maCauThu], [tenCauThu],[Tuoi],[maViTri]) VALUES ('VDV03', N'Đỗ Duy Mạnh',21,N'CB')

INSERT CauThu([maCauThu], [tenCauThu],[Tuoi],[maViTri]) VALUES ('VDV01', N'Bùi Tiến Dũng',21,N'GK')