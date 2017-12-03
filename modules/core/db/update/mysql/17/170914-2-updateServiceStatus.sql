-- update ADM_SERVICE_STATUS set SERIAL_NUMBER = <default_value> where SERIAL_NUMBER is null ;
alter table ADM_SERVICE_STATUS modify column SERIAL_NUMBER integer not null ;
