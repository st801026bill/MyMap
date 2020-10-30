/*insert into MARKER (NAME, COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME, ADDRESS, LATITUDE, LONGITUDE, COMMENT, URL)
values ('台北101', 'TAIWAN','台灣' ,'T1' ,'台灣 - 北部' , '111台灣台北市信義區信義路五段7號', 23.5, 120, '台灣最高大樓', 'https://yahoo.com.tw');
*/
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Taiwan','台灣','T1','北部');
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Taiwan','台灣','T2','中部');
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Taiwan','台灣','T3','南部');
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Taiwan','台灣','T4','東部');
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Taiwan','台灣','T5','離島');
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Japan','日本','J1','京阪神奈');
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Japan','日本','J2','北海道');
insert into MARKER_KIND (COUNTRY_ID, COUNTRY_NAME, CITY_ID, CITY_NAME) values ('Australia','澳洲','A1','布里斯本');

insert into DRINKS_QUANTITY  (ID, NAME, QUANTITY) values ('A01', '珍珠奶茶', 100);
insert into DRINKS_QUANTITY  (ID, NAME, QUANTITY) values ('A02', '阿薩姆奶茶', 50);
insert into DRINKS_QUANTITY  (ID, NAME, QUANTITY) values ('B01', '斯里蘭卡紅茶', 0);