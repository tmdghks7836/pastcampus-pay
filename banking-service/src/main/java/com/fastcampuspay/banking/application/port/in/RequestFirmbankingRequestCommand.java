package com.fastcampuspay.banking.application.port.in;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fastcampuspay.common.SelfValidating;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RequestFirmbankingRequestCommand extends SelfValidating<RegisterBankAccountCommand> {

    @NotNull
    private final String fromBankName;

    @NotNull
    private final String fromBankAccountNumber;

    @NotNull
    private final String toBankName;

    @NotNull
    private final String toBankAccountNumber;

    @NotNull
    private final int moneyAmount;

    @NotNull
    private final int status;

    public RequestFirmbankingRequestCommand(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int moneyAmount, int status) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
        this.status = status;

        this.validateSelf();
    }
}
