package com.lovediary.repository;

import com.lovediary.entity.CoupleAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 
 * CoupleAccountRepository
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-27
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-27          HTH             최초 등록
 **/
public interface CoupleAccountRepository extends JpaRepository<CoupleAccount, Long> {
    CoupleAccount findByCoupleIdxAndAccountIdxIsNot(Long coupleIdx, Long accountIdx);

    List<CoupleAccount> findByCoupleIdx(Long coupleIdx);
}
