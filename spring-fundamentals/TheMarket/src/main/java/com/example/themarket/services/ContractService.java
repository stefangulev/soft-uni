package com.example.themarket.services;

import com.example.themarket.model.dtos.BuyContractServiceDto;
import com.example.themarket.model.dtos.CreateContractServiceDto;
import com.example.themarket.model.dtos.GetActiveContractDto;
import com.example.themarket.model.dtos.UpdateContractServiceDto;

import java.util.Set;


public interface ContractService {
    boolean create(CreateContractServiceDto createContractServiceDto);
    boolean update(UpdateContractServiceDto updateContractServiceDto);
    Set<GetActiveContractDto> getActive();
    boolean buy(BuyContractServiceDto buyContractServiceDto);
}
