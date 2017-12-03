create table ADM_BANK (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime,
    UPDATED_BY varchar(50),
    CREATE_TS datetime,
    CREATED_BY varchar(50),
    --
    NAME varchar(255) not null,
    --
    primary key (ID)
);
