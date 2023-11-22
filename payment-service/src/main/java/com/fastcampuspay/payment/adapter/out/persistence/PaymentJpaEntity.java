package com.fastcampuspay.payment.adapter.out.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
public class PaymentJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String requestMembershipId;

    private String requestPrice;

    private String franchiseId;

    private String franchiseFeeRate;

    private int paymentStatus;

    private Date approvedAt;

    public PaymentJpaEntity(String requestMembershipId, String requestPrice, String franchiseId, String franchiseFeeRate, int paymentStatus, Date approvedAt) {
        this.requestMembershipId = requestMembershipId;
        this.requestPrice = requestPrice;
        this.franchiseId = franchiseId;
        this.franchiseFeeRate = franchiseFeeRate;
        this.paymentStatus = paymentStatus;
        this.approvedAt = approvedAt;
    }
}
