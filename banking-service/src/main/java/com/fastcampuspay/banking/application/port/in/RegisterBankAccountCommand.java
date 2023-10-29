package com.fastcampuspay.banking.application.port.in;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.fastcampuspay.common.SelfValidating;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 포트
 * Port는 application 입장에서 consumer, 또는 application에서 나가거나/들어오는 end point라고 볼 수 있다.
 * 포트는 내부 비즈니스 영역을 외부 영역에 노출한 API이고 인바운드(Inbound)/아웃바운드(Outbound) 포트로 구분
 * 인바운드 포트 - 내부 영역 사용을 위해 노출된 API
 * 아웃바운드 포트 - 내부 영역이 외부 영역을 사용하기 위한 API
 * */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterBankAccountCommand extends SelfValidating<RegisterBankAccountCommand> {

    @NotNull
    private final String membershipId;

    @NotNull
    private final String bankName;

    @NotNull
    private final String bankAccountNumber;

    @NotNull
    private final boolean linkedStatusIsValid;

    public RegisterBankAccountCommand( String membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;

        this.validateSelf();
    }
}
