package com.shoptech.modules.seller.repository;

import com.shoptech.modules.seller.entity.SellerWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerWalletRepository extends JpaRepository<SellerWallet, Long> {

    boolean existsBySellerProfileId(Long sellerProfileId);
}
