create table ADM_SMS_TEMPLATE (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime,
    UPDATED_BY varchar(50),
    CREATE_TS datetime,
    CREATED_BY varchar(50),
    --
    NAME varchar(255) not null,
    TEMPLATE_TEXT varchar(255) not null,
    --
    primary key (ID)
);
