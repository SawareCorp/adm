alter table ADM_TICKET add constraint FK_ADM_TICKET_STATUS foreign key (STATUS_ID) references ADM_TICKET_STATE(ID);
create index IDX_ADM_TICKET_STATUS on ADM_TICKET (STATUS_ID);
