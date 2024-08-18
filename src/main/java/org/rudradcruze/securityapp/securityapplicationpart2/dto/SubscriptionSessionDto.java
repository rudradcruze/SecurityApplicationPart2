package org.rudradcruze.securityapp.securityapplicationpart2.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionSessionDto {

    private Long id;
    private LocalDateTime expireDate;
    private LocalDateTime creationDate;
    private Integer limit;


}
