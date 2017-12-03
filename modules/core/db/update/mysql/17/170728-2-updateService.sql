alter table ADM_SERVICE drop column DIRECTION cascade ;
alter table ADM_SERVICE add column DIRECTION varchar(50) ^
update ADM_SERVICE set DIRECTION = '1' where DIRECTION is null ;
alter table ADM_SERVICE modify column DIRECTION varchar(50) not null ;
