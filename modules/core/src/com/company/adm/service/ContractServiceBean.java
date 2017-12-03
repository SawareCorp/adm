package com.company.adm.service;

import com.company.adm.entity.contracts.Contract;
import com.company.adm.entity.contracts.Contractor;
import com.company.adm.entity.contracts.ServiceStatus;
import com.company.adm.entity.contracts.analytics.Analytics;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.FileDescriptor;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(ContractService.NAME)
public class ContractServiceBean implements ContractService {
    @Inject
    private Persistence persistence;
    @Inject
    private AdmConfig admConfig;

    @Override
    public Long getNextContractNumber() {
        Long maxNum = null;
        try(Transaction tx = persistence.createTransaction()){
            maxNum = (Long) persistence.getEntityManager()
                    .createNativeQuery("select max(contract_number) from adm.ADM_CONTRACT")
                    .getSingleResult();
        }finally {
            if(maxNum == null)
                maxNum = 0l;
            return maxNum + 1;
        }
    }

    public ServiceStatus getDefaultServiceStatus(){
        try(Transaction tx = persistence.createTransaction()){
            ServiceStatus status = persistence.getEntityManager()
                    .createQuery("select ss from adm$ServiceStatus ss where ss.serialNumber = ?1", ServiceStatus.class)
                    .setParameter(1, admConfig.getDefaultServiceStatusSerialNumber())
                    .getSingleResult();
            return status;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Contract> getContractsByContractor(Contractor contractor) {
        try(Transaction tx = persistence.createTransaction()){
            List<Contract> contracts = persistence.getEntityManager()
                    .createQuery("select cn from adm$Contract cn where cn.contractor.id = ?1", Contract.class)
                    .setParameter(1, contractor.getId())
                    .getResultList();
            return contracts;
        }
    }

    @Override
    public Contractor findContractorByName(String name){
        try(Transaction tx = persistence.createTransaction()){
            List<Contractor> contractors = persistence.getEntityManager().createQuery("select c from adm$Contractor c where c.name like ?1 and c.face = 1", Contractor.class)
                    .setParameter(1, String.format("(?i)%%%s%%", name))
                    .getResultList();
            if(contractors.size() == 1)
                return contractors.get(0);
        }
        return null;
    }

}