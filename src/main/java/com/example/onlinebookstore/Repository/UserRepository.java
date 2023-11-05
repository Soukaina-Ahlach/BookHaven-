package com.example.onlinebookstore.Repository;

import org.bookhaven.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByUsername(String username);
}
