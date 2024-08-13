package org.rudradcruze.securityapp.securityapplicationpart2.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class ResponseLoggerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            logRequestDetails(wrappedRequest);
            logResponseDetails(wrappedResponse);
            wrappedResponse.copyBodyToResponse();
        }
    }

    private void logRequestDetails(ContentCachingRequestWrapper request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String headers = request.getHeader("Authorization");
        String payload = getRequestPayload(request);

        log.info("Request URI: {}", requestURI);
        log.info("Request Method: {}", method);
        log.info("Request Headers: {}", headers);
        log.info("Request Payload: {}", payload);
    }

    private void logResponseDetails(ContentCachingResponseWrapper response) {
        int status = response.getStatus();
        String payload = getResponsePayload(response);

        log.info("Response Status: {}", status);
        log.info("Response Payload: {}", payload);
    }

    private String getRequestPayload(ContentCachingRequestWrapper request) {
        byte[] contentAsByteArray = request.getContentAsByteArray();
        return new String(contentAsByteArray, StandardCharsets.UTF_8);
    }

    private String getResponsePayload(ContentCachingResponseWrapper response) {
        byte[] contentAsByteArray = response.getContentAsByteArray();
        return new String(contentAsByteArray, StandardCharsets.UTF_8);
    }
}
