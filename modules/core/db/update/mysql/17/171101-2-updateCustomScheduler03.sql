alter table ADM_CUSTOM_SCHEDULER add constraint FK_ADM_CUSTOM_SCHEDULER_CONTRACT foreign key (CONTRACT_ID) references ADM_CONTRACT(ID);
create index IDX_ADM_CUSTOM_SCHEDULER_CONTRACT on ADM_CUSTOM_SCHEDULER (CONTRACT_ID);
