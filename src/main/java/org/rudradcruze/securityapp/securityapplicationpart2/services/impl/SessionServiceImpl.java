package org.rudradcruze.securityapp.securityapplicationpart2.services.impl;

import lombok.RequiredArgsConstructor;
import org.rudradcruze.securityapp.securityapplicationpart2.entities.Session;
import org.rudradcruze.securityapp.securityapplicationpart2.entities.User;
import org.rudradcruze.securityapp.securityapplicationpart2.repositories.SessionRepository;
import org.rudradcruze.securityapp.securityapplicationpart2.services.SessionService;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;

    @Override
    public void generateNewSession(User user, String refreshToken, String accessToken) {
        List<Session> userSessions = sessionRepository.findByUser(user);

        if (userSessions.size() == SESSION_LIMIT) {
            userSessions.sort(Comparator.comparing(Session::getLastUsedAt));

            Session leastRecentlyUsedSession = userSessions.getFirst();
            sessionRepository.delete(leastRecentlyUsedSession);
        }

        Session newSession = Session.builder()
                .user(user)
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
        sessionRepository.save(newSession);
    }

    @Override
    public void validateSession(String refreshToken) {
        Session session = sessionRepository.findByRefreshToken(refreshToken).orElseThrow(
                () -> new SessionAuthenticationException("Session not found for refreshToken: " + refreshToken));
        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }

    @Override
    public void validateAccessTokenSession(String accessToken) {
        sessionRepository.findByAccessToken(accessToken).orElseThrow(
                () -> new SessionAuthenticationException("Session not found for accessToken: " + accessToken)
        );
    }

    @Override
    public void deleteSession(String refreshToken) {
        Session session = sessionRepository.findByRefreshToken(refreshToken).orElseThrow(
                () -> new SessionAuthenticationException("Session not found for refreshToken: " + refreshToken));
        sessionRepository.delete(session);
    }

    @Override
    public void updateAccessToken(String refreshToken, String accessToken) {
        Session session = sessionRepository.findByRefreshToken(refreshToken).orElseThrow(
                () -> new SessionAuthenticationException("Session not found for refreshToken: " + refreshToken)
        );
        session.setAccessToken(accessToken);
        session.setLastAccessTokenUsedTime(LocalDateTime.now());
        sessionRepository.save(session);
    }

}
