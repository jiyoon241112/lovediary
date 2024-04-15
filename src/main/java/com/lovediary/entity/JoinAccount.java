package com.lovediary.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class JoinAccount {
    @OneToOne
    @JoinColumn(name = "account_idx", referencedColumnName = "idx")
    private Account account;
}
