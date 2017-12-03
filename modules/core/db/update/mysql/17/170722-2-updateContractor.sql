alter table ADM_CONTRACTOR add column FACE integer ^
update ADM_CONTRACTOR set FACE = 1 where FACE is null ;
alter table ADM_CONTRACTOR modify column FACE integer not null ;
alter table ADM_CONTRACTOR add column COMMENT_ varchar(1000) ;
alter table ADM_CONTRACTOR drop column CONTRACT_NUMBER cascade ;
