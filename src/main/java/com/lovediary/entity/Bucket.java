package com.lovediary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import java.sql.Timestamp;

/**
 *
 * Bucket
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-09
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-09          JJY             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="bucket")
public class Bucket extends JoinAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long thumbnailIdx;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private Character achieveYn;

    @Column
    private Timestamp achieveDate;

    @Column(name = "account_idx", insertable = false, updatable=false)
    private Long accountIdx;

    @Column
    private Character deleteYn;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp registDate;

    @Column
    private Timestamp modifyDate;

    @Column
    private Timestamp deleteDate;

    @Builder
    public Bucket(Long idx, Long thumbnailIdx, String title, String contents, Character achieveYn, Timestamp achieveDate,
                  Long accountIdx, Character deleteYn, Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate
                    , Account account) {
        this.idx = idx;
        this.thumbnailIdx = thumbnailIdx;
        this.title = title;
        this.contents = contents;
        this.achieveYn = achieveYn == null ? 'N' : achieveYn;
        this.achieveDate = achieveDate;
        this.accountIdx = accountIdx;
        this.deleteYn = deleteYn == null ? 'N' : deleteYn;
        this.registDate = registDate == null ? new Timestamp(System.currentTimeMillis()) : registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
        this.setAccount(account);
    }
}
