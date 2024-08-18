package org.rudradcruze.securityapp.securityapplicationpart2.services;

import org.rudradcruze.securityapp.securityapplicationpart2.entities.Session;
import org.rudradcruze.securityapp.securityapplicationpart2.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface SessionService {

    void generateNewSession(User user, String refreshToken, String accessToken);
    void validateSession(String refreshToken);
    void validateAccessTokenSession(String accessToken);
    void deleteSession(String refreshToken);
    void updateAccessToken(String refreshToken, String accessToken);
}
