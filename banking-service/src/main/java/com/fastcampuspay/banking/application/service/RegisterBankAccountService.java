package com.fastcampuspay.banking.application.service;

import com.fastcampuspay.banking.adapter.out.external.bank.BankAccount;
import com.fastcampuspay.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.fastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.fastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.fastcampuspay.banking.application.port.in.GetRegisterBankAccountUseCase;
import com.fastcampuspay.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountCommand;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountUseCase;
import com.fastcampuspay.banking.application.port.out.*;
import com.fastcampuspay.banking.domain.bankaccount.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.UseCase;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase, GetRegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper mapper;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;
    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;
    private final GetMembershipPort getMembershipPort;
    private final CommandGateway commandGateway;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {


        /**은행 계좌 등록 로직
         *
         * //1. 등록된 계좌인지 확인
         * 1. 외부 실제 은행에 등록이 가능한 계좌인지 확인  External System
         * port -> adapter -> external
         */
        MembershipStatus membership = getMembershipPort.getMembership(command.getMembershipId());
        if(!membership.isValid()){
            return null;
        }

         /* 2. 등록 가능한 계좌라면, 등록
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
        RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity = registerBankAccountPort.create(
                new RegisteredBankAccount.RegisteredBankAccountId(""),
                new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                new RegisteredBankAccount.BankName(command.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(command.isLinkedStatusIsValid()));

        return mapper.mapToDomainEntity(registeredBankAccountJpaEntity);
    }

    @Override
    public void registerBankAccountByEvent(RegisterBankAccountCommand command) {


        commandGateway.send(command)
                .whenComplete(
                        (result, throwable) -> {

                            if (throwable != null) {
                                throwable.printStackTrace();
                            } else {
                                //성공
                                //request firm db save
                                System.out.println("RegisterBankAccountCommand Success");

                                registerBankAccountPort.create(
                                        new RegisteredBankAccount.RegisteredBankAccountId(result.toString()),
                                        new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                                        new RegisteredBankAccount.BankName(command.getBankName()),
                                        new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                                        new RegisteredBankAccount.LinkedStatusIsValid(command.isLinkedStatusIsValid()));
                            }
                        }
                );
    }

    @Override
    public RegisteredBankAccount getRegisterBankAccount(GetRegisteredBankAccountCommand command) {

        return mapper.mapToDomainEntity(getRegisteredBankAccountPort.getRegisteredBankAccount(command));
    }
}
