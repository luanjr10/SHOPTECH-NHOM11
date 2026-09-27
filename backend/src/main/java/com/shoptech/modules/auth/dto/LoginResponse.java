package com.shoptech.modules.auth.dto;

import com.shoptech.modules.user.dto.CurrentUserResponse;

public record LoginResponse(CurrentUserResponse user, String accessToken, String tokenType) {
}
