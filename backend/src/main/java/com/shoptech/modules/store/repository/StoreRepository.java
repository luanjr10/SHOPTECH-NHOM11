package com.shoptech.modules.store.repository;

import com.shoptech.modules.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findByIdIn(Collection<Long> ids);
}
