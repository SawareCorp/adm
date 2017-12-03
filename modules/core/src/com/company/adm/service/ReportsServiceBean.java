package com.company.adm.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

@Service(ReportsService.NAME)
public class ReportsServiceBean implements ReportsService {
    @Inject
    private Persistence persistence;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<Map<String, Object>> getCurrentLoans(UUID questionnaire){
        EntityManager em = persistence.getEntityManager();
        List<Map<String, Object>> result = new ArrayList<>();
        List list = em.createQuery("select " +
                "cl.organization," +
                "cl.creditSumm," +
                "cl.applicationDate," +
                "cl.dateOfLastPayment," +
                "cl.balanceOwed," +
                "cl.monthlyPayment" +
                " from adm$CurrentLoan cl where cl.questionnaire.id = ?1")
                .setParameter(1, questionnaire)
                .getResultList();
        for (Object o : list) {
            Object[] row = (Object[]) o;
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("organization", row[0]);
            rowMap.put("creditSumm", row[1]);
            rowMap.put("applicationDate", row[2]);
            rowMap.put("lastPayment", row[3]);
            rowMap.put("balance", row[4]);
            rowMap.put("monthlyPayment", row[5]);
            result.add(rowMap);
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<Map<String, Object>> getSourceOfIncomes(UUID questionnaire){
        EntityManager em = persistence.getEntityManager();
        List<Map<String, Object>> result = new ArrayList<>();
        List list = em.createQuery("select " +
                "soi.name," +
                "soi.experience," +
                "soi.position," +
                "soi.document," +
                "soi.urgency," +
                "soi.incomeAmount " +
                "from adm$SourceOfIncome soi " +
                "where soi.questionnaire.id = ?1")
                .setParameter(1, questionnaire)
                .getResultList();
        for (Object o : list) {
            Object[] row = (Object[]) o;
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("name", row[0]);
            rowMap.put("experience", row[1]);
            rowMap.put("position", row[2]);
            rowMap.put("document", row[3]);
            rowMap.put("urgency", row[4]);
            rowMap.put("incomeAmount", row[5]);
            result.add(rowMap);
        }
        return result;
    }
}