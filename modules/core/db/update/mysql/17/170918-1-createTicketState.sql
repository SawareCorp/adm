create table ADM_TICKET_STATE (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    NAME varchar(255) not null,
    POSITION_ integer not null,
    --
    primary key (ID)
);
