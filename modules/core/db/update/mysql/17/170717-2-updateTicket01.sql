alter table ADM_TICKET drop column STATUS_DATE_TIME cascade ;
alter table ADM_TICKET add column STATUS_DATE_TIME datetime(3) ;
