alter table ADM_TICKET add column PHONE varchar(20) ^
update ADM_TICKET set PHONE = '' where PHONE is null ;
alter table ADM_TICKET modify column PHONE varchar(20) not null ;
alter table ADM_TICKET add column CHARACTERISTIC varchar(1000) ;
