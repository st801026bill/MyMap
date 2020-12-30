# MyMap
旅遊地圖 : 結合google map 與 H2 DB 或 MySQL，透過resful api實作簡單的旅遊地圖點位CRUD。  
前端: jquery, springboot4, google map api  
後端: spring boot, spring jpa, h2 database or mysql

![image](https://github.com/st801026bill/MyMap/blob/master/mymap.png)
![image](https://github.com/st801026bill/MyMap/blob/master/addMarker.png)

# 建立TABLE(MYSQL)
```sql
create table MARKER (
	SN integer primary key auto_increment,
    `NAME` 			varchar(128),
    COUNTRY_ID 		varchar(10),
    COUNTRY_NAME 	varchar(64),
    CITY_ID 		varchar(10),
    CITY_NAME 		varchar(64),
    ADDRESS 		varchar(128),
    LONGITUDE 		decimal(9,6),
    LATITUDE 		decimal(8,6),
    `COMMENT` 		varchar(128),
    URL 			varchar(512)
);

create table MARKER_KIND (
	SN integer primary key auto_increment,
    COUNTRY_ID 		varchar(10),
    COUNTRY_NAME 	varchar(64),
    CITY_ID 		varchar(10),
    CITY_NAME 		varchar(64)
);

create table DRINKS_QUANTITY (
	SN integer primary key auto_increment,
    `NAME` 			varchar(128),
    ID 				varchar(10),
    QUANTITY		varchar(5)
);

insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Taiwan','台灣','T1','北部');
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Taiwan','台灣','T2','中部');
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Taiwan','台灣','T3','南部');
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Taiwan','台灣','T4','東部');
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Taiwan','台灣','T5','離島');
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Japan','日本','J1','京阪神奈');
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Japan','日本','J2','北海道');
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Australia','澳洲','A1','布里斯本');

```
