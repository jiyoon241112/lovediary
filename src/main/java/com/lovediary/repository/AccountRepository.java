package com.lovediary.repository;

import com.lovediary.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * AccountRepository
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-27
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-03-27          HTH             최초 등록
 **/
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByIdAndDeleteYn(String id, Character deleteYn);

    boolean existsById(String id);

    Account findByPhoneNumber(String phoneNumber);
}
