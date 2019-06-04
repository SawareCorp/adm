alter table ADM_CASH_WARRANT add column AMOUNT_WORDS varchar(255) ^
update ADM_CASH_WARRANT set AMOUNT_WORDS = '' where AMOUNT_WORDS is null ;
alter table ADM_CASH_WARRANT modify column AMOUNT_WORDS varchar(255) not null ;
