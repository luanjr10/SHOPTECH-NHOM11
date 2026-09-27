package com.shoptech.common.response;

import org.springframework.data.domain.Page;

public record PageMeta(int currentPage, int lastPage, int perPage, long total) {

    public static PageMeta of(Page<?> page) {
        return new PageMeta(page.getNumber() + 1, Math.max(page.getTotalPages(), 1), page.getSize(), page.getTotalElements());
    }
}
