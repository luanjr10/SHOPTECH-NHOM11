package com.shoptech.common.util;

import java.text.Normalizer;
import java.util.Locale;

/** Tương đương Illuminate\Support\Str::slug (bỏ dấu tiếng Việt, đ → d). */
public final class Slugs {

    private Slugs() {
    }

    public static String slug(String input) {
        if (input == null) {
            return "";
        }
        String s = input.replace('đ', 'd').replace('Đ', 'D');
        s = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{M}+", "");
        s = s.replace("@", "-at-").toLowerCase(Locale.ROOT);
        s = s.replaceAll("[^a-z0-9\\s_-]", "");
        s = s.replaceAll("[\\s_-]+", "-");
        return s.replaceAll("^-+|-+$", "");
    }
}
