alter table ADM_TICKET drop column AMOUNT cascade ;
alter table ADM_TICKET add column AMOUNT varchar(255) ;
