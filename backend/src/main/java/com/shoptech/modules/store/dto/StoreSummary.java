package com.shoptech.modules.store.dto;

import com.shoptech.modules.store.entity.Store;

/** Store rút gọn khi eager-load 'store:id,name,slug,logo' trong danh sách sản phẩm. */
public record StoreSummary(Long id, String name, String slug, String logo) {

    public static StoreSummary of(Store s) {
        return s == null ? null : new StoreSummary(s.getId(), s.getName(), s.getSlug(), s.getLogo());
    }
}
