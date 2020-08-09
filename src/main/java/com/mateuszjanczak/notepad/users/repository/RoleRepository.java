package com.mateuszjanczak.notepad.users.repository;

import com.mateuszjanczak.notepad.users.entity.Role;
import com.mateuszjanczak.notepad.users.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByName(RoleName roleName);

}
