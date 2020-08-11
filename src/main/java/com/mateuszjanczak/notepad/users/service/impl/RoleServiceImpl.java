package com.mateuszjanczak.notepad.users.service.impl;

import com.mateuszjanczak.notepad.users.entity.Role;
import com.mateuszjanczak.notepad.users.entity.RoleName;
import com.mateuszjanczak.notepad.users.repository.RoleRepository;
import com.mateuszjanczak.notepad.users.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleUser() {
        return roleRepository.findByName(RoleName.role_user).orElseGet(() -> roleRepository.save(new Role(RoleName.role_user)));
    }
}
