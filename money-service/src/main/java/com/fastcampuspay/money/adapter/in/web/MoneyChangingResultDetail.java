package com.fastcampuspay.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyChangingResultDetail {

    private String moneyChangingRequestId;

    private int moneyChangingType;

    private int moneyChangingResultStatus;

    private int amount;
}

/*
enum MoneyChangingType{

    INCREASING,
    DECREASING
}

enum MoneyChangingResultStatus {

    SUCCEEDED,
    FAILED,
    FAILED_NOT_ENOUGH_MONEY,
    FAILED_NOT_EXIST_MEMBERSHIP,
}

*/
