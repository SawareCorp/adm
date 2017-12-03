-- alter table ADM_ANALYTICS add column CONTRACT_SERVICE_LINE_ID varchar(32) ^
-- update ADM_ANALYTICS set CONTRACT_SERVICE_LINE_ID = <default_value> ;
-- alter table ADM_ANALYTICS modify column CONTRACT_SERVICE_LINE_ID varchar(32) not null ;
alter table ADM_ANALYTICS add column CONTRACT_SERVICE_LINE_ID varchar(32) not null ;
