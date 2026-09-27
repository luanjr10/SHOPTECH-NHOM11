package com.shoptech.modules.auth.service;

import com.shoptech.common.exception.ApiException;
import com.shoptech.common.exception.RequestValidator;
import com.shoptech.modules.auth.dto.LoginRequest;
import com.shoptech.modules.auth.dto.LoginResponse;
import com.shoptech.modules.user.entity.User;
import com.shoptech.modules.user.repository.UserRepository;
import com.shoptech.modules.user.service.UserQueryService;
import com.shoptech.security.AuthUser;
import com.shoptech.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserQueryService userQueryService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RequestValidator requestValidator;

    public LoginResponse login(LoginRequest request) {
        requestValidator.validate(request).throwIfFailed();

        String login = request.login().trim();
        User user = (login.contains("@") ? userRepository.findByEmail(login) : userRepository.findByUsername(login))
                .filter(u -> u.getPassword() != null && passwordEncoder.matches(request.password(), u.getPassword()))
                .orElseThrow(() -> ApiException.unauthorized("Thông tin đăng nhập không chính xác"));

        String token = jwtService.issue(user.getId(), user.getTokenVersion());
        return new LoginResponse(userQueryService.currentUser(user), token, "Bearer");
    }

    public void logout(AuthUser authUser) {
        jwtService.invalidate(authUser.jti(), authUser.expiresAtEpochSeconds());
    }
}
