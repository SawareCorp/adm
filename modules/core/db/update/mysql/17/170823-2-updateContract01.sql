alter table ADM_CONTRACT add constraint FK_ADM_CONTRACT_TICKET foreign key (TICKET_ID) references ADM_TICKET(ID);
create index IDX_ADM_CONTRACT_TICKET on ADM_CONTRACT (TICKET_ID);
