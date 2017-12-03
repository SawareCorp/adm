alter table ADM_CONTRACT_SERVICE_LINE drop column COST cascade ;
alter table ADM_CONTRACT_SERVICE_LINE add column COST varchar(255) ;
