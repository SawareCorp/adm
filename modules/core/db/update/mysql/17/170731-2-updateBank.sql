alter table ADM_BANK add column CODE varchar(255) ^
update ADM_BANK set CODE = '' where CODE is null ;
alter table ADM_BANK modify column CODE varchar(255) not null ;
