package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.UserEntity;
import com.example.manchesterunitedfan.model.entities.UserRoleEntity;
import com.example.manchesterunitedfan.model.enums.UserRoleEnum;
import com.example.manchesterunitedfan.model.service.ChangeRoleServiceModel;
import com.example.manchesterunitedfan.repository.UserRoleRepository;
import com.example.manchesterunitedfan.service.RoleService;
import com.example.manchesterunitedfan.service.UserService;
import com.example.manchesterunitedfan.service.exceptions.InvalidRoleChoiceException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final UserService userService;
    private final UserRoleRepository userRoleRepository;

    public RoleServiceImpl(UserService userService, UserRoleRepository userRoleRepository) {
        this.userService = userService;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void updateRole(ChangeRoleServiceModel serviceModel, Long id) {
        UserEntity userEntityById = userService.findUserEntityById(id);
        UserRoleEntity userRole = userRoleRepository.findByName(UserRoleEnum.USER);
        UserRoleEntity adminRole = userRoleRepository.findByName(UserRoleEnum.ADMIN);
        Set<UserRoleEntity> newRoles = new HashSet<>();
        switch (serviceModel.getRoleSelect()) {
            case "USER":
                newRoles.add(userRole);
                break;
            case "ADMIN":
                newRoles.add(adminRole);
                break;
            case "ADMIN & USER":
                newRoles.add(adminRole);
                newRoles.add(userRole);
                break;
            default: throw new InvalidRoleChoiceException("Invalid role choice!");
        }

        userEntityById.setRole(newRoles);
        userService.saveEntityFromOtherService(userEntityById);
    }


}
