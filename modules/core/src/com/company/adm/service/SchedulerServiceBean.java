package com.company.adm.service;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

@Service(SchedulerService.NAME)
public class SchedulerServiceBean implements SchedulerService {
    @Inject
    private Persistence persistence;

    @Override
    public List<Map<String, Object>> getScheduledTasks(User user, Date date) {
        try (Transaction tx = persistence.createTransaction()){
            List<Object> data = persistence.getEntityManager().createQuery("select " +
                    "e.status.name," +
                    "e.statusDateTime," +
                    "e.name," +
                    "e.phone " +
                    "from adm$Ticket e " +
                    "where " +
                    "cast(e.statusDateTime date) = cast(?1 date)" +
                    "and e.responsibleManager.id = ?2 " +
                    "and e.statusDateTime is not null")
                    .setParameter(1, date)
                    .setParameter(2, user)
                    .getResultList();
            List<Map<String, Object>> rows = new ArrayList<>();
            for (Object elem : data) {
                Object[] elemArr = (Object[]) elem;
                Map<String, Object> row = new HashMap<>();
                row.put("statusNameS", elemArr[0]);
                row.put("statusDate", elemArr[1]);
                row.put("name", elemArr[2]);
                row.put("phone", elemArr[3]);
                rows.add(row);
            }
            return rows;
        }
    }
}