package com.shoptech.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Đọc các trường JSON-string trong multipart (specifications, variants, existing_images...). */
@Component
@RequiredArgsConstructor
public class Json {

    private final ObjectMapper objectMapper;

    public boolean isValid(String raw) {
        if (raw == null || raw.isBlank()) {
            return true;
        }
        try {
            objectMapper.readTree(raw);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Object parse(String raw) {
        if (raw == null || raw.isBlank()) {
            return null;
        }
        try {
            return objectMapper.readValue(raw, Object.class);
        } catch (Exception e) {
            return null;
        }
    }

    public String write(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new IllegalStateException("Không thể ghi JSON", e);
        }
    }

    /** Giá trị lưu trong Mongo có thể là mảng thật hoặc chuỗi JSON. */
    public List<Object> listOf(Object stored) {
        if (stored instanceof List<?> l) {
            return new ArrayList<>(l);
        }
        return stored instanceof String s ? list(s) : new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> mapListOf(Object stored) {
        List<Map<String, Object>> out = new ArrayList<>();
        for (Object o : listOf(stored)) {
            if (o instanceof Map<?, ?> m) {
                out.add((Map<String, Object>) m);
            }
        }
        return out;
    }

    public List<Object> list(String raw) {
        Object parsed = parse(raw);
        return parsed instanceof List<?> l ? new ArrayList<>(l) : new ArrayList<>();
    }

    public List<String> stringList(String raw) {
        List<String> out = new ArrayList<>();
        for (Object o : list(raw)) {
            if (o != null && !o.toString().isBlank()) {
                out.add(o.toString());
            }
        }
        return out;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> mapList(String raw) {
        List<Map<String, Object>> out = new ArrayList<>();
        for (Object o : list(raw)) {
            if (o instanceof Map<?, ?> m) {
                out.add((Map<String, Object>) m);
            }
        }
        return out;
    }
}
