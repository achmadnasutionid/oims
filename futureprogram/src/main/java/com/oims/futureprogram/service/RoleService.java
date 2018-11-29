package com.oims.futureprogram.service;

import com.oims.futureprogram.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getRole();

    Optional<Role> getRoleByName(String name);

    Role createRole(Role role);

    Role updateRole(String name, Role rolerequest);

    void deleteRole(String name);

}
