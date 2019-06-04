alter table ADM_COMMENT add constraint FK_ADM_COMMENT_ON_TICKET foreign key (TICKET_ID) references ADM_TICKET(ID) on delete CASCADE;
