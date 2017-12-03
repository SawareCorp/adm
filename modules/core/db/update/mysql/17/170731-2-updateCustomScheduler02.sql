alter table ADM_CUSTOM_SCHEDULER add constraint FK_ADM_CUSTOM_SCHEDULER_TICKET foreign key (TICKET_ID) references ADM_TICKET(ID);
