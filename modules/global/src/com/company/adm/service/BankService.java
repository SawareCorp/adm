package com.company.adm.service;


import com.company.adm.entity.Bank;
import com.company.adm.entity.contracts.analytics.SuitableBank;

import java.util.List;

public interface BankService {
    String NAME = "adm_BankService";

    List<SuitableBank> getSuitableBanks(Bank bank);
}