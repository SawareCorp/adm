alter table ADM_TICKET add constraint FK_ADM_TICKET_INTEREST foreign key (INTEREST_ID) references ADM_INTEREST(ID);
create index IDX_ADM_TICKET_INTEREST on ADM_TICKET (INTEREST_ID);
