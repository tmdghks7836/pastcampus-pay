package com.fastcampuspay.remittance.adapter.out.persistence;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "remittance_request")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemittanceRequestJpaEntity {

    @Id
    @GeneratedValue
    private Long remittanceRequestId;

    @Getter
    private String fromMembershipId;

    @Getter
    private String toMembershipId;


    @Getter
    private String toBankName;

    @Getter
    private String toBankAccountNumber;

    @Getter
    private int remittanceType;

    @Getter
    private String remittanceStatus;

    @Getter
    private int amount;

}
