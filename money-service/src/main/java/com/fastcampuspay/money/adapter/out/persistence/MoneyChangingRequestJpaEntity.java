package com.fastcampuspay.money.adapter.out.persistence;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "money_changing_request")
@Data
@NoArgsConstructor
public class MoneyChangingRequestJpaEntity {

    @Id
    @GeneratedValue
    private Long moneyChangingRequestId;

    private String targetMembershipId;

    private int moneyChangingType;

    private int moneyAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private int changingMoneyStatus; // 0 요청 1 성공 2 실패

    private String uuid;

    public MoneyChangingRequestJpaEntity( String targetMembershipId, int moneyChangingType, int moneyAmount, Timestamp timestamp, int changingMoneyStatus, String uuid) {
        this.targetMembershipId = targetMembershipId;
        this.moneyChangingType = moneyChangingType;
        this.moneyAmount = moneyAmount;
        this.timestamp = timestamp;
        this.changingMoneyStatus = changingMoneyStatus;
        this.uuid = uuid;
    }
}
