create table ADM_CURRENT_LOAN (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    ORGANIZATION varchar(255) not null,
    CREDIT_SUMM decimal(19, 2),
    APPLICATION_DATE date,
    DATE_OF_LAST_PAYMENT date,
    BALANCE_OWED decimal(19, 2),
    MONTHLY_PAYMENT bigint,
    QUESTIONNAIRE_ID varchar(32),
    --
    primary key (ID)
);
