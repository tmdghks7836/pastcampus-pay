package com.fastcampuspay.payment.application.service;

import com.fastcampuspay.common.UseCase;
import com.fastcampuspay.payment.application.port.in.RequestPaymentCommand;
import com.fastcampuspay.payment.application.port.in.RequestPaymentUseCase;
import com.fastcampuspay.payment.application.port.out.*;
import com.fastcampuspay.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class PaymentService implements RequestPaymentUseCase {

    private final CreatePaymentPort createPaymentPort;

    private final GetMembershipPort getMembershipPort;

    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;

    @Override
    public Payment requestPayment(RequestPaymentCommand command) {

        //충전도 멤버십 머니 유효성 확인
        MembershipStatus membership = getMembershipPort.getMembership(command.getRequestMembershipId());

        RegisteredBankAccountId registeredBankAccount = getRegisteredBankAccountPort.getRegisteredBankAccount(membership.getMembershipId());

        Payment payment = createPaymentPort.createPayment(
                registeredBankAccount.getMembershipId(),
                command.getRequestPrice(),
                command.getFranchiseId(),
                command.getFranchiseFeeRate()
        );
        return payment;
    }
}
