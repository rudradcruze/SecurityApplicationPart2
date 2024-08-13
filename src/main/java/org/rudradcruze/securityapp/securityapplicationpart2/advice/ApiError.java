package org.rudradcruze.securityapp.securityapplicationpart2.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
//@Builder
public class ApiError {

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timestamp;

    private HttpStatus status;
    private String message;
    private List<String> subErrors;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public ApiError(HttpStatus status, String message, List<String> subErrors) {
        this();
        this.status = status;
        this.message = message;
        this.subErrors = subErrors;
    }
}
