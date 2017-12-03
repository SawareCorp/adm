create table ADM_CONTRACT_SERVICE_LINE (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    COST integer not null,
    STATE_ID varchar(32) not null,
    CONTRACT_ID varchar(32),
    --
    primary key (ID)
);
