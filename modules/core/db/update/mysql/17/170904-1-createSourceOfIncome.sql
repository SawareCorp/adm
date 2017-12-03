create table ADM_SOURCE_OF_INCOME (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime,
    UPDATED_BY varchar(50),
    CREATE_TS datetime,
    CREATED_BY varchar(50),
    --
    NAME varchar(255) not null,
    EXPERIENCE varchar(255),
    POSITION_ varchar(255),
    DOCUMENT varchar(255),
    URGENCY varchar(255),
    INCOME_AMOUNT integer,
    QUESTIONNAIRE_ID varchar(32),
    --
    primary key (ID)
);
