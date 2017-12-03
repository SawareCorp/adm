alter table ADM_CONTACT add constraint FK_ADM_CONTACT_TICKET foreign key (TICKET_ID) references ADM_TICKET(ID);
create index IDX_ADM_CONTACT_TICKET on ADM_CONTACT (TICKET_ID);
