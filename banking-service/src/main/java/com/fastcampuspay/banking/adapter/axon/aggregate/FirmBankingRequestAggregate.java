package com.fastcampuspay.banking.adapter.axon.aggregate;

import com.fastcampuspay.banking.adapter.axon.command.CreateFirmbankingRequestCommand;
import com.fastcampuspay.banking.adapter.axon.command.UpdateFirmbankingRequestCommand;
import com.fastcampuspay.banking.adapter.axon.event.FirmbankingRequestCreatedEvent;
import com.fastcampuspay.banking.adapter.axon.event.FirmbankingRequestUpdatedEvent;
import com.fastcampuspay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.fastcampuspay.banking.adapter.out.external.bank.FirmbankingResult;
import com.fastcampuspay.banking.application.port.out.ExternalFirmbankingPort;
import com.fastcampuspay.banking.application.port.out.RequestFirmbankingPort;
import com.fastcampuspay.banking.domain.FirmbankingRequest;
import com.fastcampuspay.common.event.RequestFirmbankingCommand;
import com.fastcampuspay.common.event.RequestFirmbankingFinishedEvent;
import com.fastcampuspay.common.event.RollbackFirmbankingFinishedEvent;
import com.fastcampuspay.common.event.RollbackFirmbankingRequestCommand;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Data
public class FirmBankingRequestAggregate {

    @AggregateIdentifier
    private String id;

    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private int moneyAmount;

    private int firmbankingStatus;

    @CommandHandler
    public FirmBankingRequestAggregate(CreateFirmbankingRequestCommand command){

        System.out.println("CreateFirmbankingRequestCommand CommandHandler");

        apply(new FirmbankingRequestCreatedEvent(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount()));
    }

    @CommandHandler
    public String handle(UpdateFirmbankingRequestCommand command){

        System.out.println("UpdateFirmbankingRequestCommand");

        id = command.getAggregateIdentifier();
        apply(new FirmbankingRequestUpdatedEvent(command.getFirmbankingStatus()));

        return id;
    }

    @CommandHandler
    public FirmBankingRequestAggregate(RequestFirmbankingCommand command,
                                       RequestFirmbankingPort requestFirmbankingPort,
                                       ExternalFirmbankingPort externalFirmbankingPort){

        System.out.println("RequestFirmbankingCommand handler");
        id = command.getFirmbankingRequestId();

        requestFirmbankingPort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(0),
                new FirmbankingRequest.AggregateIdentifier(id)
        );

        System.out.println("externalFirmbankingPort.requestExternalFirmbanking");

        //외부 은행에 펌뱅킹 요청
        FirmbankingResult firmbankingResult = externalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount()
        ));

        int resultCode = firmbankingResult.getResultCode();

        System.out.println("resultCode = " + resultCode);

        //0 성공 1 실패
        apply(new RequestFirmbankingFinishedEvent(
                id,
                command.getRechargeRequestId(),
                command.getMembershipId(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                resultCode,
                command.getRequestFirmbankingId(),
                command.getMoneyAmount()
        ));
    }

    @CommandHandler
    public FirmBankingRequestAggregate(@NotNull RollbackFirmbankingRequestCommand command,
                                       RequestFirmbankingPort requestFirmbankingPort,
                                       ExternalFirmbankingPort externalFirmbankingPort){

        System.out.println("RollbackFirmbankingRequestCommand CommandHandler");
        id = UUID.randomUUID().toString();

        //rollbank 수행 (법인계좌 -> 고객계좌 펌뱅킹)
        requestFirmbankingPort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName("fastcampus-bank"),
                new FirmbankingRequest.FromBankAccountNumber("123-333-9999"),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(0),
                new FirmbankingRequest.AggregateIdentifier(command.getFirmbankingRequestId())
        );

        //firm banking
        FirmbankingResult firmbankingResult = externalFirmbankingPort.requestExternalFirmbanking(
                new ExternalFirmbankingRequest(
                        "fastcampus",
                        "123-3333-9999",
                        command.getToBankName(),
                        command.getToBankAccountNumber(),
                        command.getMoneyAmount()
                )
        );

        int resultCode = firmbankingResult.getResultCode();

        apply(new RollbackFirmbankingFinishedEvent(
                command.getRollbackFirmbankingId(),
                command.getMembershipId(),
                id
        ));
    }


    @EventSourcingHandler
    public void on(FirmbankingRequestCreatedEvent event){
        System.out.println("FirmbankingRequestCreatedEvent EventSourcingHandler");

        id = UUID.randomUUID().toString();
        fromBankName = event.getFromBankName();
        fromBankAccountNumber = event.getFromBankAccountNumber();
        toBankAccountNumber = event.getToBankAccountNumber();
        toBankName = event.getToBankName();
    }

    @EventSourcingHandler
    public void on(FirmbankingRequestUpdatedEvent event){
        System.out.println("FirmbankingRequestUpdatedEvent EventSourcingHandler");

        firmbankingStatus = event.getFirmbankingStatus();
    }

}
