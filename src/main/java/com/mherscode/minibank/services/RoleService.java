package com.mherscode.minibank.services;

import com.mherscode.minibank.model.Role;
import com.mherscode.minibank.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;

    public void save(Role role) {
        roleRepo.save(role);
    }

    public Role findByRoleName(String roleName) {
        return roleRepo.findByRoleName(roleName);
    }

    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    public Role findOne(Long id) {
        Optional<Role> role = roleRepo.findById(id);
        return role.orElse(null);
    }

}
