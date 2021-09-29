package com.example.themarket.services.impl;

import com.example.themarket.model.dtos.CreateItemServiceDto;
import com.example.themarket.model.dtos.GetItemByOwnerSingleDto;
import com.example.themarket.model.dtos.GetItemsByOwnerServiceDto;
import com.example.themarket.model.entities.Item;
import com.example.themarket.model.entities.User;
import com.example.themarket.repositories.ItemRepository;
import com.example.themarket.repositories.UserRepository;
import com.example.themarket.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean create(CreateItemServiceDto createItemServiceDto) {
        String name = createItemServiceDto.getName();
        User owner = userRepository.findByUserId(createItemServiceDto.getOwnerId());
        if(owner == null) {
            return false;
        }
        Item item = new Item().setName(name).setOwner(owner);
        itemRepository.save(item);
        return true;
    }

    @Override
    public Set<GetItemByOwnerSingleDto> getAllItemsByOwnerId(GetItemsByOwnerServiceDto getItemsWithByOwnerServiceDto) {
        Long ownerId = getItemsWithByOwnerServiceDto.getOwnerId();
        if(userRepository.findByUserId(ownerId) == null) {
            return null;
        }
        Set<GetItemByOwnerSingleDto> allItemsByOwnerId = itemRepository.findAllItemsByOwnerId(ownerId).stream().map(d -> modelMapper.map(d, GetItemByOwnerSingleDto.class)).collect(Collectors.toSet());
        if (allItemsByOwnerId.isEmpty()) {
            return null;
        }
        return allItemsByOwnerId;
    }
}
