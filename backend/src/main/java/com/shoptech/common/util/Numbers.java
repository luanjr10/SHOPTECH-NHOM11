package com.shoptech.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Numbers {

    private Numbers() {
    }

    public static BigDecimal round2(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value.setScale(2, RoundingMode.HALF_UP);
    }

    /** Giá sau giảm = price - price * discount / 100, làm tròn 2 chữ số. */
    public static BigDecimal finalPrice(BigDecimal price, Integer discountPercent) {
        BigDecimal p = price == null ? BigDecimal.ZERO : price;
        int d = discountPercent == null ? 0 : discountPercent;
        BigDecimal discount = p.multiply(BigDecimal.valueOf(d)).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
        return round2(p.subtract(discount));
    }

    public static Integer toInt(Object value) {
        if (value == null || "".equals(value)) {
            return null;
        }
        if (value instanceof Number n) {
            return n.intValue();
        }
        try {
            return (int) Double.parseDouble(value.toString().trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** Chuỗi không phải số được coi là 0. */
    public static int toIntOrZero(Object value) {
        Integer i = toInt(value);
        return i == null ? 0 : i;
    }

    public static BigDecimal toDecimal(Object value) {
        if (value == null || "".equals(value)) {
            return null;
        }
        try {
            return new BigDecimal(value.toString().trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** filter_var(..., FILTER_VALIDATE_BOOLEAN) */
    public static boolean toBool(String value) {
        if (value == null) {
            return false;
        }
        String v = value.trim().toLowerCase();
        return v.equals("1") || v.equals("true") || v.equals("on") || v.equals("yes");
    }

    public static boolean isInteger(String value) {
        return value != null && value.trim().matches("-?\\d+");
    }
}
