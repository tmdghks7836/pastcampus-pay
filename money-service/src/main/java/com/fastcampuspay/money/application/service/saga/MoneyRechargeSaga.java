package com.fastcampuspay.money.application.service.saga;

import com.fastcampuspay.common.event.*;
import com.fastcampuspay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.fastcampuspay.money.application.port.out.IncreaseMoneyPort;
import com.fastcampuspay.money.domain.membermoney.MemberMoney;
import com.fastcampuspay.money.domain.membermoney.RechargingRequestCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@NoArgsConstructor
public class MoneyRechargeSaga {

    @NonNull
    private transient CommandGateway commandGateway;

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    /**
     * rechargingRequestId 가지고 발행
     * 충전 요청을 시작
     * 은행 계좌 체크
     */
    @StartSaga
    @SagaEventHandler(associationProperty = "rechargingRequestId")
    public void handle(RechargingRequestCreatedEvent event) {

        System.out.println("RechargingRequestCreatedEvent Start saga");

        String sagaCheckRegisteredBankAccountId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("checkRegisteredBankAccountId", sagaCheckRegisteredBankAccountId);

        // 충전 요청이 시작되었다.

        //뱅킹 계좌등록 여부 확인하기 (RegisteredBankAccount)
        // CheckRegisteredBankAccountCommand -> CHeck Bank Account
        commandGateway.send(
                        new CheckRegisteredBankAccountCommand(
                                event.getRegisteredBankAccountId(),
                                event.getRechargingRequestId(),
                                event.getMembershipId(),
                                sagaCheckRegisteredBankAccountId,
                                event.getBankName(),
                                event.getBankAccountNumber(),
                                event.getAmount()
                        ))
                .whenComplete(
                        (result, throwable) -> {

                            if (throwable != null) {
                                throwable.printStackTrace();
                            } else {
                                //성공
                                //request firm db save
                                System.out.println("RegisterBankAccountCommand Success");

                            }
                        }
                );
    }

    //from banking service registeredBankAccount aggregate
    /**
     * 은행 체크완료로 부터 시작
     * 펌뱅킹 요청
     * */
    @SagaEventHandler(associationProperty = "checkRegisteredBankAccountId")
    public void handle(CheckedRegisteredBankAccountEvent event){

        System.out.println("CheckedRegisteredBankAccountEvent saga: " + event.toString());

        boolean status = event.isChecked();

        if (status) {
            System.out.println("CheckedRegisteredBankAccountEvent event success");
        }else{
            System.out.println("CheckedRegisteredBankAccountEvent event failed");
        }

        String sagaRequestFirmbankingId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("requestFirmbankingId", sagaRequestFirmbankingId);

        //송금요청
        commandGateway.send(new RequestFirmbankingCommand(
           sagaRequestFirmbankingId,
           event.getFirmbankingRequestId(),
                event.getRechargeRequestId(),
                event.getMembershipId(),
                event.getBankName(),
                event.getBankAccountNumber(),
                "fastcampus",
                "123456789",
                event.getAmount()
        )).whenComplete(
                (result, throwable) -> {

                    if (throwable != null) {
                        throwable.printStackTrace();
                    } else {
                        //성공
                        //request firm db save
                        System.out.println("RequestFirmbankingCommand Success");

                    }
                }
        );
    }

    /**
     * 펌뱅킹 요청 완료
     * */
    @SagaEventHandler(associationProperty = "requestFirmbankingId")
    public void handle(RequestFirmbankingFinishedEvent event, IncreaseMoneyPort increaseMoneyPort){

        System.out.println("RequestFirmbankingFinishedEvent saga: " + event.toString());

        boolean status = event.getStatus() == 0;

        if (status) {
            System.out.println("RequestFirmbankingFinishedEvent event success");
        }else{
            System.out.println("RequestFirmbankingFinishedEvent event failed");
        }

        MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                new MemberMoney.MembershipId(event.getMembershipId()),
                event.getMoneyAmount()
        );



        if(memberMoneyJpaEntity == null){

            String rollbackFirmbankingId = UUID.randomUUID().toString();

            SagaLifecycle.associateWith("rollbackFirmbankingId", rollbackFirmbankingId);

            commandGateway.send(new RollbackFirmbankingRequestCommand(
                    rollbackFirmbankingId,
                    UUID.randomUUID().toString(),
                    event.getRechargeRequestId(),
                    event.getMembershipId(),
                    event.getToBankName(),
                    event.getToBankAccountNumber(),
                    event.getMoneyAmount()
            )).whenComplete(
                    (result, throwable) -> {

                        if (throwable != null) {
                            throwable.printStackTrace();
                        } else {
                            //성공
                            //request firm db save
                            System.out.println("RollbackFirmbankingRequestCommand Success");

                        }
                    }
            );
        }else{
            //성공시 종료
            SagaLifecycle.end();
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "rollbackFirmbankingId")
    public void handle(RollbackFirmbankingFinishedEvent event){

        System.out.println("RollbackFirmbankingFinishedEvent saga: " + event.toString());
    }
}
