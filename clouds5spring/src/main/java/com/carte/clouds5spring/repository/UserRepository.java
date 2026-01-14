package com.carte.clouds5spring.repository;

import com.carte.clouds5spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
