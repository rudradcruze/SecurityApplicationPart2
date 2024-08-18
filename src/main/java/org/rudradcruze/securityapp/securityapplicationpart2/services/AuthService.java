package org.rudradcruze.securityapp.securityapplicationpart2.services;

import lombok.RequiredArgsConstructor;
import org.rudradcruze.securityapp.securityapplicationpart2.dto.LoginDto;
import org.rudradcruze.securityapp.securityapplicationpart2.dto.LoginResponseDto;
import org.rudradcruze.securityapp.securityapplicationpart2.entities.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final SessionService sessionService;
    private final UserService userService;


    public LoginResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        sessionService.generateNewSession(user, refreshToken, accessToken);

        return new LoginResponseDto(user.getId(), accessToken, refreshToken);
    }

    public LoginResponseDto refresh(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);

        User user = userService.getUserById(userId);
        String accessToken = jwtService.generateAccessToken(user);

        sessionService.updateAccessToken(refreshToken, accessToken);

        return new LoginResponseDto(user.getId(), accessToken, refreshToken);
    }

    public void logout(String refreshToken) {
        sessionService.deleteSession(refreshToken);
    }
}
