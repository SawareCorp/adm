alter table ADM_COMMENT add constraint FK_ADM_COMMENT_USER foreign key (USER_ID) references SEC_USER(ID);
alter table ADM_COMMENT add constraint FK_ADM_COMMENT_CONTRACT foreign key (CONTRACT_ID) references ADM_CONTRACT(ID);
create index IDX_ADM_COMMENT_USER on ADM_COMMENT (USER_ID);
create index IDX_ADM_COMMENT_CONTRACT on ADM_COMMENT (CONTRACT_ID);
