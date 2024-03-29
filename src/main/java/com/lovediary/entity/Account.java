package com.lovediary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

/**
 * 
 * Account
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-27
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-27          HTH             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id
    private Long idx;

    @Column
    private Long profileIdx;

    @Column
    private Character type;

    @Column(length = 100)
    private String id;

    @Column(length = 300)
    private String password;

    @Column(length = 100)
    private String name;

    @Column(length = 300)
    private String email;

    @Column(length = 15)
    private String phoneNumber;

    @Column
    private Character deleteYn;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp registDate;

    @LastModifiedDate
    private Timestamp modifyDate;

    @OneToOne
    @JoinColumn(name = "idx", referencedColumnName = "account_idx")
    private CoupleAccount coupleAccount;

    @Builder
    public Account(Long idx, Long profileIdx, Character type, String id, String password, String name, String email, String phoneNumber, Character deleteYn, Timestamp registDate, Timestamp modifyDate, CoupleAccount coupleAccount) {
        this.idx = idx;
        this.profileIdx = profileIdx;
        this.type = type;
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
        this.coupleAccount = coupleAccount;
    }
}
