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

    public SubscriptionSession setUserLimit(Long id, SubscriptionSessionDto subscriptionSessionDto) {

        User user = User.builder().id(id).build();
        SubscriptionSession session = modelMapper.map(subscriptionSessionDto, SubscriptionSession.class);
        session.setUser(user);
        return subscriptionSessionRepository.save(session);
    }
}
