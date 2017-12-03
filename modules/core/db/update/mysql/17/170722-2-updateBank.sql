alter table ADM_BANK add column DOCUMENT_LIST varchar(1000) ^
update ADM_BANK set DOCUMENT_LIST = '' where DOCUMENT_LIST is null ;
alter table ADM_BANK modify column DOCUMENT_LIST varchar(1000) not null ;
