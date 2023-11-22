
package com.fastcampuspay.payment.adapter.out.persistence;

import com.fastcampuspay.payment.domain.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment mapToDomainEntity(PaymentJpaEntity entity){

        return new Payment(
                entity.getRequestMembershipId(),
                entity.getRequestPrice(),
                entity.getFranchiseId(),
                entity.getFranchiseFeeRate(),
                entity.getPaymentStatus(),
                entity.getApprovedAt()
        );
    }
}
