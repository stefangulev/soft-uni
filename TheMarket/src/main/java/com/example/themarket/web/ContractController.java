package com.example.themarket.web;

import com.example.themarket.model.dtos.*;
import com.example.themarket.services.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping("/contract")
public class ContractController {
    private final ContractService contractService;
    private static Logger LOGGER = LoggerFactory.getLogger(ContractController.class);

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("/create")
    public boolean create(CreateContractControllerDto createContractControllerDto) {
        Long itemId = createContractControllerDto.getItemId();
        BigDecimal price = createContractControllerDto.getPrice();
        if(itemId == null || price == null) {
            LOGGER.info("Request to create a contract was registered, but failed. Item ID and/or price were not entered!");
            return false;
        }
        boolean creationSuccessful = contractService.create(new CreateContractServiceDto().setItemId(itemId).setPrice(price));
        if(creationSuccessful) {
            LOGGER.info("Contract for item with ID# {} succesfully created in the database! Initial price - {}!", itemId, price);
        } else {
            LOGGER.info("Contract was not created. Either Item with ID# {} does not exist in the database, contract for this item is already created or the price entered is invalid!", itemId);
        }
        return creationSuccessful;
    }

    @PutMapping("/update")
    public boolean update(UpdateContractControllerDto updateContractControllerDto) {
        Long itemId = updateContractControllerDto.getItemId();
        BigDecimal price = updateContractControllerDto.getPrice();
        if(itemId == null || price == null) {
            LOGGER.info("Request to update a contract was registered, but failed. Item ID and/or price were not entered!");
            return false;
        }
        boolean updateSuccessful = contractService.update(new UpdateContractServiceDto().setItemId(itemId).setPrice(price));
        if (updateSuccessful) {
            LOGGER.info("Contract for item with ID# {} was updated! New price is {}!", itemId, price);
        } else {
            LOGGER.info("Contract for item with ID# {} was not updated! The contract was either inactive, there is no such contract for this item or the new price was negative!", itemId);
        }
        return updateSuccessful;
    }
    @GetMapping("/active")
    public Set<GetActiveContractDto> getAllActive() {
        Set<GetActiveContractDto> active = contractService.getActive();
        if (active == null) {
            LOGGER.info("No active contracts!");
            return null;
        }
        LOGGER.info("Returning all active contracts ordered by price in ASC order!");
        return active;
    }
    @PutMapping("/buy")
    public boolean buy(BuyContractControllerDto buyContractControllerDto) {
        Long buyerId = buyContractControllerDto.getBuyerId();
        Long itemId = buyContractControllerDto.getItemId();
        if(itemId == null || buyerId == null) {
            LOGGER.info("Request to buy a contract was registered, but failed. Item ID and/or Buyer Id were not entered!");
            return false;
        }

        boolean buySuccessful = contractService.buy(new BuyContractServiceDto().setBuyerId(buyerId).setItemId(itemId));
        if (buySuccessful) {
            LOGGER.info("Contract bought! Item with ID# {} is now owned by User with ID # {}!", itemId, buyerId);
        } else {
            LOGGER.info("Request to buy was unsuccessful. There was no active contract for this item, or the buyer was invalid due to not existing in the database. having low funds or buying from himself!");
        }

        return  buySuccessful;
    }
}
