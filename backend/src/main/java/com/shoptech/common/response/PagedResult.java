package com.shoptech.common.response;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Kết quả phân trang đặt trọn trong "data" (current_page, data, last_page, total...) —
 * dùng cho các màn hình đọc data.data / data.last_page / data.total.
 */
public record PagedResult<T>(
        int currentPage,
        List<T> data,
        Long from,
        int lastPage,
        int perPage,
        Long to,
        long total
) {

    public static <T> PagedResult<T> of(Page<T> page) {
        boolean empty = page.getContent().isEmpty();
        long from = (long) page.getNumber() * page.getSize() + 1;
        return new PagedResult<>(
                page.getNumber() + 1,
                page.getContent(),
                empty ? null : from,
                Math.max(page.getTotalPages(), 1),
                page.getSize(),
                empty ? null : from + page.getNumberOfElements() - 1,
                page.getTotalElements()
        );
    }
}
