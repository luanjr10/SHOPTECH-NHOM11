package com.shoptech.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "app")
public record AppProperties(
        String url,
        List<String> frontendUrls,
        Jwt jwt,
        String cloudinaryUrl,
        Mail mail
) {

    public record Jwt(String secret, long ttlMinutes, String cookieName, boolean cookieSecure) {
    }

    public record Mail(String fromAddress, String fromName) {
    }

    /** URL công khai của file trong thư mục storage (URL tuyệt đối thì giữ nguyên). */
    public String publicStorageUrl(String path) {
        if (path == null || path.isBlank()) {
            return null;
        }
        if (path.startsWith("http://") || path.startsWith("https://")) {
            return path;
        }
        return url.replaceAll("/+$", "") + "/storage/" + path.replaceAll("^/+", "");
    }
}
