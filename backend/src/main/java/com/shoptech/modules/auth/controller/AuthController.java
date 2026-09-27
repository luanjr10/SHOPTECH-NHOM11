package com.shoptech.modules.auth.controller;

import com.shoptech.common.response.ApiResponse;
import com.shoptech.modules.auth.dto.LoginRequest;
import com.shoptech.modules.auth.dto.LoginResponse;
import com.shoptech.modules.auth.service.AuthService;
import com.shoptech.modules.user.dto.CurrentUserResponse;
import com.shoptech.modules.user.service.UserQueryService;
import com.shoptech.security.AccessGuard;
import com.shoptech.security.AuthUser;
import com.shoptech.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserQueryService userQueryService;
    private final JwtService jwtService;

    @PostMapping("/api/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse result = authService.login(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtService.cookie(result.accessToken()).toString())
                .body(ApiResponse.ok("Đăng nhập thành công", result));
    }

    @GetMapping("/api/me")
    public ApiResponse<CurrentUserResponse> me() {
        AuthUser authUser = AccessGuard.currentUser();
        return ApiResponse.ok(userQueryService.currentUser(userQueryService.getById(authUser.id())));
    }

    @PostMapping("/api/logout")
    public ResponseEntity<ApiResponse<Void>> logout() {
        authService.logout(AccessGuard.currentUser());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtService.forgetCookie().toString())
                .body(ApiResponse.message("Đã đăng xuất"));
    }
}
