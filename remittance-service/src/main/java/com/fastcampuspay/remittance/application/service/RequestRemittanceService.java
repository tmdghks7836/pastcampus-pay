package com.fastcampuspay.remittance.application.service;


import com.fastcampuspay.common.UseCase;
import com.fastcampuspay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.fastcampuspay.remittance.adapter.out.persistence.RemittanceRequestMapper;
import com.fastcampuspay.remittance.adapter.out.service.membership.MembershipStatus;
import com.fastcampuspay.remittance.adapter.out.service.money.MoneyInfo;
import com.fastcampuspay.remittance.application.port.in.RequestRemittanceCommand;
import com.fastcampuspay.remittance.application.port.in.RequestRemittanceUseCase;
import com.fastcampuspay.remittance.application.port.out.BankingPort;
import com.fastcampuspay.remittance.application.port.out.MembershipPort;
import com.fastcampuspay.remittance.application.port.out.MoneyPort;
import com.fastcampuspay.remittance.application.port.out.RequestRemittancePort;
import com.fastcampuspay.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class RequestRemittanceService implements RequestRemittanceUseCase {

    private final RequestRemittancePort requestRemittancePort;

    private final RemittanceRequestMapper mapper;

    private final MembershipPort membershipPort;

    private final MoneyPort moneyPort;

    private final BankingPort bankingPort;

    @Override
    public RemittanceRequest requestRemittance(RequestRemittanceCommand command) {

        //송금 요청 상태를 시작 상태로 기록 (persistence layer)
        RemittanceRequestJpaEntity entity = requestRemittancePort.createRemittanceRequestHistory(command);

        //멤버십 상태 확인 (membership svc)
        MembershipStatus membershipStatus = membershipPort.getMembershipStatus(command.getFromMembershipId());

        if(!membershipStatus.isValid()){
            return null;
        }

        // 잔액 존재하는지 확인 (money svc)
        MoneyInfo moneyInfo = moneyPort.getMoneyInfo(command.getFromMembershipId());

        if(moneyInfo.getBalance() < command.getAmount()){

            int rechargeAmount = (int) Math.ceil((command.getAmount() - moneyInfo.getBalance()) / 10000.0) * 10000;

            // 잔액이 충분하지 않다면 충전요청 (money svc)
            boolean moneyResult =  moneyPort.requestMoneyRecharging(command.getFromMembershipId(), rechargeAmount);

            if(!moneyResult){
                return null;
            }
        }

        // 송금 타입 (고객/은행)
        if(command.getRemittanceType()==0){

            boolean remittanceResult1 = moneyPort.requestMoneyDecrease(command.getFromMembershipId(), command.getAmount());
            boolean remittanceResult2 = moneyPort.requestMoneyIncrease(command.getToMembershipId(), command.getAmount());
            //요청 실패하면
            if(!remittanceResult1 || !remittanceResult2){
                return null;
            }

        }else if(command.getRemittanceType() == 1){

            boolean remittanceResult = bankingPort.requestFirmbanking(command.getToBankName(), command.getToBankAccountNumber(), command.getAmount());
            if(!remittanceResult){
                return null;
            }
        }

        // 내부 고객일 경우
        // from 고객머니 감액, to 고객 머니 증액

        //외부 은행게좌 라면
        //외부은행 계좌가 적절한지 확인
        //법인계좌 -> 외부 은행 계좌 펌뱅킹 요청 (banking svc)

        //송금 요청 상태를 성공 상태로 기록 (persistence layer)
        entity.setRemittanceStatus("success");
        boolean result = requestRemittancePort.saveRemittanceRequestHistory(entity);

        if(!result){
          return null;
        }

        //송금 기록 (persistence svc) succeeded history

        return mapper.mapToDomainEntity(entity);
    }
}




