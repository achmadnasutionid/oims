package com.oims.futureprogram.controller;

import com.oims.futureprogram.model.Role;
import com.oims.futureprogram.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public List<Role> getRole() {
        return roleService.getRole();
    }

    @GetMapping("/role/{roleName}")
    public Optional<Role> getRoleByName(@PathVariable String roleName) {
        return roleService.getRoleByName(roleName);
    }

    @PostMapping("/role")
    public Role createRole(@Valid @RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping("/role/{roleName")
    public Role updateRole(@PathVariable String roleName, @Valid @RequestBody Role rolerequest) {
        return roleService.updateRole(roleName, rolerequest);
    }

    @DeleteMapping("/role/{roleName")
    public void deleteRole(@PathVariable String roleName) {
        roleService.deleteRole(roleName);
    }
}
