create table ADM_COMMENT (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime,
    UPDATED_BY varchar(50),
    CREATE_TS datetime,
    CREATED_BY varchar(50),
    --
    USER_ID varchar(32) not null,
    COMMENT_ varchar(1000) not null,
    DATE_TIME datetime(3) not null,
    CONTRACT_ID varchar(32),
    --
    primary key (ID)
);
