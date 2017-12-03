alter table ADM_TICKET_STATE add column HAS_DATE_TIME boolean ^
update ADM_TICKET_STATE set HAS_DATE_TIME = false where HAS_DATE_TIME is null ;
alter table ADM_TICKET_STATE modify column HAS_DATE_TIME boolean not null ;
