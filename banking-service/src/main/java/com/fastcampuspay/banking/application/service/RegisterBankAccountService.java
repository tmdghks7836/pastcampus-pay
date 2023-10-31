package com.fastcampuspay.banking.application.service;

import com.fastcampuspay.banking.adapter.out.external.bank.BankAccount;
import com.fastcampuspay.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.fastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.fastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountCommand;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountUseCase;
import com.fastcampuspay.banking.application.port.out.RegisterBankAccountPort;
import com.fastcampuspay.banking.application.port.out.RequestBankAccountInfoPort;
import com.fastcampuspay.banking.domain.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.UseCase;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerMembershipPort;
    private final RegisteredBankAccountMapper mapper;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;

    @Override
    public RegisteredBankAccount register(RegisterBankAccountCommand command) {


        /**은행 계좌 등록 로직
         *
         * //1. 등록된 계좌인지 확인
         * 1. 외부 실제 은행에 등록이 가능한 계좌인지 확인  External System
         * port -> adapter -> external
         *
         * 2. 등록 가능한 계좌라면, 등록
         * 2-1. 등록 가능하지 않은 계좌라면 에러를 리턴
         *
         *
         * */
        GetBankAccountRequest request = GetBankAccountRequest
                .builder()
                .bankAccountNumber(command.getBankAccountNumber())
                .bankName(command.getBankName())
                .build();

        /** 실제 외부의 은행계좌 정보 GET*/
        BankAccount bankAccount = requestBankAccountInfoPort.getBankAccountInfo(request);

        if (!bankAccount.isValid()) return null;

        /**등록정보 저장 */
        RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity = registerMembershipPort.create(
                new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                new RegisteredBankAccount.BankName(command.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(command.isLinkedStatusIsValid()));

        return mapper.mapToDomainEntity(registeredBankAccountJpaEntity);
    }
}
