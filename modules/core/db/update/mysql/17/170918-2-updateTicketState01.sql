alter table ADM_TICKET_STATE add column DEFAULT_STATUS boolean ^
update ADM_TICKET_STATE set DEFAULT_STATUS = false where DEFAULT_STATUS is null ;
alter table ADM_TICKET_STATE modify column DEFAULT_STATUS boolean not null ;
