alter table ADM_TICKET add constraint FK_ADM_TICKET_SCHEDULER foreign key (SCHEDULER_ID) references ADM_CUSTOM_SCHEDULER(ID);
