package com.fastcampuspay.banking.adapter.out.persistence;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "firmbanking")
@Data
@NoArgsConstructor
public class FirmbankingRequestJpaEntity {

    @Id
    @GeneratedValue
    private Long requestedFirmbankingId;

    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private Integer moneyAmount;

    private Integer firmbankingStatus; // 0: request 1: success 2: failed

    private UUID uuid;

    public FirmbankingRequestJpaEntity(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, Integer moneyAmount, Integer firmbankingStatus, UUID uuid) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
        this.firmbankingStatus = firmbankingStatus;
        this.uuid = uuid;
    }
}
