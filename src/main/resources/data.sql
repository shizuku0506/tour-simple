-- 코드 테이블 > 기초 데이터 (국가언어)
insert into tb_code(group_id , code_cd , code_nm) values (100 , 'ko');
insert into tb_code(group_id , code_cd , code_nm) values (100 , 'en');

-- 지역 테이블 > 기초 데이터
insert into tb_region(title,lang) values ('한국','ko');
insert into tb_region(title,lang) values ('Korea','en');
insert into tb_region(title,lang) values ('미국','ko');
insert into tb_region(title,lang) values ('USA','en');
insert into tb_region(title,lang) values ('태국','ko');
insert into tb_region(title,lang) values ('THAI','en');

-- 컨텐츠 테이블 > 페이크 데이터
insert into tb_content(title , description , lang ) values ('기초컨텐츠1' , '테스트 1' , 'ko');
insert into tb_content(title , description , lang ) values ('기초컨텐츠2' , '테스트 2' , 'ko');
insert into tb_content(title , description , lang ) values ('기초컨텐츠3' , '테스트 3' , 'ko');

-- 스토리지 테이블 > 페이크 데이터
insert into tb_storage(con_seq , file_size , file_path , file_nm, real_file_nm , file_ext , running_sec , file_status
  , mime_tp )
values ( 1 , 1000000 , 'D:/d_temp/123' ,'그림1' , '123' , 'jpg' , 0 , 'C' , 'image/jpg');
insert into tb_storage(con_seq , file_size , file_path , file_nm, real_file_nm , file_ext , running_sec , file_status
  , mime_tp )
values ( 1 , 1000000 , 'D:/d_temp/123' ,'영상1' , '123' , 'wmv' , 30 , 'C' , 'video/wmv');
insert into tb_storage(con_seq , file_size , file_path , file_nm, real_file_nm , file_ext , running_sec , file_status
  , mime_tp )
values ( 1 , 1000000 , 'D:/d_temp/123' ,'그림2' , '123' , 'jpg' , 0 , 'C' , 'image/jpg');