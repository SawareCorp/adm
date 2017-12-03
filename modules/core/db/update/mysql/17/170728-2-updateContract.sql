alter table ADM_CONTRACT drop column DIRECTION cascade ;
alter table ADM_CONTRACT add column DIRECTION varchar(50) ^
update ADM_CONTRACT set DIRECTION = '1' where DIRECTION is null ;
alter table ADM_CONTRACT modify column DIRECTION varchar(50) not null ;
