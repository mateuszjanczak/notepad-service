package com.mateuszjanczak.notepad.users.service;

import com.mateuszjanczak.notepad.users.dao.RoleDao;
import com.mateuszjanczak.notepad.users.model.Role;
import com.mateuszjanczak.notepad.users.model.RoleName;
import com.mateuszjanczak.notepad.users.model.User;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleDao roleRepository;

    public RoleService(RoleDao roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(RoleName name) {
        return roleRepository.findByName(name);
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

}
