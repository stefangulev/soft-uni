package com.example.themarket.services.impl;

import com.example.themarket.model.dtos.BuyContractServiceDto;
import com.example.themarket.model.dtos.CreateContractServiceDto;
import com.example.themarket.model.dtos.GetActiveContractDto;
import com.example.themarket.model.dtos.UpdateContractServiceDto;
import com.example.themarket.model.entities.Contract;
import com.example.themarket.model.entities.Item;
import com.example.themarket.model.entities.User;
import com.example.themarket.repositories.ContractRepository;
import com.example.themarket.repositories.ItemRepository;
import com.example.themarket.repositories.UserRepository;
import com.example.themarket.services.ContractService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ContractServiceImpl(ContractRepository contractRepository, ItemRepository itemRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.contractRepository = contractRepository;
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public boolean create(CreateContractServiceDto createContractServiceDto) {
        Long itemId = createContractServiceDto.getItemId();
        Item requestedItem = itemRepository.findItemById(itemId);
        BigDecimal price = createContractServiceDto.getPrice();
        if (requestedItem == null || contractRepository.findContractByItemId(itemId) != null || price.doubleValue() < 0) {
            return false;
        }
        User seller = requestedItem.getOwner();
       contractRepository.save(new Contract().setActive(true).setSeller(seller)
               .setItem(requestedItem).setPrice(price));
        return true;
    }

    @Override
    public boolean update(UpdateContractServiceDto updateContractServiceDto) {
        Contract contractByItemId = contractRepository.findContractByItemId(updateContractServiceDto.getItemId());
        if (contractByItemId == null || !contractByItemId.isActive() || updateContractServiceDto.getPrice().doubleValue() < 0) {
            return false;
        }
        contractRepository.save(contractByItemId.setPrice(updateContractServiceDto.getPrice()));
        return true;
    }

    @Override
    public Set<GetActiveContractDto> getActive() {
        Set<Contract> allByActiveTrue = contractRepository.findAllByActiveTrue();
        if (allByActiveTrue.isEmpty()) {
            return null;
        }


        return allByActiveTrue.stream().map(c -> {
            GetActiveContractDto dto = modelMapper.map(c, GetActiveContractDto.class);
            dto.setSellerId(c.getSeller().getId());
            dto.setItemId(c.getItem().getId());
            return dto;
        }).sorted(Comparator.comparing(GetActiveContractDto::getPrice)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public boolean buy(BuyContractServiceDto buyContractServiceDto) {
        Long buyerId = buyContractServiceDto.getBuyerId();
        Long itemId = buyContractServiceDto.getItemId();
        Contract contract = contractRepository.findContractByItemId(itemId);
        User buyer = userRepository.findByUserId(buyerId);
        if (contract == null || !contract.isActive() || buyer == null || buyer.getAccountBalance().doubleValue() < contract.getPrice().doubleValue()) {
            return false;
        }
        User seller = userRepository.findByUserId(contract.getSeller().getId());
        if (buyerId.equals(seller.getId())) {
            return false;
        }
        Item item = itemRepository.findItemById(itemId);
        seller.setAccountBalance(seller.getAccountBalance().add(contract.getPrice()));
        buyer.setAccountBalance(buyer.getAccountBalance().subtract(contract.getPrice()));
        item.setOwner(buyer);
        contract.setBuyer(buyer);
        contract.setActive(false);
        userRepository.save(buyer);
        userRepository.save(seller);
        itemRepository.save(item);
        contractRepository.save(contract);
        return true;
    }
}
