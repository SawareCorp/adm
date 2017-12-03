alter table ADM_TICKET add column FACE integer ^
update ADM_TICKET set FACE = 1 where FACE is null ;
alter table ADM_TICKET modify column FACE integer not null ;
