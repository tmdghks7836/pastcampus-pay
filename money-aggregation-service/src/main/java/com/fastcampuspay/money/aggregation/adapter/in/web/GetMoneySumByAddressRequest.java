package com.fastcampuspay.money.aggregation.adapter.in.web;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetMoneySumByAddressRequest {

    private String address;
}
