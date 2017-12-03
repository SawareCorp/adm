create table ADM_ANALYTICS (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime,
    UPDATED_BY varchar(50),
    CREATE_TS datetime,
    CREATED_BY varchar(50),
    --
    NUMBER_ integer not null,
    DATE_CREATION date not null,
    SCORING_SCORE integer,
    --
    primary key (ID)
);
