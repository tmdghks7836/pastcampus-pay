package com.fastcampuspay.banking.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetRegisteredBankAccountCommand {


    private String membershipId;
}
