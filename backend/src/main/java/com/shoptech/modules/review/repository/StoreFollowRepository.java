package com.shoptech.modules.review.repository;

import com.shoptech.modules.review.entity.StoreFollow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreFollowRepository extends JpaRepository<StoreFollow, Long> {

    @Query("""
            select f from StoreFollow f
            where (:storeId is null or f.storeId = :storeId)
              and (:search is null or f.userId in (
                    select u.id from User u
                    where u.name like concat('%', :search, '%') or u.email like concat('%', :search, '%')))
            """)
    Page<StoreFollow> search(@Param("storeId") Long storeId, @Param("search") String search, Pageable pageable);
}
