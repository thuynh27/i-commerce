package com.nab.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nab.user.model.UserAuthority;

@Repository
public interface UserRepository extends JpaRepository<UserAuthority, String> {

	UserAuthority findByName(final String userName);

    Optional<UserAuthority> findByEmail(final String email);
}
