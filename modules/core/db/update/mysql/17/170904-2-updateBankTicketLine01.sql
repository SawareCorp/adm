alter table ADM_BANK_TICKET_LINE add constraint FK_ADM_BANK_TICKET_LINE_QUESTIONNAIRE foreign key (QUESTIONNAIRE_ID) references ADM_QUESTIONNAIRE(ID);
create index IDX_ADM_BANK_TICKET_LINE_QUESTIONNAIRE on ADM_BANK_TICKET_LINE (QUESTIONNAIRE_ID);
