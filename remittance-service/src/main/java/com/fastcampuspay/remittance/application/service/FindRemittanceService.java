
package com.fastcampuspay.remittance.application.service;


import com.fastcampuspay.common.UseCase;
import com.fastcampuspay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.fastcampuspay.remittance.adapter.out.persistence.RemittanceRequestMapper;
import com.fastcampuspay.remittance.adapter.out.service.membership.MembershipStatus;
import com.fastcampuspay.remittance.adapter.out.service.money.MoneyInfo;
import com.fastcampuspay.remittance.application.port.in.FindRemittanceCommand;
import com.fastcampuspay.remittance.application.port.in.FindRemittanceUseCase;
import com.fastcampuspay.remittance.application.port.in.RequestRemittanceCommand;
import com.fastcampuspay.remittance.application.port.in.RequestRemittanceUseCase;
import com.fastcampuspay.remittance.application.port.out.*;
import com.fastcampuspay.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@UseCase
@Transactional
public class FindRemittanceService implements FindRemittanceUseCase {

    private final FindRemittancePort findRemittancePort;

    private final RemittanceRequestMapper mapper;

    @Override
    public List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command) {

        List<RemittanceRequestJpaEntity> remittanceHistory = findRemittancePort.findRemittanceHistory(command);

        return remittanceHistory.stream()
                .map(remittanceRequestJpaEntity -> mapper.mapToDomainEntity(remittanceRequestJpaEntity))
                .collect(Collectors.toList());

    }
}




