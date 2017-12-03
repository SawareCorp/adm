package com.company.adm.service;


import com.haulmont.cuba.security.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SchedulerService {
    String NAME = "adm_SchedulerService";

    List<Map<String, Object>> getScheduledTasks(User user, Date date);
}