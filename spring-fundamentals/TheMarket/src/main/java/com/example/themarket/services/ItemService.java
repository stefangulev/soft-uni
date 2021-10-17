package com.example.themarket.services;

import com.example.themarket.model.dtos.CreateItemServiceDto;
import com.example.themarket.model.dtos.GetItemByOwnerSingleDto;
import com.example.themarket.model.dtos.GetItemsByOwnerServiceDto;
import com.example.themarket.model.entities.Item;

import java.util.Set;

public interface ItemService {
    boolean create(CreateItemServiceDto createItemServiceDto);
    Set<GetItemByOwnerSingleDto> getAllItemsByOwnerId(GetItemsByOwnerServiceDto getItemsWithByOwnerServiceDto);
}
