create table ADM_QUESTIONNAIRE (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DATE_BORN date not null,
    PHONE varchar(255) not null,
    REGISTRATION_ADDRESS varchar(255) not null,
    ACTUAL_ADDRESS varchar(255),
    SECOND_DOCUMENT varchar(255),
    PROPERTY_IN_OWN longtext,
    ARREARS varchar(255),
    DEPOSITS longtext,
    PARTICIPATION_IN_LEGAL_ENTITIES varchar(255),
    REGISTRATION_AS_IP varchar(255),
    PROSECUTIONS varchar(255),
    LITIGATIONS varchar(255),
    DEBT_OF_BEILIFFS varchar(255),
    UNDERAGE_CHILDREN varchar(255),
    CREDIT_AMOUNT bigint,
    CREDIT_TERM varchar(255),
    CREDIT_TYPE varchar(255),
    --
    primary key (ID)
);
