alter table ADM_CONTACT add constraint FK_ADM_CONTACT_BANK foreign key (BANK_ID) references ADM_BANK(ID);
create index IDX_ADM_CONTACT_BANK on ADM_CONTACT (BANK_ID);
