alter table adm.ADM_CONTRACT add constraint FK_ADM_CONTRACT_SOURCE foreign key (SOURCE_ID) references ADM_SOURCE_DESCRIPTION(ID);
create index IDX_ADM_CONTRACT_SOURCE on adm.ADM_CONTRACT (SOURCE_ID);
