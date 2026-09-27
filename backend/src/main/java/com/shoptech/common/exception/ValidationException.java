package com.shoptech.common.exception;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Lỗi 422: { success:false, message, errors: { field: [msg] } }. */
@Getter
public class ValidationException extends RuntimeException {

    private final Map<String, List<String>> errors;

    public ValidationException(Map<String, List<String>> errors) {
        super("Dữ liệu không hợp lệ");
        this.errors = errors;
    }

    public static ValidationException of(String field, String message) {
        Map<String, List<String>> errors = new LinkedHashMap<>();
        errors.put(field, List.of(message));
        return new ValidationException(errors);
    }
}
