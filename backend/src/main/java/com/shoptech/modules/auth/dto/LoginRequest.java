package com.shoptech.modules.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Vui lòng nhập tên đăng nhập hoặc email") String login,
        @NotBlank(message = "Vui lòng nhập mật khẩu") String password
) {
}
