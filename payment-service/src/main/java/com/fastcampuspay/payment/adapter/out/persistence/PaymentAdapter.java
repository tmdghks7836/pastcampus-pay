package com.fastcampuspay.payment.adapter.out.persistence;

import com.fastcampuspay.common.PersistenceAdapter;
import com.fastcampuspay.payment.application.port.out.CreatePaymentPort;
import com.fastcampuspay.payment.domain.Payment;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PaymentAdapter implements CreatePaymentPort {

    private final SpringDataPaymentRepository paymentRepository;

    private final PaymentMapper mapper;

    @Override
    public Payment createPayment(String requestMembershipId, String requestPrice, String franchiseId, String franchiseFeeRate) {


        PaymentJpaEntity entity = paymentRepository.save(
                new PaymentJpaEntity(
                        requestMembershipId,
                        requestPrice,
                        franchiseId,
                        franchiseFeeRate,
                        0,
                        null
                )
        );

        return mapper.mapToDomainEntity(entity);
    }
}
