package com.fastcampuspay.money.domain.membermoney;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RechargingRequestCreatedEvent {

    //충전 동작 요청이 생성되엇다는 이벤트
    
    private String rechargingRequestId;
    
    private String membershipId;
    
    private int amount;

    private String registeredBankAccountId;

    private String bankName;
    private String bankAccountNumber;

}
