alter table ADM_CUSTOM_SCHEDULER drop column DATE_ cascade ;
alter table ADM_CUSTOM_SCHEDULER add column DATE_ datetime(3) ^
update ADM_CUSTOM_SCHEDULER set DATE_ = current_timestamp where DATE_ is null ;
alter table ADM_CUSTOM_SCHEDULER modify column DATE_ datetime(3) not null ;
