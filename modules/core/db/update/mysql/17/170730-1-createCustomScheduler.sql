create table ADM_CUSTOM_SCHEDULER (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    TICKET_ID varchar(32),
    CONTRACT_ID varchar(32),
    DATE_ date not null,
    --
    primary key (ID)
);
