package org.rudradcruze.securityapp.securityapplicationpart2.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rudradcruze.securityapp.securityapplicationpart2.dto.SubscriptionSessionDto;
import org.rudradcruze.securityapp.securityapplicationpart2.services.SubscriptionSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionSessionController {

    private final SubscriptionSessionService sessionService;
    private final ModelMapper modelMapper;

    @PutMapping("/user/{id}/limit")
    public ResponseEntity<SubscriptionSessionDto> setUserLimit(
            @PathVariable Long id,
            @RequestBody SubscriptionSessionDto subscriptionSessionDto) {

        System.out.println(subscriptionSessionDto);
        System.out.println(id);

        return ResponseEntity.ok(modelMapper.map(sessionService.setUserLimit(id, subscriptionSessionDto), SubscriptionSessionDto.class));
    }


}
