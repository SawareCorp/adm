create table ADM_SUITABLE_BANK_STATE (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime,
    UPDATED_BY varchar(50),
    CREATE_TS datetime,
    CREATED_BY varchar(50),
    --
    NAME varchar(255) not null,
    POSITION_ integer not null,
    --
    primary key (ID)
);
