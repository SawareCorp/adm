package com.company.adm.listener;

import com.company.adm.entity.contracts.Comment;
import com.company.adm.entity.contracts.ContractServiceLine;
import com.company.adm.entity.contracts.analytics.Analytics;
import com.company.adm.entity.scheduler.CustomScheduler;
import com.haulmont.cuba.core.entity.FileDescriptor;
import org.springframework.stereotype.Component;
import com.haulmont.cuba.core.listener.BeforeDeleteEntityListener;
import com.haulmont.cuba.core.EntityManager;
import com.company.adm.entity.contracts.Contract;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;

@Component("adm_ContractEntityListener")
public class ContractEntityListener implements BeforeDeleteEntityListener<Contract>, BeforeInsertEntityListener<Contract>, BeforeUpdateEntityListener<Contract> {


    @Override
    public void onBeforeDelete(Contract entity, EntityManager entityManager) {
        for (ContractServiceLine serviceLine : entity.getServices())
            entityManager.remove(serviceLine);
        for (Analytics analytics : entity.getAnalytics())
            entityManager.remove(analytics);
        if(entity.getSchedules() != null)
            for (CustomScheduler scheduler : entity.getSchedules())
                entityManager.remove(scheduler);
        if(entity.getHistory() != null)
            for (Comment comment : entity.getHistory())
                entityManager.remove(comment);
        if(entity.getInternalFiles() != null)
            for (FileDescriptor fileDescriptor : entity.getInternalFiles())
                entityManager.remove(fileDescriptor);
    }

    

    @Override
    public void onBeforeInsert(Contract entity, EntityManager entityManager) {

    }


    @Override
    public void onBeforeUpdate(Contract entity, EntityManager entityManager) {

    }


}