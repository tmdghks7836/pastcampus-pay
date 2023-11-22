package com.fastcampuspay.payment.application.port.out;

import com.fastcampuspay.payment.domain.Payment;

import java.util.Date;

public interface CreatePaymentPort {


    Payment createPayment(String requestMembershipId, String requestPrice, String franchiseId, String franchiseFeeRate);
}
