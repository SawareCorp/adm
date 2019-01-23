-- begin ADM_CONTACT
alter table ADM_CONTACT add constraint FK_ADM_CONTACT_BANK foreign key (BANK_ID) references ADM_BANK(ID)^
alter table ADM_CONTACT add constraint FK_ADM_CONTACT_TICKET foreign key (TICKET_ID) references ADM_TICKET(ID)^
alter table ADM_CONTACT add constraint FK_ADM_CONTACT_CONTRACTOR foreign key (CONTRACTOR_ID) references ADM_CONTRACTOR(ID)^
create index IDX_ADM_CONTACT_BANK on ADM_CONTACT (BANK_ID)^
create index IDX_ADM_CONTACT_TICKET on ADM_CONTACT (TICKET_ID)^
create index IDX_ADM_CONTACT_CONTRACTOR on ADM_CONTACT (CONTRACTOR_ID)^
-- end ADM_CONTACT
-- begin ADM_TICKET
alter table ADM_TICKET add constraint FK_ADM_TICKET_INTEREST foreign key (INTEREST_ID) references ADM_INTEREST(ID)^
alter table ADM_TICKET add constraint FK_ADM_TICKET_SOURCE_DESCRIPTION foreign key (SOURCE_DESCRIPTION_ID) references ADM_SOURCE_DESCRIPTION(ID)^
alter table ADM_TICKET add constraint FK_ADM_TICKET_STATUS foreign key (STATUS_ID) references ADM_TICKET_STATE(ID)^
alter table ADM_TICKET add constraint FK_ADM_TICKET_RESPONSIBLE_MANAGER foreign key (RESPONSIBLE_MANAGER_ID) references SEC_USER(ID)^
alter table ADM_TICKET add constraint FK_ADM_TICKET_QUESTIONNAIRE foreign key (QUESTIONNAIRE_ID) references ADM_QUESTIONNAIRE(ID)^
create index IDX_ADM_TICKET_INTEREST on ADM_TICKET (INTEREST_ID)^
create index IDX_ADM_TICKET_SOURCE_DESCRIPTION on ADM_TICKET (SOURCE_DESCRIPTION_ID)^
create index IDX_ADM_TICKET_STATUS on ADM_TICKET (STATUS_ID)^
create index IDX_ADM_TICKET_RESPONSIBLE_MANAGER on ADM_TICKET (RESPONSIBLE_MANAGER_ID)^
-- end ADM_TICKET
-- begin ADM_CONTRACTOR
create unique index IDX_ADM_CONTRACTOR_ENT_UNQ on ADM_CONTRACTOR (NAME, TIN) ^
create unique index IDX_ADM_CONTRACTOR_IND_UNQ on ADM_CONTRACTOR (NAME, DATE_BORN) ^
-- end ADM_CONTRACTOR
-- begin ADM_CONTRACT
alter table ADM_CONTRACT add constraint FK_ADM_CONTRACT_ON_TICKET foreign key (TICKET_ID) references ADM_TICKET(ID)^
alter table ADM_CONTRACT add constraint FK_ADM_CONTRACT_ON_CONTRACTOR foreign key (CONTRACTOR_ID) references ADM_CONTRACTOR(ID)^
alter table ADM_CONTRACT add constraint FK_ADM_CONTRACT_ON_RESPONSIBLE_MANAGER foreign key (RESPONSIBLE_MANAGER_ID) references SEC_USER(ID)^
alter table ADM_CONTRACT add constraint FK_ADM_CONTRACT_ON_QUESTIONNAIRE foreign key (QUESTIONNAIRE_ID) references ADM_QUESTIONNAIRE(ID)^
alter table ADM_CONTRACT add constraint FK_ADM_CONTRACT_ON_SOURCE foreign key (SOURCE_ID) references ADM_SOURCE_DESCRIPTION(ID)^
create unique index IDX_ADM_CONTRACT_UNIQ_CONTRACT_NUMBER on ADM_CONTRACT (CONTRACT_NUMBER) ^
create index IDX_ADM_CONTRACT_ON_TICKET on ADM_CONTRACT (TICKET_ID)^
create index IDX_ADM_CONTRACT_ON_CONTRACTOR on ADM_CONTRACT (CONTRACTOR_ID)^
create index IDX_ADM_CONTRACT_ON_RESPONSIBLE_MANAGER on ADM_CONTRACT (RESPONSIBLE_MANAGER_ID)^
create index IDX_ADM_CONTRACT_ON_QUESTIONNAIRE on ADM_CONTRACT (QUESTIONNAIRE_ID)^
create index IDX_ADM_CONTRACT_ON_SOURCE on ADM_CONTRACT (SOURCE_ID)^
-- end ADM_CONTRACT
-- begin ADM_CONTRACT_SERVICE_LINE
alter table ADM_CONTRACT_SERVICE_LINE add constraint FK_ADM_CONTRACT_SERVICE_LINE_SERVICE foreign key (SERVICE_ID) references ADM_SERVICE(ID)^
alter table ADM_CONTRACT_SERVICE_LINE add constraint FK_ADM_CONTRACT_SERVICE_LINE_STATE foreign key (STATE_ID) references ADM_SERVICE_STATUS(ID)^
alter table ADM_CONTRACT_SERVICE_LINE add constraint FK_ADM_CONTRACT_SERVICE_LINE_CONTRACT foreign key (CONTRACT_ID) references ADM_CONTRACT(ID)^
create index IDX_ADM_CONTRACT_SERVICE_LINE_SERVICE on ADM_CONTRACT_SERVICE_LINE (SERVICE_ID)^
create index IDX_ADM_CONTRACT_SERVICE_LINE_STATE on ADM_CONTRACT_SERVICE_LINE (STATE_ID)^
create index IDX_ADM_CONTRACT_SERVICE_LINE_CONTRACT on ADM_CONTRACT_SERVICE_LINE (CONTRACT_ID)^
-- end ADM_CONTRACT_SERVICE_LINE

-- begin ADM_COMMENT
alter table ADM_COMMENT add constraint FK_ADM_COMMENT_USER foreign key (USER_ID) references SEC_USER(ID)^
alter table ADM_COMMENT add constraint FK_ADM_COMMENT_CONTRACT foreign key (CONTRACT_ID) references ADM_CONTRACT(ID)^
alter table ADM_COMMENT add constraint FK_ADM_COMMENT_TICKET foreign key (TICKET_ID) references ADM_TICKET(ID)^
create index IDX_ADM_COMMENT_USER on ADM_COMMENT (USER_ID)^
create index IDX_ADM_COMMENT_CONTRACT on ADM_COMMENT (CONTRACT_ID)^
create index IDX_ADM_COMMENT_TICKET on ADM_COMMENT (TICKET_ID)^
-- end ADM_COMMENT
-- begin ADM_SUITABLE_BANK
alter table ADM_SUITABLE_BANK add constraint FK_ADM_SUITABLE_BANK_BANK foreign key (BANK_ID) references ADM_BANK(ID)^
alter table ADM_SUITABLE_BANK add constraint FK_ADM_SUITABLE_BANK_STATE foreign key (STATE_ID) references ADM_SUITABLE_BANK_STATE(ID)^
alter table ADM_SUITABLE_BANK add constraint FK_ADM_SUITABLE_BANK_ANALYTICS foreign key (ANALYTICS_ID) references ADM_ANALYTICS(ID)^
create index IDX_ADM_SUITABLE_BANK_BANK on ADM_SUITABLE_BANK (BANK_ID)^
create index IDX_ADM_SUITABLE_BANK_STATE on ADM_SUITABLE_BANK (STATE_ID)^
create index IDX_ADM_SUITABLE_BANK_ANALYTICS on ADM_SUITABLE_BANK (ANALYTICS_ID)^
-- end ADM_SUITABLE_BANK
-- begin ADM_SUITABLE_BANK_DOCUMENT_LINK
alter table ADM_SUITABLE_BANK_DOCUMENT_LINK add constraint FK_ASBDL_DOCUMENT foreign key (DOCUMENT_ID) references ADM_DOCUMENT(ID)^
alter table ADM_SUITABLE_BANK_DOCUMENT_LINK add constraint FK_ASBDL_SUITABLE_BANK foreign key (SUITABLE_BANK_ID) references ADM_SUITABLE_BANK(ID)^
-- end ADM_SUITABLE_BANK_DOCUMENT_LINK
-- begin ADM_SERVICE_STATUS
create unique index IDX_ADM_SERVICE_STATUS_UNIQ_SERIAL_NUMBER on ADM_SERVICE_STATUS (SERIAL_NUMBER) ^
-- end ADM_SERVICE_STATUS
-- begin ADM_CUSTOM_SCHEDULER
alter table ADM_CUSTOM_SCHEDULER add constraint FK_ADM_CUSTOM_SCHEDULER_TICKET foreign key (TICKET_ID) references ADM_TICKET(ID)^
alter table ADM_CUSTOM_SCHEDULER add constraint FK_ADM_CUSTOM_SCHEDULER_CONTRACT foreign key (CONTRACT_ID) references ADM_CONTRACT(ID)^
create index IDX_ADM_CUSTOM_SCHEDULER_TICKET on ADM_CUSTOM_SCHEDULER (TICKET_ID)^
create index IDX_ADM_CUSTOM_SCHEDULER_CONTRACT on ADM_CUSTOM_SCHEDULER (CONTRACT_ID)^
-- end ADM_CUSTOM_SCHEDULER

-- begin ADM_ANALYTICS
alter table ADM_ANALYTICS add constraint FK_ADM_ANALYTICS_CONTRACT foreign key (CONTRACT_ID) references ADM_CONTRACT(ID)^
create index IDX_ADM_ANALYTICS_CONTRACT on ADM_ANALYTICS (CONTRACT_ID)^
-- end ADM_ANALYTICS

-- begin ADM_BANK_TICKET_LINE
alter table ADM_BANK_TICKET_LINE add constraint FK_ADM_BANK_TICKET_LINE_BANK foreign key (BANK_ID) references ADM_BANK(ID)^
alter table ADM_BANK_TICKET_LINE add constraint FK_ADM_BANK_TICKET_LINE_ANALYTICS foreign key (ANALYTICS_ID) references ADM_ANALYTICS(ID)^
alter table ADM_BANK_TICKET_LINE add constraint FK_ADM_BANK_TICKET_LINE_QUESTIONNAIRE foreign key (QUESTIONNAIRE_ID) references ADM_QUESTIONNAIRE(ID)^
create index IDX_ADM_BANK_TICKET_LINE_BANK on ADM_BANK_TICKET_LINE (BANK_ID)^
create index IDX_ADM_BANK_TICKET_LINE_ANALYTICS on ADM_BANK_TICKET_LINE (ANALYTICS_ID)^
create index IDX_ADM_BANK_TICKET_LINE_QUESTIONNAIRE on ADM_BANK_TICKET_LINE (QUESTIONNAIRE_ID)^
-- end ADM_BANK_TICKET_LINE
-- begin ADM_CONTRACTOR_FILE_DESCRIPTOR_LINK
alter table ADM_CONTRACTOR_FILE_DESCRIPTOR_LINK add constraint FK_ACFDL_CONTRACTOR foreign key (CONTRACTOR_ID) references ADM_CONTRACTOR(ID)^
alter table ADM_CONTRACTOR_FILE_DESCRIPTOR_LINK add constraint FK_ACFDL_FILE_DESCRIPTOR foreign key (FILE_DESCRIPTOR_ID) references SYS_FILE(ID)^
-- end ADM_CONTRACTOR_FILE_DESCRIPTOR_LINK
-- begin ADM_CURRENT_LOAN
alter table ADM_CURRENT_LOAN add constraint FK_ADM_CURRENT_LOAN_QUESTIONNAIRE foreign key (QUESTIONNAIRE_ID) references ADM_QUESTIONNAIRE(ID)^
alter table ADM_CURRENT_LOAN add constraint FK_ADM_CURRENT_LOAN_ANALYTICS foreign key (ANALYTICS_ID) references ADM_ANALYTICS(ID)^
create index IDX_ADM_CURRENT_LOAN_QUESTIONNAIRE on ADM_CURRENT_LOAN (QUESTIONNAIRE_ID)^
create index IDX_ADM_CURRENT_LOAN_ANALYTICS on ADM_CURRENT_LOAN (ANALYTICS_ID)^
-- end ADM_CURRENT_LOAN
-- begin ADM_SOURCE_OF_INCOME
alter table ADM_SOURCE_OF_INCOME add constraint FK_ADM_SOURCE_OF_INCOME_QUESTIONNAIRE foreign key (QUESTIONNAIRE_ID) references ADM_QUESTIONNAIRE(ID)^
create index IDX_ADM_SOURCE_OF_INCOME_QUESTIONNAIRE on ADM_SOURCE_OF_INCOME (QUESTIONNAIRE_ID)^
-- end ADM_SOURCE_OF_INCOME
-- begin ADM_CONTRACT_FILE_DESCRIPTOR_LINK
alter table ADM_CONTRACT_FILE_DESCRIPTOR_LINK add constraint FK_ACTFDL_CONTRACT foreign key (CONTRACT_ID) references ADM_CONTRACT(ID)^
alter table ADM_CONTRACT_FILE_DESCRIPTOR_LINK add constraint FK_ACTFDL_FILE_DESCRIPTOR foreign key (FILE_DESCRIPTOR_ID) references SYS_FILE(ID)^
-- end ADM_CONTRACT_FILE_DESCRIPTOR_LINK
-- begin ADM_TICKET_STATE
create unique index IDX_ADM_TICKET_STATE_UNIQ_POSITION_ on ADM_TICKET_STATE (POSITION_) ^
create unique index IDX_ADM_TICKET_STATE_UNIQ_NAME on ADM_TICKET_STATE (NAME) ^
create unique index IDX_ADM_TICKET_STATE_UNQ_NAME on ADM_TICKET_STATE (NAME) ^
create unique index IDX_ADM_TICKET_STATE_UNQ_POSITION on ADM_TICKET_STATE (POSITION_) ^
-- end ADM_TICKET_STATE
-- begin ADM_SUITABLE_BANK_STATE
create unique index IDX_ADM_SUITABLE_BANK_STATE_UNIQ_POSITION_ on ADM_SUITABLE_BANK_STATE (POSITION_) ^
create unique index IDX_ADM_SUITABLE_BANK_STATE_UNIQ_NAME on ADM_SUITABLE_BANK_STATE (NAME) ^
-- end ADM_SUITABLE_BANK_STATE
