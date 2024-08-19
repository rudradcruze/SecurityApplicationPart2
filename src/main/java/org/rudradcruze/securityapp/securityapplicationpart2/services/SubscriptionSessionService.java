package org.rudradcruze.securityapp.securityapplicationpart2.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rudradcruze.securityapp.securityapplicationpart2.dto.SubscriptionSessionDto;
import org.rudradcruze.securityapp.securityapplicationpart2.entities.SubscriptionSession;
import org.rudradcruze.securityapp.securityapplicationpart2.entities.User;
import org.rudradcruze.securityapp.securityapplicationpart2.repositories.SubscriptionSessionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionSessionService {

    private final ModelMapper modelMapper;
    private final SubscriptionSessionRepository subscriptionSessionRepository;
    private final UserService userService;

    public SubscriptionSession setUserLimit(Long id, SubscriptionSessionDto subscriptionSessionDto) {

        User user = userService.getUserById(id);

        SubscriptionSession session = modelMapper.map(subscriptionSessionDto, SubscriptionSession.class);
        session.setUser(user);
        return subscriptionSessionRepository.save(session);
    }

    public SubscriptionSession getLatestSubscriptionSession(Long userId) {
        return subscriptionSessionRepository.findLatestByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No subscription session found for user " + userId));
    }

//    public boolean isLatestSessionExpired(SubscriptionSession SubscriptionSession) {
//        SubscriptionSession latestSession = getLatestSubscriptionSession(userId);
//        return latestSession.getExpireDate().isBefore(LocalDateTime.now());
//    }
}
