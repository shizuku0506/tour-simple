drop database if exists tour;
create database tour;
use tour;

create table tb_code
(
  seq int auto_increment,
  gourp_id int,
  code_cd varchar(10) null,
  code_nm varchar(100) null,
  constraint tb_code_pk
    primary key (seq)
);

create table tb_region
(
  seq int auto_increment,
  title varchar(1000) null,
  lang CHAR(2) NOT NULL,
  create_dt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  update_dt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  del_yn CHAR(1) DEFAULT 'N',
  constraint tb_region_pk
   primary key (seq)
);

create table tb_content
(
  seq int auto_increment,
  title varchar(1000) NOT NULL,
  description varchar(4000) NULL,
  lang CHAR(2) NOT NULL,
  create_dt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  update_dt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  del_yn CHAR(1) DEFAULT 'N',
  constraint tb_content_pk
    primary key (seq)
);

create table tb_storage
(
  seq int auto_increment,
  con_seq int NOT NULL,
  file_size bigint NOT NULL,
  file_path varchar(200) NOT NULL,
  file_nm varchar(100) NOT NULL,
  real_file_nm varchar(100) NOT NULL,
  file_ext varchar(4) NOT NULL,
  running_sec int NOT NULL,
  file_status char(1) NOT NULL,
  mime_tp varchar(20) NOT NULL,
  create_dt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  constraint tb_storage_pk
    primary key (seq)
);