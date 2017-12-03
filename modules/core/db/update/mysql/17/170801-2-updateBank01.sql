alter table ADM_BANK add constraint FK_ADM_BANK_ANALYTICS foreign key (ANALYTICS_ID) references ADM_ANALYTICS(ID);
create index IDX_ADM_BANK_ANALYTICS on ADM_BANK (ANALYTICS_ID);
