create table ADM_CASH_WARRANT (
    ID bigint,
    CREATE_TS datetime,
    CREATED_BY varchar(50),
    --
    AMOUNT bigint,
    CONTRACT_ID varchar(32) not null,
    --
    primary key (ID)
);
