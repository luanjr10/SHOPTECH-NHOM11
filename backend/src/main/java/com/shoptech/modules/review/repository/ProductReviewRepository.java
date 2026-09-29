package com.shoptech.modules.review.repository;

import com.shoptech.modules.review.entity.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    @Query("""
            select r from ProductReview r
            where (:rating is null or r.rating = :rating)
              and (:storeId is null or r.productId in (select p.id from Product p where p.storeId = :storeId))
            """)
    Page<ProductReview> search(@Param("rating") Integer rating, @Param("storeId") Long storeId, Pageable pageable);

    /** [count, avg rating] mọi đánh giá của sản phẩm thuộc gian hàng. */
    @Query("select count(r), avg(r.rating) from ProductReview r where r.productId in (select p.id from Product p where p.storeId = :storeId)")
    List<Object[]> storeStats(@Param("storeId") Long storeId);
}
