package com.shoptech.common.exception;

import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * Chạy Bean Validation trên DTO và trả về {@link Validator} để service bổ sung các rule cần DB
 * (unique, exists...) trước khi ném 422 — frontend nhận đủ lỗi của mọi trường trong một lần.
 */
@Component
@RequiredArgsConstructor
public class RequestValidator {

    private final jakarta.validation.Validator beanValidator;

    public Validator validate(Object dto, Class<?>... groups) {
        Validator v = new Validator();
        beanValidator.validate(dto, groups).stream()
                .sorted(Comparator.comparing((ConstraintViolation<Object> c) -> c.getPropertyPath().toString())
                        .thenComparing(RequestValidator::requiredFirst))
                .forEach(c -> v.check(false, toSnake(c.getPropertyPath().toString()), c.getMessage()));
        return v;
    }

    /** Mỗi trường chỉ giữ một lỗi; ưu tiên lỗi "bắt buộc". */
    private static int requiredFirst(ConstraintViolation<Object> c) {
        String name = c.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
        return name.startsWith("NotBlank") || name.startsWith("NotNull") || name.startsWith("NotEmpty") ? 0 : 1;
    }

    private static String toSnake(String path) {
        return path.replaceAll("([a-z0-9])([A-Z])", "$1_$2").toLowerCase();
    }
}
