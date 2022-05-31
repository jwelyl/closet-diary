--- Clothes Query ---

---insert---
insert into clothes (name, category, brand, purchase_date, price, origin_pic_name, pic_path)
values ('jwelyl', 'shirts', 'bonded', '2022-05-17', 13000, 'shirts1', '/Users/heojaeseong');

insert into clothes (name, category, brand, purchase_date, price, origin_pic_name, pic_path)
values ('jaeseong', 'pants', 'unknown', '2019-05-29', 20000, 'pants1', '/Users/heojaeseong/clothes');

insert into clothes (name, category, brand, purchase_date, price, origin_pic_name, pic_path)
values ('cork', 'hoodie', 'toffee', '2016-03-21', 39000, 'hoodie1', '/Users/heojaeseong/clothes/hoodie');

---select---
select * from clothes;

--- Picture Query ---

---insert---
insert into picture (origin_file_name, file_name, file_path, file_size)
values ('origin_pic_name0', 'pic_name0', '/sogang/under/cse20161663/path0', 1024);

insert into picture (origin_file_name, file_name, file_path, file_size)
values ('origin_pic_name1', 'pic_name1', '/sogang/under/cse20161663/path1', 2048);

insert into picture (origin_file_name, file_name, file_path, file_size)
values ('origin_pic_name2', 'pic_name2', '/sogang/under/cse20161663/path2', 4096);

---select---
select * from picture;
