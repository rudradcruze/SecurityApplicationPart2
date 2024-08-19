package org.rudradcruze.securityapp.securityapplicationpart2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionSessionDto {

    private Long id;

    @FutureOrPresent(message = "Date must be future")
    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime expireDate;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime creationDate;

    private Integer sessionLimit;

}
