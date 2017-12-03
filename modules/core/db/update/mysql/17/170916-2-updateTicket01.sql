alter table ADM_TICKET drop column STATUS cascade ;
alter table ADM_TICKET add column STATUS integer ^
update ADM_TICKET set STATUS = 1 where STATUS is null ;
alter table ADM_TICKET modify column STATUS integer not null ;
