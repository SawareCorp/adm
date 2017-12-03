alter table ADM_CONTRACT add column DIRECTION integer ^
update ADM_CONTRACT set DIRECTION = 1 where DIRECTION is null ;
alter table ADM_CONTRACT modify column DIRECTION integer not null ;
alter table ADM_CONTRACT drop column DIRECTIOIN cascade ;
