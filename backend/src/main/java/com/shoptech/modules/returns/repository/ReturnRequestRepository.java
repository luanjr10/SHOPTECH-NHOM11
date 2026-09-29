package com.shoptech.modules.returns.repository;

import com.shoptech.modules.returns.entity.ReturnRequest;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReturnRequestRepository extends JpaRepository<ReturnRequest, Long> {

    /** Yêu cầu thuộc các phần đơn của gian hàng, lọc theo trạng thái (null = tất cả). */
    @Query("""
            select r from ReturnRequest r
            where r.sellerOrderId in (select so.id from SellerOrder so where so.storeId = :storeId)
              and (:status is null or r.status = :status)
            """)
    Page<ReturnRequest> searchByStore(@Param("storeId") Long storeId, @Param("status") String status, Pageable pageable);

    /** Khoá dòng khi phản hồi để hai lần bấm không cùng xử lý một yêu cầu. */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select r from ReturnRequest r where r.id = :id")
    Optional<ReturnRequest> findByIdForUpdate(@Param("id") Long id);
}
