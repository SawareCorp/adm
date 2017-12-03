alter table ADM_SUITABLE_BANK add column STATUS integer ^
update ADM_SUITABLE_BANK set STATUS = 1 where STATUS is null ;
alter table ADM_SUITABLE_BANK modify column STATUS integer not null ;
alter table ADM_SUITABLE_BANK add column ANALYTICS_ID varchar(32) ;
