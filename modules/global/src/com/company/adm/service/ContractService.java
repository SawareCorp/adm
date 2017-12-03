package com.company.adm.service;


import com.company.adm.entity.contracts.Contract;
import com.company.adm.entity.contracts.Contractor;
import com.company.adm.entity.contracts.ServiceStatus;

import java.util.List;

public interface ContractService {
    String NAME = "adm_ContractService";

    Long getNextContractNumber();
    ServiceStatus getDefaultServiceStatus();
    List<Contract> getContractsByContractor(Contractor contractor);
    Contractor findContractorByName(String name);
}