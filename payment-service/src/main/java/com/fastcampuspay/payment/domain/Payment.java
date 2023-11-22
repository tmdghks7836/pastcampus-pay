package com.fastcampuspay.payment.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
public class Payment {

    private Long id;

    private String requestMembershipId;

    private String requestPrice;

    private String franchiseId;

    private String franchiseFeeRate;

    private int paymentStatus;

    private Date approvedAt;

    public Payment(String requestMembershipId, String requestPrice, String franchiseId, String franchiseFeeRate, int paymentStatus, Date approvedAt) {
        this.requestMembershipId = requestMembershipId;
        this.requestPrice = requestPrice;
        this.franchiseId = franchiseId;
        this.franchiseFeeRate = franchiseFeeRate;
        this.paymentStatus = paymentStatus;
        this.approvedAt = approvedAt;
    }
}
