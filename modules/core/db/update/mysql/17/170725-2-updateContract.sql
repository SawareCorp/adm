-- alter table ADM_CONTRACT add column RESPONSIBLE_MANAGER_ID varchar(32) ^
-- update ADM_CONTRACT set RESPONSIBLE_MANAGER_ID = <default_value> ;
-- alter table ADM_CONTRACT modify column RESPONSIBLE_MANAGER_ID varchar(32) not null ;
alter table ADM_CONTRACT add column RESPONSIBLE_MANAGER_ID varchar(32) not null ;
