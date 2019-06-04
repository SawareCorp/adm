package com.company.adm.service;


import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ReportsService {
    String NAME = "adm_ReportsService";

    List<Map<String, Object>> getCurrentLoans(UUID questionnaire);
    List<Map<String, Object>> getSourceOfIncomes(UUID questionnaire);
    String getAmountWords(Long amount);
}