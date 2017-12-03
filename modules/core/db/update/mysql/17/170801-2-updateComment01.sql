alter table ADM_COMMENT add constraint FK_ADM_COMMENT_TICKET foreign key (TICKET_ID) references ADM_TICKET(ID);
create index IDX_ADM_COMMENT_TICKET on ADM_COMMENT (TICKET_ID);
