package com.company.adm.listener;

import com.company.adm.entity.contracts.analytics.Analytics;
import org.springframework.stereotype.Component;
import com.haulmont.cuba.core.listener.BeforeDeleteEntityListener;
import com.haulmont.cuba.core.EntityManager;
import com.company.adm.entity.contracts.ContractServiceLine;

@Component("adm_ContractServiceLineEntityListener")
public class ContractServiceLineEntityListener implements BeforeDeleteEntityListener<ContractServiceLine> {


    @Override
    public void onBeforeDelete(ContractServiceLine entity, EntityManager entityManager) {

    }


}