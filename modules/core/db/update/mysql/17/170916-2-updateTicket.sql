alter table ADM_TICKET drop column STATUS cascade ;
alter table ADM_TICKET add column STATUS varchar(50) ^
update ADM_TICKET set STATUS = 'Recall' where STATUS is null ;
alter table ADM_TICKET modify column STATUS varchar(50) not null ;
