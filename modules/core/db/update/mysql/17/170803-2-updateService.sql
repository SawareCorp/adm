alter table ADM_SERVICE drop column DEFAULT_TAX cascade ;
alter table ADM_SERVICE add column DEFAULT_TAX varchar(255) ^
update ADM_SERVICE set DEFAULT_TAX = '' where DEFAULT_TAX is null ;
alter table ADM_SERVICE modify column DEFAULT_TAX varchar(255) not null ;
