update ADM_TICKET set NAME = '' where NAME is null ;
alter table ADM_TICKET modify column NAME longtext not null ;
