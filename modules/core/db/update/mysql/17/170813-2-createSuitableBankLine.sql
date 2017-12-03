alter table ADM_SUITABLE_BANK_LINE add constraint FK_ADM_SUITABLE_BANK_LINE_SUITABLE_BANK foreign key (SUITABLE_BANK_ID) references ADM_BANK(ID);
alter table ADM_SUITABLE_BANK_LINE add constraint FK_ADM_SUITABLE_BANK_LINE_ANALYTICS foreign key (ANALYTICS_ID) references ADM_ANALYTICS(ID);
create index IDX_ADM_SUITABLE_BANK_LINE_SUITABLE_BANK on ADM_SUITABLE_BANK_LINE (SUITABLE_BANK_ID);
create index IDX_ADM_SUITABLE_BANK_LINE_ANALYTICS on ADM_SUITABLE_BANK_LINE (ANALYTICS_ID);
