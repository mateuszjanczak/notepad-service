package com.mateuszjanczak.notepad.users.dao;

import com.mateuszjanczak.notepad.users.model.Role;
import com.mateuszjanczak.notepad.users.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByName(RoleName name);

}
