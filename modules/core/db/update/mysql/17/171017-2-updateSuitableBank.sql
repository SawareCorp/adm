alter table adm.ADM_SUITABLE_BANK add column STATE_ID varchar(32) ^

insert into adm.ADM_SUITABLE_BANK_STATE(ID, VERSION, NAME, POSITION_) values (newid(), 1, 'ПОМЕНЯТЬ!', 0) ;

update adm.ADM_SUITABLE_BANK set STATE_ID = (select ID from adm.ADM_SUITABLE_BANK_STATE LIMIT 1) ;

alter table adm.ADM_SUITABLE_BANK modify column STATE_ID varchar(32) not null ;
