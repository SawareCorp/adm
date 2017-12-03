alter table ADM_TICKET add constraint FK_ADM_TICKET_SOURCE_BANK foreign key (SOURCE_BANK_ID) references ADM_BANK(ID);
create index IDX_ADM_TICKET_SOURCE_BANK on ADM_TICKET (SOURCE_BANK_ID);
