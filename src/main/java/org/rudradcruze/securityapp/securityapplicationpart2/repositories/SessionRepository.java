package org.rudradcruze.securityapp.securityapplicationpart2.repositories;

import org.rudradcruze.securityapp.securityapplicationpart2.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByUserId(Long userId);
}
