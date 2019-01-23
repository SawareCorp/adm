-- begin ADM_CONTACT
create table ADM_CONTACT (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    PHONE varchar(255) not null,
    NAME varchar(255) not null,
    COMMENT_ varchar(1000),
    BANK_ID varchar(32),
    TICKET_ID varchar(32),
    CONTRACTOR_ID varchar(32),
    --
    primary key (ID)
)^
-- end ADM_CONTACT
-- begin ADM_BANK
create table ADM_BANK (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    NAME varchar(255) not null,
    ADDRESS varchar(255),
    --
    primary key (ID)
)^
-- end ADM_BANK
-- begin ADM_TICKET
create table ADM_TICKET (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    NAME longtext not null,
    PHONE varchar(20) not null,
    INTEREST_ID varchar(32),
    SOURCE integer not null,
    SOURCE_DESCRIPTION_ID varchar(32) not null,
    AMOUNT varchar(255),
    STATUS_ID varchar(32) not null,
    STATUS_DATE_TIME datetime(3),
    LAST_CALL date,
    LAST_MEETING date,
    RESPONSIBLE_MANAGER_ID varchar(32) not null,
    CHARACTERISTIC varchar(1000),
    FACE integer not null,
    E_MAIL varchar(255),
    QUESTIONNAIRE_ID varchar(32),
    --
    primary key (ID)
)^
-- end ADM_TICKET
-- begin ADM_INTEREST
create table ADM_INTEREST (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime,
    UPDATED_BY varchar(50),
    CREATE_TS datetime,
    CREATED_BY varchar(50),
    --
    NAME varchar(255) not null,
    --
    primary key (ID)
)^
-- end ADM_INTEREST
-- begin ADM_CONTRACTOR
create table ADM_CONTRACTOR (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    NAME varchar(255) not null,
    REPRESENTATIVE varchar(255),
    DATE_BORN date,
    PHONE varchar(50),
    FACE integer not null,
    COMMENT_ varchar(1000),
    PASSPORT varchar(20),
    ISSUING_AUTHORITY varchar(255),
    ISSUE_DATE date,
    DIVISION_CODE varchar(10),
    SNAILS varchar(255),
    ADDRESS varchar(255),
    TIN varchar(20),
    KPP varchar(20),
    OGRN varchar(255),
    JURIDICAL_ADDRESS varchar(255),
    POST_ADDRESS varchar(255),
    EMAIL varchar(255),
    --
    primary key (ID)
)^
-- end ADM_CONTRACTOR
-- begin ADM_CONTRACT
create table ADM_CONTRACT (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    TICKET_ID varchar(32),
    SIGNAL_ID bigint,
    CONTRACT_NUMBER bigint not null,
    CONTRACTOR_ID varchar(32) not null,
    CONTRACT_STATUS integer not null,
    DIRECTION varchar(50) not null,
    COMPENSATION integer,
    AMOUNT bigint,
    DESIRED_AMOUNT varchar(255),
    CREATE_DATE date not null,
    RESPONSIBLE_MANAGER_ID varchar(32) not null,
    QUESTIONNAIRE_ID varchar(32),
    SOURCE_ID varchar(32),
    CONTRACT_EVENT integer,
    EVENT_TIME datetime(3),
    --
    primary key (ID)
)^
-- end ADM_CONTRACT
-- begin ADM_SERVICE
create table ADM_SERVICE (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    NAME varchar(1000) not null,
    DIRECTION varchar(50) not null,
    DEFAULT_TAX varchar(255) not null,
    --
    primary key (ID)
)^
-- end ADM_SERVICE
-- begin ADM_SERVICE_STATUS
create table ADM_SERVICE_STATUS (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    NAME varchar(255) not null,
    IS_CREDITING boolean,
    IS_BOOKKEEPING boolean,
    IS_JURIDICAL boolean,
    DIRECTIONS_LIST varchar(255),
    SERIAL_NUMBER integer not null,
    --
    primary key (ID)
)^
-- end ADM_SERVICE_STATUS
-- begin ADM_CONTRACT_SERVICE_LINE
create table ADM_CONTRACT_SERVICE_LINE (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    SERVICE_ID varchar(32),
    COST varchar(255),
    STATE_ID varchar(32) not null,
    CONTRACT_ID varchar(32),
    --
    primary key (ID)
)^
-- end ADM_CONTRACT_SERVICE_LINE
-- begin ADM_COMMENT
create table ADM_COMMENT (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    USER_ID varchar(32) not null,
    COMMENT_ varchar(1000) not null,
    DATE_TIME datetime(3) not null,
    CONTRACT_ID varchar(32),
    TICKET_ID varchar(32),
    --
    primary key (ID)
)^
-- end ADM_COMMENT
-- begin ADM_SUITABLE_BANK
create table ADM_SUITABLE_BANK (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    BANK_ID varchar(32) not null,
    MAX_AMOUNT bigint,
    STATE_ID varchar(32) not null,
    ANALYTICS_ID varchar(32),
    COMMENT_ varchar(255),
    --
    primary key (ID)
)^
-- end ADM_SUITABLE_BANK
-- begin ADM_DOCUMENT
create table ADM_DOCUMENT (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime,
    UPDATED_BY varchar(50),
    CREATE_TS datetime,
    CREATED_BY varchar(50),
    --
    NAME varchar(255) not null,
    --
    primary key (ID)
)^
-- end ADM_DOCUMENT
-- begin ADM_ANALYTICS
create table ADM_ANALYTICS (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    NUMBER_ integer not null,
    DATE_CREATION date not null,
    SCORING_SCORE integer,
    SECURITY_CHECK varchar(1500),
    ARREARS varchar(255),
    MONTHLY_PAYMENTS bigint,
    CURRENT_CREDITS bigint,
    CONTRACT_ID varchar(32),
    COMMENT_ varchar(255),
    --
    primary key (ID)
)^
-- end ADM_ANALYTICS
-- begin ADM_SUITABLE_BANK_DOCUMENT_LINK
create table ADM_SUITABLE_BANK_DOCUMENT_LINK (
    DOCUMENT_ID varchar(32),
    SUITABLE_BANK_ID varchar(32),
    primary key (DOCUMENT_ID, SUITABLE_BANK_ID)
)^
-- end ADM_SUITABLE_BANK_DOCUMENT_LINK
-- begin ADM_CUSTOM_SCHEDULER
create table ADM_CUSTOM_SCHEDULER (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    TICKET_ID varchar(32),
    DATE_ datetime(3) not null,
    WORKED_OUT boolean not null,
    COMMENT_ varchar(255),
    CONTRACT_ID varchar(32),
    --
    primary key (ID)
)^
-- end ADM_CUSTOM_SCHEDULER
-- begin ADM_SOURCE_DESCRIPTION
create table ADM_SOURCE_DESCRIPTION (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    NAME varchar(255) not null,
    --
    primary key (ID)
)^
-- end ADM_SOURCE_DESCRIPTION

-- begin ADM_BANK_TICKET_LINE
create table ADM_BANK_TICKET_LINE (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    BANK_ID varchar(32) not null,
    APPLICATION_DATE date not null,
    COMMENT_ varchar(1000),
    ANALYTICS_ID varchar(32),
    QUESTIONNAIRE_ID varchar(32),
    --
    primary key (ID)
)^
-- end ADM_BANK_TICKET_LINE
-- begin ADM_CONTRACTOR_FILE_DESCRIPTOR_LINK
create table ADM_CONTRACTOR_FILE_DESCRIPTOR_LINK (
    CONTRACTOR_ID varchar(32),
    FILE_DESCRIPTOR_ID varchar(32),
    primary key (CONTRACTOR_ID, FILE_DESCRIPTOR_ID)
)^
-- end ADM_CONTRACTOR_FILE_DESCRIPTOR_LINK
-- begin ADM_QUESTIONNAIRE
create table ADM_QUESTIONNAIRE (
    ID varchar(32),
    VERSION integer not null,
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DATE_BORN date,
    PHONE varchar(255) not null,
    REGISTRATION_ADDRESS varchar(255),
    ACTUAL_ADDRESS varchar(255),
    SECOND_DOCUMENT varchar(255),
    PROPERTY_IN_OWN longtext,
    ARREARS varchar(255),
    PARTICIPATION_IN_LEGAL_ENTITIES varchar(255),
    REGISTRATION_AS_IP varchar(255),
    PROSECUTIONS varchar(255),
    LITIGATIONS varchar(255),
    DEBT_OF_BEILIFFS varchar(255),
    UNDERAGE_CHILDREN varchar(255),
    CREDIT_AMOUNT varchar(255),
    CREDIT_TERM varchar(255),
    GUARANTORS varchar(255),
    --
    primary key (ID)
)^
-- end ADM_QUESTIONNAIRE
-- begin ADM_CURRENT_LOAN
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
    ANALYTICS_ID varchar(32),
    --
    primary key (ID)
)^
-- end ADM_CURRENT_LOAN
-- begin ADM_SOURCE_OF_INCOME
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
)^
-- end ADM_SOURCE_OF_INCOME
-- begin ADM_CONTRACT_FILE_DESCRIPTOR_LINK
create table ADM_CONTRACT_FILE_DESCRIPTOR_LINK (
    CONTRACT_ID varchar(32),
    FILE_DESCRIPTOR_ID varchar(32),
    primary key (CONTRACT_ID, FILE_DESCRIPTOR_ID)
)^
-- end ADM_CONTRACT_FILE_DESCRIPTOR_LINK
-- begin ADM_TICKET_STATE
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
    HAS_DATE_TIME boolean not null,
    DEFAULT_STATUS boolean not null,
    CLOSED boolean not null,
    --
    primary key (ID)
)^
-- end ADM_TICKET_STATE
-- begin ADM_SUITABLE_BANK_STATE
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
)^
-- end ADM_SUITABLE_BANK_STATE
-- begin ADM_SMS_TEMPLATE
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
)^
-- end ADM_SMS_TEMPLATE
