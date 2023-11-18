package com.fastcampuspay.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckedRegisteredBankAccountEvent {

    private String rechargeRequestId;

    private String checkRegisteredBankAccountId;

    private String membershipId;

    private boolean isChecked;

    private int amount;

    private String firmbankingRequestId;

    private String bankName;

    private String bankAccountNumber;

//    private String bankAccountId; //RegisteredBankAccount

}
