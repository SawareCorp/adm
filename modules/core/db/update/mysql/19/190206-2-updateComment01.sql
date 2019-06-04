alter table ADM_COMMENT add constraint FK_ADM_COMMENT_ON_CONTRACT foreign key (CONTRACT_ID) references ADM_CONTRACT(ID) on delete CASCADE;
