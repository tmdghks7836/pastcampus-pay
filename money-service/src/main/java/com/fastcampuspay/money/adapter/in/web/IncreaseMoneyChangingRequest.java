package com.fastcampuspay.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncreaseMoneyChangingRequest {

    private String membershipId;

    private int amount;

}
