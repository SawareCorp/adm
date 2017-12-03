update ADM_ANALYTICS set NUMBER_ = 0 where NUMBER_ is null ;
alter table ADM_ANALYTICS modify column NUMBER_ integer not null ;
update ADM_ANALYTICS set SCORING_SCORE = 0 where SCORING_SCORE is null ;
alter table ADM_ANALYTICS modify column SCORING_SCORE integer not null ;
