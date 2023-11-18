package com.fastcampuspay.banking.domain.bankaccount;

import com.fastcampuspay.banking.adapter.out.external.bank.BankAccount;
import com.fastcampuspay.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountCommand;
import com.fastcampuspay.banking.application.port.out.GetRegisteredBankAccountPort;
import com.fastcampuspay.banking.application.port.out.RequestBankAccountInfoPort;
import com.fastcampuspay.common.event.CheckRegisteredBankAccountCommand;
import com.fastcampuspay.common.event.CheckedRegisteredBankAccountEvent;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.*;

@Aggregate
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredBankAccount {

    @AggregateIdentifier
    @Getter
    private String id;

    @Getter
    private String membershipId;

    @Getter
    private String bankName;

    @Getter
    private String bankAccountNumber;

    @Getter
    private boolean linkedStatusIsValid;


    @CommandHandler
    public RegisteredBankAccount(RegisterBankAccountCommand command){

        System.out.println("RegisterBankAccountCommand CommandHandler");
        apply(new CreateRegisteredBankAccountEvent(
                command.getMembershipId(),
                command.getBankName(),
                command.getBankAccountNumber()));
    }

    @CommandHandler
    public void handle(@NotNull CheckRegisteredBankAccountCommand command, RequestBankAccountInfoPort bankAccountInfoPort){

        System.out.println("CheckRegisteredBankAccountCommand CommandHandler");

        /** 이 어그리거트가 정상인지 확인 */
        id = command.getBankAccountId();

        /** 체크 */
        BankAccount bankAccountInfo = bankAccountInfoPort.getBankAccountInfo(
                new GetBankAccountRequest(
                        command.getBankName(),
                        command.getBankAccountNumber()
                ));

        boolean valid = bankAccountInfo.isValid();

        String firmbankingUUID = UUID.randomUUID().toString();

        //bank account 잘 체크되었다는 이벤트 발행
        apply(CheckedRegisteredBankAccountEvent.builder()
                .rechargeRequestId(command.getRechargeRequestId())
                .checkRegisteredBankAccountId(command.getCheckRegisteredBankAccountId())
                .membershipId(command.getMembershipId())
                .isChecked(valid)
                .amount(command.getAmount())
                .firmbankingRequestId(firmbankingUUID)
                .bankName(bankAccountInfo.getBankName())
                .bankAccountNumber(bankAccountInfo.getBankAccountNumber())
                            .build()

        );
    }

   /* public void handle(@NotNull CheckRegisteredBankAccountCommand command){
        System.out.println("CheckRegisteredBankAccountCommand Handler");

        // command 를 통해, 이 어그리거트(RegisteredBankAccount) 가 정상인지를 확인해야 함.


        apply();
    }*/

    @EventSourcingHandler
    public void on(CreateRegisteredBankAccountEvent event){

        System.out.println("CreateRegisteredBankAccountEvent Sourcing Handler");
        id = UUID.randomUUID().toString();
        membershipId = event.getMembershipId();
        bankAccountNumber = event.getBankAccountNumber();
        bankName = event.getBankName();
    }

    @Deprecated
    public static RegisteredBankAccount generate(RegisteredBankAccountId registeredBankAccountId,
                                                 MembershipId membershipId,
                                                 BankName bankName,
                                                 BankAccountNumber bankAccountNumber,
                                                 LinkedStatusIsValid linkedStatusIsValid) {

        return new RegisteredBankAccount(
                registeredBankAccountId.value,
                membershipId.value,
                bankName.value,
                bankAccountNumber.value,
                linkedStatusIsValid.value);
    }


    @Value
    public static class RegisteredBankAccountId {

        public RegisteredBankAccountId(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class MembershipId {

        public MembershipId(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class BankName {

        public BankName(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class BankAccountNumber {

        public BankAccountNumber(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class LinkedStatusIsValid {

        public LinkedStatusIsValid(boolean value) {
            this.value = value;
        }

        boolean value;
    }
}
