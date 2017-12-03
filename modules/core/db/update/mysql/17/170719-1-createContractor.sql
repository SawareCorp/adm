create table ADM_CONTRACTOR (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    CONTRACT_NUMBER bigint not null,
    NAME varchar(255) not null,
    REPRESENTATIVE varchar(255),
    DATE_BORN date,
    PERSONAL_DATA longtext not null,
    PHONE varchar(50) not null,
    --
    primary key (ID)
);
