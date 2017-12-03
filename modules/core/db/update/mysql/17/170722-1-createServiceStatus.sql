create table ADM_SERVICE_STATUS (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    NAME varchar(255) not null,
    IS_CREDITING boolean,
    IS_BOOKKEEPING boolean,
    IS_JURIDICAL boolean,
    --
    primary key (ID)
);
