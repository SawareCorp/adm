update ADM_CUSTOM_SCHEDULER set WORKED_OUT = false where WORKED_OUT is null ;
alter table ADM_CUSTOM_SCHEDULER modify column WORKED_OUT boolean not null ;
