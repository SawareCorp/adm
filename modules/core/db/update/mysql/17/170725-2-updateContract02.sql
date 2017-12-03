-- update ADM_CONTRACT set CONTRACT_NUMBER = <default_value> where CONTRACT_NUMBER is null ;
alter table ADM_CONTRACT modify column CONTRACT_NUMBER bigint not null ;
