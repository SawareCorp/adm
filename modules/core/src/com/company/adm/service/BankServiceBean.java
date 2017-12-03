package com.company.adm.service;

import com.company.adm.entity.Bank;
import com.company.adm.entity.contracts.analytics.SuitableBank;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(BankService.NAME)
public class BankServiceBean implements BankService {
    @Inject
    private Persistence persistence;

    @Override
    public List<SuitableBank> getSuitableBanks(Bank bank){
        try(Transaction tx = persistence.createTransaction()){
            List<SuitableBank> suitableBanks = persistence.getEntityManager().createQuery("select sb from adm$SuitableBank sb where sb.bank.id = ?1", SuitableBank.class)
                    .setParameter(1, bank)
                    .getResultList();
            return suitableBanks;
        }catch (Exception e) {
            return null;
        }
    }
}