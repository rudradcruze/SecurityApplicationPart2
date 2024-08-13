package org.rudradcruze.securityapp.securityapplicationpart2.repositories;

import org.rudradcruze.securityapp.securityapplicationpart2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
