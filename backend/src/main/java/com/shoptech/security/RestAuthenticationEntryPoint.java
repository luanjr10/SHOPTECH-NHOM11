package com.shoptech.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoptech.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/** Trả JSON 401/403 thay vì trang lỗi mặc định của Spring Security. */
@Component
@RequiredArgsConstructor
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
            throws IOException {
        String message = request.getAttribute(JwtAuthenticationFilter.SESSION_EXPIRED_ATTR) != null
                ? "Phiên đăng nhập đã hết hiệu lực"
                : "Chưa đăng nhập";
        write(response, HttpServletResponse.SC_UNAUTHORIZED, message);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
            throws IOException {
        write(response, HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền thực hiện thao tác này");
    }

    private void write(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getOutputStream(), ApiResponse.fail(message));
    }
}
