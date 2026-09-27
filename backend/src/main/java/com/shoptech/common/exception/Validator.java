package com.shoptech.common.exception;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Gom lỗi validate (Bean Validation + rule cần DB như unique/exists) rồi ném một lần,
 * để frontend nhận đủ lỗi của mọi trường trong một lần.
 */
public class Validator {

    private final Map<String, List<String>> errors = new LinkedHashMap<>();

    public Validator add(String field, String message) {
        errors.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
        return this;
    }

    /** Thêm lỗi khi điều kiện sai — mỗi trường chỉ giữ lỗi đầu tiên. */
    public Validator check(boolean condition, String field, String message) {
        if (!condition && !errors.containsKey(field)) {
            add(field, message);
        }
        return this;
    }

    public boolean has(String field) {
        return errors.containsKey(field);
    }

    public void throwIfFailed() {
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
