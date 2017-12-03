alter table ADM_TICKET_STATE add column CLOSED boolean ^
update ADM_TICKET_STATE set CLOSED = false where CLOSED is null ;
alter table ADM_TICKET_STATE modify column CLOSED boolean not null ;
