package org.rudradcruze.securityapp.securityapplicationpart2.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rudradcruze.securityapp.securityapplicationpart2.dto.SubscriptionSessionDto;
import org.rudradcruze.securityapp.securityapplicationpart2.entities.SubscriptionSession;
import org.rudradcruze.securityapp.securityapplicationpart2.services.SubscriptionSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionSessionController {

    private final ModelMapper modelMapper;
    private SubscriptionSessionService sessionService;

    @PutMapping("/user/{id}/limit")
    public ResponseEntity<SubscriptionSession> setUserLimit(
            @PathVariable Long id,
            @RequestBody SubscriptionSessionDto subscriptionSessionDto) {

        return ResponseEntity.ok(sessionService.setUserLimit(id, subscriptionSessionDto));
    }


}
