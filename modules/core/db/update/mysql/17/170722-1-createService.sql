create table ADM_SERVICE (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    NAME varchar(1000) not null,
    DIRECTION integer not null,
    TAX integer not null,
    --
    primary key (ID)
);
