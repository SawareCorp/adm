alter table ADM_CONTRACT add column CREATE_DATE date ^
update ADM_CONTRACT set CREATE_DATE = current_date where CREATE_DATE is null ;
alter table ADM_CONTRACT modify column CREATE_DATE date not null ;
