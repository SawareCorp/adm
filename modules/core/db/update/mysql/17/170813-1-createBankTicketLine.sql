create table ADM_BANK_TICKET_LINE (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    BANK_ID varchar(32) not null,
    APPLICATION_DATE date not null,
    --
    primary key (ID)
);
