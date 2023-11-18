package com.fastcampuspay.money.domain.membermoney;

import com.fastcampuspay.money.application.port.in.CreateMemberMoneyCommand;
import com.fastcampuspay.money.application.port.in.IncreaseMemberMoneyCommand;
import com.fastcampuspay.money.application.port.in.RechargingMoneyRequestCreateCommand;
import com.fastcampuspay.money.application.port.out.GetRegisteredBankAccountPort;
import com.fastcampuspay.money.application.port.out.RegisteredBankAccountId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Slf4j
@Aggregate
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMoney {

    @AggregateIdentifier
    private String id;

    private String membershipId;

    private int balance;

    @CommandHandler
    public MemberMoney(CreateMemberMoneyCommand command) {
        log.debug("MemberMoneyCreateCommand Handler");
        this.id = UUID.randomUUID().toString();

        apply(new MemberMoneyCreatedEvent(id, command.getMembershipId(), 0));
    }

    @CommandHandler
    public void handle(IncreaseMemberMoneyCommand command) {

        log.debug("IncreaseMemberMoneyCommand Handler");
        id = command.getMemberMoneyId();
        apply(new MemberMoneyIncreasedEvent(command.getMembershipId(), command.getAmount()));
    }

    @CommandHandler
    public void handler(RechargingMoneyRequestCreateCommand command,
                        GetRegisteredBankAccountPort getRegisteredBankAccountPort){ // 의존성 주입 가능

        System.out.println("RechargingMoneyRequestCreateCommand CommandHandler");
        id = command.getMemberMoneyId();

        System.out.println("getRegisteredBankAccountPort.getRegisteredBankAccount");
        //banking 정보가 필요함.
        RegisteredBankAccountId registeredBankAccount = getRegisteredBankAccountPort.getRegisteredBankAccount(command.getMembershipId());

        System.out.println( "  registeredBankAccount.getRegisteredBankAccountId() = " + registeredBankAccount.getRegisteredBankAccountId() );

        apply(new RechargingRequestCreatedEvent(
                command.getRechargingRequestId(),
                command.getMembershipId(),
                command.getAmount(),
                registeredBankAccount.getRegisteredBankAccountId(),
                registeredBankAccount.getBankName(),
                registeredBankAccount.getBankAccountNumber()
        ));
    }


    @EventSourcingHandler
    public void on(MemberMoneyCreatedEvent event) {
        log.debug("MemberMoneyCreateEvent Sourcing Handler");
        id = event.getMemberMoneyId();
        membershipId = event.getMembershipId();
        balance = event.getMoneyAmount();
    }

    @EventSourcingHandler
    public void on(MemberMoneyIncreasedEvent event) {

        log.debug("MemberMoneyIncreasedEvent Sourcing Handler");
        balance += event.getAmount();
    }

    @Value
    public static class MemberMoneyId {

        public MemberMoneyId(String value) {
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
    public static class Balance {

        public Balance(int value) {
            this.value = value;
        }

        int value;
    }

    @Value
    public static class MoneyAggregateIdentifier {

        public MoneyAggregateIdentifier(String value) {
            this.value = value;
        }

        String value;
    }
}
