package com.lovediary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 
 * TimeEntity
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-26
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-26          HTH             최초 등록
 **/
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeEntity {
    @Column
    private Long accountIdx;

    @Column(length = 1)
    private String deleteYn;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp registDate;

    @LastModifiedDate
    private Timestamp modifyDate;

    @Column
    private Timestamp deleteDate;
}
