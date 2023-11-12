package com.fastcampuspay.money.domain.membermoney;

import com.fastcampuspay.common.SelfValidating;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberMoneyCreatedEvent {

    private String memberMoneyId;

    private String membershipId;

    private int moneyAmount;
}
