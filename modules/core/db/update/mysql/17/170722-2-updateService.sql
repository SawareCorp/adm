alter table ADM_SERVICE add column DEFAULT_TAX integer ^
update ADM_SERVICE set DEFAULT_TAX = 0 where DEFAULT_TAX is null ;
alter table ADM_SERVICE modify column DEFAULT_TAX integer not null ;
-- alter table ADM_SERVICE add column SERVICE_STATUS_ID varchar(32) ^
-- update ADM_SERVICE set SERVICE_STATUS_ID = <default_value> ;
-- alter table ADM_SERVICE modify column SERVICE_STATUS_ID varchar(32) not null ;
alter table ADM_SERVICE add column SERVICE_STATUS_ID varchar(32) not null ;
alter table ADM_SERVICE drop column TAX cascade ;
