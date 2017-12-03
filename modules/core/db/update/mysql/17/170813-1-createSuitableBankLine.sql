create table ADM_SUITABLE_BANK_LINE (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    SUITABLE_BANK_ID varchar(32) not null,
    STATUS integer,
    ANALYTICS_ID varchar(32),
    --
    primary key (ID)
);
