package com.example.themarket.web;

import com.example.themarket.model.dtos.*;
import com.example.themarket.model.entities.Item;
import com.example.themarket.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;
    private static Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public boolean create(CreateItemControllerDto createItemControllerDto) {
        String inputName = createItemControllerDto.getName();
        Long ownerId = createItemControllerDto.getOwnerId();
        if (inputName.trim().length() < 1 || ownerId == null) {
            LOGGER.info("Request to create item was registered, but failed. Item name and/or owner id were not entered!");
            return false;
        }
        boolean creationSuccessful = itemService.create(new CreateItemServiceDto().setName(inputName)
                .setOwnerId(ownerId));
        if (creationSuccessful) {
            LOGGER.info("Item with name {}, owned by user with ID# {} created in the database!", inputName, ownerId);
        } else {
            LOGGER.info("Item was not created. User with ID# {} does not exist in database!", ownerId);
        }
        return creationSuccessful;
    }
    @GetMapping
    public Set<GetItemByOwnerSingleDto> getAllItems(GetItemsByOwnerControllerDto GetItemsWithByOwnerControllerDto) {
        Long ownerId = GetItemsWithByOwnerControllerDto.getOwnerId();
        if (ownerId == null) {
            LOGGER.info("Request to get all items for user was registered, but failed. User ID was not entered!");
            return null;
        }
        Set<GetItemByOwnerSingleDto> allItemsByOwnerId = itemService.getAllItemsByOwnerId(new GetItemsByOwnerServiceDto().setOwnerId(ownerId));
        if (allItemsByOwnerId == null) {
            LOGGER.info("There are no items for User with ID# {} in the database!", ownerId);
        } else {
            LOGGER.info("Returning all items for User with ID# {}!", ownerId);
        }
        return allItemsByOwnerId;


    }
}
