package com.fastcampuspay.money.aggregation.domain.membermoney;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberMoney {

    private String id;

    private String membershipId;

    private int balance;

}
