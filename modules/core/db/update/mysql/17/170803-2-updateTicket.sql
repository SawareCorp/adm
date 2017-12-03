alter table ADM_TICKET add column SOURCE_DESCRIPTION_ID varchar(32) ;
alter table ADM_TICKET drop column SOURCE_BANK_ID cascade ;
