create table ADM_CONTACT (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    PHONE varchar(255) not null,
    NAME varchar(255) not null,
    COMMENT_ varchar(1000),
    BANK_ID varchar(32),
    TICKET_ID varchar(32),
    --
    primary key (ID)
);
