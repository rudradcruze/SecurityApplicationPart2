package org.rudradcruze.securityapp.securityapplicationpart2.repositories;

import org.rudradcruze.securityapp.securityapplicationpart2.entities.Session;
import org.rudradcruze.securityapp.securityapplicationpart2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByUser(User user);

    Optional<Session> findByRefreshToken(String refreshToken);

    Optional<Session> findByAccessToken(String accessToken);
}
