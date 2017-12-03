alter table ADM_ANALYTICS_BANK_LINK add constraint FK_AABL_ANALYTICS foreign key (ANALYTICS_ID) references ADM_ANALYTICS(ID);
alter table ADM_ANALYTICS_BANK_LINK add constraint FK_AABL_BANK foreign key (BANK_ID) references ADM_BANK(ID);
