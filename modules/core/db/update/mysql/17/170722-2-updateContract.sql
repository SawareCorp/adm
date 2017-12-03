alter table ADM_CONTRACT add column CONTRACT_STATUS integer ^
update ADM_CONTRACT set CONTRACT_STATUS = 1 where CONTRACT_STATUS is null ;
alter table ADM_CONTRACT modify column CONTRACT_STATUS integer not null ;
alter table ADM_CONTRACT drop column TICKET_STATUS cascade ;
