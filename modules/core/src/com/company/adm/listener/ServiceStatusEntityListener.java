package com.company.adm.listener;

import org.springframework.stereotype.Component;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.cuba.core.EntityManager;
import com.company.adm.entity.contracts.ServiceStatus;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;

@Component("adm_ServiceStatusEntityListener")
public class ServiceStatusEntityListener implements BeforeInsertEntityListener<ServiceStatus>, BeforeUpdateEntityListener<ServiceStatus> {


    @Override
    public void onBeforeInsert(ServiceStatus entity, EntityManager entityManager) {

    }


    @Override
    public void onBeforeUpdate(ServiceStatus entity, EntityManager entityManager) {

    }



}