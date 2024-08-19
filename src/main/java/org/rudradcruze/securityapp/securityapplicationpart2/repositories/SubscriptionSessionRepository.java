package org.rudradcruze.securityapp.securityapplicationpart2.repositories;

import org.rudradcruze.securityapp.securityapplicationpart2.entities.SubscriptionSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionSessionRepository extends JpaRepository<SubscriptionSession, Long> {

    @Query("SELECT s FROM SubscriptionSession s WHERE s.user.id = :userId ORDER BY s.creationDate DESC")
    Optional<SubscriptionSession> findLatestByUserId(Long userId);
}
