package com.dreamholiday.areas.users.repositories;

import com.dreamholiday.areas.users.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findOneByAuthority(String authority);
}
