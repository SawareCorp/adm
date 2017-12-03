-- alter table ADM_SERVICE_STATUS add column SERIAL_NUMBER integer ^
-- update ADM_SERVICE_STATUS set SERIAL_NUMBER = <default_value> ;
-- alter table ADM_SERVICE_STATUS modify column SERIAL_NUMBER integer not null ;
alter table ADM_SERVICE_STATUS add column SERIAL_NUMBER integer ;
