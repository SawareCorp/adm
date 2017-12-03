alter table ADM_ANALYTICS add column CONTRACT_SERVICE_LINE_ID varchar(32) ;
alter table ADM_ANALYTICS drop column SERVICE_ID cascade ;
