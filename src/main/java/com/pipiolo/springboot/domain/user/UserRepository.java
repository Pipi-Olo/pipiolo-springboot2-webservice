package com.pipiolo.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Deprecated
public interface UserRepository
//        extends JpaRepository<User, Long>
{

    Optional<User> findByEmail(String email);
}
