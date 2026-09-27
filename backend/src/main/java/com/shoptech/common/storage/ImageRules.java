package com.shoptech.common.storage;

import com.shoptech.common.exception.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;
import java.util.Set;

/** Kiểm tra file upload: phải là ảnh, đúng định dạng cho phép và tối đa 5MB. */
public final class ImageRules {

    public static final long MAX_BYTES = 5L * 1024 * 1024;
    public static final Set<String> DEFAULT_EXT = Set.of("jpg", "jpeg", "png", "webp");

    private ImageRules() {
    }

    public static List<MultipartFile> nonEmpty(List<MultipartFile> files) {
        return files == null ? List.of() : files.stream().filter(f -> f != null && !f.isEmpty()).toList();
    }

    /**
     * @param field     tên trường gốc (vd "images" → lỗi gắn vào "images.0")
     * @param indexed   true khi là mảng file (images[]), false khi là một file đơn (image)
     */
    public static void check(Validator v, List<MultipartFile> files, String field, boolean indexed, Set<String> allowedExt,
                             String imageMsg, String mimesMsg, String maxMsg) {
        for (int i = 0; i < files.size(); i++) {
            MultipartFile f = files.get(i);
            String key = indexed ? field + "." + i : field;
            String type = f.getContentType() == null ? "" : f.getContentType().toLowerCase(Locale.ROOT);
            String ext = extension(f.getOriginalFilename());
            if (!type.startsWith("image/")) {
                v.check(false, key, imageMsg);
            } else if (allowedExt != null && !allowedExt.contains(ext)) {
                v.check(false, key, mimesMsg);
            } else if (f.getSize() > MAX_BYTES) {
                v.check(false, key, maxMsg);
            }
        }
    }

    private static String extension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase(Locale.ROOT);
    }
}
