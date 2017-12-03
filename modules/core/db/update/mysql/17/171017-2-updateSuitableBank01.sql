alter table adm.ADM_SUITABLE_BANK add constraint FK_ADM_SUITABLE_BANK_STATE foreign key (STATE_ID) references adm.ADM_SUITABLE_BANK_STATE(ID);

create index IDX_ADM_SUITABLE_BANK_STATE on adm.ADM_SUITABLE_BANK (STATE_ID);
