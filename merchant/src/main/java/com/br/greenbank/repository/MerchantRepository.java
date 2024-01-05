package com.br.greenbank.repository;

import com.br.greenbank.domain.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    @Query("SELECT m FROM Merchant m WHERE m.customerType = 0")
    List<Merchant> findAllMerchants();
}
