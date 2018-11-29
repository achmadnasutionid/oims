package com.oims.futureprogram.service.impl;

import com.oims.futureprogram.error.ErrorCode;
import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.Role;
import com.oims.futureprogram.repository.RoleRepository;
import com.oims.futureprogram.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRole() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleByName (String name) {
        if (!roleRepository.existsByName(name)) {
            throw new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }
        else {
            return roleRepository.findByName(name);
        }
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(String name, Role rolerequest) {
        return roleRepository.findByName(name).map(role -> {
            role.setName(rolerequest.getName());
            return roleRepository.save(role);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    public void deleteRole(String name) {
        if(!roleRepository.existsByName(name)) {
            throw  new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }
        else {
            roleRepository.deleteByName(name);
        }
    }
}
