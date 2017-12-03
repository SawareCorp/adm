-- alter table ADM_TICKET add column STATUS_ID varchar(32) ^
-- update ADM_TICKET set STATUS_ID = <default_value> ;
-- alter table ADM_TICKET modify column STATUS_ID varchar(32) not null ;
alter table ADM_TICKET add column STATUS_ID varchar(32) not null ;
alter table ADM_TICKET drop column STATUS cascade ;
