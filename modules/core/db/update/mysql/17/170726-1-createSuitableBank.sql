create table ADM_SUITABLE_BANK (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    BANK_ID varchar(32) not null,
    MAX_AMOUNT bigint,
    ANALYTICS_ID varchar(32),
    --
    primary key (ID)
);
