create table ADM_CONTRACT (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    CONTRACT_NUMBER bigint,
    CONTRACTOR_ID varchar(32) not null,
    TICKET_STATUS integer not null,
    DIRECTIOIN integer not null,
    AMOUNT bigint,
    --
    primary key (ID)
);
