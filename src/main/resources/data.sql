-- 코드 테이블 > 국가언어
insert into tb_code(group_id , code_cd , code_nm) values (100 , 'ko');
insert into tb_code(group_id , code_cd , code_nm) values (100 , 'en');

-- 지역 테이블 > 기초 데이터
insert into tb_region(title,lang) values ('한국','ko');
insert into tb_region(title,lang) values ('Korea','en');
insert into tb_region(title,lang) values ('미국','ko');
insert into tb_region(title,lang) values ('USA','en');
insert into tb_region(title,lang) values ('태국','ko');
insert into tb_region(title,lang) values ('THAI','en');