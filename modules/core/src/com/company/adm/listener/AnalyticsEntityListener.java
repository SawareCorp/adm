package com.company.adm.listener;

import com.company.adm.entity.contracts.analytics.BankTicketLine;
import com.company.adm.entity.contracts.analytics.SuitableBank;
import com.haulmont.cuba.core.Persistence;
import org.springframework.stereotype.Component;
import com.haulmont.cuba.core.listener.BeforeDeleteEntityListener;
import com.haulmont.cuba.core.EntityManager;
import com.company.adm.entity.contracts.analytics.Analytics;

import javax.inject.Inject;
import java.util.List;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;

@Component("adm_AnalyticsEntityListener")
public class AnalyticsEntityListener implements BeforeDeleteEntityListener<Analytics>, BeforeInsertEntityListener<Analytics>, BeforeUpdateEntityListener<Analytics> {

    @Override
    public void onBeforeDelete(Analytics entity, EntityManager entityManager) {
        for (SuitableBank bank : entity.getSuitableBanks())
            entityManager.remove(bank);

        for (BankTicketLine ticketLine : entity.getTicketToBanks())
            entityManager.remove(ticketLine);
    }


    @Override
    public void onBeforeInsert(Analytics entity, EntityManager entityManager) {

    }


    @Override
    public void onBeforeUpdate(Analytics entity, EntityManager entityManager) {

    }


}