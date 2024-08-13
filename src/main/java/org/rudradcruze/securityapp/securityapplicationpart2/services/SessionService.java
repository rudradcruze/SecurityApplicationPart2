package org.rudradcruze.securityapp.securityapplicationpart2.services;

import org.rudradcruze.securityapp.securityapplicationpart2.entities.Session;
import org.rudradcruze.securityapp.securityapplicationpart2.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface SessionService {
    Session createSession(User user, String token);
    Session getSessionByUserId(Long userId);

}
