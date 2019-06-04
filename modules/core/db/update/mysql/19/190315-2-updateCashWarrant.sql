update ADM_CASH_WARRANT set AMOUNT = 0 where AMOUNT is null ;
alter table ADM_CASH_WARRANT modify column AMOUNT bigint not null ;
