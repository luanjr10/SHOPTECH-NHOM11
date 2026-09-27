package com.shoptech.common.response;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class Pagination {

    private Pagination() {
    }

    /** page bắt đầu từ 1; per_page kẹp tối đa 100, giá trị <= 0 dùng mặc định. */
    public static Pageable of(Integer page, Integer perPage, int defaultPerPage, Sort sort) {
        int size = perPage == null ? defaultPerPage : Math.min(perPage, 100);
        if (size <= 0) {
            size = defaultPerPage;
        }
        int index = page == null || page < 1 ? 0 : page - 1;
        return PageRequest.of(index, size, sort);
    }
}
