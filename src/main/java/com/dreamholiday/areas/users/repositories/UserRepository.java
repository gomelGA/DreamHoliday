package com.dreamholiday.areas.users.repositories;

import com.dreamholiday.areas.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

    T findOneByUsername(String username);
}
