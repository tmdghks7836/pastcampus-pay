package com.fastcampuspay.money.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class IncreaseMoneyRequestByMembershipIdCommand {

    private String membershipId;

    private int amount;
}
